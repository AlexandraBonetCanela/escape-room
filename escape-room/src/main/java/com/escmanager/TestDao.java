package com.escmanager;

import com.escmanager.dao.RoomDAO;
import com.escmanager.dao.RoomImpl;
import com.escmanager.model.Room;

public class TestDao {
    public static void main(String[] args) {

        RoomDAO RoomDAO = new RoomImpl();

//        EscapeRoom newEscapeRoom = new EscapeRoom(0,"IT Academy", 100,"ACTIVE");
//        escapeRoomDAO.create(newEscapeRoom);

//        List<EscapeRoom> escapeRooms = escapeRoomDAO.getAll();
//        for (EscapeRoom escapeRoom : escapeRooms) {
//            System.out.println(escapeRoom);
//        }

//        EscapeRoom escapeRoom = (EscapeRoom) escapeRoomDAO.getById(2);
//        System.out.println(escapeRoom);

        Room room = (Room) RoomDAO.getById(1);
        System.out.println(room);
        room.setName("Baño");
        room.setTheme("Años 80");
        room.setDifficulty("Hard");
        room.setElement_quantity(6);
        room.setEscape_room_id(1);
        room.setName("Active");
        RoomDAO.update(room);
        System.out.println(room);
    }
}
