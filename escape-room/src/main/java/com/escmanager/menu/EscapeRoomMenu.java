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

import static com.escmanager.menu.Main.scanner;

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
                        System.out.println("Current Escape Rooms:");
                        printEscapeRooms(escapeRoomService.getAllEscapeRooms());
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

    private static void printInventory(int escaperoomId) {
        System.out.println("\n");
        System.out.println("Escape room info:");
        EscapeRoom escapeRoom = escapeRoomService.getById(escaperoomId);
        System.out.println("\t- ID:\t" + escapeRoom.getId());
        System.out.println("\t- Name:\t" + escapeRoom.getName());
        System.out.println("\t- Price:\t" + escapeRoom.getPrice());
        System.out.println();

        List<Room> rooms = RoomService.getInstance().findAllByEscaperoomId(escaperoomId);
        for(Room room: rooms) {
            System.out.println("Room info:");
            System.out.println("\t- ID:\t" + room.getId());
            System.out.println("\t- Name:\t" + room.getName());
            System.out.println("\t- Difficulty:\t" + room.getDifficulty());
            System.out.println("\t- Theme:\t" + room.getTheme());
            System.out.println("\t- Total elements:\t" + room.getElementQuantity());

            System.out.println("\t- Hints:");
            try {
                List<Hint> hints = ElementService.getInstance().getHints(room.getId());
                if(hints.isEmpty()) {
                    System.out.println("\t\t(No hints)");
                }
                else {
                    System.out.println("\t\tID\tPrice\tName\tTheme");
                }
                for(Hint hint: hints) {
                    System.out.print("\t\t");
                    System.out.print(hint.getId());
                    System.out.print("\t");
                    System.out.print(hint.getPrice());
                    System.out.print("\t");
                    System.out.print(hint.getName());
                    System.out.print("\t");
                    System.out.print(hint.getTheme());
                    System.out.println();
                }

                List<Prop> props = ElementService.getInstance().getProps(room.getId());
                System.out.println("\t- Props:");

                if(props.isEmpty()) {
                    System.out.println("\t\t(No props)");
                }
                else {
                    System.out.println("\t\tID\tPrice\tName\tMaterial type");
                }
                for(Prop prop: props) {
                    System.out.print("\t\t");
                    System.out.print(prop.getId());
                    System.out.print("\t");
                    System.out.print(prop.getPrice());
                    System.out.print("\t");
                    System.out.print(prop.getName());
                    System.out.print("\t");
                    System.out.print(prop.getMaterialType());
                    System.out.println();
                }

            } catch (RoomDoesNotExistException e) {
                System.out.println("The room does not exist!");
            }
        }

        System.out.println("Escape room info:");
        System.out.println("Escape room info:");
        System.out.println("Escape room info:");
    }

    public static void printEscapeRooms(List<EscapeRoom> escapeRoomList) {
        for (EscapeRoom escapeRooms : escapeRoomList){
            System.out.println(escapeRooms);
        }
    }
}
