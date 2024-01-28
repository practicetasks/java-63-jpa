package service;

import dao.PostDao;
import dao.UserDao;
import model.Post;
import model.User;

import java.time.LocalDateTime;
import java.util.Scanner;

public class PostService {
    private final PostDao postDao;
    private final UserDao userDao;
    private final Scanner scanner;

    public PostService(Scanner scanner) {
        postDao = new PostDao();
        userDao = new UserDao();
        this.scanner = scanner;
    }


    public void create() {
        System.out.println("Введите id пользователя");
        int id = Integer.parseInt(scanner.nextLine());

        User user = userDao.findById(id);
        if (user == null) {
            System.out.println("Пользователь с id=" + id + " не найден!");
            return;
        }

        System.out.println("Введите заголовок поста");
        String title = scanner.nextLine();

        System.out.println("Введите описание поста");
        String description = scanner.nextLine();


        Post post = Post.builder()
                .title(title)
                .subtitle(description)
                .createdAt(LocalDateTime.now())
                .build();

        postDao.create(post);

    }
}
