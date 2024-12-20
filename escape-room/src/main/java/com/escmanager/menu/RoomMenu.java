package com.escmanager.menu;

import com.escmanager.enums.DifficultyLevel;
import com.escmanager.exceptions.DaoException;
import com.escmanager.exceptions.room.RoomAlreadyExistsException;
import com.escmanager.exceptions.room.RoomDoesNotExistException;
import com.escmanager.model.Room;
import com.escmanager.service.RoomService;

import java.util.List;

import static com.escmanager.menu.Menu.scanner;

public class RoomMenu {

    static RoomService roomService = RoomService.getInstance();

    public static void showMenu() {
        boolean backToMain = false;
        final int ADD_ROOM_MENU = 1;
        final int DELETE_ROOM_MENU = 2;
        final int MAIN_MENU = 3;

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
                    case ADD_ROOM_MENU -> {
                        MenuUtils.showAllEscapeRooms();
                        System.out.print("Enter Escape Room ID: ");
                        int escapeRoomId = scanner.nextInt();
                        scanner.nextLine();

                        String roomName = MenuUtils.getNonEmptyString("Name");

                        DifficultyLevel difficultyLevel = null;
                        while (difficultyLevel == null) {
                            System.out.print("""
                                    Enter Room Difficulty Level:
                                    1. EASY,
                                    2. MEDIUM,
                                    3. DIFFICULT
                                    """);
                            int difficultyInput = scanner.nextInt();
                            scanner.nextLine();
                            difficultyLevel = switch (difficultyInput) {
                                case 1 -> DifficultyLevel.EASY;
                                case 2 -> DifficultyLevel.MEDIUM;
                                case 3 -> DifficultyLevel.DIFFICULT;
                                default -> {
                                    System.out.println("Invalid input. Try again.");
                                    yield null;
                                }
                            };
                        }
                        System.out.print("Enter Room Theme: ");
                        String theme = scanner.nextLine();

                        try {
                            roomService.addRoom(escapeRoomId, difficultyLevel, roomName, theme);
                        } catch (RoomAlreadyExistsException | DaoException e) {
                            System.out.println(e.getMessage());
                        }
                        System.out.print("Room successfully added to Escape Room:  " + escapeRoomId);
                    }
                    case DELETE_ROOM_MENU -> {
                        MenuUtils.showAllEscapeRooms();
                        System.out.print("Enter Escape Room ID: ");
                        int escapeRoomId = scanner.nextInt();
                        System.out.println("Current Rooms:");
                        List<Room> roomList = roomService.findAllByEscaperoomId(escapeRoomId);
                        MenuUtils.printRooms(roomList);
                        System.out.print("Enter Room ID to delete: ");
                        int roomId = scanner.nextInt();
                        roomService.deleteRoom(roomId);
                        System.out.println("Room deleted successfully.");
                    }
                    case MAIN_MENU -> backToMain = true;
                    default -> System.out.println("Invalid choice. Returning to main menu.");
                }
            } catch (RoomDoesNotExistException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}