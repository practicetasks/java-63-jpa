package dao;

import model.Post;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class PostDao {
    static EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
    static EntityManager manager = factory.createEntityManager();

    public void findAll(){
        List<Post> posts = manager.createQuery("select p from Post p", Post.class).getResultList();

        System.out.println(posts);
    }

    public Post getPost(){
        return manager.find(Post.class, 1);
    }
}
