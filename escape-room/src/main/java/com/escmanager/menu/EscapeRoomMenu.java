package com.escmanager.menu;

import com.escmanager.exceptions.escaperoom.EscapeRoomAlreadyExistException;
import com.escmanager.exceptions.escaperoom.EscapeRoomDoesNotExistException;
import com.escmanager.exceptions.room.RoomDoesNotExistException;
import com.escmanager.model.*;
import com.escmanager.service.ElementService;
import com.escmanager.service.EscapeRoomService;
import com.escmanager.service.RoomService;
import static com.escmanager.menu.MenuUtils.getNonEmptyString;

import java.math.BigDecimal;
import java.util.List;

import static com.escmanager.menu.Menu.scanner;

public class EscapeRoomMenu {

    static EscapeRoomService escapeRoomService = EscapeRoomService.getInstance();



    public static void showMenu() {

        final int CREATE_ESCAPEROOM = 1;
        final int DELETE_ESCAPEROOM = 2;
        final int MAIN_MENU = 3;
        boolean backToMain = false;

        while (!backToMain) {
            System.out.println("""
                    Escape Room Management. Choose an option:
                    1. Create Escape Room
                    2. Delete Escape Room
                    3. Back to Main Menu
                    """);
            int option = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (option) {
                    case CREATE_ESCAPEROOM -> {
                        String name = getNonEmptyString("EscapeRoom name");
                        System.out.print("Enter Escape room price: ");
                        BigDecimal price = scanner.nextBigDecimal();
                        escapeRoomService.addEscapeRoom(name, price);
                    }
                    case DELETE_ESCAPEROOM -> {
                        MenuUtils.showAllEscapeRooms();
                        System.out.print("Enter Escape Room ID to delete: ");
                        int id = scanner.nextInt();
                        escapeRoomService.deleteEscapeRoom(id);
                        System.out.println("Room deleted successfully.");
                    }
                    case MAIN_MENU -> backToMain = true;

                    default -> System.out.println("Invalid choice. Returning to main menu.");
                }
            } catch (EscapeRoomDoesNotExistException | EscapeRoomAlreadyExistException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
