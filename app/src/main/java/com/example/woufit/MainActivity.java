package com.example.woufit;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.woufit.model.FormValidation;
import com.example.woufit.model.Users;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText emailInputEditText;
    private EditText passwordInputEditText;
    private Button loginButton;
    private Button registerButton;
    private ArrayList<Users> usersArrayList;
    /*
    public static String TAG = "WouFit Project";
    public static String KEY_EMAIL = "email";
    public static String KEY_PASSWORD = "password";
     */

    Users user1 = new Users("U001",
                            "Jerry",
                            "Springer",
                            "jspringer@gmail.com",
                            "nohackplease",
                            false);
    Users user2 = new Users("U002",
                            "Jon",
                            "Snow",
                            "lordcommander@gmail.com",
                            "youknownothing",
                            false);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        usersArrayList = new ArrayList<>();
        usersArrayList.add(user1);
        usersArrayList.add(user2);

        findViews();

        registerButton.setOnClickListener(v -> {
            registerDialog();
        });

        loginButton.setOnClickListener(v1 -> {
            String userEmail = emailInputEditText.getText().toString();
            String userPassword = passwordInputEditText.getText().toString();

            boolean loginSuccess = false;

            for (int i=0;i<usersArrayList.size();i++) {
                if (userEmail.equals(usersArrayList.get(i).getUserEmail())) {
                    if (userPassword.equals(usersArrayList.get(i).getUserPassword())) {
                        Intent intent = account_initialization.newIntent(MainActivity.this,
                                                                usersArrayList.get(i).getUserFName());
                        loginSuccess = true;
                        startActivity(intent);
                    }
                }
            }

            if (!loginSuccess) {
                Toast.makeText(MainActivity.this, "Invalid email/password",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void findViews() {
        emailInputEditText = findViewById(R.id.email_input_edit_text);
        passwordInputEditText = findViewById(R.id.password_input_edit_text);
        loginButton = findViewById(R.id.login_button);
        registerButton = findViewById(R.id.register_button);
    }

    //consider changing to a DialogFragment for more functionality (see documentation)
    private void registerDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //adds the custom register_layout to the AlertDialog builder object
        final View registerLayout = getLayoutInflater().inflate(R.layout.register_layout, null);
        builder.setView(registerLayout);

        //shows the dialog
        AlertDialog dialog = builder.create();
        dialog.show();

        //looking for register button to add onClickListener
        Button confirmRegistration = registerLayout.findViewById(R.id.register_confirm_button);
        confirmRegistration.setOnClickListener(v -> {

            EditText firstNameEditText = registerLayout.findViewById(R.id.first_name_register_edit_text);
            EditText lastNameEditText = registerLayout.findViewById(R.id.last_name_register_edit_text);
            EditText emailEditTExt = registerLayout.findViewById(R.id.email_register_edit_text);
            EditText passwordEditText = registerLayout.findViewById(R.id.password_register_edit_text);

            //making sure the data entries are valid
            if (inputDataValidation(firstNameEditText, lastNameEditText, emailEditTExt, passwordEditText)) {
                String firstName = firstNameEditText.getText().toString();
                String lastName = lastNameEditText.getText().toString();
                String email = emailEditTExt.getText().toString();
                String password = passwordEditText.getText().toString();

                //adds the new user to "database"
                Users newUser = new Users("U003", firstName, lastName, email, password, true);
                usersArrayList.add(newUser);

                //sets the email and password on login page to newly created account
                emailInputEditText.setText(email);
                passwordInputEditText.setText(password);

                dialog.dismiss();

                Toast.makeText(MainActivity.this, "Account successfully created" ,
                        Toast.LENGTH_SHORT).show();
            }
        });

        //looking for close button to add onClickListener
        ImageButton toolbarCloseButton = registerLayout.findViewById(R.id.register_toolbar)
                                                        .findViewById(R.id.register_toolbar_close_button);
        toolbarCloseButton.setOnClickListener(v1 -> {
            dialog.dismiss();
        });
    }

    //using methods from FormValidation model class
    private boolean inputDataValidation(EditText firstName, EditText lastName, EditText email, EditText password) {
        if (FormValidation.isEntryEmpty(firstName)) {
            Toast.makeText(this, "Please enter a first name", Toast.LENGTH_SHORT).show();
        }
        else if (FormValidation.isEntryEmpty(lastName)) {
            Toast.makeText(this, "Please enter a last name", Toast.LENGTH_SHORT).show();
        }
        else if (!FormValidation.isValidEmail(email)) {
            Toast.makeText(this, "Please enter valid email", Toast.LENGTH_SHORT).show();
        }
        else if (FormValidation.isEntryEmpty(password)) {
            //add password parameters?
            Toast.makeText(this, "Please enter a password", Toast.LENGTH_SHORT).show();
        }
        else {
            return true;
        }
        return false;
    }

    /* maybe implement landscape mode
    @Override
    protected void onSaveInstanceState(@NonNull Bundle onSavedInstanceState) {
        super.onSaveInstanceState(onSavedInstanceState);

    }
     */

}