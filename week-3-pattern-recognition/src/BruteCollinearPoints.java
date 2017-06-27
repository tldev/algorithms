import java.util.Arrays;
import java.util.Stack;

public class BruteCollinearPoints {

    private static final int MAX_MATCHES = 4;
    private LineSegment[] segments;
    private Point[] points;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        Stack<LineSegment> lineSegments = new Stack<LineSegment>();
        Arrays.sort(points);

        for(int i = 0; i < points.length - 3; i ++) {
            for (int j = i + 1; j < points.length - 2; j++) {
                double ij = points[i].slopeTo(points[j]);
                for (int k = j + 1; k < points.length - 1; k++) {
                    double ik = points[i].slopeTo(points[k]);
                    if (ik == ij) {
                        for (int l = k + 1; l < points.length; l++) {
                            double il = points[i].slopeTo(points[l]);
                            if (il == ij) {
                                lineSegments.push(new LineSegment(points[i], points[j]));
                            }
                        }
                    }
                }
            }
        }

        segments = (LineSegment[]) lineSegments.toArray();
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.length;
    }

    // the line segments
    public LineSegment[] segments() {
        return segments;
    }
}
