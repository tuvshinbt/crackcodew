package br.pre.crackcodew.chap1.arrstr;

/**
 * Is Unique: Implement an algorithm to determine if a string has all unique
 * characters. What if you cannot use additional data structures?
 * 
 * @author bbadarch
 *
 */
public class Prob1 {

	public static void main(String[] args) {
		Prob1 prob1 = new Prob1();
		System.out.println("Result of isUniqueChars - " + prob1.isUniqueChars("abc"));
		System.out.println("Result of isUniqueChars2 - " + prob1.isUniqueChars2("abc"));
	}

	boolean isUniqueChars(String str) {
		if (str.length() > 128)
			return false;
		boolean[] char_set = new boolean[128];
		for (int i = 0; i < str.length(); i++) {
			int val = str.charAt(i);
			if (char_set[val]) { // Already found this char in string
				return false;
			}
			char_set[val] = true;
		}
		return true;
	}

	boolean isUniqueChars2(String str) {
		int checker = 0;
		for (int i = 0; i < str.length(); i++) {
			int val = str.charAt(i) - 'a';
			if ((checker & (1 << val)) > 0) {
				return false;
			}
			checker |= (1 << val);
		}
		return true;
	}
}
