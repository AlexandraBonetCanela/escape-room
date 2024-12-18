package com.escmanager.menu;

import com.escmanager.exceptions.UserAlreadyExistException;
import com.escmanager.exceptions.UserAlreadyRegisteredException;
import com.escmanager.exceptions.UserDoesNotExistException;
import com.escmanager.exceptions.UserNotFoundException;
import com.escmanager.exceptions.ticket.TicketAlreadyExistException;

import java.util.Scanner;

public class Main {

    public static final  Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws TicketAlreadyExistException, UserNotFoundException, UserAlreadyExistException, UserDoesNotExistException, UserAlreadyRegisteredException {

        boolean running = true;

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