package hw6;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class Test {

    public static Account generateAccountA() {
        return new Account(new Transaction[]{
                new Transaction(TransactionType.DEPOSIT, 100, "Initial deposit"),
                new Transaction(TransactionType.DEPOSIT, 20),
                new Transaction(TransactionType.DEPOSIT, 43),
                new Transaction(TransactionType.DEPOSIT, 976, "BIG MONEY"),
                new Transaction(TransactionType.DEPOSIT, 34, Optional.empty()),

                new Transaction(TransactionType.WITHDRAWAL, 53, "LONG LONG LONG LONG LONG LONG LONG LONG"),
                new Transaction(TransactionType.WITHDRAWAL, 434, Optional.of("optional")),
                new Transaction(TransactionType.WITHDRAWAL, 43, ""),
                new Transaction(TransactionType.WITHDRAWAL, 12),
                new Transaction(TransactionType.WITHDRAWAL, 0.05, "Thank you Nathan"),
        });
    }

    public static void printOut(List<Transaction> transactions) {
        System.out.println(transactions);
        System.out.println(transactions.size());
        System.out.println();
    }

    public static void test1() {
        Account account = Test.generateAccountA();
        System.out.println(account.getPastTransactions().size());
        System.out.println(account.getTransaction(4));
        System.out.println();

        printOut(account.findTransactionsByPredicate(
                (transaction -> (transaction.getType() == TransactionType.DEPOSIT && transaction.getAmount() == 100))
        ));

        printOut(account.findTransactionsByAmount(43));

        printOut(account.getWithdrawals());

        printOut(account.getDeposits());

        printOut(account.getTransactionsWithCommentLongerThan(20));

        printOut(account.getTransactionsWithComment());
    }


    public static void main(String[] args) {
        Test.test1();
    }
}
