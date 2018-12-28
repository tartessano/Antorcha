package app.antorcha.antorcha.librerias;


import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class BaseDatos extends AsyncTask<String, String, String> {

    protected String doInBackground(String... strings) {
        HttpURLConnection httpURLConnection = null;
        URL url = null;
        try {
            url = new URL(strings[0]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();
            int code =  httpURLConnection.getResponseCode();

            if(code == HttpURLConnection.HTTP_OK){
                InputStream in = new BufferedInputStream (httpURLConnection.getInputStream());
                BufferedReader reader = BufferedReader(new InputStreamReader(in));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }



    }

}