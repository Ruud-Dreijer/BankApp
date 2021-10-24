package JavaAssociate;

import java.util.Random;

public class Bank {
    // 1-b-a
    public Account[] accounts;
    public int numberOfAccountsInUse = 0;
    public static int MAXACCOUNTS = 10;

    // Constructor
    // 1-b-a
    public Bank() {
        // Create storage for MAXACCOUNTS accounts
        accounts = new Account[ MAXACCOUNTS ];
    }

    // Basic Methods
    // 1-b-b-i
    /**
     *
     * @param amount as deposited by Customer on opening of Account
     * @return accountNumber of specific account to be returned to Customer
     *
     */
    public int openAccount( double amount ) {
        Account account = new Account ( generateAccountNumber(), amount );

        accounts[ numberOfAccountsInUse ] = account;
        numberOfAccountsInUse++;

        return account.accountNumber;
    }

    // 1-b-b-ii
    public boolean transfer( int accountNumberFrom, int accountNumberTo, double amount ) {
        Account accountFrom;
        Account accountTo;

        // Checks on accountNumbers supplied
        if ( accountNumberFrom == accountNumberTo ) {
            System.out.println("Transfer Money: Accounts specified are the same, no transfer executed");
            return false;
        }

        accountFrom = findAccount(accountNumberFrom);
        if ( accountFrom == null ) {
            System.out.println("Transfer Money: Account (from) does not exist, no transfer executed");
            return false;
        }

        accountTo = findAccount(accountNumberTo);
        if ( accountTo == null ) {
            System.out.println("Transfer Money: Account (to) does not exist, no transfer executed");
            return false;
        }

        accountFrom.withdraw( amount );
        accountTo.deposit( amount );
        return true;
    }

    // 1-b-b-iii
    public double totalMoneyInBank() {
        double total = 0;

        for ( Account account : accounts ) {
            if ( account == null ) {
                break;
            } else {
                total += account.balance;
            }
        }

        return total;
    }


    // Helper Methods
    /**
     *
     * @param accountNumber as specified by Customer
     * @return Account object as stored on Array
     *
     * Account objects are stored on our Array of Accounts in a continuous list.
     * At first, Array only contains 'nulls'. When opening Accounts Array is filled from left (0) to right (Max).
     *
     * When checking the Array, both 'null' and numberOfAccountsInUse can be used to be sure that all Accounts have been reviewed
     *
     */
    public Account findAccount( int accountNumber ) {
        for ( Account account : accounts ) {
            if (account == null) {          // Because of continuous list, when 'null' is found, the accountNumber doesn't exist
                return null;
            } else if (account.accountNumber == accountNumber) {
                return account;
            }
        }

        return null;                        // When Array is full, but accountNumber still isn't found, 'null' should be returned
    }

    // 1-b-b-i
    private int generateAccountNumber() {
        return new Random().nextInt(5000);
    }
}
