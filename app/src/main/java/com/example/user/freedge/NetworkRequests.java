package com.example.user.freedge;


import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class NetworkRequests extends AsyncTask<String, Void, String> {

    private Context context;

    public NetworkRequests(Context con) {
        this.context = con;
    }

    @Override
    protected String doInBackground(String... uri) {
        try {
            URL url = new URL(uri[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            Toast toast = Toast.makeText(context, "Код ответа: " + String.valueOf(responseCode), Toast.LENGTH_SHORT);
            toast.show();
        } catch (MalformedURLException e) {
            Toast toast = Toast.makeText(context, String.valueOf(e), Toast.LENGTH_SHORT);
            toast.show();
        } catch (IOException e) {
            Toast toast = Toast.makeText(context, String.valueOf(e), Toast.LENGTH_SHORT);
            toast.show();
        } catch (Exception e) {
            Toast toast = Toast.makeText(context, String.valueOf(e), Toast.LENGTH_SHORT);
            toast.show();
        }
        return "";
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        //Do anything with response..*/
    }
}
