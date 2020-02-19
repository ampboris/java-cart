package com.interview.ee;

import java.util.Objects;

public class Product {
    private String sku;
    private String name;
    // price unit is cent, no decimal!!!
    private int price;

    public Product(String sku, String name, int price) {
        this.sku = sku;
        this.name = name;
        this.price = price;
    }

    public int getPrice() {
        return this.price;
    }

    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Product product = (Product) o;
        return getPrice() == product.getPrice() &&
                Objects.equals(getSku(), product.getSku()) &&
                Objects.equals(getName(), product.getName());
    }
}
