package com.beginner.doughboys;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import static com.beginner.doughboys.R.string.username;

public class LoginLanding extends AppCompatActivity {
    DatabaseHelper helper = new DatabaseHelper(this);
    RestaurantDatabaseHelper restaurantHelper = new RestaurantDatabaseHelper(this);
    String usernameIntent;
    String id, name, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_landing);
        showProfiles();
    }

    public void showProfiles() {
        Cursor thisCursor = helper.getAllData();
        Cursor restaurantCursor = restaurantHelper.getAllData();

        Intent intent = getIntent();
        // If intent equals Profile Screen, set userNameIntent to a the value FROM profile.
        boolean hasUsernamefromSignin = intent.hasExtra("Signin_username");
        boolean hasUsernamefromUpdate = intent.hasExtra("Update_username");
        boolean hasUsernamefromReview = intent.hasExtra("ReviewDetails_username");

        if (hasUsernamefromSignin==true) {
            usernameIntent = intent.getStringExtra("Signin_username");
        } else if(hasUsernamefromUpdate==true) {
            usernameIntent = intent.getStringExtra("Update_username");
        } else if(hasUsernamefromReview==true) {
            usernameIntent = intent.getStringExtra("ReviewDetails_username");
        }

        if(thisCursor.getCount()==0) {
            showMessage("Error", "No Profiles Found");
            return;
        } else {
            StringBuffer dynamicBuffer = new StringBuffer();
            StringBuffer staticBuffer = new StringBuffer();
            while(thisCursor.moveToNext()) {
                id = thisCursor.getString(0);
                name = thisCursor.getString(1);
                email = thisCursor.getString(2);
                password = thisCursor.getString(4);
                if(usernameIntent.equals(thisCursor.getString(3))) {
                    break;
                }
            }

            while(restaurantCursor.moveToNext()) {
                staticBuffer.append("Username:\n" + "Restaurant Name:\n" + "Restaurant City:\n" + "Rating:\n\n");
                dynamicBuffer.append(restaurantCursor.getString(1) + "\n" + restaurantCursor.getString(2) + "\n" +
                        restaurantCursor.getString(3) + "\n" + restaurantCursor.getString(4) + "\n\n");

            }

            TextView labels = (TextView) findViewById(R.id.labelViews);
            labels.setText(staticBuffer.toString());

            TextView profiles = (TextView) findViewById(R.id.profileData);
            profiles.setText(dynamicBuffer.toString());
        }
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void goToProfile(View v) {
        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra("LoginLanding_username", usernameIntent);
        intent.putExtra("LoginLanding_name", name);
        intent.putExtra("LoginLanding_email", email);
        intent.putExtra("LoginLanding_password", password);
        intent.putExtra("LoginLanding_id", id);
        startActivity(intent);
    }

    public void addReview(View v) {
        Intent intent = new Intent(this, AddReviewActivity.class);
        intent.putExtra("LoginLanding_username", usernameIntent);
        startActivity(intent);
    }

    public void signout(View v) {
        Intent myIntent = new Intent(getApplicationContext(), Signin.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(myIntent);
    }

}
