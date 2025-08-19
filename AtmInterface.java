package CodsoftInternship;
    import java.util.Scanner;


class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            return false;
        }
        balance -= amount;
        return true;
    }
}


class ATM {
    private BankAccount account;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("\n===== ATM Menu =====");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            
            int choice = -1;

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number (1-4).");
                continue;
            }

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    running = false;
                    System.out.println("Thank you for using the ATM!");
                    break;
                default:
                    System.out.println("Invalid option. Please choose between 1 and 4.");
            }
        }
    }

    private void checkBalance() {
        System.out.printf("Your current balance is: Rs %.2f%n", account.getBalance());
    }

    private void deposit() {
        System.out.print("Enter deposit amount: ");
        double amount = getPositiveDouble();
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
            return;
        }
        account.deposit(amount);
        System.out.printf("Successfully deposited Rs %.2f. New balance: Rs %.2f%n", amount, account.getBalance());
    }

    private void withdraw() {
        System.out.print("Enter withdrawal amount: ");
        double amount = getPositiveDouble();
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return;
        }
        if (account.withdraw(amount)) {
            System.out.printf("Successfully withdrew Rs %.2f. New balance: Rs %.2f%n", amount, account.getBalance());
        } else {
            System.out.println("Insufficient balance for this withdrawal.");
        }
    }


    private double getPositiveDouble() {
        double amount = -1;
        try {
            amount = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format.");
            return -1;
        }
        return amount;
    }
}

public class AtmInterface {
    public static void main(String[] args) {

        BankAccount account = new BankAccount(1000.0);
        ATM atm = new ATM(account);
        atm.start();
    }
}

    

