package main.java;
import java.util.ArrayList;
import java.util.List;

public class AVLTree<T extends Comparable<T>> {

    private class Node {
        T data;
        Node left, right;
        int height;

        Node(T data) {
            this.data = data;
            height = 1;
        }
    }

    private Node root;

    public void insert(T data) {
        root = insertRec(root, data);
    }

    public void delete(T data) {
        root = deleteRec(root, data);
    }

    private int height(Node n) {
        return (n == null) ? 0 : n.height;
    }

    private int getBalance(Node n) {
        return (n == null) ? 0 : height(n.left) - height(n.right);
    }

    private Node insertRec(Node node, T data) {
        if (node == null) return new Node(data);
        if (data.compareTo(node.data) < 0)
            node.left = insertRec(node.left, data);
        else if (data.compareTo(node.data) > 0)
            node.right = insertRec(node.right, data);
        else
            return node; // duplicate not allowed

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);
        if (balance > 1 && data.compareTo(node.left.data) < 0)
            return rotateRight(node);
        if (balance < -1 && data.compareTo(node.right.data) > 0)
            return rotateLeft(node);
        if (balance > 1 && data.compareTo(node.left.data) > 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        if (balance < -1 && data.compareTo(node.right.data) < 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }
        return node;
    }

    private Node deleteRec(Node root, T key) {
        if (root == null)
            return root;

        if (key.compareTo(root.data) < 0)
            root.left = deleteRec(root.left, key);
        else if (key.compareTo(root.data) > 0)
            root.right = deleteRec(root.right, key);
        else {
            if ((root.left == null) || (root.right == null)) {
                Node temp = (root.left != null) ? root.left : root.right;
                if (temp == null) {
                    root = null;
                } else {
                    root = temp;
                }
            } else {
                Node temp = minValueNode(root.right);
                root.data = temp.data;
                root.right = deleteRec(root.right, temp.data);
            }
        }

        if (root == null)
            return root;

        root.height = Math.max(height(root.left), height(root.right)) + 1;
        int balance = getBalance(root);

        if (balance > 1 && getBalance(root.left) >= 0)
            return rotateRight(root);
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = rotateLeft(root.left);
            return rotateRight(root);
        }
        if (balance < -1 && getBalance(root.right) <= 0)
            return rotateLeft(root);
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rotateRight(root.right);
            return rotateLeft(root);
        }

        return root;
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    private Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }

    public List<T> inorderTraversal() {
        List<T> list = new ArrayList<>();
        inorderRec(root, list);
        return list;
    }

    private void inorderRec(Node node, List<T> list) {
        if (node != null) {
            inorderRec(node.left, list);
            list.add(node.data);
            inorderRec(node.right, list);
        }
    }
}
