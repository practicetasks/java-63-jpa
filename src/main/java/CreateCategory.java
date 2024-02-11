import model.Category;
import model.Option;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class CreateCategory {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager manager = factory.createEntityManager();

        System.out.println("Введите название категорий");
        String name = scanner.nextLine();
        if (name.isBlank()) {
            return;
        }

        Category category = manager
                .createQuery("select c from Category c where c.name = :name", Category.class)
                .setParameter("name", name)
                .getResultStream()
                .findFirst()
                .orElse(null);

        if (category != null) {
            System.out.println("Категория уже существует");
            return;
        }

        category = new Category();
        category.setName(name);

        try {
            manager.getTransaction().begin();
            manager.persist(category);

            System.out.println("Введите характеристики (через запятую и пробел \", \")");
            String[] input = scanner.nextLine().split(", ");
            for (String optionName : input) {
                Option option = new Option();
                option.setName(optionName);
                option.setCategory(category);
                manager.persist(option);
            }

            manager.getTransaction().commit();
        } catch (Exception exception) {
            manager.getTransaction().rollback();
            System.out.println(exception.getMessage());
        }


    }
}

