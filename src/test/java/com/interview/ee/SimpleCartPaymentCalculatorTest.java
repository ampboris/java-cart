package com.interview.ee;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SimpleCartPaymentCalculatorTest {

    private float taxRatePct = 10.5f;
    private CartPaymentCalculator calculator = new SimpleCartPaymentCalculator(taxRatePct);

    @Test
    public void givenCalculator_whenGetRate_thenValidRateReturn() {
        assertTrue(taxRatePct == calculator.getTaxRatePercent());
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
    public void givenItemsRoundingRequired_whenGetTotal_thenValidAmountReturn() {
        List<ProductItem> items = new ArrayList<>();

        Product testProduct = new Product("test-1", "test product 1", 3);
        // 3 * 1.105 = 3.315 => 3
        items.add(new ProductItem(testProduct, 1));
        assertEquals(3, calculator.getPaymentAmount(items));

        // 5 * 1.105 = 5.525 => 6
        testProduct = new Product("test-1", "test product 1", 5);
        items.clear();
        items.add(new ProductItem(testProduct, 1));
        assertEquals(6, calculator.getPaymentAmount(items));

        // 6 * 1.105 = 6.63 => 7
        testProduct = new Product("test-1", "test product 1", 6);
        items.clear();
        items.add(new ProductItem(testProduct, 1));
        assertEquals(7, calculator.getPaymentAmount(items));
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


        //Total without tax = 33, with tax = 33 * 1.105 = 36.465 => 36
        assertEquals(3, calculator.getPaymentTaxAmount(items));
        assertEquals(33, calculator.getPaymentAmountExcludeTax(items));
        assertEquals(36, calculator.getPaymentAmount(items));
    }
}