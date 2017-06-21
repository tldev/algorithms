import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;

/**
 * Created by tjohnell on 6/18/17.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private static final int INITIAL_ARRAY_SIZE = 8;

    private int head;
    private int tail;
    private int size;
    private Item[] queue;

    // construct an empty randomized queue
    public RandomizedQueue() {
        queue = createArray(INITIAL_ARRAY_SIZE);
        tail = 0;
        size = 0;
    }

    // is the queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new java.lang.NullPointerException();
        }

        queue[tail] = item;
        ++size;

        // So random, many wows
        swap(tail, randomIndex());
        tail = (++tail % queue.length);

        // We need to up the capacity
        if (size > queue.length / 2) {
            changeQueueSize(queue.length * 2);
        }
    }

    // remove and return a random item
    public Item dequeue() {
        if (size == 0) {
            throw new java.util.NoSuchElementException();
        }

        Item item = queue[head];
        queue[head] = null;
        --size;

        head = (++head % queue.length);

        if (size < queue.length / 4) {
            changeQueueSize(queue.length / 2);
        }

        return item;
    }

    // return (but do not remove) a random item
    public Item sample() {
        if (size == 0) {
            throw new java.util.NoSuchElementException();
        }

        return queue[randomIndex()];
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int[] randomIndexes = StdRandom.permutation(size);
        private int current = 0;

        public boolean hasNext() {
            return current < size;
        }

        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }

            return queue[(head + randomIndexes[current++]) % queue.length];
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private int randomIndex() {
        return (head + StdRandom.uniform(size)) % queue.length;
    }

    private void swap(int i, int j) {
        Item temp = queue[i];
        queue[i] = queue[j];
        queue[j] = temp;
    }

    private void changeQueueSize(int max) {
        Item[] newQueue = createArray(max);
        for (int i = 0; i < size; i++) {
            newQueue[i] = queue[(head + i) % queue.length];
        }

        queue = newQueue;
        head = 0;
        tail = size;
    }

    private Item[] createArray(int size) {
        return (Item[]) new Object[size];
    }

    public static void main(String[] args) {

    }
}
