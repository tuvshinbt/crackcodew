package br.pre.crackcodew.chap2.linkedlist;

import java.util.HashSet;

import br.pre.crackcodew.lib.LinkedListNode;

/**
 * Remove Dups: Write code to remove duplicates from an unsorted linked list.
 * FOLLOW UP<br/>
 * How would you solve this problem if a temporary buffer is not allowed?<br/>
 * 
 * @author bbadarch
 *
 */
public class Prob2_1 {
	public static void deleteDups(LinkedListNode n) {
		HashSet<Integer> set = new HashSet<Integer>();
		LinkedListNode previous = null;
		while (n != null) {
			if (set.contains(n.data)) {
				previous.next = n.next;
			} else {
				set.add(n.data);
				previous = n;
			}
			n = n.next;
		}
	}

	public static void deleteDupsSolution2(LinkedListNode head) {
		LinkedListNode current = head;
		while (current != null) {
			/* Remove all future nodes that have the same value */
			LinkedListNode runner = current;
			while (runner.next != null) {
				if (runner.next.data == current.data) {
					runner.next = runner.next.next;
				} else {
					runner = runner.next;
				}
			}
			current = current.next;
		}
	}

	public static void main(String[] args) {
		LinkedListNode first = new LinkedListNode(0, null, null); // AssortedMethods.randomLinkedList(1000, 0, 2);
		LinkedListNode head = first;
		LinkedListNode second = first;
		for (int i = 1; i < 8; i++) {
			second = new LinkedListNode(i % 3, null, null);
			first.setNext(second);
			second.setPrevious(first);
			first = second;
		}
		LinkedListNode head1 = head.clone();
		System.out.println(head.printForward());
		deleteDups(head);
		System.out.println(head.printForward());
		deleteDupsSolution2(head1);
		System.out.println(head1.printForward());
	}
}
