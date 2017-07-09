import java.util.Arrays;
import java.util.Stack;

public class BruteCollinearPoints {

    private final LineSegment[] segments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new java.lang.IllegalArgumentException();
        }

        for (Point p : points) {
            if (p == null) {
                throw new java.lang.IllegalArgumentException();
            }
        }

        points = points.clone();
        Arrays.sort(points);

        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i + 1]) == 0) {
                throw new java.lang.IllegalArgumentException();
            }
        }

        Stack<LineSegment> lineSegments = new Stack<>();

        for (int i = 0; i < points.length - 3; i++) {
            for (int j = i + 1; j < points.length - 2; j++) {
                double ij = points[i].slopeTo(points[j]);
                for (int k = j + 1; k < points.length - 1; k++) {
                    double ik = points[i].slopeTo(points[k]);
                    if (Double.compare(ik, ij) == 0) {
                        for (int m = k + 1; m < points.length; m++) {
                            double im = points[i].slopeTo(points[m]);
                            if (Double.compare(im, ij) == 0) {
                                lineSegments.push(new LineSegment(points[i], points[m]));
                            }
                        }
                    }
                }
            }
        }

        segments = new LineSegment[lineSegments.size()];
        int i = 0;
        for (LineSegment segment : lineSegments) {
            segments[i++] = segment;
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.length;
    }

    // the line segments
    public LineSegment[] segments() {
        return segments.clone();
    }
}
