package com.escmanager.menu;


import java.util.Scanner;

public class Main {

    public static final  Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        boolean running = true;

        final int ESCAPE_ROOM_MENU = 1;
        final int ROOM_MENU = 2;
        final int HINT_MENU = 3;
        final int PROP_MENU = 4;
        final int USER_MENU = 5;
        final int TICKET_CERTIFICATE_MENU = 6;
        final int SALES_REPORT_MENU = 7;
        final int EXIT = 8;

        while (running) {
            System.out.println("""
                    \nMENU
                        1. Escape Room Management
                        2. Room Management
                        3. Hint Management
                        4. Prop Management
                        5. User Management
                        6. Ticket & Certification Management
                        7. Sales & Reports
                        8. Exit
                    """);
            System.out.print("Choose one of the following options: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case ESCAPE_ROOM_MENU -> EscapeRoomMenu.showMenu();
                case ROOM_MENU -> RoomMenu.showMenu();
                case HINT_MENU -> HintMenu.showMenu();
                case PROP_MENU -> PropMenu.showMenu();
                case USER_MENU -> UserMenu.showMenu();
                case TICKET_CERTIFICATE_MENU -> TicketMenu.showMenu();
                case SALES_REPORT_MENU -> SalesMenu.showMenu();
                case EXIT -> {
                    System.out.println("Exiting Escape Room Manager. Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}