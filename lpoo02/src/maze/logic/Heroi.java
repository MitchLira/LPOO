/**  
* Heroi.java - classe do heroi do labirinto  
* @author  Miguel Lira e Miriam Goncalves 
*/


package maze.logic;

// TODO: Auto-generated Javadoc
/**
 * The Class Heroi.
 */
public class Heroi {
	
	/** The arma. */
	private boolean arma;
	
	/** The vida. */
	private boolean vida;
	
	/** The x. */
	private int x;
	
	/** The y. */
	private int y;
	
	/** The vitoria. */
	private boolean vitoria;
	
	/** The simbolo. */
	private char simbolo;

	/**
	 * Construtor por default do heroi.
	 */
	public Heroi()
	{
		this.arma = false;
		this.vida = true;
		this.y = 1;
		this.x = 1;
		this.vitoria = false;
		this.simbolo = 'H';
	}
	
	/**
	 * Construtor com as coordenadas do heroi.
	 *
	 * @param linha inteiro que indica a linha onde o heroi se encontra
	 * @param coluna inteiro que indica a coluna onde o heroi se encontra
	 */
	public Heroi(int linha, int coluna)
	{
		this.arma = false;
		this.vida = true;
		this.y = linha;
		this.x = coluna;
		this.vitoria = false;
		this.simbolo = 'H';
	}
	
	/**
	 * Set da linha do heroi.
	 *
	 * @param linha inteiro que indica a linha onde o heroi vai ficar
	 */
	public void setLinha(int linha) {
		this.y = linha;
	}

	/**
	 * Set da coluna do heroi.
	 *
	 * @param coluna inteiro que indica a coluna onde o heroi vai ficar
	 */
	public void setColuna(int coluna) {
		this.x = coluna;
	}
	
	/**
	 * Get da linha do heroi.
	 *
	 * @return y inteiro que indica a linha do heroi
	 */
	public int getLinha() {
		return this.y;
	}
	
	/**
	 * Get coluna do heroi.
	 *
	 * @return x inteiro que indica a coluna do heroi
	 */
	public int getColuna(){
		return this.x;
	}
	
	/**
	 * Get vitoria do heroi.
	 *
	 * @return vitoria boleano que indica o parametro vitoria do heroi
	 */
	public boolean getVitoria()
	{
		return this.vitoria;
	}

	

	/**
	 * Get arma do heroi.
	 *
	 * @return arma boleano que indica se o heroi esta armado ou nao
	 */
	public boolean getArma() {
		return this.arma;
	}

	/**
	 * Get vida do heroi.
	 *
	 * @return vida boleano que indica se o heroi esta vivou ou morto
	 */
	public boolean getVida(){
		return this.vida;
	}

	/**
	 * Get simbolo do heroi.
	 *
	 * @return simbolo char que indica o simbolo atual do heroi
	 */
	public char getSimbolo(){
		return simbolo;
	}
	
	/**
	 * Set da posicao do heroi.
	 *
	 * @param l inteiro que indica a linha onde o heroi vai ficar
	 * @param c inteiro que indica a coluna onde o heroi vai ficar
	 */
	public void setPosicaoHeroi(int l, int c)
	{
		this.x = c;
		this.y = l;
	}
	
	/**
	 * Get da posicao do heroi.
	 *
	 * @return point posicao atual do heroi no labirinto
	 */
	public Point getHeroiPosicao(){
		Point point = new Point(y, x);
		return point;
	}
	
	/**
	 * Ativa a arma do heroi metendo o seu simbolo como A e o paramentro arma a true.
	 */
	public void ativaArma()
	{
		this.simbolo = 'A';
		this.arma = true;
	}
	
	/**
	 * Mata o heroi metendo o parametro vida como false e vitoria false.
	 */
	public void heroiMorre()
	{
		this.vida = false;
		this.vitoria = false;
	}
	
	/**
	 * Vitoria do heroi metendo o parametro vitoria como true.
	 */
	public void heroiGanha()
	{
		this.vitoria = true;
	}
	
	/**
	 * Derrota do heroi metendo os parametros vida e vitoria a false.
	 */
	public void heroiPerde(){
		this.vida = false;
		this.vitoria = false;
	}
	
	/**
	 * Move o heroi para norte diminuindo uma unidade na linha onde o heroi se encontra.
	 */
	public void moveHeroiNorte()
	{
		this.setLinha(y-1);
		this.setColuna(x);
	}
	
	/**
	 * Move o heroi para sul aumentando uma unidade na linha onde o heroi se encontra.
	 */
	public void moveHeroiSul()
	{
		this.setLinha(y+1);
		this.setColuna(x);
	}
	
	/**
	 * Move o heroi para a direita aumentando uma unidade na coluna onde o heroi se encontra.
	 */
	public void moveHeroiDireita()
	{
		this.setLinha(y);
		this.setColuna(x+1);
	}
	
	/**
	 * Move o heroi para a esquerda diminuindo uma unidade na coluna onde o heroi se encontra.
	 */
	public void moveHeroiEsquerda()
	{
		this.setLinha(y);
		this.setColuna(x-1);
	}
	
	
	
}
