package app.antorcha.antorcha.librerias;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class LoginAPI extends AsyncTask<String,String,String>{


    @Override
    protected String doInBackground(String... params) {

        String line = "";
        try {

            String parametros = params.toString();
            String user , Pass;
            String[] tokens = parametros.split("/");
            user = tokens[0];
            Pass = tokens[1];
            StringBuffer buffer = new StringBuffer();


            String http="http://188.166.81.174:5000/login";
            URL url=new URL(http);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestMethod("POST");

            JSONObject cred = new JSONObject();
            cred.put("User",user);
            cred.put("Pass",Pass);

            OutputStream os = con.getOutputStream();
            os.write(cred.toString().getBytes("UTF-8"));
            os.close();


            InputStream in = new BufferedInputStream(con.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));


            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }



        }
        catch (Exception e){
            Log.v("ErrorAPP",e.toString());
        }

        return line;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }
}
