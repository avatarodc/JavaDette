package org.example;

import org.example.views.MenuView;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        // Initialiser le contexte Spring
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("org.example");
        context.refresh();

        // Récupérer le bean MenuView et afficher le menu principal
        MenuView menuView = context.getBean(MenuView.class);
        menuView.displayMainMenu();
    }
}