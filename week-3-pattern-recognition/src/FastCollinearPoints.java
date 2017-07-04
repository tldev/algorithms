import java.util.Arrays;
import java.util.Stack;

/**
 * Created by tjohnell on 6/26/17.
 */
public class FastCollinearPoints {
    private final LineSegment[] segments;

    // finds all line segments containing 4 points
    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new java.lang.IllegalArgumentException();
        }

        for (Point p : points) {
            if (p == null) {
                throw new java.lang.IllegalArgumentException();
            }
        }

        points = points.clone();

        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i + 1]) == 0) {
                throw new java.lang.IllegalArgumentException();
            }
        }

        Stack<LineSegment> lineSegments = new Stack<>();

        for (int i = 0; i < points.length - 3; i++) {
            Point basePoint = points[i];
            Arrays.sort(points, basePoint.slopeOrder());

            int min = 1;
            while (min < points.length) {
                int max = min;
                while (max < points.length
                        && Double.compare(basePoint.slopeTo(points[min]), basePoint.slopeTo(points[max])) == 0) {
                    max++;
                }

                int size = max - min;
                if (size >= 3) {
                    Point[] newPoints = new Point[size];
                    System.arraycopy(points, min, newPoints, 0, size);
                    Arrays.sort(newPoints);
                    if (basePoint.compareTo(newPoints[0]) < 0) {
                        lineSegments.push(new LineSegment(basePoint, newPoints[size - 1]));
                    }
                }

                min = max;
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
