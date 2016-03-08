package maze.logic;

public class Dragao {
	private int x;
	private int y;
	private boolean vida;
	private boolean adormecido;
	private char simbolo;
	
	public Dragao()
	{
		this.x = 3;
		this.y = 1;
		this.vida = true;
		this.adormecido = false;
		this.simbolo = 'D';
	}
	
	public Dragao(int coluna, int linha)
	{
		this.x = linha;
		this.y = coluna;
		this.vida = true;
		this.adormecido = false;
		this.simbolo = 'D';
	}
	
	public char getSimbolo(){
		return simbolo;
	}
	
	public int getColuna()
	{
		return this.y;
	}
	
	public void setColuna(int coluna)
	{
		this.y = coluna;
	}
	
	public int getLinha()
	{
		return this.x;
	}
	
	public void setLinha(int linha)
	{
		this.x = linha;
	}
	
	public boolean  getVida()
	{
		return this.vida;
	}
	
	public boolean getAdormecido()
	{
		return this.adormecido;
	}
	
	public void DragaoMorre()
	{
		this.vida = false;
	}
	
	public void DragaoDorme()
	{
		this.simbolo = 'd';
		this.adormecido = true;
	}
	
	public void DragaoAcorda()
	{
		this.simbolo = 'D';
		this.adormecido = false;
	}
	
	public void DragEsp(){
		this.simbolo = 'F';
	}
	
	public void mudaPosicaoDragao(int l, int c)
	{
		this.y = c;
		this.x = l;
	}
	
	public void mantemPosicaoDragao()
	{
		this.setLinha(x);
		this.setColuna(y);
	}
	
	public void moveDragaoNorte()
	{
		this.setLinha(x-1);
		this.setColuna(y);
	}
	
	public void moveDragaoSul()
	{
		this.setLinha(x+1);
		this.setColuna(y);
	}
	
	public void moveDragaoDireita()
	{
		this.setLinha(x);
		this.setColuna(y+1);
	}
	
	public void moveDragaoEsquerda()
	{
		this.setLinha(x);
		this.setColuna(y-1);
	}

	public Point getDragaoPosicao(){
		Point point = new Point(y, x);
		return point;
	}
	
	
	public void setSimbolo(){
		this.simbolo = 'D';
	}
	
	public static void main(String[] args) {

	}

}
