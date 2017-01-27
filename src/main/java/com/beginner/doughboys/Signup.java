package com.beginner.doughboys;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    public void signupClick(View view) {
        EditText nameEditText = (EditText) findViewById(R.id.signupName);
        EditText emailEditText = (EditText) findViewById(R.id.signupEmail);
        EditText usernameEditText = (EditText) findViewById(R.id.signupUsername);
        EditText passwordEditText = (EditText) findViewById(R.id.signupPassword);
        EditText passwordConfirmEditText = (EditText) findViewById(R.id.signupPasswordConfirm);

        String nameStr = nameEditText.getText().toString();
        String emailStr = emailEditText.getText().toString();
        String userNameStr = usernameEditText.getText().toString();
        String passwordStr = passwordEditText.getText().toString();
        String passwordConfirmStr = passwordConfirmEditText.getText().toString();

        //Handles instance where ANY field is blank
        if(nameStr.equals("") || emailStr.equals("") || userNameStr.equals("") ||
                passwordStr.equals("") || passwordConfirmStr.equals("")) {

            Toast.makeText(getApplicationContext(), "You may not leave blank values in any areas. Please correct and " +
                    "try again", Toast.LENGTH_SHORT).show();

        } else         //Handles instance where confirmation doesn't match password
            if(!passwordStr.equals(passwordConfirmStr)) {
                passwordEditText.setText("");
                passwordConfirmEditText.setText("");
                Toast.makeText(getApplicationContext(), "Password and Password Confirmation fields" +
                        " do not match.", Toast.LENGTH_SHORT).show();
            } else {
                Contact newContact = new Contact();
                newContact.setName(nameStr);
                newContact.setEmail(emailStr);
                newContact.setUsername(userNameStr);
                newContact.setPassword(passwordStr);
                boolean insertSuccess = helper.insertContact(newContact);
                if(insertSuccess==true) {
                    Toast.makeText(getApplicationContext(), "Signup completed!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Signup Failed.", Toast.LENGTH_SHORT).show();
                }

            }





    }
}
