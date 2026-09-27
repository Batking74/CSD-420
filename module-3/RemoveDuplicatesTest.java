import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Random;

/*
 * Name: Nazir Knuckles
 * Date: September 27, 2026
 * Assignment: 3 - CSD 420 - Remove Duplicates from an ArrayList
 * Purpose: Generate 50 random integers, remove duplicates with a generic static method,
 *          and display the original and duplicate-free lists.
 * Source: Original assignment solution; AI assistance used to draft this code.
 */

public class RemoveDuplicatesTest {

    public static void main(String[] args) {
        // Create and fill the original list with 50 random integers from 1 through 20.
        ArrayList<Integer> originalList = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 50; i++) {
            originalList.add(random.nextInt(20) + 1);
        }

        // Create a separate list containing each value only once.
        ArrayList<Integer> uniqueList = removeDuplicates(originalList);

        // Display both lists so the result can be checked.
        System.out.println("Original list (50 random values from 1 to 20):");
        System.out.println(originalList);
        System.out.println();

        System.out.println("List after removing duplicates:");
        System.out.println(uniqueList);
        System.out.println();

        System.out.println("Original list size: " + originalList.size());
        System.out.println("List size after removing duplicates: " + uniqueList.size());
    }

    /**
     * Returns a new ArrayList containing the original list's values without duplicates.
     * The first occurrence of each value is retained, preserving original order.
     *
     * @param <E>  the element type
     * @param list the list from which duplicates will be removed
     * @return a new ArrayList containing only unique values
     */
    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {
        // LinkedHashSet removes duplicates while preserving insertion order.
        return new ArrayList<>(new LinkedHashSet<>(list));
    }
}
