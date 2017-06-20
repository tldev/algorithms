import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

/**
 * Created by tjohnell on 6/18/17.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private static final int INITIAL_ARRAY_SIZE = 10;

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
        return true;
    }

    // return the number of items on the queue
    public int size()                         {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (tail == queue.length) {
            tail = 0;
        }

        queue[tail++] = item;
        ++size;

        // We need to up the capacity
        if (size > queue.length / 2.0) {
            changeQueueSize(queue.length * 2);
        }
    }

    // remove and return a random item
    public Item dequeue() {
        Item item = queue[head];
        queue[head++] = null;
        --size;

        if (head == queue.length) {
            head = 0;
        }

        if (size < queue.length / 4.0) {
            changeQueueSize(queue.length / 2);
        }

        return item;
    }

    // return (but do not remove) a random item
    public Item sample() {
        return null;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public Item next() {
                return null;
            }

            @Override
            public void remove() {

            }
        };
    }

    private void changeQueueSize(int newSize) {
        Item[] newQueue = createArray(newSize);
        int j = 0;
        if (head > tail) {
            for(int i = head; i < queue.length; i++) {
                newQueue[j++] = queue[i];
            }
            for(int i = 0; i < tail; i++) {
                newQueue[j++] = queue[i];
            }
        } else {
            for(int i = head; i < tail; i++) {
                newQueue[j++] = queue[i];
            }
        }

        queue = newQueue;
        head = 0;
        tail = j;
    }

    private Item[] createArray(int size) {
        return (Item[])new Object[size];
    }

    public static void main(String[] args) {

    }
}
