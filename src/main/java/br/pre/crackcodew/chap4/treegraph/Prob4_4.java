package br.pre.crackcodew.chap4.treegraph;

import br.pre.crackcodew.lib.AssortedMethods;
import br.pre.crackcodew.lib.TreeNode;

/**
 * <b>Check Balanced:<b/> Implement a function to check if a binary tree is
 * balanced. For the purposes of this question, a balanced tree is defined to be
 * a tree such that the heights of the two subtrees of any node never differ by
 * more than one.
 * 
 * @author bbadarch
 *
 */
public class Prob4_4 {

	public static int getHeight(TreeNode root) {
		if (root == null) {
			return -1;
		}
		return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
	}

	public static boolean isBalanced(TreeNode root) {
		if (root == null) {
			return true;
		}
		int heightDiff = getHeight(root.left) - getHeight(root.right);
		if (Math.abs(heightDiff) > 1) {
			return false;
		} else {
			return isBalanced(root.left) && isBalanced(root.right);
		}
	}

	public static void main(String[] args) {
		// Create balanced tree
		int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		TreeNode root = TreeNode.createMinimalBST(array);
		System.out.println("Root? " + root.data);
		System.out.println("Is balanced? " + isBalanced(root));

		// Could be balanced, actually, but it's very unlikely...
		TreeNode unbalanced = new TreeNode(10);
		for (int i = 0; i < 10; i++) {
			unbalanced.insertInOrder(AssortedMethods.randomIntInRange(0, 100));
		}
		System.out.println("Root? " + unbalanced.data);
		System.out.println("Is balanced? " + isBalanced(unbalanced));
	}
}

class Prob4_4Solution2 {

	public static int checkHeight(TreeNode root) {
		if (root == null) {
			return -1;
		}
		int leftHeight = checkHeight(root.left);
		if (leftHeight == Integer.MIN_VALUE)
			return Integer.MIN_VALUE; // Propagate error up

		int rightHeight = checkHeight(root.right);
		if (rightHeight == Integer.MIN_VALUE)
			return Integer.MIN_VALUE; // Propagate error up

		int heightDiff = leftHeight - rightHeight;
		if (Math.abs(heightDiff) > 1) {
			return Integer.MIN_VALUE; // Found error -> pass it back
		} else {
			return Math.max(leftHeight, rightHeight) + 1;
		}
	}

	public static boolean isBalanced(TreeNode root) {
		return checkHeight(root) != Integer.MIN_VALUE;
	}

	public static void main(String[] args) {
		// Create balanced tree
		int[] array = { 0, 1, 2, 3, 5, 6, 7, 8, 9, 10 };
		TreeNode root = TreeNode.createMinimalBST(array);

		System.out.println("Is balanced? " + isBalanced(root));

		root.insertInOrder(4); // Add 4 to make it unbalanced

		System.out.println("Is balanced? " + isBalanced(root));
	}

}
