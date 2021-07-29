package hw6;

import java.util.Optional;

public class Transaction {
    TransactionType type;
    double amount;
    Optional<String> comment;

    public Transaction(TransactionType type, double amount, Optional<String> comment) {
        this.type = type;
        this.amount = amount;
        this.comment = comment;
    }

    public Transaction(TransactionType type, double amount) {
        this.type = type;
        this.amount = amount;
        this.comment = Optional.empty();
    }

    public Transaction(TransactionType type, double amount, String comment) {
        this.type = type;
        this.amount = amount;
        this.comment = Optional.of(comment);
    }

    public TransactionType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public Optional<String> getComment() {
        return comment;
    }

    public boolean hasComment() {
        return comment.isPresent();
    }

    // For testing

    @Override
    public String toString() {
        return "Transaction{" +
                "type=" + type +
                ", amount=" + amount +
                ", comment=" + comment +
                '}';
    }
}
