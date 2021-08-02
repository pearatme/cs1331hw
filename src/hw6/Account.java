package hw6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Account {
    List<Transaction> pastTransactions;

    public Account(Transaction[] pastTransactions) {
        this.pastTransactions = Arrays.asList(pastTransactions);
    }

    public Transaction getTransaction(int n) {
        return pastTransactions.get(n);
    }

    public List<Transaction> getPastTransactions() {
        return pastTransactions;
    }

    public List<Transaction> findTransactionsByPredicate(Predicate<Transaction> predicate) {
        List<Transaction> result = new ArrayList<>();
        for (Transaction transaction: pastTransactions)
            if (predicate.test(transaction))
                result.add(transaction);
        return result;
    }

    static class EqualToAmount<E> implements Predicate<Transaction> {
        private final double amount;

        public EqualToAmount(double amount) {
            this.amount = amount;
        }

        @Override
        public boolean test(Transaction transaction) {
            return transaction.getAmount() == amount;
        }

    }

    public List<Transaction> findTransactionsByAmount(double amount) {
        return this.findTransactionsByPredicate(new EqualToAmount<Transaction>(amount));
    }

    public List<Transaction> getWithdrawals() {
        return this.findTransactionsByPredicate(new Predicate<Transaction>() {
            @Override
            public boolean test(Transaction transaction) {
                return transaction.getType() == TransactionType.WITHDRAWAL;
            }
        });
    }

    public List<Transaction> getDeposits() {
        Predicate<Transaction> isDeposit = transaction -> (transaction.getType() == TransactionType.DEPOSIT);
        return this.findTransactionsByPredicate(isDeposit);
    }

    public List<Transaction> getTransactionsWithCommentLongerThan(int length) {
        return this.findTransactionsByPredicate((transaction -> {
            if(!transaction.hasComment())
                return false;
            return transaction.getComment().get().length() > length;
        }));
    }

    public List<Transaction> getTransactionsWithComment() {
        return this.findTransactionsByPredicate(Transaction::hasComment);
    }
}
