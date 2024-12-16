package com.escmanager.service;

import com.escmanager.dao.CertificateDAO;
import com.escmanager.dao.implementation.CertificateImpl;
import com.escmanager.exceptions.CertificateAlreadyExistException;
import com.escmanager.exceptions.CertificateDoesNotExistException;
import com.escmanager.model.Certificate;

import java.util.List;

public class CertificateService {

    CertificateDAO certificateDAO = new CertificateImpl();

    public Certificate generateCertificate(String name, String description, int escape_room_id)
            throws CertificateAlreadyExistException {

        Certificate certificate = certificateDAO.getByName(name);

        if (certificate != null) {
            throw new CertificateAlreadyExistException();
        }

        certificate = new Certificate();
        certificate.setName(name);
        certificate.setDescription(description);
        certificate.setEscape_room_id(escape_room_id);
        certificateDAO.create(certificate);

        return certificate;
    }

    public Certificate updateCertificate(int id, String name, String description, int escape_room_id)
            throws CertificateDoesNotExistException {

        Certificate certificate = (Certificate) certificateDAO.getById(id);

        if(certificate == null){
            throw new CertificateDoesNotExistException();
        }

        certificate.setId(1);
        certificate.setName("name");
        certificate.setDescription("description");
        certificate.setEscape_room_id(1);
        certificateDAO.update(certificate);

        return certificate;
    }

    public List<Certificate> getAllCertificates() {
        List<Certificate> certificateList = certificateDAO.getAll();
        for (Certificate certificate : certificateList){
            System.out.println(certificate);
        }
        return certificateList;
    }
}