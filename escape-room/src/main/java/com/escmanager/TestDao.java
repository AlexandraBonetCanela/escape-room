package com.escmanager;

import com.escmanager.dao.EscapeRoomDAO;
import com.escmanager.dao.EscapeRoomImpl;
import com.escmanager.model.EscapeRoom;

import java.util.List;

public class TestDao {
    public static void main(String[] args) {
        EscapeRoomDAO escapeRoomDAO = new EscapeRoomImpl();

        EscapeRoom newEscapeRoom = new EscapeRoom(0,"Metro", "ACTIVE");
        escapeRoomDAO.save(newEscapeRoom);

//        List<EscapeRoom> escapeRooms = escapeRoomDAO.getAll();
//        for (EscapeRoom escapeRoom : escapeRooms) {
//            System.out.println(escapeRoom);
//        }
//
//        EscapeRoom escapeRoom = escapeRoomDAO.getById(3);
//        System.out.println(escapeRoom);
//
//        escapeRoom.setName("Metro 2048");
//        escapeRoomDAO.update(escapeRoom);
//
//        escapeRoomDAO.delete(3);
    }
}
