import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class WordProcessor {

    /**
     * Reads words from a text file, removes duplicates (case-insensitive),
     * and returns them sorted in ascending and descending order.
     */
    public static Map<String, List<String>> processWords(String filename) throws IOException {
        Path path = Paths.get(filename);
        if (!Files.exists(path)) {
            throw new IOException("The file '" + filename + "' was not found.");
        }

        // TreeSet automatically stores unique elements and sorts them in ascending order
        Set<String> uniqueWords = new TreeSet<>();

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split line by whitespace
                String[] words = line.split("\\s+");
                for (String word : words) {
                    // Normalize casing and clean whitespace to handle duplicates accurately
                    String cleanedWord = word.trim().toLowerCase();
                    if (!cleanedWord.isEmpty()) {
                        uniqueWords.add(cleanedWord);
                    }
                }
            }
        }

        List<String> ascendingList = new ArrayList<>(uniqueWords);
        List<String> descendingList = new ArrayList<>(ascendingList);
        Collections.reverse(descendingList);

        Map<String, List<String>> result = new HashMap<>();
        result.put("ascending", ascendingList);
        result.put("descending", descendingList);

        return result;
    }

    // --- Test Code ---
    public static void runTests() {
        System.out.println("=== Running Unit Tests ===");
        String testFilename = "test_collection.txt";
        try {
            // Create a temporary test file with known data
            List<String> testLines = Arrays.asList(
                "apple banana apple cherry",
                "Banana date elderberry"
            );
            Files.write(Paths.get(testFilename), testLines);

            // Execute logic
            Map<String, List<String>> results = processWords(testFilename);
            List<String> asc = results.get("ascending");
            List<String> desc = results.get("descending");

            List<String> expectedAsc = Arrays.asList("apple", "banana", "cherry", "date", "elderberry");
            List<String> expectedDesc = Arrays.asList("elderberry", "date", "cherry", "banana", "apple");

            // Verify assertions
            if (asc.equals(expectedAsc) && desc.equals(expectedDesc)) {
                System.out.println("Tests passed successfully!\n");
            } else {
                System.err.println("Test failed: Sorting or deduplication mismatch.");
            }

        } catch (IOException e) {
            System.err.println("Test failed due to IO error: " + e.getMessage());
        } finally {
            // Clean up test file
            try {
                Files.deleteIfExists(Paths.get(testFilename));
            } catch (IOException ignored) {}
        }
    }

    public static void main(String[] args) {
        // Run test code to ensure functionality before executing main task
        runTests();

        // Main execution file requirement
        String targetFile = "collection_of_words.txt";
        try {
            Path targetPath = Paths.get(targetFile);
            
            // Automatically generate collection_of_words.txt if it doesn't exist
            if (!Files.exists(targetPath)) {
                List<String> sampleContent = Arrays.asList(
                    "java programming code test apple banana java",
                    "development software engineer code database apple"
                );
                Files.write(targetPath, sampleContent);
                System.out.println("Created sample '" + targetFile + "' for execution.");
            }

            System.out.println("Reading words from '" + targetFile + "'...");
            Map<String, List<String>> results = processWords(targetFile);

            System.out.println("\nNon-duplicate words in Ascending Order:");
            System.out.println(results.get("ascending"));

            System.out.println("\nNon-duplicate words in Descending Order:");
            System.out.println(results.get("descending"));

        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}