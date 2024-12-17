package com.escmanager.menu;

//import com.escmanager.service.HintService;
import com.escmanager.exceptions.RoomNotFoundException;
import static com.escmanager.menu.Main.scanner;
//import static com.escmanager.menu.Main.hintService;

public class HintMenu {
    public static void showMenu() {
        boolean backToMain = false;
//        while (!backToMain) {
//            System.out.println("""
//                    Hint Management. Choose one of the options:
//                    1. Add Hint to Room
//                    2. Delete Hint from Room
//                    3. Show Hints in a Room
//                    4. Back to Main Menu
//                    """);
//            int option = scanner.nextInt();
//            scanner.nextLine();
//            try {
//                switch (option) {
//                    case 1 -> {
//                        System.out.print("Enter Room ID: ");
//                        int roomId = scanner.nextInt();
//                        scanner.nextLine();
//                        System.out.print("Enter Hint: ");
//                        String hint = scanner.nextLine();
//                        hintService.addHint(roomId, hint);
//                    }
//                    case 2 -> {
//                        System.out.print("Enter Room ID: ");
//                        int roomId = scanner.nextInt();
//                        System.out.print("Enter Hint ID to delete: ");
//                        int hintId = scanner.nextInt();
//                        hintService.deleteHint(roomId, hintId);
//                    }
//                    case 3 -> {
//                        System.out.print("Enter Room ID: ");
//                        int roomId = scanner.nextInt();
//                        hintService.showHints(roomId);
//                    }
//                    case 4 -> backToMain = true;
//                    default -> System.out.println("Invalid choice. Returning to main menu.");
//                }
//            } catch (RoomNotFoundException e) {
//                System.out.println("Error: " + e.getMessage());
//            }
//        }
    }
}