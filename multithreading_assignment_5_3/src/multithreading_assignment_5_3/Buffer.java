package multithreading_assignment_5_3;
import java.util.LinkedList;

class Buffer {
    private LinkedList<Integer> items;
    private int maxSize;

    public Buffer(int size) {
        items = new LinkedList<>();
        maxSize = size;
    }

    public synchronized void produce(int item) throws InterruptedException {
        while (items.size() == maxSize) {
            // Buffer is full, wait for the consumer to make space
            wait();
        }

        items.add(item);
        System.out.println("Produced: " + item);
        notify(); // Notify the consumer that an item is added
    }

    public synchronized int consume() throws InterruptedException {
        while (items.isEmpty()) {
            // Buffer is empty, wait for the producer to produce an item
            wait();
        }

        int item = items.removeFirst();
        System.out.println("Consumed: " + item);
        notify(); // Notify the producer that space is available
        return item;
    }
}
class Producer implements Runnable {
    private Buffer buffer;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            try {
                buffer.produce(i);
                Thread.sleep(100); // Simulate the time taken to produce an item
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

class Consumer implements Runnable {
    private Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            try {
                int item = buffer.consume();
                Thread.sleep(200); // Simulate the time taken to consume an item
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
