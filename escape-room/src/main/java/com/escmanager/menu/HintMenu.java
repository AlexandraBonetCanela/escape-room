package com.escmanager.menu;


import com.escmanager.service.ElementService;

import static com.escmanager.menu.Main.scanner;

public class HintMenu {

    static ElementService elementService = ElementService.getInstance();

    public static void showMenu() {
        boolean backToMain = false;
        final int ADD_HINT = 1;
        final int DELETE_HINT = 2;
        final int SHOW_HINTS = 3;
        final int MAIN_MENU = 4;

        while (!backToMain) {
            System.out.println("""
                    Hint Management. Choose one of the options:
                    1. Add Hint to Room
                    2. Delete Hint from Room
                    3. Show Hints in a Room
                    4. Back to Main Menu
                    """);
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case ADD_HINT -> {
                    System.out.print("Enter Room ID: ");
                    int roomId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Hint: ");
                    String hint = scanner.nextLine();
                    // TODO: Fix
//                        hintService.addHint(roomId, hint);
                }
                case DELETE_HINT -> {
                    System.out.print("Enter Room ID: ");
                    int roomId = scanner.nextInt();
                    System.out.print("Enter Hint ID to delete: ");
                    int hintId = scanner.nextInt();
                    // TODO: Fix
//                        hintService.deleteHint(roomId, hintId);
                }
                case SHOW_HINTS -> {
                    System.out.print("Enter Room ID: ");
                    int roomId = scanner.nextInt();
                    // TODO: Fix
//                        hintService.showHints(roomId);
                }
                case MAIN_MENU -> backToMain = true;
                default -> System.out.println("Invalid choice. Returning to main menu.");
            }
        }
    }
}