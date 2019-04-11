package br.pre.crackcodew.chap8.recursion;

public class Prob8_5 {
	public static void main(String[] args) {

	}
}

class Prob8_5A {

	public static int counter = 0;

	public static int sum(int x, int y) {
		counter++;
		return x + y;
	}

	public static int minProductHelper(int smaller, int bigger) {
		if (smaller == 0) { // 0 x bigger = 0
			return 0;
		} else if (smaller == 1) { // 1 x bigger = bigger
			return bigger;
		}

		/*
		 * Compute half. If uneven, compute other half. If even, double it.
		 */
		int s = smaller >> 1; // Divide by 2
		int side1 = minProductHelper(s, bigger);
		int side2 = side1;
		if (smaller % 2 == 1) {
			counter++;
			side2 = minProductHelper(smaller - s, bigger);
		}
		counter++;
		return side1 + side2;
	}

	public static int minProduct(int a, int b) {
		int bigger = a < b ? b : a;
		int smaller = a < b ? a : b;
		return minProductHelper(smaller, bigger);
	}

	public static void main(String[] args) {
		int a = 7;
		int b = 7;
		int product = a * b;
		int minProduct = minProduct(a, b);
		if (product == minProduct) {
			System.out.println("Success: " + a + " * " + b + " = " + product);
		} else {
			System.out.println("Failure: " + a + " * " + b + " = " + product + " instead of " + minProduct);
		}
		System.out.println("Adds: " + counter);
	}

}

class Prob8_5B {

	public static int counter = 0;

	public static int sum(int x, int y) {
		counter += 1;
		return x + y;
	}

	public static int minProduct(int smaller, int bigger, int[] memo) {
		if (smaller == 0) {
			return 0;
		} else if (smaller == 1) {
			return bigger;
		} else if (memo[smaller] > 0) {
			return memo[smaller];
		}

		/*
		 * Compute half. If uneven, compute other half. If even, double it.
		 */
		int s = smaller >> 1; // Divide by 2
		int side1 = minProduct(s, bigger, memo); // Compute half
		int side2 = side1;
		if (smaller % 2 == 1) {
			counter++;
			side2 = minProduct(smaller - s, bigger, memo);
		}

		/* Sum and cache. */
		counter++;
		memo[smaller] = side1 + side2;
		return memo[smaller];
	}

	public static int minProduct(int a, int b) {
		int bigger = a < b ? b : a;
		int smaller = a < b ? a : b;

		int memo[] = new int[sum(smaller, 1)];
		return minProduct(smaller, bigger, memo);
	}

	public static void main(String[] args) {
		int a = 13494;
		int b = 22323;
		int product = a * b;
		int minProduct = minProduct(a, b);
		if (product == minProduct) {
			System.out.println("Success: " + a + " * " + b + " = " + product);
		} else {
			System.out.println("Failure: " + a + " * " + b + " = " + product + " instead of " + minProduct);
		}
		System.out.println("Adds: " + counter);
	}

}

class Prob8_5C {

	public static int counter = 0;

	public static int minProductHelper(int smaller, int bigger) {
		if (smaller == 0) {
			return 0;
		} else if (smaller == 1) {
			return bigger;
		}

		int s = smaller >> 1;
		int halfProd = minProductHelper(s, bigger);

		if (smaller % 2 == 0) {
			counter++;
			return halfProd + halfProd;
		} else {
			counter += 2;
			return halfProd + halfProd + bigger;
		}
	}

	public static int minProduct(int a, int b) {
		int bigger = a < b ? b : a;
		int smaller = a < b ? a : b;

		return minProductHelper(smaller, bigger);
	}

	public static void main(String[] args) {
		int a = 13494;
		int b = 22323;
		int product = a * b;
		int minProduct = minProduct(a, b);
		if (product == minProduct) {
			System.out.println("Success: " + a + " * " + b + " = " + product);
		} else {
			System.out.println("Failure: " + a + " * " + b + " = " + product + " instead of " + minProduct);
		}
		System.out.println("Adds: " + counter);
	}

}
