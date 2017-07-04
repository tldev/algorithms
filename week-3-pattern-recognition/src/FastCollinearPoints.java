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
            Point[] pointCandidates = new Point[points.length - i - 1];
            int k = 0;
            for (int j = i + 1; j < points.length; j++) {
                pointCandidates[k++] = points[j];
            }

            Arrays.sort(pointCandidates, basePoint.slopeOrder());

            int currentCount = 2;
            for (int m = 1; m < pointCandidates.length; m++) {
                if (isEqualSlopes(basePoint.slopeTo(pointCandidates[m]), basePoint.slopeTo(pointCandidates[m - 1]))) {
                    currentCount++;
                } else {
                    if (currentCount >= 4) {
                        Point[] sortedPoints = new Point[currentCount];
                        sortedPoints[0] = basePoint;
                        System.arraycopy(pointCandidates, m - currentCount + 1, sortedPoints, 1, currentCount - 1);
                        Arrays.sort(sortedPoints);

                        lineSegments.push(new LineSegment(sortedPoints[0], sortedPoints[currentCount - 1]));
                    }

                    currentCount = 2;
                }
            }

            if (currentCount >= 4) {
                Point[] sortedPoints = new Point[currentCount];
                sortedPoints[0] = basePoint;
                System.arraycopy(pointCandidates, pointCandidates.length - currentCount + 1, sortedPoints, 1, currentCount - 1);
                Arrays.sort(sortedPoints);

                lineSegments.push(new LineSegment(sortedPoints[0], sortedPoints[currentCount - 1]));
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
