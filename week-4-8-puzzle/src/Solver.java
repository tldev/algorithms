import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.ResizingArrayStack;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by tjohnell on 7/9/17.
 */
public class Solver {
    private MinPQ<SearchNode> queue;
    private MinPQ<SearchNode> queueTwin;
    private boolean hasRun = false;
    private Iterable<Board> solution;
    private int numMoves = -1;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        initial.manhattan();
        queue = new MinPQ<>();
        queue.insert(new SearchNode(initial, 0, null));
        queueTwin = new MinPQ<>();
        queueTwin.insert(new SearchNode(initial.twin(), 0, null));
    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return solution() != null;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        solution();
        return numMoves;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (hasRun) return solution;
        SearchNode node = null;
        SearchNode nodeTwin = null;
        while (!queue.isEmpty() && !queueTwin.isEmpty()) {
            node = queue.delMin();
            nodeTwin = queueTwin.delMin();

            if (node.b.isGoal()) break;
            if (nodeTwin.b.isGoal()) break;

            for (Board n : node.b.neighbors()) {
                if (node.lastSearchNode == null || !node.lastSearchNode.b.equals(n)) {
                    queue.insert(new SearchNode(n, node.numMoves + 1, node));
                }
            }

            for (Board n : nodeTwin.b.neighbors()) {
                if (nodeTwin.lastSearchNode == null || !nodeTwin.lastSearchNode.b.equals(n)) {
                    queueTwin.insert(new SearchNode(n, nodeTwin.numMoves + 1, nodeTwin));
                }
            }
        }

        if (node == null && nodeTwin == null) {
            return null;
        }

        if (nodeTwin.b.isGoal()) {
            return null;
        }

        numMoves = node.numMoves;

        ResizingArrayStack<Board> solution = new ResizingArrayStack<>();
        while (node != null) {
            solution.push(node.b);
            node = node.lastSearchNode;
        }

        hasRun = true;
        this.solution = solution;

        return this.solution;
    }

    private class SearchNode implements Comparable<SearchNode> {
        private Board b;
        private int numMoves;
        public SearchNode lastSearchNode;

        public SearchNode(Board b, int numMoves, SearchNode lastSearchNode) {
            this.b = b;
            this.numMoves = numMoves;
            this.lastSearchNode = lastSearchNode;
        }

        public int manhattan() {
            return b.manhattan() + numMoves;
        }


        public int compareTo(SearchNode o) {
            return Integer.compare(manhattan(), o.manhattan());
        }
    }

    public static void main(String[] args) {

    }
}
