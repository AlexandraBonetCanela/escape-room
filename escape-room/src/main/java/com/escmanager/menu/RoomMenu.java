package com.escmanager.menu;

import com.escmanager.enums.DifficultyLevel;
import com.escmanager.exceptions.DaoException;
import com.escmanager.exceptions.room.RoomAlreadyExistsException;
import com.escmanager.exceptions.room.RoomDoesNotExistException;
import com.escmanager.model.EscapeRoom;
import com.escmanager.model.Room;
import com.escmanager.service.EscapeRoomService;
import com.escmanager.service.RoomService;

import java.util.List;

import static com.escmanager.menu.Main.scanner;

public class RoomMenu {

    static RoomService roomService = RoomService.getInstance();
    static EscapeRoomService escapeRoomService = EscapeRoomService.getInstance();

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
                        System.out.println("Current Escape Rooms:");
                        List<EscapeRoom> escapeRoomList = escapeRoomService.getAllEscapeRooms();
                        EscapeRoomMenu.printEscapeRooms(escapeRoomList);
                        System.out.print("Enter Escape Room ID: ");
                        int escapeRoomId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter Room Name: ");
                        String roomName = scanner.nextLine();
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
                    case 2 -> {
                        System.out.println("Current Escape Rooms:");
                        List<EscapeRoom> escapeRoomList = escapeRoomService.getAllEscapeRooms();
                        EscapeRoomMenu.printEscapeRooms(escapeRoomList);
                        System.out.print("Enter Escape Room ID: ");
                        int escapeRoomId = scanner.nextInt();
                        System.out.println("Current Rooms:");
                        List<Room> roomList = roomService.findAllByEscaperoomId(escapeRoomId);
                        printRooms(roomList);
                        System.out.print("Enter Room ID to delete: ");
                        int roomId = scanner.nextInt();
                        roomService.deleteRoom(roomId);
                        System.out.println("Room deleted successfully.");
                    }
                    case 3 -> backToMain = true;
                    default -> System.out.println("Invalid choice. Returning to main menu.");
                }
            } catch (RoomDoesNotExistException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static void printRooms(List<Room> roomList) {
        System.out.println("ID\tName\tTheme\tDifficulty");
        for (Room room : roomList){
            System.out.print(room.getId());
            System.out.print("\t");
            System.out.print(room.getName());
            System.out.print("\t");
            System.out.print(room.getTheme());
            System.out.print("\t");
            System.out.print(room.getDifficulty());
            System.out.print("\n");


        }
    }
}