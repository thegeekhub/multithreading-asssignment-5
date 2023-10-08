package multithreading_assignment_5_3;


public class ProducerConsumer {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(5); // Buffer size is 5

        Thread producerThread = new Thread(new Producer(buffer));
        Thread consumerThread = new Thread(new Consumer(buffer));

        producerThread.start();
        consumerThread.start();
    }
}
