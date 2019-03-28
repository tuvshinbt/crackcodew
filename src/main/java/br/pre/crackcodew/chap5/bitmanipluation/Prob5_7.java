package br.pre.crackcodew.chap5.bitmanipluation;

import br.pre.crackcodew.lib.AssortedMethods;

public class Prob5_7 {
	public static int swapOddEvenBits(int x) {
		return (((x & 0xaaaaaaaa) >>> 1) | ((x & 0x55555555) << 1));
	}

	public static void main(String[] args) {
		int a = 234321;
		System.out.println(a + ": " + AssortedMethods.toFullBinaryString(a));
		int b = swapOddEvenBits(a);
		System.out.println(b + ": " + AssortedMethods.toFullBinaryString(b));
	}

}
