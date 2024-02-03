import model.Post;
import model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.Scanner;

public class CreatePost {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager manager = factory.createEntityManager();

        int id = getInt("Введите id пользователя");
        User user = manager.find(User.class, id);

        if (user == null) {
            System.out.printf("Пользователь с id=%d не найден\n", id);
            return;
        }

        String title = getString("Введите заголовок");
        String subtitle = getString("Введите подзаголовок");

        Post post = Post.builder()
                .title(title)
                .subtitle(subtitle)
                .user(user)
                .createdAt(LocalDateTime.now())
                .build();


        try {
            manager.getTransaction().begin();
            manager.persist(post);
            manager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            manager.getTransaction().rollback();
        }


        // CreateCategory
        // - необходимо добавить новую категорию, при этом проверяем существует ли эта категория
        // - название категорий не может быть пустым


        // CreateOption
        // - создать несколько характеристик для категорий
        // - проверить существует ли характеристика с таким наименованием

        // Введите id категорий: ___
        // Введите название характеристики: ___
        // Сообщение "добавлена"

        // Введите название характеристики:


        // CreateProduct
        // - вывод всех категорий
        //    1. Ноутбуки
        //    2. Смартфоны
        //    3. ...
        // - выберите категорию: 1

        // - название товара
        // - стоимость товара
        // - ЗАПОЛНЕНИЕ ХАРАКТЕРИСТИК
        // - диагональ: ___
        // - производитель: ___
        // - озу: ___

        // Товар добавлен


        // Ноутбуки
        // - диагональ
        // - производитель
        // - озу

    }


    static String getString(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

    static int getInt(String message) {
        System.out.println(message);
        return Integer.parseInt(scanner.nextLine());
    }
}
