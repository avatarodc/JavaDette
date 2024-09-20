package org.example.repositories;

import org.example.collections.CrudRepository;
import org.example.entities.Article;
import org.example.repositories.interfaces.IArticleRepository;
import org.example.database.interfaces.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

@Repository
@Profile("jdbc")
public class ArticleRepositoryJDBC extends CrudRepository<Article> implements IArticleRepository {

    @Autowired
    public ArticleRepositoryJDBC(Database database) {
        super(database, Article.class);
    }

    @Override
    protected String getTableName() {
        return "articles";
    }

    @Override
    public Collection<Article> findByLibelle(String libelle) {
        String query = "SELECT * FROM " + getTableName() + " WHERE libelle = ?";
        try {
            ResultSet rs = database.executeQuery(query, libelle);
            return resultSetToCollection(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la recherche par libellé", e);
        }
    }

    @Override
    public void save(Article article) {
        String query = "INSERT INTO " + getTableName() + " (libelle, prix, quantite) VALUES (?, ?, ?)";
        try {
            database.executeUpdate(query, article.getLibelle(), article.getPrix(), article.getQuantite());
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la sauvegarde de l'article", e);
        }
    }

    @Override
    public void update(Article article) {
        String query = "UPDATE " + getTableName() + " SET libelle = ?, prix = ?, quantite = ? WHERE id = ?";
        try {
            database.executeUpdate(query, article.getLibelle(), article.getPrix(), article.getQuantite(), article.getId());
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la mise à jour de l'article", e);
        }
    }

}