package com.rohit.org.model;

import com.rohit.org.model.Product;

import java.math.BigDecimal;

public class BasketItem {
    private final Product product;
    private final int quantity;

    public BasketItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public BigDecimal getTotalTax() {
        return product.calculateTax().multiply(BigDecimal.valueOf(quantity));
    }

    public BigDecimal getTotalPriceWithTax() {
        return product.getUnitPrice()
                .add(product.calculateTax())
                .multiply(BigDecimal.valueOf(quantity));
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}
