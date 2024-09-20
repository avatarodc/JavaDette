package org.example.config;

import org.example.database.DatabaseFactory;
import org.example.repositories.ArticleRepositoryCollection;
import org.example.repositories.interfaces.IArticleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collection;

@Component
public class CollectionProvider<T> {
    public Collection<T> getCollection() {
        return new ArrayList<>();
    }
}