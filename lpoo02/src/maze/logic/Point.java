/**  
* Point.java - classe do Point do labirinto  
* @author  Miguel Lira e Miriam Goncalves 
*/

package maze.logic;

// TODO: Auto-generated Javadoc
/**
 * The Class Point.
 */
public class Point {
	
	/** The y. */
	public int x, y;
		
	/**
	 * Construtor do Point .
	 *
	 * @param y inteiro que indica o y
	 * @param x inteiro que indica o x
	 */
	public Point(int y, int x) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Verifica se dois pontos sao adjacentes.
	 *
	 * @param p point
	 * @return boleano que indica se os dois pontos estao adjacentes
	 */
	public boolean adjacentTo(Point p) {
		return Math.abs(p.x - this.x) + Math.abs(p.y - this.y) == 1;
	}
	
	/**
	 * Verifica se dois pontos sao iguais.
	 *
	 * @param p point
	 * @return boleano que indica se sao iguais ou nao
	 */
	public boolean iguais(Point p){
		if(p.x == this.x && p.y == this.y)
			return true;
		else
			return false;
	}
	
	/**
	 * Verifica se o ponto tem coordenadas impares.
	 *
	 * @return boleano que indica se sao impares ou nao
	 */
	public boolean temCoordImpares() {

		return ((x % 2 != 0) && (y % 2 != 0));
	}
	

	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	/* 
	 * Equals da classe point para permitir comparar dois objetos desta classe
	 */
	@Override
	public boolean equals(Object obj) {

		if (this.getClass() != obj.getClass())
			return false;

		Point other = (Point) obj;
		return (this.x == other.x && this.y == other.y);
	}
	
}
