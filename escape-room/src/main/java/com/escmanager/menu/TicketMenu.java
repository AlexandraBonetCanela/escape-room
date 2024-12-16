package com.escmanager.menu;

import com.escmanager.service.TicketService;

import static com.escmanager.menu.Main.*;

public class TicketMenu {
    public static void showMenu() {
        boolean backToMain = false;
        while (!backToMain) {
            System.out.println("""
                    Ticket & Certification Management. Choose one of the options:
                    1. Issue Ticket
                    2. Validate Ticket
                    3. Back to Main Menu
                    """);
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> {
                    System.out.print("Enter User ID: ");
                    int userId = scanner.nextInt();
                    escapeRoomService.getAllEscapeRooms();
                    System.out.print("Enter Escaperoom ID: ");
                    int userId = scanner.nextInt();
                    ticketService.createTicket(userId);
                }
                case 2 -> backToMain = true;
                default -> System.out.println("Invalid choice. Returning to main menu.");
            }
        }
    }
}