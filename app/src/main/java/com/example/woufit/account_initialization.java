package com.example.woufit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class account_initialization extends AppCompatActivity {

    private TextView greetingTextView;
    private String userFNameRetrieve;
    private static String EXTRA_USER_FNAME = "com.example.woufit.userFName";

    public static Intent newIntent(Context packageContext, String userFName) {
        Intent intent = new Intent(packageContext, account_initialization.class);

        //codes the data into EXTRA parameters
        intent.putExtra(EXTRA_USER_FNAME, userFName);;

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_account_initialization);

        //placing data from EXTRA parameters into instance variables
        userFNameRetrieve = getIntent().getStringExtra(EXTRA_USER_FNAME);
        String greeting = "Welcome "+userFNameRetrieve;

        greetingTextView = findViewById(R.id.greeting_text_view);
        greetingTextView.setText(greeting);

    }
}