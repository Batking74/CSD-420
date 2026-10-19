import java.util.Comparator;
import java.util.Arrays;

/**
 * Provides generic bubble sort methods for arrays.
 *
 * The first method uses each element's natural ordering through Comparable.
 * The second method uses a Comparator to define a custom ordering.
 *
 * @author Student
 * @version 1.0
 */
public class Bubble_Sort {

    /**
     * Sorts an array into ascending natural order using bubble sort.
     *
     * @param <T> the element type, which must implement Comparable
     * @param array the array to sort
     * @throws IllegalArgumentException if array is null
     */
    public static <T extends Comparable<? super T>> void bubbleSort(T[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Array cannot be null.");
        }

        // Each pass moves the largest unsorted element to the end.
        for (int pass = 0; pass < array.length - 1; pass++) {
            boolean swapped = false;

            for (int i = 0; i < array.length - 1 - pass; i++) {
                if (array[i].compareTo(array[i + 1]) > 0) {
                    T temporary = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temporary;
                    swapped = true;
                }
            }

            // If no elements were swapped, the array is already sorted.
            if (!swapped) {
                break;
            }
        }
    }

    /**
     * Sorts an array using the ordering defined by the supplied Comparator.
     *
     * @param <T> the element type
     * @param array the array to sort
     * @param comparator the Comparator that defines element ordering
     * @throws IllegalArgumentException if array or comparator is null
     */
    public static <T> void bubbleSort(T[] array, Comparator<? super T> comparator) {
        if (array == null) {
            throw new IllegalArgumentException("Array cannot be null.");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null.");
        }

        for (int pass = 0; pass < array.length - 1; pass++) {
            boolean swapped = false;

            for (int i = 0; i < array.length - 1 - pass; i++) {
                if (comparator.compare(array[i], array[i + 1]) > 0) {
                    T temporary = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temporary;
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }
    }

    /**
     * Runs demonstration tests for both bubble sort methods.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Test 1: Comparable natural ordering with integers.
        Integer[] numbers = {5, 1, 4, 2, 8};
        bubbleSort(numbers);
        assertArrayEquals(new Integer[]{1, 2, 4, 5, 8}, numbers,
                "Comparable sort with integers");

        // Test 2: Comparable natural ordering with strings.
        String[] words = {"pear", "apple", "orange", "banana"};
        bubbleSort(words);
        assertArrayEquals(new String[]{"apple", "banana", "orange", "pear"}, words,
                "Comparable sort with strings");

        // Test 3: Comparator custom ordering, descending integers.
        Integer[] descendingNumbers = {3, 9, 1, 7, 5};
        bubbleSort(descendingNumbers, Comparator.reverseOrder());
        assertArrayEquals(new Integer[]{9, 7, 5, 3, 1}, descendingNumbers,
                "Comparator sort in descending order");

        // Test 4: Empty array.
        Integer[] empty = {};
        bubbleSort(empty);
        assertArrayEquals(new Integer[]{}, empty, "Empty array");

        // Test 5: Already sorted array.
        Integer[] sorted = {1, 2, 3, 4, 5};
        bubbleSort(sorted);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, sorted,
                "Already sorted array");

        // Test 6: Duplicate values.
        Integer[] duplicates = {4, 2, 4, 1, 2};
        bubbleSort(duplicates);
        assertArrayEquals(new Integer[]{1, 2, 2, 4, 4}, duplicates,
                "Array containing duplicates");

        System.out.println("All bubble sort tests passed.");
    }

    /**
     * Compares an actual array with an expected array and throws an
     * AssertionError if the arrays differ.
     *
     * @param expected the expected sorted contents
     * @param actual the actual array contents
     * @param testName description of the test
     */
    private static <T> void assertArrayEquals(T[] expected, T[] actual,
                                              String testName) {
        if (!Arrays.equals(expected, actual)) {
            throw new AssertionError(
                    testName + " failed. Expected " + Arrays.toString(expected)
                    + " but got " + Arrays.toString(actual));
        }
        System.out.println("PASS: " + testName);
    }
}
