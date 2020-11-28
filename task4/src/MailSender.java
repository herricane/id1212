import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class MailSender {
    private String username = "chenhui";
    private String password = "Xch159357";
    private int delay = 1000;

    public MailSender() {}

    private void execute() {
        try {
            Socket socket = new Socket("smtp.kth.se", 587);

            new Thread(() -> {
                try {
                    readFromServer(socket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            sendToServer(socket, "EHLO " + InetAddress.getLocalHost().getHostAddress() + "\r\n");
            sendToServer(socket, "STARTTLS\r\n");

            SSLSocket sslSocket = (SSLSocket) ((SSLSocketFactory) SSLSocketFactory.getDefault())
                    .createSocket(socket, "smtp.kth.se", 587, true);
            sslSocket.startHandshake();

            new Thread(() -> {
                try {
                    readFromServer(sslSocket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            sendToServer(sslSocket, "EHLO " + InetAddress.getLocalHost().getHostAddress() + "\r\n");
            sendToServer(sslSocket, "AUTH LOGIN\r\n");
            sendToServer(sslSocket, encode(username) + "\r\n");
            sendToServer(sslSocket, encode(password) + "\r\n");
            sendToServer(sslSocket, "MAIL FROM:<chenhui@kth.se>\r\n");
            sendToServer(sslSocket, "RCPT TO:<xiaoyiwe@kth.se>\r\n");
            sendToServer(sslSocket, "DATA\r\n");
            sendToServer(sslSocket, "Subject: ID1212 Test\r\n");
            sendToServer(sslSocket, "Hello Sylvia!\r\n");
            sendToServer(sslSocket, ".\r\n");
            sendToServer(sslSocket, "QUIT\r\n");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void readFromServer(Socket socket) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println("SERVER: " + line);
            if(line.endsWith("start TLS")) {
                break;
            }
        }
    }

    private void sendToServer(Socket socket, String string) throws IOException, InterruptedException {
        System.out.print("CLIENT: " + string);
        PrintStream ps = new PrintStream(socket.getOutputStream());
        ps.print(string);
        Thread.sleep(this.delay);
    }

    private static String encode(String string) {
        return Base64.getEncoder().encodeToString(string.getBytes(StandardCharsets.UTF_8));
    }

    public static void main(String[] args) {
        MailSender ms = new MailSender();
        ms.execute();
    }
}
