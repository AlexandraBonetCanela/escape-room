package com.escmanager.service;

import com.escmanager.dao.CertificateDAO;
import com.escmanager.dao.EscapeRoomDAO;
import com.escmanager.dao.implementation.CertificateImpl;
import com.escmanager.dao.implementation.EscapeRoomImpl;
import com.escmanager.enums.Status;
import com.escmanager.exceptions.EscapeRoomAlreadyExistException;
import com.escmanager.exceptions.EscapeRoomDoesNotExistException;
import com.escmanager.model.Certificate;
import com.escmanager.model.EscapeRoom;


import java.math.BigDecimal;
import java.util.List;

public class CertificateService {

    CertificateDAO certificateDAO = new CertificateImpl();

    public Certificate generateCertificate(User user, EscapeRoom escapeRoom, String description) throws EscapeRoomAlreadyExistException, EscapeRoomDoesNotExistException {

        Certificate certificate = certificateDAO.getById(id);

        // UserDoesntExist must be integrated
        if(escapeRoom == null || user == null){
            throw new EscapeRoomDoesNotExistException();
        }

        certificate = new Certificate();
        certificate.setName(User.getName());
        certificate.setDescription(description);
        certificate.setEscape_room_id(escapeRoomId);
        certificate = certificateDAO.create(certificate);

        return certificate;
    }

    public boolean deleteEscapeRoom(int id) throws EscapeRoomDoesNotExistException {

        EscapeRoom escapeRoom = (EscapeRoom) escapeRoomDAO.getById(id);

        if(escapeRoom == null){
            throw new EscapeRoomDoesNotExistException();
        }
        escapeRoom.setStatus(Status.INACTIVE);
        escapeRoomDAO.update(escapeRoom);

        return true;
    }

    public List<EscapeRoom> getAllEscapeRooms() {

        List<EscapeRoom> escapeRooms = escapeRoomDAO.getAll();

        return escapeRooms;
    }

}
