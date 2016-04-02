package maze.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import maze.logic.Labirinto;
import maze.logic.Point;

public class LabirintoBuilder extends LabirintoPanelImagens implements MouseListener{

	private Labirinto lab;
	public int Xinicial = 5;
	private char s;

	private boolean heroiExiste;
	private boolean espadaExiste;
	
	private Point heroiAnt;
	private Point espadaAnt;
	
	private Point PosParede;
	private Point PosEspada;
	private Point PosHeroi;
	private Point PosDragao;
	private Point PosCaminho;
	private int dimBotaoX;
	private int dimBotaoY;
	
	public LabirintoBuilder(){
		super();
		dimBotaoX = 100;
		dimBotaoY = 49;
		
		heroiExiste = false;
		espadaExiste = false;
		s = 'X';
		
		PosParede = new Point(650, 119);
		PosCaminho = new Point(540, 10);
		PosEspada = new Point(650, 10);
		PosHeroi = new Point(540, 65);
		PosDragao = new Point(650, 65);
		
		
		addMouseListener(this);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.white);
		

		if(lab != null){
			int dimensao = lab.getDimensao();
			int Y = this.getHeight()/dimensao;
			int X = this.getWidth()/dimensao;

			for(int i = 0; i < dimensao; i++){
				int Yini = i*Y;
				int Yfim = Y + Yini;

				for(int j = 0; j < dimensao; j++){
					int Xini = j*X ;
					int Xfim = X + Xini;

					if(lab.getSimboloPosicao(i, j) == lab.X)
						g.drawImage(parede, Xini, Yini, Xfim, Yfim, 0, 0, parede.getWidth(), parede.getHeight(), null);

					else if(lab.getSimboloPosicao(i, j) == lab.P)
						g.drawImage(ponto, Xini, Yini, Xfim, Yfim, 0, 0, ponto.getWidth(), ponto.getHeight(), null);

					else if(lab.getSimboloPosicao(i, j) == lab.S)
						g.drawImage(saida, Xini, Yini, Xfim, Yfim, 0, 0, saida.getWidth(), saida.getHeight(), null);

					else if(lab.getSimboloPosicao(i, j) == lab.H)
						g.drawImage(heroi, Xini, Yini, Xfim, Yfim, 0, 0, heroi.getWidth(), heroi.getHeight(), null);

					else if(lab.getSimboloPosicao(i, j) == lab.A)
						g.drawImage(heroi, Xini, Yini, Xfim, Yfim, 0, 0, heroi.getWidth(), heroi.getHeight(), null);

					else if(lab.getSimboloPosicao(i, j) == lab.E)
						g.drawImage(espada, Xini, Yini, Xfim, Yfim, 0, 0, espada.getWidth(), espada.getHeight(), null);

					else if(lab.getSimboloPosicao(i, j) == lab.D)
						g.drawImage(dragao, Xini, Yini, Xfim, Yfim, 0, 0, dragao.getWidth(), dragao.getHeight(), null);

					else if(lab.getSimboloPosicao(i, j) == lab.F)
						g.drawImage(espada, Xini, Yini, Xfim, Yfim, 0, 0, saida.getWidth(), saida.getHeight(), null);

					else if(lab.getSimboloPosicao(i, j) == lab.d)
						g.drawImage(dragao, Xini, Yini, Xfim, Yfim, 0, 0, saida.getWidth(), saida.getHeight(), null);
				}
			}
		}
	}	
	
	public void setLabirinto(Labirinto lab){
		this.lab = lab;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent g) {
		int x = g.getX();
		int y = g.getY();
		
		if(x > Xinicial){
			
		}
		else{
			
		}

		repaint();		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	private boolean between(int x, int xi, int xf) {

		return (x >= xi && x <= xf);
	}
}
