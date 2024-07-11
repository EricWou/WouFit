package com.example.woufit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.woufit.model.Users;
import com.example.woufit.myfragments.Welcome_Fragment;

public class account_initialization extends AppCompatActivity {

    private TextView greetingTextView;
    //private Users userRetrieve;
    //private static String EXTRA_USER = "com.example.woufit.model.Users";

    /*
    public static Intent newIntent(Context packageContext, Users importUser) {

        Intent intent = new Intent(packageContext, account_initialization.class);

        //codes the data into EXTRA parameters
        intent.putExtra(EXTRA_USER, importUser);

        return intent;
    }
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_account_initialization);

        if (savedInstanceState == null) {

            //using Bundle class to send data of Users object to Welcome_Fragment.java
            Bundle bundle = new Bundle();
            //placing data from EXTRA_USER inside key-value pair of key "user"
            bundle.putParcelable("user", getIntent().getParcelableExtra(MainActivity.EXTRA_USER));

            //transferring view to Welcome_Fragment.java
            getSupportFragmentManager()
                    .beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.initialization_fragment_container, Welcome_Fragment.class, bundle)
                    .commit();
        }



    }
}