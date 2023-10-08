package multithreading_assignment_5_2;

public class BankAccount {
    private double balance = 0;

    public synchronized void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
            System.out.println("New Balance: " + balance);
        }
    }

    public synchronized void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
            System.out.println("New Balance: " + balance);
        } else {
            System.out.println("Withdrawal failed. Insufficient balance.");
        }
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount();

        Thread depositThread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                account.deposit(100);
                try {
                    Thread.sleep(100); // Sleep for a short time to simulate other operations
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread withdrawThread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                account.withdraw(50);
                try {
                    Thread.sleep(100); // Sleep for a short time to simulate other operations
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        depositThread.start();
        withdrawThread.start();
    }
}

