package com.interview.ee;

import java.util.List;

public abstract class Calculator {
    public abstract String getCalculatorType();
    public abstract int getPaymentAmount(List<ProductItem> items);
}
