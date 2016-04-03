/**  
* Dragao.java - classe do dragao do labirinto  
* @author  Miguel Lira e Miriam Goncalves 
*/


package maze.logic;


// TODO: Auto-generated Javadoc
/**
 * The Class Dragao.
 */
public class Dragao {
	
	/** The x. */
	private int x;
	
	/** The y. */
	private int y;
	
	/** The vida. */
	private boolean vida;
	
	/** The adormecido. */
	private boolean adormecido;
	
	/** The simbolo. */
	private char simbolo;
	
	
	/**
	 * Construtor por default do Dragao.
	 */
	public Dragao()
	{
		this.x = 3;
		this.y = 1;
		this.vida = true;
		this.adormecido = false;
		this.simbolo = 'D';
	}
	
	/**
	 * Construtor com a posicao do Dragao.
	 *
	 * @param linha            inteiro que indica a linha do dragao
	 * @param coluna            inteiro que indica a coluna do dragao
	 */
	public Dragao(int linha, int coluna)
	{
		this.y = linha;
		this.x = coluna;
		this.vida = true;
		this.adormecido = false;
		this.simbolo = 'D';
	}
	
	
	/**
	 * Get do simbolo do dragao.
	 *
	 * @return simbolo char atual do dragao
	 */
	public char getSimbolo(){
		return simbolo;
	}
	
	
	/**
	 * Set do simbolo do dragao para D.
	 */
	public void setSimbolo(){
		this.simbolo = 'D';
	}

	/**
	 * Get da coluna onde o dragao se encontra.
	 *
	 * @return x inteiro que indica a coluna onde o dragao se encontra
	 */
	public int getColuna()
	{
		return this.x;
	}
	
	
	/**
	 * Set da coluna do dragao.
	 *
	 * @param coluna inteiro que representa a coluna onde o dragao vai ficar
	 */
	public void setColuna(int coluna)
	{
		this.x = coluna;
	}
	
	/**
	 * Get da linha onde o dragao se encontra.
	 *
	 * @return y inteiro que indica a linha onde o dragao se encontra
	 */
	public int getLinha()
	{
		return this.y;
	}

	/**
	 * Set da linha do dragao.
	 *
	 * @param linha            inteiro que representa a linha onde o dragao vai ficar
	 */
	public void setLinha(int linha)
	{
		this.y = linha;
	}
	
	
	/**
	 * Set da posicao do dragao.
	 *
	 * @param l            inteiro que representa a linha onde o dragao vai ficar
	 * @param c            inteiro que representa a coluna onde o dragao vai ficar
	 */
	public void setPosicaoDragao(int l, int c)
	{
		this.y = l;
		this.x = c;
	}
	
	/**
	 * Get da vida do dragao.
	 *
	 * @return vida boleano que indica se o dragao esta vivo ou morto
	 */
	public boolean  getVida()
	{
		return this.vida;
	}
	
	/**
	 * Get do parametro adormecido do dragao.
	 *
	 * @return adormecido boleano que indica se o dragao esta adormecido ou nao
	 */
	public boolean getAdormecido()
	{
		return this.adormecido;
	}
	
	/**
	 *   
	 * Mata o dragao pondo a vida a false.
	 */
	public void DragaoMorre()
	{
		this.vida = false;
	}
	
	/**
	 * Adormece o dragao pondo o simbolo a d e o paramentro adormecido a true.
	 */
	public void DragaoDorme()
	{
		this.simbolo = 'd';
		this.adormecido = true;
	}
	
	/**
	 * Acorda o dragao pondo o simbolo a D e o paramentro adormecido a false.
	 */
	public void DragaoAcorda()
	{
		this.simbolo = 'D';
		this.adormecido = false;
	}
	
	/**
	 * Representa o dragao quando esta na posicao da espada pondo o simbolo a F.
	 */
	public void DragEsp(){
		this.simbolo = 'F';
	}
	
	/**
	 *   
	 * Mantem a posicao atual do dragao no mesmo sitio.
	 */
	public void mantemPosicaoDragao()
	{
		this.setColuna(x);
		this.setLinha(y);
	}
	
	/**
	 *   
	 * Move o dragao para norte diminuindo uma unidade na linha.
	 */
	public void moveDragaoNorte()
	{
		this.setLinha(y-1);
		this.setColuna(x);
		
	}
	
	/**
	 *   
	 * Move o dragao para sul aumentando uma unidade na linha.
	 */
	public void moveDragaoSul()
	{
		this.setLinha(y+1);
		this.setColuna(x);
	}
	
	/**
	 *   
	 * Move o dragao para direitra aumentando uma unidade na coluna.
	 */
	public void moveDragaoDireita()
	{
		this.setLinha(y);
		this.setColuna(x+1);
	}
	
	/**
	 *   
	 * Move o dragao para esquerda diminuindo uma unidade na coluna.
	 */
	public void moveDragaoEsquerda()
	{
		this.setLinha(y);
		this.setColuna(x-1);
	}
	
	/**
	 *   
	 * Get da posicao do dragao no labirinto.
	 *
	 * @return point posicao do dragao
	 */
	public Point getDragaoPosicao(){
		Point point = new Point(y, x);
		return point;
	}
	
	
	
	


}
