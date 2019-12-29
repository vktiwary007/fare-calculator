package com.vivekasu.calculator;

import com.vivekasu.constants.Constants;
import com.vivekasu.pojo.RidesPOJO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class TransitCalculator extends RidesPOJO{

    private int num_of_fares;
    private DecimalFormat df = new DecimalFormat("0.00");

    public TransitCalculator(int num_of_days, int num_of_rides){
        super(num_of_days,num_of_rides );
        this.num_of_fares = Constants.num_of_fares;
    }

    public double unlimited7Price(){

        double pay_per_ride = (Math.ceil((double)getNum_of_days()/7)*Constants._7day_ride)/getNum_of_rides();
        BigDecimal bd = new BigDecimal(pay_per_ride).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();

    }

    public double unlimited30Price(){

        double pay_per_ride = (Math.ceil((double)getNum_of_days()/30)*Constants._30day_ride)/getNum_of_rides();
        BigDecimal bd = new BigDecimal(pay_per_ride).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();

    }

    public double[] getRidePrices(){

        double[] ride_prices = new double[num_of_fares];

        ride_prices[0] = Constants.pay_per_ride;
        ride_prices[1] = unlimited7Price();
        ride_prices[2] = unlimited30Price();

        return ride_prices;
    }

    public String getBestFares(){

        double[] ride_prices = getRidePrices();
        int min_index = 0;
        for(int i=1; i<num_of_fares; i++){
            if(ride_prices[i]<ride_prices[min_index])
                min_index = i;
        }

        String option = "";
        if(min_index==0){
            option = Constants.pay_per_ride_string;
        }
        else if(min_index==1){
            option = Constants._7day_ride_string;
        }
        else if(min_index==2){
            option = Constants._30day_ride_string;
        }

        String message = "You should get the "+option+" option at $" +ride_prices[min_index]+ " per ride.";

        return message;
    }
}
