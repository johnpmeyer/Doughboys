package com.beginner.doughboys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class AddReviewActivity extends AppCompatActivity {

    RestaurantDatabaseHelper helper = new RestaurantDatabaseHelper(this);
    String username, restaurantName, restaurantCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);
        Intent intent = getIntent();
        username = intent.getStringExtra("LoginLanding_username");
        Log.d("My Log", "String extra: " + username);
    }

    public void nextReviewPage(View v) {
        EditText restaurantNameEditText = (EditText) findViewById(R.id.restaurantNameEditText);
        restaurantName = restaurantNameEditText.getText().toString();
        EditText restaurantCityEditText = (EditText) findViewById(R.id.restaurantCityEditText);
        restaurantCity = restaurantCityEditText.getText().toString();

        if(restaurantName.equals("")||restaurantCity.equals("")) {
            Toast.makeText(this, "Must insert name for restaurant and city", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, ReviewDetails.class);
            intent.putExtra("AddReviewActivity_username", username);
            intent.putExtra("AddReviewActivity_name", restaurantName);
            intent.putExtra("AddReviewActivity_city", restaurantCity);
            startActivity(intent);
        }
    }
}
