package com.rohit.org;

import com.rohit.org.input.ReceiptItemParser;
import com.rohit.org.model.Basket;
import com.rohit.org.model.BasketItem;
import com.rohit.org.model.Product;
import com.rohit.org.model.ReceiptItem;
import com.rohit.org.output.ReceiptPrinter;

import java.util.List;

public class TaxPriceCalculatorSystem {
    private final Basket basket = new Basket();
    private final ReceiptItemParser parser = new ReceiptItemParser();
    private final ReceiptPrinter printer = new ReceiptPrinter();

    public String getReceipt(List<String> inputItems) {
        for (String line : inputItems) {
            ReceiptItem r = parser.parseInput(line);
            Product product = new Product(r.getProductName(), r.isImported(), r.getUnitPrice());
            basket.addBasketItem(new BasketItem(product, r.getQuantity()));
        }
        return printer.generateReceipt(basket);
    }
}
