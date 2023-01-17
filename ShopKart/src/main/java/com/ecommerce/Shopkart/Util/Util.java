package com.ecommerce.Shopkart.Util;

public class Util {
    public static boolean stringNullChecker(String str){
        return str!=null && !str.isEmpty() && !str.equalsIgnoreCase("null") && !str.equalsIgnoreCase("na");

    }
}
