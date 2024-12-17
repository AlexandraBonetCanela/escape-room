package com.escmanager.menu;

import static com.escmanager.menu.Main.scanner;

public class MenuUtils {
    public static String getNonEmptyString(String propertyName) {
        String result = null;
        while(result == null || result.trim().isEmpty()) {
            System.out.print("Enter " + propertyName + ": ");
            result = scanner.nextLine();
            if(result == null || result.trim().isEmpty()) {
                System.out.println("Error: " + propertyName + " can't be empty. Please try again.");
            }
        }

        return result;
    }
}
