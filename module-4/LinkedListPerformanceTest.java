/*
 * Assignment: LinkedList Performance Test
 * Name: Naz Knuckles
 * Date: October 5, 2026
 *
 * Description:
 * This program tests the performance of traversing a LinkedList
 * containing 50,000 and 500,000 integers. It compares two
 * different traversal techniques:
 *
 * 1. Using an Iterator
 * 2. Using the get(index) method
 *
 * The program also performs correctness tests by calculating the
 * sum of all integers using both traversal methods and comparing
 * the results to the mathematically expected sum.
 *
 * Results and Discussion:
 *
 * The Iterator approach should be significantly faster than the
 * get(index) approach when traversing a LinkedList. An Iterator
 * moves sequentially from one node to the next, making traversal
 * approximately O(n).
 *
 * The get(index) method is much slower for a LinkedList because
 * LinkedList does not provide constant-time random access. Each
 * call to get(index) requires the program to traverse the list
 * until it reaches the requested node. Repeating get(index) for
 * every element causes the overall operation to be approximately
 * O(n^2).
 *
 * The difference becomes much more noticeable when the list grows
 * from 50,000 to 500,000 elements. Although the list is only ten
 * times larger, the amount of work required by repeated get(index)
 * operations increases much more rapidly because each access may
 * require traversing a large portion of the list.
 *
 * Therefore, an Iterator is the preferred approach when sequentially
 * traversing a LinkedList. The get(index) method is better suited
 * for collections that provide efficient random access, such as
 * ArrayList.
 *
 * The correctness tests ensure that both traversal methods process
 * every element correctly by comparing their calculated sums with
 * the mathematically expected sum.
 */

import java.util.Iterator;
import java.util.LinkedList;

public class LinkedListPerformanceTest {

    /**
     * Main method.
     *
     * Tests LinkedLists containing 50,000 and 500,000 integers.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {

        // Test the required list sizes.
        testPerformance(50_000);
        testPerformance(500_000);
    }

    /**
     * Creates a LinkedList containing the specified number of integers
     * and compares traversal using an Iterator with traversal using
     * the get(index) method.
     *
     * @param size the number of integers to store in the LinkedList
     */
    public static void testPerformance(int size) {

        // Create a LinkedList to store the integers.
        LinkedList<Integer> list = new LinkedList<>();

        // Add integers from 0 through size - 1.
        for (int i = 0; i < size; i++) {
            list.add(i);
        }

        System.out.println();
        System.out.println("======================================");
        System.out.println("Testing " + size + " integers");
        System.out.println("======================================");

        /*
         * Calculate the expected sum.
         *
         * The list contains:
         * 0 + 1 + 2 + ... + (size - 1)
         *
         * The formula for this sum is:
         * n(n - 1) / 2
         */
        long expectedSum = ((long) size * (size - 1)) / 2;

        /*
         * ----------------------------------------------------------
         * Test 1: Traversal using an Iterator
         * ----------------------------------------------------------
         */
        long startTime = System.nanoTime();

        long iteratorSum = 0;

        Iterator<Integer> iterator = list.iterator();

        while (iterator.hasNext()) {
            iteratorSum += iterator.next();
        }

        long endTime = System.nanoTime();

        long iteratorTime = endTime - startTime;

        /*
         * ----------------------------------------------------------
         * Test 2: Traversal using get(index)
         * ----------------------------------------------------------
         */
        startTime = System.nanoTime();

        long getSum = 0;

        for (int i = 0; i < list.size(); i++) {
            getSum += list.get(i);
        }

        endTime = System.nanoTime();

        long getTime = endTime - startTime;

        /*
         * ----------------------------------------------------------
         * Correctness Tests
         * ----------------------------------------------------------
         *
         * Both traversal methods should calculate the same sum as
         * the mathematically expected value.
         */
        boolean iteratorCorrect = iteratorSum == expectedSum;
        boolean getCorrect = getSum == expectedSum;

        /*
         * ----------------------------------------------------------
         * Display Results
         * ----------------------------------------------------------
         */
        System.out.println("Expected sum: " + expectedSum);
        System.out.println("Iterator sum: " + iteratorSum);
        System.out.println("get(index) sum: " + getSum);

        System.out.println();
        System.out.println("Correctness Tests:");
        System.out.println("------------------");
        System.out.println("Iterator correct: " + iteratorCorrect);
        System.out.println("get(index) correct: " + getCorrect);

        System.out.println();
        System.out.println("Performance Results:");
        System.out.println("--------------------");

        System.out.printf(
                "Iterator time: %.3f ms%n",
                iteratorTime / 1_000_000.0
        );

        System.out.printf(
                "get(index) time: %.3f ms%n",
                getTime / 1_000_000.0
        );

        /*
         * Calculate how many times slower get(index) was compared
         * with the Iterator.
         */
        if (iteratorTime > 0) {
            double ratio = (double) getTime / iteratorTime;

            System.out.printf(
                    "get(index) was %.2f times slower than Iterator.%n",
                    ratio
            );
        }

        /*
         * Final correctness status.
         */
        System.out.println();

        if (iteratorCorrect && getCorrect) {
            System.out.println("PASS: Both traversal methods produced "
                    + "the correct result.");
        } else {
            System.out.println("FAIL: One or more correctness tests failed.");
        }
    }
}