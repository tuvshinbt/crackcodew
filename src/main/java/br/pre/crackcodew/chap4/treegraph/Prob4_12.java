package br.pre.crackcodew.chap4.treegraph;

import java.util.HashMap;

/**
 * <b>Paths with Sum:</b> You are given a binary tree in which each node
 * contains an integer value (which might be positive or negative). Design an
 * algorithm to count the number of paths that sum to a given value. The path
 * does not need to start or end at the root or a leaf, but it must go downwards
 * (traveling only from parent nodes to ch ild nodes)
 * 
 * @author bbadarch
 *
 */
public class Prob4_12 {

	public static int countPathsWithSum(TreeNode root, int targetSum) {
		if (root == null)
			return 0;

		/* Count paths with sum starting from the root. */
		int pathsFromRoot = countPathsWithSumFromNode(root, targetSum, 0);

		/* Try the nodes on the left and right. */
		int pathsOnLeft = countPathsWithSum(root.left, targetSum);
		int pathsOnRight = countPathsWithSum(root.right, targetSum);

		return pathsFromRoot + pathsOnLeft + pathsOnRight;
	}

	/* Returns the number of paths with this sum starting from this node. */
	public static int countPathsWithSumFromNode(TreeNode node, int targetSum, int currentSum) {
		if (node == null)
			return 0;

		currentSum += node.data;

		int totalPaths = 0;
		if (currentSum == targetSum) { // Found a path from the root
			totalPaths++;
		}

		totalPaths += countPathsWithSumFromNode(node.left, targetSum, currentSum); // Go left
		totalPaths += countPathsWithSumFromNode(node.right, targetSum, currentSum); // Go right

		return totalPaths;
	}

	public static void main(String[] args) {
//		TreeNode root = new TreeNode(5);
//		root.left = new TreeNode(3);		
//		root.right = new TreeNode(1);
//		root.left.left = new TreeNode(-8);
//		root.left.right = new TreeNode(8);
//		root.right.left = new TreeNode(2);
//		root.right.right = new TreeNode(6);	
//		System.out.println(countPathsWithSum(root, 0));

//		TreeNode root = new TreeNode(-7);
//		root.left = new TreeNode(-7);
//		root.left.right = new TreeNode(1);
//		root.left.right.left = new TreeNode(2);
//		root.right = new TreeNode(7);
//		root.right.left = new TreeNode(3);
//		root.right.right = new TreeNode(20);
//		root.right.right.left = new TreeNode(0);
//		root.right.right.left.left = new TreeNode(-3);
//		root.right.right.left.left.right = new TreeNode(2);
//		root.right.right.left.left.right.left = new TreeNode(1);
//		System.out.println(countPathsWithSum(root, -14));

		TreeNode root = new TreeNode(0);
		root.left = new TreeNode(0);
		root.right = new TreeNode(0);
		root.right.left = new TreeNode(0);
		root.right.left.right = new TreeNode(0);
		root.right.right = new TreeNode(0);
		System.out.println(countPathsWithSum(root, 0));
		System.out.println(countPathsWithSum(root, 4));
	}
}

class Prob4_12_Solution2 {

	public static int countPathsWithSum(TreeNode root, int targetSum) {
		return countPathsWithSum(root, targetSum, 0, new HashMap<Integer, Integer>());
	}

	public static int countPathsWithSum(TreeNode node, int targetSum, int runningSum, HashMap<Integer, Integer> pathCount) {
		if (node == null)
			return 0; // Base case

		runningSum += node.data;

		/* Count paths with sum ending at the current node. */
		int sum = runningSum - targetSum;
		int totalPaths = pathCount.getOrDefault(sum, 0);

		/*
		 * If runningSum equals targetSum, then one additional path starts at root. Add
		 * in this path.
		 */
		if (runningSum == targetSum) {
			totalPaths++;
		}

		/* Add runningSum to pathCounts. */
		incrementHashTable(pathCount, runningSum, 1);

		/* Count paths with sum on the left and right. */
		totalPaths += countPathsWithSum(node.left, targetSum, runningSum, pathCount);
		totalPaths += countPathsWithSum(node.right, targetSum, runningSum, pathCount);

		incrementHashTable(pathCount, runningSum, -1); // Remove runningSum
		return totalPaths;
	}

	public static void incrementHashTable(HashMap<Integer, Integer> hashTable, int key, int delta) {
		int newCount = hashTable.getOrDefault(key, 0) + delta;
		if (newCount == 0) { // Remove when zero to reduce space usage
			hashTable.remove(key);
		} else {
			hashTable.put(key, newCount);
		}
	}

	public static void main(String[] args) {
//		TreeNode root = new TreeNode(5);
//		root.left = new TreeNode(3);		
//		root.right = new TreeNode(1);
//		root.left.left = new TreeNode(-8);
//		root.left.right = new TreeNode(8);
//		root.right.left = new TreeNode(2);
//		root.right.right = new TreeNode(6);	
//		root.right.left.left = new TreeNode(0);	
//		System.out.println(countPathsWithSum(root, 0));

//		TreeNode root = new TreeNode(-7);
//		root.left = new TreeNode(-7);
//		root.left.right = new TreeNode(1);
//		root.left.right.left = new TreeNode(2);
//		root.right = new TreeNode(7);
//		root.right.left = new TreeNode(3);
//		root.right.right = new TreeNode(20);
//		root.right.right.left = new TreeNode(0);
//		root.right.right.left.left = new TreeNode(-3);
//		root.right.right.left.left.right = new TreeNode(2);
//		root.right.right.left.left.right.left = new TreeNode(1);
//		System.out.println(countPathsWithSum(root, 0));

		TreeNode root = new TreeNode(0);
		root.left = new TreeNode(0);
		root.right = new TreeNode(0);
		root.right.left = new TreeNode(0);
		root.right.left.right = new TreeNode(0);
		root.right.right = new TreeNode(0);
		System.out.println(countPathsWithSum(root, 0));
		System.out.println(countPathsWithSum(root, 4));
	}
}