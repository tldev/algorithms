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

        Point[] pointsClone = points.clone();

        for (int i = 0; i < pointsClone.length - 1; i++) {
            if (pointsClone[i].compareTo(pointsClone[i + 1]) == 0) {
                throw new java.lang.IllegalArgumentException();
            }
        }

        Stack<LineSegment> lineSegments = new Stack<>();

        for (Point point : points) {
            // Sub-sort by min to max
            Arrays.sort(pointsClone);
            // Sort by slope
            Arrays.sort(pointsClone, point.slopeOrder());

            int min = 1;
            while (min < pointsClone.length) {
                int max = min + 1;
                while (max < pointsClone.length
                        && Double.compare(point.slopeTo(pointsClone[min]), point.slopeTo(pointsClone[max])) == 0) {
                    max++;
                }

                // If at least 3 points in a row (total 4), and the current test point is the min,
                // create the line segment.  Testing the current test point is the min ensures we do not
                // attempt to add subsets to this line segment
                if (max - min >= 3 && point.compareTo(pointsClone[min]) < 0) {
                    lineSegments.push(new LineSegment(point, pointsClone[max - 1]));
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
