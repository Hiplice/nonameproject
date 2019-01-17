package com.example.user.freedge;


import android.os.AsyncTask;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class NetworkRequests extends AsyncTask<String, Void, Void> {

    @Override
    protected Void doInBackground(String... uri) {
        /*try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                // Do normal input or output stream reading
            } else {
                response = "FAILED"; // See documentation for more info on response handling
            }
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        //Do anything with response..*/
        return null;
    }
}
