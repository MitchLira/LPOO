package maze.logic;

public class Heroi {
	private boolean arma;
	private boolean vida;
	private int x;
	private int y;
	private boolean vitoria;
	private char simbolo;

	public Heroi()
	{
		this.arma = false;
		this.vida = true;
		this.y = 1;
		this.x = 1;
		this.vitoria = false;
		this.simbolo = 'H';
	}
	
	public Heroi(int linha, int coluna)
	{
		this.arma = false;
		this.vida = true;
		this.y = linha;
		this.x = coluna;
		this.vitoria = false;
		this.simbolo = 'H';
	}
	
	public void setLinha(int linha) {
		this.y = linha;
	}

	public void setColuna(int coluna) {
		this.x = coluna;
	}
	
	public int getLinha() {
		return this.y;
	}
	
	public boolean getVitoria()
	{
		return this.vitoria;
	}

	public int getColuna(){
		return this.x;
	}

	public boolean getArma() {
		return this.arma;
	}

	public boolean getVida(){
		return this.vida;
	}

	public char getSimbolo(){
		return simbolo;
	}
	
	public void setPosicaoHeroi(int l, int c)
	{
		this.x = c;
		this.y = l;
	}
	
	public void ativaArma()
	{
		this.simbolo = 'A';
		this.arma = true;
	}
	
	public void heroiMorre()
	{
		this.vida = false;
		this.vitoria = false;
	}
	
	public void heroiGanha()
	{
		this.vitoria = true;
	}
	
	public void heroiPerde(){
		this.vida = false;
		this.vitoria = false;
	}
	
	public void moveHeroiNorte()
	{
		this.setLinha(y-1);
		this.setColuna(x);
	}
	
	public void moveHeroiSul()
	{
		this.setLinha(y+1);
		this.setColuna(x);
	}
	
	public void moveHeroiDireita()
	{
		this.setLinha(y);
		this.setColuna(x+1);
	}
	
	public void moveHeroiEsquerda()
	{
		this.setLinha(y);
		this.setColuna(x-1);
	}
	
	public Point getHeroiPosicao(){
		Point point = new Point(y, x);
		return point;
	}
	
}
