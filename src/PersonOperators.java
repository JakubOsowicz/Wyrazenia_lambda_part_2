import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class PersonOperators {
    public static void main(String[] args) {
        String[] firstNames = {"Jan", "Karol", "Piotr", "Robert"};
        String[] lastNames = {"Abacki", "Kowalski", "Zalewski", "Korzeniowski"};
        int[] ages = {22, 33, 44, 55};
        Random random = new Random();
        Supplier<Person> supplier = () -> {
            String firstName = firstNames[random.nextInt(firstNames.length)];
            String lastName = lastNames[random.nextInt(lastNames.length)];
            int age = ages[random.nextInt(ages.length)];
            return new Person(firstName, lastName, age);
        };

        List<Person> people = generateRandomList(5, supplier);
//        List<Person> people = new ArrayList<>();
//        people.add(new Person("Jan", "Kowalski", 42));
//        people.add(new Person("Kasia", "Kruczkowska", 22));
//        people.add(new Person("Piotr", "Adamiak", 15));
//        people.add(new Person("Jan", "Zawadzki", 17));
//        people.add(new Person("Krzysztof", "Wójtyniak", 16));
//        people.add(new Person("Agnieszka", "Zagumna", 18));
//        people.add(new Person("Basia", "Cyniczna", 28));

        System.out.println("People");
        consumeList(people, p -> System.out.println(p));
        System.out.println("People + 1");
        consumeList(people, p -> p.setAge(p.getAge() + 1));
        consumeList(people, p -> System.out.println(p));
        System.out.println("Adults");
        List<Person> adults = filterByPredicate(people, p -> p.getAge() >= 18);
        consumeList(adults, p -> System.out.println(p));
        System.out.println("Jany");
        List<Person> janList = filterByPredicate(people, p -> p.getFirstName().equals("Jan"));
        consumeList(janList, p -> System.out.println(p));
        System.out.println("imiona");
        List<String> firstNames1 = convertList(people, p -> p.getFirstName());
        consumeList(firstNames1, p -> System.out.println(p));
    }

    private static <T, R> List<R> convertList(List<T> list, Function<T, R> function){
        List<R> result= new ArrayList<>();
        for (T t : list) {
            R apply = function.apply(t);
            result.add(apply);
        }
        return result;
    }

    private static <T> List<T> filterByPredicate(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (predicate.test(t)){
                result.add(t);
            }
        }
        return result;
    }

    private static <T> void consumeList(List<T> list, Consumer<T> consumer){
        // Person p -> void
        for (T t : list) {
            consumer.accept(t);
        }
    }

    private static <T> List<T> generateRandomList(int elements, Supplier<T> supplier){
        List<T> resultList = new ArrayList<>();
        for (int i = 0; i < elements; i++) {
            resultList.add(supplier.get());
        }
        return resultList;
    }
}
