package com.beginner.doughboys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Signin extends AppCompatActivity {
    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);
    }
    /*
    Gets Login fields, compares to DB password, then brings user to Landing page if they
    got it right
     */
    public void loginClick(View view) {
        EditText editTextUsername = (EditText) findViewById(R.id.usernameInput);
        String userNameString = editTextUsername.getText().toString();
        EditText editTextPassword = (EditText) findViewById(R.id.passwordInput);
        String passwordString = editTextPassword.getText().toString();

        if(editTextUsername.getText().toString().equals("")) {
            Toast.makeText(Signin.this, "Please enter a username", Toast.LENGTH_LONG).show();
            return;
        } else if(editTextPassword.getText().toString().equals("")) {
            Toast.makeText(Signin.this, "Please enter a password", Toast.LENGTH_LONG).show();
            return;
        }

        String password = helper.searchPass(userNameString);
        Log.d("My Log", "searchPass returned " + password);
        if(passwordString.equals(password)) {

            Intent intent = new Intent(this, LoginLanding.class);
            intent.putExtra("Signin_username", userNameString);
            startActivity(intent);
            finish();

        } else if(password.equals("Username not found")) {
            Toast.makeText(Signin.this, "Username not found", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(Signin.this, "Password doesn't match username account", Toast.LENGTH_SHORT).show();
            editTextPassword.setText("");
        }

    }

    public void signup(View view) {
        Intent myIntent = new Intent(this, Signup.class);
        startActivity(myIntent);
    }

}
