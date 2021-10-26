package JavaAssociate;

import java.util.Random;
import java.util.Scanner;

public class Bank {
    // 1-b-a, 2-a
    private Account[] accounts;
    private int numberOfAccountsInUse = 0;
    private final static int MAXACCOUNTS = 10;

    // Constructor
    // 1-b-a
    public Bank() {
        // Create storage for MAXACCOUNTS accounts
        accounts = new Account[ MAXACCOUNTS ];
    }

    // Basic Methods
    // 1-b-b-i, 2-c
    /**
     *
     * @param amount as deposited by Customer on opening of Account
     * @return accountNumber of specific account to be returned to Customer
     *
     */
    public int openAccount( double amount ) {
        int accountNumber = generateAccountNumber();

        Account account = new Account ( accountNumber, amount );

        accounts[ numberOfAccountsInUse ] = account;
        numberOfAccountsInUse++;

        return accountNumber;
    }

    // 3-b
    public void removeAccount(int accountNumberToRemove) {
        double balance;

        Account accountToRemove = findAccount( accountNumberToRemove );

        if ( accountToRemove == null ) {
            System.out.println("removeAccount: The account specified does not exist");
        } else {
            removeAccountFromArray(accountToRemove);

            balance = accountToRemove.getBalance();
            if ( balance > 0.0 ) {
                System.out.println("Account " + accountNumberToRemove + " is removed. Balance of " + balance + " must be paid to Customer in cash");
            } else if ( balance < 0.0 ) {
                System.out.println("Account " + accountNumberToRemove + " is removed. Balance of " + balance + " must be paid by Customer in cash");
            }
        }
    }

    // 3-b
    public void removeAccount(int accountNumberToRemove, int accountNumberToTransferTo) {
        if ( accountNumberToRemove == accountNumberToTransferTo ) {
            System.out.println("removeAccount: Accounts specified are the same, no transfer executed");
        } else {
            Account accountToRemove = findAccount(accountNumberToRemove);
            Account accountToTransferTo = findAccount(accountNumberToTransferTo);

            if ( accountToRemove == null || accountToTransferTo == null) {
                System.out.println("removeAccount: One of the accounts specified does not exist");
            } else {
                double balance = accountToRemove.getBalance();

                if (transfer(accountNumberToRemove, accountNumberToTransferTo, balance)) {
                    removeAccountFromArray(accountToRemove);

                    System.out.println("Account " + accountNumberToRemove + " is removed. Balance of " + balance + " is transfered to account " + accountNumberToTransferTo);
                }
            }
        }
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

    // 1-b-b-iii, 2-c
    public double totalMoneyInBank() {
        double total = 0;

        for ( Account account : accounts ) {
            if ( account == null ) {
                break;
            } else {
                total += account.getBalance();
            }
        }

        return total;
    }

    // 3-a
    public double processAnnualInterest() {
        double totalInterestCalculated = 0.0;

        for (Account account: accounts) {
            if (account == null) {
                break;
            } else {
                totalInterestCalculated += account.processInterest();
            }
        }

        return totalInterestCalculated;
    }


    // Helper Methods
    // 2-c
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
            } else if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }

        return null;                        // When Array is full, but accountNumber still isn't found, 'null' should be returned
    }

    // 1-b-b-i
    private int generateAccountNumber() {
        return new Random().nextInt(5000);
    }

    // 3-b
    // An Array (' accounts') is used, to store Account objects
    // This Array is initialiazed with 'null'  at first, and filled from left (0) to right (MAXACCOUNTS-1) with new Accounts
    // That means that the Array is a continuous list op opened Accounts, until 'null' is found or (numberOfAccountsInUse-1) Accounts have been read
    //
    // This also means, that when an Account is removed, this continuous list must still remain
    // So, when Accounts are removed from the list, other Accounts need to shift in the Array
    private void removeAccountFromArray(Account account ) {
        if ( numberOfAccountsInUse > 0 ) {
            boolean accountFound = false;

            for (int counter = 0; counter < numberOfAccountsInUse; counter++) {
                if (accounts[counter] == account) {
                    accountFound = true;

                    for (; counter < numberOfAccountsInUse - 1; counter++) {
                        accounts[counter] = accounts[counter + 1];
                    }
                }
            }

            if ( accountFound ) {
                accounts[numberOfAccountsInUse - 1] = null;
                numberOfAccountsInUse--;
            } else {
                System.out.println("The account specified does not exist");
            }

        } else {
            System.out.println("There are no accounts to remove");
        }
    }
}
