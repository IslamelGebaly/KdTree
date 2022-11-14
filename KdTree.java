import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.LinkedList;

public class KdTree {

    private class TreeNode implements Comparable<Point2D> {

        Point2D p;
        TreeNode leftNode;
        TreeNode rightNode;
        boolean vertical;

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

        void insert(Point2D p) {
            if (this.compareTo(p) == 1) {
                if (this.left() == null)
                    this.leftNode = new TreeNode(p, !isVertical());
                else
                    this.left().insert(p);
            } else {
                if (this.right() == null)
                    this.rightNode = new TreeNode(p, !isVertical());
                else
                    this.right().insert(p);
            }
        }

        TreeNode left() {
            if (this.leftNode == null)
                return null;

            return this.leftNode;
        }

        TreeNode right() {
            if (this.rightNode == null)
                return null;

            return this.rightNode;
        }

        boolean search(Point2D p) {
            if (p.equals(this.getP()))
                return true;
            else if (this.compareTo(p) == 1) {
                if (this.left() == null)
                    return false;
                return this.left().search(p);
            } else {
                if (this.right() == null)
                    return false;
                return this.right().search(p);
            }
        }

        LinkedList<Point2D> range(RectHV rect) {
            LinkedList<Point2D> validPoints = new LinkedList<>();

            if (rect.contains(this.getP()))
                validPoints.add(this.getP());


            if (this.isVertical()) {
                if (rect.intersects(new RectHV(this.getP().x(), 0, this.getP().x(), 1))) {
                    if (this.left() != null)
                        validPoints.addAll(this.left().range(rect));
                    if (this.right() != null)
                        validPoints.addAll(this.right().range(rect));
                } else if (rect.xmax() < this.getP().x() && (this.left() != null)) {
                    validPoints.addAll(this.left().range(rect));
                } else if (rect.xmin() >= this.getP().x() && this.right() != null) {
                    validPoints.addAll(this.right().range(rect));
                }
            } else {

                if (rect.intersects(new RectHV(0, this.getP().y(), 1, this.getP().y()))) {
                    if (this.left() != null)
                        validPoints.addAll(this.left().range(rect));
                    if (this.right() != null)
                        validPoints.addAll(this.right().range(rect));
                } else if (rect.ymax() < this.getP().y() && (this.left() != null)) {
                    validPoints.addAll(this.left().range(rect));
                } else if (rect.ymin() >= this.getP().y() && (this.right() != null)) {
                    validPoints.addAll(this.right().range(rect));
                }
            }

            return validPoints;
        }

        private Point2D nearestNeighbor(Point2D p, Point2D champion) {

            Point2D ch = this.getP().distanceSquaredTo(p) < p.distanceSquaredTo(champion) ? this.getP() : champion;
            Point2D pot = ch;

            TreeNode near;
            TreeNode far;

            if (this.compareTo(p) == 1) {
                near = this.left();
                far = this.right();
            } else {
                near = this.right();
                far = this.left();
            }

            if (near != null)
                pot = near.nearestNeighbor(p, pot);
            if (far != null)
                pot = far.nearestNeighbor(p, pot);

            return pot;
        }

        void draw() {
            p.draw();

            if (this.left() != null)
                this.left().draw();
            if (this.right() != null)
                this.right().draw();
        }

        Point2D getP() {
            return this.p;
        }

        boolean isVertical() {
            return this.vertical;
        }

        public int compareTo(Point2D that) {
            if (isVertical()) {
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

    private TreeNode root;
    private int size;

    public KdTree() {
        this.root = null;
        this.size = 0;
    }

    public boolean isEmpty() {  // is the set empty?
        return root == null;
    }

    public int size() {  // number of points in the set
        return this.size;
    }

    public void insert(Point2D p) {// add the point to the set (if it is not already in the set)
        if (p == null)
            throw new IllegalArgumentException();

        if (root == null) {
            root = new TreeNode(p);
            size++;
            return;
        }

        if (this.contains(p))
            return;

        root.insert(p);
        size++;
    }

    public boolean contains(Point2D p) {// does the set contain point p?
        if (p == null)
            throw new IllegalArgumentException();

        return root.search(p);
    }

    public void draw() // draw all points to standard draw
    {
        root.draw();
    }

    public Iterable<Point2D> range(RectHV rect) {// all points that are inside the rectangle (or on the boundary)
        if (rect == null)
            throw new IllegalArgumentException();

        if (isEmpty())
            return null;

        LinkedList<Point2D> validPoints = new LinkedList<>();

        validPoints.addAll(root.range(rect));
        return validPoints;
    }

    public Point2D nearest(Point2D p) {

        if (p == null)
            throw new IllegalArgumentException();

        if (isEmpty())
            return null;

        return root.nearestNeighbor(p, root.getP());
    }

    public static void main(String[] args) {

    }
}
