package org.example.services;

import org.example.entities.Article;
import org.example.repositories.interfaces.IArticleRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@Component
public class ArticleServiceImpl implements ArticleService {

    private final IArticleRepository articleRepository;

    public ArticleServiceImpl(IArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public Collection<Article> getArticlesByLibelle(String libelle) {
        return articleRepository.findByLibelle(libelle);
    }

    @Override
    public void saveArticle(Article article) {
        articleRepository.save(article);
    }

    @Override
    public Collection<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @Override
    public Optional<Article> getArticleById(int id) {
        return articleRepository.findById(id);
    }

    @Override
    public void updateArticle(Article article) {
        articleRepository.update(article);
    }

    @Override
    public void deleteArticle(int id) {
        articleRepository.delete(id);
    }
}
