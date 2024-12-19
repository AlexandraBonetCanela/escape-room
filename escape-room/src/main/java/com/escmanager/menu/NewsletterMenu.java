package com.escmanager.menu;

import com.escmanager.service.NewsletterService;

public class NewsletterMenu {

    static NewsletterService newsletterService = NewsletterService.getInstance();

    public static void showMenu(){

        System.out.println("\nSEND NEW NEWSLETTER");
        String newsletterName = MenuUtils.getNonEmptyString("name of the new newsletter");
        newsletterService.createNewsletter(newsletterName);
        System.out.println("Newsletter successfully sent!");
    }
}
