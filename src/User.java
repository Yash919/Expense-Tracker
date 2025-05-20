import java.util.*;

public class User {
    private String username;
    private List<Transaction> transactions;

    public User(String username) {
        this.username = username;
        this.transactions = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
}
