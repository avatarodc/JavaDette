package org.example.services;

import org.example.entities.Article;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
@Service
public interface ArticleService {
    Collection<Article> getArticlesByLibelle(String libelle);
    void saveArticle(Article article);
    Collection<Article> getAllArticles();
    Optional<Article> getArticleById(int id);
    void updateArticle(Article article);
    void deleteArticle(int id);
}
