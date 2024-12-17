package com.escmanager.menu;

import java.util.Scanner;

public class Main {

    public static final  Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        boolean running = true;

        while (running) {
            System.out.println("""
                    \n Escape Room Manager. Choose one of the following options:
                        1. Escape Room Management
                        2. Room Management
                        3. Hint Management
                        4. Prop Management
                        5. User Management
                        6. Ticket & Certification Management
                        7. Sales & Reports
                        8. Exit
                        """);
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> EscapeRoomMenu.showMenu();
                case 2 -> RoomMenu.showMenu();
                case 3 -> HintMenu.showMenu();
                case 4 -> PropMenu.showMenu();
                case 5 -> UserMenu.showMenu();
                case 6 -> TicketMenu.showMenu();
                case 7 -> SalesMenu.showMenu();
                case 8 -> {
                    System.out.println("Exiting Escape Room Manager. Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}