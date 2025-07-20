package com.rohit.org.input;

import com.rohit.org.model.ReceiptItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReceiptItemParser {
    public ReceiptItem parseInput(String line) {

        //check for imported product
        boolean isImported = isImported(line);

        //calculate quantity and price from input line
        List<BigDecimal> quantityPriceList = extractNumbers(line);
        int quantity = 0;
        BigDecimal price = BigDecimal.valueOf(0);
        try {
            quantity = (quantityPriceList.get(0)).intValue();
        } catch (Exception e) {
            System.out.println("Please input a valid String");
        }
        try {
            price = quantityPriceList.get(1);
        } catch (Exception e) {
            System.out.println("Please input a valid String");
        }

        //calculate product name from line
        String productName = null;
        int splitIndex = line.lastIndexOf("at");
        if (splitIndex == -1) {
            System.out.println("Bad Formatting");
        } else {
            String lineWithoutNumbers = removeNumbersFromLine(line);
            //String lineWithoutNumbers is a string between the qty and "at"

            productName = removeExtras(lineWithoutNumbers);
            //this removes "at" and "imported" from String lineWithoutNumbers & gives product name only

            productName = productName.trim();
        }
        return new ReceiptItem(productName,isImported,quantity, price);
    }

    private boolean isImported(String line) {
        return line.contains("imported");
    }

    private List<BigDecimal> extractNumbers(String input) {
        List<BigDecimal> numbers = new ArrayList<>();
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            String numberStr = matcher.group();
            BigDecimal number = new BigDecimal(numberStr);
            numbers.add(number);
        }
        return numbers;
    }
    public static String removeNumbersFromLine(String input) {
        return input.replaceAll("\\d+(\\.\\d+)?", "");
    }

    public static String removeExtras(String input) {
        input = input.replaceAll(" at ", "");
        return input.replaceAll("imported ", "");
    }
}