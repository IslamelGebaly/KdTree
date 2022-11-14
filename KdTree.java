import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.LinkedList;
import java.util.TreeSet;

public class KdTree {

    private class TreeNode implements Comparable<Point2D> {

        Point2D p;
        boolean vertical;

        TreeNode(Point2D p) {
            this.p = p;
            this.vertical = true;
        }

        private TreeNode(Point2D p, boolean vertical) {
            this.p = p;
            this.vertical = vertical;
        }

        Point2D getP() {
            return this.p;
        }

        boolean isVertical() {
            return this.vertical;
        }

        public int compareTo(Point2D that) {
            if (this.isVertical()) {
                if (this.getP().x() > that.x())
                    return 1;
                if (this.getP().x() < that.x())
                    return -1;

                return 0;
            }

            if (this.getP().y() > that.y())
                return 1;
            if (this.getP().y() < that.y())
                return -1;

            return 0;
        }
    }

    private TreeSet<TreeNode> set;

    public KdTree() {
        this.set = new TreeSet<>();
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

        set.add(new TreeNode(p));
    }

    public boolean contains(Point2D p) {// does the set contain point p?
        if (p == null)
            throw new IllegalArgumentException();
        return set.contains(p);
    }

    public void draw() // draw all points to standard draw
    {
        for (TreeNode t : set)
            t.getP().draw();
    }

    public Iterable<Point2D> range(RectHV rect) {// all points that are inside the rectangle (or on the boundary)
        if (rect == null)
            throw new IllegalArgumentException();

        if (isEmpty())
            return null;

        LinkedList<Point2D> validPoints = new LinkedList<>();
        

        return validPoints;
    }

    public Point2D nearest(Point2D p) {

        if (p == null)
            throw new IllegalArgumentException();

        if (isEmpty())
            return null;

        Point2D champion = root.getP();
        Point2D new_champion = nearestNeighbor(root, p, champion);
        return new_champion;
    }

    public static void main(String[] args) {

    }
}
