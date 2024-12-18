package com.escmanager.menu;


import com.escmanager.exceptions.element.ElementAlreadyExistsException;
import com.escmanager.exceptions.element.ElementDoesNotExistException;
import com.escmanager.exceptions.room.RoomDoesNotExistException;
import com.escmanager.service.ElementService;
import com.escmanager.service.RoomService;

import java.math.BigDecimal;

import static com.escmanager.menu.Main.scanner;


public class PropMenu {

    static ElementService elementService = ElementService.getInstance();
    static RoomService roomService = RoomService.getInstance();
    static RoomMenu roomMenu = new RoomMenu();

    public static void showMenu() {
        boolean backToMain = false;
        final int ADD_PROP = 1;
        final int REMOVE_PROP = 2;
        final int SHOW_PROPS = 3;
        final int DELETE_PROP = 4;
        final int MAIN_MENU = 5;

        while (!backToMain) {
            System.out.println("""
                    Prop Management. Choose one of the options:
                    1. Add Prop to Room
                    2. Remove Prop from Room
                    3. Show Props in a Room
                    4. Delete Prop
                    5. Back to Main Menu
                    """);
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case ADD_PROP -> {
                    MenuUtils.showAllRooms();
                    System.out.print("Enter Room ID: ");
                    int roomId = scanner.nextInt();
                    scanner.nextLine();
                    String propName = MenuUtils.getNonEmptyString("prop Name");
                    String propMaterialType = MenuUtils.getNonEmptyString("type of material");
                    BigDecimal propPrice = scanner.nextBigDecimal();
                    scanner.nextLine();
                    try {
                        elementService.addProp(roomId, propMaterialType, propName, propPrice);
                    } catch (RoomDoesNotExistException | ElementAlreadyExistsException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case REMOVE_PROP -> {
                    MenuUtils.showAllRooms();
                    System.out.print("Enter Room ID: ");
                    int roomId = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        elementService.getHints(roomId);
                    } catch (RoomDoesNotExistException e) {
                        System.out.println(e.getMessage());
                    }

                    System.out.print("Enter Prop ID to delete: ");
                    int propId = scanner.nextInt();
                    try {
                        elementService.removeElementFromRoom(propId);
                    } catch (ElementDoesNotExistException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case SHOW_PROPS -> {
                    MenuUtils.showAllRooms();
                    System.out.print("Enter Room ID: ");
                    int roomId = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        elementService.getProps(roomId);
                    } catch (RoomDoesNotExistException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case DELETE_PROP -> {
                    System.out.print("Enter Prop ID to delete: ");
                    int propId = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        elementService.deleteElement(propId);
                    } catch (ElementDoesNotExistException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case MAIN_MENU -> backToMain = true;
                default -> System.out.println("Invalid choice. Returning to main menu.");
            }
        }
    }
}