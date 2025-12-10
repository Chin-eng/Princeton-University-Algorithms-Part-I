package collinearPoints;

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

    private Point minPoint(Point currentMin, Point newMin) {
        if (currentMin.compareTo(newMin) < 0) {
            return currentMin;
        } else {
            return newMin;
        }
        
    }

    private Point maxPoint(Point curentMax, Point newMax) {
        if (curentMax.compareTo(newMax) > 0) {
            return curentMax;
        } else {
            return newMax;
        }
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

        for (int i = 0; i < points.length; i++) {
            Point[] freshcopy = freshCopy(points);
            Comparator<Point> slopeComparator = freshcopy[i].slopeOrder();
            Arrays.sort(freshcopy, slopeComparator);            
            for (int j = 0; j < freshcopy.length; j++) {
                int current = j;
                // System.out.println(current);

                Point minPoint = freshcopy[current];
                Point maxPoint = freshcopy[current];
                // System.out.println(Arrays.toString(freshcopy));
            
                // System.out.println(freshcopy.length-1);

                while (current < freshcopy.length-1 && Math.abs(freshcopy[current].slopeTo(freshcopy[current+1])) == Math.abs((freshcopy[current+1].slopeTo(freshcopy[current])))) {
                    
                    // System.out.println(current);

                    // System.out.println(current);

                    minPoint = minPoint(minPoint, freshcopy[current+1]);
                    maxPoint = maxPoint(maxPoint, freshcopy[current+1]);

                    if (current >= 3) {
                        LineSegment segment = new LineSegment(minPoint, maxPoint);
                        
                        System.out.println(segment);

                        if (segment != null && !segments.contains(segment)) {
                            segments.add(segment);
                        }
                        numberOfSegments++;
                    }
                     current++;
                }
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
        return null;
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
        // System.out.println(fast.numberOfSegments());

    }
    
}
