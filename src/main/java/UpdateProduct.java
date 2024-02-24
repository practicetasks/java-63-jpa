import model.Category;
import model.Option;
import model.Product;
import model.Value;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
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

            Category category = product.getCategory();
            List<Option> options = category.getOptions();

            for (Option option : options) {
                TypedQuery<Value> query = manager
                        .createQuery("select v from Value v where v.option = :option and v.product = :product", Value.class)
                        .setParameter("option", option)
                        .setParameter("product", product);

                Value value = query
                        .getResultStream()
                        .findFirst()
                        .orElse(null);

                if (value == null) {
                    System.out.printf("%s [null]: ", option.getName());
                    String valueName = scanner.nextLine();
                    value = new Value();
                    value.setProduct(product);
                    value.setOption(option);
                    value.setName(valueName);
                    manager.persist(value);
                    continue;
                }

                System.out.printf("%s [%s]: ", option.getName(), value.getName());
                String valueName = scanner.nextLine();

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
