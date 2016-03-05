package maze.logic;

public class Heroi {
	private boolean arma;
	private boolean vida;
	private int linha;
	private int coluna;
	private boolean vitoria;

	public Heroi()
	{
		this.arma = false;
		this.vida = true;
		this.linha = 1;
		this.coluna = 1;
		this.vitoria = false;
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

	public void mudaPosicaoHeroi(int l, int c)
	{
		this.coluna = c;
		this.linha = l;
	}
	
	public void ativaArma()
	{
		this.arma = true;
	}
	
	public void heroiMorre()
	{
		this.vida = false;
	}
	
	public void heroiGanha()
	{
		this.vitoria = true;
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
		return (new Point(linha, coluna));
	}
	
	public static void main(String[] args) {
	}

}
