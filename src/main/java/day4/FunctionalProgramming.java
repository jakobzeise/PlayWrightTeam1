package day4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class FunctionalProgramming {

    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);


        //filter this collection to only get the even ones
        List<Integer> list = numbers.stream().filter(integer -> integer % 2 == 0).toList();

        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Anna");


        // A method that takes a method as an argument
        List<String> upperCaseNames = names.stream().map(s -> s.toUpperCase()).toList();

//        List<String> upperCaseNames = new ArrayList<>();
//        for (String name: names ) {
//            upperCaseNames.add(name.toUpperCase());
//        }
//        System.out.println(upperCaseNames);

        // anonymous method

        upperCaseNames.stream().forEach(s -> {
            System.out.println(s.repeat(2));
        });

        // Supplier (takes no arguments and returns a value
        List<Integer> values = Stream.generate(() -> new Random().nextInt(100))
                .limit(100)
                .toList();

        System.out.println(values);

        values = values.stream()
                .filter(integer -> integer % 5 == 0)
                .filter(integer -> integer > 50)
                .toList();

        System.out.println(values);

        List<String> names1 = Arrays.asList("Alice", "Bob", "Charlie", "David", "Anna");

        names1 = names1.stream().filter(s -> s.toUpperCase().startsWith("A")).toList();
        System.out.println("names1 = " + names1);

        names1.stream().forEach(s -> {
            System.out.println(s);
        });

        List<String> names2= Arrays.asList("Alice", "Bob", "Charlie", "David", "Anna");
        names2.forEach(System.out::println);

        names2 = names2.stream().map(FunctionalProgramming::test).toList();


        String s = "Hello";

        // map
        List<String> strings = Arrays.asList("1", "2", "3", "4", "5");
        List<Integer> integers = strings.stream().map(Integer::valueOf).toList();

        integers.forEach(FunctionalProgramming::squareNumber);
        integers.forEach(number -> System.out.println(number * number));

    }

    public static void squareNumber(int number) {
        System.out.println(number * number);
    }

    public static String test(String input) {
        return input.toUpperCase();
    }




}
