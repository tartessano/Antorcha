package app.antorcha.antorcha;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;


public class MainActivity extends AppCompatActivity {

    private EditText correo;
    private EditText pass;
    private TextView textView4;
    private Button btnLogin;
    private FirebaseAuth mAuth;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    //Inicializamos las dos entradas de texto user y pass
      correo = (EditText) findViewById(R.id.User);
      pass = (EditText) findViewById(R.id.Pass);
      textView4 = (TextView) findViewById(R.id.textView4);
      btnLogin = (Button) findViewById(R.id.button);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String CorreoS = correo.getText().toString();
                String PassS = pass.getText().toString();
                mAuth = FirebaseAuth.getInstance();



                if(isValidEmail(CorreoS) )  {

                    Toast.makeText(MainActivity.this, "Validaciones funcionando.", Toast.LENGTH_SHORT).show();
                    mAuth.signInWithEmailAndPassword(CorreoS, PassS)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Toast.makeText(MainActivity.this, "Se logeo correctamente.", Toast.LENGTH_SHORT).show();
                                        nextActivity();
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        FirebaseAuthException e = (FirebaseAuthException )task.getException();
                                        Toast.makeText(MainActivity.this, "Error al iniciar sesion: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });


                }else{

                    Toast.makeText(MainActivity.this, "Formato de correo invalido", Toast.LENGTH_SHORT).show();
                }


            }
        });




    }

    public void registrarseActivity(View v){


        startActivity(new Intent(MainActivity.this, Register.class));
        finish();




    }



    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }



    /*public void pulsar (View v) throws Exception {

            //Capturamos las dos entradas de texto una vez pulsado el boton
            String userS = user.getText().toString();
            String passS = pass.getText().toString();
            String array[];
            array = new String[]  {userS,passS};

            LoginPost loginpost = new LoginPost();

            String response =  loginpost.execute(array).get();
            textView4.setText(response);

            //Se la pasamos al servidor
            //String response = loginAPI.execute(userS + "/" + passS).get();
            //textView4.setText(response);
                Log.d("HASTA", response);

            if (response.equals("[]")) {

                Toast toast = Toast.makeText(getApplicationContext(), "Error Login", Toast.LENGTH_SHORT);
                toast.show();
            }


            //String response = bbdd.execute("http://188.166.81.174:5000/cliente").get();

       // String response = bbdd.execute("http://188.166.81.174:5000/cliente").get();




           // String User = jsonObject.getString("User");

    }*/


    /*protected void onResume() {
        super.onResume();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){
            Toast.makeText(this, "Usuario logeado.", Toast.LENGTH_SHORT).show();
            nextActivity();
        }
    }*/

    private void nextActivity(){
        startActivity(new Intent(MainActivity.this,prueba1.class));
        finish();

    }


}

