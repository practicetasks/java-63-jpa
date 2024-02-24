import model.Category;
import model.Option;
import model.Product;
import model.Value;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class CreateProduct {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager manager = factory.createEntityManager();

        List<Category> categories = manager.createQuery("select c from Category c", Category.class)
                .getResultList();

        for (Category category : categories) {
            System.out.printf("%d. %s\n", category.getId(), category.getName());
        }

        System.out.println("Выберите категорию");
        int categoryId = Integer.parseInt(scanner.nextLine());

        Category productCategory = manager.find(Category.class, categoryId);

        if (productCategory == null) {
            System.out.println("Несущ id");
            return;
        }

        System.out.println("Название товара");
        String name = scanner.nextLine();

        System.out.println("Стоимость товара");
        Double price = Double.parseDouble(scanner.nextLine());

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setCategory(productCategory);

        try {
            manager.getTransaction().begin();
            manager.persist(product);

            for (Option option : productCategory.getOptions()) {
                System.out.printf("%s: ", option.getName());
                String valueName = scanner.nextLine();
                Value value = new Value();
                value.setName(valueName);
                value.setProduct(product);
                value.setOption(option);
                manager.persist(value);
            }

            manager.getTransaction().commit();
        } catch (Exception exception) {
            manager.getTransaction().rollback();
            System.out.println(exception.getMessage());
        }
    }
}
