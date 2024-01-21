import service.UserService;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserService service = new UserService(scanner);

        while (true) {
            menu();
            int command = Integer.parseInt(scanner.nextLine());
            switch (command) {
                case 0 -> {
                    return;
                }
                case 1 -> service.create();
            }
        }
    }

    private static void menu() {
        System.out.println("1. Создать");
        System.out.println("...");
    }
}
