package se.kth;

import java.io.*;
import java.net.Socket;

/**
 * The ChatClient contains two threads,
 * one to listen for incoming messages from the server
 * and one to send messages to the server.
 */
public class ChatClient {
    private String serverHost;
    private int serverPort;

    public ChatClient(String serverHost, int serverPort) {
        this.serverHost = serverHost;
        this.serverPort = serverPort;
    }

    public void execute() {
        try {
            Socket socket = new Socket(serverHost, serverPort);

            System.out.println("Connected to the chat server at " + serverHost + ":" + serverPort + ".");
            System.out.println("You are at port " + socket.getLocalPort() + ".");

            new Thread(() -> {
                try {
                    listenMessage(socket);
                } catch (IOException e) {
                    // e.printStackTrace();
                }
            }).start();
            new Thread(() -> {
                try {
                    sendMessage(socket);
                } catch (IOException e) {
                    // e.printStackTrace();
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listenMessage(Socket socket) throws IOException {
        InputStream input = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        while (true) {
            String message = reader.readLine();
            if (message == null) {
                socket.close();
                System.out.println("The server is shut down.");
                System.out.println("You have quit the chat.");
                System.exit(1);
            }
            System.out.println(message);
        }
    }

    public void sendMessage(Socket socket) throws IOException {
        PrintStream printer = new PrintStream(socket.getOutputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String message;

        do {
            message = reader.readLine();
            if (!message.strip().equals("")) {
                printer.println(message);
            }
        } while (!message.equalsIgnoreCase("quit"));

        socket.close();
        System.out.println("You have quit the chat.");
        System.exit(0);
    }

    public static void main(String[] args) {
        String host = "localhost";
        int port = 3000;

        ChatClient client = new ChatClient(host, port);
        client.execute();
    }
}