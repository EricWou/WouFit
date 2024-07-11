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

import com.example.woufit.dao.SaltDao;
import com.example.woufit.dao.UsersDao;
import com.example.woufit.database.WouFitDatabase;
import com.example.woufit.model.FormValidation;
import com.example.woufit.model.PasswordHashing;
import com.example.woufit.model.Salt;
import com.example.woufit.model.Users;

import java.security.NoSuchAlgorithmException;
import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity implements Executor {

    private EditText emailInputEditText;
    private EditText passwordInputEditText;
    private Button loginButton;
    private Button registerButton;
    private WouFitDatabase db;
    private UsersDao usersDao;
    private SaltDao saltDao;
    public static String EXTRA_USER = "com.example.woufit.model.Users";

    /*
    public static String TAG = "WouFit Project";
    public static String KEY_EMAIL = "email";
    public static String KEY_PASSWORD = "password";
     */

    /*
    Users user2 = new Users("Jon",
                            "Snow",
                            "lordcommander@gmail.com",
                            "youknownothing",
                            false);
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //initialize Room Persistence Library database and corresponding DAOs
        db = Room.databaseBuilder(getApplicationContext(),
                                WouFitDatabase.class,
                          "woufit.db").build();
        usersDao = db.usersDao();
        saltDao = db.saltDao();

        /*
        //used for generating the initial salt String and test user
        //creates new Thread so that the database methods can happen in the background
        execute(new Runnable() {
            @Override
            public void run() {
                //creating saltString for 1st run
                Salt salt;
                try {
                    salt = new Salt(PasswordHashing.computeSalt());
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }
                saltDao.createSaltString(salt);

                //creating test user for 1st run
                Users user1 = new Users("Jerry",
                        "Springer",
                        "test@gmail.com",
                        "test",
                        false);
                String passwordToHash = user1.getUserPassword();
                user1.setUserPassword(PasswordHashing.hashPassword(passwordToHash, salt.getSaltString()));

                usersDao.createUser(user1);
            }
        });
         */

        findViews();

        registerButton.setOnClickListener(v -> {
            registerDialog();
        });

        loginButton.setOnClickListener(v1 -> {
            //creates new Thread so that the database methods can happen in the background
            execute(new Runnable() {
                @Override
                public void run() {
                    String userEmail = emailInputEditText.getText().toString();
                    String userPassword = passwordInputEditText.getText().toString();

                    boolean loginSuccess = false;

                    //retrieving password from database if userEmail exists
                    String comparePassword = usersDao.compareLogin(userEmail);

                    if (comparePassword != null) {
                        String saltString = saltDao.readSaltString();
                        String hashPassword = PasswordHashing.hashPassword(userPassword, saltString);

                        if (hashPassword.equals(comparePassword)) {
                            loginSuccess = true;

                            Users loginUser = usersDao.retrieveLoginUser(userEmail);

                            Intent account_initialization_intent = new Intent(getApplicationContext(),
                                    account_initialization.class);
                            account_initialization_intent.putExtra(EXTRA_USER, loginUser);

                            startActivity(account_initialization_intent);
                        }
                    }

                    if (!loginSuccess) {
                        Toast.makeText(MainActivity.this, "Invalid email/password",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });

        });

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

                Users newUser = new Users(firstName, lastName, email, password, true);

                /*
                //adds the new user to "database"
                Users newUser = new Users(firstName, lastName, email, password, true);
                usersArrayList.add(newUser);
                 */

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

    private void findViews() {
        emailInputEditText = findViewById(R.id.email_input_edit_text);
        passwordInputEditText = findViewById(R.id.password_input_edit_text);
        loginButton = findViewById(R.id.login_button);
        registerButton = findViewById(R.id.register_button);
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

    /* maybe implement landscape mode
    @Override
    protected void onSaveInstanceState(@NonNull Bundle onSavedInstanceState) {
        super.onSaveInstanceState(onSavedInstanceState);

    }
     */

}