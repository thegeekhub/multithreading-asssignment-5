package multithreading_assignment_5;


class Printer {
    private boolean isEvenTurn = true;

    public synchronized void printEven(int num) {
        while (!isEvenTurn) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Even: " + num);
        isEvenTurn = false;
        notify();
    }

    public synchronized void printOdd(int num) {
        while (isEvenTurn) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Odd: " + num);
        isEvenTurn = true;
        notify();
    }
}

class EvenTask implements Runnable {
    private final Printer printer;

    public EvenTask(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void run() {
        for (int i = 2; i <= 10; i += 2) {
            printer.printEven(i);
        }
    }
}

class OddTask implements Runnable {
    private final Printer printer;

    public OddTask(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i += 2) {
            printer.printOdd(i);
        }
    }
}
