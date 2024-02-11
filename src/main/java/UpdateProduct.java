import model.Option;
import model.Product;
import model.Value;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class UpdateProduct {
public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
    EntityManager manager = factory.createEntityManager();


    System.out.println("Введите id товара");
    long id = Long.parseLong(scanner.nextLine());

    Product product = manager.find(Product.class, id);

    if (product == null) {
        System.out.printf("Товар с id=%d не сущ", id);
        return;
    }

    System.out.printf("Введите новое название товара [%s]: ", product.getName());
    String name = scanner.nextLine();

    if (!name.isBlank()) {
        product.setName(name);
    }

    System.out.printf("Введите новую стоимость товара [%s]: ", product.getPrice());
    try {
        Double price = Double.parseDouble(scanner.nextLine());
        product.setPrice(price);
    } catch (NumberFormatException ignored) {
    }

    try {
        manager.getTransaction().begin();
        manager.persist(product);

        for (Value value : product.getValues()) {
            Option option = value.getOption();
            System.out.printf("%s [%s]: ", option.getName(), value.getName());
            String valueName = scanner.nextLine();

            if (valueName.isBlank()) {
                continue;
            }

            value.setName(valueName);
            manager.persist(value);
        }

        manager.getTransaction().commit();
    } catch (Exception exception) {
        manager.getTransaction().rollback();
        System.out.println(exception.getMessage());
    }
}
}
