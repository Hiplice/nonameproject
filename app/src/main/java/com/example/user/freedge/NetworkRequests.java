package com.example.user.freedge;


import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetworkRequests extends AsyncTask<String, String, String> {

    private Context context;

    public NetworkRequests(Context con) {
        context = con;
    }

    @Override
    protected String doInBackground(String... uri) {
        StringBuffer response = new StringBuffer();
        try {
            URL url = new URL(uri[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
        return response.toString();
    }
}
