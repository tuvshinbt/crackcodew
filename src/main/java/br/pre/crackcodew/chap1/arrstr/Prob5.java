package br.pre.crackcodew.chap1.arrstr;

import java.util.Arrays;

/**
 * One Away: There are three types of edits that can be performed on strings:
 * insert a character, remove a character, or replace a character. Given two
 * strings, write a function to check if they are one edit (or zero edits) away.
 * EXAMPLE <br/>
 * pale, ple -> true <br/>
 * pales, pale -> true <br/>
 * pale, bale -> true <br/>
 * pale, bae -> false<br/>
 * 
 * @author bbadarch
 *
 */
public class Prob5 {
	public static void main(String[] args) {
		Prob5 prob5 = new Prob5();
		String[][] inputs = { { "pale", "ple" }, { "pales", "pale" }, { "pale", "bale" }, { "pale", "bae" }, };

		Arrays.stream(inputs).forEach(item -> System.out.println(Arrays.deepToString(item) + " result - " + prob5.oneEditAway(item[0], item[1])));
		Arrays.stream(inputs).forEach(item -> System.out.println(Arrays.deepToString(item) + " result - " + prob5.oneEditAwaySolution2(item[0], item[1])));

	}

	boolean oneEditAway(String first, String second) {
		if (first.length() == second.length()) {
			return oneEditReplace(first, second);
		} else if (first.length() + 1 == second.length()) {
			return oneEditlnsert(first, second);
		} else if (first.length() - 1 == second.length()) {
			return oneEditlnsert(second, first);
		}
		return false;
	}

	boolean oneEditReplace(String s1, String s2) {
		boolean foundDifference = false;
		for (int i = 0; i < s1.length(); i++) {
			if (s1.charAt(i) != s2.charAt(i)) {
				if (foundDifference) {
					return false;
				}
				foundDifference = true;
			}
		}
		return true;
	}

	/* Check if you can insert a character into s1 to make s2. */
	boolean oneEditlnsert(String s1, String s2) {
		int index1 = 0;
		int index2 = 0;
		while (index2 < s2.length() && index1 < s1.length()) {
			if (s1.charAt(index1) != s2.charAt(index2)) {
				if (index1 != index2) {
					return false;
				}
				index2++;
			} else {
				index1++;
				index2++;
			}
		}
		return true;
	}

	boolean oneEditAwaySolution2(String first, String second) {
		/* Length checks. */
		if (Math.abs(first.length() - second.length()) > 1) {
			return false;
		}

		/* Get shorter and longer string. */
		String s1 = first.length() < second.length() ? first : second;
		String s2 = first.length() < second.length() ? second : first;
		int index1 = 0;
		int index2 = 0;
		boolean foundDifference = false;
		while (index2 < s2.length() && index1 < s1.length()) {
			if (s1.charAt(index1) != s2.charAt(index2)) {
				/* Ensure that this is the first difference found. */
				if (foundDifference)
					return false;
				foundDifference = true;

				if (s1.length() == s2.length()) { // On replace, move shorter pointer
					index1++;
				}
			} else {
				index1++; // If matching, move shorter pointer
			}
			index2++; // Always move pointer for longer string
		}
		return true;
	}
}
