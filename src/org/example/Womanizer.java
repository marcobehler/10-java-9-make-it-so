package org.example;

/**
 * Thanks for watching this episode! Send any feedback to info@marcobehler.com!
 */
public interface Womanizer {

    public default void womanize() {
        System.out.println("Hi, my name is Bond!");
        doBondPeacock();
    }

    private void doBondPeacock() {
        System.out.println("I am the greatest!");
    }
}
