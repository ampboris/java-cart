package com.interview.ee;

import org.junit.Test;

import static org.junit.Assert.*;

public class CartTest {

    // using with Aus calculator
    private float gstPercent = 10;
    private Cart cart = new Cart(new SimpleCartPaymentCalculator(gstPercent));
    private Product testProduct1 = new Product("test-1", "test product 1", 1999);
    private Product testProduct2 = new Product("test-2", "test product 2", 3);
    private Product testProduct3 = new Product("test-3", "test product 3", 11300);

    @Test
    public void givenNewProductItem_whenAdd_thenCorrectItemReturn() {
        cart.emptyCart();
        assertEquals("cart is NOT empty",0, cart.getItems().size());
        cart.addItem(testProduct1, 3);
        assertEquals("item count is 1 after add new item",1, cart.getItems().size());
        assertEquals("item count is 1 after add new item",3, cart.getItemBySku(testProduct1.getSku()).getQuantity());
    }

    @Test
    public void givenUpdatedProductItem_whenAdd_thenUpdatedItemReturn() {
        cart.emptyCart();
        assertEquals("cart is NOT empty",0, cart.getItems().size());
        cart.addItem(testProduct1, 3);
        assertEquals("cart has NO or More than 1 item",1, cart.getItems().size());
        assertEquals("product item quantity is NOT as expected",3, cart.getItemBySku(testProduct1.getSku()).getQuantity());

        cart.addItem(testProduct1, 7);
        assertEquals("cart has NO or More than 1 item",1, cart.getItems().size());
        assertEquals("product item quantity is NOT as expected",10, cart.getItemBySku(testProduct1.getSku()).getQuantity());
    }

    @Test
    public void givenExistedProduct_whenRemoveFromCart_thenOnlyRequestItemRemoved() {
        cart.addItem(testProduct1, 1);
        cart.addItem(testProduct2, 2);
        cart.addItem(testProduct3, 3);
        assertEquals("cart has LESS or More than 3 item",3, cart.getItems().size());
        cart.removeItem(testProduct2);
        assertEquals("cart has LESS or More than 2 item",2, cart.getItems().size());
    }

    @Test
    public void givenProductNotInCart_whenRemove_thenItemsStillThere() {
        cart.addItem(testProduct1, 1);
        cart.addItem(testProduct3, 3);
        assertEquals("cart has LESS or More than 2 item",2, cart.getItems().size());
        cart.removeItem(testProduct2);
        assertEquals("cart has LESS or More than 2 item",2, cart.getItems().size());
    }

    @Test
    public void givenNotEmptyCart_whenEmptyCart_thenNoItemReturn() {
        cart.addItem(testProduct1, 1);
        assertTrue("cart is empty", cart.getItems().size() > 0);
        cart.emptyCart();
        assertTrue("cart is NOT empty after empty cart", cart.getItems().size() == 0);
    }

    @Test
    public void givenEmptyCart_whenGetPaymentAmount_thenZeroReturn() {
        cart.emptyCart();
        assertTrue("cart is NOT empty", cart.getItems().size() == 0);
        int total = cart.getPaymentAmount();
        assertTrue("empty cart has invalid payment total", total == 0);
    }

    @Test
    public void givenNoneEmptyCart_whenGetPaymentAmount_thenCorrectTotalReturn() {
        cart.emptyCart();
        assertTrue("cart is NOT empty", cart.getItems().size() == 0);
        cart.addItem(testProduct1, 1);
        cart.addItem(testProduct2, 2);
        cart.addItem(testProduct3, 3);
        assertEquals("cart has LESS or More than 3 item",3, cart.getItems().size());
        int total = cart.getPaymentAmount();
        // 39495.5 => 39496
        assertEquals("payment amount is not expected",39496, total);
    }
}