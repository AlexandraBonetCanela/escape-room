package com.escmanager.menu;

import com.escmanager.model.InventoryItem;
import com.escmanager.service.InventoryService;

import java.math.BigDecimal;
import java.util.List;

import static com.escmanager.menu.Menu.scanner;
import static com.escmanager.menu.TicketMenu.ticketService;

public class SalesInventoryMenu {

    static InventoryService inventoryService = InventoryService.getInstance();

    public static void showMenu() {

        final int SALES = 1;
        final int INVENTORY = 2;
        final int MAIN_MENU = 3;
        boolean backToMain = false;

        while (!backToMain) {
            System.out.println("""
                    Sales & Inventory Management. Choose an option:
                    1. Get Total Sales
                    2. See Inventory
                    3. Back to Main Menu
                    """);
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case SALES -> {
                    System.out.println("\nTICKET MONEY EARNED");
                    BigDecimal moneyEarned = ticketService.getMoneyEarned();
                    System.out.println("Money earned: " + moneyEarned + "$");
                }
                case INVENTORY-> {
                    System.out.println("\nINVENTORY");
                    printInventory();
                }
                case MAIN_MENU -> backToMain = true;

                default -> System.out.println("Invalid choice. Returning to main menu.");
            }
        }
    }

    private static void printInventory(){
        String[] columnNames = {"type", "reference", "name", "price", "details"};
        System.out.println();
        for (String columnName : columnNames) {
            System.out.printf("%-26s", columnName);
        }
        System.out.println();
        System.out.println("=".repeat(columnNames.length * 20));

        List<InventoryItem> inventoryItemList = inventoryService.getInventory();
        for(InventoryItem inventoryItem: inventoryItemList){
            System.out.print(inventoryItem.toString() + "\n");
        }
        System.out.println();
    }
}