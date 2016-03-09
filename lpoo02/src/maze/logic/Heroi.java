package maze.logic;

public class Heroi {
	private boolean arma;
	private boolean vida;
	private int linha;
	private int coluna;
	private boolean vitoria;
	private char simbolo;

	public Heroi()
	{
		this.arma = false;
		this.vida = true;
		this.linha = 1;
		this.coluna = 1;
		this.vitoria = false;
		this.simbolo = 'H';
	}
	
	public Heroi(int coluna, int linha)
	{
		this.arma = false;
		this.vida = true;
		this.linha = linha;
		this.coluna = coluna;
		this.vitoria = false;
		this.simbolo = 'H';
	}
	
	public void setLinha(int linha) {
		this.linha = linha;
	}

	public void setColuna(int coluna) {
		this.coluna = coluna;
	}
	
	public int getLinha() {
		return this.linha;
	}
	
	public boolean getVitoria()
	{
		return this.vitoria;
	}

	public int getColuna(){
		return this.coluna;
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
		this.coluna = c;
		this.linha = l;
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
		this.setLinha(linha-1);
		this.setColuna(coluna);
	}
	
	public void moveHeroiSul()
	{
		this.setLinha(linha+1);
		this.setColuna(coluna);
	}
	
	public void moveHeroiDireita()
	{
		this.setLinha(linha);
		this.setColuna(coluna+1);
	}
	
	public void moveHeroiEsquerda()
	{
		this.setLinha(linha);
		this.setColuna(coluna-1);
	}
	
	public Point getHeroiPosicao(){
		Point point = new Point(coluna, linha);
		return point;
	}
	
	public static void main(String[] args) {
	}

}
