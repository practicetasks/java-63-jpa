package dao;

import model.Post;

import java.util.List;

public class PostDao extends EntityDao<Post> {

    @Override
    public Post findById(int id) {
        return manager.find(Post.class, id);
    }

    @Override
    public List<Post> findAll() {
        return manager.createQuery("select p from Post p", Post.class).getResultList();
    }
}
