package collinearPoints;

import java.util.Arrays;
import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point>{
    private final int x; 
    private final int y;


   public Point(int x, int y) {
        this.x = x;
        this.y = y;
   }                        

   public void draw() {
        StdDraw.point(x, y);
   }  
   
   
   public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
   }                  
   
   public String toString() {
    return "(" + x + "," + y + ")";
   }                          

   public int compareTo(Point that) {
    if (this.x == that.x && this.y == that.y) return 0;
    else if (this.y < that.y || (this.y == that.y && this.x < that.x)) return -1;
    else return 1;
   }

   public double slopeTo(Point that) {
    if (this.x == that.x && this.y == that.y) return Double.NEGATIVE_INFINITY;
    if (this.y  == that.y) return 0.0;
    if (this.x == that.x) return Double.POSITIVE_INFINITY; 
    return (that.y - this.y)/(that.x - this.x);
   }

   public Comparator<Point> slopeOrder() {
    return null;
   }

    public static void main(String[] args) {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(1, 1);

        System.out.println(p2.compareTo(p1));
        Comparator<Point> slopeComparator = p1.slopeOrder();

        Point[] points = {p2, p1};
        Arrays.sort(points, slopeComparator);
    }
}
