package com.vivekasu.calculator;

/**
 * @author vivek
 *
 * Base interface for fare calculator transit system, can be implemented by any transit system eg: Rail, Bus etc.
 */
public interface CalculatorService {

    public double unlimited7Price();

    public double unlimited30Price();

    public String getBestFares();
}
