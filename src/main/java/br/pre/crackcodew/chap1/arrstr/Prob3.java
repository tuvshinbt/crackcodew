package br.pre.crackcodew.chap1.arrstr;

import java.util.Arrays;

/**
 * URLify: Write a method to replace all spaces in a string with '%20: You may
 * assume that the string has sufficient space at the end to hold the additional
 * characters, and that you are given the "true" length of the string. (Note: if
 * implementing in Java, please use a character array so that you can perform
 * this operation in place.) <br/>
 * EXAMPLE <br/>
 * Input: "Mr John Smith     ", 13 <br/>
 * Output: "Mr%20John%20Smith"
 * 
 * @author bbadarch
 *
 */
public class Prob3 {

	public static void main(String[] args) {
		Prob3 prob3 = new Prob3();
		String input = "Mr John Smith    ";
		int trueLength = 13;
		char[] charsOfInput = input.toCharArray();
		System.out.println(new String(charsOfInput));
		prob3.replaceSpaces(charsOfInput, trueLength);
		System.out.println(new String(charsOfInput));
	}

	void replaceSpaces(char[] str, int trueLength) {
		int spaceCount = 0, index, i = 0;
		for (i = 0; i < trueLength; i++) {
			if (str[i] == ' ') {
				spaceCount++;
			}
		}
		index = trueLength + spaceCount * 2;
		if (trueLength < str.length)
			str[trueLength] = '\0'; // End array
		for (i = trueLength - 1; i >= 0; i--) {
			if (str[i] == ' ') {
				str[index - 1] = '0';
				str[index - 2] = '2';
				str[index - 3] = '%';
				index = index - 3;
			} else {
				str[index - 1] = str[i];
				index--;
			}
		}
		
	}
}
