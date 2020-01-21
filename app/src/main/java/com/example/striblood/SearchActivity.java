package com.example.striblood;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class SearchActivity extends AppCompatActivity {

    private Spinner mSearchField;
    private EditText mSearchField1;
    private ImageButton mSearchBtn;
    private TextView call;
    private RecyclerView mResultList;

    private DatabaseReference mUserDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mSearchField = (Spinner) findViewById(R.id.search_field);
        mSearchField1 = (EditText) findViewById(R.id.search_filed1);
        spinner1();
        mUserDatabase = FirebaseDatabase.getInstance().getReference("Users");
        mSearchBtn = (ImageButton) findViewById(R.id.search_btn);
        mResultList = (RecyclerView) findViewById(R.id.result_list);
        //spinner1();
        mResultList.setHasFixedSize(true);
        mResultList.setLayoutManager(new LinearLayoutManager(this));

        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String searchText = mSearchField.getSelectedItem().toString();
                String searchText1 = mSearchField1.getText().toString();
                if (!(searchText.isEmpty() || searchText1.isEmpty())) {
                    firebaseUserSearch(searchText);
                }
                //firebaseUserSearch(searchText1);
            }


        });

    }

    private void firebaseUserSearch(String searchText) {

        Toast.makeText(SearchActivity.this, "Started Search", Toast.LENGTH_LONG).show();

        Query firebaseSearchQuery = mUserDatabase.orderByChild("bloodgroup").startAt(searchText).endAt(searchText + "\uf8ff");

        FirebaseRecyclerAdapter<User, UsersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<User, UsersViewHolder>(

                User.class,
                R.layout.list_layout,
                UsersViewHolder.class,
                firebaseSearchQuery

        ) {
            @Override
            protected void populateViewHolder(UsersViewHolder viewHolder, final User model, int position) {
                viewHolder.setDetails(getApplicationContext(), model.getName(), model.getTel(), model.getImages(), model.getAddress());

            }
        };

        mResultList.setAdapter(firebaseRecyclerAdapter);

    }



    // View Holder Class

    public static class UsersViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public UsersViewHolder(View itemView) {
            super(itemView);

            mView = itemView;

        }

        public void setDetails(Context ctx, String userName, String Usertatus, String userImage, String adr) {
            TextView user_name = (TextView) mView.findViewById(R.id.name_text);
            TextView user_adress = (TextView) mView.findViewById(R.id.addre);
            TextView user_status = (TextView) mView.findViewById(R.id.status_text);
            ImageView user_image = (ImageView) mView.findViewById(R.id.profile_image);
            user_name.setText(userName);
            user_status.setText("Phone: " + Usertatus);
            Picasso.get().load(userImage).into(user_image);
            user_adress.setText("Address: " + adr);
        }


    }

    private void spinner1() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinnerArray1, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSearchField.setAdapter(adapter);
    }

    private void callme() {
        User user = new User();
        String callTel = user.getTel();
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + callTel));//change the number
        if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }
        startActivity(callIntent);

    }

}
