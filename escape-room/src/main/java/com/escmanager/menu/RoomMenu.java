package com.escmanager.menu;

import com.escmanager.service.RoomService;
import com.escmanager.service.EscapeRoomService;
import com.escmanager.enums.DifficultyLevel;
import com.escmanager.exceptions.EscapeRoomNotFoundException;
import com.escmanager.exceptions.RoomNotFoundException;
import static com.escmanager.menu.Main.scanner;
import static com.escmanager.menu.Main.roomService;

public class RoomMenu {
    public static void showMenu() {
        boolean backToMain = false;
        while (!backToMain) {
            System.out.println("""
                    Room Management. Choose one of the options:
                    1. Add Room to Escape Room
                    2. Delete Room from Escape Room
                    3. Back to Main Menu
                    """);
            int option = scanner.nextInt();
            scanner.nextLine();
            try {
                switch (option) {
                    case 1 -> {
                        System.out.print("Enter Escape Room ID: ");
                        int escapeRoomId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter Room Name: ");
                        String roomName = scanner.nextLine();
                        DifficultyLevel difficultyLevel = null;
                        while (difficultyLevel == null) {
                            System.out.print("Enter Room Difficulty Level (EASY, MEDIUM, HARD, EXTRA_HARD): ");
                            String difficultyInput = scanner.nextLine().toUpperCase();
                            try {
                                difficultyLevel = DifficultyLevel.valueOf(difficultyInput);
                            } catch (IllegalArgumentException e) {
                                System.out.println("Invalid difficulty level. Please try again.");
                            }
                        }
                        System.out.print("Enter Room Theme: ");
                        String theme = scanner.nextLine();
                        roomService.addRoom(escapeRoomId, difficultyLevel, roomName, theme);
                    }
                    case 2 -> {
                        System.out.println("Current Rooms:");
                        roomService.ListRooms();
                        System.out.print("Enter Room ID to delete: ");
                        int roomId = scanner.nextInt();
                        roomService.deleteRoom(roomId);
                        System.out.println("Room deleted successfully.");
                    }
                    case 3 -> backToMain = true;
                    default -> System.out.println("Invalid choice. Returning to main menu.");
                }
            } catch (EscapeRoomNotFoundException | RoomNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}