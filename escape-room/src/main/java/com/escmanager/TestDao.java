package com.escmanager;

import com.escmanager.dao.EscapeRoomDAO;
import com.escmanager.dao.implementation.EscapeRoomImpl;
import com.escmanager.model.EscapeRoom;

public class TestDao {
    public static void main(String[] args) {

        EscapeRoomDAO escapeRoomDAO = new EscapeRoomImpl();

//        EscapeRoom newEscapeRoom = new EscapeRoom(0,"IT Academy", new BigDecimal(112) , Status.ACTIVE);
//        escapeRoomDAO.create(newEscapeRoom);

//        List<EscapeRoom> escapeRooms = escapeRoomDAO.getAll();
//        for (EscapeRoom escapeRoom : escapeRooms) {
//            System.out.println(escapeRoom);
//        }

        EscapeRoom escapeRoom = (EscapeRoom) escapeRoomDAO.getById(2);
        if (escapeRoom != null){
        System.out.println(escapeRoom);
        }
//        EscapeRoom escapeRoom = (EscapeRoom) escapeRoomDAO.findByName("Mystery Mansion");
//        System.out.println(escapeRoom);

//        EscapeRoom escapeRoom = (EscapeRoom) escapeRoomDAO.getById(1);
//        System.out.println(escapeRoom);
//        escapeRoom.setName("Oblivion");
//        escapeRoom.setPrice(new BigDecimal(100));
//        escapeRoom.setStatus(Status.INACTIVE);
//        escapeRoomDAO.update(escapeRoom);
//        System.out.println(escapeRoom);
    }
}
