package org.example.collections;

import org.example.database.interfaces.Database;
import org.example.interfaces.IRepository;
import org.example.interfaces.Identifiable;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public abstract class CrudRepository<T extends Identifiable> implements IRepository<T> {
    protected final Database database;
    protected final String tableName;
    protected final Class<T> entityClass;

    public CrudRepository(Database database, Class<T> entityClass) {
        this.database = database;
        this.tableName = getTableName();
        this.entityClass = entityClass;
    }

    protected abstract String getTableName();

    @Override
    public void save(T t) {
        String query = "INSERT INTO " + tableName + " VALUES (?)"; // Ajustez la requête selon les colonnes
        try {
            database.executeUpdate(query, t.getId()); // Ajoutez d'autres valeurs selon les colonnes
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la sauvegarde", e);
        }
    }

    @Override
    public Collection<T> findAll() {
        String query = "SELECT * FROM " + tableName;
        try {
            ResultSet rs = database.executeQuery(query);
            return resultSetToCollection(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération de tous les éléments", e);
        }
    }

    @Override
    public Optional<T> findById(int id) {
        String query = "SELECT * FROM " + tableName + " WHERE id = ?";
        try {
            ResultSet rs = database.executeQuery(query, id);
            if (rs.next()) {
                return Optional.of(resultSetToObject(rs));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la recherche par ID", e);
        }
    }

    @Override
    public void update(T t) {
        // Exemple d'implémentation, ajustez selon vos besoins
        String query = "UPDATE " + tableName + " SET column = ? WHERE id = ?"; // Ajustez pour toutes les colonnes
        try {
            database.executeUpdate(query, "value", t.getId()); // Ajoutez d'autres valeurs selon les colonnes
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la mise à jour", e);
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM " + tableName + " WHERE id = ?";
        try {
            database.executeUpdate(query, id);
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la suppression", e);
        }
    }

    protected T resultSetToObject(ResultSet rs) throws SQLException {
        try {
            Constructor<T> constructor = entityClass.getDeclaredConstructor();
            T entity = constructor.newInstance();

            Field[] fields = entityClass.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                Class<?> fieldType = field.getType();

                setFieldValue(entity, rs, field, fieldName, fieldType);
            }

            return entity;
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Erreur lors de la création dynamique de l'objet", e);
        }
    }

    private void setFieldValue(T entity, ResultSet rs, Field field, String fieldName, Class<?> fieldType) throws SQLException, IllegalAccessException {
        if (fieldType == String.class) {
            field.set(entity, rs.getString(fieldName));
        } else if (fieldType == int.class || fieldType == Integer.class) {
            field.set(entity, rs.getInt(fieldName));
        } else if (fieldType == double.class || fieldType == Double.class) {
            field.set(entity, rs.getDouble(fieldName));
        } else if (fieldType == boolean.class || fieldType == Boolean.class) {
            field.set(entity, rs.getBoolean(fieldName));
        } else if (fieldType == long.class || fieldType == Long.class) {
            field.set(entity, rs.getLong(fieldName));
        } else if (fieldType == float.class || fieldType == Float.class) {
            field.set(entity, rs.getFloat(fieldName));
        }
    }

    protected Collection<T> resultSetToCollection(ResultSet rs) throws SQLException {
        Collection<T> collection = new ArrayList<>();
        while (rs.next()) {
            collection.add(resultSetToObject(rs));
        }
        return collection;
    }

    @Override
    public Optional<T> find() {
        String query = "SELECT * FROM " + tableName + " LIMIT 1";
        try {
            ResultSet rs = database.executeQuery(query);
            if (rs.next()) {
                return Optional.of(resultSetToObject(rs));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la recherche", e);
        }
    }
}
