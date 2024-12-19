package com.escmanager.menu;


import com.escmanager.exceptions.element.ElementAlreadyExistsException;
import com.escmanager.exceptions.element.ElementDoesNotExistException;
import com.escmanager.exceptions.room.RoomDoesNotExistException;
import com.escmanager.model.Prop;
import com.escmanager.service.ElementService;

import java.math.BigDecimal;
import java.util.List;

import static com.escmanager.menu.Menu.scanner;


public class PropMenu {

    static ElementService elementService = ElementService.getInstance();

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
                    System.out.println("Enter Prop price: ");
                    BigDecimal propPrice = scanner.nextBigDecimal();
                    scanner.nextLine();
                    try {
                        elementService.addProp(roomId, propMaterialType, propName, propPrice);
                        System.out.println("Prop successfully added!");
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
                        printProps(elementService.getProps(roomId));
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

    public static void printProps (List<Prop> propList) {
        System.out.println("ID\tName\tMaterial\tPrice");
        for (Prop prop : propList) {
            System.out.print(prop.getId());
            System.out.print("\t");
            System.out.print(prop.getName());
            System.out.print("\t");
            System.out.print(prop.getMaterialType());
            System.out.print("\t");
            System.out.print(prop.getPrice());
            System.out.print("\n");
        }
    }
}
