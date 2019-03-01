package br.pre.crackcodew.chap2.linkedlist;

import br.pre.crackcodew.lib.AssortedMethods;
import br.pre.crackcodew.lib.LinkedListNode;

/**
 * Return Kth to Last: Implement an algorithm to find the kth to last element of
 * a singly linked list.<br/>
 * 
 * @author bbadarch
 *
 */
public class Prob2_2 {

	// Approach A: Don't Return the Element.
	public static int printKthToLast(LinkedListNode head, int k) {
		if (head == null) {
			return 0;
		}
		int index = printKthToLast(head.next, k) + 1;
		if (index == k) {
			System.out.println(k + "th to last node is " + head.data);
		}
		return index;
	}

	public static void main(String[] args) {
		int[] array = { 0, 1, 2, 3, 4, 5, 6 };
		LinkedListNode head = AssortedMethods.createLinkedListFromArray(array);
		System.out.println(head.printForward());
		for (int i = 0; i <= array.length + 1; i++) {
			printKthToLast(head, i);
		}
		for (int i = 0; i <= array.length + 1; i++) {
			LinkedListNode node = Prob2_2Solution2.kthToLast(head, i);
			String nodeValue = node == null ? "null" : "" + node.data;
			System.out.println(i + ": " + nodeValue);
		}
		for (int i = 0; i <= array.length + 1; i++) {
			LinkedListNode node = Prob2_2Solution3.nthToLast(head, i);
			String nodeValue = node == null ? "null" : "" + node.data;
			System.out.println(i + ": " + nodeValue);
		}
	}
}

class Prob2_2Solution2 {
	public static class Index {
		public int value = 0;
	}

	public static LinkedListNode kthToLast(LinkedListNode head, int k) {
		Index idx = new Index();
		return kthToLast(head, k, idx);
	}

	public static LinkedListNode kthToLast(LinkedListNode head, int k, Index idx) {
		if (head == null) {
			return null;
		}
		LinkedListNode node = kthToLast(head.next, k, idx);
		idx.value = idx.value + 1;
		if (idx.value == k) {
			return head;
		}
		return node;
	}
}

class Prob2_2Solution3 {
	public static LinkedListNode nthToLast(LinkedListNode head, int k) {
		LinkedListNode p1 = head;
		LinkedListNode p2 = head;

		/* Move p1 k nodes into the list. */
		for (int i = 0; i < k; i++) {
			if (p1 == null)
				return null; // Out of bounds
			p1 = p1.next;
		}

		/*
		 * Move them at the same pace. When p1 hits the end, p2 will be at the right
		 * element.
		 */
		while (p1 != null) {
			p1 = p1.next;
			p2 = p2.next;
		}
		return p2;
	}
}
