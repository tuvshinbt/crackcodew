package br.pre.crackcodew.chap2.linkedlist;

import br.pre.crackcodew.lib.LinkedListNode;

/**
 * Partition: Write code to partition a linked list around a value x, such that
 * all nodes less than x come before all nodes greater than or equal to x. If x
 * is contained within the list, the values of x only need to be after the
 * elements less than x (see below). The partition element x can appear anywhere
 * in the "right partition"; it does not need to appear between the left and
 * right partitions.<br/>
 * EXAMPLE<br/>
 * Input: 3 -) 5 -) 8 -) 5 -) 10 -) 2 -) 1 [partition = 5]<br/>
 * Output: 3 -) 1 -) 2 -) 10 -) 5 -) 5 -) 8
 * 
 * @author bbadarch
 *
 */
public class Prob2_4 {

	public static LinkedListNode partition(LinkedListNode node, int x) {
		LinkedListNode beforeStart = null;
		LinkedListNode beforeEnd = null;
		LinkedListNode afterStart = null;
		LinkedListNode afterEnd = null;

		/* Partition list */
		while (node != null) {
			LinkedListNode next = node.next;
			node.next = null;
			if (node.data < x) {
				if (beforeStart == null) {
					beforeStart = node;
					beforeEnd = beforeStart;
				} else {
					beforeEnd.next = node;
					beforeEnd = node;
				}
			} else {
				if (afterStart == null) {
					afterStart = node;
					afterEnd = afterStart;
				} else {
					afterEnd.next = node;
					afterEnd = node;
				}
			}
			node = next;
		}

		/* Merge before list and after list */
		if (beforeStart == null) {
			return afterStart;
		}

		beforeEnd.next = afterStart;
		return beforeStart;
	}

	public static void main(String[] args) {
		/* Create linked list */
		int[] vals = { 33, 9, 2, 3, 10, 10389, 838, 874578, 5 };
		LinkedListNode head = new LinkedListNode(vals[0], null, null);
		LinkedListNode current = head;
		for (int i = 1; i < vals.length; i++) {
			current = new LinkedListNode(vals[i], null, current);
		}
		System.out.println(head.printForward());

		LinkedListNode head1 = head.clone();
		/* Partition */
		LinkedListNode h = partition(head, 6);

		/* Print Result */
		System.out.println(h.printForward());
		/* Partition */
		LinkedListNode h1 = Prob2_4Solution2.partition(head1, 6);

		/* Print Result */
		System.out.println(h1.printForward());
	}
}

class Prob2_4Solution2 {
	public static LinkedListNode partition(LinkedListNode node, int x) {
		LinkedListNode beforeStart = null;
		LinkedListNode afterStart = null;

		/* Partition list */
		while (node != null) {
			LinkedListNode next = node.next;
			if (node.data < x) {
				/* Insert node into start of before list */
				node.next = beforeStart;
				beforeStart = node;
			} else {
				/* Insert node into front of after list */
				node.next = afterStart;
				afterStart = node;
			}
			node = next;
		}

		/* Merge before list and after list */
		if (beforeStart == null) {
			return afterStart;
		}

		LinkedListNode head = beforeStart;
		while (beforeStart.next != null) {
			beforeStart = beforeStart.next;
		}
		beforeStart.next = afterStart;
		return head;
	}
}
