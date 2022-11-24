package homework;

import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

public class AirConsumer implements Runnable {

    private Queue<Crystal> crystalQueue;
    private int capacity;
    private int counter;
    private AtomicBoolean finish;


    public AirConsumer(Queue<Crystal> crystalQueue, int capacity, int counter, AtomicBoolean finish) {
        this.crystalQueue = crystalQueue;
        this.capacity = capacity;
        this.counter = counter;
        this.finish = finish;
    }

    @Override
    public void run() {
        while (counter < capacity && finish.get()) {
            synchronized (crystalQueue) {
                if (!crystalQueue.isEmpty()) {
                    Crystal crystal = crystalQueue.peek();
                    if (crystal.getColor() == CrystalColor.WHITE) {
                        crystalQueue.poll();
                        counter++;
                        System.out.println("У расы Воздуха всего кристолов " + counter);
                    } else {
                        System.out.println("Раса Воздуха не нашла белых кристаллов");
                    }
                }

            }
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (counter>=capacity) {
                finish.getAndSet(false);
            }
        }
    }

    @Override
    public String toString() {
        return "AirConsumer{" +
                "crystalQueue=" + crystalQueue +
                ", capacity=" + capacity +
                ", counter=" + counter +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AirConsumer that = (AirConsumer) o;
        return capacity == that.capacity && counter == that.counter && Objects.equals(crystalQueue, that.crystalQueue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(crystalQueue, capacity, counter);
    }

    public Queue<Crystal> getCrystalQueue() {
        return crystalQueue;
    }

    public void setCrystalQueue(Queue<Crystal> crystalQueue) {
        this.crystalQueue = crystalQueue;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
