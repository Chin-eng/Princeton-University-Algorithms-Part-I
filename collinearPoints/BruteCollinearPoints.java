package collinearPoints;

import java.util.Arrays;

public class BruteCollinearPoints {
    private int numberOfSegments;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException();
            }
        }
        
        for (int i = 0; i < points.length-1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 1) {
                    Point temp = points[i];
                    points[i] = points[j];
                    points[j] = temp;
                }
            }
        } 
        // System.out.println(Arrays.toString(points));
        for (int i = 1; i < points.length; i++) {
            if (points[i-1].compareTo(points[i]) == 0) {
                throw new IllegalArgumentException();
            }
        }

        
    }   
    
    public int numberOfSegments() {
        return this.numberOfSegments;
    }   

    public LineSegment[] segments() {
        return null;
    }

    public static void main(String[] args) {
        Point p0 = new Point(0, 0);
        Point p1 = new Point(1, 1);
        Point p2 = new Point(2, 2);
        Point p3 = new Point(3, 3);

        Point[] Points = {p1, p3, p0, p2};

        BruteCollinearPoints brute = new BruteCollinearPoints(Points);
        
    }
}
