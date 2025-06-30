import java.util.ArrayList;
import java.util.Scanner;


public class task5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Account> accounts = new ArrayList<>();
        int choice;

        do {
            System.out.println("\n--- Bank Main Menu ---");
            System.out.println("1. Create New Account");
            System.out.println("2. Access Existing Account");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Account Holder Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter New Account Number: ");
                    int newAccNum = sc.nextInt();

                    if (findAccount(accounts, newAccNum) != null) {
                        System.out.println("Account number already exists!");
                    } else {
                        Account newAcc = new Account(name, newAccNum);
                        accounts.add(newAcc);
                        System.out.println("Account created successfully!");
                    }
                    break;

                case 2:
                    System.out.print("Enter Account Number: ");
                    int accNum = sc.nextInt();
                    Account existingAcc = findAccount(accounts, accNum);
                    if (existingAcc == null) {
                        System.out.println("Account not found!");
                    } else {
                        operateAccount(existingAcc, sc);
                    }
                    break;

                case 3:
                    System.out.println("Exiting. Thank you!");
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        } while (choice != 3);

        sc.close();
    }

    // Method to find account by number
    public static Account findAccount(ArrayList<Account> accounts, int accNum) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber() == accNum) {
                return acc;
            }
        }
        return null;
    }

    // Account operations menu
    public static void operateAccount(Account account, Scanner sc) {
        int option;
        do {
            System.out.println("\n--- Account Menu (" + account.getAccountHolder() + ") ---");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Show Balance");
            System.out.println("4. Show Transaction History");
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Enter amount to deposit: ₹");
                    double dep = sc.nextDouble();
                    account.deposit(dep);
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ₹");
                    double wd = sc.nextDouble();
                    account.withdraw(wd);
                    break;
                case 3:
                    account.showBalance();
                    break;
                case 4:
                    account.showTransactions();
                    break;
                case 5:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (option != 5);
    }
}

class Account {
    private String accountHolder;
    private int accountNumber;
    private double balance;
    private ArrayList<String> transactionHistory;

    public Account(String accountHolder, int accountNumber) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
            return;
        }
        balance += amount;
        transactionHistory.add("Deposited: ₹" + amount);
        System.out.println("₹" + amount + " deposited successfully.");
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return;
        }
        if (amount > balance) {
            System.out.println("Insufficient balance!");
            return;
        }
        balance -= amount;
        transactionHistory.add("Withdrew: ₹" + amount);
        System.out.println("₹" + amount + " withdrawn successfully.");
    }

    public void showBalance() {
        System.out.println("Current Balance: ₹" + balance);
    }

    public void showTransactions() {
        System.out.println("Transaction History:");
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (String txn : transactionHistory) {
                System.out.println(txn);
            }
        }
    }
}
