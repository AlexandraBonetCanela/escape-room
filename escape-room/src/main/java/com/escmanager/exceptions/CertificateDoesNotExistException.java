package com.escmanager.exceptions;

public class CertificateDoesNotExistException extends Exception {

    public CertificateDoesNotExistException() {
        super("Certificate does Not Exist");
    }
}
