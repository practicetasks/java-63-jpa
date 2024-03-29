package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public abstract class EntityDao<T> {
    protected static EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
    protected static EntityManager manager = factory.createEntityManager();

    private final Class<T> type;

    public EntityDao(Class<T> type) {
        this.type = type;
    }

    public T findById(int id) {
        return manager.find(type, id);
    }

    public List<T> findAll() {
        return manager.createQuery("select t from " + type.getSimpleName() + " t", type).getResultList();
    }

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
