package se.kth.id1212;

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

            System.out.println("Connected to the chat server at " + serverHost + ":" + serverPort);

            new Thread(() -> {
                try {
                    read(socket);
                } catch (IOException e) {
                    // e.printStackTrace();
                }
            }).start();
            new Thread(() -> {
                try {
                    write(socket);
                } catch (IOException e) {
                    // e.printStackTrace();
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void read(Socket socket) throws IOException {
        InputStream input = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        while (true) {
            String response = reader.readLine();
            if (response == null) {
                socket.close();
                System.out.println("The server is shut down.");
                System.out.println("You have quit the chat.");
                System.exit(1);
            }
            System.out.println(response);
        }
    }

    public void write(Socket socket) throws IOException {
        PrintStream printer = new PrintStream(socket.getOutputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String text = reader.readLine();
        while (text != null && !text.equalsIgnoreCase("quit")) {
            if (!text.strip().equals("")) {
                printer.println(text);
            }
            text = reader.readLine();
        }

        socket.close();
        System.out.println("You have quit the chat.");
        System.exit(0);
    }

    public static void main(String[] args) {
        int port = 3000;

        ChatClient client = new ChatClient("localhost", port);
        client.execute();
    }
}