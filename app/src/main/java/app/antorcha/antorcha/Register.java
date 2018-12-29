package app.antorcha.antorcha;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

import java.util.concurrent.ExecutionException;

import app.antorcha.antorcha.librerias.RegisterPost;

public class Register extends AppCompatActivity {

    private EditText user;
    private EditText correo;
    private EditText pass;
    private EditText validarPass;
    private EditText tlf;
    private FirebaseAuth mAuth;
    private Button btnRegistrar;
    String PassS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        user = (EditText) findViewById(R.id.User);
        correo = (EditText) findViewById(R.id.Correo);
        pass = (EditText) findViewById(R.id.Pass);
        validarPass = (EditText) findViewById(R.id.validarPass);
        tlf = (EditText) findViewById(R.id.tlf);
        //FirebaseApp.initializeApp(Register.this);
        mAuth = FirebaseAuth.getInstance();


        btnRegistrar = (Button) findViewById(R.id.registrarse);



        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String UserS = user.getText().toString();
                String CorreoS = correo.getText().toString();
                String PassS = pass.getText().toString();
                String validarPassS = validarPass.getText().toString();

                String tlfS = tlf.getText().toString();
                mAuth = FirebaseAuth.getInstance();
                final String array[] = new String[]  {UserS,CorreoS,tlfS};


               if(isValidEmail(CorreoS) && validarContraseña(PassS,validarPassS) ) {

                    Toast.makeText(Register.this, "Validaciones funcionando.", Toast.LENGTH_SHORT).show();
                    mAuth.createUserWithEmailAndPassword(CorreoS, PassS)
                            .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        Toast.makeText(Register.this, "Se registro correctamente.", Toast.LENGTH_SHORT).show();

                                        try {
                                            registerPost(array);
                                        } catch (ExecutionException e) {
                                            e.printStackTrace();
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }

                                        Intent ListSong = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(ListSong);

                                    } else {
                                        // If sign in fails, display a message to the user.


                                        FirebaseAuthException e = (FirebaseAuthException )task.getException();
                                        Toast.makeText(Register.this, "Error al Registrarse: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                        Log.d("Entro", "AQUI");

                                    }
                                }
                            });

                    }else{
                    Toast.makeText(Register.this, "Compruebe correo y contraseña", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    RegisterPost instanciaRegister = new RegisterPost();
    public void registerPost(String array[]) throws ExecutionException, InterruptedException {

        String response =  instanciaRegister.execute(array).get();

    }




    /*public void registrarse(View v){

        String UserS = user.getText().toString();
        String CorreoS = correo.getText().toString();
        String PassS = pass.getText().toString();
        String validarPassS = validarPass.getText().toString();
        String tlfS = tlf.getText().toString();
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String correo = txtCorreo.getText().toString();
                final String nombre = txtNombre.getText().toString();
                if(isValidEmail(correo) && validarContraseña()){

                    mAuth.createUserWithEmailAndPassword(correo, contraseña)
                            .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Register.this, "Registro Completado", Toast.LENGTH_SHORT).show();

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(Register.this, "Error al registrarse.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }else{
                    Toast.makeText(RegistroActivity.this, "Validaciones funcionando.", Toast.LENGTH_SHORT).show();
                }
            }
        });


        }*/






        /*Intent ListSong = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(ListSong);*/





    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    private boolean validarContraseña(String pass, String validarPass){
        Boolean res = false;

        if(pass.equals(validarPass)){
            if(pass.length() < 16 && pass.length() > 5){
                res = true;

            }else{

                Toast toast = Toast.makeText(getApplicationContext(), "Contraseña Invalida (Mas de 6 caracteres y menos de 16", Toast.LENGTH_SHORT);
                toast.show();

            }

        }
        return res;
    }




}
