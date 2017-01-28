package com.beginner.doughboys;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.Rating;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class ReviewDetails extends AppCompatActivity {
    String username, restaurantName, restaurantCity;
    float rating;
    RestaurantDatabaseHelper helper = new RestaurantDatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_details);
        Intent intent = getIntent();
        username = intent.getStringExtra("AddReviewActivity_username");
        restaurantName = intent.getStringExtra("AddReviewActivity_name");
        restaurantCity = intent.getStringExtra("AddReviewActivity_city");
        TextView restaurantNameTV = (TextView) findViewById(R.id.restaurantNameTV);
        TextView restaurantCityTV = (TextView) findViewById(R.id.restaurantCityTV);
        restaurantNameTV.setText(restaurantName);
        restaurantCityTV.setText(restaurantCity);
    }

    public void submitReview(View v) {

        RatingBar ratingBar = (RatingBar) findViewById(R.id.overallRating);
        rating = ratingBar.getRating();

        Review review = new Review();
        review.setUsername(username);
        review.setRestaurantName(restaurantName);
        review.setRestaurantCity(restaurantCity);
        review.setRating(rating);

        boolean insertSuccess = helper.insertReview(review);
        if(insertSuccess==true) {
            Intent newIntent = new Intent(this, LoginLanding.class);
            newIntent.putExtra("ReviewDetails_username", username);
            newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            Toast.makeText(this, "Review entered!", Toast.LENGTH_SHORT).show();
            startActivity(newIntent);
        } else {
            Toast.makeText(this, "Review entry failed", Toast.LENGTH_SHORT).show();
        }
    }
}
