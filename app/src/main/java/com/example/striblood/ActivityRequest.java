package com.example.striblood;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class ActivityRequest extends AppCompatActivity {

    private int buttonState = 1;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;
    private Button buttonRequest;
    private String group;
    private TextInputEditText nom1, telephone1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        buttonRequest = (Button) findViewById(R.id.request_button);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users");
        nom1 = (TextInputEditText) findViewById(R.id.nom);
        telephone1 = (TextInputEditText) findViewById(R.id.telephone);
        mAuth = FirebaseAuth.getInstance();
        buttonRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatData();
            }
        });


        final Button button = (Button) findViewById(R.id.a);
        final Button button1 = (Button) findViewById(R.id.b);
        final Button button2 = (Button) findViewById(R.id.c);
        final Button button3 = (Button) findViewById(R.id.d);
        final Button button4 = (Button) findViewById(R.id.e);
        final Button button5 = (Button) findViewById(R.id.f);
        final Button button6 = (Button) findViewById(R.id.g);
        final Button button7 = (Button) findViewById(R.id.k);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonState == 1) {
                    group ="A+";
                    button.setBackgroundResource(R.drawable.border3);
                    button1.setBackgroundResource(R.drawable.border4);
                    button2.setBackgroundResource(R.drawable.border4);
                    button3.setBackgroundResource(R.drawable.border4);
                    button4.setBackgroundResource(R.drawable.border4);
                    button5.setBackgroundResource(R.drawable.border4);
                    button6.setBackgroundResource(R.drawable.border4);
                    button7.setBackgroundResource(R.drawable.border4);
                } else {
                    button.setBackgroundResource(R.drawable.border4);

                }


            }

        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonState == 1) {
                    group ="A-";
                    button1.setBackgroundResource(R.drawable.border3);
                    button.setBackgroundResource(R.drawable.border4);
                    button2.setBackgroundResource(R.drawable.border4);
                    button3.setBackgroundResource(R.drawable.border4);
                    button4.setBackgroundResource(R.drawable.border4);
                    button5.setBackgroundResource(R.drawable.border4);
                    button6.setBackgroundResource(R.drawable.border4);
                    button7.setBackgroundResource(R.drawable.border4);

                } else {
                    button1.setBackgroundResource(R.drawable.border4);


                }


            }

        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonState == 1) {
                    group ="B+";
                    button1.setBackgroundResource(R.drawable.border4);
                    button.setBackgroundResource(R.drawable.border4);
                    button2.setBackgroundResource(R.drawable.border3);
                    button3.setBackgroundResource(R.drawable.border4);
                    button4.setBackgroundResource(R.drawable.border4);
                    button5.setBackgroundResource(R.drawable.border4);
                    button6.setBackgroundResource(R.drawable.border4);
                    button7.setBackgroundResource(R.drawable.border4);

                } else {
                    button1.setBackgroundResource(R.drawable.border4);


                }


            }

        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonState == 1) {
                    group ="B-";
                    button3.setBackgroundResource(R.drawable.border3);
                    button1.setBackgroundResource(R.drawable.border4);
                    button.setBackgroundResource(R.drawable.border4);
                    button2.setBackgroundResource(R.drawable.border4);
                    button4.setBackgroundResource(R.drawable.border4);
                    button5.setBackgroundResource(R.drawable.border4);
                    button6.setBackgroundResource(R.drawable.border4);
                    button7.setBackgroundResource(R.drawable.border4);

                } else {
                    button1.setBackgroundResource(R.drawable.border4);


                }


            }

        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonState == 1) {
                    group ="O+";
                    button3.setBackgroundResource(R.drawable.border4);
                    button1.setBackgroundResource(R.drawable.border4);
                    button.setBackgroundResource(R.drawable.border4);
                    button2.setBackgroundResource(R.drawable.border4);
                    button4.setBackgroundResource(R.drawable.border3);
                    button5.setBackgroundResource(R.drawable.border4);
                    button6.setBackgroundResource(R.drawable.border4);
                    button7.setBackgroundResource(R.drawable.border4);

                } else {
                    button1.setBackgroundResource(R.drawable.border4);


                }


            }

        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonState == 1) {
                    group ="O-";
                    button3.setBackgroundResource(R.drawable.border4);
                    button1.setBackgroundResource(R.drawable.border4);
                    button.setBackgroundResource(R.drawable.border4);
                    button2.setBackgroundResource(R.drawable.border4);
                    button4.setBackgroundResource(R.drawable.border4);
                    button5.setBackgroundResource(R.drawable.border3);
                    button6.setBackgroundResource(R.drawable.border4);
                    button7.setBackgroundResource(R.drawable.border4);

                } else {
                    button1.setBackgroundResource(R.drawable.border4);


                }


            }

        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonState == 1) {
                    group ="AB+";
                    button3.setBackgroundResource(R.drawable.border4);
                    button1.setBackgroundResource(R.drawable.border4);
                    button.setBackgroundResource(R.drawable.border4);
                    button2.setBackgroundResource(R.drawable.border4);
                    button4.setBackgroundResource(R.drawable.border4);
                    button5.setBackgroundResource(R.drawable.border4);
                    button6.setBackgroundResource(R.drawable.border3);
                    button7.setBackgroundResource(R.drawable.border4);

                } else {
                    button1.setBackgroundResource(R.drawable.border4);


                }


            }

        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonState == 1) {
                    group ="AB-";
                    button3.setBackgroundResource(R.drawable.border4);
                    button1.setBackgroundResource(R.drawable.border4);
                    button.setBackgroundResource(R.drawable.border4);
                    button2.setBackgroundResource(R.drawable.border4);
                    button4.setBackgroundResource(R.drawable.border4);
                    button5.setBackgroundResource(R.drawable.border4);
                    button6.setBackgroundResource(R.drawable.border4);
                    button7.setBackgroundResource(R.drawable.border3);

                } else {
                    button1.setBackgroundResource(R.drawable.border4);


                }


            }

        });
    }

    private void creatData() {
        database = FirebaseDatabase.getInstance();
        FirebaseUser use = mAuth.getCurrentUser();
        myRef = database.getReference("Request");
        String userId = use.getUid();
        String name = nom1.getText().toString();
        String telephone = telephone1.getText().toString();

        Request resquest = new Request(name,telephone,group);
        Map<String, Object> requestValue = new HashMap<>();
        requestValue.put("nom", resquest.getName());
        requestValue.put("telephone", resquest.getTelephone());
        requestValue.put("Group",resquest.getGroup());
        myRef.child(userId).push().updateChildren(requestValue);

    }

}
