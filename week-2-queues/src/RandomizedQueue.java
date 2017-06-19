import java.util.Iterator;

/**
 * Created by tjohnell on 6/18/17.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    // construct an empty randomized queue
    public RandomizedQueue() {

    }

    // is the queue empty?
    public boolean isEmpty() {
        return true;
    }

    // return the number of items on the queue
    public int size()                         {
        return 0;
    }

    // add the item
    public void enqueue(Item item) {

    }

    // remove and return a random item
    public Item dequeue() {
        return null;
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

    public static void main(String[] args) {

    }
}
