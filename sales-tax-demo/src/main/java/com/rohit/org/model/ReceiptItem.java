package com.rohit.org.model;

import java.math.BigDecimal;

public class ReceiptItem {
    private final String productName;
    private final boolean isImported;
    private final int quantity;
    private final BigDecimal unitPrice;

    public ReceiptItem(String productName, boolean isImported, int quantity, BigDecimal unitPrice) {
        this.productName = productName;
        this.isImported = isImported;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getProductName() {
        return productName;
    }

    public boolean isImported() {
        return isImported;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

}