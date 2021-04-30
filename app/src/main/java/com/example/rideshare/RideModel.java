package com.example.rideshare;

public class RideModel {
    String ride_desc, from, to, price, rating, seats;

    public RideModel() {}

    public RideModel(String ride_desc, String from, String to, String price, String rating, String seats) {
        this.ride_desc = ride_desc;
        this.from = from;
        this.to = to;
        this.price = price;
        this.rating = rating;
        this.seats = seats;
    }

    public String getRide_desc() {
        return ride_desc;
    }

    public void setRide_desc(String ride_desc) {
        this.ride_desc = ride_desc;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }


}