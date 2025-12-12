package collinearPoints;

import java.util.ArrayList;
// import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FastCollinearPoints {
    private int numberOfSegments;
    private List<LineSegment> segments;

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

    private List<Point> getRun(Point p, int start, int end, Point[] array) {
        List<Point> run = new ArrayList<Point>();
        run.add(p);
        int i = start;
        while (i < end) {
            run.add(array[i]);
            i++;
        }
        return run;
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
        for (int i = 0; i < points.length; i++) {
            Point[] freshcopy = freshCopy(points);
            Point p = freshcopy[i];
            Comparator<Point> slopeComparator = p.slopeOrder();
            Arrays.sort(freshcopy, slopeComparator);
            // System.out.println(Arrays.toString(freshcopy));

            int start = 1;
            while (start < freshcopy.length) {
                int end = start + 1;  
                while (end < freshcopy.length && p.slopeTo(freshcopy[start]) == p.slopeTo(freshcopy[end])) {
                    end++;
                }
                int runLength = (end - start) + 1;
                if (runLength >= 4) {
                    List<Point> run = getRun(p, start, end, freshcopy);
                    
                    Point minPoint = minPoint(run);
                    Point maxPoint = maxPoint(run);
                    if (p == minPoint) {
                        LineSegment segment = new LineSegment(minPoint, maxPoint);
                        this.segments.add(segment);
                        this.numberOfSegments++;
                    }
                }
                start = end;
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

    }
    
}
