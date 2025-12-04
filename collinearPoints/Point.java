package collinearPoints;

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
    return "";
   }                          

   public int compareTo(Point that) {
    return 0;
   }

   public double slopeTo(Point that) {
    return 0.0;
   }

   public Comparator<Point> slopeOrder() {
    return null;
   }           
}
