import model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class CreateUser {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager manager = factory.createEntityManager();

        System.out.println("Введите имя пользователя");
        String name = scanner.nextLine();

        System.out.println("Введите почту");
        String email = scanner.nextLine();

        System.out.println("Введите возраст");
        int age = Integer.parseInt(scanner.nextLine());


        User user = User.builder()
                .name(name)
                .email(email)
                .age(age)
                .build();

        try {
            manager.getTransaction().begin();
            manager.persist(user);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }
}
