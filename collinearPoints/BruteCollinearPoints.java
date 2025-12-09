package collinearPoints;

import java.util.ArrayList;
// import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {
    private int numberOfSegments;
    private List<LineSegment> segments;

    private Point[] sort(Point[] points) {
        for (int i = 0; i < points.length-1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 1) {
                    Point temp = points[i];
                    points[i] = points[j];
                    points[j] = temp;
                }
            }
        } 
        return points;
    }

    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException();
            }
        }
        
        Point[] sorted_points = sort(points);
        // System.out.println(Arrays.toString(points));
        for (int i = 1; i < sorted_points.length; i++) {
            if (sorted_points[i-1].compareTo(sorted_points[i]) == 0) {
                throw new IllegalArgumentException();
            }
        }

        segments = new ArrayList<LineSegment>();

        for (int i = 0; i < sorted_points.length-3; i++) {
            for (int j = i+1; j < sorted_points.length-2; j++) {
                for (int k = j + 1; k < sorted_points.length-1; k++) {
                    for (int l = k + 1; l < sorted_points.length; l++) {
                        double ij = sorted_points[i].slopeTo(sorted_points[j]);
                        double ik = sorted_points[i].slopeTo(sorted_points[k]);
                        double il = sorted_points[i].slopeTo(sorted_points[l]);
                        if (ij == ik && ik == il && ij == il && ij != Double.NEGATIVE_INFINITY) {
                            Point[] temp = {sorted_points[i], sorted_points[j], sorted_points[k], sorted_points[l]};
                            Point[] sorted_temp = sort(temp);
                            Point p0 = sorted_temp[0];
                            Point p3 = sorted_temp[3];
                            LineSegment segment = new LineSegment(p0, p3);
                            segments.add(segment);
                            numberOfSegments++;
                        }
                    }
                }
            }
        }
    }   
    
    public int numberOfSegments() {
        return this.numberOfSegments;
    }   

    public LineSegment[] segments() {
        LineSegment[] answer = new LineSegment[this.numberOfSegments];
        
        for (int i = 0; i < answer.length; i++) {
            answer[i] = this.segments.get(i);
        }
        // System.out.println(answer);

        return answer;
    }

    public static void main(String[] args) {
        Point p0 = new Point(0, 0);
        Point p1 = new Point(1, 1);
        Point p2 = new Point(2, 2);
        Point p3 = new Point(3, 3);

        Point[] Points = {p1, p3, p0, p2};

        BruteCollinearPoints brute = new BruteCollinearPoints(Points);
        System.out.println(brute.numberOfSegments);
        System.out.println(brute.segments);

    }
}
