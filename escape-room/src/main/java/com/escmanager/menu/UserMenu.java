package com.escmanager.menu;


import com.escmanager.exceptions.user.UserAlreadyExistException;
import com.escmanager.exceptions.user.UserAlreadyRegisteredException;
import com.escmanager.exceptions.user.UserDoesNotExistException;
import com.escmanager.model.User;
import com.escmanager.service.UserService;

import java.util.List;

import static com.escmanager.menu.Main.scanner;
import static com.escmanager.menu.MenuUtils.getNonEmptyString;

public class UserMenu {

    static UserService userService = UserService.getInstance();

    public static void showMenu() {

        boolean backToMain = false;
        final int ADD_USER = 1;
        final int REGISTER_USER = 2;
        final int UPDATE_USER = 3;
        final int LIST_ALL_USERS = 4;
        final int MAIN_MENU = 5;

        while (!backToMain) {
            System.out.println("""
                    \nUSER MENU
                        1. Add User
                        2. Register user
                        3. Update user
                        4. List All Users
                        5. Back to Main Menu
                    """);
            System.out.print("Choose one of the following options: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case ADD_USER -> {
                    System.out.println("\nADD USER");
                    System.out.print("Enter user email: ");
                    String email = scanner.nextLine();
                    try {
                        userService.addUser(email);
                    } catch (UserAlreadyExistException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println("The user has been added to the database");
                }
                case REGISTER_USER -> {
                    System.out.println("\nREGISTER USER");
                    System.out.print("Enter user email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter user name: ");
                    String name = scanner.nextLine();
                    try {
                        userService.registerUser(email, name);
                    } catch (UserAlreadyRegisteredException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println("The user has been registered");
                }
                case UPDATE_USER -> {
                    System.out.println("\nUPDATE USER");
                    String email = getNonEmptyString("actual user email");
                    User user = userService.getUser(email);
                    String newEmail = getNonEmptyString("new user email");
                    user.setEmail(newEmail);
                    String newName = getNonEmptyString("new user name");
                    user.setName(newName);
                    if (user.isRegistered()){
                        System.out.print("The user is already registered. Do you want to unregister him? (true/false): ");
                        boolean registered = scanner.nextBoolean();
                        user.setRegistered(!registered);
                    } else {
                        System.out.print("The user is not registered, do you want to register him? (true/false): ");
                        boolean registered = scanner.nextBoolean();
                        user.setRegistered(registered);
                    }
                    if (user.isNotifications()){
                        System.out.print("The user has the notifications enabled. Would like to disabled them? (true/false): ");
                        boolean notifications = scanner.nextBoolean();
                        user.setNotifications(notifications);
                    } else {
                        System.out.print("The user has the notifications disabled. Would like to enabled them? (true/false): ");
                        boolean notifications = scanner.nextBoolean();
                        user.setNotifications(notifications);
                    }
                    try {
                        userService.updateUserObject(email, user);
                    } catch (UserDoesNotExistException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println("The user with the email " + email + ", has been updated.");
                }
                case LIST_ALL_USERS -> printUsers(userService.getAllUsers());
                case MAIN_MENU -> backToMain = true;
                default -> System.out.println("Invalid choice. Returning to main menu.");
            }
        }
    }
    private static void printUsers (List<User> userList){
        for (User user : userList){
            System.out.println(user);
        }
    }
}