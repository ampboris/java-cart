package com.interview.ee;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class InterviewTest {
    @Test
    public void givenOneProductAndEmptyCart_whenAdd_thenSameProductAndRightTotalReturn() {
        // testcase input
        String productSku = "dove-soap";
        String productName = "Dove Soap";
        int productPrice = 3999;
        int orderQuantity = 5;

        // An empty shopping cart using simple pricing calculator
        Cart shoppingCart = new Cart(new SimpleCartPaymentCalculator(0));
        // Product - Dove Soap with a unit price of $39.99
        Product product = new Product(productSku, productName, productPrice);

        //  adds 5 Dove Soaps to the shopping cart
        shoppingCart.addItem(product, orderQuantity);

        ProductItem item = shoppingCart.getItemBySku(product.getSku());
        // The shopping cart should contain 5 Dove Soaps
        assertEquals(orderQuantity, item.getQuantity());
        // each with a unit price of $39.99
        assertEquals(productPrice, item.getProduct().getPriceInCents());
        assertTrue(product.equals(item.getProduct()));
        // the shopping cart’s total price should equal $199.95
        assertEquals(19995, shoppingCart.getPaymentAmount());
    }

    @Test
    public void givenOneProductAndEmptyCart_whenAddMultipleTime_thenSameProductAndRightTotalReturn() {
        // testcase input
        String productSku = "dove-soap";
        String productName = "Dove Soap";
        int productPrice = 3999;
        int orderQuantity = 5;

        // An empty shopping cart using simple cart payment calculator with 0 tax rate
        Cart shoppingCart = new Cart(new SimpleCartPaymentCalculator(0));
        // Product - Dove Soap with a unit price of $39.99
        Product product = new Product(productSku, productName, productPrice);

        //  adds 5 Dove Soaps to the shopping cart
        shoppingCart.addItem(product, orderQuantity);

        //  adds 3 Dove Soaps to the shopping cart
        shoppingCart.addItem(product, 3);

        ProductItem item = shoppingCart.getItemBySku(product.getSku());
        // The shopping cart should contain 8 Dove Soaps
        assertEquals(orderQuantity + 3, item.getQuantity());
        // each with a unit price of $39.99
        assertEquals(productPrice, item.getProduct().getPriceInCents());
        assertTrue(product.equals(item.getProduct()));
        // the shopping cart’s total price should equal $319.92
        assertEquals(31992, shoppingCart.getPaymentAmount());
    }

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

        // An empty shopping cart using simple cart payment calculator with 12.5% tax rate
        float taxRatePercent = 12.5f;
        Cart shoppingCart = new Cart(new SimpleCartPaymentCalculator(taxRatePercent));


        //  adds 2 Dove Soaps to the shopping cart
        int doveQuantity = 2;
        shoppingCart.addItem(productDove, doveQuantity);

        //  adds 3 Dove Soaps to the shopping cart
        int axeQuantity = 2;
        shoppingCart.addItem(productAxe, axeQuantity);


        ProductItem doveItem = shoppingCart.getItemBySku(productDove.getSku());
        assertEquals(doveQuantity, doveItem.getQuantity());
        assertEquals(dovePriceInCents, doveItem.getProduct().getPriceInCents());
        assertTrue(productDove.equals(doveItem.getProduct()));


        ProductItem axeItem = shoppingCart.getItemBySku(productAxe.getSku());
        assertEquals(axeQuantity, axeItem.getQuantity());
        assertEquals(axePriceInCents, axeItem.getProduct().getPriceInCents());
        assertTrue(productAxe.equals(axeItem.getProduct()));

        assertEquals(3500, shoppingCart.getTaxAmount());
        assertEquals(31496, shoppingCart.getPaymentAmount());
        assertEquals(27996, shoppingCart.getPaymentAmountExcludeTax());
    }
}