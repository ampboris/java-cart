package com.interview.ee;

import java.io.Serializable;

public class ProductItem implements Serializable {
    private Product product;
    private int quantity;

    public ProductItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return this.product;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getItemTotal() {
        return this.product.getPriceInCents() * this.quantity;
    }
}
