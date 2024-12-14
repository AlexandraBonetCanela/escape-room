package com.escmanager.dao;

import com.escmanager.enums.ElementType;
import com.escmanager.model.Element;

import java.util.List;

public interface ElementDAO extends DAO{

    Element create(Element element);
    Element update(Element element);
    List<Element> findAllByTypeAndRoomId(ElementType elementType, int roomId);
    Element findByTypeNameAndRoomId(ElementType type, String name, int roomId);
}
