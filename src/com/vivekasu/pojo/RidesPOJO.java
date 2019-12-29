package com.vivekasu.pojo;

import java.text.DecimalFormat;

public class RidesPOJO {

    private int num_of_days;
    private int num_of_rides;

    public RidesPOJO(int num_of_days, int num_of_rides){

        this.num_of_days = num_of_days;
        this.num_of_rides = num_of_rides;
    }

    public int getNum_of_days() {
        return num_of_days;
    }

    public void setNum_of_days(int num_of_days) {
        this.num_of_days = num_of_days;
    }

    public int getNum_of_rides() {
        return num_of_rides;
    }

    public void setNum_of_rides(int num_of_rides) {
        this.num_of_rides = num_of_rides;
    }
}
