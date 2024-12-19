package com.escmanager.menu;

import com.escmanager.exceptions.user.UserDoesNotExistException;
import com.escmanager.model.Certificate;
import com.escmanager.service.CertificateService;
import com.escmanager.service.TicketService;

import java.math.BigDecimal;

import static com.escmanager.menu.Menu.*;

public class TicketMenu {

    static TicketService ticketService = TicketService.getInstance();
    static CertificateService certificateService = CertificateService.getInstance();

    public static void showMenu() {

        boolean backToMain = false;
        final int CREATE_TICKET = 1;
        final int GIVE_CERTIFICATE = 2;
        final int MAIN_MENU = 3;

        while (!backToMain) {
            System.out.println(""" 
                    \nTICKET MENU
                        1. Create Ticket
                        2. Give Certificate
                        3. Back to Main Menu
                    """);
            System.out.print("Choose one of the options: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case CREATE_TICKET -> {
                    System.out.println("\nCREATING TICKET");
                    System.out.print("Enter user ID: ");
                    int user_id = scanner.nextInt();
                    System.out.print("Enter escapeRoom ID: ");
                    int escape_room_id = scanner.nextInt();
                    System.out.print("Enter unit price: ");
                    BigDecimal unit_price = scanner.nextBigDecimal();
                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt();
                    BigDecimal quantityBigDecimal = new BigDecimal(quantity);
                    BigDecimal total_price = unit_price.multiply(quantityBigDecimal);
                    try {
                        ticketService.createTicket(user_id, escape_room_id, unit_price, quantity, total_price);
                    } catch (UserDoesNotExistException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println("The ticket has been created");
                }
                case GIVE_CERTIFICATE -> {
                    System.out.println("\nCREATING TICKET");
                    System.out.print("Enter escaperoom id: ");
                    int escape_room_id = scanner.nextInt();
                    Certificate certificate = certificateService.getCertificateById(escape_room_id);
                    System.out.println(certificate.getName() + ": " + certificate.getDescription());
                }
                case MAIN_MENU -> backToMain = true;
                default -> System.out.println("Invalid choice. Returning to main menu.");
            }
        }
    }
}