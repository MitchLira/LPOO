package maze.logic;

public class Point {
	
	public int x, y;
		
	public Point(int y, int x) {
		this.x = x;
		this.y = y;
	}

	public boolean adjacentTo(Point p) {
		return Math.abs(p.x - this.x) + Math.abs(p.y - this.y) == 1;
	}
	
	public boolean iguais(Point p){
		if(p.x == this.x && p.y == this.y)
			return true;
		else
			return false;
	}
	public boolean temCoordImpares() {

		return ((x % 2 != 0) && (y % 2 != 0));
	}
	

	
	@Override
	public boolean equals(Object obj) {

		if (this.getClass() != obj.getClass())
			return false;

		Point other = (Point) obj;
		return (this.x == other.x && this.y == other.y);
	}
	
}
