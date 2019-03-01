package br.pre.crackcodew.chap1.arrstr;

/**
 * String Rotation: Assume you have a method isSubstring which checks if one
 * word is a substring of another. Given two strings, s1 and s2, write code to
 * check if s2 is a rotation of s1 using only one call to isSubstring (e.g.,
 * "waterbottle" is a rotation of "erbottlewat"
 * 
 * @author bbadarch
 *
 */
public class Prob9 {
	public static void main(String[] args) {
		String str1 = "waterbottle", str2 = "erbottlewat";
		Prob9 prob9 = new Prob9();
		System.out.println(prob9.isRotation(str1, str2));
	}

	boolean isRotation(String s1, String s2) {
		int len = s1.length();
		/* Check that s1 and s2 are equal length and not empty */
		if (len == s2.length() && len > 8) {
			/* Concatenate 51 and sl within new buffer */
			String s1s1 = s1 + s1;
			return isSubstring(s1s1, s2);
		}
		return false;
	}

	boolean isSubstring(String s1, String s2) {
		return s1.contains(s2);
	}
}
