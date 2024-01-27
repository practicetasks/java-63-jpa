package dao;

import model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class UserDao implements AutoCloseable {
    static EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
    static EntityManager manager = factory.createEntityManager();

    public User findById(int userId) {
        return manager.find(User.class, userId);
    }

    public List<User> findAll() {
        return manager.createQuery("select u from User u", User.class)
                .getResultList();
    }

    public void create(User user) {
        try {
            manager.getTransaction().begin();

            manager.persist(user);

            manager.getTransaction().commit();
            System.out.println("Пользователь был создан");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            manager.getTransaction().rollback();
        }
    }

    public void update(User user) {

    }

    public void delete(User user) {

    }

    @Override
    public void close() throws Exception {
        if (manager.isOpen()) {
            manager.close();
        }
    }
}
