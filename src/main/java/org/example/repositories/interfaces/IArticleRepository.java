package org.example.repositories.interfaces;

import org.example.entities.Article;
import org.example.interfaces.IRepository;
import java.util.Collection;
import java.util.Optional;

public interface IArticleRepository extends IRepository<Article> {
    Collection<Article> findByLibelle(String libelle);
}
