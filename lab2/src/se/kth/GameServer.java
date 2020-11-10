package se.kth;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GameServer {
    private int port;
    private Map<String, GuessGame> games = new HashMap<>();

    public GameServer(int port) {
        this.port = port;
    }

    public void execute() {
        try {
            ServerSocket ss = new ServerSocket(this.port);
            while (true) {
                Socket s = ss.accept();
                BufferedReader request = new BufferedReader(new InputStreamReader(s.getInputStream()));
                PrintStream response = new PrintStream(s.getOutputStream());

                String requestLine = request.readLine();
                String file = requestLine.split(" ")[1];

                if (file.equals("/") || file.contains("/?guess=")) {
                    String guessStr = parseGuess(file);
                    String userId = parseId(request);

                    try {
                        int guess = Integer.parseInt(guessStr);
                        if (userId.equals("0") || guess == -1) {
                            String uid = UUID.randomUUID().toString();
                            GuessGame game = new GuessGame(uid);
                            this.games.put(uid, game);
                            initResponse(response, game);
                        } else {
                            GuessGame game = this.games.get(userId);
                            if (guess < 1 || guess > 100) {
                                errorResponse(response, game);
                            } else {
                                guessResponse(response, game, guess);
                            }
                        }
                    } catch (NumberFormatException e) {
                        GuessGame game = this.games.get(userId);
                        errorResponse(response, game);
                    }
                }

                s.shutdownOutput();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeHeaders(PrintStream response, GuessGame game, int status) {
        response.println("HTTP/1.1 " + status + (status == 200 ? "OK" : "Not Found"));
        response.println("Server: Trash 0.1 Beta");
        response.println("Content-Type: text/html");
        response.println("Set-Cookie: userId=" + game.getId());
        response.println();
    }

    public void initResponse(PrintStream response, GuessGame game) {
        writeHeaders(response, game, 200);
        response.println(game.initPage());
    }

    public void guessResponse(PrintStream response, GuessGame game, int guess) {
        writeHeaders(response, game, 200);
        response.println(game.guessPage(guess));
    }

    public void errorResponse(PrintStream response, GuessGame game) {
        writeHeaders(response, game, 404);
        response.println(game.errorPage());
    }

    public String parseGuess(String file) {
        String guess = "-1";

        if (file.contains("guess")) {
            String[] pair = file.split("=");
            if (pair.length > 1) {
                guess = pair[1];
            }
        }
        return guess;
    }

    public String parseId(BufferedReader request) {
        String userId = "0";

        String str = null;
        try {
            while ((str = request.readLine()) != null && str.length() > 0) {
                if (str.contains("userId=")) {
                    userId = str.split("userId=")[1].substring(0, 36);
                }
            }
        } catch (ArrayIndexOutOfBoundsException | IOException e) {
            System.out.println(e);
        }

        return userId;
    }

    public static void main(String[] args) {
        int port = 8090;
        GameServer gameServer = new GameServer(port);

        gameServer.execute();
    }
}
