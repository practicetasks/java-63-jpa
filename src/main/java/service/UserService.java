package service;

import dao.UserDao;
import lombok.Getter;
import model.User;

import java.util.Scanner;

@Getter
public class UserService {
    private final Scanner scanner;

    public UserService(Scanner scanner) {
        this.scanner = scanner;
    }

    public void findById() {
        try (UserDao dao = new UserDao()) {
            int id = Integer.parseInt(scanner.nextLine());
            User user = dao.findById(id);
            if (user == null) {
                System.out.println("not found");
            }

            System.out.println(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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

//        userDao.create(user);

    }


}


