package maze.logic;

public class Point {
	
	private int x, y;
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean adjacentTo(Point p) {
		return Math.abs(p.x - this.x) + Math.abs(p.y - this.y) == 1;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}	
}
