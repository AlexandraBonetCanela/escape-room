package com.escmanager.menu;

import com.escmanager.exceptions.escaperoom.EscapeRoomAlreadyExistException;
import com.escmanager.exceptions.escaperoom.EscapeRoomDoesNotExistException;

import java.math.BigDecimal;

import static com.escmanager.menu.Main.scanner;
import static com.escmanager.menu.MenuUtils.getNonEmptyString;
import static com.escmanager.menu.TicketMenu.ticketService;

public class SalesMenu {

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
                        //TODO:
                    }
                    case MAIN_MENU -> backToMain = true;

                    default -> System.out.println("Invalid choice. Returning to main menu.");
                }

        }
    }
}