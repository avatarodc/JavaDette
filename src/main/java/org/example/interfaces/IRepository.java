package org.example.interfaces;

import java.util.Collection;
import java.util.Optional;

public interface IRepository<T> {
    void save(T t); // Sauvegarder un élément
    Collection<T> findAll(); // Retourne tous les éléments sous forme de Collection
    Optional<T> findById(int id); // Trouver un élément par ID
    Optional<T> find(); // Trouver un seul élément (le premier ou une condition)
    void update(T t); // Mettre à jour un élément
    void delete(int id); // Supprimer un élément par ID

}
