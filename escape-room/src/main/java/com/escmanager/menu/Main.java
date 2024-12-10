package com.escmanager.menu;

import com.escmanager.exceptions.*;
import com.escmanager.service.EscapeRoomService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        NotificationService notificationService = new NotificationService();
        EscapeRoomService escapeRoomService = new EscapeRoomService(notificationService);
        RoomService roomService = new RoomService();
        HintService hintService = new HintService();
        PropService propService = new PropService();
        UserService userService = new UserService(notificationService);
        TicketService ticketService = new TicketService(notificationService);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("""
                    \n Escape Room Manager. Choose one of the following options:
                        1. Escape Room Management
                        2. Room Management
                        3. Hint Management
                        4. Prop Management
                        5. User Management
                        6. Ticket & Certification Management
                        7. Sales & Reports
                        8. Exit
                        """);
            int option = scanner.nextInt();
            scanner.nextLine();
            if (option < 1 || option > 8) {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }
            switch (option) {
                case 1 -> escapeRoomMenu(escapeRoomService, scanner);
                case 2 -> roomMenu(roomService, scanner);
                case 3 -> hintMenu(hintService, scanner);
                case 4 -> propMenu(propService, scanner);
                case 5 -> userMenu(userService, scanner);
                case 6 -> ticketMenu(ticketService, scanner);
                case 7 -> salesMenu(escapeRoomService);
                case 8 -> {
                    System.out.println("Exiting Escape Room Manager. Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private static void escapeRoomMenu(EscapeRoomService service, Scanner scanner) {
        System.out.println("""
                    Escape Room Management. Choose an option:
                     1. Create Escape Room
                     2. Delete Escape Room
                     3. View Escape Room Inventory
                     4. View Total Cost of Escape Room
                """);
        int option = scanner.nextInt();
        scanner.nextLine();
        try {
            switch (option) {
                case 1 -> {
                    System.out.print("Enter Escape Room name: ");
                    String name = scanner.nextLine();
                    service.createEscapeRoom(name);
                }
                case 2 -> {
                    System.out.print("Enter Escape Room ID to delete: ");
                    int id = scanner.nextInt();
                    service.deleteEscapeRoom(id);
                }
                case 3 -> {
                    System.out.print("Enter Escape Room ID to view inventory: ");
                    int id = scanner.nextInt();
                    service.showInventory(id);
                }
                case 4 -> {
                    System.out.print("Enter Escape Room ID to view total cost: ");
                    int id = scanner.nextInt();
                    service.getTotalCost(id);
                }
                default -> System.out.println("Invalid choice. Returning to main menu.");
            }
        } catch (EscapeRoomNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void roomMenu(RoomService service, Scanner scanner) {
        System.out.println("""
                    Room Management. Choose one of the options:
                        1. Add Room to Escape Room
                        2. Delete Room from Escape Room
                """);
        int option = scanner.nextInt();
        scanner.nextLine();
        try {
            switch (option) {
                case 1 -> {
                    System.out.print("Enter Escape Room ID: ");
                    int escapeRoomId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Room Name: ");
                    String roomName = scanner.nextLine();
                    DifficultyLevel difficultyLevel = null;
                    while (difficultyLevel == null) {
                        System.out.print("Enter Room Difficulty Level (EASY, MEDIUM, HARD, EXTRA_HARD): ");
                        String difficultyInput = scanner.nextLine().toUpperCase();
                        try {
                            difficultyLevel = DifficultyLevel.valueOf(difficultyInput);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid difficulty level. Please enter one of the following: EASY, MEDIUM, HARD, EXTRA_HARD.");
                        }
                    }
                    System.out.print("Enter Room Theme: ");
                    String theme = scanner.nextLine();
                    service.addRoom(escapeRoomId, difficultyLevel, roomName, theme);
                }
                case 2 -> {
                    System.out.print("Enter Room ID to delete: ");
                    int roomId = scanner.nextInt();
                    service.deleteRoom(roomId);
                }
                default -> System.out.println("Invalid choice. Returning to main menu.");
            }
        } catch (EscapeRoomNotFoundException | RoomNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void hintMenu(HintService service, Scanner scanner) {
        System.out.println("""
                    Hint Management. Choose one of the options:
                        1. Add Hint to Room
                        2. Add Multiple Hints to Escape Room
                        3. Delete Hint from Room
                """);
        int choice = scanner.nextInt();
        scanner.nextLine();
        try {
            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Room ID: ");
                    int roomId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Hint Name: ");
                    String hintName = scanner.nextLine();
                    System.out.print("Enter Hint Theme: ");
                    String hintTheme = scanner.nextLine();
                    service.addHint(roomId, hintName, hintTheme);
                }
                case 2 -> {
                    System.out.print("Enter Escape Room ID: ");
                    int escapeRoomId = scanner.nextInt();
                    service.addHints(escapeRoomId);
                }
                case 3 -> {
                    System.out.print("Enter Hint ID to delete: ");
                    int hintId = scanner.nextInt();
                    service.deleteHint(hintId);
                }
                default -> System.out.println("Invalid choice. Returning to main menu.");
            }
        } catch (RoomNotFoundException | HintNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void propMenu(PropService service, Scanner scanner) {
        System.out.println("""
                    Prop Management. Choose one of the options:
                        1. Add Prop to Room
                        2. Add Multiple Props to Escape Room
                        3. Delete Prop from Room
                """);
        int choice = scanner.nextInt();
        scanner.nextLine();
        try {
            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Room ID: ");
                    int roomId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Prop Name: ");
                    String propName = scanner.nextLine();
                    System.out.print("Enter Prop Material Type: ");
                    String materialType = scanner.nextLine();
                    System.out.print("Enter Prop Price: ");
                    double price = scanner.nextDouble();
                    service.addProp(roomId, materialType, propName, price);
                }
                case 2 -> {
                    System.out.print("Enter Escape Room ID: ");
                    int escapeRoomId = scanner.nextInt();
                    scanner.nextLine();
                    List<Prop> props = new ArrayList<>();
                    boolean addingProps = true;
                    while (addingProps) {
                        System.out.print("Enter Prop Name: ");
                        String propName = scanner.nextLine();
                        System.out.print("Enter Prop Material Type: ");
                        String materialType = scanner.nextLine();
                        System.out.print("Enter Prop Price: ");
                        double price = scanner.nextDouble();
                        scanner.nextLine();

                        props.add(new Prop(materialType, propName, price));

                        System.out.print("Do you want to add another prop? (yes/no): ");
                        String response = scanner.nextLine();
                        addingProps = response.equalsIgnoreCase("yes");
                    }
                    try {
                        List<Integer> propIds = service.addProps(escapeRoomId, props);
                        System.out.println("Props added successfully with IDs: " + propIds);
                    } catch (EscapeRoomNotFoundException e) {
                        System.out.println("Error: " + e.getMessage());
                    } catch (InvalidInputException e) {
                        System.out.println("Error: Invalid input - " + e.getMessage());
                    }
                }
                case 3 -> {
                    System.out.print("Enter Prop ID to delete: ");
                    int propId = scanner.nextInt();
                    service.deleteProp(propId);
                }
                default -> System.out.println("Invalid choice. Returning to main menu.");
            }
        } catch (RoomNotFoundException | PropNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void userMenu(UserService service, Scanner scanner) {
        System.out.println("""
                    User Management. Choose one of the options:
                        1. Register User
                        2. Toggle User Notifications
                        3. Notify Users of Updates
                """);
        int choice = scanner.nextInt();
        scanner.nextLine();
        try {
            switch (choice) {
                case 1 -> {
                    System.out.print("Enter User Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter User Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter User Surnames: ");
                    String surnames = scanner.nextLine();
                    service.registerUser(email, name, surnames);
                }
                case 2 -> {
                    System.out.print("Enter User ID to toggle notifications: ");
                    int userId = scanner.nextInt();
                    service.setUserToggleNotifications(userId);
                }
                case 3 -> service.notifyUsers();
                default -> System.out.println("Invalid choice. Returning to main menu.");
            }
        } catch (UserNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void ticketMenu(TicketService service, Scanner scanner) {
        System.out.println("""
                    Ticket & Certification Management. Choose one of the options:
                    1. Issue Ticket
                    2. Generate Certificate for a User
                    3. Give Present to a Certified User
                """);

        int choice = scanner.nextInt();
        scanner.nextLine();

        try {
            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Escape Room ID: ");
                    int escapeRoomId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter User Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter Ticket Date (YYYY-MM-DD): ");
                    String date = scanner.nextLine();
                    System.out.print("Enter Number of Players: ");
                    int numberPlayers = scanner.nextInt();
                    service.generateTicket(escapeRoomId, email, date, numberPlayers);
                    System.out.println("Ticket issued successfully.");
                }
                case 2 -> {
                    System.out.print("Enter User ID: ");
                    int userId = scanner.nextInt();
                    System.out.print("Enter Room ID: ");
                    int roomId = scanner.nextInt();
                    service.generateCertificate(userId, roomId);
                    System.out.println("Certificate generated successfully.");
                }
                case 3 -> {
                    System.out.print("Enter User ID: ");
                    int userId = scanner.nextInt();
                    service.givePresent(userId);
                    System.out.println("Present given successfully.");
                }
                default -> System.out.println("Invalid choice. Returning to main menu.");
            }
        } catch (UserNotFoundException | RoomNotFoundException | EscapeRoomNotFoundException |
                 InvalidInputException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    private static void salesMenu(EscapeRoomService escapeRoomService) {
        System.out.println("Sales & Reports");
        try {
            BigDecimal totalSales = escapeRoomService.getEscapeRoomsSales();
            System.out.println("Total sales from all Escape Rooms: " + totalSales);
        } catch (EscapeRoomNotFoundException e) {
            System.out.println("Error: No Escape Rooms found. Please create at least one Escape Room first.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}