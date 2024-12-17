package com.escmanager.menu;

import com.escmanager.exceptions.escaperoom.EscapeRoomAlreadyExistException;
import com.escmanager.exceptions.escaperoom.EscapeRoomDoesNotExistException;
import com.escmanager.service.EscapeRoomService;
import java.math.BigDecimal;
import static com.escmanager.menu.Main.scanner;
import static com.escmanager.menu.Main.escapeRoomService;

public class EscapeRoomMenu {
    public static void showMenu() {
        boolean backToMain = false;
        while (!backToMain) {
            System.out.println("""
                    Escape Room Management. Choose an option:
                    1. Create Escape Room
                    2. Delete Escape Room
                    3. View Escape Room Inventory
                    4. View Total Cost of Escape Room
                    5. Back to Main Menu
                    """);
            int option = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (option) {
                    case 1 -> {
                        System.out.print("Enter Escape Room name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter Escape room price");
                        BigDecimal price = scanner.nextBigDecimal();
                        escapeRoomService.addEscapeRoom(name, price);
                    }
                    case 2 -> {
                        System.out.println("Current Escape Rooms:");
                        EscapeRoomService.getAllEscapeRooms();
                        System.out.print("Enter Escape Room ID to delete: ");
                        int id = scanner.nextInt();
                        escapeRoomService.deleteEscapeRoom(id);
                        System.out.println("Room deleted successfully.");
                    }
                    case 3 -> {
                        System.out.print("Enter Escape Room ID to view inventory: ");
                        int id = scanner.nextInt();
                        escapeRoomService.showInventory(id);
                    }
                    case 4 -> {
                        System.out.print("Enter Escape Room ID to view total cost: ");
                        int id = scanner.nextInt();
                        escapeRoomService.getTotalCost(id);
                    }
                    case 5 -> backToMain = true;

                    default -> System.out.println("Invalid choice. Returning to main menu.");
                }
            } catch (EscapeRoomDoesNotExistException | EscapeRoomAlreadyExistException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
