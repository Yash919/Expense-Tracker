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

    public static Transaction fromString(String s) {
        String[] parts = s.split(",");
        if (parts.length != 4) {
            throw new IllegalArgumentException("Invalid line format: " + s);
        }
        String typeStr = parts[0].trim();
        String category = parts[1].trim();
        double amount;
        LocalDate date;
        try {
            amount = Double.parseDouble(parts[2].trim());
            date = LocalDate.parse(parts[3].trim());
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid amount or date format: " + s);
        }

        TransactionType type;
        try {
            type = TransactionType.valueOf(typeStr.toUpperCase());  // enum matching
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid transaction type: " + typeStr);
        }

        return new Transaction(type, category, amount, date);
    }


    @Override
    public String toString() {
        return type + "," + category + "," + amount + "," + date;
    }
}
