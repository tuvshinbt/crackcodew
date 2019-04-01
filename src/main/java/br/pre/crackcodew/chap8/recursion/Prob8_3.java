package br.pre.crackcodew.chap8.recursion;

import java.util.Arrays;

import br.pre.crackcodew.lib.AssortedMethods;

/**
 * <b>Magic Index:</b> A magic index in an array A [1. .. n -1] is defined to be
 * an index such that A[ i] i. Given a sorted array of distinct integers, write
 * a method to find a magic index, if one exists, in array A. FOLLOW UP What if
 * the values are not distinct?
 * 
 * @author bbadarch
 *
 */
public class Prob8_3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

class Prob8_3A {

	public static int magicSlow(int[] array) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == i) {
				return i;
			}
		}
		return -1;
	}

	public static int magicFast(int[] array, int start, int end) {
		if (end < start) {
			return -1;
		}
		int mid = (start + end) / 2;
		if (array[mid] == mid) {
			return mid;
		} else if (array[mid] > mid) {
			return magicFast(array, start, mid - 1);
		} else {
			return magicFast(array, mid + 1, end);
		}
	}

	public static int magicFast(int[] array) {
		return magicFast(array, 0, array.length - 1);
	}

	/* Creates an array that is distinct and sorted */
	public static int[] getDistinctSortedArray(int size) {
		int[] array = AssortedMethods.randomArray(size, -1 * size, size);
		Arrays.sort(array);
		for (int i = 1; i < array.length; i++) {
			if (array[i] == array[i - 1]) {
				array[i]++;
			} else if (array[i] < array[i - 1]) {
				array[i] = array[i - 1] + 1;
			}
		}
		return array;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 1000; i++) {
			int size = AssortedMethods.randomIntInRange(5, 20);
			int[] array = getDistinctSortedArray(size);
//			System.out.println(Arrays.toString(array));
			int v2 = magicFast(array);
			if (v2 == -1 && magicSlow(array) != -1) {
				int v1 = magicSlow(array);
				System.out.println("Incorrect value: index = -1, actual = " + v1 + " " + i);
				System.out.println(AssortedMethods.arrayToString(array));
				break;
			} else if (v2 > -1 && array[v2] != v2) {
				System.out.println("Incorrect values: index= " + v2 + ", value " + array[v2]);
				System.out.println(AssortedMethods.arrayToString(array));
				break;
			}
		}
	}

}

class Prob8_3B {

	public static int magicSlow(int[] array) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == i) {
				return i;
			}
		}
		return -1;
	}

	public static int magicFast(int[] array, int start, int end) {
		if (end < start) {
			return -1;
		}
		int midIndex = (start + end) / 2;
		int midValue = array[midIndex];
		if (midValue == midIndex) {
			return midIndex;
		}
		/* Search left */
		int leftIndex = Math.min(midIndex - 1, midValue);
		int left = magicFast(array, start, leftIndex);
		if (left >= 0) {
			return left;
		}

		/* Search right */
		int rightIndex = Math.max(midIndex + 1, midValue);
		int right = magicFast(array, rightIndex, end);

		return right;
	}

	public static int magicFast(int[] array) {
		return magicFast(array, 0, array.length - 1);
	}

	/* Creates an array that is sorted */
	public static int[] getSortedArray(int size) {
		int[] array = AssortedMethods.randomArray(size, -1 * size, size);
		Arrays.sort(array);
		return array;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 1000; i++) {
			int size = AssortedMethods.randomIntInRange(5, 20);
			int[] array = getSortedArray(size);
			int v2 = magicFast(array);
			if (v2 == -1 && magicSlow(array) != -1) {
				int v1 = magicSlow(array);
				System.out.println("Incorrect value: index = -1, actual = " + v1 + " " + i);
				System.out.println(AssortedMethods.arrayToString(array));
				break;
			} else if (v2 > -1 && array[v2] != v2) {
				System.out.println("Incorrect values: index= " + v2 + ", value " + array[v2]);
				System.out.println(AssortedMethods.arrayToString(array));
				break;
			}
		}
	}

}
