package org.example.Day8BST;

public class BinarySearchTree {
    private TreeNode node;

    private TreeNode insert(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (val < root.val) {
            root.left = insert(root.left, val);
        } else {
            root.right = insert(root.right, val);
        }
        return root;
    }

    private TreeNode deleteNode(TreeNode node, int val) {
        if (node == null) {
            return null;
        }

        if (val < node.val) {
            node.left = deleteNode(node.left, val);
        } else if (val > node.val) {
            node.right = deleteNode(node.right, val);
        } else {
            if (node.left == null && node.right == null) {
                return null;
            }
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            } else {//Find inorderSucessor
                TreeNode node1 = findMin(node.right);
                node.val = node1.val;
                node.right = deleteNode(node.right, node1.val);

            }
        }
        return node;
    }

    private TreeNode findMin(TreeNode node) {
       while (node.left != null) {
           node = node.left;
       }
       return node;
    }
}