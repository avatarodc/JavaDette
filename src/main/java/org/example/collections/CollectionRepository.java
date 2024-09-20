package org.example.collections;

import org.example.config.CollectionProvider;
import org.example.interfaces.IRepository;
import org.example.interfaces.Identifiable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

public abstract class CollectionRepository<T extends Identifiable> implements IRepository<T> {

    protected final CollectionProvider<T> collectionProvider;
    protected final Collection<T> collection;

    public CollectionRepository(CollectionProvider<T> collectionProvider) {
        this.collectionProvider = collectionProvider;
        this.collection = collectionProvider.getCollection();
    }
    @Override
    public void save(T t) {
        collection.add(t);
    }

    @Override
    public Collection<T> findAll() {
        return collection;
    }

    @Override
    public Optional<T> findById(int id) {
        return collection.stream()
                .filter(t -> t.getId() == id)
                .findFirst();
    }

    @Override
    public Optional<T> find() {
        return collection.stream().findFirst();
    }

    @Override
    public void update(T t) {
        delete(t.getId());
        save(t);
    }

    @Override
    public void delete(int id) {
        collection.removeIf(t -> t.getId() == id);
    }
}