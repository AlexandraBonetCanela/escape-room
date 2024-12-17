package com.escmanager.dao.implementation;

import com.escmanager.builder.ElementBuilder;
import com.escmanager.builder.HintBuilder;
import com.escmanager.builder.PropBuilder;
import com.escmanager.dao.ConnectionDB;
import com.escmanager.dao.ElementDAO;
import com.escmanager.enums.ElementType;
import com.escmanager.enums.Status;
import com.escmanager.exceptions.DaoException;
import com.escmanager.exceptions.element.ElementDoesNotExistException;
import com.escmanager.model.Element;
import com.escmanager.model.Hint;
import com.escmanager.model.Prop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ElementImpl implements ElementDAO {

    ConnectionDB dao = ConnectionDB.getInstance();

    @Override
    public Element create(Element element) {
        int elementId;
        switch (element.getType()) {
            case PROP:
                PropBuilder propBuilder = new PropBuilder();
                elementId = createElementDetails(element, propBuilder);
                Prop prop = (Prop) element;
                propBuilder.setMaterialType(createPropMaterialType(elementId, prop.getMaterialType()));
                return propBuilder.build();
            case HINT:
                HintBuilder hintBuilder = new HintBuilder();
                elementId = createElementDetails(element, hintBuilder);
                Hint hint = (Hint) element;
                hintBuilder.setTheme(createHintTheme(elementId, hint.getTheme()));
                return hintBuilder.build();
            default:
                throw new DaoException("Unsupported element type", null);
        }
    }

    @Override
    public Element update(Element element) {
        String query = "UPDATE element SET room_id = ?, type = ?, name = ?, price = ?, status = ? WHERE id = ?";
        Integer result = null;
        try (Connection connection = dao.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            if(element.getRoomId() == null) {
                statement.setNull(1, Types.INTEGER);
            }
            else {
                statement.setInt(1, element.getRoomId());
            }
            statement.setString(2, String.valueOf(element.getType()));
            statement.setString(3, element.getName());
            statement.setBigDecimal(4, element.getPrice());
            statement.setString(5, String.valueOf(element.getStatus()));
            statement.setInt(6, element.getId());
            statement.executeUpdate();

            return findByTypeNameAndRoomId(element.getType(), element.getName(), element.getRoomId());

        } catch (SQLException e) {
            throw new DaoException("Failed at updating Escaperoom in database", e);
        }
    }

    public boolean removeAllElementsFromRoom(int roomId){
        String query = "UPDATE element SET room_id = NULL WHERE room_Id = ?";

        try (Connection connection = dao.getConnection();
        PreparedStatement statement = connection.prepareStatement(query)){

            statement.setInt(1, roomId);
            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            throw new DaoException("Failed at detaching elements from room in database", e);
        }
    }

    @Override
    public Element findByTypeNameAndRoomId(ElementType type, String name, Integer roomId) {
        try {
            switch (type) {
                case PROP:
                    PropBuilder propBuilder = new PropBuilder();
                    int propId = getElementDetails(propBuilder, type, name, roomId);
                    propBuilder.setMaterialType(getPropMaterialType(propId));
                    return propBuilder.build();
                case HINT:
                    HintBuilder hintBuilder = new HintBuilder();
                    int hintId = getElementDetails(hintBuilder, type, name, roomId);
                    hintBuilder.setTheme(getHintTheme(hintId));
                    return hintBuilder.build();
                default:
                    throw new DaoException("Unsupported element type", null);
            }
        }
        catch(ElementDoesNotExistException e) {
            return null;
        }
    }

    @Override
    public List<Element> findAllByTypeAndRoomId(ElementType elementType, Integer roomId) {

        String tailQuery;
        if(roomId != null){
           tailQuery = "AND e.room_id = ?";
        } else {
            tailQuery = "AND e.room_id IS ?";}

        String propQuery = "SELECT * FROM element e INNER JOIN prop p ON e.id = p.element_id WHERE e.status = 'ACTIVE' AND e.type = ? " + tailQuery;
        String hintQuery = "SELECT * FROM element e INNER JOIN hint h ON e.id = h.element_id WHERE e.status = 'ACTIVE' AND e.type = ? " + tailQuery;



        List<Element> elements = new ArrayList<>();

        try (Connection connection = dao.getConnection();
            PreparedStatement propStatement = connection.prepareStatement(propQuery);
            PreparedStatement hintStatement = connection.prepareStatement(hintQuery)){

            switch (elementType) {
                case PROP:
                    propStatement.setString(1, String.valueOf(elementType));
                    propStatement.setObject(2, roomId);
                    ResultSet propResultSet = propStatement.executeQuery();

                    while (propResultSet.next()){
                        PropBuilder propBuilder = new PropBuilder();
                        propBuilder.setId(propResultSet.getInt("id"));
                        propBuilder.setRoomId(propResultSet.getObject("room_id", Integer.class));
                        propBuilder.setType(ElementType.valueOf(propResultSet.getString("type")));
                        propBuilder.setName(propResultSet.getString("name"));
                        propBuilder.setPrice(propResultSet.getBigDecimal("price"));
                        propBuilder.setStatus(Status.valueOf(propResultSet.getString("status")));
                        propBuilder.setDateCreated(propResultSet.getTimestamp("date_created"));
                        propBuilder.setLastUpdated(propResultSet.getTimestamp("last_updated"));
                        propBuilder.setMaterialType(propResultSet.getString("material_type"));
                        elements.add(propBuilder.build());
                    }
                    return elements;
                case HINT:
                    hintStatement.setString(1, String.valueOf(elementType));
                    hintStatement.setObject(2, roomId);
                    hintStatement.executeQuery();
                    ResultSet hintResultSet = hintStatement.executeQuery();

                    while (hintResultSet.next()){
                        HintBuilder hintBuilder = new HintBuilder();
                        hintBuilder.setId(hintResultSet.getInt("id"));
                        hintBuilder.setRoomId(hintResultSet.getInt("room_id"));
                        hintBuilder.setType(ElementType.valueOf(hintResultSet.getString("type")));
                        hintBuilder.setName(hintResultSet.getString("name"));
                        hintBuilder.setPrice(hintResultSet.getBigDecimal("price"));
                        hintBuilder.setStatus(Status.valueOf(hintResultSet.getString("status")));
                        hintBuilder.setDateCreated(hintResultSet.getTimestamp("date_created"));
                        hintBuilder.setLastUpdated(hintResultSet.getTimestamp("last_updated"));
                        hintBuilder.setTheme(hintResultSet.getString("theme"));
                        elements.add(hintBuilder.build());
                    }
                    return elements;
            }

        } catch (SQLException e){
            throw new DaoException("Failed at querying Element in database", e);
        }
        return null;
    }

    @Override
    public List<Element> getAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Element getById(int elementId) {
        String query = "SELECT * FROM element WHERE id = ? AND status = 'ACTIVE'";
        ElementBuilder elementBuilder;
        try (Connection connection = dao.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, elementId);
            ResultSet resultSet = statement.executeQuery();

            ElementType type;
            if (resultSet.next()) {
                type = ElementType.valueOf(resultSet.getString("type"));
                switch (type) {
                    case PROP:
                        elementBuilder = new PropBuilder();
                        break;
                    case HINT:
                        elementBuilder = new HintBuilder();
                        break;
                    default:
                        throw new DaoException("Unsupported element type", null);
                }

                elementBuilder.setId(resultSet.getInt("id"));
                elementBuilder.setRoomId(resultSet.getObject("room_id", Integer.class));
                elementBuilder.setType(ElementType.valueOf(resultSet.getString("type")));
                elementBuilder.setName(resultSet.getString("name"));
                elementBuilder.setPrice(resultSet.getBigDecimal("price"));
                elementBuilder.setStatus(Status.valueOf(resultSet.getString("status")));
                elementBuilder.setDateCreated(resultSet.getTimestamp("date_created"));
                elementBuilder.setLastUpdated(resultSet.getTimestamp("last_updated"));

                switch (type) {
                    case PROP:
                        PropBuilder propBuilder = (PropBuilder) elementBuilder;
                        propBuilder.setMaterialType(getPropMaterialType(elementId));
                        return propBuilder.build();
                    case HINT:
                        HintBuilder hintBuilder = (HintBuilder) elementBuilder;
                        hintBuilder.setTheme(getHintTheme(elementId));
                        return hintBuilder.build();
                    default:
                        throw new DaoException("Unsupported element type", null);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Failed to find element by type and name and roomId", e);
        }
        return null;
    }

    private Integer createElementDetails(Element element, ElementBuilder elementBuilder) {
        String query = "INSERT INTO element(room_id, type, name, price, status) VALUES(?,?,?,?,?)";
        Integer result = null;
        try (Connection connection = dao.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, element.getRoomId());
            statement.setString(2, String.valueOf(element.getType()));
            statement.setString(3, element.getName());
            statement.setBigDecimal(4, element.getPrice());
            statement.setString(5, String.valueOf(element.getStatus()));
            statement.executeUpdate();

            return getElementDetails(elementBuilder, element.getType(), element.getName(), element.getRoomId());

        } catch (SQLException | ElementDoesNotExistException e){
            throw new DaoException("Failed to create Element in database", e);
        }
    }

    private String createPropMaterialType(int elementId, String materialType) {
        String query = "INSERT INTO prop(element_id, material_type) VALUES(?,?)";
        Integer result = null;
        try (Connection connection = dao.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, elementId);
            statement.setString(2, materialType);
            statement.executeUpdate();

            return getPropMaterialType(elementId);

        } catch (SQLException e){
            throw new DaoException("Failed to create Element in database", e);
        }
    }

    private String createHintTheme(int elementId, String hint) {
        String query = "INSERT INTO hint(element_id, theme) VALUES(?,?)";
        Integer result = null;
        try (Connection connection = dao.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, elementId);
            statement.setString(2, hint);
            statement.executeUpdate();

            return getPropMaterialType(elementId);

        } catch (SQLException e){
            throw new DaoException("Failed to create Element in database", e);
        }
    }

    private int getElementDetails(ElementBuilder elementBuilder, ElementType type, String name, Integer roomId) throws ElementDoesNotExistException {

        String tailQuery;
        if(roomId != null){
            tailQuery = "AND room_id = ?";
        } else {
            tailQuery = "AND room_id IS ?";}

        String query = "SELECT * FROM element WHERE type = ? AND status = 'ACTIVE' AND name = ? " + tailQuery;
        int elementId;
        try (Connection connection = dao.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, type.name());
            statement.setString(2, name);
            statement.setObject(3, roomId, Types.INTEGER);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                elementId = resultSet.getInt("id");
                elementBuilder.setId(resultSet.getInt("id"));
                elementBuilder.setRoomId(resultSet.getObject("room_id", Integer.class));
                elementBuilder.setType(type);
                elementBuilder.setName(resultSet.getString("name"));
                elementBuilder.setPrice(resultSet.getBigDecimal("price"));
                elementBuilder.setStatus(Status.valueOf(resultSet.getString("status")));
                elementBuilder.setDateCreated(resultSet.getTimestamp("date_created"));
                elementBuilder.setLastUpdated(resultSet.getTimestamp("last_updated"));
                return elementId;
            }
            else {
                throw new ElementDoesNotExistException("Type: " + type + " name: " + name + " roomId: " + roomId);
            }
        } catch (SQLException e) {
            throw new DaoException("Failed to find element by type and name and roomId", e);
        }
    }

    private String getPropMaterialType(int elementId){
        String materialTypeQuery = "SELECT * FROM prop WHERE element_id = ?";
        try (Connection connection = dao.getConnection();
             PreparedStatement statement = connection.prepareStatement(materialTypeQuery)) {

            statement.setInt(1, elementId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("material_type");
            }
        } catch (SQLException e) {
            throw new DaoException("Failed to find prop by type and name and roomId", e);
        }
        return null;
    }

    private String getHintTheme(int elementId){
        String themeQuery = "SELECT * FROM hint WHERE element_id = ?";
        try (Connection connection = dao.getConnection();
             PreparedStatement statement = connection.prepareStatement(themeQuery)) {

            statement.setInt(1, elementId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("theme");
            }
        } catch (SQLException e) {
            throw new DaoException("Failed to find hint by type and name and roomId", e);
        }
        return null;
    }
}