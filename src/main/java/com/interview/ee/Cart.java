package com.interview.ee;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Cart {
    // key is product sku, maintain uniqueness of product and simplify lookup
    private Map<String, ProductItem> items = new HashMap<>();
    // default calculator
    private Calculator calculator;

    public Cart(Calculator calculator) {
        this.calculator = calculator;
    }

    // list all items in cart
    public Map<String, ProductItem> getItems() {
        return this.items;
    }

    public void addItem(Product product, int quantity) {
        ProductItem item = new ProductItem(product, quantity);
        this.items.merge(product.getSku(), item , (k, v) -> item );
    }

    // remove item from cart
    public void removeItem(Product product) {
        this.items.remove(product.getSku());
    }

    public void emptyCart() {
        this.items.clear();
    }

    public int getPaymentAmount() {
        return this.calculator.getPaymentAmount(this.items.values().stream().collect(Collectors.toList()));
    }
}
