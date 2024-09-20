package org.example.views;

import org.example.entities.Article;
import org.example.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Scanner;

@Component
public class ArticleView {

    private final ArticleService articleService;
    private final Scanner scanner;

    @Autowired
    public ArticleView(ArticleService articleService) {
        this.articleService = articleService;
        this.scanner = new Scanner(System.in);
    }

    public void displayArticleMenu() {
        boolean inArticleMenu = true;
        while (inArticleMenu) {
            System.out.println("\nGestion des Articles:");
            System.out.println("1. Ajouter un Article");
            System.out.println("2. Lister les Articles");
            System.out.println("3. Modifier un Article");
            System.out.println("4. Supprimer un Article");
            System.out.println("5. Retour au menu principal");
            System.out.print("Votre choix : ");

            int choice = getIntInput();
            switch (choice) {
                case 1:
                    addArticle();
                    break;
                case 2:
                    displayAllArticles();
                    break;
                case 3:
                    updateArticle();
                    break;
                case 4:
                    deleteArticle();
                    break;
                case 5:
                    inArticleMenu = false;
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
    }

    private void addArticle() {
        System.out.print("Entrez le libellé de l'article : ");
        String libelle = scanner.nextLine();
        System.out.print("Entrez le prix de l'article : ");
        double prix = getDoubleInput();
        System.out.print("Entrez la quantité de l'article : ");
        int quantite = getIntInput();

        Article newArticle = new Article(libelle, 0, prix, quantite);
        articleService.saveArticle(newArticle);
        System.out.println("Article ajouté avec succès.");
    }

    public void displayAllArticles() {
        Collection<Article> articles = articleService.getAllArticles();
        if (articles.isEmpty()) {
            System.out.println("Aucun article trouvé.");
        } else {
            articles.forEach(System.out::println);
        }
    }

    private void updateArticle() {
        System.out.print("Entrez l'ID de l'article à modifier : ");
        int id = getIntInput();
        Article article = articleService.getArticleById(id).orElse(null);
        if (article == null) {
            System.out.println("Article non trouvé.");
            return;
        }

        System.out.print("Nouveau libellé (laisser vide pour ne pas changer) : ");
        String libelle = scanner.nextLine();
        if (!libelle.isEmpty()) {
            article.setLibelle(libelle);
        }

        System.out.print("Nouveau prix (0 pour ne pas changer) : ");
        double prix = getDoubleInput();
        if (prix > 0) {
            article.setPrix(prix);
        }

        System.out.print("Nouvelle quantité (-1 pour ne pas changer) : ");
        int quantite = getIntInput();
        if (quantite >= 0) {
            article.setQuantite(quantite);
        }

        articleService.updateArticle(article);
        System.out.println("Article mis à jour avec succès.");
    }

    private void deleteArticle() {
        System.out.print("Entrez l'ID de l'article à supprimer : ");
        int id = getIntInput();
        articleService.deleteArticle(id);
        System.out.println("Article supprimé avec succès.");
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

    private double getDoubleInput() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Veuillez entrer un nombre valide : ");
            }
        }
    }
}