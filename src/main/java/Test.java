import javax.persistence.*;
import java.util.List;

public class Test {
    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager manager = factory.createEntityManager();

        // Достать пользователя по имени
        TypedQuery<User> query = manager.createQuery("select u from User u where name = ?1", User.class);
        query.setParameter(1, "имя");
        User userByName = query.getSingleResult();

        // 1. Достать пользователя по id
        User user = manager.find(User.class, 1);

        // 2. Достать всех пользователей
        List<User> users = manager.createQuery("select u from User u", User.class).getResultList();

        // 3. Создать пользователя
        try {
            manager.getTransaction().begin();

            User newUser = new User();
            newUser.setName("userFromJPA");
            newUser.setEmail("userFromJPA@gmail.com");
            newUser.setAge(22);

            manager.persist(newUser);
            manager.getTransaction().commit();
            System.out.println("Пользователь был создан");
        } catch (Exception e) {
            manager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }


        // 4. Обновить данные пользователя
        try {
            manager.getTransaction().begin();

            User updateUser = manager.find(User.class, 2);
            updateUser.setAge(user.getAge() + 1);
            manager.merge(updateUser);

            manager.getTransaction().commit();

            System.out.println("Пользователь был обновлен");
        } catch (Exception e) {
            manager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }

        // 5. Удалить пользователя
        try {
            manager.getTransaction().begin();

            User deleteUser = manager.find(User.class, 2);
            manager.remove(deleteUser);
            manager.getTransaction().commit();

            System.out.println("Пользователь был удален");
        } catch (Exception e) {
            manager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }


        // 1. Достать пользователя по id
        // 2. Достать всех пользователей
        // 3. Создать пользователя
        // 4. Обновить данные пользователя
        // 5. Удалить пользователя

        // CRUD

        // select * from users
        // select u from User u

    }
}
