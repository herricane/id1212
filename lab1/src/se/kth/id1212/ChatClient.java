package se.kth.id1212;

import java.awt.print.PrinterGraphics;
import java.io.*;
import java.net.Socket;

/**
 * The ChatClient contains two threads,
 * one to listen for incoming messages from the server
 * and one to send messages to the server.
 */
public class ChatClient {
    private String serverName;
    private int serverPort;

    public ChatClient(String serverName, int serverPort){
        this.serverName = serverName;
        this.serverPort = serverPort;
    }

    public void execute() {
        try{
            Socket socket = new Socket(serverName, serverPort);

            System.out.println("Connected to the chat server.");

            new ReadThread(socket, this).start();
            new WriteThread(socket, this).start();

        }catch (IOException e){
            //handle the exceptions
        }

    }

    public static void main(String[] args) {
        int port = 3000;

        ChatClient client = new ChatClient("localhost", port);
        client.execute();
    }

    public class ReadThread extends Thread{
        private BufferedReader reader;
        private Socket socket;
        private ChatClient client;

        public ReadThread(Socket socket, ChatClient client){
            this.socket = socket;
            this.client = client;

            try{
                InputStream input = socket.getInputStream();
                reader = new BufferedReader(new InputStreamReader(input));
            }catch (IOException e){
                //handle the exceptions
            }
        }
        public void run(){
            while (true){
                try{
                    String response = reader.readLine();
                    System.out.println(response);
                }catch (IOException e){
                    //handle the exceptions
                }
            }
        }
    }

    public class WriteThread extends Thread{
        private Socket socket;
        private ChatClient client;

        public WriteThread(Socket socket, ChatClient client){
            this.socket = socket;
            this.client = client;

            try{
                OutputStream output = socket.getOutputStream();
            }catch (IOException e){
                //handle the exceptions
            }
        }
        public void run(){
            PrintStream out = null;
            try {
                out = new PrintStream(socket.getOutputStream());
                BufferedReader indata = new BufferedReader(new InputStreamReader(System.in));
                String text = indata.readLine();
                while (!text.equalsIgnoreCase("bye")){
                    if (!text.strip().equals("")) {
                        out.println(text);
                    }
                    text = indata.readLine();
                }
                socket.close();
                System.out.println("You quit the chat.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }




    
}
