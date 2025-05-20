import java.time.LocalDate;
import java.util.*;

public class ExpenseTracker {
    private Map<String, User> users = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    public void addTransaction() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        User user = users.computeIfAbsent(username, User::new);

        int typeChoice = getIntInput("Enter type (1-Income, 2-Expense): ");

        TransactionType type = (typeChoice == 1) ? TransactionType.INCOME : TransactionType.EXPENSE;
        String category = getCategory(type);

        double amount = getDoubleInput("Enter amount: ");

        Transaction transaction = new Transaction(type, category, amount, LocalDate.now());
        user.addTransaction(transaction);

        System.out.println("Transaction added successfully!");
    }

    private String getCategory(TransactionType type) {
        if (type == TransactionType.INCOME) {
            System.out.print("Enter income category (Salary/Business): ");
        } else {
            System.out.print("Enter expense category (Food/Rent/Travel): ");
        }
        return scanner.nextLine();
    }

    public void showMonthlySummary() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        User user = users.get(username);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        Map<String, Double> incomeMap = new HashMap<>();
        Map<String, Double> expenseMap = new HashMap<>();
        double totalIncome = 0;
        double totalExpense = 0;

        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusMonths(1);

        for (Transaction t : user.getTransactions()) {
            if (!t.getDate().isBefore(startDate) && !t.getDate().isAfter(endDate))  {
                if (t.getType() == TransactionType.INCOME) {
                    incomeMap.put(t.getCategory(), incomeMap.getOrDefault(t.getCategory(), 0.0) + t.getAmount());
                    totalIncome += t.getAmount();
                } else {
                    expenseMap.put(t.getCategory(), expenseMap.getOrDefault(t.getCategory(), 0.0) + t.getAmount());
                    totalExpense += t.getAmount();
                }
            }
        }

        System.out.println("Monthly Summary for " + username);
        System.out.println("Income:");
        if (incomeMap.isEmpty()) {
            System.out.println("  Income: 0");
        } else {
            incomeMap.forEach((k, v) -> System.out.println("  " + k + ": " + v));
        }
        System.out.println("Total Income: " + totalIncome);

        System.out.println("\nExpenses:");
        if (expenseMap.isEmpty()) {
            System.out.println("  Expenses: 0");
        } else {
            expenseMap.forEach((k, v) -> System.out.println("  " + k + ": " + v));
        }
        System.out.println("Total Expenses: " + totalExpense);

        System.out.println("\nNet Savings: " + (totalIncome - totalExpense));
    }

    public void saveAllData() {
        System.out.print("Enter filename to save all users: ");
        String filename = scanner.nextLine();
        FileHandler.saveAllUsersToFile(users, filename);
    }

    public void loadData() {
        System.out.print("Enter filename to load data: ");
        String filename = scanner.nextLine();
        FileHandler.loadFromFile(filename, users);
    }

    public int getIntInput(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private double getDoubleInput(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid amount.");
            }
        }
    }

}
