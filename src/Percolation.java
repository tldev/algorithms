import edu.princeton.cs.algs4.QuickFindUF;

/**
 * Created by tjohnell on 5/31/17.
 */
public class Percolation {
    private int n;
    private QuickFindUF qf;
    private int[][] grid;
    private int num_open;


    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        this.n = n;
        this.num_open = 0;
        this.grid = new int[n][n];
        this.qf = new QuickFindUF(n * n + 2);
        this.unionTopAndBottomRows();
    }

    public void open(int row, int col) {
        validatePosition(row, col);

        openGrid(row, col);
        this.num_open++;

        int current_index = this.gridToIndex(row, col);

        // Union above
        if (row > 1 && this.isOpen(row - 1, col)) {
            qf.union(current_index, this.gridToIndex(row - 1, col));
        }

        // Union right
        if (col < this.n && this.isOpen(row, col + 1)) {
            qf.union(current_index, this.gridToIndex(row, col + 1));
        }

        // Union bottom
        if (row < this.n && this.isOpen(row + 1, col)) {
            qf.union(current_index, this.gridToIndex(row + 1, col));
        }

        // Union left
        if (col > 1 && this.isOpen(row, col - 1)) {
            qf.union(current_index, this.gridToIndex(row, col - 1));
        }
    }

    public boolean isOpen(int row, int col) {
        validatePosition(row, col);

        return isGridOpen(row, col) == 1;
    }

    public boolean isFull(int row, int col) {
        validatePosition(row, col);

        return this.qf.connected(this.gridToIndex(row, col), this.topSiteIndex());
    }

    public int numberOfOpenSites() {
        return this.num_open;
    }

    public boolean percolates() {
        return qf.connected(this.topSiteIndex(), this.bottomSiteIndex());
    }

    private void validatePosition(int row, int col) {
        if (row < 1 || row > this.n || col < 1 || col > this.n) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void unionTopRow() {
        for (int i = 1; i <= this.n; i++) {
            this.open(1, i);
        }
    }

    private void unionTopAndBottomRows() {
        for (int i = 1; i <= this.n; i++) {
            qf.union(gridToIndex(1, i), this.topSiteIndex());
            qf.union(gridToIndex(this.n, i), this.bottomSiteIndex());
        }
    }

    private int bottomSiteIndex() {
        return (this.n * this.n) + 1;
    }

    private int topSiteIndex() {
        return (this.n * this.n);
    }

    private void openGrid(int row, int col) {

        this.grid[row - 1][col - 1] = 1;
    }

    private int isGridOpen(int row, int col) {
        return this.grid[row - 1][col - 1];
    }

    private int gridToIndex(int row, int col) {
        return this.n * (row - 1) + col - 1;
    }

    public static void main(String[] args) {

    }
}
