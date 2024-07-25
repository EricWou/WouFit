package com.example.woufit;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.woufit.initializationfragments.Welcome_Fragment;
import com.example.woufit.model.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class account_initialization extends AppCompatActivity {

    private String userID;
    DatabaseReference databaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_account_initialization);

        databaseRef = FirebaseDatabase.getInstance().getReference();

        userID = getIntent().getExtras().getString("userID");
        Bundle bundle = new Bundle();

        databaseRef.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot ss:snapshot.getChildren()) {
                    if (ss.getKey().equals(userID)) {
                        Users currentUser = ss.getValue(Users.class);

                        Log.d("account_initialization", currentUser.toString());

                        //using Bundle class to send data of Users object to Welcome_Fragment.java
                        //placing data from EXTRA_USER inside key-value pair of key "currentUser"
                        bundle.putParcelable("user", currentUser);
                        //also passing along the unique ID of the user
                        bundle.putString("userID", userID);

                        //transferring view to Welcome_Fragment.java
                        getSupportFragmentManager()
                                .beginTransaction()
                                .setReorderingAllowed(true)
                                .addToBackStack("Welcome")
                                .setCustomAnimations(
                                        R.anim.slide_in,  // enter
                                        R.anim.fade_out,  // exit
                                        R.anim.fade_in,   // popEnter
                                        R.anim.slide_out  // popExit
                                )
                                .add(R.id.initialization_fragment_container, Welcome_Fragment.class, bundle)
                                .commit();
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}