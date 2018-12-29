package app.antorcha.antorcha.librerias;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginPost extends AsyncTask<String, Void, String> {


    protected  String doInBackground(String... urls) {
        // params comes from the execute() call: params[0] is the url.
        try {
            try {
                String user = urls[0];
                String pass = urls[1];
                return HttpPost("http://206.189.106.150:5000/login", user, pass);
            } catch (JSONException e) {
                e.printStackTrace();
                return "Error!";
            }
        } catch (IOException e) {
            return "Unable to retrieve web page. URL may be invalid.";
        }
    }
// onPostExecute displays the results of the AsyncTask.



    protected static String HttpPost(String myUrl, String user, String pass) throws IOException, JSONException {
        String result = "";

        URL url = new URL(myUrl);

        // 1. create HttpURLConnection
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");

        // 2. build JSON object
        JSONObject jsonObject = buidJsonObject(user, pass);

        // 3. add JSON content to POST request body
        setPostRequestContent(conn, jsonObject);

        // 4. make POST request to the given URL
        conn.connect();

        InputStream in = new BufferedInputStream(conn.getInputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line = "";
        StringBuffer buffer = new StringBuffer();

        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }

        in.close();

        conn.connect();

        // 5. return response message
        return buffer.toString();

    }

    protected static JSONObject buidJsonObject(String user, String pass) throws JSONException {


        JSONObject jsonObject = new JSONObject();
        jsonObject.accumulate("User", user);
        jsonObject.accumulate("Pass",  pass);


        return jsonObject;
    }

    protected static  void setPostRequestContent(HttpURLConnection conn,
                                                 JSONObject jsonObject) throws IOException {

        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        writer.write(jsonObject.toString());
        Log.i("JSON", jsonObject.toString());
        writer.flush();
        writer.close();
        os.close();





    }






}
