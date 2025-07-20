package com.rohit.org.service;

import com.rohit.org.model.Category;

import java.util.Set;

public class CategoryService {

    Set<String> foods=Set.of("chocolate","chocolate bar","box of chocolates");
    Set<String> medicines=Set.of("packet of headache pills");
    Set<String> books=Set.of("book");


    public Category getCategoryByProductName(String productName) {
        if(foods.contains(productName)){
            return Category.FOOD;
        }
         else if(medicines.contains(productName)){
            return Category.MEDICAL;
        }
         else if(books.contains(productName)){
            return Category.BOOK;
        }
        return Category.OTHERS;
    }
}