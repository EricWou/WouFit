package com.example.woufit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.woufit.database.WouFitDatabase;
import com.example.woufit.model.Users;

import java.security.NoSuchAlgorithmException;
import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity implements Executor {

    private EditText emailInputEditText;
    private EditText passwordInputEditText;
    private Button loginButton;
    private Button registerButton;
    private WouFitDatabase db;
    //private UsersDao usersDao;
    //private SaltDao saltDao;
    public static String EXTRA_USER = "com.example.woufit.model.Users";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        /*
        //initialize Room Persistence Library database and corresponding DAOs
        db = Room.databaseBuilder(getApplicationContext(),
                                WouFitDatabase.class,
                          "woufit.db").build();
        usersDao = db.usersDao();
        saltDao = db.saltDao();
        */

    }

    /*
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

                Users newUser = new Users(firstName, lastName, email, password, true);

                //the Thread can be a small part of a bigger code
                execute(() -> {
                    String saltString = saltDao.readSaltString();
                    String hashPassword = PasswordHashing.hashPassword(newUser.getUserPassword(), saltString);
                    newUser.setUserPassword(hashPassword);

                    usersDao.createUser(newUser);
                });

                //sets the email on login page to newly created account
                emailInputEditText.setText(email);

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
    */

    @Override
    public void execute(Runnable command) {
        Thread thread = new Thread(command);
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



}