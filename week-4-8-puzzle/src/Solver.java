import edu.princeton.cs.algs4.MinPQ;

/**
 * Created by tjohnell on 7/9/17.
 */
public class Solver {
    private MinPQ<Board> queue;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        initial.manhattan();
        queue = new MinPQ<>();
        queue.insert(initial);
    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return false;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return 0;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        return new Board(new int[0][0]).neighbors();
    }

    // solve a slider puzzle (given below)
    public static void main(String[] args) {

    }
}
