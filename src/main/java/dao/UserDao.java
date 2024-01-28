package dao;

import model.User;

import java.util.List;

public class UserDao extends EntityDao<User> {

    @Override
    public User findById(int id) {
        return manager.find(User.class, id);
    }

    @Override
    public List<User> findAll() {
        return manager.createQuery("select u from User u", User.class).getResultList();
    }
}
