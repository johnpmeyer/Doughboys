package com.beginner.doughboys;

/**
 * Created by John Boy on 1/23/2017.
 */

public class Review {
    String restaurantName, restaurantCity;
    Float rating;

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String name) {
        this.restaurantName = name;
    }

    public String getRestaurantCity() {
        return restaurantCity;
    }

    public void setRestaurantCity(String city) {
        this.restaurantCity = city;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }
}
