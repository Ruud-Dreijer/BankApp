package JavaAssociate;

public class Account {
    // 1-a-a
    public int accountNumber;
    public double balance;

    // Constructor
    // 1-a-b
    public Account(int accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
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
}
