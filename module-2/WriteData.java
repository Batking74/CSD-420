import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class WriteData {
    public static void main(String[] args) {
        String fileName = "Nazirdatafile.dat";
        Random random = new Random();

        // Create arrays of five random integers and five random doubles
        int[] integers = new int[5];
        double[] doubles = new double[5];

        for (int i = 0; i < 5; i++) {
            integers[i] = random.nextInt(100);       // 0-99
            doubles[i] = random.nextDouble() * 100;  // 0.0-100.0
        }

        // Open the file in append mode
        try (DataOutputStream output = new DataOutputStream(
                new FileOutputStream(fileName, true))) {

            // Write the five integers
            for (int value : integers) {
                output.writeInt(value);
            }

            // Write the five doubles
            for (double value : doubles) {
                output.writeDouble(value);
            }

            System.out.println("Data successfully written to " + fileName);

        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}