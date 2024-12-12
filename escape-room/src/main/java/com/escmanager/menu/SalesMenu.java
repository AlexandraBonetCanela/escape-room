package com.escmanager.menu;
import com.escmanager.service.EscapeRoomService;
import com.escmanager.exception.EscapeRoomNotFoundException;
import java.math.BigDecimal;

public class SalesMenu {
    public static void showMenu() {
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