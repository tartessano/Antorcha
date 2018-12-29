package app.antorcha.antorcha;


import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
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
    private TextView textView4;

    BaseDatos bbdd = new BaseDatos();
    LoginAPI loginAPI = new LoginAPI();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    //Inicializamos las dos entradas de texto user y pass
      user = (EditText) findViewById(R.id.User);
      pass = (EditText) findViewById(R.id.Pass);

      textView4 = (TextView) findViewById(R.id.textView4);

    }



    public void pulsar (View v) throws Exception {

            //Capturamos las dos entradas de texto una vez pulsado el boton
            String userS = user.getText().toString();
            String passS = pass.getText().toString();

            //Se la pasamos al servidor
            //String response = loginAPI.execute(userS + "/" + passS).get();
            //textView4.setText(response);
                Log.d("HASTA", "AQUI4");

           /* if (response == "[]"){

                Toast toast = Toast.makeText(getApplicationContext(), "Error Login", Toast.LENGTH_SHORT);
                toast.show();
            }else{

                JSONObject jsonObject = new JSONObject(response);
                String cmpUser = jsonObject.getString("User");
                if(cmpUser.equals(userS)){
                    Toast toast = Toast.makeText(getApplicationContext(), "Login Correcto", Toast.LENGTH_SHORT);
                    toast.show();
                }

            }*/


            //String response = bbdd.execute("http://188.166.81.174:5000/cliente").get();

       // String response = bbdd.execute("http://188.166.81.174:5000/cliente").get();
        String response = LoginAPI.sendPost();
        textView4.setText(response);




           // String User = jsonObject.getString("User");

    }


}
