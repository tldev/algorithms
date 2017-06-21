import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by tjohnell on 6/18/17.
 */
public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> queue = new RandomizedQueue<String>();

        for (int i = 0; i < k; i++) {
            queue.enqueue(StdIn.readString());
        }

        for (String s : queue) {
            StdOut.println(s);
        }
    }
}