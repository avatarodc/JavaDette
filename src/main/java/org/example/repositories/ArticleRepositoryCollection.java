package org.example.repositories;

import org.example.collections.CollectionRepository;
import org.example.config.CollectionProvider;
import org.example.entities.Article;
import org.example.repositories.interfaces.IArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;

@Repository
@Profile("collection")  // Utiliser ce repository avec le profil "collection"
public class ArticleRepositoryCollection extends CollectionRepository<Article> implements IArticleRepository {

    // Injecter CollectionProvider<Article>
    @Autowired
    public ArticleRepositoryCollection(CollectionProvider<Article> collectionProvider) {
        super(collectionProvider);  // Utilise getCollection() pour obtenir la collection d'articles
        // Définir des données par défaut
        this.collection.add(new Article("Lait", 1, 100, 100));
        this.collection.add(new Article("Couche",2 , 200, 200));
    }

    @Override
    public Collection<Article> findByLibelle(String libelle) {
        Collection<Article> result = new ArrayList<>();
        for (Article article : findAll()) {
            if (article.getLibelle().equalsIgnoreCase(libelle)) {
                result.add(article);
            }
        }
        return result;
    }
}
