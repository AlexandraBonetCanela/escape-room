package com.escmanager.service;

public interface Observable {

    public void subscribe(Observer subscriber);
    public void unsubscribe(Observer subscriber);
    public void notifyObservers();
}
