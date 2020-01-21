package com.example.striblood;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
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
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;


public class SignUp extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private Spinner spinner;
    private int year=1990, month, day;
    private TextInputEditText inputName, inputEmail, inputPassword, inputBirthday;
    private MaterialButton Next;
    private static final String TAG = "SignUp";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        spinner = (Spinner) findViewById(R.id.spinnerTariffCalculator);
        spinner1();
        Next = (MaterialButton) findViewById(R.id.next);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users");
        inputName = (TextInputEditText) findViewById(R.id.name);
        inputEmail = (TextInputEditText) findViewById(R.id.email);
        inputPassword = (TextInputEditText) findViewById(R.id.password);
        inputBirthday = (TextInputEditText) findViewById(R.id.birthday);
        inputBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        SignUp.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        inputBirthday.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }


        });
        mAuth = FirebaseAuth.getInstance();
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatUser();
            }
        });
    }

    private void creatData() {
        database = FirebaseDatabase.getInstance();
        FirebaseUser use = mAuth.getCurrentUser();
        myRef = database.getReference("Users");
        String userId = use.getUid();
        String nam = inputName.getText().toString();
        String birth = inputBirthday.getText().toString();
        String email = inputEmail.getText().toString();
        User user = new User(nam, birth, email);
        Map<String, Object> userValue = new HashMap<>();
        userValue.put("name",user.getName());
        userValue.put("email",user.getEmail());
        userValue.put("birthday",user.getBirthady());
        myRef.child(userId).setValue(userValue);
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("gender", spinner.getSelectedItem().toString());
        myRef.child(userId).updateChildren(childUpdates);
    }

    public void signin(View view) {
        startActivity(new Intent(SignUp.this, MainActivity.class));
    }


    private void creatUser() {
        final String email = inputEmail.getText().toString();
        final String password = inputPassword.getText().toString();
        final String name = inputName.getText().toString();
        final String birthday = inputBirthday.getText().toString();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Name is missing!!", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Email is missing!!", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(password) || password.length() < 8) {
            Toast.makeText(this, "Password is missing!!", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(birthday)) {
            Toast.makeText(this, "Birthday is missing!!", Toast.LENGTH_LONG).show();
        } else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(SignUp.this, "SignUp Unsuccessful, Please Try Again", Toast.LENGTH_SHORT).show();
                    } else {
                        startActivity(new Intent(SignUp.this, com.example.striblood.SignUp2.class));
                        creatData();
                    }
                }
            });
        }
    }


    private void spinner1() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinnerArray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}