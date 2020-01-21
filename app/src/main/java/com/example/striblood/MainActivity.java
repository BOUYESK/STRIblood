package com.example.striblood;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private final static String TAG = "ActivityMain";
    private EditText UserEmail, UserPassword;
    private TextView signUp;
    private Button LoginButton;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UserEmail = (EditText) findViewById(R.id.login_email);
        UserPassword = (EditText) findViewById(R.id.login_password);
        signUp = (TextView)findViewById(R.id.signup);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SignUp.class));
            }
        });
        LoginButton = (Button) findViewById(R.id.login_button);
        mAuth = FirebaseAuth.getInstance();
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllowUserToLogin();
                //startActivity(new Intent(MainActivity.this, ActivityPrincipale.class));
            }
        });

    }

    private void AllowUserToLogin() {
        String email = UserEmail.getText().toString();
        String password = UserPassword.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Saisir votre email", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Saisir un mot de passe", Toast.LENGTH_SHORT).show();
        } else {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                SendUserToPrincipalActivity();
                            } else {
                                String message = task.getException().toString();
                                Toast.makeText(MainActivity.this, "Error : mot de pass ou////", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }


    }

    public void signup(View view) {
        startActivity(new Intent(MainActivity.this, SignUp.class));
    }

    private void SendUserToPrincipalActivity() {
        Intent principalIntent = new Intent(MainActivity.this, ActivityPrincipale.class);
        principalIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(principalIntent);
        finish();
    }


    public void forgot(View view) {
        //startActivity(new Intent(MainActivity.this, forgot.class));
    }
}
