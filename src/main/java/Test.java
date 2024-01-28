import service.PostService;

import java.util.Scanner;

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
        PostService service = new PostService(new Scanner(System.in));

        service.create();


//        Post post = postDao.getPost();
//
//        UserDao userDao = new UserDao();
//
//        User user = userDao.findById(1);
//        System.out.println(user);
//
//        LocalDateTime now = LocalDateTime.now();
//
//
//        Duration duration = Duration.between(now, post.getCreatedAt());
//        Period period = Period.between(now.toLocalDate(), post.getCreatedAt().toLocalDate());
//
//        System.out.println(duration);
//        System.out.println(period);

    }

    private static void menu() {
        System.out.println("1. Создать");
        System.out.println("...");
    }
}
