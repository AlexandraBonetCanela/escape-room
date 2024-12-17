package com.escmanager.dao;

import com.escmanager.enums.ElementType;
import com.escmanager.model.Element;

import java.util.List;

public interface ElementDAO extends DAO{

    Element create(Element element);
    Element update(Element element);
    List<Element> findAllByTypeAndRoomId(ElementType elementType, Integer roomId);
    Element findByTypeNameAndRoomId(ElementType type, String name, Integer roomId);
    boolean removeAllElementsFromRoom(int roomId);
}
