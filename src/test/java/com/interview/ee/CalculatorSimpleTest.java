package com.interview.ee;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CalculatorSimpleTest {

    private Calculator calculator = new CalculatorSimple();

    @Test
    public void givenAusCalculator_whenConstruct_thenCorrectTypeReturn() {

        assertEquals("SIMPLE", calculator.getCalculatorType());
    }

    @Test
    public void givenEmptyItems_whenGetTotal_thenValidAmountReturn() {
        List<ProductItem> items = new ArrayList<>();
        assertEquals(0, calculator.getPaymentAmount(items));
    }
    @Test
    public void givenNullList_whenGetTotal_thenValidAmountReturn() {
        assertEquals(0, calculator.getPaymentAmount(null));
    }

    @Test
    public void givenMultipleItems_whenGetTotal_thenValidAmountReturn() {
        List<ProductItem> items = new ArrayList<>();

        Product testProduct1 = new Product("test-1", "test product 1", 2);
        Product testProduct2 = new Product("test-2", "test product 1", 5);
        Product testProduct3 = new Product("test-3", "test product 1", 7);

        // 1 * 2 = 2
        items.add(new ProductItem(testProduct1, 1));
        // 5 * 2 = 10
        items.add(new ProductItem(testProduct2, 2));
        // 7 * 3 = 21
        items.add(new ProductItem(testProduct3, 3));

        //Total without tax = 33
        assertEquals(33, calculator.getPaymentAmount(items));
    }
}