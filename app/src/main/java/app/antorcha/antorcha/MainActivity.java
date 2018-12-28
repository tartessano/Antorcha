package app.antorcha.antorcha;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;

import android.widget.TextView;

import java.util.concurrent.ExecutionException;

import app.antorcha.antorcha.librerias.BaseDatos;


public class MainActivity extends AppCompatActivity {
    private TextView texto;
    BaseDatos bbdd = new BaseDatos();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        texto =  findViewById(R.id.textView);

    }



    public void pulsar (View v){

        try {

            String response = bbdd.execute("http://188.166.81.174:5000/cliente").get();

            texto.setText(response);


        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


}
