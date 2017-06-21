import java.util.Iterator;

/**
 * Created by tjohnell on 6/18/17.
 */
public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size;

    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }

    // construct an empty deque
    public Deque() {

    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new java.lang.IllegalArgumentException();
        }

        Node newFirst = new Node();
        newFirst.item = item;
        newFirst.prev = null;
        newFirst.next = first;

        if (first != null) {
            first.prev = newFirst;
        } else {
            last = newFirst;
        }

        first = newFirst;
        size++;
    }

    // add the item to the end
    public void addLast(Item item) {
        if (item == null) {
            throw new java.lang.IllegalArgumentException();
        }

        Node newLast = new Node();
        newLast.item = item;
        newLast.next = null;
        newLast.prev = last;

        if (last != null) {
            last.next = newLast;
        } else {
            first = newLast;
        }

        last = newLast;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (size == 0) {
            throw new java.util.NoSuchElementException();
        }

        Node oldFirst = first;
        first = first.next;

        if (first == null) {
            last = null;
        } else {
            first.prev = null;
        }

        size--;

        return oldFirst.item;
    }

    // remove and return the item from the end
    public Item removeLast() {
        if (size == 0) {
            throw new java.util.NoSuchElementException();
        }

        Node oldLast = last;
        last = oldLast.prev;

        if (last == null) {
            first = null;
        } else {
            last.next = null;
        }

        size--;

        return oldLast.item;
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }

            Item item = current.item;
            current = current.next;

            return item;
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }
}
