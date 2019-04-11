package br.pre.crackcodew.chap8.recursion;

import java.util.ArrayList;

/**
 * <b>Permutations without Dups:</b> Write a method to compute all permutations
 * of a string of unique characters.
 * 
 * @author bbadarch
 *
 */
public class Prob8_7 {
	public static void main(String[] args) {

	}
}

class Prob8_7A {

	public static ArrayList<String> getPerms(String str) {
		if (str == null) {
			return null;
		}
		ArrayList<String> permutations = new ArrayList<String>();
		if (str.length() == 0) { // base case
			permutations.add("");
			return permutations;
		}

		char first = str.charAt(0); // get the first character
		String remainder = str.substring(1); // remove the first character
		ArrayList<String> words = getPerms(remainder);
		for (String word : words) {
			for (int j = 0; j <= word.length(); j++) {
				String s = insertCharAt(word, first, j);
				permutations.add(s);
			}
		}
		return permutations;
	}

	public static String insertCharAt(String word, char c, int i) {
		String start = word.substring(0, i);
		String end = word.substring(i);
		return start + c + end;
	}

	public static void main(String[] args) {
		ArrayList<String> list = getPerms("abcde");
		System.out.println("There are " + list.size() + " permutations.");
		for (String s : list) {
			System.out.println(s);
		}
	}

}

class Prob8_7B {
	public static ArrayList<String> getPerms(String remainder) {
		int len = remainder.length();
		ArrayList<String> result = new ArrayList<String>();

		/* Base case. */
		if (len == 0) {
			result.add(""); // Be sure to return empty string!
			return result;
		}

		for (int i = 0; i < len; i++) {
			/* Remove char i and find permutations of remaining characters. */
			String before = remainder.substring(0, i);
			String after = remainder.substring(i + 1, len);
			ArrayList<String> partials = getPerms(before + after);

			/* Prepend char i to each permutation. */
			for (String s : partials) {
				result.add(remainder.charAt(i) + s);
			}
		}

		return result;
	}

	public static void main(String[] args) {
		ArrayList<String> list = getPerms("abc");
		System.out.println("There are " + list.size() + " permutations.");
		for (String s : list) {
			System.out.println(s);
		}
	}

}
