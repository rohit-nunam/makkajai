package com.rohit.org;

import com.rohit.org.input.ReceiptItemParser;
import com.rohit.org.model.Basket;
import com.rohit.org.model.BasketItem;
import com.rohit.org.model.Product;
import com.rohit.org.model.ReceiptItem;
import com.rohit.org.output.ReceiptPrinter;
import com.rohit.org.service.CategoryService;
import com.rohit.org.service.TaxCalculator;

import java.util.List;

public class TaxPriceCalculatorSystem {
    Basket basket = new Basket();
    CategoryService categoryService = new CategoryService();
    ReceiptItemParser receiptItemParser = new ReceiptItemParser();
    TaxCalculator taxCalculator = new TaxCalculator();
    ReceiptPrinter receiptPrinter=new ReceiptPrinter();

    public String getReceipt(List<String> inputItems) {
        for (String item:inputItems) {
            ReceiptItem receiptItem = receiptItemParser.parseInput(item);
            Product product=new Product(
                    receiptItem.getProductName(),
                    categoryService.getCategoryByProductName(receiptItem.getProductName()),
                    receiptItem.isImported(),
                    receiptItem.getUnitPrice()
            );
            BasketItem basketItem = new BasketItem(
                    product,
                    receiptItem.getQuantity(),
                    receiptItem.getUnitPrice(),
                    taxCalculator.getTaxPerUnitProduct(product)
            );
            basket.addBasketItem(basketItem);
        }

        return receiptPrinter.generateReceipt(basket);
    }
}