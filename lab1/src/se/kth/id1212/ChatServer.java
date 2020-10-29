package se.kth.id1212;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

/**
 * This ChatServer contains two threads one thread
 * to each of the clients currently connected and
 * sends the messages to other connected clients
 * one thread to listen for new incoming connections
 * from new clients.
 */
public class ChatServer {
    private int port;
    private Set<UserThread> userThreads = new HashSet<>();

    public ChatServer(int port) {
        this.port = port;
    }

    public void execute() {
        try {
            ServerSocket serverSocket = new ServerSocket(this.port);

            while (true) {
                Socket socket = serverSocket.accept();

                UserThread newUser = new UserThread(socket, this);
                userThreads.add(newUser);
                newUser.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void broadcast(String message, UserThread user) {
        for (UserThread u: userThreads) {
            if (u != user) {
                u.printMessage(message);
            }
        }
    }

    public void remove(UserThread user) {
        userThreads.remove(user);
    }

    public static void main(String[] args) {
        int port = 3000;
        ChatServer chatServer = new ChatServer(port);

        chatServer.execute();
    }

    public class UserThread extends Thread {
        private Socket socket;
        private ChatServer chatServer;
        private PrintStream ps;

        public UserThread(Socket socket, ChatServer chatServer) {
            this.socket = socket;
            this.chatServer = chatServer;
        }

        @Override
        public void run() {
            try {
                InputStream in = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                OutputStream out = socket.getOutputStream();
                ps = new PrintStream(out);

                String serverMessage;
                String clientMessage = reader.readLine();

                while (!clientMessage.equalsIgnoreCase("quit")) {
                    serverMessage = "Port " + socket.getPort() + ": " + clientMessage;
                    chatServer.broadcast(serverMessage, this);
                    clientMessage = reader.readLine();
                }

                chatServer.remove(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void printMessage(String message) {
            ps.println(message);
        }
    }
}
