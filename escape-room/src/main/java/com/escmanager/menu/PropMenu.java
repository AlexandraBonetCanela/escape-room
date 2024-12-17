package com.escmanager.menu;


import com.escmanager.model.Room;
import com.escmanager.service.ElementService;
import com.escmanager.service.RoomService;

import java.util.List;

import static com.escmanager.menu.Main.scanner;


public class PropMenu {

    static ElementService elementService = ElementService.getInstance();
    static RoomService roomService = RoomService.getInstance();
    static RoomMenu roomMenu = new RoomMenu();

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
            switch (option) {
                case 1 -> {
                    System.out.println("Select a room: ");
                    List<Room> roomList = roomService.getAllRooms();
                    roomMenu.printRooms(roomList);
                    System.out.print("Enter Room ID: ");
                    int roomId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Prop Name: ");
                    String propName = scanner.nextLine();
                    //TODO: Missing asking for materialType, price...
                    //elementService.addProp(roomId, propName);
                }
                case 2 -> {
                    System.out.print("Enter Room ID: ");
                    int roomId = scanner.nextInt();
                    System.out.print("Enter Prop ID to delete: ");
                    int propId = scanner.nextInt();
                    //TODO: Fix deleteProp
                    //elementService.deleteProp(roomId, propId);
                }
                case 3 -> {
                    System.out.print("Enter Room ID: ");
                    int roomId = scanner.nextInt();
                    //TODO: Fix this
                    //elementService.showProps(roomId);
                }
                case 4 -> backToMain = true;
                default -> System.out.println("Invalid choice. Returning to main menu.");
            }
        }
    }
}