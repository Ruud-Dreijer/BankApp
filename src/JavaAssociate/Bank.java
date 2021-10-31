package JavaAssociate;

import java.util.*;

public class Bank {
    // 1-b-a, 2-a
//    private Account[] accounts;
    private int numberOfAccountsInUse = 0;
//    private final static int MAXACCOUNTS = 10;

    // Inner List = { accountNumber, account }; two different types (int, account), so Object
    // Outer List = { {<inner list>} }
    // Define Array as a list of (list of Objects), no size
    // 5-a
    private ArrayList<ArrayList<Object>> accounts = new ArrayList<>();     // Definitie Buitenste Array zonder grootte

    // Constructor
    // 1-b-a, 5-b
    public Bank() {
        System.out.println("BankApp Initialized");
    }

    // Menu Methods
    // 4-a
    private static void showMenu() {
        System.out.println("*****************************************");
        System.out.println("*                                       *");
        System.out.println("* Account functions:                    *");
        System.out.println("* 1. Open an Account                    *");
        System.out.println("* 2. Remove an Account                  *");
        System.out.println("* 3. Show Balance of Account            *");
        System.out.println("* 4. Withdraw from Account              *");
        System.out.println("* 5. Deposit into Account               *");
        System.out.println("* 6. Transfer between Accounts          *");
        System.out.println("*                                       *");
        System.out.println("* Management functions:                 *");
        System.out.println("* 11. Show all Accountnumbers           *");
        System.out.println("* 12. Process annual interest           *");
        System.out.println("* 13. Show total Balance on Accounts    *");
        System.out.println("*                                       *");
        System.out.println("* Other functions:                      *");
        System.out.println("* 99. Stop programma                    *");
        System.out.println("*                                       *");
        System.out.println("*****************************************");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.print("Make a selection from the Menu: ");
    }

    // 4-a
    public static void showMenuAndProcesSelectedChoice(Bank bank)  {
        // Create Object to retrieve User input
        Scanner scanUserInput = new Scanner(System.in);

        menu: while (true) {
            showMenu();

            int menuSelection = scanUserInput.nextInt();
            switch(menuSelection) {
                case 1:         // Open an Account
                    bank.openAccount(scanUserInput);

                    break;
                case 2:         // Remove an Account, handle Balance
                    bank.removeAccount(scanUserInput);

                    break;
                case 3:         // Show Balance
                    int accountNumber = getIntFromInput("What is the Accountnumber: ", scanUserInput);

                    System.out.printf("Balance of Account %d is %5.2f EURO %n", accountNumber, bank.findAccount(accountNumber).getBalance());

                    break;
                case 4:         // Withdraw
                    bank.withdraw(scanUserInput);

                    break;
                case 5:         // Deposit
                    bank.deposit(scanUserInput);

                    break;
                case 6:         // Transfer
                    bank.transfer(scanUserInput);

                    break;
                case 11:         // Show all Accountnumbers
                    bank.showAllAccounts();

                    break;
                case 12:         // Verwerk jaarlijkse rente
                    System.out.printf("Total interest processed = %.2f", bank.processAnnualInterest());
                    System.out.println();

                    break;
                case 13:         // Geld in Bank
                    System.out.printf("Total amount on all Accounts = %.2f", bank.totalMoneyInBank());
                    System.out.println();

                    break;
                case 99:
                    break menu;
                default:
                    System.out.println();
                    System.out.println("Invalid entry, choose one of the menu options: ");
            }
        }
    }

    // 4-a
    private void transfer( Scanner scanUserInput ) {
        System.out.println("************************************");
        System.out.println("*                                  *");
        System.out.println("* Transfer between Accounts        *");
        System.out.println("*                                  *");
        System.out.println("************************************");

        // get input
        int accountNumberFrom = getIntFromInput("What is the Accountnumber (from): ", scanUserInput);
        int accountNumberTo = getIntFromInput("What is the Accountnumber (to): ", scanUserInput);
        double amount = getDoubleFromInput("What is the amount you want to transfer: ", scanUserInput);
        Account accountFrom = findAccount(accountNumberFrom);
        Account accountTo = findAccount(accountNumberTo);

        System.out.println("Balance on Accountnumer " + accountNumberFrom + " before withdrawal of " + amount + " euro, was " + accountFrom.getBalance() + " euro");
        System.out.println("Balance on Accountnumer " + accountNumberTo + " before deposit of " + amount + " euro, was " + accountTo.getBalance() + " euro");

        if (transfer(accountFrom, accountTo, amount)) {
            System.out.println("New balance on Accountnumer " + accountNumberFrom + " is " + accountFrom.getBalance() + " euro");
            System.out.println("New balance on Accountnumer " + accountNumberTo + " is " + accountTo.getBalance() + " euro");
        } else {
            System.out.println("Transfer failed");
        }
    }

    // 4-a
    private void deposit( Scanner scanUserInput ) {
        System.out.println("************************************");
        System.out.println("*                                  *");
        System.out.println("* Deposit into an Account          *");
        System.out.println("*                                  *");
        System.out.println("************************************");

        // get input
        int accountNumber = getIntFromInput("What is the Accountnumber: ", scanUserInput);
        double amount = getDoubleFromInput("What is the amount you want to deposit: ", scanUserInput);
        Account account = findAccount(accountNumber);

        // write output
        System.out.println("Balance of Accountnumber " + accountNumber + " before deposit of " + amount + " euro, was " + account.getBalance() + " euro");
        System.out.println("New balance is: " + account.deposit(amount));
    }

    // 4-a
    private void withdraw( Scanner scanUserInput ) {
        System.out.println("************************************");
        System.out.println("*                                  *");
        System.out.println("* Withdraw from an Account         *");
        System.out.println("*                                  *");
        System.out.println("************************************");

        // get input
        int accountNumber = getIntFromInput("What is the Accountnumber: ", scanUserInput);
        double amount = getDoubleFromInput("What is the amount you want to withdraw: ", scanUserInput);
        Account account = findAccount(accountNumber);

        // write output
        System.out.println("Balance of Accountnumber " + accountNumber + " before withdrawal of " + amount + " euro, was " + account.getBalance() + " euro");
        System.out.println("New balance is: " + account.withdraw(amount));
    }

    // 4-a
    private void removeAccount( Scanner scanUserInput ) {
        System.out.println("************************************");
        System.out.println("*                                  *");
        System.out.println("* Removing an Account              *");
        System.out.println("*                                  *");
        System.out.println("************************************");

        // Get Accountnumber to remove
        Account accountToRemove = findAccount( getIntFromInput("What is the Accountnumber, which must be removed: ", scanUserInput) );

        if ( accountToRemove == null ) {
            System.out.println("removeAccount: The account (from) specified does not exist");
            return;
        }

        // Handle balance or transfer
        int cashOrTransfer = getIntFromInput("Will the Balance be handled in cash (1) or transfered (2): ", scanUserInput);

        if ( cashOrTransfer == 1 ) {

            // Remove Account, handle Balance
            removeAccount( accountToRemove );

        } else if ( cashOrTransfer == 2 ) {

            // Get Accountnumber to transfer to
            Account accountToTransferTo = findAccount( getIntFromInput("What is the Accountnumber, to transfer to: ", scanUserInput) );

            if ( accountToTransferTo == null ) {
                System.out.println("removeAccount: The account (to) specified does not exist");
                return;
            } else if ( accountToRemove == accountToTransferTo ) {
                System.out.println("removeAccount: Accounts specified are the same, no transfer executed");
                return;
            } else {
                // Remove Account, transfer Balance
                removeAccount( accountToRemove, accountToTransferTo );
            }

        } else {

            System.out.println("removeAccount: Invalid entry, choose 1 or 2");
            return;

        }
    }

    // Basic Methods
    // 1-b-b-i, 2-c, 4-a, 5-b
    /**
     *
     * @param scanUserInput for reuse of Scanner Object
     * @return accountNumber of specific account to be returned to Customer
     *
     */
    private int openAccount( Scanner scanUserInput ) {
        System.out.println("************************************");
        System.out.println("*                                  *");
        System.out.println("* Opening an Account               *");
        System.out.println("*                                  *");
        System.out.println("************************************");

        // Generate the Accountnumber for the Account
        int accountNumber = generateAccountNumber();

        // Store Account Object
        // Create new Inner block, size 2
        accounts.add(new ArrayList<Object>(2));

        // Write two values to created empty Inner Block
        accounts.get(numberOfAccountsInUse).add(0, accountNumber);
        accounts.get(numberOfAccountsInUse).add(1, new Account ( accountNumber, getDoubleFromInput("What will be the initial balance: ", scanUserInput) ));

        // Increase number of Accounts in use
        numberOfAccountsInUse++;

        System.out.println("Account created, number: " + accountNumber);

        return accountNumber;
    }

    // 3-b, 4-a
    private void removeAccount(Account accountToRemove) {
        double balance = accountToRemove.getBalance();
        if ( balance > 0.0 ) {
            System.out.println("Account " + accountToRemove.getAccountNumber() + " is removed. Balance of " + accountToRemove.getBalance() + " must be paid to Customer in cash");
        } else if ( balance < 0.0 ) {
            System.out.println("Account " + accountToRemove.getAccountNumber() + " is removed. Balance of " + accountToRemove.getBalance() + " must be paid by Customer in cash");
        }

        removeAccountFromArray(accountToRemove);
    }

    // 3-b, 4-a
    private void removeAccount(Account accountToRemove, Account accountToTransferTo) {
        double balance = accountToRemove.getBalance();

        if (transfer(accountToRemove, accountToTransferTo, balance)) {
            System.out.println("Account " + accountToRemove.getAccountNumber() + " is removed. Balance of " + balance + " is transfered to account " + accountToTransferTo.getAccountNumber());

            removeAccountFromArray(accountToRemove);
        }
    }

    // 1-b-b-ii, 4-a
    private boolean transfer( Account accountFrom, Account accountTo, double amount ) {
        // Checks on accountNumbers supplied
        if ( accountFrom == accountTo ) {
            System.out.println("Transfer Money: Accounts specified are the same, no transfer executed");
            return false;
        }

        if ( accountFrom == null ) {
            System.out.println("Transfer Money: Account (from) does not exist, no transfer executed");
            return false;
        }

        if ( accountTo == null ) {
            System.out.println("Transfer Money: Account (to) does not exist, no transfer executed");
            return false;
        }

        accountFrom.withdraw( amount );
        accountTo.deposit( amount );
        return true;
    }

    // 1-b-b-iii, 2-c, 5-b
    private double totalMoneyInBank() {
        double total = 0;
        Account account;

        for (int counter = 0; counter < numberOfAccountsInUse; counter++) {
            account = (Account) accounts.get( counter ).get(1);
            total += account.getBalance();
        }

        return total;
    }

    // 3-a, 5-b
    private double processAnnualInterest() {
        double totalInterestCalculated = 0.0;
        Account account;

        for (int counter = 0; counter < numberOfAccountsInUse; counter++) {
            account = (Account) accounts.get( counter ).get(1);
            totalInterestCalculated += account.processInterest();
        }

        return totalInterestCalculated;
    }

    // 5-b
    private void showAllAccounts() {
        Account account;

        for (int counter = 0; counter < numberOfAccountsInUse; counter++) {
            account = (Account) accounts.get( counter ).get(1);
            System.out.print(accounts.get( counter ).get(0) + " (" + account.getBalance() + ")  ");
        }

        System.out.println();
    }

    // Helper Methods
    // 2-c, 5-b
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
    private Account findAccount( int accountNumber ) {
        for (int counter = 0; counter < numberOfAccountsInUse; counter++) {
            if ( (int) accounts.get( counter ).get(0) == accountNumber ) {
                return (Account) accounts.get( counter ).get(1);
            }
        }

        return null;
    }

    // 1-b-b-i
    private int generateAccountNumber() {
        return new Random().nextInt(5000);
    }

    // 3-b, 5-b
    private void removeAccountFromArray(Account account ) {
        if ( numberOfAccountsInUse > 0 ) {
            int counter;
            boolean accountFound = false;

            for (counter = 0; counter < numberOfAccountsInUse; counter++) {
                if ( accounts.get( counter ).get(1) == account ) {
                    accountFound = true;
                    break;
                }
            }

            if ( accountFound ) {
                accounts.remove( counter );
                numberOfAccountsInUse--;
            } else {
                System.out.println("The account specified does not exist");
            }

        } else {
            System.out.println("There are no accounts to remove");
        }
    }

    // Scanner Methods
    // 4-a
    private static int getIntFromInput(String printText, Scanner scanUserInput) {
        System.out.print(printText);
        return scanUserInput.nextInt();
    }

    // 4-a
    private static double getDoubleFromInput(String printText, Scanner scanUserInput) {
        System.out.print(printText);
        return scanUserInput.nextDouble();
    }
}
