package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public abstract class EntityDao<T> {
    static EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
    static EntityManager manager = factory.createEntityManager();

    public abstract T findById(int id);

    public abstract List<T> findAll();

    public void update(T entity) {
        try {
            manager.getTransaction().begin();

            manager.merge(entity);

            manager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            manager.getTransaction().rollback();
        }
    }

    public void delete(T entity) {
        try {
            manager.getTransaction().begin();

            manager.remove(entity);

            manager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            manager.getTransaction().rollback();
        }
    }

    public void create(T entity) {
        try {
            manager.getTransaction().begin();

            manager.persist(entity);

            manager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            manager.getTransaction().rollback();
        }
    }
}
