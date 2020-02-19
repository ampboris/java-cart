package com.interview.ee;

import java.util.List;

public class CalculatorAus extends Calculator {
    private String type = "AUS";
    private int taxRatePercent;

    public CalculatorAus(int taxRatePercent) {
        this.taxRatePercent = taxRatePercent;
    }

    @Override
    public String getCalculatorType(){
        return this.type;
    }

    @Override
    public int getPaymentAmount(List<ProductItem> items) {
        Integer sum = items == null ? 0 : items.stream()
                .map(x -> x.getItemTotal())
                .reduce(0, Integer::sum);

        // tax calculation could lead to decimal, decimal need to maintain before rounding!
        float total = sum.floatValue() * (100 + this.taxRatePercent) / 100;
        return Math.round(total);
    }
}
