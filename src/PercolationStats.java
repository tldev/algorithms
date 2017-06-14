import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;;

/**
 * Created by tjohnell on 6/1/17.
 */
public class PercolationStats {
    private int n;
    private int gridSize;
    private int trials;
    private double mean;
    private double stddev;
    private double confidenceLo;
    private double confidenceHi;
    private double[] percs;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("Nope");
        }

        this.n = n;
        this.gridSize = n * n;
        this.trials = trials;
        this.run();
    }

    // sample mean of percolation threshold
    public double mean() {
        return this.mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return this.stddev;
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.confidenceLo;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.confidenceHi;
    }

    private void run() {
        double[] percs = new double[this.trials];
        for (int i = 0; i < this.trials; i++) {
            int[] randomIndexes = StdRandom.permutation(this.gridSize);
            Percolation p = new Percolation(this.n);
            for (int j = 0; j < this.gridSize; j++) {
                int index = randomIndexes[j];
                int row = rowByIndex(index);
                int col = columnByIndex(index);
                p.open(row, col);

                if (p.percolates()) {
                    percs[i] = p.numberOfOpenSites() / (double) this.gridSize;
                    break;
                }
            }
        }

        this.percs = percs;

        this.mean = StdStats.mean(this.percs);
        this.stddev = StdStats.stddev(this.percs);
        double diff = (1.96 * Math.sqrt(this.stddev))/(Math.sqrt(this.trials));
        this.confidenceLo = this.mean - diff;
        this.confidenceHi = this.mean + diff;
    }

    private int rowByIndex(int index) {
        return index / this.n + 1;
    }

    private int columnByIndex(int index) {
        return index % this.n + 1;
    }


    // test client (described below)
    public static void main(String[] args) {
        Integer n = Integer.parseInt(args[0]);
        Integer T = Integer.parseInt(args[1]);

        PercolationStats ps = new PercolationStats(n, T);

        StdOut.printf("mean                    = %f\n", ps.mean());
        StdOut.printf("stdev                   = %f\n", ps.stddev);
        StdOut.printf("95%% confidence interval = [%f, %f]", ps.confidenceLo(), ps.confidenceHi());
    }
}
