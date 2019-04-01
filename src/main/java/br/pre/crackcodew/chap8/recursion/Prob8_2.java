package br.pre.crackcodew.chap8.recursion;

import java.util.ArrayList;
import java.util.HashSet;

import br.pre.crackcodew.lib.AssortedMethods;

/**
 * <b>Robot in a Grid:</b> Imagine a robot sitting on the upper left corner of
 * grid with r rows and c columns. The robot can only move in two directions,
 * right and down, but certain cells are "off limits" such that the robot cannot
 * step on them. Design an algorithm to find a path for the robot from the top
 * left to the bottom right.
 * 
 * @author bbadarch
 *
 */
public class Prob8_2 {
	public static void main(String[] args) {

	}
}

class Point {
	public int row, column;

	public Point(int row, int column) {
		this.row = row;
		this.column = column;
	}

	@Override
	public String toString() {
		return "(" + row + ", " + column + ")";
	}

	@Override
	public int hashCode() {
		return this.toString().hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if ((o instanceof Point) && (((Point) o).row == this.row) && (((Point) o).column == this.column)) {
			return true;
		} else {
			return false;
		}
	}
}

class Prob8_2A {

	public static ArrayList<Point> getPath(boolean[][] maze) {
		if (maze == null || maze.length == 0)
			return null;
		ArrayList<Point> path = new ArrayList<Point>();
		if (getPath(maze, maze.length - 1, maze[0].length - 1, path)) {
			return path;
		}
		return null;
	}

	public static boolean getPath(boolean[][] maze, int row, int col, ArrayList<Point> path) {
		// If out of bounds or not available, return.
		if (col < 0 || row < 0 || !maze[row][col]) {
			return false;
		}

		boolean isAtOrigin = (row == 0) && (col == 0);

		// If there's a path from the start to my current location, add my location.
		if (isAtOrigin || getPath(maze, row, col - 1, path) || getPath(maze, row - 1, col, path)) {
			Point p = new Point(row, col);
			path.add(p);
			return true;
		}

		return false;
	}

	public static void main(String[] args) {
		int size = 5;
		boolean[][] maze = AssortedMethods.randomBooleanMatrix(size, size, 70);

		AssortedMethods.printMatrix(maze);

		ArrayList<Point> path = getPath(maze);
		if (path != null) {
			System.out.println(path.toString());
		} else {
			System.out.println("No path found.");
		}
	}

}

class Prob8_2B {
	public static ArrayList<Point> getPath(boolean[][] maze) {
		if (maze == null || maze.length == 0)
			return null;
		ArrayList<Point> path = new ArrayList<Point>();
		HashSet<Point> failedPoints = new HashSet<Point>();
		if (getPath(maze, maze.length - 1, maze[0].length - 1, path, failedPoints)) {
			return path;
		}
		return null;
	}

	public static boolean getPath(boolean[][] maze, int row, int col, ArrayList<Point> path, HashSet<Point> failedPoints) {
		/* If out of bounds or not available, return. */
		if (col < 0 || row < 0 || !maze[row][col]) {
			return false;
		}

		Point p = new Point(row, col);

		/* If we've already visited this cell, return. */
		if (failedPoints.contains(p)) {
			return false;
		}

		boolean isAtOrigin = (row == 0) && (col == 0);

		/* If there's a path from the start to my current location, add my location. */
		if (isAtOrigin || getPath(maze, row, col - 1, path, failedPoints) || getPath(maze, row - 1, col, path, failedPoints)) {
			path.add(p);
			return true;
		}

		failedPoints.add(p); // Cache result
		return false;
	}

	public static void main(String[] args) {
		int size = 20;
		boolean[][] maze = AssortedMethods.randomBooleanMatrix(size, size, 60);

		AssortedMethods.printMatrix(maze);

		ArrayList<Point> path = getPath(maze);
		if (path != null) {
			System.out.println(path.toString());
		} else {
			System.out.println("No path found.");
		}
	}

}
