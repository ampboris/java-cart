package com.interview.ee;

import java.util.List;

public class SimpleCartPaymentCalculator extends CartPaymentCalculator {
    private float taxRatePercent;

    public SimpleCartPaymentCalculator(float taxRatePercent) {
        this.taxRatePercent = taxRatePercent;
    }

    @Override
    public float getTaxRatePercent() {
        return this.taxRatePercent;
    }

    @Override
    public int getTaxAmount(List<ProductItem> items) {
        float serviceTax = this.getPaymentAmountExcludeTax(items) * this.taxRatePercent / 100;
        return Math.round(serviceTax);
    }

    @Override
    public int getPaymentAmount(List<ProductItem> items) {
//        float total = this.getPaymentAmountExcludeTax(items) * (100 + this.taxRatePercent) / 100;
//        return Math.round(total);
        return this.getPaymentAmountExcludeTax(items) + this.getTaxAmount(items);
    }

    @Override
    public int getPaymentAmountExcludeTax(List<ProductItem> items) {
        return items == null ? 0 : items.stream()
                .map(x -> x.getItemTotal())
                .reduce(0, Integer::sum);
    }
}
