import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/**
 * Created by tjohnell on 6/18/17.
 */
public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size;

    private class Node {
        Item item;
        Node next;
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

        Node new_first = new Node();
        new_first.item = item;

        if (first != null) {
            new_first.next = first;
        } else {
            last = new_first;
        }

        first = new_first;
        size++;
    }

    // add the item to the end
    public void addLast(Item item) {
        if (item == null) {
            throw new java.lang.NullPointerException();
        }

        Node new_last = new Node();
        new_last.item = item;

        if (last != null) {
            last.next = new_last;
        } else {
            first = new_last;
        }

        last = new_last;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (size == 0) {
            throw new java.util.NoSuchElementException();
        }

        Node old_first = first;
        first = first.next;

        size--;

        return old_first.item;
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

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            Node current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Item next() {
                if (!hasNext()) {
                    throw new java.util.NoSuchElementException();
                }

                Item item = current.item;
                current = current.next;

                return item;
            }

            @Override
            public void remove() {
                throw new java.lang.UnsupportedOperationException();
            }
        };
    }

    // unit testing (optional)
    public static void main(String[] args) {

    }
}