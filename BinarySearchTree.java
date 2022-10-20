public class BinarySearchTree {

    private class Node {
        public int key;
        public Node left;
        public Node right;

        public Node(int k, Node l, Node r) {
            key = k;
            left = l;
            right = r;
        }

        public Node(int k) {
            this(k, null, null);
        }
    }

    public Node root;

    public BinarySearchTree() {
        root = null;
    }

    public Node search(int value) {
        return search(value, root);
    }

    private Node search(int value, Node node) {
        Node result = null;
        if (node.key == value) {
            result = node;
        } else {
            if (value < node.key) {
                result = search(value, node.left);
            } else {
                result = search(value, node.right);
            }
        }
        return result;
    }

    public int minimum() {
        return minimum(root);
    }

    private int minimum(Node branch) {
        if (branch.left == null) {
            return branch.key;
        } else {
            return minimum(branch.left);
        }
    }

    public void insert(int value) {
        Node curr = root;
        while (curr.key < value) {
            if (value < curr.key) {
                if (curr.left != null) {
                    curr.left = new Node(value);
                } else {
                    curr = curr.left;
                }
            } else {
                if (curr.right != null) {
                    curr.right = new Node(value);
                } else {
                    curr = curr.right;
                }
            }

        }
    }

    public void delete(int target) {
        delete(target, root);
    }

    private void delete(int target, Node branch) {
        Node prev = null;
        while (branch != null) {
            // Found item
            if (branch.key == target) {
                // Leaf
                if (branch.left == null && branch.right == null) {
                    branch = null;
                }

                // One child
                if (branch.right == null) { // Only left child
                    if (prev.left == branch) {
                        prev.left = branch.left;
                    } else {
                        prev.right = branch.left;
                    }
                } else { // Only right child
                    if (prev.left == branch) {
                        prev.left = branch.right;
                    } else {
                        prev.right = branch.right;
                    }
                }

                // Two children
                branch.key = minimum(branch.right);
                delete(branch.key, branch.right);

                // Not found
            } else if (target < branch.key) {
                branch = branch.left;
            } else {
                branch = branch.right;
            }
            prev = branch;
        }
    }

    void printTree() {
        printInorder(root);
    }

    void printInorder(Node node) {
        if (node == null)
            return;

        /* first recur on left child */
        printInorder(node.left);

        /* then print the data of node */
        System.out.print(node.key + " ");

        /* now recur on right child */
        printInorder(node.right);
    }
}