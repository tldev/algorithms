import edu.princeton.cs.algs4.StdOut;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by tjohnell on 6/1/17.
 */
public class PercolationPrinter {

    public static void percolatingPrinter() {
        int n = 600;
        Percolation perc = new Percolation(n);

        while (!perc.percolates()) {
            int row = ThreadLocalRandom.current().nextInt(1, n + 1);
            int col = ThreadLocalRandom.current().nextInt(1, n + 1);
            perc.open(row, col);
        }

        StdOut.println("WE'RE FUCKING PERCOLATING BABY\n");
        PercolationPrinter.printGrid(perc.grid);
    }

    public static void printGrid(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] == 1) {
                    StdOut.print("O ");
                } else {
                    StdOut.print(". ");
                }
            }
            StdOut.println();
        }
        StdOut.println();
    }
}
