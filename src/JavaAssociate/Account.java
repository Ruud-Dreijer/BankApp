package JavaAssociate;

public class Account {
    // 1-a-a, 2-a
    private int accountNumber;
    private double balance;

    // 3-a
    private static final double INTEREST_ON_POSITIVE_BALANCE = 0.1;
    private static final double INTEREST_ON_NEGATIVE_BALANCE = 0.5;

    // Constructor
    // 1-a-b
    public Account(int accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    // Getters and Setters
    // 2-b
    public int getAccountNumber() {
        return accountNumber;
    }

    // 2-b
    public double getBalance() {
        return balance;
    }

    // Basic Methods
    // 1-a-c-i
    public double deposit(double amount) {
        return ( balance += amount );
    }

    // 1-a-c-ii
    public double withdraw(double amount) {
        return ( balance -= amount );
    }

    // 3-a
    public double processInterest() {
        double interest;

        if ( balance < 0 ) {
            interest = ( INTEREST_ON_NEGATIVE_BALANCE / 100 ) * balance;
        } else {
            interest = ( INTEREST_ON_POSITIVE_BALANCE / 100 ) * balance;
        }

        balance += interest;

        return interest;
    }
}
