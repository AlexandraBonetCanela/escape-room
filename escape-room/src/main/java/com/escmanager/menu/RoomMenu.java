package com.escmanager.menu;

import com.escmanager.enums.DifficultyLevel;
import com.escmanager.exceptions.EscapeRoomNotFoundException;
import com.escmanager.exceptions.RoomNotFoundException;
import static com.escmanager.menu.Main.scanner;


public class RoomMenu {
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

        }
    }
}