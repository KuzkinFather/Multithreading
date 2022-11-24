package homework;

import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;

public class CrystalProducer implements Runnable {

    private final Queue<Crystal> crystalQueue;
    private final int maxCrystal;
    private int counter;
    private AtomicBoolean finish;


    public CrystalProducer(Queue<Crystal> crystalQueue, int maxCrystal, int counter, AtomicBoolean finish) {
        this.crystalQueue = crystalQueue;
        this.maxCrystal = maxCrystal;
        this.counter = counter;
        this.finish = finish;
    }

    @Override
    public void run() {
        System.out.println("CrystalProducer: начал создавать кристалы");
        while (counter < maxCrystal && finish.get()) {
            synchronized (crystalQueue) {
                int totalCount = ThreadLocalRandom.current().nextInt(2, 5);
                int redCrystalCount = ThreadLocalRandom.current().nextInt(0, totalCount);

                for (int i = 0; i < redCrystalCount; i++) {
                    crystalQueue.offer(new Crystal(CrystalColor.RED));
                    counter++;
                }
                for (int i = 0; i < totalCount - redCrystalCount; i++) {
                    crystalQueue.offer(new Crystal(CrystalColor.WHITE));
                    counter++;
                }

                System.out.println("Есть - красных кристалов: " + redCrystalCount + " и белых: " + (totalCount - redCrystalCount));
            }

            try {
                Thread.sleep(800L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
        System.out.println("Crystal producer: закончил создавать кристалы");
    }

    @Override
    public String toString() {
        return "CrystalProducer{" +
                "crystalQueue=" + crystalQueue +
                ", maxCrystal=" + maxCrystal +
                ", counter=" + counter +
                ", finish=" + finish +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CrystalProducer that = (CrystalProducer) o;
        return maxCrystal == that.maxCrystal && counter == that.counter && Objects.equals(crystalQueue, that.crystalQueue) && Objects.equals(finish, that.finish);
    }

    @Override
    public int hashCode() {
        return Objects.hash(crystalQueue, maxCrystal, counter, finish);
    }

    public Queue<Crystal> getCrystalQueue() {
        return crystalQueue;
    }

    public int getMaxCrystal() {
        return maxCrystal;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public AtomicBoolean getFinish() {
        return finish;
    }

    public void setFinish(AtomicBoolean finish) {
        this.finish = finish;
    }
}
