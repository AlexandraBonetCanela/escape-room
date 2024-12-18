package com.escmanager.menu;

import com.escmanager.exceptions.UserAlreadyExistException;
import com.escmanager.exceptions.UserAlreadyRegisteredException;
import com.escmanager.exceptions.UserDoesNotExistException;
import com.escmanager.model.User;
import com.escmanager.service.UserService;

import static com.escmanager.menu.Main.scanner;

public class UserMenu {

    static UserService userService = UserService.getInstance();

    public static void showMenu() throws UserAlreadyExistException, UserAlreadyRegisteredException, UserDoesNotExistException {
        boolean backToMain = false;
        while (!backToMain) {
            System.out.println("""
                    \nUSER MENU
                        1. Add User
                        2. Register user
                        3. Update user
                        3. List All Users
                        4. Back to Main Menu
                    """);
            System.out.print("Choose one of the following options: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> {
                    System.out.println("\nADD USER");
                    System.out.print("Enter user email: ");
                    String email = scanner.nextLine();
                    userService.addUser(email);
                    System.out.println("The user has been added to the database");
                }
                case 2 -> {
                    System.out.println("\nREGISTER USER");
                    System.out.print("Enter user email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter user name: ");
                    String name = scanner.nextLine();
                    userService.registerUser(email, name);
                    System.out.println("The user has been registered");
                }
                case 3 -> {
                    System.out.println("\nUPDATE USER");
                    System.out.print("Enter actual user email: ");
                    String email = scanner.nextLine();
                    User user = userService.getUser(email);
                    System.out.print("Enter new user email: ");
                    String newEmail = scanner.nextLine();
                    user.setEmail(newEmail);
                    System.out.print("Enter new user name: ");
                    String newName = scanner.nextLine();
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
                    userService.updateUserObject(email, user);
                    System.out.println("The user with the email " + email + ", has been updated.");
                }
                case 4 -> userService.getAllUsers();
                case 5 -> backToMain = true;
                default -> System.out.println("Invalid choice. Returning to main menu.");
            }
        }
    }
}