package br.pre.crackcodew.chap4.treegraph;

import br.pre.crackcodew.lib.AssortedMethods;
import br.pre.crackcodew.lib.TreeNode;

/**
 * <b>Validate BST:</b> Implement a function to check if a binary tree is a
 * binary search tree.
 * 
 * @author bbadarch
 *
 */
public class Prob4_5 {

	public static Integer lastPrinted = null;

	public static boolean checkBST(TreeNode node) {
		return checkBST(node, true);
	}

	// Allow "equal" value only for left child. This validates the BST property.
	public static boolean checkBST(TreeNode n, boolean isLeft) {
		if (n == null) {
			return true;
		}
		
		// Check / recurse left
		if (!checkBST(n.left, true)) {
			return false;
		}
		
		// Check current
		if (lastPrinted != null) {
			if (isLeft) {
				// left child "is allowed" be equal to parent.
				if (n.data < lastPrinted) {
					return false;
				}
			} else {
				// Right child "is not allowed" be equal to parent.
				if (n.data <= lastPrinted) {
					return false;
				}
			}
		}
		lastPrinted = n.data;
		
		// Check / recurse right
		if (!checkBST(n.right, false)) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		int[] array = {Integer.MIN_VALUE, Integer.MAX_VALUE - 2, Integer.MAX_VALUE - 1, Integer.MAX_VALUE};
		TreeNode node = TreeNode.createMinimalBST(array);
		//node.left.data = 5;
		//node.left.right.data = 3;
		System.out.println(checkBST(node));

		test();
	}

	public static void test() {
		TreeNode node;
		boolean condition;
		System.out.println("test cases for equals condition.");

		/* Expect true: for left child: node.data <= last_printed.
//   2
//  / \
// /   \
// 2   3
//      \
      4
		*/
		int[] array2 = {1, 2, 3, 4};
		node = TreeNode.createMinimalBST(array2);
		node.left.data = 2;
		node.print();
		lastPrinted = null;
		condition = checkBST(node);
		System.out.println("should be true: " + condition);

		/* Expect false: for right child: node.data <= last_printed.
//   2
//  / \
// /   \
// 1   2
//      \
//      4
		 */
		int[] array3 = {1, 2, 3, 4};
		node = TreeNode.createMinimalBST(array3);
		node.right.data = 2;
		node.print();
		lastPrinted = null;
		condition = checkBST(node);
		System.out.println("should be false: " + condition);
	}
}

class Prob4_5Solution2 {
	public static boolean checkBST(TreeNode n, Integer min, Integer max) {
		if (n == null) {
			return true;
		}
		if ((min != null && n.data <= min) || (max != null && n.data > max)) {
			return false;
		}
		if (!checkBST(n.left, min, n.data) || !checkBST(n.right, n.data, max)) {
			return false;
		}
		return true;
	}
		
	public static boolean checkBST(TreeNode n) {
		return checkBST(n, null, null);
	}
	
	public static boolean checkBSTAlternate(TreeNode n) {
		return checkBSTAlternate(n, new IntWrapper(0), new IntWrapper(0));
	}		

	public static boolean checkBSTAlternate(TreeNode n, IntWrapper min, IntWrapper max) {
		/* An alternate, less clean approach. This is not provided in the book, but is used to test the other method. */
		if (n.left == null) {
			min.data = n.data;
		} else {
			IntWrapper leftMin = new IntWrapper(0);
			IntWrapper leftMax = new IntWrapper(0);
			if (!checkBSTAlternate(n.left, leftMin, leftMax)) {
				return false;
			}
			if (leftMax.data > n.data) {
				return false;
			}
			min.data = leftMin.data;
		}
		if (n.right == null) {
			max.data = n.data;
		} else {
			IntWrapper rightMin = new IntWrapper(0);
			IntWrapper rightMax = new IntWrapper(0);
			if (!checkBSTAlternate(n.right, rightMin, rightMax)) {
				return false;
			}
			if (rightMin.data <= n.data) {
				return false;
			}
			max.data = rightMax.data;
		}
		return true;
	}

	/* Create a tree that may or may not be a BST */
	public static TreeNode createTestTree() {
		/* Create a random BST */
		TreeNode head = AssortedMethods.randomBST(10, -10, 10); 
		
		/* Insert an element into the BST and potentially ruin the BST property */
		TreeNode node = head;
		do {
			int n = AssortedMethods.randomIntInRange(-10, 10);
			int rand = AssortedMethods.randomIntInRange(0, 5);
			if (rand == 0) {
				node.data = n;
			} else if (rand == 1) {
				node = node.left;
			} else if (rand == 2) {
				node = node.right;
			} else if (rand == 3 || rand == 4) {
				break;
			}
		} while (node != null);	
		
		return head;
	}
	
	public static void main(String[] args) {
		/* Simple test -- create one */
		int[] array = {Integer.MIN_VALUE, 3, 5, 6, 10, 13, 15, Integer.MAX_VALUE};
		TreeNode node = TreeNode.createMinimalBST(array);
		//node.left.data = 6; // "ruin" the BST property by changing one of the elements
		node.print();
		boolean isBst = checkBST(node);
		System.out.println(isBst);
		
		/* More elaborate test -- creates 100 trees (some BST, some not) and compares the outputs of various methods. */
		/*for (int i = 0; i < 100; i++) {
			TreeNode head = createTestTree();
			
			// Compare results 
			boolean isBst1 = checkBST(head);
			boolean isBst2 = checkBSTAlternate(head);
			
			if (isBst1 != isBst2) {
				System.out.println("*********************** ERROR *******************");
				head.print();
				break;
			} else {
				System.out.println(isBst1 + " | " + isBst2);
				head.print();
			}
		}*/
	}
}
class IntWrapper {
	public IntWrapper(int m) {
		data = m;
	}
	public int data;
}

