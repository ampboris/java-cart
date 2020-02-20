package com.interview.ee;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Step1Test {
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
        assertEquals(19995, shoppingCart.getCheckoutAmount());
    }

}