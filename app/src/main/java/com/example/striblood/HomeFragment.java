package com.example.striblood;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeFragment extends Fragment {
    //Firebase
    FirebaseStorage storage;
    StorageReference storageReference;
    CircleImageView n;
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_principale, container, false);
        n = (CircleImageView) view.findViewById(R.id.principal);
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference("Users");

        Query query = myRef.child(firebaseUser.getUid());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //for (DataSnapshot ds : dataSnapshot.getChildren()) {
                String imag = "" + dataSnapshot.child("Images").getValue();

               try {
                    Picasso.get().load(imag).into(n);
                } catch (Exception e) {
                    Picasso.get().load(R.drawable.boss).into(n);
                }
                //Picasso.get().load(imag).into(n);

                //try {
                //  Picasso.get().load(imag).into(n);
                //} catch (Exception e) {
                //  Picasso.get().load(R.drawable.boss).into(n);
                //}
                //}

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}