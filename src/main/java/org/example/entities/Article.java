package org.example.entities;

import org.example.interfaces.Identifiable;

        public class Article implements Identifiable {
            private String libelle;
            private int id;
            private double prix;
            private int quantite;

            // Constructeur par défaut
            public Article() {
            }

    // Constructeur avec tous les champs
    public Article(String libelle, int id, double prix, int quantite) {
        this.libelle = libelle;
        this.id = id;
        this.prix = prix;
        this.quantite = quantite;
    }

            // Getters et setters
    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                ", prix=" + prix +
                ", quantite=" + quantite +
                '}';
    }
}