import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double[] thresholds;
    private int totalTrails;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        this.totalTrails = trials;
        this.thresholds = new double[trials];
        for (int T = 0; T < trials; T++) {
            Percolation perc = new Percolation(n);
            while (!perc.percolates()) {
                perc.open(StdRandom.uniformInt(1, n+1), StdRandom.uniformInt(1, n+1));
            }
            double treshold = ((double) perc.numberOfOpenSites() / (n*n));
            thresholds[T] = treshold;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(thresholds);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return StdStats.mean(thresholds) - (1.96 * StdStats.stddev(thresholds)/ Math.sqrt(totalTrails));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return StdStats.mean(thresholds) + (1.96 * StdStats.stddev(thresholds)/ Math.sqrt(totalTrails));
    }

   // test client (see below)
   public static void main(String[] args) {

        int n = Integer.parseInt(args[0]);
        int trails = Integer.parseInt(args[1]);
        
        PercolationStats stats = new PercolationStats(n, trails);

        System.out.println("mean  = " + stats.mean());
        System.out.println("stdev = " + stats.stddev());
        System.out.println("95% confidence interval = [" 
                    + stats.confidenceLo() + ", " 
                    + stats.confidenceHi() + "]");
   }

}