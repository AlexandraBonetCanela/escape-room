package com.escmanager.menu;


import com.escmanager.exceptions.element.ElementAlreadyExistsException;
import com.escmanager.exceptions.element.ElementDoesNotExistException;
import com.escmanager.exceptions.room.RoomDoesNotExistException;
import com.escmanager.model.Hint;
import com.escmanager.service.ElementService;

import java.math.BigDecimal;
import java.util.List;

import static com.escmanager.menu.Menu.scanner;

public class HintMenu {

    static ElementService elementService = ElementService.getInstance();

    public static void showMenu() {
        boolean backToMain = false;
        final int ADD_HINT = 1;
        final int REMOVE_HINT = 2;
        final int SHOW_HINTS = 3;
        final int DELETE_HINT = 4;
        final int MAIN_MENU = 5;

        while (!backToMain) {
            System.out.println("""
                    Hint Management. Choose one of the options:
                    1. Add Hint to Room
                    2. Remove Hint from Room
                    3. Show Hints in a Room
                    4. Delete Hint (archive)
                    4. Back to Main Menu
                    """);
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case ADD_HINT -> {
                    System.out.print("Enter Room ID: ");
                    int roomId = scanner.nextInt();
                    scanner.nextLine();
                    String hintName = MenuUtils.getNonEmptyString("Hint name");
                    System.out.println("Enter Hint price: ");
                    BigDecimal hintPrice = scanner.nextBigDecimal();
                    scanner.nextLine();
                    String hintTheme = MenuUtils.getNonEmptyString("theme");
                    try {
                        elementService.addHint(roomId, hintTheme, hintName, hintPrice);
                    } catch (RoomDoesNotExistException | ElementAlreadyExistsException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case REMOVE_HINT -> {
                    System.out.print("Enter Room ID: ");
                    int roomId = scanner.nextInt();
                    try {
                        elementService.getHints(roomId);
                    } catch (RoomDoesNotExistException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.print("Enter Hint ID to remove: ");
                    int hintId = scanner.nextInt();
                    try {
                        elementService.removeElementFromRoom(hintId);
                    } catch (ElementDoesNotExistException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case SHOW_HINTS -> {
                    System.out.print("Enter Room ID: ");
                    int roomId = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        printHints(elementService.getHints(roomId));
                    } catch (RoomDoesNotExistException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case DELETE_HINT -> {
                    System.out.print("Enter Hint ID to delete: ");
                    int hintId = scanner.nextInt();
                    try {
                        elementService.deleteElement(hintId);
                    } catch (ElementDoesNotExistException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case MAIN_MENU -> backToMain = true;
                default -> System.out.println("Invalid choice. Returning to main menu.");
            }
        }
    }
    public static void printHints(List<Hint> hintList) {
        System.out.println("ID\tName\tTheme\tPrice");
        for (Hint hint : hintList){
            System.out.print(hint.getId());
            System.out.print("\t");
            System.out.print(hint.getName());
            System.out.print("\t");
            System.out.print(hint.getTheme());
            System.out.print("\t");
            System.out.print(hint.getPrice());
            System.out.print("\n");
        }
    }
}