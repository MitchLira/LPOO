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
	
	public Dragao(int linha, int coluna)
	{
		this.y = linha;
		this.x = coluna;
		this.vida = true;
		this.adormecido = false;
		this.simbolo = 'D';
	}
	
	public char getSimbolo(){
		return simbolo;
	}
	
	public int getColuna()
	{
		return this.x;
	}
	
	public void setColuna(int coluna)
	{
		this.x = coluna;
	}
	
	public int getLinha()
	{
		return this.y;
	}
	
	public void setLinha(int linha)
	{
		this.y = linha;
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
	
	public void setPosicaoDragao(int l, int c)
	{
		this.y = l;
		this.x = c;
	}
	
	public void mantemPosicaoDragao()
	{
		this.setColuna(x);
		this.setLinha(y);
	}
	
	public void moveDragaoNorte()
	{
		this.setLinha(y-1);
		this.setColuna(x);
		
	}
	
	public void moveDragaoSul()
	{
		this.setLinha(y+1);
		this.setColuna(x);
	}
	
	public void moveDragaoDireita()
	{
		this.setLinha(y);
		this.setColuna(x+1);
	}
	
	public void moveDragaoEsquerda()
	{
		this.setLinha(y);
		this.setColuna(x-1);
	}

	public Point getDragaoPosicao(){
		Point point = new Point(y, x);
		return point;
	}
	
	
	public void setSimbolo(){
		this.simbolo = 'D';
	}
	


}
