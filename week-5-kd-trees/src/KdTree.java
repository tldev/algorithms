import edu.princeton.cs.algs4.*;

/**
 * Created by tjohnell on 7/16/17.
 */
public class KdTree {
    private Node root;
    private int size;

    // construct an empty set of points
    public KdTree() {
        size = 0;
    }

    // is the set empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // number of points in the set
    public int size() {
        return size;
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException();

        root = put(root, p, 0);
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();

        return _contains(root, p, 0);
    }

    // draw all points to standard draw
    public void draw() {
        StdDraw.rectangle(0.5, 0.5, 0.5, 0.5);
        for (Point2D point : points()) {
            StdDraw.point(point.x(), point.y());
        }
    }

    // all points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new java.lang.IllegalArgumentException();
        }

        Queue<Point2D> points = new Queue<Point2D>();
        rangeAndEnqueue(root, rect, 0, points);

        return points;
    }

    private Iterable<Point2D> points() {
        Queue<Point2D> points = new Queue<Point2D>();
        inorder(root, points);

        return points;
    }

    private void inorder(Node x, Queue<Point2D> q) {
        if (x == null) return;
        inorder(x.left, q);
        q.enqueue(x.point);
        inorder(x.right, q);
    }

    private void rangeAndEnqueue(Node h, RectHV rect, int level, Queue<Point2D> q) {
        if (h == null) return;


        if (rect.contains(h.point)) {
            q.enqueue(h.point);
        }

        if (level % 2 == 0) {
            if (Double.compare(rect.xmax(), h.point.x()) >= 0) {
                rangeAndEnqueue(h.right, rect, level + 1, q);
            }

            if (Double.compare(rect.xmin(), h.point.x()) < 0) {
                rangeAndEnqueue(h.left, rect, level + 1, q);
            }
        } else {
            if (Double.compare(rect.ymax(), h.point.y()) >= 0) {
                rangeAndEnqueue(h.right, rect, level + 1, q);
            }

            if (Double.compare(rect.ymin(), h.point.y()) < 0) {
                rangeAndEnqueue(h.left, rect, level + 1, q);
            }
        }
    }


    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new java.lang.IllegalArgumentException();
        }

        return root.point;
    }


    private class Node {
        Point2D point;
        int level;
        Node left, right;

        Node(Point2D point, int level) {
            this.level = level;
            this.point = point;
        }
    }

    private Node put(Node h, Point2D point, int level) {
        if (h == null) {
            size++;
            return new Node(point, level);
        }
        int cmp = level % 2 == 0 ? Double.compare(h.point.x(), point.x()) : Double.compare(h.point.y(), point.y());

        if (cmp > 0) h.left = put(h.left, point, level + 1);
        else if (h.point.compareTo(point) != 0) h.right = put(h.right, point, level + 1);

        return h;
    }

    private boolean _contains(Node h, Point2D point, int level) {
        if (h == null) return false;
        int cmp = level % 2 == 0 ? Double.compare(h.point.x(), point.x()) : Double.compare(h.point.y(), point.y());

        if (cmp < 0)
            return _contains(h.left, point, level + 1);
        else
            return h.point.compareTo(point) == 0 || _contains(h.right, point, level + 1);
    }
}
