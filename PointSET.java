import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class PointSET {
    // construct an empty set of points
    private TreeSet<Point2D> set;

    public PointSET() {
        set = new TreeSet<>();
    }

    public boolean isEmpty() {  // is the set empty?
        return set.isEmpty();
    }

    public int size() {  // number of points in the set
        return set.size();
    }

    public void insert(Point2D p) {// add the point to the set (if it is not already in the set)
        if (p == null)
            throw new IllegalArgumentException();
        set.add(p);
    }

    public boolean contains(Point2D p) { // does the set contain point p?
        if (p == null)
            throw new IllegalArgumentException();

        return set.contains(p);
    }

    public void draw() // draw all points to standard draw
    {
        if (isEmpty())
            return;

        for (Point2D p : set)
            p.draw();
    }

    public Iterable<Point2D> range(RectHV rect) {// all points that are inside the rectangle (or on the boundary)
        if (rect == null)
            throw new IllegalArgumentException();

        List<Point2D> inRangePoints = new ArrayList<Point2D>();

        for (Point2D p : set) {
            if (rect.contains(p))
                inRangePoints.add(p);
        }

        return inRangePoints;
    }

    public Point2D nearest(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException();

        if (isEmpty())
            return null;

        double min_distance = Double.POSITIVE_INFINITY;
        Point2D nearest = p;
        for (Point2D neighbor : set) {
            if (p.distanceTo(neighbor) < min_distance) {
                nearest = neighbor;
                min_distance = p.distanceTo(neighbor);
            }
        }

        return nearest;

    }

    public static void main(String[] args) {

    }
}
