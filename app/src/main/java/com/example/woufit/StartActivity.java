package com.example.woufit;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.woufit.model.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StartActivity extends AppCompatActivity {

    private EditText emailInputEditText;
    private EditText passwordInputEditText;
    private Button loginButton;
    private Button registerButton;
    private FirebaseAuth auth;
    private DatabaseReference databaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_start);

        auth = FirebaseAuth.getInstance();
        databaseRef = FirebaseDatabase.getInstance().getReference();
        findViews();

        registerButton.setOnClickListener(v -> {
            registerDialog();
        });

        loginButton.setOnClickListener(v1 -> {

            String userEmail = emailInputEditText.getText().toString();
            String userPassword = passwordInputEditText.getText().toString();

            login(userEmail, userPassword);
        });

    }

    private void registerDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //adds the custom register_layout to the AlertDialog builder object
        final View registerLayout = getLayoutInflater().inflate(R.layout.register_layout, null);
        builder.setView(registerLayout);

        //shows the dialog
        AlertDialog dialog = builder.create();
        dialog.show();

        //Inside the popup registerDialog
        //looking for close button to add onClickListener
        ImageButton toolbarCloseButton = registerLayout.findViewById(R.id.register_toolbar)
                .findViewById(R.id.register_toolbar_close_button);
        toolbarCloseButton.setOnClickListener(v1 -> {
            dialog.dismiss();
        });

        //looking for register button to add onClickListener
        Button confirmRegistration = registerLayout.findViewById(R.id.register_confirm_button);
        confirmRegistration.setOnClickListener(v -> {

            EditText firstNameEditText = registerLayout.findViewById(R.id.first_name_register_edit_text);
            EditText lastNameEditText = registerLayout.findViewById(R.id.last_name_register_edit_text);
            EditText emailEditText = registerLayout.findViewById(R.id.email_register_edit_text);
            EditText passwordEditText = registerLayout.findViewById(R.id.password_register_edit_text);

            String firstName = firstNameEditText.getText().toString();
            String lastName = lastNameEditText.getText().toString();
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(StartActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful())
                    {
                        //retrieves firebase defined unique key for the user registering in
                        String userID = auth.getUid();

                        //creates new User in firebase
                        Users newUser = new Users(firstName, lastName, true);
                        databaseRef.child("Users")
                                    .child(userID)
                                    .setValue(newUser);

                        //sets the email on login page to newly created account
                        emailInputEditText.setText(email);

                        dialog.dismiss();

                        Toast.makeText(StartActivity.this, "Account successfully created",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(StartActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        });
    }

    private void login(String email, String password){
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful())
                {
                    //do you even see this?
                    Toast.makeText(StartActivity.this, "Login successful", Toast.LENGTH_SHORT).show();

                    String userID = auth.getUid();
                    databaseRef.child("Users").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            for(DataSnapshot ss:snapshot.getChildren()) {
                                if (ss.getKey().equals(userID)) {
                                    boolean firstLogin = ss.getValue(Users.class).getFirstLogin();

                                    //ss.getValue(Users.class) properly retrieves
                                    //Log.d("firstLogin", ss.getValue(Users.class).toString());

                                    Bundle bundle = new Bundle();
                                    bundle.putString("userID", userID);

                                    if (firstLogin) {
                                        //go to account_initialization activity
                                        Intent nextActivity = new Intent(StartActivity.this, account_initialization.class);
                                        nextActivity.putExtras(bundle);
                                        startActivity(nextActivity);
                                    }
                                    else {
                                        Intent nextActivity = new Intent(StartActivity.this, MainActivity.class);
                                        nextActivity.putExtras(bundle);
                                        startActivity(nextActivity, bundle);
                                    }
                                }
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                else
                {
                    Toast.makeText(StartActivity.this, "Invalid email/password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void findViews() {
        emailInputEditText = findViewById(R.id.email_input_edit_text);
        passwordInputEditText = findViewById(R.id.password_input_edit_text);
        loginButton = findViewById(R.id.login_button);
        registerButton = findViewById(R.id.register_button);
    }
}