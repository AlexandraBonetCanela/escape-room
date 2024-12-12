package com.escmanager.dao;

import com.escmanager.model.Certificate;

public interface CertificateDAO extends DAO{

    Certificate create(Certificate certificate);
    Certificate update(Certificate certificate);
    Certificate getByName(String name);

}
