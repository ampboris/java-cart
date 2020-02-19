package com.interview.ee;

import java.util.List;

public class CalculatorSimple extends Calculator {
    private String type = "SIMPLE";

    @Override
    public int getPaymentAmount(List<ProductItem> items) {
        Integer sum = items == null ? 0 : items.stream()
                .map(x -> x.getItemTotal())
                .reduce(0, Integer::sum);

        return sum.intValue();
    }

    @Override
    public String getCalculatorType(){
        return this.type;
    }
}
