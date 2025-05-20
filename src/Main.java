import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ExpenseTracker tracker = new ExpenseTracker();

        while (true) {
            System.out.println("\n-------- Tracker Menu ----------");
            System.out.println("1. Add Income/Expenses");
            System.out.println("2. Monthly Summary");
            System.out.println("3. Save to File");
            System.out.println("4. Load From File");
            System.out.println("5. Exit");
            System.out.print("Choose an option [1-5]: ");

            int choice = tracker.getIntInput("Choose an option: ");

            switch (choice) {
                case 1 -> tracker.addTransaction();
                case 2 -> tracker.showMonthlySummary();
                case 3 -> tracker.saveAllData();
                case 4 -> tracker.loadData();
                case 5 -> {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
