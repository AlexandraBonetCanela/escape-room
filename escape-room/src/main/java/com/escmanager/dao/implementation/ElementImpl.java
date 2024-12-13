package com.escmanager.dao.implementation;

import com.escmanager.dao.ConnectionDB;
import com.escmanager.dao.ElementDAO;
import com.escmanager.enums.ElementType;
import com.escmanager.model.Element;

import java.util.List;

public class ElementImpl implements ElementDAO {

    ConnectionDB dao = ConnectionDB.getInstance();

    @Override
    public Element create(Element element) {
        return null;
    }

    @Override
    public Element update(Element element) {
        return null;
    }

    @Override
    public List<Element> findAllByTypeAndRoomId(ElementType type, int roomId) {
        return List.of();
    }

    @Override
    public Element findByTypeNameAndRoomId(ElementType type, String name, int roomId) {
        return null;
    }

    @Override
    public List getAll() {
        return List.of();
    }

    @Override
    public Object getById(int id) {
        return null;
    }
}
