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
        Stack<LineSegment> lineSegments = new Stack<>();
        Arrays.sort(points);

        for (int i = 0; i < points.length - 3; i++) {
            for (int j = i + 1; j < points.length - 2; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    continue;
                }
                double ij = points[i].slopeTo(points[j]);
                for (int k = j + 1; k < points.length - 1; k++) {
                    if(points[j].compareTo(points[k]) == 0) {
                        continue;
                    }
                    double ik = points[i].slopeTo(points[k]);
                    if (isEqualSlopes(ik, ij)) {
                        for (int l = k + 1; l < points.length; l++) {
                            if(points[k].compareTo(points[l]) == 0) {
                                continue;
                            }
                            double il = points[i].slopeTo(points[l]);
                            if (isEqualSlopes(il, ij)) {
                                lineSegments.push(new LineSegment(points[i], points[l]));
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

    private boolean isEqualSlopes(double slope1, double slope2) {
        return slope1 == slope2 || Math.abs(slope1 - slope2) < 0.000001;
    }
}
