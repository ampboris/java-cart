package com.interview.ee;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProductItemTest {

    private Product testProduct1 = new Product("test-1", "test product 1", 1999);
    private int testQuantity = 111;
    private ProductItem testProductItem = new ProductItem(testProduct1, testQuantity);

    @Test
    public void givenProduct_whenCreateItem_thenSameProductShouldReturn() {
        assertTrue("Product is changed when product item created!", testProduct1.equals(testProductItem.getProduct()));
    }

    @Test
    public void givenQuantity_whenCreateItem_thenSameQuantityShouldReturn() {
        assertTrue("Quantity is changed when product item created!", testQuantity == testProductItem.getQuantity());
    }

    @Test
    public void getItemTotal() {
        int expectedValue = testProduct1.getPrice() * testQuantity;
        assertEquals("product item sub total calculation come back unexpected value", expectedValue, testProductItem.getItemTotal());
    }
}