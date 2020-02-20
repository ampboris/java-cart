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
    public int getPaymentTaxAmount(List<ProductItem> items) {
        float serviceTax = this.getPaymentAmountExcludeTax(items) * this.taxRatePercent / 100;
        return Math.round(serviceTax);
    }

    @Override
    public int getPaymentAmount(List<ProductItem> items) {
        return this.getPaymentAmountExcludeTax(items) + this.getPaymentTaxAmount(items);
    }

    @Override
    public int getPaymentAmountExcludeTax(List<ProductItem> items) {
        return items == null ? 0 : items.stream()
                .map(x -> x.getItemTotal())
                .reduce(0, Integer::sum);
    }
}
