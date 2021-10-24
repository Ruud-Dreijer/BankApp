package JavaAssociate;

public class Test {
    // 1-c
    public static void main(String[] args) {
        // Create Bank
        Bank ingBank = new Bank();

        // Create Accounts
        int account1 = ingBank.openAccount( 1_000.0 );
        int account2 = ingBank.openAccount( 5_120.75 );
        int account3 = ingBank.openAccount( 250.0 );
        int account4 = ingBank.openAccount( 750.0 );

        System.out.println("Created account1. accountNumber = " + account1);
        System.out.println("Created account2. accountNumber = " + account2);
        System.out.println("Created account3. accountNumber = " + account3);
        System.out.println("Created account4. accountNumber = " + account4);
        System.out.println();

        // Get balance of account
        System.out.println("Balance of account1 = "  +  ingBank.findAccount(account1).balance);
        System.out.println("Balance of account2 = "  +  ingBank.findAccount(account2).balance);
        System.out.println("Balance of account3 = "  +  ingBank.findAccount(account3).balance);
        System.out.println("Balance of account4 = "  +  ingBank.findAccount(account4).balance);
        System.out.println();

        // Show Money in the Bank
        System.out.println("Total amount in the Bank = " + ingBank.totalMoneyInBank());
        System.out.println();

        // Deposit
        System.out.println("New balance of account1, after deposit = " + ingBank.findAccount(account1).deposit( 1_000.0 ));
        System.out.println("New balance of account2, after deposit = " + ingBank.findAccount(account2).deposit( 2_500.0 ));
        System.out.println();

        // Withdraw
        System.out.println("New balance of account3, after withdraw = " + ingBank.findAccount(account3).withdraw( 1_000.0 ));
        System.out.println("New balance of account4, after withdraw = " + ingBank.findAccount(account4).withdraw( 2_000.0 ));
        System.out.println();

        // Show Money in the Bank
        System.out.println("Total amount in the Bank = " + ingBank.totalMoneyInBank());
        System.out.println();

        // Transfer
        if ( ingBank.transfer( account1, account3, 500.0 )) {
            System.out.println("Transfer of 500 euro from account1 to account3 succeeded");
        } else {
            System.out.println("Transfer of 500 euro from account1 to account3 failed");
        }

        if ( ingBank.transfer( account2, account4, 250.0 )) {
            System.out.println("Transfer of 250 euro from account2 to account4 succeeded");
        } else {
            System.out.println("Transfer of 250 euro from account2 to account4 failed");
        }

        System.out.println();

        // Show Money in the Bank
        System.out.println("Total amount in the Bank = " + ingBank.totalMoneyInBank());
        System.out.println();
    }
}
