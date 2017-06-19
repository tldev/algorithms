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
            throw new java.lang.NullPointerException();
        }

        Node newFirst = new Node();
        newFirst.item = item;

        if (first != null) {
            newFirst.next = first;
        } else {
            last = newFirst;
        }

        first = newFirst;
        size++;
    }

    // add the item to the end
    public void addLast(Item item) {
        if (item == null) {
            throw new java.lang.NullPointerException();
        }

        Node newLast = new Node();
        newLast.item = item;

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

        size--;

        return oldFirst.item;
    }

    // remove and return the item from the end
    public Item removeLast() {
        if (size == 0) {
            throw new java.util.NoSuchElementException();
        }

        Node previous = null;
        Node current = first;
        while (current.next != null) {
            previous = current;
            current = current.next;
        }

        if (previous != null) {
            previous.next = null;
        } else {
            first = null;
        }

        last = previous;
        size--;

        return current.item;
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

    // unit testing (optional)
    public static void main(String[] args) {

    }
}