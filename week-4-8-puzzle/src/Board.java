import java.util.Arrays;
import java.util.Stack;

/**
 * Created by tjohnell on 7/9/17.
 */
public class Board {
    private final int[][] blocks;
    private int ham = -1, man = -1, zRow = -1, zColumn = -1;

    // construct a board from an n-by-n array of blocks
    public Board(int[][] blocks) {
        this.blocks = blocksClone(blocks);
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
        return new Board(copyAndSwap(0, 0, 0, 1));
    }

    // does this board equal y?
    public boolean equals(Object y) {
        return y == null || y.getClass() == getClass() && Arrays.deepEquals(((Board) y).blocks, blocks);
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        Stack<Board> neighbors = new Stack<>();
        int emptyRow = zeroRow();
        int emptyColumn = zeroColumn();
        int[][] offsets = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        for (int[] offset : offsets) {
            int newEmptyRow = emptyRow + offset[0];
            int newEmptyColumn = emptyColumn + offset[1];

            if (newEmptyRow >= 0 && newEmptyRow < dimension() && newEmptyColumn >= 0 && newEmptyColumn < dimension())
                neighbors.push(new Board(copyAndSwap(emptyRow, emptyColumn, newEmptyRow, newEmptyColumn)));
        }

        return neighbors;
    }

    // string representation of this board (in the output format specified below)
    public String toString() {
        StringBuilder builder = (new StringBuilder()).append(dimension()).append("\n");
        for (int row = 0; row < dimension(); row++) {
            builder.append(" ");
            for (int column = 0; column < dimension(); column++) {
                builder.append(String.format("%2d ", value(row, column)));
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
            int gRow = goalRow(i);
            int gColumn = goalColumn(i);
            int currentValue = value(gRow, gColumn);

            if (currentValue == 0) {
                setZeroRowAndColumn(gRow, gColumn);
            }

            if (currentValue != i) misplaced++;
        }

        return misplaced;
    }

    private int calcManhattan() {
        int manhattan = 0;
        for (int row = 0; row < dimension(); row++) {
            for (int column = 0; column < dimension(); column++) {
                int value = value(row, column);
                if (value == 0) {
                    setZeroRowAndColumn(row, column);
                    continue;
                }
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

    private int zeroRow() {
        if (zRow == -1) {
            findAndSetZeroRowAndColumn();
        }

        return zRow;
    }

    private int zeroColumn() {
        if (zColumn == -1) {
            findAndSetZeroRowAndColumn();
        }

        return zColumn;
    }

    private void findAndSetZeroRowAndColumn() {
        for (int row = 0; row < dimension(); row++) {
            for (int column = 0; column < dimension(); column++) {
                if (value(row, column) == 0) setZeroRowAndColumn(row, column);
            }
        }
    }

    private void setZeroRowAndColumn(int row, int column) {
        zRow = row;
        zColumn = column;
    }

    private int[][] copyAndSwap(int p1Row, int p1Column, int p2Row, int p2Column) {
        int[][] blocksCopy = blocksClone(blocks);
        int temp = blocksCopy[p1Row][p1Column];
        blocksCopy[p1Row][p1Column] = blocksCopy[p2Row][p2Column];
        blocksCopy[p2Row][p2Column] = temp;

        return blocksCopy;
    }

    private int[][] blocksClone(int[][] originalBlocks) {
        int[][] blocksCopy = originalBlocks.clone();
        for (int i = 0; i < originalBlocks.length; i++) {
            blocksCopy[i] = originalBlocks[i].clone();
        }

        return blocksCopy;
    }

    // unit tests (not graded)
    public static void main(String[] args) {

    }
}
