import dao.PostDao;
import dao.UserDao;
import model.Post;
import model.User;

public class Test {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        UserService service = new UserService(scanner);
//
//        while (true) {
//            menu();
//            int command = Integer.parseInt(scanner.nextLine());
//            switch (command) {
//                case 0 -> {
//                    return;
//                }
//                case 1 -> service.create();
//            }
//        }
//    }

    public static void main(String[] args) {
        PostDao postDao = new PostDao();


        Post post = postDao.getPost();

        UserDao userDao = new UserDao();

        User user = userDao.findById(1);
        System.out.println(user);

    }

    private static void menu() {
        System.out.println("1. Создать");
        System.out.println("...");
    }
}
