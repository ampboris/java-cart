package com.interview.ee;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProductTest {
    private String sku = "EE-2020";
    private String name = "java cart demo";
    private int price = 1999;

    @Test
    public void givenProductDetails_whenCreateProduct_thenCorrectValuesReturn() {
        Product product = new Product(sku, name, price);

        assertNotNull(product);
        assertEquals(sku, product.getSku());
        assertEquals(name, product.getName());
        assertEquals(price, product.getPriceInCents());
    }

    @Test
    public void givenTwoProductWithSameDetails_whenCompare_thenEqualTrueReturn() {
        Product product1 = new Product(sku, name, price);
        Product product2 = new Product(sku, name, price);

        assertTrue(product1.equals(product2));
    }
}