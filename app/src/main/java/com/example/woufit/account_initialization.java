package com.example.woufit;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.woufit.initializationfragments.Welcome_Fragment;

public class account_initialization extends AppCompatActivity {

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