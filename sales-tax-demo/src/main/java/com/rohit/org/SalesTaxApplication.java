package com.rohit.org;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SalesTaxApplication {
    public static void main(String[] args) throws IOException {
        List<String> listOfInputItems = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = br.readLine()) != null && !line.isEmpty()) {
            listOfInputItems.add(line);
        }

        TaxPriceCalculatorSystem taxPriceCalculatorSystem = new TaxPriceCalculatorSystem();
        String receipt = taxPriceCalculatorSystem.getReceipt(listOfInputItems);

        System.out.println(receipt);
    }
}