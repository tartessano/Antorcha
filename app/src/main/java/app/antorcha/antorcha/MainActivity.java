package app.antorcha.antorcha;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;

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
        String res = bbdd.doInBackground();
        Log.d("HOLAAAAAAA", res);
        texto.setText("HOLA");

    }


}
