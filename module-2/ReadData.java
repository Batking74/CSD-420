import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadData {
    public static void main(String[] args) {
        String fileName = "Nazirdatafile.dat";

        try (DataInputStream input = new DataInputStream(
                new FileInputStream(fileName))) {

            int recordNumber = 1;

            while (true) {
                try {
                    // Read the first integer to determine if another record exists
                    int firstInteger = input.readInt();

                    System.out.println("\nRecord " + recordNumber);

                    System.out.print("Integers: ");
                    System.out.print(firstInteger + " ");

                    // Read the remaining four integers
                    for (int i = 1; i < 5; i++) {
                        System.out.print(input.readInt() + " ");
                    }

                    System.out.print("\nDoubles: ");

                    // Read five doubles
                    for (int i = 0; i < 5; i++) {
                        System.out.printf("%.2f ", input.readDouble());
                    }

                    System.out.println();

                    recordNumber++;

                } catch (EOFException e) {
                    // Normal end of file
                    break;
                }
            }

            System.out.println("\nFinished reading " + fileName);

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}