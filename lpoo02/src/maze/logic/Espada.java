/**  
* Espada.java - classe da espada do labirinto  
* @author  Miguel Lira e Miriam Goncalves 
*/

package maze.logic;


// TODO: Auto-generated Javadoc
/**
 * The Class Espada.
 */
public class Espada {
	
	/** The x. */
	public int x;
	
	/** The y. */
	public int y;
	
	/** The simbol. */
	public char simbol;
	
	/**
	 * Construtor por default da espada.
	 */
	public Espada(){
		this.x = 8;
		this.y = 1;
		this.simbol = 'E';
	}
	
	/**
	 * Construtor da espada com as suas coordenadas.
	 *
	 * @param y indica a linha da espada
	 * @param x indica a coluna da espada
	 */
	public Espada(int y, int x){
		this.y = y;
		this.x = x;
		this.simbol = 'E';
	}

	/**
	 * Get da posicao da espada.
	 *
	 * @return point coordenadas da espada
	 */
	public Point getEspPos(){
		Point point = new Point(y, x);
		return point;
	}
	
	/**
	 * Set da posicao da espada a 0 para quando o heroi a encontrar.
	 */
	public void setEspada(){
		this.x = 0;
		this.y = 0;
	}
	
	/**
	 * Set da posicao da espada.
	 *
	 * @param l inteiro que indica a linha da espada
	 * @param c inteiro que indica a coluna da espada
	 */
	public void setEspadaPosicao(int l, int c)
	{
		this.y = l;
		this.x = c;
	}
	


}
