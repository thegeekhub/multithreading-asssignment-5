package multithreading_assignment_5;

public class EvenOddPrinter {
    public static void main(String[] args) {
        Printer printer = new Printer();

        Thread evenThread = new Thread(new EvenTask(printer));
        Thread oddThread = new Thread(new OddTask(printer));

        evenThread.start();
        oddThread.start();
    }
}
