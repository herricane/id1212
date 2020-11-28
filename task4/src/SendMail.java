import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class SendMail {

    private static DataOutputStream dataOutStream;

    public static void main(String[] args) throws Exception {

        int delay = 1000;
        String user = "chenhui";
        String pass = "Xch159357";
        String username = Base64.getEncoder().encodeToString(user.getBytes(StandardCharsets.UTF_8));
        String password = Base64.getEncoder().encodeToString(pass.getBytes(StandardCharsets.UTF_8));
        Socket socket = new Socket("smtp.kth.se", 587);
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        new Thread(() -> {
                String line;

                try {
                    while ((line = br.readLine()) != null) {
                        System.out.println("SERVER: "+line);

                        if(line.endsWith("start TLS\r\n")) {
                            break;
                        }
                    }
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        ).start();

        dataOutStream = new DataOutputStream(socket.getOutputStream());

        send("EHLO " + InetAddress.getLocalHost().getHostAddress() + "\r\n");
        send("STARTTLS\r\n");

        SSLSocket sslSocket = (SSLSocket) ((SSLSocketFactory) SSLSocketFactory.getDefault()).createSocket(socket, "smtp.kth.se", 587, true);
        sslSocket.startHandshake();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
        dataOutStream = new DataOutputStream(sslSocket.getOutputStream());


        new Thread(() -> {
            String line;

            try {
                while ((line = bufferedReader.readLine()) != null)
                    System.out.println("SERVER: "+line);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        ).start();



        send("EHLO " + InetAddress.getLocalHost().getHostAddress() + "\r\n");
        Thread.sleep(delay);
        send("AUTH LOGIN\r\n");
        Thread.sleep(delay);
        send(username+"\r\n");
        Thread.sleep(delay);
        send(password+"\r\n");
        Thread.sleep(delay);
        send("MAIL FROM:<chenhui@kth.se>\r\n");
        Thread.sleep(delay);
        send("RCPT TO:<xiaoyiwe@kth.se>\r\n");
        Thread.sleep(delay);
        send("DATA\r\n");
        Thread.sleep(delay);
        send("Subject: ID1212 Test\r\n");
        Thread.sleep(delay);
        send("Hello Sylvia!\r\n");
        Thread.sleep(delay);
        send(".\r\n");
        Thread.sleep(delay);
        send("QUIT\r\n");
    }

    private static void send(String s) throws Exception {
        dataOutStream.writeBytes(s);
        System.out.println("CLIENT: "+s);
    }

    private static void readInput() {
        int input;
        String allInput = "";

    }

}
