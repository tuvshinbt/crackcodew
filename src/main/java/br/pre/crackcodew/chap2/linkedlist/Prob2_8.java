package br.pre.crackcodew.chap2.linkedlist;

import br.pre.crackcodew.lib.LinkedListNode;

/**
 * Loop Detection: Given a circular linked list, implement an algorithm that
 * returns the node at the beginning of the loop. <br/>
 * DEFINITION Circular linked list: A (corrupt) linked list in which a node's
 * next pointer points to an earlier node, so as to make a loop in the linked
 * list. <br/>
 * EXAMPLE <br/>
 * Input: A -> B -> C - > D -> E -> C [the same C as earlier)<br/>
 * Output: C
 * 
 * @author bbadarch
 *
 */
public class Prob2_8 {
	public static LinkedListNode FindBeginning(LinkedListNode head) {
		LinkedListNode slow = head;
		LinkedListNode fast = head;

		// Find meeting point
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast) {
				break;
			}
		}

		// Error check - there is no meeting point, and therefore no loop
		if (fast == null || fast.next == null) {
			return null;
		}

		/*
		 * Move slow to Head. Keep fast at Meeting Point. Each are k steps /* from the
		 * Loop Start. If they move at the same pace, they must meet at Loop Start.
		 */
		slow = head;
		while (slow != fast) {
			slow = slow.next;
			fast = fast.next;
		}

		// Both now point to the start of the loop.
		return fast;
	}

	public static void main(String[] args) {
		int list_length = 100;
		int k = 10;

		// Create linked list
		LinkedListNode[] nodes = new LinkedListNode[list_length];
		for (int i = 0; i < list_length; i++) {
			nodes[i] = new LinkedListNode(i, null, i > 0 ? nodes[i - 1] : null);
		}

		// Create loop;
		nodes[list_length - 1].next = nodes[list_length - k];

		LinkedListNode loop = FindBeginning(nodes[0]);
		if (loop == null) {
			System.out.println("No Cycle.");
		} else {
			System.out.println(loop.data);
		}
	}
}
