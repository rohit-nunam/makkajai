package com.rohit.org.service;
import com.rohit.org.model.Category;
import com.rohit.org.model.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TaxCalculator {
    private final BigDecimal importTaxPercentage=BigDecimal.valueOf(5);
    private final BigDecimal basicSalesTaxPercentage=BigDecimal.valueOf(10);

    public BigDecimal getTaxPerUnitProduct(Product product){
        BigDecimal unitPrice = product.getUnitPrice();

        boolean isImported = product.isImported();
        boolean isExempted = false;
        Category productCategory = product.getCategory();
        if (productCategory.equals(Category.BOOK) ||
                productCategory.equals(Category.FOOD) ||
                productCategory.equals(Category.MEDICAL)) {
            isExempted = true;
        }
        BigDecimal nonRoundOffTax= taxForBasketItem(isImported,isExempted,unitPrice);
        return calculateRoundOffValue(nonRoundOffTax);
    }

    private BigDecimal taxForBasketItem(boolean isImported, boolean isExempted, BigDecimal unitPrice) {
        BigDecimal importDuty = BigDecimal.ZERO;
        BigDecimal basicSalesDuty = BigDecimal.ZERO;
        if(isImported && isExempted) {
            importDuty = calculateTaxByTaxPercentage(unitPrice,importTaxPercentage);
        }
        else if(!isImported && !isExempted) {
            basicSalesDuty=calculateTaxByTaxPercentage(unitPrice,basicSalesTaxPercentage);
        }
        else if(!isImported && isExempted) {
            return BigDecimal.ZERO;
        }
        else if (isImported && !isExempted) {
            importDuty = calculateTaxByTaxPercentage(unitPrice,importTaxPercentage);
            basicSalesDuty=calculateTaxByTaxPercentage(unitPrice,basicSalesTaxPercentage);
        }
        return importDuty.add(basicSalesDuty);
    }

    private BigDecimal calculateRoundOffValue(BigDecimal decimalNumber) {
        BigDecimal roundedNumber = decimalNumber.divide(new BigDecimal("0.05"), 0, RoundingMode.UP)
                .multiply(new BigDecimal("0.05"));
        return roundedNumber;
    }

    private BigDecimal calculateTaxByTaxPercentage(BigDecimal unitPrice, BigDecimal taxPercentage){
        BigDecimal calculatedTax = (taxPercentage.multiply(unitPrice)).divide(BigDecimal.valueOf(100));
        return calculatedTax;
    }
}