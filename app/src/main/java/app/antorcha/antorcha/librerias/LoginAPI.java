package app.antorcha.antorcha.librerias;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


public class LoginAPI {


   /* protected String doInBackground(String... strings) {

        String line = "";
        try {
            Log.d("HASTA", "AQUI2");
            //String parametros = strings.toString();
           // Log.i("HASTA", parametros);
            String user , Pass;
           // String[] tokens = parametros.split("/");
           // user = tokens[0];
            //Pass = tokens[1];
            user = "pol";
            Pass = "patata123";
            Log.d("HASTA", "AQUI2");
            StringBuffer buffer = new StringBuffer();
            Log.d("HASTA", "AQUIJDOER");

            String http="http://188.166.81.174:5000/login";
            URL url=new URL(http);
            Log.d("HASTA", "AQUI");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            Log.d("HASTA", "AQUI");
            con.connect();
            int code = con.getResponseCode();

            if (code == HttpURLConnection.HTTP_OK) {
                con.setDoOutput(true);
                con.setDoInput(true);
                con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                con.setRequestMethod("POST");

                JSONObject cred = new JSONObject();
                cred.put("User", user);
                cred.put("Pass", Pass);

                OutputStream os = con.getOutputStream();
                os.write(cred.toString().getBytes("UTF-8"));
                os.close();
            }else{

                Log.d("ERROR", "CONECTAR");

            }

           /* InputStream in = new BufferedInputStream(con.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));


            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            in.close();

        }
        catch (Exception e){
            Log.v("ErrorAPP",e.toString());
        }

        return "[]";
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
    }*/
    public static String sendPost() throws Exception {

        String user , Pass;

        user = "pol";
        Pass = "patata123";

    String url = "https://selfsolve.apple.com/wcResults.do";
    URL obj = new URL(url);
    HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

    //add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", "USER_AGENT");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");



    // Send post request
		con.setDoOutput(true);
    DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        JSONObject cred = new JSONObject();
        cred.put("User", user);
        cred.put("Pass", Pass);



        wr.write(cred.toString().getBytes("UTF-8"));
		wr.flush();
		wr.close();



    BufferedReader in = new BufferedReader(
            new InputStreamReader(con.getInputStream()));
    String inputLine;
    StringBuffer response = new StringBuffer();

    while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
    }

            in.close();


    return response.toString();
}



}





