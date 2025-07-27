package com.rohit.org.model;

import com.rohit.org.model.Category;
import com.rohit.org.service.CategoryService;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Product {
    private final String name;
    private final boolean isImported;
    private final BigDecimal unitPrice;

    public Product(String name, boolean isImported, BigDecimal unitPrice) {
        this.name = name;
        this.isImported = isImported;
        this.unitPrice = unitPrice;
    }

    public String getName() {
        return name;
    }

    public boolean isImported() {
        return isImported;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public Category getCategory() {
        if (CategoryService.isBook(name)) return Category.BOOK;
        if (CategoryService.isMedical(name)) return Category.MEDICAL;
        if (CategoryService.isFood(name)) return Category.FOOD;
        return Category.OTHERS;
    }

    public boolean isTaxExempted() {
        Category cat = getCategory();
        return cat == Category.BOOK || cat == Category.FOOD || cat == Category.MEDICAL;
    }

    public BigDecimal calculateTax() {
        BigDecimal tax = BigDecimal.ZERO;

        if (!isTaxExempted()) {
            tax = tax.add(unitPrice.multiply(BigDecimal.valueOf(0.10)));
        }

        if (isImported) {
            tax = tax.add(unitPrice.multiply(BigDecimal.valueOf(0.05)));
        }

        return roundTax(tax);
    }

    private BigDecimal roundTax(BigDecimal value) {
        return value.divide(new BigDecimal("0.05"), 0, RoundingMode.UP).multiply(new BigDecimal("0.05"));
    }
}
