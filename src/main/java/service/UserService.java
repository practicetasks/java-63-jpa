package service;

import dao.UserDao;
import lombok.Getter;
import model.User;

import java.util.Scanner;

@Getter
public class UserService {
    private final UserDao userDao;
    private final Scanner scanner;

    public UserService(Scanner scanner) {
        this.userDao = new UserDao();
        this.scanner = scanner;
    }

    public void findById() {
        int id = Integer.parseInt(scanner.nextLine());
        User user = userDao.findById(id);
        if (user == null) {
            return;
        }

        System.out.println(user);
    }

    public void create() {
        System.out.println("Введите имя пользователя:");
        String name = scanner.nextLine();

        System.out.println("Введите электронную почту:");
        String email = scanner.nextLine();

        System.out.println("Введите возраст:");
        int age = Integer.parseInt(scanner.nextLine());

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setAge(age);

        userDao.create(user);

    }


}


