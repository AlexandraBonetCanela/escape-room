package com.escmanager.service;

import com.escmanager.dao.CertificateDAO;
import com.escmanager.dao.implementation.CertificateImpl;
import com.escmanager.exceptions.CertificateAlreadyExistException;
import com.escmanager.exceptions.CertificateDoesNotExistException;
import com.escmanager.model.Certificate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class CertificateServiceTest {

    @Mock
    private CertificateDAO certificateDAO;

    @InjectMocks
    private CertificateService certificateService;

    @BeforeEach
    @SuppressWarnings("unchecked")
    public void setUp() {
        certificateDAO = Mockito.mock(CertificateDAO.class);
        certificateService = new CertificateService();
        certificateService.certificateDAO = certificateDAO;
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testGenerateCertificate_Success() throws CertificateAlreadyExistException {

        String name = "TestingCertificate";
        String description = "TestingDescription";
        int escapeRoomId = 1;

        when(certificateDAO.getByName(name)).thenReturn(null);

        Certificate certificate = certificateService.generateCertificate(name, description, escapeRoomId);

        assertNotNull(certificate);
        assertEquals(name, certificate.getName());
        assertEquals(description, certificate.getDescription());
        assertEquals(escapeRoomId, certificate.getEscape_room_id());

        verify(certificateDAO, times(1)).create(certificate);
    }

    @Test
    public void testGenerateCertificate_AlreadyExists() {

        String name = "ExistingCertificate";
        String description = "TestDescription";
        int escapeRoomId = 1;

        Certificate existingCertificate = new Certificate();
        existingCertificate.setName(name);
        when(certificateDAO.getByName(name)).thenReturn(existingCertificate);

        assertThrows(CertificateAlreadyExistException.class, () -> {
            certificateService.generateCertificate(name, description, escapeRoomId);
        });

        verify(certificateDAO, never()).create(any());
    }

    @Test
    public void testUpdateCertificate_Success() throws CertificateDoesNotExistException {

        int certificateId = 1;
        int escapeRoomId = 2;
        String name = "UpdatedCertificate";
        String description = "UpdatedDescription";

        Certificate certificate = new Certificate();
        certificate.setId(certificateId);

        when(certificateDAO.getById(certificateId)).thenReturn(certificate);


        Certificate updatedCertificate = certificateService.updateCertificate(certificateId, name, description, escapeRoomId);

        assertNotNull(updatedCertificate);
        assertEquals("Updated Certificate", updatedCertificate.getName());
        assertEquals(description, updatedCertificate.getDescription());
        assertEquals(escapeRoomId, updatedCertificate.getEscape_room_id());

        verify(certificateDAO, times(1)).update(updatedCertificate);
    }

    @Test
    public void testUpdateCertificate_DoesNotExist() {

        int certificateId = 999;
        when(certificateDAO.getById(certificateId)).thenReturn(null);

        assertThrows(CertificateDoesNotExistException.class, () -> {
            certificateService.updateCertificate(certificateId, "name", "description", 1);
        });

        verify(certificateDAO, never()).update(any());
    }

    @Test
    public void testGetAllCertificates() {

        List<Certificate> certificateList = new ArrayList<>();
        Certificate cert1 = new Certificate();
        cert1.setName("Cert 1");
        certificateList.add(cert1);
        when(certificateDAO.getAll()).thenReturn(certificateList);

        List<Certificate> certificates = certificateService.getAllCertificates();

        assertNotNull(certificates);
        assertEquals(1, certificates.size());
        assertEquals("Cert 1", certificates.get(0).getName());

        verify(certificateDAO, times(1)).getAll();
    }

    @Test
    public void testGetCertificateById() {

        int certificateId = 1;
        Certificate certificate = new Certificate();
        certificate.setId(certificateId);
        certificate.setName("Certificate Name");
        when(certificateDAO.getById(certificateId)).thenReturn(certificate);

        Certificate result = certificateService.getCertificateById(certificateId);

        assertNotNull(result);
        assertEquals("Certificate Name", result.getName());

        verify(certificateDAO, times(1)).getById(certificateId);
    }
}