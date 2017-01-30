package com.beginner.doughboys;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);
    RestaurantDatabaseHelper restaurantHelper = new RestaurantDatabaseHelper(this);
    String id, username, name, email, password;
    EditText profileNameEditText, profileUsernameEditText, profilePasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent intent = getIntent();
        username = intent.getStringExtra("LoginLanding_username");
        name = intent.getStringExtra("LoginLanding_name");
        email = intent.getStringExtra("LoginLanding_email");
        password = intent.getStringExtra("LoginLanding_password");
        id = intent.getStringExtra("LoginLanding_id");
        Log.d("My Log", id + " " + name + " " + username + " " + email + " "+ password);
        showProfileData();
    }


    public void showProfileData() {
        profileNameEditText = (EditText) findViewById(R.id.profileName);
        profileUsernameEditText = (EditText) findViewById(R.id.profileUserName);
        profilePasswordEditText = (EditText) findViewById(R.id.profilePassword);
        profileNameEditText.setText(name);
        profileUsernameEditText.setText(username);
        profilePasswordEditText.setText(password);

        showUserReviews();

    }

    public void changeProfileData(View v) {
        name = profileNameEditText.getText().toString();
        username = profileUsernameEditText.getText().toString();
        password = profilePasswordEditText.getText().toString();

        boolean isUpdated = helper.updateData(id, profileNameEditText.getText().toString(),
                profileUsernameEditText.getText().toString(), profilePasswordEditText.getText().toString());
        if(isUpdated==true) {
            Toast.makeText(ProfileActivity.this, "Update successful!", Toast.LENGTH_SHORT).show();
            Intent myIntent = new Intent(getApplicationContext(), LoginLanding.class);
            myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            myIntent.putExtra("Update_username", username);
            startActivity(myIntent);
        } else {
            Toast.makeText(ProfileActivity.this, "Update failed", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteProfile(View v) {
        Integer deletedRows = helper.deleteData(id);
        if (deletedRows>0) {
            Intent myIntent = new Intent(this, Signin.class);
            myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            Toast.makeText(this, "Profile deleted!", Toast.LENGTH_SHORT).show();
            startActivity(myIntent);
        } else {
            Toast.makeText(this, "Profile deletion unsuccessful", Toast.LENGTH_SHORT).show();
        }
    }

    public void showUserReviews() {

        Cursor thisCursor = restaurantHelper.getAllData();

        while(thisCursor.moveToNext()) {
            if(thisCursor.getString(1).equals(username)) {
                LinearLayout linearLayoutTitles = (LinearLayout) findViewById(R.id.profileLabels);

                for(int i=0; i<5; i++) {
                    TextView myNewTextView = (TextView) getLayoutInflater().inflate(R.layout.dynamic_textview_labels, null);
                    linearLayoutTitles.addView(myNewTextView);
                    switch(i) {
                        case 0: myNewTextView.setText("Username:");
                            break;
                        case 1: myNewTextView.setText("City:");
                            break;
                        case 2: myNewTextView.setText("Restaurant:");
                            break;
                        case 3: myNewTextView.setText("Rating:");
                            break;
                        case 4: myNewTextView.setText("");
                            break;
                    }
                }

                LinearLayout linearLayout = (LinearLayout) findViewById(R.id.profileReviewInfo);
                for(int i=0; i<5; i++) {
                    TextView myNewTextView = (TextView) getLayoutInflater().inflate(R.layout.dynamic_textview, null);
                    linearLayout.addView(myNewTextView);

                    switch(i) {
                        case 0: myNewTextView.setText(thisCursor.getString(1));
                            break;
                        case 1: myNewTextView.setText(thisCursor.getString(2));
                            break;
                        case 2: myNewTextView.setText(thisCursor.getString(3));
                            break;
                        case 3: myNewTextView.setText(thisCursor.getString(4));
                            break;
                        case 4: myNewTextView.setText("");
                            break;
                    }
                }
            }
        }
    }

    public void addReview(View v) {
        Intent intent = new Intent(this, AddReviewActivity.class);
        intent.putExtra("LoginLanding_username", username);
        startActivity(intent);
    }

    public void goHome(View v) {
        finish();
    }
}
