package lpoo02;

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
	
	public int getLinha()
	{
		return this.linha;
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
	public static void main(String[] args) {

	}

}
