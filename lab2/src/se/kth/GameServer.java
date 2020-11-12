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
                    s.shutdownInput();

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
                } else {
                    s.shutdownInput();
                }

                s.shutdownOutput();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String writeHeaders(GuessGame game, int status) {
        String headers = "HTTP/1.1 " + status + (status == 200 ? " OK" : " Not Found") + "\n";
        headers += "Server: Trash 0.1 Beta\n";
        headers += "Content-Type: text/html\n";
        headers += "Set-Cookie: userId=" + game.getId();
        headers += "\n\n";
        return headers;
    }

    public void initResponse(PrintStream response, GuessGame game) {
        String respStr = writeHeaders(game, 200);
        respStr += game.initPage();
        response.println(respStr);
    }

    public void guessResponse(PrintStream response, GuessGame game, int guess) {
        String respStr = writeHeaders(game, 200);
        respStr += game.guessPage(guess);
        response.println(respStr);
    }

    public void errorResponse(PrintStream response, GuessGame game) {
        String respStr = writeHeaders(game, 404);
        respStr += game.errorPage();
        response.println(respStr);
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
