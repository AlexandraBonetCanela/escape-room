package com.escmanager.exceptions.certificate;

public class CertificateDoesNotExistException extends Exception {

    public CertificateDoesNotExistException() {
        super("Certificate does Not Exist");
    }
}
