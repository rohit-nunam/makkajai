package com.rohit.org.model;


import java.math.BigDecimal;

public class BasketItem {
    private final Product product;
    private final int quantity;
    private final BigDecimal price;
    private final BigDecimal tax;

    public BasketItem(Product product, int quantity, BigDecimal price, BigDecimal tax) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.tax = tax;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Product getProduct() {
        return product;
    }

    public BigDecimal getTaxPrice() {
        return tax;
    }
}