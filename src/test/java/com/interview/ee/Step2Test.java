package com.interview.ee;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Step2Test {
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
        assertEquals(31992, shoppingCart.getCheckoutAmount());
    }

}