package br.pre.crackcodew.chap4.treegraph;

import br.pre.crackcodew.lib.TreeNode;

/**
 * <b>First Common Ancestor:</b> Design an algorithm and write code to find the
 * first common ancestor of two nodes in a binary tree. Avoid storing additional
 * nodes in a data structure. NOTE: This is not necessarily a binary search
 * tree.
 * 
 * @author bbadarch
 *
 */
public class Prob4_8 {
	public static void main(String[] args) {

	}
}

class QuestionA {
	public static TreeNode commonAncestor(TreeNode p, TreeNode q) {
		if (p == q)
			return p;

		TreeNode ancestor = p;
		while (ancestor != null) {
			if (isOnPath(ancestor, q)) {
				return ancestor;
			}
			ancestor = ancestor.parent;
		}
		return null;
	}

	public static boolean isOnPath(TreeNode ancestor, TreeNode node) {
		while (node != ancestor && node != null) {
			node = node.parent;
		}
		return node == ancestor;
	}

	public static void main(String[] args) {
		int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		TreeNode root = TreeNode.createMinimalBST(array);
		root.print();
		TreeNode n3 = root.find(7);
		TreeNode n7 = root.find(10);
		TreeNode ancestor = commonAncestor(n3, n7);
		System.out.println(ancestor.data);
	}

}

class QuestionB {
	public static TreeNode commonAncestor(TreeNode p, TreeNode q) {
		int delta = depth(p) - depth(q); // get difference in depths
		TreeNode first = delta > 0 ? q : p; // get shallower node
		TreeNode second = delta > 0 ? p : q; // get deeper node
		second = goUpBy(second, Math.abs(delta)); // move shallower node to depth of deeper
		while (first != second && first != null && second != null) {
			first = first.parent;
			second = second.parent;
		}
		return first == null || second == null ? null : first;
	}

	public static TreeNode goUpBy(TreeNode node, int delta) {
		while (delta > 0 && node != null) {
			node = node.parent;
			delta--;
		}
		return node;
	}

	public static int depth(TreeNode node) {
		int depth = 0;
		while (node != null) {
			node = node.parent;
			depth++;
		}
		return depth;
	}

	public static void main(String[] args) {
		int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		TreeNode root = TreeNode.createMinimalBST(array);
		TreeNode n3 = root.find(3);
		TreeNode n7 = root.find(7);
		TreeNode ancestor = commonAncestor(n3, n7);
		System.out.println(ancestor.data);
	}

}

class QuestionC {

	public static TreeNode commonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (!covers(root, p) || !covers(root, q)) {
			return null;
		} else if (covers(p, q)) {
			return p;
		} else if (covers(q, p)) {
			return q;
		}

		TreeNode sibling = getSibling(p);
		TreeNode parent = p.parent;
		while (!covers(sibling, q)) {
			sibling = getSibling(parent);
			parent = parent.parent;
		}
		return parent;
	}

	public static boolean covers(TreeNode root, TreeNode p) {
		if (root == null)
			return false;
		if (root == p)
			return true;
		return covers(root.left, p) || covers(root.right, p);
	}

	public static TreeNode getSibling(TreeNode node) {
		if (node == null || node.parent == null) {
			return null;
		}

		TreeNode parent = node.parent;
		return parent.left == node ? parent.right : parent.left;
	}

	public static void main(String[] args) {
		int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		TreeNode root = TreeNode.createMinimalBST(array);
		TreeNode n3 = root.find(1);
		TreeNode n7 = root.find(7);
		TreeNode ancestor = commonAncestor(root, n3, n7);
		System.out.println(ancestor.data);
	}

}
