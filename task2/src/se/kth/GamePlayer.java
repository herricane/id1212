package se.kth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.HttpURLConnection;

public class GamePlayer {
    private int times;
    private URL url;

    public GamePlayer(int times) {
        this.times = times;
        try {
            this.url = new URL("http", "127.0.0.1", 8090, "/");
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int guessTimes() {
        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        con.setRequestProperty("Accept", "text/html");
        con.setRequestProperty("Cache-Control", "max-age=0");
        con.setRequestProperty("Connection", "keep-alive");
        con.setRequestProperty("User-Agent","Mozilla/5.0");

        try {
            con.connect();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }

        String row;
        try{
            while( (row=reader.readLine()) != null){
                System.out.println(row);
            }
        }
        catch(IOException e){
            System.out.println("#5 " + e.getMessage());
        }

        System.out.println(con.getHeaderField("Cookie"));

        return 0;
    }

    public static void main(String[] args) {
        GamePlayer gp = new GamePlayer(100);
//        float avg = 0;
//
//        for (int i = 0; i < gp.times; i++) {
//            int count = gp.guessTimes();
//            avg += count;
//        }
//
//        avg /= gp.times;
//        System.out.println(avg);
        gp.guessTimes();
    }
}
