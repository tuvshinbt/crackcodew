package br.pre.crackcodew.chap5.bitmanipluation;

import java.util.ArrayList;

/**
 * Flip Bit to Win: You have an integer and you can flip exactly one bit from a
 * 0 to a 1. Write code to find the length of the longest sequence of 1 s you
 * could create. <br/>
 * EXAMPLE <br/>
 * Input: 1775 (or: 110111101111)<br/>
 * Output: 8
 * 
 * @author bbadarch
 *
 */
public class Prob5_3 {

	public static void main(String[] args) {
		int x = 5;
		Integer.toBinaryString(5 & 1);
		Integer.toBinaryString(x >> 1);
		Integer.toBinaryString(x >>>= 1);
		System.out.println(x);
	}

}

class Prob5_3A {

	public static int SEQUENCE_LENGTH = 32;

	public static boolean getBit(int num, int i) {
		return ((num & (1 << i)) != 0);
	}

	public static int longestSequence(int n) {
		int maxSeq = 0;

		for (int i = 0; i < SEQUENCE_LENGTH; i++) {
			maxSeq = Math.max(maxSeq, longestSequenceOf1s(n, i));
		}

		return maxSeq;
	}

	public static int longestSequenceOf1s(int n, int indexToIgnore) {
		int max = 0;
		int counter = 0;
		for (int i = 0; i < SEQUENCE_LENGTH; i++) {
			if (i == indexToIgnore || getBit(n, i)) {
				counter++;
				max = Math.max(counter, max);
			} else {
				counter = 0;
			}
		}
		return max;
	}

	public static void main(String[] args) {
		int original_number = 5;
		int new_number = longestSequence(original_number);

		System.out.println(Integer.toBinaryString(original_number));
		System.out.println(new_number);
	}

}

class Prob5_3B {

	public static int longestSequence(int n) {
		if (n == -1)
			return Integer.BYTES * 8;
		ArrayList<Integer> sequences = getAlternatingSequences(n);
		return findLongestSequence(sequences);
	}

	/*
	 * Return a list of the sizes of the sequences. The sequence starts off with the
	 * number of 0s (which might be 0) and then alternates with the counts of each
	 * value.
	 */
	public static ArrayList<Integer> getAlternatingSequences(int n) {
		ArrayList<Integer> sequences = new ArrayList<Integer>();

		int searchingFor = 0;
		int counter = 0;

		for (int i = 0; i < Integer.BYTES * 8; i++) {
			if ((n & 1) != searchingFor) {
				sequences.add(counter);
				searchingFor = n & 1; // Flip 1 to 0 or 0 to 1
				counter = 0;
			}
			counter++;
			n >>>= 1;
		}
		sequences.add(counter);

		return sequences;
	}

	public static int findLongestSequence(ArrayList<Integer> seq) {
		int maxSeq = 1;

		for (int i = 0; i < seq.size(); i += 2) {
			int zerosSeq = seq.get(i);
			int onesSeqPrev = i - 1 >= 0 ? seq.get(i - 1) : 0;
			int onesSeqNext = i + 1 < seq.size() ? seq.get(i + 1) : 0;

			int thisSeq = 0;
			if (zerosSeq == 1) { // Can merge
				thisSeq = onesSeqNext + 1 + onesSeqPrev;
			} else if (zerosSeq > 1) { // Just add a one to either side
				thisSeq = 1 + Math.max(onesSeqPrev, onesSeqNext);
			} else if (zerosSeq == 0) { // No zero, but take either side
				thisSeq = Math.max(onesSeqPrev, onesSeqNext);
			}
			maxSeq = Math.max(thisSeq, maxSeq);
		}

		return maxSeq;
	}

	public static void main(String[] args) {
		int original_number = 1775;
		int new_number = longestSequence(original_number);

		System.out.println(Integer.toBinaryString(-1));
		System.out.println(Integer.toBinaryString(original_number));
		System.out.println(new_number);
	}

}

class Prob5_3D {

	public static int flipBit(int a) {
		/* If all 1s, this is already the longest sequence. */
		if (~a == 0)
			return Integer.BYTES * 8;

		int currentLength = 0;
		int previousLength = 0;
		int maxLength = 1; // We can always have a sequence of at least one 1
		while (a != 0) {
			if ((a & 1) == 1) {
				currentLength++;
			} else if ((a & 1) == 0) {
				/* Update to 0 (if next bit is 0) or currentLength (if next bit is 1). */
				previousLength = (a & 2) == 0 ? 0 : currentLength;
				currentLength = 0;
			}
			maxLength = Math.max(previousLength + currentLength + 1, maxLength);
			a >>>= 1;
		}
		return maxLength;
	}

	public static void main(String[] args) {
		System.out.println(flipBit(1775));
	}

}
