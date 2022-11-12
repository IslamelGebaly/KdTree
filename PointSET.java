import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.ArrayList;
import java.util.List;

public class PointSET {
    // construct an empty set of points
    private List<Point2D> set;

    public PointSET() {
        set = new ArrayList<>();
    }

    public boolean isEmpty() {  // is the set empty?
        return set.isEmpty();
    }

    public int size() {  // number of points in the set
        return set.size();
    }

    public void insert(Point2D p) { // add the point to the set (if it is not already in the set)
        set.add(p);
    }

    public boolean contains(Point2D p) { // does the set contain point p?
        return set.contains(p);
    }

    public void draw() // draw all points to standard draw
    {
        for (Point2D p : set)
            p.draw();
    }

    public Iterable<Point2D> range(RectHV rect) { // all points that are inside the rectangle (or on the boundary)
        List<Point2D> inRangePoints = new ArrayList<Point2D>();

        for (Point2D p : set) {
            if (rect.contains(p))
                inRangePoints.add(p);
        }

        return inRangePoints;
    }

    public Point2D nearest(Point2D p) {
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
