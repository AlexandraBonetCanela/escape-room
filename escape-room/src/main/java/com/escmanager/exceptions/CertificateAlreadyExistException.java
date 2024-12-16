package com.escmanager.exceptions;

public class CertificateAlreadyExistException extends Exception {

    public CertificateAlreadyExistException() {
        super("Certificate already exist");
    }
}
