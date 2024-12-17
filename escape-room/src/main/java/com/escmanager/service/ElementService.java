package com.escmanager.service;

import com.escmanager.builder.HintBuilder;
import com.escmanager.dao.ElementDAO;
import com.escmanager.dao.implementation.ElementImpl;
import com.escmanager.enums.ElementType;
import com.escmanager.enums.Status;
import com.escmanager.exceptions.DaoException;
import com.escmanager.exceptions.element.ElementAlreadyExistsException;
import com.escmanager.exceptions.element.ElementDoesNotExistException;
import com.escmanager.exceptions.room.RoomDoesNotExistException;
import com.escmanager.model.Element;
import com.escmanager.builder.PropBuilder;

import java.math.BigDecimal;
import java.util.List;

public class ElementService {

    private static ElementService instance = new ElementService();
    public static ElementService getInstance() {
        return instance;
    }

    private ElementService() {}
    ElementDAO elementDAO = new ElementImpl();
    RoomService roomService = RoomService.getInstance();

    public Element addProp(int roomId, String materialType, String name, BigDecimal price) throws RoomDoesNotExistException, DaoException, ElementAlreadyExistsException {

        roomService.getRoomById(roomId);

        checkElementDoesNotExist(ElementType.PROP, name, roomId);

        PropBuilder propBuilder = new PropBuilder();
        propBuilder.setRoomId(roomId);
        propBuilder.setName(name);
        propBuilder.setPrice(price);
        propBuilder.setStatus(Status.ACTIVE);
        propBuilder.setType(ElementType.PROP);

        propBuilder.setMaterialType(materialType);

        Element prop = propBuilder.build();

        return elementDAO.create(prop);
    }

    public Element addHint(int roomId, String theme, String name, BigDecimal price) throws RoomDoesNotExistException, ElementAlreadyExistsException {

        roomService.getRoomById(roomId);

        Element element = elementDAO.findByTypeNameAndRoomId(ElementType.HINT, name, roomId);

        if (element != null) {
            throw new ElementAlreadyExistsException("Element already exists");
        }

        HintBuilder hintBuilder = new HintBuilder();
        hintBuilder.setRoomId(roomId);
        hintBuilder.setName(name);
        hintBuilder.setPrice(price);
        hintBuilder.setStatus(Status.ACTIVE);
        hintBuilder.setType(ElementType.HINT);

        hintBuilder.setTheme(theme);

        Element hint = hintBuilder.build();

        return elementDAO.create(hint);
    }

    public boolean removeAllElementsFromRoom(int roomId) throws RoomDoesNotExistException {
        elementDAO.removeAllElementsFromRoom(roomId);
        return true;
    }

    public boolean removeElementFromRoom(int elementId) throws ElementDoesNotExistException {

        Element element = getElementById(elementId);

        element.setRoomId(null);
        elementDAO.update(element);

        return true;
    }

    public boolean deleteElement(int elementId) throws ElementDoesNotExistException {
        Element element = getElementById(elementId);

        element.setStatus(Status.INACTIVE);
        elementDAO.update(element);

        return true;
    }

//    public List<Element> getRoomElements(int roomId) throws RoomDoesNotExistException {
//
//        return;
//    }


    public List<Element> getHints(int roomId) throws RoomDoesNotExistException {

        roomService.getRoomById(roomId);

        return elementDAO.findAllByTypeAndRoomId(ElementType.HINT, roomId);
    }

    public void checkElementDoesNotExist(ElementType elementType, String name, int roomId) throws ElementAlreadyExistsException {

        Element element = elementDAO.findByTypeNameAndRoomId(elementType, name, roomId);

        if(element != null){
            throw new ElementAlreadyExistsException("Element already exists");
        }
    }

    public Element getElementById(int elementId) throws ElementDoesNotExistException {

        Element element = (Element) elementDAO.getById(elementId);

        if(element == null){
            throw new ElementDoesNotExistException("Element does not exist");
        }
        return element;
    }
}
