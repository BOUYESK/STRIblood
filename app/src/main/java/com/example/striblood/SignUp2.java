package com.example.striblood;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class SignUp2 extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private TextInputEditText  tel, weight, address;
    private Spinner blood;
    private MaterialButton Next;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final String TAG = "SignUp";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);
        Next = (MaterialButton) findViewById(R.id.next1);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users");
        blood = (Spinner) findViewById(R.id.fasila);
        spinner1();
        tel = (TextInputEditText) findViewById(R.id.tel);
        weight = (TextInputEditText) findViewById(R.id.weight);
        address = (TextInputEditText) findViewById(R.id.add);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    //toastMessage("Successfully signed in with: " + user.getEmail());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    //toastMessage("Successfully signed out.");
                }
                // ...
            }
        };
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp2.this, com.example.striblood.MainActivity.class));
                creatData();
            }
        });
    }


    private void creatData() {
        database = FirebaseDatabase.getInstance();
        FirebaseUser use = mAuth.getCurrentUser();
        myRef = database.getReference("Users");
        String userId = use.getUid();
        String fasil = blood.getSelectedItem().toString();
        String tele = tel.getText().toString();
        String addre = address.getText().toString();
        String weig = weight.getText().toString();
        Map<String, Object> userValueA = new HashMap<>();
        userValueA.put("bloodgroup",fasil);
        userValueA.put("tel",tele);
        userValueA.put("weight",weig);
        userValueA.put("address",addre);
        userValueA.put("Images", "");
        myRef.child(userId).updateChildren(userValueA);
    }


    public void signin(View view) {
        startActivity(new Intent(SignUp2.this, MainActivity.class));
    }

    private void spinner1() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinnerArray1, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        blood.setAdapter(adapter);
    }

}