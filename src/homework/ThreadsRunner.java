package homework;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

public class ThreadsRunner {

    public void runnerThreads() {
        Queue<Crystal> crystalQueue = new LinkedBlockingDeque<>();
        AtomicBoolean finish = new AtomicBoolean(true);
        Thread airThread = new Thread(new AirConsumer(crystalQueue, 100, 0, finish));
        Thread fireThread = new Thread(new FireConsumer(crystalQueue, 100, 0, finish));

        Thread producer = new Thread(new CrystalProducer(crystalQueue, 500, 0, finish));

        airThread.start();
        fireThread.start();
        producer.start();
    }

}
