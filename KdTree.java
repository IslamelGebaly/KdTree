import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.LinkedList;

public class KdTree {

    private class TreeNode {

        Point2D p;
        TreeNode leftNode;
        TreeNode rightNode;
        Boolean vertical;

        TreeNode(Point2D p) {
            this.p = p;
            this.leftNode = null;
            this.rightNode = null;
            this.vertical = true;
        }

        private TreeNode(Point2D p, boolean vertical) {
            this.p = p;
            this.leftNode = null;
            this.rightNode = null;
            this.vertical = vertical;
        }

        void insertLeft(Point2D p) {
            if (vertical)
                this.leftNode = new TreeNode(p, false);
            else
                this.leftNode = new TreeNode(p, true);
        }

        void insertRight(Point2D p) {
            if (vertical)
                this.rightNode = new TreeNode(p, false);
            else
                this.rightNode = new TreeNode(p, true);
        }

        TreeNode traverseLeft() {
            if (this.leftNode == null)
                return null;

            return this.leftNode;
        }

        TreeNode traverseRight() {
            if (this.rightNode == null)
                return null;

            return this.rightNode;
        }

        Point2D getP() {
            return this.p;
        }

    }

    private TreeNode root;
    private int size;

    public KdTree() {
        root = null;
        size = 0;
    }

    public boolean isEmpty() {  // is the set empty?
        return !(root == null);
    }

    public int size() {  // number of points in the set
        return this.size;
    }

    public void insert(Point2D p) { // add the point to the set (if it is not already in the set)
        if (root == null)
            root = new TreeNode(p);
        else if (root.getP().x() < p.x()) {

        }
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
        return new LinkedList<Point2D>();
    }

    public Point2D nearest(Point2D p) {
        return new Point2D(0, 0);
    }

    public static void main(String[] args) {

    }
}
