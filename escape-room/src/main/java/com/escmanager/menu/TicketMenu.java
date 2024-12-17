package com.escmanager.menu;

import com.escmanager.exceptions.UserNotFoundException;
import com.escmanager.exceptions.ticket.TicketAlreadyExistException;
import com.escmanager.model.Certificate;
import com.escmanager.model.Ticket;
import com.escmanager.service.CertificateService;
import com.escmanager.service.EscapeRoomService;
import com.escmanager.service.TicketService;

import java.math.BigDecimal;

import static com.escmanager.menu.Main.*;

public class TicketMenu {

    static EscapeRoomService escapeRoomService = EscapeRoomService.getInstance();
    static TicketService ticketService = TicketService.getInstance();
    static CertificateService certificateService = CertificateService.getInstance();

    public static void showMenu() throws TicketAlreadyExistException, UserNotFoundException {
        boolean backToMain = false;
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
                case 1 -> {
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
                    ticketService.createTicket(user_id, escape_room_id, unit_price, quantity, total_price);
                    System.out.println("The ticket has been created");
                }
                case 2 -> {
                    System.out.println("\nCREATING TICKET");
                    System.out.print("Enter escaperoom id: ");
                    int escape_room_id = scanner.nextInt();
                    Certificate certificate = certificateService.getCertificateById(escape_room_id);
                    System.out.println(certificate.getName() + "\n" + certificate.getDescription());
                }

                case 3 -> backToMain = true;
                default -> System.out.println("Invalid choice. Returning to main menu.");
            }
        }
    }
}