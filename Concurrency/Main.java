/*
 * Script in Java showing basic understanding of concurency and multithreaded programming.
 * M.S.
 */

import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    static AtomicInteger counter = new AtomicInteger(0);
    static int numCycles = 9;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        CountDownLatch latch = new CountDownLatch(numCycles);
        int numThreads = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        System.out.print("Hi, please enter your name.");
        String name = scanner.nextLine();

        for (int i = 0; i < numCycles; i++) {
            executor.submit(() -> count(latch));
        }

        try {
            latch.await();
        } catch (InterruptedException exception) {
            System.out.println(exception.getMessage());
        }

        System.out.println("Hi " + name + " while you entered your name, this code counted up to: " + counter
                + ", it did it in a " + numCycles
                + " threads. Each counted to "
                + ((int) (counter.doubleValue() / numCycles)) + ".");
        executor.shutdown();
        scanner.close();

    }

    public static void count(CountDownLatch latch) {
        for (int i = 0; i < 100000; i++) {
            counter.getAndIncrement();
        }
        latch.countDown();
    }
}
