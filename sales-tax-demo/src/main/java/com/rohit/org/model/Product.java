package com.rohit.org.model;

import java.math.BigDecimal;

public class Product {
    private final String name;
    private final Category category;
    private final boolean isImported;
    private final BigDecimal unitPrice;

    public Product(String name, Category category, boolean isImported, BigDecimal unitPrice) {
        this.name = name;
        this.category = category;
        this.isImported = isImported;
        this.unitPrice = unitPrice;
    }


    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public boolean isImported() {
        return isImported;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (isImported != product.isImported) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (category != product.category) return false;
        return unitPrice != null ? unitPrice.equals(product.unitPrice) : product.unitPrice == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (isImported ? 1 : 0);
        result = 31 * result + (unitPrice != null ? unitPrice.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", category=" + category +
                ", isImported=" + isImported +
                ", unitPrice=" + unitPrice +
                '}';
    }
}