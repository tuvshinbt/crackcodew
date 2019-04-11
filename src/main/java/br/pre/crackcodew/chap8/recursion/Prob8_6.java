package br.pre.crackcodew.chap8.recursion;

import java.util.Stack;

/**
 * Towers of Hanoi: In the classic problem of the Towers of Hanoi, you have 3
 * towers and N disks of different sizes which can slide onto any tower. The
 * puzzle starts with disks sorted in ascending order of size from top to bottom
 * (Le., each disk sits on top of an even larger one). You have the following
 * constraints: <br/>
 * (1) Only one disk can be moved at a time. <br/>
 * (2) A disk is slid off the top of one tower onto another tower. <br/>
 * (3) A disk cannot be placed on top of a smaller disk. <br/>
 * Write a program to move the disks from the first tower to the last using
 * Stacks.
 * 
 * @author bbadarch
 *
 */
public class Prob8_6 {

	public static void main(String[] args) {
		Tower source = new Tower();
		Tower destination = new Tower();
		Tower buffer = new Tower();

		source.name = "s";
		destination.name = "d";
		buffer.name = "b";

		/* Load up tower */
		int numberOfDisks = 3;
		for (int disk = numberOfDisks - 1; disk >= 0; disk--) {
			source.add(disk);
		}

		source.print();
		source.moveDisks(numberOfDisks, destination, buffer);
		destination.print();
	}
}

class Tower {
	private Stack<Integer> disks = new Stack<Integer>();
	public String name;

	public void add(int d) {
		if (!disks.isEmpty() && disks.peek() <= d) {
			System.out.println("Error placing disk " + d);
		} else {
			disks.push(d);
		}
	}

	public void moveTopTo(Tower t) {
		int top = disks.pop();
		t.add(top);
	}

	public void print() {
		System.out.println("Contents of Tower " + name + ": " + disks.toString());
	}

	public void moveDisks(int quantity, Tower destination, Tower buffer) {
		if (quantity <= 0)
			return;

		moveDisks(quantity - 1, buffer, destination);
		System.out.println("Move " + disks.peek() + " from " + this.name + " to " + destination.name);
		moveTopTo(destination);
		buffer.moveDisks(quantity - 1, destination, this);
	}
}
