package collinearPoints;

import java.util.ArrayList;
// import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FastCollinearPoints {
    private int numberOfSegments;
    private List<LineSegment> segments;
    private List<Point> run;

    private Point[] sort(Point[] points) {
        Point[] answer = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            answer[i] = points[i];
        }
        for (int i = 0; i < answer.length-1; i++) {
            for (int j = i + 1; j < answer.length; j++) {
                if (answer[i].compareTo(answer[j]) > 0) {
                    Point temp = answer[i];
                    answer[i] = answer[j];
                    answer[j] = temp;
                }
            }
        } 
        return answer;
    }

    private Point[] freshCopy(Point[] points) {
        Point[] copy = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            copy[i] = points[i];
        }
        return copy; 
    }

    private Point minPoint(List<Point> run) {
        Point min = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
        for (int i = 0; i < run.size(); i++) {
            if (run.get(i).compareTo(min) < 0) {
                min = run.get(i);
            }
        }
        return min;
    }

    private Point maxPoint(List<Point> run) {
        Point max = new Point(Integer.MIN_VALUE, Integer.MIN_VALUE);
        for (int i = 0; i < run.size(); i++) {
            if (run.get(i).compareTo(max) > 0) {
                max = run.get(i);
            }
        }
        return max;
    }
    
    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException();
            }
        } 
        Point[] sorted_points = sort(points);
        for (int i = 1; i < sorted_points.length; i++) {
            if (sorted_points[i-1].compareTo(sorted_points[i]) == 0) {
                throw new IllegalArgumentException();
            }
        }

        segments = new ArrayList<LineSegment>();
        run = new ArrayList<Point>();
        for (int i = 0; i < points.length; i++) {
            Point[] freshcopy = freshCopy(points);
            Point p = freshcopy[i];
            Point minPoint = p;
            Point maxPoint = p;
            Comparator<Point> slopeComparator = p.slopeOrder();
            Arrays.sort(freshcopy, slopeComparator);            
            for (int j = 0; j < freshcopy.length; j++) {
                this.run.clear();
                int current = j;
                int runStart = 0;
                int runEnd = 0; 
                this.run.add(p);
                while (current < freshcopy.length-1 && p.slopeTo(freshcopy[current]) == p.slopeTo(freshcopy[current+1])) {
                    runEnd++;
                    if (!this.run.contains(freshcopy[current])) {
                        this.run.add(freshcopy[current]);
                    }
                    if (!this.run.contains(freshcopy[current+1])) {
                        this.run.add(freshcopy[current+1]);
                    }
                    if ((runEnd - runStart + 1) >= 3) {

                        minPoint = minPoint(run);
                        maxPoint = maxPoint(run);
                        // System.out.println(minPoint);
                        // System.out.println(maxPoint);

                        LineSegment segment = new LineSegment(minPoint, maxPoint);
                        
                        if (p == minPoint && !this.segments.contains(segment)) {
                            this.segments.add(segment);
                            this.numberOfSegments++;
                        }
                        // System.out.println(this.segments);
                    }
                    current++;
                }
                // System.out.println(this.run);
            }
        }
        
        // System.out.println(Arrays.toString(points));
        // Point[] slope_sorted = slopeSort(points);
        // System.out.println(Arrays.toString(slope_sorted));

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
        Point p5 = new Point(1, 1);
        Point p4 = new Point(2, 2);
        Point p3 = new Point(3, 3);
        Point p1 = new Point(4, 4);
        Point p2 = new Point(1, 3);
        Point p0 = new Point(2, 1);

        Point[] points = {p0,p1,p2,p3,p4,p5};
        FastCollinearPoints fast = new FastCollinearPoints(points);
        System.out.println(fast.numberOfSegments());

        LineSegment segment = new LineSegment(p5, p1);
        List<LineSegment> segments = new ArrayList<LineSegment>();

        // System.out.println(segments);
        // segments.add(segment);
        // System.out.println(segments);
        // System.out.println(segments.contains(segment));
        




        // segments.add();


    }
    
}
