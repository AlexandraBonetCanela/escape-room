package com.escmanager.menu;

import
import com.escmanager.service.PropService;
import com.escmanager.exceptions.RoomNotFoundException;
import static com.escmanager.menu.Main.scanner;
import static com.escmanager.menu.Main.propService;

public class PropMenu {
    public static void showMenu() {
        boolean backToMain = false;
        while (!backToMain) {
            System.out.println("""
                    Prop Management. Choose one of the options:
                    1. Add Prop to Room
                    2. Delete Prop from Room
                    3. Show Props in a Room
                    4. Back to Main Menu
                    """);
            int option = scanner.nextInt();
            scanner.nextLine();
            try {
                switch (option) {
                    case 1 -> {
                        System.out.print("Enter Room ID: ");
                        int roomId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter Prop Name: ");
                        String propName = scanner.nextLine();
                        element.addProp(roomId, propName);
                    }
                    case 2 -> {
                        System.out.print("Enter Room ID: ");
                        int roomId = scanner.nextInt();
                        System.out.print("Enter Prop ID to delete: ");
                        int propId = scanner.nextInt();
                        elementService.deleteProp(roomId, propId);
                    }
                    case 3 -> {
                        System.out.print("Enter Room ID: ");
                        int roomId = scanner.nextInt();
                        elementService.showProps(roomId);
                    }
                    case 4 -> backToMain = true;
                    default -> System.out.println("Invalid choice. Returning to main menu.");
                }
            } catch (RoomNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}