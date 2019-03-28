package br.pre.crackcodew.chap5.bitmanipluation;

import br.pre.crackcodew.lib.AssortedMethods;

public class Prob5_6 {
	public static void main(String[] args) {

	}
}

class Prob5_6A {
	public static int bitSwapRequired(int a, int b) {
		int count = 0;
		int c = a ^ b;
		while (c != 0) {
			count += c & 1; // Increment count if c ends with a 1
			c >>>= 1; // Shift right by 1
		}
		return count;
	}

	public static void main(String[] args) {
		int a = -23432;
		int b = 512132;
		System.out.println(a + ": " + AssortedMethods.toFullBinaryString(a));
		System.out.println(b + ": " + AssortedMethods.toFullBinaryString(b));
		System.out.println("Required number of bits: " + bitSwapRequired(a, b));
	}
}

class Prob5_6B {
	public static int bitSwapRequired(int a, int b) {
		int count = 0;
		int c = a ^ b;

		System.out.println("****");
		System.out.println(c + ": " + AssortedMethods.toFullBinaryString(c));
		while (c != 0) {
			System.out.println("c - 1: " + c + ": " + AssortedMethods.toFullBinaryString(c - 1));
			c = c & (c - 1);
			System.out.println("c: " + c + ": " + AssortedMethods.toFullBinaryString(c));
			count++;
			System.out.println("****");
		}
		return count;
	}

	public static void main(String[] args) {
		int a = -23432;
		int b = 512132;
		System.out.println(a + ": " + AssortedMethods.toFullBinaryString(a));
		System.out.println(b + ": " + AssortedMethods.toFullBinaryString(b));
		System.out.println("Required number of bits: " + bitSwapRequired(a, b));
	}
}
