package app.antorcha.antorcha;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import app.antorcha.antorcha.librerias.BaseDatos;
import app.antorcha.antorcha.librerias.LoginAPI;


public class MainActivity extends AppCompatActivity {

    private EditText user;
    private EditText pass;


    BaseDatos bbdd = new BaseDatos();
    LoginAPI loginAPI = new LoginAPI();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    //Inicializamos las dos entradas de texto user y pass
      user = (EditText) findViewById(R.id.User);
      pass = (EditText) findViewById(R.id.Pass);


    }



    public void pulsar (View v) throws ExecutionException, InterruptedException, JSONException {

            //Capturamos las dos entradas de texto una vez pulsado el boton
            String userS = user.getText().toString();
            String passS = pass.getText().toString();

            //Se la pasamos al servidor
            String response = loginAPI.execute(userS + "/" + passS).get();

            if (response == "null"){

                Toast toast = Toast.makeText(getApplicationContext(), "Error Login", Toast.LENGTH_SHORT);
                toast.show();
            }


            //String response = bbdd.execute("http://188.166.81.174:5000/cliente").get();

            JSONArray jsonArray = new JSONArray(response);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            
           // String User = jsonObject.getString("User");

    }


}
