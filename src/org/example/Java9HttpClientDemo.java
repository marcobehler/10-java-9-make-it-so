package org.example;

import jdk.incubator.http.HttpClient;
import jdk.incubator.http.HttpRequest;
import jdk.incubator.http.HttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

/**
 * Thanks for watching this episode! If you want to, drop me a line to info@marcobehler.com.
 */
public class Java9HttpClientDemo {

    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {

        // 1. -- prehistoric java!
        URL mbDotCom = new URL("https://www.marcobehler.com/");
        try (BufferedReader in = new BufferedReader(new InputStreamReader(mbDotCom.openStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
            }
        }

        System.out.println("#################");

        // 2. -- more convenient and flexible options came with Apache HTTP Client or OkHTTP

        /*
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(https://www.marcobehler.com/)
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
        */

        // 3. -- BUT NOW > java 9

        HttpClient client = HttpClient.newBuilder()
                .authenticator(new Authenticator() {
                    @Override  // http basic
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("someUser", "somePass".toCharArray());
                    }
                })
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(new URI("https://www.marcobehler.com"))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandler.asString());

        System.out.println(response.statusCode());
        System.out.println(response.body());
    }
}
