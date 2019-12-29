package com.vivekasu.calculator;

import com.vivekasu.constants.Constants;
import com.vivekasu.pojo.RidesPOJO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/***
 * @author vivek
 *
 * This class is used to write the business logic for calculating fares for a bus transit system.
 */
public class TransitCalculator extends RidesPOJO{

    //types of fares available to end-users like pay-per-ride or 7-day-unlimited etc.
    private int num_of_fares;

    //pay-per-ride value
    private double pay_per_ride;
    //7-day pass value
    private double _7day_ride;
    //30-day pass value
    private double _30day_ride;

    //format to return fares with 2 decimal points
    private DecimalFormat df = new DecimalFormat("0.00");

    /**
     * Costructor to initialize POJO class for fare calculation
     * @param num_of_days is the number of days for which pass is needed.
     * @param num_of_rides is the number of rides taken by user.
     */
    public TransitCalculator(int num_of_days, int num_of_rides ){
        super(num_of_days,num_of_rides );
        this.num_of_fares = Constants.num_of_fares;
        this._7day_ride =  Constants._7day_ride;
        this.pay_per_ride = Constants.pay_per_ride;
        this._30day_ride = Constants._30day_ride;
    }

    /**
     * This method calculates the fare per ride if the user takes 7-day unlimited pass
     * @return the fare per ride based on 7-day unlimited pass
     */
    public double unlimited7Price(){

        //Take ceiling as division will return decimals and passes are based on 7 day period only. Can't be anything in-between
        double pay_per_ride = (Math.ceil((double)getNum_of_days()/7)*_7day_ride)/getNum_of_rides();
        BigDecimal bd = new BigDecimal(pay_per_ride).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();

    }

    /**
     * This method calculates the fare per ride if the user takes 30-day unlimited pass
     * @return the fare per ride based on 30-day unlimited pass
     */
    public double unlimited30Price(){
        //Take ceiling as division will return decimals and passes are based on 30 day period only. Can't be anything in-between
        double pay_per_ride = (Math.ceil((double)getNum_of_days()/30)*_30day_ride)/getNum_of_rides();
        BigDecimal bd = new BigDecimal(pay_per_ride).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();

    }

    /**
     * This method calculates the fare per ride for each pass options available to users.
     *
     * @return prices that user has to pay per ride based on different types of pass options available.
     * [0] gives pay-per-ride,[1] gives pay-per-ride for 7-day unlimited pass, [2] gives pay-per-ride for 7-day unlimited pass
     */
    public double[] getRidePrices(){

        double[] ride_prices = new double[num_of_fares];

        ride_prices[0] = pay_per_ride;
        ride_prices[1] = unlimited7Price();
        ride_prices[2] = unlimited30Price();

        return ride_prices;
    }

    /**
     * This method finds the minimum of the fare per ride available to user based on multiple pass options.
     * @return generic message providing the best(minimum) fare per ride option available to user.
     */
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
