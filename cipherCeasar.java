import java.io.*;
import java.util.Scanner;

public class CaesarCipherFile {

    // Encrypt function
    public static String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();

        for (char character : text.toCharArray()) {
            if (Character.isUpperCase(character)) {
                char ch = (char) (((character - 'A' + shift) % 26) + 'A');
                result.append(ch);
            } else if (Character.isLowerCase(character)) {
                char ch = (char) (((character - 'a' + shift) % 26) + 'a');
                result.append(ch);
            } else {
                // Non-alphabetic characters remain unchanged
                result.append(character);
            }
        }
        return result.toString();
    }

    // Decrypt function
    public static String decrypt(String text, int shift) {
        return encrypt(text, 26 - (shift % 26));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Input file
            System.out.print("Enter input file path: ");
            String inputFile = scanner.nextLine();

            // Output files
            String encryptedFile = "encrypted.txt";
            String decryptedFile = "decrypted.txt";

            System.out.print("Enter shift key (number): ");
            int shift = scanner.nextInt();
            scanner.nextLine(); // consume newline

            // Read input text from file
            StringBuilder inputText = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    inputText.append(line).append("\n");
                }
            }

            // Encrypt
            String encryptedText = encrypt(inputText.toString(), shift);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(encryptedFile))) {
                writer.write(encryptedText);
            }
            System.out.println("Encrypted text saved to " + encryptedFile);

            // Decrypt
            String decryptedText = decrypt(encryptedText, shift);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(decryptedFile))) {
                writer.write(decryptedText);
            }
            System.out.println("Decrypted text saved to " + decryptedFile);

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

        scanner.close();
    }
}
