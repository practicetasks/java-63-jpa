package dao;

import model.User;

public class UserDao extends EntityDao<User> {
    public UserDao() {
        super(User.class);
    }
}
