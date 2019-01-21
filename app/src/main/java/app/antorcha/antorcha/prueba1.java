package app.antorcha.antorcha;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class prueba1 extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private TextView usuarioActivo;
    private String email;
    private TextView currentID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba1);
        mAuth = FirebaseAuth.getInstance();
        usuarioActivo = (TextView) findViewById(R.id.User);
        currentID = (TextView) findViewById(R.id.currentUser);


      //  FirebaseUser currentUser = mAuth.getCurrentUser();
       // if(currentUser != null){
       //     String correo = currentUser.getEmail();
       //     usuarioActivo.setText(correo);

       // }


    }


    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        email = currentUser.getEmail();
        currentID.setText(email);
    }






}
