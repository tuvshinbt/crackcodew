package br.pre.crackcodew.chap2.linkedlist;

import br.pre.crackcodew.lib.AssortedMethods;
import br.pre.crackcodew.lib.LinkedListNode;

/**
 * Delete Middle Node: Implement an algorithm to delete a node in the middle
 * (Le., any node but the fi rst and last node, not necessarily the exact
 * middle) of a singly linked list, given only access to that node. <br/>
 * EXAMPLE <br/>
 * Input: the node c from the linked list a - >b- >c - >d - >e- >f<br/>
 * Result: nothing is returned, but the new linked list looks like a->b->d->e->f
 * 
 * @author bbadarch
 *
 */
public class Prob2_3 {
	public static boolean deleteNode(LinkedListNode n) {
		if (n == null || n.next == null) {
			return false; // Failure
		}
		LinkedListNode next = n.next;
		n.data = next.data;
		n.next = next.next;
		return true;
	}

	public static void main(String[] args) {
		LinkedListNode head = AssortedMethods.randomLinkedList(10, 0, 10);
		System.out.println(head.printForward());
		deleteNode(head.next.next.next.next); // delete node 4
		System.out.println(head.printForward());
	}

}
