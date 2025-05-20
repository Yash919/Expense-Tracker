import java.io.*;
import java.util.Map;

public class FileHandler {
    public static void saveAllUsersToFile(Map<String, User> users, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (User user : users.values()) {
                for (Transaction t : user.getTransactions()) {
                    writer.write(user.getUsername() + "," + t);
                    writer.newLine();
                }
            }
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }

    public static void loadFromFile(String filename, Map<String, User> userMap) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                System.out.println("Reading line " + lineNumber + ": " + line);
                try {
                    String[] parts = line.split(",", 2);
                    if (parts.length < 2) {
                        throw new IllegalArgumentException("Invalid line format");
                    }

                    String username = parts[0].trim();
                    String transactionData = parts[1].trim();

                    // Debug print
                    System.out.println("Parsed username: " + username);
                    System.out.println("Parsed transaction data: " + transactionData);

                    Transaction transaction = Transaction.fromString(transactionData);
                    userMap.computeIfAbsent(username, User::new).addTransaction(transaction);
                } catch (IllegalArgumentException e) {
                    System.out.println("Line " + lineNumber + " skipped: " + e.getMessage());
                }
            }
            System.out.println("Data loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }


}
