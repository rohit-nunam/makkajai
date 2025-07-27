package com.rohit.org.service;

import java.util.Set;

public class CategoryService {
    private static final Set<String> foods = Set.of("chocolate", "chocolate bar", "box of chocolates");
    private static final Set<String> medicines = Set.of("packet of headache pills");
    private static final Set<String> books = Set.of("book");

    public static boolean isFood(String productName) {
        return foods.contains(productName);
    }

    public static boolean isMedical(String productName) {
        return medicines.contains(productName);
    }

    public static boolean isBook(String productName) {
        return books.contains(productName);
    }
}
