package com.escmanager.menu;

import com.escmanager.model.EscapeRoom;
import com.escmanager.model.Room;

import java.util.List;

import static com.escmanager.menu.EscapeRoomMenu.escapeRoomService;
import static com.escmanager.menu.Menu.scanner;
import static com.escmanager.menu.RoomMenu.roomService;

public class MenuUtils {
    public static String getNonEmptyString(String propertyName) {
        String result = null;
        while(result == null || result.trim().isEmpty()) {
            System.out.print("Enter " + propertyName + ": ");
            result = scanner.nextLine();
            if(result == null || result.trim().isEmpty()) {
                System.out.println("Error: " + propertyName + " can't be empty. Please try again.");
            }
        }

        return result;
    }

    public static void showAllRooms(){
        System.out.println("Select a room: ");
        List<Room> roomList = roomService.getAllRooms();
        printRooms(roomList);

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

    public static void showAllEscapeRooms(){
        System.out.println("Current Escape Rooms:");
        List<EscapeRoom> escapeRoomList = escapeRoomService.getAllEscapeRooms();
        for (EscapeRoom escapeRooms : escapeRoomList){
            System.out.println(escapeRooms);
        }
    }
}
