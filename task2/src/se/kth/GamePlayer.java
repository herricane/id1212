package se.kth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;

public class GamePlayer {
    private int trials = 100;
    private String host = "http://localhost:8090/";

    public GamePlayer(int trials) {
        this.trials = trials;
    }

    private HttpURLConnection getConnection(String location) throws IOException {
        URL url = new URL(this.host + location);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent","Mozilla/5.0");

        return con;
    }

    private String getResponseBody(HttpURLConnection con) {
        String responseBody = "";
        try {
            con.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            responseBody = reader.readLine();
            con.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseBody;
    }

    public String getCookie() {
        String cookie = "";
        try {
            HttpURLConnection con = getConnection("");
            con.connect();
            cookie = con.getHeaderField("Set-Cookie");
            con.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cookie;
    }

    private int getResult(int guess, String cookie) {
        try {
            HttpURLConnection con = getConnection("?guess=" + guess);
            con.setRequestProperty("Cookie", cookie);
            String responseBody = getResponseBody(con);
            if (responseBody.contains("higher")) {
                return -1;
            } else if (responseBody.contains("lower")) {
                return 1;
            } else {
                return 0;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public int guessTimes(int low, int high, String cookie) {
        int count = 1;
        int guess = (low + high) / 2;

        int result = getResult(guess, cookie);
        if (result == 1) {
            count += guessTimes(low, guess - 1, cookie);
        } else if (result == -1) {
            count += guessTimes(guess + 1, high, cookie);
        }

        return count;
    }

    public static void main(String[] args) {
        GamePlayer gp = new GamePlayer(100);
        double avg = 0;

        for (int i = 0; i < gp.trials; i++) {
            String cookie = gp.getCookie();
            int times = gp.guessTimes(1, 100, cookie);
            System.out.println(times);
            avg += times;
        }
        avg /= gp.trials;

        System.out.println(avg);
    }
}
