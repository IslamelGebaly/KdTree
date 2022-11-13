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
            this.leftNode = new TreeNode(p, !this.isVertical());
        }

        void insertRight(Point2D p) {
            this.rightNode = new TreeNode(p, !this.isVertical());
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

        boolean isVertical() {
            return this.vertical;
        }

    }

    private TreeNode root;
    private int size;

    private void insertNode(TreeNode node, Point2D p) {
        if (node.isVertical()) {
            if (p.x() < node.getP().x()) {
                if (node.traverseLeft() == null) {
                    node.insertLeft(p);
                    return;
                }
                insertNode(node.traverseLeft(), p);
                return;
            }

            if (node.traverseRight() == null) {
                node.insertRight(p);
                return;
            }

            insertNode(node.traverseRight(), p);
            return;
        }

        if (p.y() < node.getP().y()) {
            if (node.traverseLeft() == null) {
                node.insertLeft(p);
                return;
            }

            insertNode(node.traverseLeft(), p);
            return;
        }

        if (node.traverseRight() == null) {
            node.insertRight(p);
            return;
        }

        insertNode(node.traverseRight(), p);

    }

    private boolean search(TreeNode node, Point2D p) {

        if (node == null)
            throw new IllegalArgumentException();

        if (node.getP().equals(p))
            return true;

        if (node.isVertical()) {
            if (p.x() < node.getP().x())
                return search(node.traverseLeft(), p);
            return search(node.traverseRight(), p);
        }

        if (p.y() < node.getP().y())
            return search(node.traverseLeft(), p);
        return search(node.traverseRight(), p);
    }

    private void traverseDraw(TreeNode node) {
        if (node == null)
            return;

        node.getP().draw();
        traverseDraw(node.traverseLeft());
        traverseDraw(node.traverseRight());
    }

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
        if (root == null) {
            root = new TreeNode(p);
            size++;
            return;
        }

        insertNode(root, p);
        size++;
    }

    public boolean contains(Point2D p) { // does the set contain point p?
        return search(root, p);
    }

    public void draw() // draw all points to standard draw
    {
        traverseDraw(root);
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
