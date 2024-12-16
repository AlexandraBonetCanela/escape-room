package com.escmanager;

import com.escmanager.dao.ElementDAO;
import com.escmanager.dao.RoomDAO;
import com.escmanager.dao.implementation.ElementImpl;
import com.escmanager.dao.implementation.RoomImpl;
import com.escmanager.enums.DifficultyLevel;
import com.escmanager.enums.ElementType;
import com.escmanager.enums.Status;
import com.escmanager.exceptions.DaoException;
import com.escmanager.exceptions.element.ElementAlreadyExistsException;
import com.escmanager.exceptions.element.ElementDoesNotExistException;
import com.escmanager.exceptions.room.RoomAlreadyExistsException;
import com.escmanager.exceptions.room.RoomDoesNotExistException;
import com.escmanager.model.Element;
import com.escmanager.model.Hint;
import com.escmanager.model.Prop;
import com.escmanager.model.Room;
import com.escmanager.service.ElementService;
import com.escmanager.service.RoomService;

import java.math.BigDecimal;
import java.util.List;

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

    //    try {
//            boolean result = roomService.deleteRoom(4);
//            System.out.println(result);
//        } catch (RoomDoesNotExistException | DaoException e) {
//            System.out.println(e.getMessage());
//        }

        ElementDAO elementDAO = new ElementImpl();
//        Prop prop = new Prop(4, "new chair2", ElementType.PROP, BigDecimal.valueOf(34.56), Status.ACTIVE, "wood");
//        try {
//            Element result = elementDAO.create(prop);
//            System.out.println(result);
//
//        } catch (DaoException e){
//            System.out.println(e.getMessage());
//
//        }
//
//        Hint hint = new Hint(4, "new chair hint", ElementType.HINT, BigDecimal.valueOf(34.56), Status.ACTIVE, "wood theme");
//        try {
//            Element result = elementDAO.create(hint);
//            System.out.println(result);
//
//        } catch (DaoException e){
//            System.out.println(e.getMessage());
//
//        }

//        try {
//            Element element = elementDAO.findByTypeNameAndRoomId(ElementType.PROP, "new chair", 4);
//            System.out.println(element);
//        } catch (DaoException e) {
//            System.out.println(e.getMessage());
//        }

//        try {
//            List<Element> elements = elementDAO.findAllByTypeAndRoomId(ElementType.PROP, 4);
//            elements.forEach(System.out::println);
//        } catch (DaoException e) {
//            System.out.println(e.getMessage());
//        }

//        try {
//            Element element = (Element) elementDAO.getById(7);
//            System.out.println(element);
//        } catch (DaoException e) {
//            System.out.println(e.getMessage());
//        }

//        Hint hint = new Hint(4, "new chair hint", ElementType.HINT, BigDecimal.valueOf(34.56), Status.ACTIVE, "wood theme");
//        try {
//            Element result = elementDAO.update(hint);
//            System.out.println(result);
//
//        } catch (DaoException e){
//            System.out.println(e.getMessage());
//
//        }

//        ElementService elementService = new ElementService();
//
//        try {
//            Element result = elementService.addProp(4, "wood", "new chair 3", BigDecimal.valueOf(49.00));
//            System.out.println(result);
//
//        } catch (DaoException | RoomDoesNotExistException | ElementAlreadyExistsException e){
//            System.out.println(e.getMessage());
//
//        }

//        ElementService elementService = new ElementService();
//
//        try {
//            Element result = elementService.addHint(4, "dark", "new chair 3 hint", BigDecimal.valueOf(49.00));
//            System.out.println(result);
//
//        } catch (DaoException | RoomDoesNotExistException | ElementAlreadyExistsException e){
//            System.out.println(e.getMessage());
//
//        }

        ElementService elementService = new ElementService();

        try {
            // Test elementService.deleteElement
            elementService.deleteElement(6);

            // Test elementService.removeElementFromRoom
            boolean result = elementService.removeElementFromRoom(11);
            System.out.println(result);

            // Test elementDAO.findAllByTypeAndRoomId
            List<Element> roomProps = elementDAO.findAllByTypeAndRoomId(ElementType.PROP, 4);
            System.out.println("Props in room 4");
            for(Element e: roomProps) {
                System.out.println(e);
            }

            // Ensure element 6 is not returned
            List<Element> roomHints = elementDAO.findAllByTypeAndRoomId(ElementType.HINT, 4);
            System.out.println("Hints in room 4");
            for(Element e: roomHints) {
                System.out.println(e);
            }

            // Test elementDAO.findAllByTypeAndRoomId: null roomId
            List<Element> noRoomProps = elementDAO.findAllByTypeAndRoomId(ElementType.PROP, null);
            System.out.println("Props with no room");
            for(Element e: noRoomProps) {
                System.out.println(e);
            }

            // Test elementDAO.findByTypeNameAndRoomId
            Element element10 = elementDAO.findByTypeNameAndRoomId(ElementType.HINT, "new chair hint", 4);
            Element element7 = elementDAO.findByTypeNameAndRoomId(ElementType.PROP, "new chair2", 4);
            Element elementNull = elementDAO.findByTypeNameAndRoomId(ElementType.HINT, "new chair2", 4);
            Element element11 = elementDAO.findByTypeNameAndRoomId(ElementType.PROP, "new chair 3", null);

            System.out.println("Element 10: " + element10);
            System.out.println("Element 7: " + element7);
            System.out.println("Element null: " + elementNull);
            System.out.println("Element 11: " + element11);


            // Test elementService.getElementById
            Element element1 = elementService.getElementById(1);
            System.out.println("Element 1 from service: " + element1);
            Element element2 = elementService.getElementById(2);
            System.out.println("Element 2 from service: " + element2);
            Element element11_2 = elementService.getElementById(11);
            System.out.println("Element 11 from service: " + element11_2);

        } catch (DaoException | ElementDoesNotExistException e){
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
