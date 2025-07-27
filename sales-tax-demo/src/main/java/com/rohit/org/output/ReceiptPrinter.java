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

        for (BasketItem item : basketItems) {
            Product product = item.getProduct();
            String importedText = product.isImported() ? "imported " : "";
            BigDecimal itemTax = item.getTotalTax();
            BigDecimal itemPriceWithTax = item.getTotalPriceWithTax();

            totalSalesTax = totalSalesTax.add(itemTax);
            totalAmount = totalAmount.add(itemPriceWithTax);

            output += item.getQuantity() + " " + importedText + product.getName() + ": " + itemPriceWithTax + "\n";
        }

        output = output + generateSalesTaxAndTotalAmount(totalSalesTax, totalAmount);
        return output;
    }

    public String generateSalesTaxAndTotalAmount(BigDecimal totalSalesTax, BigDecimal totalAmount) {
        String taxAndTotalAmount;
        taxAndTotalAmount = "Sales Taxes: " + totalSalesTax + '\n';
        taxAndTotalAmount = taxAndTotalAmount + "Total: " + totalAmount + '\n';
        return taxAndTotalAmount;
    }
}