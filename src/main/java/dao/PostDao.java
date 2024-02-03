package dao;

import model.Post;

public class PostDao extends EntityDao<Post> {

    public PostDao() {
        super(Post.class);
    }
}
