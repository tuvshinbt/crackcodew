package br.pre.crackcodew.chap1.arrstr;

import java.util.Arrays;

/**
 * Check Permutation: Given two strings, write a method to decide if one is a
 * permutation of the other.
 * 
 * @author bbadarch
 *
 */
public class Prob2 {

	public static void main(String[] args) {
		String a = "abc", b = "bcd";
		Prob2 prob2 = new Prob2();
		System.out.println(prob2.permutation(a, b));
		Prob2Solution2 prob2Solution2 = new Prob2Solution2();
		System.out.println(prob2Solution2.permutation(a, b));
	}

	String sort(String s) {
		char[] content = s.toCharArray();
		java.util.Arrays.sort(content);
		return new String(content);
	}

	boolean permutation(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}
		return sort(s).equals(sort(t));
	}
}

class Prob2Solution2 {
	boolean permutation(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}

		int[] letters = new int[128]; // Assumption

		char[] s_array = s.toCharArray();
		for (char c : s_array) { // count number of each char in s.
			letters[c]++;
		}
		Arrays.stream(letters).forEach(System.out::print);
		System.out.println("");

		for (int i = 0; i < t.length(); i++) {
			int c = (int) t.charAt(i);
			letters[c]--;
			if (letters[c] < 0) {
				return false;
			}
		}
		Arrays.stream(letters).forEach(System.out::print);
		System.out.println("");
		return true;
	}
}
