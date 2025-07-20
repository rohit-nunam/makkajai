package com.rohit.org.output;

import com.rohit.org.model.BasketItem;
import com.rohit.org.model.Basket;
import com.rohit.org.model.Product;

import java.math.BigDecimal;
import java.util.List;

public class ReceiptPrinter {

    BigDecimal totalSalesTax = BigDecimal.ZERO;
    BigDecimal totalAmount = BigDecimal.ZERO;


    public String generateReceipt(Basket basket) {
        List<BasketItem> basketItems = basket.getBasketItems();
        String output = "";

        for (var basketItem : basketItems) {
            Product product = basketItem.getProduct();
            String isImported = imported(product);

            // considering quantity 1 for all
            BigDecimal totalPrice = basketItem.getPrice().add(basketItem.getTaxPrice());
            totalSalesTax = totalSalesTax.add(basketItem.getTaxPrice());
            totalAmount = totalAmount.add(totalPrice);
            String line = basketItem.getQuantity() + " " + isImported + product.getName() + ": " + totalPrice;
            line = line + '\n';
            output = output + line;
        }
        output = output + generateSalesTaxAndTotalAmount(totalSalesTax, totalAmount);
        return output;
    }

    private String imported(Product product) {
        String imported;
        if (product.isImported())
            imported = "imported ";
        else
            imported = "";
        return imported;
    }

    public String generateSalesTaxAndTotalAmount(BigDecimal totalSalesTax, BigDecimal totalAmount) {
        String taxAndTotalAmount;
        taxAndTotalAmount = "Sales Taxes: " + totalSalesTax + '\n';
        taxAndTotalAmount = taxAndTotalAmount + "Total: " + totalAmount + '\n';
        return taxAndTotalAmount;
    }
}