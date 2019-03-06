package br.pre.crackcodew.chap2.linkedlist;

import java.util.Stack;

import br.pre.crackcodew.lib.LinkedListNode;

/**
 * Palindrome: Implement a function to check if a linked list is a palindrome.
 * 
 * @author bbadarch
 *
 */
public class Prob2_6 {
	public static boolean isPalindrome(LinkedListNode head) {
		LinkedListNode reversed = reverseAndClone(head);
		return isEqual(head, reversed);
	}

	public static LinkedListNode reverseAndClone(LinkedListNode node) {
		LinkedListNode head = null;
		while (node != null) {
			LinkedListNode n = new LinkedListNode(node.data); // Clone
			n.next = head;
			head = n;
			node = node.next;
		}
		return head;
	}

	public static boolean isEqual(LinkedListNode one, LinkedListNode two) {
		while (one != null && two != null) {
			if (one.data != two.data) {
				return false;
			}
			one = one.next;
			two = two.next;
		}
		return one == null && two == null;
	}

	public static void main(String[] args) {
		int length = 9;
		LinkedListNode[] nodes = new LinkedListNode[length];
		for (int i = 0; i < length; i++) {
			nodes[i] = new LinkedListNode(i >= length / 2 ? length - i - 1 : i, null, null);
		}

		for (int i = 0; i < length; i++) {
			if (i < length - 1) {
				nodes[i].setNext(nodes[i + 1]);
			}
			if (i > 0) {
				nodes[i].setPrevious(nodes[i - 1]);
			}
		}
		// nodes[length - 2].data = 9; // Uncomment to ruin palindrome

		LinkedListNode head = nodes[0];
		System.out.println(head.printForward());
		System.out.println(isPalindrome(head));
	}
}

class Prob2_6Solution2 {
	public static boolean isPalindrome(LinkedListNode head) {
		LinkedListNode fast = head;
		LinkedListNode slow = head;

		Stack<Integer> stack = new Stack<Integer>();

		while (fast != null && fast.next != null) {
			stack.push(slow.data);
			slow = slow.next;
			fast = fast.next.next;
		}

		/* Has odd number of elements, so skip the middle */
		if (fast != null) {
			slow = slow.next;
		}

		while (slow != null) {
			int top = stack.pop().intValue();
			if (top != slow.data) {
				return false;
			}
			slow = slow.next;
		}
		return true;
	}

	public static void main(String[] args) {
		int length = 9;
		LinkedListNode[] nodes = new LinkedListNode[length];
		for (int i = 0; i < length; i++) {
			nodes[i] = new LinkedListNode(i >= length / 2 ? length - i - 1 : i, null, null);
		}

		for (int i = 0; i < length; i++) {
			if (i < length - 1) {
				nodes[i].setNext(nodes[i + 1]);
			}
			if (i > 0) {
				nodes[i].setPrevious(nodes[i - 1]);
			}
		}
		// nodes[length - 2].data = 9; // Uncomment to ruin palindrome

		LinkedListNode head = nodes[0];
		System.out.println(head.printForward());
		System.out.println(isPalindrome(head));
	}

}

class Prob2_6Solution3 {
	public static class Result {
		public LinkedListNode node;
		public boolean result;

		public Result(LinkedListNode n, boolean res) {
			node = n;
			result = res;
		}
	}

	public static Result isPalindromeRecurse(LinkedListNode head, int length) {
		if (head == null || length <= 0) { // Even number of nodes
			return new Result(head, true);
		} else if (length == 1) { // Odd number of nodes
			return new Result(head.next, true);
		}

		/* Recurse on sublist. */
		Result res = isPalindromeRecurse(head.next, length - 2);

		/*
		 * If child calls are not a palindrome, pass back up a failure.
		 */
		if (!res.result || res.node == null) {
			return res;
		}

		/* Check if matches corresponding node on other side. */
		res.result = (head.data == res.node.data);

		/* Return corresponding node. */
		res.node = res.node.next;

		return res;
	}

	public static int lengthOfList(LinkedListNode n) {
		int size = 0;
		while (n != null) {
			size++;
			n = n.next;
		}
		return size;
	}

	public static boolean isPalindrome(LinkedListNode head) {
		int length = lengthOfList(head);
		Result p = isPalindromeRecurse(head, length);
		return p.result;
	}

	public static void main(String[] args) {
		int length = 9;
		LinkedListNode[] nodes = new LinkedListNode[length];
		for (int i = 0; i < length; i++) {
			nodes[i] = new LinkedListNode(i >= length / 2 ? length - i - 1 : i, null, null);
		}

		for (int i = 0; i < length; i++) {
			if (i < length - 1) {
				nodes[i].setNext(nodes[i + 1]);
			}
			if (i > 0) {
				nodes[i].setPrevious(nodes[i - 1]);
			}
		}
		// nodes[length - 2].data = 9; // Uncomment to ruin palindrome

		LinkedListNode head = nodes[0];
		System.out.println(head.printForward());
		System.out.println(isPalindrome(head));
	}

}
