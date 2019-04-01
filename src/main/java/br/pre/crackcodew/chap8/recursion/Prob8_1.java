package br.pre.crackcodew.chap8.recursion;

import java.util.Arrays;

/**
 * <b>Triple Step:</b> A child is running up a staircase with n steps and can
 * hop either 1 step, 2 steps, or 3 steps at a time. Implement a method to count
 * how many possible ways the child can run up the stairs.
 * 
 * @author bbadarch
 *
 */
public class Prob8_1 {
	public static void main(String[] args) {

	}
}

class Prob8_1A {

	public static int countWays(int n) {
		if (n < 0) {
			return 0;
		} else if (n == 0) {
			return 1;
		} else {
			return countWays(n - 1) + countWays(n - 2) + countWays(n - 3);
		}
	}

	public static void main(String[] args) {
		int n = 3;
		int ways = countWays(n);
		System.out.println(ways);
	}

}

class Prob8_1B {

	public static int countWays(int n) {
		int[] map = new int[n + 1];
		Arrays.fill(map, -1);
		return countWays(n, map);
	}

	public static int countWays(int n, int[] memo) {
		if (n < 0) {
			return 0;
		} else if (n == 0) {
			return 1;
		} else if (memo[n] > -1) {
			return memo[n];
		} else {
			memo[n] = countWays(n - 1, memo) + countWays(n - 2, memo) + countWays(n - 3, memo);
			return memo[n];
		}
	}

	public static void main(String[] args) {
		int n = 4;
		int ways = countWays(n);
		System.out.println(ways);
	}

}
