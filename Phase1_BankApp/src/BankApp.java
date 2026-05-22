import java.util.ArrayList;
import java.util.List;

enum TransactionType {
    DEPOSIT, WITHDRAW
}

class Account implements Printable {
    int balance;
    List<Transaction> transactions;

    public Account() {
        this(0);
    }

    public Account(int balance) {
        this.balance = balance;
        transactions = new ArrayList<>();
    }

    void deposit(int depositAmount) {
        balance += depositAmount;
        System.out.println("Deposit Successful");
        System.out.println("Current Balance: " + balance);
        transactions.add(new Transaction(TransactionType.DEPOSIT, depositAmount));
    }

    void withdraw(int withdrawAmount) {
        if(withdrawAmount > balance) {
            System.out.println("Withdraw Amount is more that the available balance. Please enter a valid amount.");
            System.out.println("Current Balance: " + balance);
        }
        else {
            balance -= withdrawAmount;
            System.out.println("Withdraw Successful");
            System.out.println("Current Balance: " + balance);
            transactions.add(new Transaction(TransactionType.WITHDRAW, withdrawAmount));
        }
    }

    @Override
    public void printSummary() {
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }
}

class SavingsAccount extends Account {
//    Assumption: The difference between savings and a normal account is that savings account has a minimum balance. Doesn't make much sense when you think about it. Arguably There should be an interest value for both and savings account has a bit more interest. but we are not going there.
    static int minimumBalance = 500;

    public SavingsAccount() {
//        Assuming that if someone is creating a savings account they are creating it with the minimum balance. like they pay in the bank and it appears in the account or something like that.
        super(minimumBalance);
    }

    @Override
    void withdraw(int withdrawAmount) {
        if(balance - withdrawAmount >= minimumBalance) {
            super.withdraw(withdrawAmount);
        }
        else {
            System.out.println("Not Possible.");
            System.out.println("Withdrawing amount will make balance less than minimum balance of " + minimumBalance);
        }
    }
}

class Transaction {
    TransactionType transactionType;
    int amount;

    public Transaction(TransactionType transactionType, int amount) {
        this.transactionType = transactionType;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionType=" + transactionType +
                ", amount=" + amount +
                '}';
    }
}

interface Printable {
    void printSummary();
}

public class BankApp {
    public static void main(String[] args) {
        Account account1 = new Account();
        Account account2 = new Account(200);

        Account account3 = new SavingsAccount();

        account1.deposit(650);
        account1.deposit(120);
        account1.withdraw(400);

        account2.withdraw(320);

        account3.deposit(420);
        account3.withdraw(1020);

        account1.printSummary();
        account2.printSummary();
        account3.printSummary();
    }
}
