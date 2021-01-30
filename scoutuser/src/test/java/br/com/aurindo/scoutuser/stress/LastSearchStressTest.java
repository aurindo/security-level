package br.com.aurindo.scoutuser.stress;


import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

public class LastSearchStressTest {

    public static void doRequest(int i) {
        try {
            URL url = new URL("http://localhost:8081/scout-user/92706916320");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");


            System.out.println(new Date().toString() + " - Response from " + i + ": " + con.getResponseCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createThread(int i) {
        Thread newThread = new Thread( () -> {
            System.out.println("==> " + i);
            doRequest(i);
        });

        newThread.start();
    }

    public static void main(String args[]) {

        for (int i = 0; i < 999; i++) {
            createThread(i);
        }

    }

}
