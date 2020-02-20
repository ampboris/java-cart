package com.interview.ee;

public class Product {
    private String sku;
    private String name;
    // priceInCents:  unit is cent, no decimal!!!
    private int priceInCents;

    public Product(String sku, String name, int priceInCents) {
        this.sku = sku;
        this.name = name;
        this.priceInCents = priceInCents;
    }

    public int getPriceInCents() {
        return this.priceInCents;
    }

    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return getPriceInCents() == product.getPriceInCents() &&
                getSku().equals(product.getSku()) &&
                getName().equals(product.getName());
    }
}
