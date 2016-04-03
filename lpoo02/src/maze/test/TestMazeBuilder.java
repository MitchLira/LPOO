/*
 * 
 */
package maze.test;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Arrays;
import java.util.Random;


import maze.logic.Labirinto;

// TODO: Auto-generated Javadoc
/**
 * The Class TestMazeBuilder.
 */
public class TestMazeBuilder {
	
	/**
	 * The Class Point.
	 */
	// Auxiliary class
	public static class Point {		
		
		/** The y. */
		private int x, y;
		
		/**
		 * Gets the x.
		 *
		 * @return the x
		 */
		public int getX() {
			return x;
		}
		
		/**
		 * Gets the y.
		 *
		 * @return the y
		 */
		public int getY() {
			return y;
		}
		
		/**
		 * Instantiates a new point.
		 *
		 * @param y the y
		 * @param x the x
		 */
		public Point(int y, int x) {
			this.x = x;
			this.y = y;
		}

		/**
		 * Adjacent to.
		 *
		 * @param p the p
		 * @return true, if successful
		 */
		public boolean adjacentTo(Point p) {
			return Math.abs(p.x - this.x) + Math.abs(p.y - this.y) == 1;
		}
	}

	// a) the maze boundaries must have exactly one exit and everything else walls
	/**
	 * Check boundaries.
	 *
	 * @param m the m
	 * @return true, if successful
	 */
	// b) the exit cannot be a corner
	private boolean checkBoundaries(char [][] m) {
		int countExit = 0;
		int n = m.length;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				if (i == 0 || j == 0 || i == n - 1 || j == n - 1)
					if (m[i][j] == 'S')
						if ((i == 0 || i == n-1) && (j == 0 || j == n-1))
							return false;
						else
							countExit++;
					else if (m[i][j] != 'X')
						return false;
		return countExit == 1;
	}
	

	// d) there cannot exist 2x2 (or greater) squares with blanks only 
	// e) there cannot exit 2x2 (or greater) squares with blanks in one diagonal and walls in the other
	/**
	 * Checks for square.
	 *
	 * @param maze the maze
	 * @param square the square
	 * @return true, if successful
	 */
	// d) there cannot exist 3x3 (or greater) squares with walls only
	private boolean hasSquare(char[][] maze, char[][] square) {
		for (int i = 0; i < maze.length - square.length; i++)
			for (int j = 0; j < maze.length - square.length; j++) {
				boolean match = true;
				for (int y = 0; y < square.length; y++)
					for (int x = 0; x < square.length; x++) {
						if (maze[i+y][j+x] != square[y][x])
							match = false;
					}
				if (match)
					return true;
			}		
		return false; 
	}

	/**
	 * Find pos.
	 *
	 * @param maze the maze
	 * @param c the c
	 * @return the point
	 */
	private Point findPos(char [][] maze, char c) {
		for (int x = 0; x < maze.length; x++)			
			for (int y = 0; y < maze.length; y++)
				if (maze[y][x] == c)
					return new Point(y, x);
		return null;		
	}
	
	/**
	 * Check exit reachable.
	 *
	 * @param maze the maze
	 * @return true, if successful
	 */
	// c) there must exist a path between any blank cell and the maze exit 
	private boolean checkExitReachable(char [][] maze) {
		Point p = findPos(maze, 'S');
		boolean [][] visited = new boolean[maze.length] [maze.length];
		
		visit(maze, p.getY(), p.getX(), visited);
		
		for (int i = 0; i < maze.length; i++)
			for (int j = 0; j < maze.length; j++)
				if (maze[i][j] != 'X' && ! visited[i][j] )
					return false;
		
		return true; 
	}
	
	// auxiliary method used by checkExitReachable
	/**
	 * Visit.
	 *
	 * @param m the m
	 * @param i the i
	 * @param j the j
	 * @param visited the visited
	 */
	// marks a cell as visited and proceeds recursively to its neighbors
	private void visit(char[][] m, int i, int j, boolean [][] visited) {
		if (i < 0 || i >= m.length || j < 0 || j >= m.length)
			return;
		if (m[i][j] == 'X' || visited[i][j])
			return;
		visited[i][j] = true;
		visit(m, i-1, j, visited);
		visit(m, i+1, j, visited);
		visit(m, i, j-1, visited);
		visit(m, i, j+1, visited);
	}
	
	/**
	 * Test random maze generator.
	 *
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	@Test
	public void testRandomMazeGenerator() throws IllegalArgumentException {
		int numMazes = 1000; // number of mazes to generate and test
		int maxMazeSize = 101; // can change to any odd number >= 5
		int minMazeSize = 5;
		
		Labirinto builder = new Labirinto();
		char[][] badWalls = {
				{'X', 'X', 'X'},
				{'X', 'X', 'X'},
				{'X', 'X', 'X'}};
		char[][] badSpaces = {
				{' ', ' '},
				{' ', ' '}};
		char[][] badDiagonalDown = {
				{'X', ' '},
				{' ', 'X'}};
		char[][] badDiagonalUp = {
				{' ', 'X'},
				{'X', ' '}};
		
		Random rand = new Random(); 
		
		for (int i = 0; i < numMazes; i++) {
			int size = maxMazeSize == minMazeSize? minMazeSize : minMazeSize + 2 * rand.nextInt((maxMazeSize - minMazeSize)/2);
			char[][] m = builder.ColocarCarateres(builder.GerarLabirinto(size), 1);
			assertTrue("Invalid maze boundaries in maze:\n" + m, checkBoundaries(m));			
			assertTrue("Invalid walls in maze:\n" + m, ! hasSquare(m, badWalls));
			assertTrue("Invalid spaces in maze:\n" + m, ! hasSquare(m, badSpaces));
			assertTrue("Invalid diagonals in maze:\n" + m, ! hasSquare(m, badDiagonalDown));
			assertTrue("Invalid diagonals in maze:\n" + m, ! hasSquare(m, badDiagonalUp));
			assertTrue("Maze exit not reachable in maze:\n" + m, checkExitReachable(m));			
			assertNotNull("Missing exit in maze:\n" + m, findPos(m, 'S'));
			assertNotNull("Missing hero in maze:\n" + m, findPos(m, 'H'));
//			assertNotNull("Missing dragon in maze:\n" + m, findPos(m, 'D'));
			assertNotNull("Missing sward in maze:\n" + m, findPos(m, 'E'));
			assertFalse("Adjacent hero and dragon in maze:\n" + str(m), findPos(m, 'H').adjacentTo(findPos(m, 'D')));
		}	
	}
	
	/**
	 * Str.
	 *
	 * @param maze the maze
	 * @return the string
	 */
	public String str(char [][] maze) {
		StringBuilder s = new StringBuilder();
		for (char [] line : maze) {
			s.append(Arrays.toString(line));
			s.append("\n");
		}
		return s.toString();
	}
}