package br.pre.crackcodew.chap4.treegraph;

import br.pre.crackcodew.lib.AssortedMethods;
import br.pre.crackcodew.lib.TreeNode;

/**
 * Check Subtree: Tl and T2 are two very large binary trees, with Tl much bigger
 * than T2. Create an algorithm to determine if T2 is a subtree of Tl. <br/>
 * A tree T2 is a subtree of T1 if there exists a node n in Tl such that the
 * subtree of n is identical to T2 . That is, if you cut off the tree at node n,
 * the two trees would be identical.
 * 
 * @author bbadarch
 *
 */
public class Prob4_10 {

	public static boolean containsTree(TreeNode t1, TreeNode t2) {
		StringBuilder string1 = new StringBuilder();
		StringBuilder string2 = new StringBuilder();

		getOrderString(t1, string1);
		getOrderString(t2, string2);

		return string1.indexOf(string2.toString()) != -1;
	}

	public static void getOrderString(TreeNode node, StringBuilder sb) {
		if (node == null) {
			sb.append("X"); // Add null indicator
			return;
		}
		sb.append(node.data); // Add root
		getOrderString(node.left, sb); // Add left
		getOrderString(node.right, sb); // Add right
	}

	public static void main(String[] args) {
		// t2 is a subtree of t1
		int[] array1 = { 1, 2, 1, 3, 1, 1, 5 };
		int[] array2 = { 2, 3, 1 };

		TreeNode t1 = AssortedMethods.createTreeFromArray(array1);
		TreeNode t2 = AssortedMethods.createTreeFromArray(array2);
		t1.print();
		t2.print();

		if (containsTree(t1, t2)) {
			System.out.println("t2 is a subtree of t1");
		} else {
			System.out.println("t2 is not a subtree of t1");
		}

		// t4 is not a subtree of t3
		int[] array3 = { 1, 2, 3 };
		TreeNode t3 = AssortedMethods.createTreeFromArray(array1);
		TreeNode t4 = AssortedMethods.createTreeFromArray(array3);

		if (containsTree(t3, t4)) {
			System.out.println("t4 is a subtree of t3");
		} else {
			System.out.println("t4 is not a subtree of t3");
		}
	}
}

class Prob4_10Solution2 {

	public static boolean containsTree(TreeNode t1, TreeNode t2) {
		if (t2 == null) {
			return true; // The empty tree is a subtree of every tree.
		}
		return subTree(t1, t2);
	}

	/*
	 * Checks if the binary tree rooted at r1 contains the binary tree rooted at r2
	 * as a subtree somewhere within it.
	 */
	public static boolean subTree(TreeNode r1, TreeNode r2) {
		if (r1 == null) {
			return false; // big tree empty & subtree still not found.
		} else if (r1.data == r2.data && matchTree(r1, r2)) {
			return true;
		}
		return subTree(r1.left, r2) || subTree(r1.right, r2);
	}

	/*
	 * Checks if the binary tree rooted at r1 contains the binary tree rooted at r2
	 * as a subtree starting at r1.
	 */
	public static boolean matchTree(TreeNode r1, TreeNode r2) {
		if (r1 == null && r2 == null) {
			return true; // nothing left in the subtree
		} else if (r1 == null || r2 == null) {
			return false; // exactly one tree is empty, therefore trees don't match
		} else if (r1.data != r2.data) {
			return false; // data doesn't match
		} else {
			return matchTree(r1.left, r2.left) && matchTree(r1.right, r2.right);
		}
	}

	public static void main(String[] args) {
		// t2 is a subtree of t1
		int[] array1 = { 1, 2, 1, 3, 1, 1, 5 };
		int[] array2 = { 2, 3, 1 };

		TreeNode t1 = AssortedMethods.createTreeFromArray(array1);
		TreeNode t2 = AssortedMethods.createTreeFromArray(array2);

		if (containsTree(t1, t2)) {
			System.out.println("t2 is a subtree of t1");
		} else {
			System.out.println("t2 is not a subtree of t1");
		}

		// t4 is not a subtree of t3
		int[] array3 = { 1, 2, 3 };
		TreeNode t3 = AssortedMethods.createTreeFromArray(array1);
		TreeNode t4 = AssortedMethods.createTreeFromArray(array3);

		if (containsTree(t3, t4)) {
			System.out.println("t4 is a subtree of t3");
		} else {
			System.out.println("t4 is not a subtree of t3");
		}
	}

}