/*
 * 
 */
package maze.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import maze.logic.Labirinto;
import maze.logic.Point;

// TODO: Auto-generated Javadoc
/**
 * The Class LabirintoBuilder.
 */
public class LabirintoBuilder extends LabirintoPanelImagens implements MouseListener{

	/** The lab. */
	private Labirinto lab;
	
	/** The labirinto. */
	private char labirinto[][];
	
	/** The Xinicial. */
	public int Xinicial = 5;
	
	/** The s. */
	private char s;

	/** The heroi existe. */
	private boolean heroiExiste;
	
	/** The espada existe. */
	private boolean espadaExiste;

	/** The heroi ant. */
	private Point heroiAnt;
	
	/** The espada ant. */
	private Point espadaAnt;

	/** The Pos parede. */
	private Point PosParede;
	
	/** The Pos espada. */
	private Point PosEspada;
	
	/** The Pos heroi. */
	private Point PosHeroi;
	
	/** The Pos dragao. */
	private Point PosDragao;
	
	/** The Pos caminho. */
	private Point PosCaminho;
	
	/** The dim botao x. */
	private int dimBotaoX;
	
	/** The dim botao y. */
	private int dimBotaoY;
	
	/** The Y bloco. */
	private int YBloco;
	
	/** The X bloco. */
	private int XBloco;

	/**
	 * Instantiates a new labirinto builder.
	 */
	public LabirintoBuilder(){
		super();
		lab = new Labirinto();
		
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

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); 
		lab.criarLabirintoP(labirinto);

		g.setColor(Color.white);


		if(lab != null){
			int dimensao = lab.getDimensao();
			YBloco = this.getHeight()/dimensao;
			XBloco = this.getWidth()/dimensao;

			for(int i = 0; i < dimensao; i++){
				int Yini = i*YBloco;
				int Yfim = YBloco + Yini;

				for(int j = 0; j < dimensao; j++){
					int Xini = j*XBloco ;
					int Xfim = XBloco + Xini;

					if(lab.getSimboloPosicao(i, j) == lab.X)
						g.drawImage(parede, Xini, Yini, Xfim, Yfim, 0, 0, parede.getWidth(), parede.getHeight(), null);

					else if(lab.getSimboloPosicao(i, j) == lab.P)
						g.drawImage(ponto, Xini, Yini, Xfim, Yfim, 0, 0, ponto.getWidth(), ponto.getHeight(), null);

					else if(lab.getSimboloPosicao(i, j) == lab.S)
						g.drawImage(saida, Xini, Yini, Xfim, Yfim, 0, 0, saida.getWidth(), saida.getHeight(), null);

					else if(lab.getSimboloPosicao(i, j) == lab.H)
						g.drawImage(heroiFrente, Xini, Yini, Xfim, Yfim, 0, 0, heroiFrente.getWidth(), heroiFrente.getHeight(), null);

					else if(lab.getSimboloPosicao(i, j) == lab.A)
						g.drawImage(heroiArmado, Xini, Yini, Xfim, Yfim, 0, 0, heroiArmado.getWidth(), heroiArmado.getHeight(), null);

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

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent g) {
		int X = g.getX();
		int Y = g.getY();

		if(X >= 5 && X <= getWidth()){
			int y = Y / YBloco;
			int x = (X - Xinicial) / XBloco;	
			switch (s) {

			case '.':
				if(labirinto[y][x] == '.')
					break;
				else if(labirinto[y][x] == 'H'){
					labirinto[y][x] = '.';
					heroiExiste = false;
					heroiAnt = null;
				}
				else if(labirinto[y][x] == 'E'){
					labirinto[y][x] = '.';
					espadaExiste = false;
					espadaAnt = null;
				}
				else
					labirinto[y][x] = '.';
				break;

			case 'H':
				if(labirinto[y][x] == 'H'){
					labirinto[y][x] = '.';
					heroiExiste = false;
					heroiAnt = null;
				}
				else if(labirinto[y][x] == 'E'){
					labirinto[y][x] = 'H';
					espadaExiste = false;
					espadaAnt = null;
					heroiExiste = true;
					heroiAnt = new Point(y,x);
				}
				else{
					labirinto[y][x] = 'H';
					heroiExiste = true;
					heroiAnt = new Point(y,x);
				}
				break;

			case 'D':
				if(labirinto[y][x] == 'D'){
					labirinto[y][x] = '.';
				}
				else if(labirinto[y][x] == 'H'){
					labirinto[y][x] = 'D';
					heroiExiste = false;
					heroiAnt = null;
				}
				else if(labirinto[y][x] == 'E'){
					labirinto[y][x] = 'D';
					espadaExiste = false;
					espadaAnt = null;
				}
				else{
					labirinto[y][x] = 'D';
				}
				break;

			case 'E':
				if(labirinto[y][x] == 'E'){
					labirinto[y][x] = '.';
					espadaExiste = false;
					espadaAnt = null;
				}
				else if(labirinto[y][x] == 'H'){
					labirinto[y][x] = 'E';
					espadaExiste = true;
					espadaAnt = new Point(y,x);
					heroiExiste = false;
					heroiAnt = null;
				}
				else{
					labirinto[y][x] = 'E';
					espadaExiste = true;
					espadaAnt = new Point(y,x);
				}
				break;
			case 'X':
				if(labirinto[y][x] == 'E'){
					labirinto[y][x] = 'X';
					espadaExiste = false;
					espadaAnt = null;
				}
				else if(labirinto[y][x] == 'H'){
					labirinto[y][x] = 'X';
					heroiExiste = false;
					heroiAnt = null;
				}
				else{
					labirinto[y][x] = 'X';
				}
				break;
			}
			
			repaint();
		}
		else{
			if(between(X, PosCaminho.x, PosCaminho.x+dimBotaoX) && between(Y, PosCaminho.y, PosCaminho.y+dimBotaoY))
				s = '.';
			else if(between(X, PosHeroi.x, PosHeroi.x+dimBotaoX) && between(Y, PosHeroi.y, PosHeroi.y+dimBotaoY))
				s = 'H';
			else if(between(X, PosDragao.x, PosDragao.x+dimBotaoX) && between(Y, PosDragao.y, PosDragao.y+dimBotaoY))
				s = 'D';
			else if(between(X, PosEspada.x, PosEspada.x+dimBotaoX) && between(Y, PosEspada.y, PosEspada.y+dimBotaoY))
				s = 'E';
			else if(between(X, PosParede.x, PosParede.x+dimBotaoX) && between(Y, PosParede.y, PosParede.y+dimBotaoY))
				s = 'X';
		}

		
		repaint();
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * Between.
	 *
	 * @param x the x
	 * @param xi the xi
	 * @param xf the xf
	 * @return true, if successful
	 */
	private boolean between(int x, int xi, int xf) {

		return (x >= xi && x <= xf);
	}
	
	/**
	 * Sets the labirinto.
	 *
	 * @param lab the new labirinto
	 */
	public void setLabirinto(char[][] lab){
		this.labirinto = lab;
	}
	
	public void setSimbolo(char p){
		s = p;
	}
}
