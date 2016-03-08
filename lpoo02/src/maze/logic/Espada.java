package maze.logic;

public class Espada {
	
	public int x;
	public int y;
	public char simbol;
	
	public Espada(){
		this.x = 8;
		this.y = 1;
		this.simbol = 'E';
	}
	
	public Espada(int y, int x){
		this.y = y;
		this.x = x;
		this.simbol = 'E';
	}

	public Point getEspPos(){
		Point point = new Point(y, x);
		return point;
	}
	
	public static void main(String[] args) {
		
	}

}
