import java.util.List;

public class StreamTest {

    static List<Item> items = List.of(
            new Item(1, "item1"),
            new Item(2, "item2"),
            new Item(3, "item3"),
            new Item(4, "item4"),
            new Item(5, "item5")
    );

    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

//        List<Integer> filteredNumbers = new ArrayList<>();
//        for (Integer number : numbers) {
//            if (number >= 4) {
//                filteredNumbers.add(number);
//            }
//        }

//        List<Integer> filteredNumbers = numbers.stream()
//                .filter(x -> x >= 4)
//                .toList();
//
//        System.out.println(filteredNumbers);


        try {
            Item item = findById(8);
            System.out.println(item);
        } catch (NotFoundByIdException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Item findById(int id) {
        return items.stream()
                .filter(item -> item.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NotFoundByIdException("Item с id=" + id + " не найден"));
    }
}

class Item {
    private int id;
    private String name;

    public Item(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class NotFoundByIdException extends RuntimeException {
    public NotFoundByIdException(String message) {
        super(message);
    }
}
