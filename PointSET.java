import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.LinkedList;

public class PointSET {

    public PointSET() { // construct an empty set of points
    }

    public boolean isEmpty() {  // is the set empty?
        return false;
    }

    public int size() {  // number of points in the set
        return 0;
    }

    public void insert(Point2D p) { // add the point to the set (if it is not already in the set)
        return;
    }

    public boolean contains(Point2D p) { // does the set contain point p?
        return false;
    }

    public void draw() { // draw all points to standard draw
        return;
    }

    public Iterable<Point2D> range(RectHV rect) { // all points that are inside the rectangle (or on the boundary)
        return new LinkedList<Point2D>();
    }

    public Point2D nearest(Point2D p) {
        return new Point2D(0, 0);
    }

    public static void main(String[] args) {

    }
}
