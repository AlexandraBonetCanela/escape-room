package com.escmanager.exceptions.certificate;

public class CertificateAlreadyExistException extends Exception {

    public CertificateAlreadyExistException() {
        super("Certificate already exist");
    }
}
