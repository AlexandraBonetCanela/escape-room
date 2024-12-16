package com.escmanager.service;

import com.escmanager.dao.NewsletterDAO;
import com.escmanager.dao.implementation.NewsletterImpl;
import com.escmanager.model.Newsletter;

import java.util.ArrayList;

public class NewsletterService implements Observable {

    private ArrayList<Observer> observers = new ArrayList<>();
    private Newsletter newsletter;
    NewsletterDAO newsletterDAO = new NewsletterImpl();

    public Newsletter createNewsletter(String name){
        newsletter = newsletterDAO.create(name);
        notifyObservers();
        return newsletter;
    }

    @Override
    public void subscribe(Observer subscriber) {
        this.observers.add(subscriber);
    }

    @Override
    public void unsubscribe(Observer subscriber) {
        this.observers.remove(subscriber);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(subscriber -> {
            subscriber.update(this.newsletter);
            newsletterDAO.addUserToNewsletter(subscriber.getId(), newsletter.getId());
        });
    }
}
