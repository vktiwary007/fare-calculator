package com.vivekasu.main;

import com.vivekasu.calculator.TransitCalculator;

public class Main {

    public static void main(String[] args) {

        TransitCalculator obj = new TransitCalculator(5,12);
        System.out.println(obj.unlimited30Price());

        TransitCalculator obj1 = new TransitCalculator(19,20);
        System.out.println(obj1.unlimited7Price());

        TransitCalculator obj2 = new TransitCalculator(6,14);
        System.out.println(obj2.unlimited7Price());

        System.out.println(obj.getBestFares());
    }
}
