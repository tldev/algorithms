import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

/**
 * Created by tjohnell on 6/18/17.
 */
public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> queue = new RandomizedQueue<String>();

        while (true) {
            try {
                queue.enqueue(StdIn.readString());
            } catch (NoSuchElementException e) {
                break;
            }

        }
        for (int i = 0; i < k; i++) {
            StdOut.println(queue.dequeue());
        }
    }
}