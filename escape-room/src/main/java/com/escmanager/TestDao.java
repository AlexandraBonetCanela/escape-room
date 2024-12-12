package com.escmanager;

import com.escmanager.dao.RoomDAO;
import com.escmanager.dao.implementation.RoomImpl;
import com.escmanager.enums.DifficultyLevel;
import com.escmanager.exceptions.DaoException;
import com.escmanager.exceptions.room.RoomAlreadyExistsException;
import com.escmanager.exceptions.room.RoomDoesNotExistException;
import com.escmanager.model.Room;
import com.escmanager.service.RoomService;

public class TestDao {
    public static void main(String[] args) {

    //    EscapeRoomDAO escapeRoomDAO = new EscapeRoomImpl();

    //    EscapeRoom newEscapeRoom = new EscapeRoom(0,"IT Academy", new BigDecimal(112) , Status.ACTIVE);
    //    escapeRoomDAO.create(newEscapeRoom);

        RoomDAO roomDAO = new RoomImpl();

  //      Room room = roomDAO.findByNameAndEscaperoomId("Library of Secrets", 2);
  //      System.out.println(room);

           RoomService roomService = new RoomService();

   //         try{
    //            Room room = roomService.addRoom(1, DifficultyLevel.EASY, "Library of Curses", "themename");

   //         } catch (RoomAlreadyExistsException | DaoException e){
    //            System.out.println(e.getMessage());
    //        }

        try {
            boolean result = roomService.deleteRoom(4);
            System.out.println(result);
        } catch (RoomDoesNotExistException | DaoException e) {
            System.out.println(e.getMessage());
        }


//        List<EscapeRoom> escapeRooms = escapeRoomDAO.getAll();
//        for (EscapeRoom escapeRoom : escapeRooms) {
//            System.out.println(escapeRoom);
//        }

//        EscapeRoom escapeRoom = (EscapeRoom) escapeRoomDAO.getById(2);
//        System.out.println(escapeRoom);

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
