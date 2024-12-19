package com.escmanager.service;

import com.escmanager.dao.NewsletterDAO;
import com.escmanager.dao.UserDAO;
import com.escmanager.dao.implementation.NewsletterImpl;
import com.escmanager.dao.implementation.UserImpl;
import com.escmanager.model.Newsletter;
import com.escmanager.model.User;

import java.util.ArrayList;
import java.util.List;

public class NewsletterService implements Observable {

    private static NewsletterService instance = new NewsletterService();
    public static NewsletterService getInstance() {
        return instance;
    }
    private NewsletterService () {}

    private ArrayList<Observer> observers = new ArrayList<>();
    private Newsletter newsletter;
    NewsletterDAO newsletterDAO = new NewsletterImpl();
    UserDAO userDAO = new UserImpl();

    public void loadSubscribers() {
        List<User> dbSubscribers = userDAO.getAllSubscribers();
        observers.clear();
        observers.addAll(dbSubscribers);
    }

    public Newsletter createNewsletter(String name){
        newsletter = newsletterDAO.create(name);
        loadSubscribers();
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
