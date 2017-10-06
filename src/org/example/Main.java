package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        // 1. collection factory methods
        System.out.println( Arrays.asList(1, 2, 3, 4));

        System.out.println( List.of(1 , 2, 3, 4));
        System.out.println( Map.of("hello", "world", "what", "is")); //feels a bit clumsy
        System.out.println( Set.of(1, 1,1, 2, 3)); // what happens here?


        // 2. optionals

        Optional<String> someCustomer = Optional.of("some customer");

        someCustomer.ifPresentOrElse(
                c -> System.out.println("Send email to cusotmer"),
                () -> System.out.println("Send alarm to ops guys, do something entirely different"));

        Optional.of(1).map(x -> x * 2);
        Optional.of(1).stream().map(x -> x *3);


        Connection connection = null;

        try (connection) {

        } catch (SQLException e) {
            e.printStackTrace();
        }

        /* 3. try-with improvements

        try (Connection connection = DriverManager.getConnection("jdbc:some:url")) {

            try (connection) {
                System.out.println(connection.isValid(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        InputStream inputStream = Files.newInputStream(Paths.get("c:\\some\\path"));
        try {
            inputStream.available();
        } catch (IOException e) {
            e.printStackTrace();
        }*/


        // 4. diamond operator..wicket!!!

        TextField<Integer> integerTextField = new TextField<>(20) {

        };



        // 6


        Stream.of("a", "b", "c", "de", "f")
                .takeWhile(s -> s.length() == 1)
                .forEach(System.out::print);


    }


   /// 4
    public static abstract class TextField<T> {
        private T t;

        public TextField(T t) {
            this.t = t;
        }
    }

    // 5 Private methods on interfaces

     public interface Womanizer {

        public default void womanize() {
            System.out.println("My name's Bond!");
            bondPeacock();
        }

        private void bondPeacock() {
            System.out.println("I am the greatest, lad-eeeh!");
        }
    }
}
