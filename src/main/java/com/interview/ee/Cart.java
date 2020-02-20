package com.interview.ee;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Cart {
    // key is product sku, maintain uniqueness of product and simplify lookup
    private Map<String, ProductItem> items = new Hashtable<>();
    private CartPaymentCalculator calculator;

    public Cart(CartPaymentCalculator calculator) {
        //always has a default calculator!!
        this.calculator = calculator != null ? calculator : new SimpleCartPaymentCalculator(0);
    }

    // list all items in cart
    public List<ProductItem> getItems() {
        return this.items.values().stream().collect(Collectors.toList());
    }

    // place order here
    public void addItem(Product product, int quantity) {
        if(product != null) {
            // if sku is not found, it push new order item in
            // if sku is found, it do merge, basically accumulate order quantity.
            this.items.merge(product.getSku(), (new ProductItem(product, quantity)) , (oldItem, newItem) -> new ProductItem(oldItem.getProduct(), oldItem.getQuantity() + newItem.getQuantity()));
        }
    }

    // get product item by sku
    public ProductItem getItemBySku(String sku) {
        return this.items.get(sku);
    }

    // remove item from cart
    public void removeItemBySku(String sku) {
        this.items.remove(sku);
    }
    // empty cart
    public void emptyCart() {
        this.items.clear();
    }

    // return full payment amount include tax
    public int getCheckoutAmount() {
        return this.calculator.getPaymentAmount(this.getItems());
    }
    // return tax
    public int getCheckoutTaxAmount() {
        return this.calculator.getPaymentTaxAmount(this.getItems());
    }
    // return amount without tax
    public int getCheckoutAmountExcludeTax() {
        return this.calculator.getPaymentAmountExcludeTax(this.getItems());
    }
}
