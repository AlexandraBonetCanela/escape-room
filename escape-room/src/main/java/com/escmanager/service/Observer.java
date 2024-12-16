package com.escmanager.service;

import com.escmanager.model.Newsletter;

public interface Observer {

    void update(Newsletter message);
    int getId();
}
