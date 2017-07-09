import java.util.Arrays;
import java.util.Stack;

/**
 * Created by tjohnell on 7/9/17.
 */
public class Board {
    private final int[][] blocks;
    private int ham = -1, man = -1;

    // construct a board from an n-by-n array of blocks
    public Board(int[][] blocks) {
        this.blocks = blocks.clone();
    }

    // (where blocks[i][j] = block in row i, column j)
    // board dimension n
    public int dimension() {
        return blocks.length;
    }

    // number of blocks out of place
    public int hamming() {
        if (ham == -1) {
            ham = calcHamming();
        }

        return ham;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        if (man == -1) {
            man = calcManhattan();
        }

        return man;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        return new Board(new int[0][0]);
    }

    // does this board equal y?
    public boolean equals(Object y) {
        return y == null || y.getClass() == getClass() && Arrays.deepEquals(((Board) y).blocks, blocks);
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        return new Stack<Board>();
    }

    // string representation of this board (in the output format specified below)
    public String toString() {
        StringBuilder builder = (new StringBuilder()).append(dimension()).append("\n");
        for (int row = 0; row < dimension(); row++) {
            builder.append(" ");
            for (int column = 0; column < dimension(); column++) {
                builder.append(String.format("%d  ", value(row, column)));
            }
            builder.append("\n");
        }

        return builder.toString();
    }

    private int value(int row, int column) {
        return blocks[row][column];
    }

    private int calcHamming() {
        int misplaced = 0;
        for (int i = 1; i < dimension() * dimension(); i++) {
            if (value(goalRow(i), goalColumn(i)) != i) misplaced++;
        }

        return misplaced;
    }

    private int calcManhattan() {
        int manhattan = 0;
        for (int row = 0; row < dimension(); row++) {
            for (int column = 0; column < dimension(); column++) {
                int value = value(row, column);
                if (value == 0) continue;
                manhattan += Math.abs(row - goalRow(value)) + Math.abs(column - goalColumn(value));
            }
        }

        return manhattan;
    }

    private int goalRow(int value) {
        return (value - 1) / dimension();
    }

    private int goalColumn(int value) {
        return (value - 1) % dimension();
    }

    // unit tests (not graded)
    public static void main(String[] args) {

    }
}
