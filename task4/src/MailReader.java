import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class MailReader {
    private static DataOutputStream dataOutStream;

    public static void main(String[] args) throws Exception {

        int delay = 1000;
        String host = "webmail.kth.se";
        String username = "chenhui";
        String password = "Xch159357";

        SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();
        HttpsURLConnection.setDefaultSSLSocketFactory(sf);
        SSLSocket socket = null;

        try {
            socket = (SSLSocket) sf.createSocket(host, 993);
        } catch (IOException e) {
            e.printStackTrace();
        }

        final BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        (new Thread(new Runnable() {
            @Override
            public void run() {
                String line;

                try {
                    while ((line = br.readLine()) != null)
                        System.out.println("SERVER: "+line);
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        })).start();

        dataOutStream = new DataOutputStream(socket.getOutputStream());

        send("A1 login " + username + " "+ password + "\r\n");
        Thread.sleep(delay);
        send("A1 list \"\" *\r\n");
        Thread.sleep(delay);
        send("A1 select inbox\r\n");
        Thread.sleep(delay);
        send("A1 fetch 612 all\r\n");

    }

    private static void send(String s) throws Exception {
        dataOutStream.writeBytes(s);
        System.out.println("CLIENT: "+s);
    }

}
