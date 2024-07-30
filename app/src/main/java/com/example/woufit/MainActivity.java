package com.example.woufit;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;
import androidx.viewpager2.widget.ViewPager2;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import cz.msebera.android.httpclient.Header;

import com.example.woufit.database.WouFitDatabase;
import com.example.woufit.model.Users;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {

    private Toolbar upperToolbar;
    private TextView inspirationQuoteTextView;
    private TextView inspirationAuthorTextView;
    private Button startWorkoutButton;
    //private TextView mainWorkoutTextView;
    private ViewPager2 mainViewPager;
    private Toolbar bottomToolbar;
    private String userID;
    DatabaseReference databaseRef;
    private HashMap<String, HashMap<String, Integer>> programData;


    //to allow use of online APIs, need to have this in the AndroidManifest.xml
    //<uses-permission android:name="android.permission.INTERNET"/>

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //helps to place Toolbar correctly
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(0, systemBars.top, 0, 0);
            return insets;
        });

        findViews();
        //bottomToolbar.inflateMenu(R.menu.bottom_menu);

        userID = getIntent().getExtras().getString("userID");

        //gets the inspirational quote of the day from  an external API
        fetchInspiration();

        //retrieves program data for the user
        fetchWorkouts();

    }

    private void fetchInspiration() {

        String mode = "random";
        String uri = "https://zenquotes.io/api/"+mode;
        new AsyncHttpClient().get(uri, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                try {
                    //converts responseBody to String format
                    String responseString = new String(responseBody);

                    //converts responseString to JSONArray
                    JSONArray responseArray = new JSONArray(responseString);

                    //convert JSONArray to JSONObject (the response is always just 1 element)
                    JSONObject response = responseArray.getJSONObject(0);

                    String quote = response.getString("q");
                    String author = response.getString("a");

                    inspirationQuoteTextView.setText(quote);
                    inspirationAuthorTextView.setText("-"+author);

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    private void fetchWorkouts() {
        databaseRef = FirebaseDatabase.getInstance().getReference();

        databaseRef.child("Users")
                .child(userID)
                .child("Program").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        //wanting to save all the information with programData as the top level parent
                        programData = new HashMap<>();

                        //retrieving each workout listed under "program"
                        for(DataSnapshot workoutSnapshot : snapshot.getChildren()) {
                            String workoutName = workoutSnapshot.getKey();

                            HashMap<String, Integer> workoutData = new HashMap<>();

                            //retrieving each exercise listed under each workout
                            for (DataSnapshot exerciseSnapshot : workoutSnapshot.getChildren()) {
                                String exerciseName = exerciseSnapshot.getKey();
                                Integer exerciseID = exerciseSnapshot.getValue(Integer.class);

                                workoutData.put(exerciseName, exerciseID);
                            }

                            programData.put(workoutName, workoutData);
                        }

                        //sets the adapter for ViewPager2 (to allow for horizontal swiping between workouts)
                        WorkoutAdapter workoutAdapter = new WorkoutAdapter(programData);
                        mainViewPager.setAdapter(workoutAdapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    private void findViews() {
        upperToolbar = findViewById(R.id.upper_toolbar);
        inspirationQuoteTextView = findViewById(R.id.inspiration_quote_textView);
        inspirationAuthorTextView = findViewById(R.id.inspiration_author_textView);
        startWorkoutButton = findViewById(R.id.start_workout_button);
        mainViewPager = findViewById(R.id.main_viewPager);
        bottomToolbar = findViewById(R.id.bottom_toolbar);
    }


}