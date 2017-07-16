import edu.princeton.cs.algs4.*;

/**
 * Created by tjohnell on 7/16/17.
 */
public class PointSET {
    private SET<Point2D> set;

    // construct an empty set of points
    public PointSET() {
        set = new SET<Point2D>();
    }

    // is the set empty?
    public boolean isEmpty() {
        return set.isEmpty();
    }

    // number of points in the set
    public int size() {
        return set.size();
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        set.add(p);
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        return set.contains(p);
    }

    // draw all points to standard draw
    public void draw() {
        StdDraw.rectangle(0.5, 0.5, 0.5, 0.5);
        for(Point2D point : set) {
            StdDraw.point(point.x(), point.y());
        }
    }

    // all points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new java.lang.IllegalArgumentException();
        }

        Stack<Point2D> points = new Stack<Point2D>();
        for (Point2D point : set) {
            if (rect.contains(point)) {
                points.push(point);
            }
        }

        return points;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new java.lang.IllegalArgumentException();
        }

        Point2D nearest = null;
        double smallestDistance = Double.POSITIVE_INFINITY;
        for (Point2D point : set) {
            double curDistance = p.distanceTo(point);
            if (nearest == null || curDistance < smallestDistance) {
                nearest = point;
                smallestDistance = curDistance;
            }
        }

        return nearest;
    }
}
