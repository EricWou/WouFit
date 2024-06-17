package com.example.woufit.model;

import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;

public class FormValidation {

    //making sure the entry is not empty
    public static boolean isEntryEmpty(EditText text) {
        CharSequence input = text.getText().toString();

        return TextUtils.isEmpty(input);
    }

    //making sure the email is in a valid format
    public static boolean isValidEmail(EditText text) {
        CharSequence inputEmail = text.getText().toString();

        return (!TextUtils.isEmpty(inputEmail) && Patterns.EMAIL_ADDRESS.matcher(inputEmail).matches());
    }

}
