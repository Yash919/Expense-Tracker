import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private TransactionType type;
    private String category;
    private double amount;
    private LocalDate date;

    public Transaction(TransactionType type, String category, double amount, LocalDate date) {
        this.type = type;
        this.category = category;
        this.amount = amount;
        this.date = date;
    }

    public TransactionType getType() {
        return type;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public static Transaction fromString(String line) {
        String[] parts = line.split(",");
        if (parts.length != 5) {
            throw new IllegalArgumentException("Invalid line format: " + line);
        }

        TransactionType type = TransactionType.valueOf(parts[1].trim().toUpperCase());
        String category = parts[2].trim();
        double amount = Double.parseDouble(parts[3].trim());
        LocalDate date = LocalDate.parse(parts[4].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return new Transaction(type, category, amount, date);
    }

    @Override
    public String toString() {
        return type + "," + category + "," + amount + "," + date;
    }
}
