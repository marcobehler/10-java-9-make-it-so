package org.example;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Stream;

/**
 * Thanks for watching this episode! Send any feedback to info@marcobehler.com!
 */
public class Java9Changes implements Womanizer{

    public static void main(String[] args) {

        // 1

        List<Integer> intList = Arrays.asList(1, 2, 3, 4);

        List.of(1,2,3,4);
        Set.of(1,2,3,4); // -> exception!
        Map.of("John", "Snow", "Tyrion", "Lannister"); // a-> b

        // 2

        Optional<String> customer = Optional.of("John Snow");
        customer.ifPresentOrElse(
                c -> System.out.println("Send email to customer -> " + c), // if value
                () -> System.out.println("Alarm, alarm!")  // empty!
        );

        // customer.stream().map( )


        // 3

        Connection connection = null; // get it somewhere
        try (connection){
                // do something
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 4

        TextField<Integer> intTextField = new TextField<>(42) {

        };

        // 5
        // private interface methods


        // 6

        Stream.of("a", "b", "c", "de", "fg").takeWhile(s -> s.length() == 1).forEach(System.out::print);
        System.out.println("-----");
        Stream.of("a", "b", "c", "de", "fg").dropWhile(s -> s.length() == 1).forEach(System.out::print);

    }

    public static abstract class TextField<T> {

        private T t;

        public TextField(T t) {
            this.t = t;
        }
    }
}