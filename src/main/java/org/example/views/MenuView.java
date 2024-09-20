package org.example.views;

import org.example.services.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MenuView {
    private static final Logger logger = LoggerFactory.getLogger(MenuView.class);
    private final ArticleView articleView;
    private final Scanner scanner = new Scanner(System.in);

    @Autowired
    public MenuView(ArticleView articleView) {
        this.articleView = articleView;
    }

    public void displayMainMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\nMenu Principal:");
            System.out.println("1. Ajouter Client");
            System.out.println("2. Lister Client");
            System.out.println("3. Créer un compte user pour un client");
            System.out.println("4. Créer une Dette pour un Client");
            System.out.println("5. Effectuer un Paiement");
            System.out.println("6. Lister les dettes d'un client");
            System.out.println("7. Lister les Paiements une dette");
            System.out.println("8. Gestion des Articles");
            System.out.println("9. Quitter");
            System.out.print("Votre choix : ");

            int choice = getIntInput();
            switch (choice) {
                case 1:
                    // Ajouter Client
                    break;
                case 2:
                    // Lister Client
                    break;
                case 3:
                    // Créer un compte user pour un client
                    break;
                case 4:
                    // Créer une Dette pour un Client
                    break;
                case 5:
                    // Effectuer un Paiement
                    break;
                case 6:
                    // Lister les dettes d'un client
                    break;
                case 7:
                    // Lister les Paiements une dette
                    break;
                case 8:
                    articleView.displayArticleMenu();
                    break;
                case 9:
                    running = false;
                    System.out.println("Au revoir!");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
    }

    private int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Veuillez entrer un nombre valide : ");
            }
        }
    }
}