package com.interview.ee;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Step3Test {
    @Test
    public void givenMultipleProductsAndEmptyCart_whenAddMAllProducts_thenProductAndRightTotalReturn() {
        // Product - Dove Soap with a unit price of $39.99
        String doveSku = "dove-soap";
        String doveName = "Dove Soap";
        int dovePriceInCents = 3999;
        Product productDove = new Product(doveSku, doveName, dovePriceInCents);

        // Product - Dove Soap with a unit price of $39.99
        String axeSku = "axe-deo";
        String axeName = "Axe Deo";
        int axePriceInCents = 9999;
        Product productAxe = new Product(axeSku, axeName, axePriceInCents);

        // An empty shopping cart with sales tax rate of 12.5%
        float taxRatePercent = 12.5f;
        Cart shoppingCart = new Cart(new SimpleCartPaymentCalculator(taxRatePercent));


        //  adds 2 Dove Soaps to the shopping cart
        int doveQuantity = 2;
        shoppingCart.addItem(productDove, doveQuantity);

        //  adds 3 Dove Soaps to the shopping cart
        int axeQuantity = 2;
        shoppingCart.addItem(productAxe, axeQuantity);

        //shopping cart should contain 2 Dove Soaps each with a unit price of 39.99
        ProductItem doveItem = shoppingCart.getItemBySku(productDove.getSku());
        assertEquals(doveQuantity, doveItem.getQuantity());
        assertEquals(dovePriceInCents, doveItem.getProduct().getPriceInCents());
        assertTrue(productDove.equals(doveItem.getProduct()));

        // shopping cart should contain 2 Axe Deos each with a unit price of 99.99
        ProductItem axeItem = shoppingCart.getItemBySku(productAxe.getSku());
        assertEquals(axeQuantity, axeItem.getQuantity());
        assertEquals(axePriceInCents, axeItem.getProduct().getPriceInCents());
        assertTrue(productAxe.equals(axeItem.getProduct()));

        // the total sales tax amount for the shopping cart should equal 35.00
        assertEquals(3500, shoppingCart.getCheckoutTaxAmount());
        assertEquals(27996, shoppingCart.getCheckoutAmountExcludeTax());
        // the shopping cart’s total price should equal 314.96
        assertEquals(31496, shoppingCart.getCheckoutAmount());

    }
}