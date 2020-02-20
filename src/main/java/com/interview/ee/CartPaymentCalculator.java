package com.interview.ee;

import java.util.List;

public abstract class CartPaymentCalculator {
    public abstract float getTaxRatePercent();
    public abstract int getTaxAmount(List<ProductItem> items);
    public abstract int getPaymentAmount(List<ProductItem> items);
    public abstract int getPaymentAmountExcludeTax(List<ProductItem> items);
}
