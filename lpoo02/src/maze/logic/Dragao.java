package maze.logic;

public class Dragao {
	private int linha;
	private int coluna;
	private boolean vida;
	private boolean adormecido;
	
	public Dragao()
	{
		this.linha = 3;
		this.coluna = 1;
		this.vida = true;
		this.adormecido = false;
	}
	
	public int getColuna()
	{
		return this.coluna;
	}
	public void setColuna(int coluna)
	{
		this.coluna = coluna;
	}
	public int getLinha()
	{
		return this.linha;
	}
	public void setLinha(int linha)
	{
		this.linha = linha;
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
		this.adormecido = true;
	}
	public void DragaoAcorda()
	{
		this.adormecido = false;
	}
	public void mudaPosicaoDragao(int l, int c)
	{
		this.coluna = c;
		this.linha = l;
	}
	public void mantemPosicaoDragao()
	{
		this.setLinha(linha);
		this.setColuna(coluna);
	}
	public void moveDragaoNorte()
	{
		this.setLinha(linha-1);
		this.setColuna(coluna);
	}
	public void moveDragaoSul()
	{
		this.setLinha(linha+1);
		this.setColuna(coluna);
	}
	public void moveDragaoDireita()
	{
		this.setLinha(linha);
		this.setColuna(coluna+1);
	}
	public void moveDragaoEsquerda()
	{
		this.setLinha(linha);
		this.setColuna(coluna-1);
	}
	public static void main(String[] args) {

	}

}
