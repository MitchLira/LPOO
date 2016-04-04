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
import java.util.ArrayList;

import maze.logic.Dragao;
import maze.logic.Espada;
import maze.logic.Heroi;
import maze.logic.Labirinto;
import maze.logic.Point;

// TODO: Auto-generated Javadoc
/**
 * The Class LabirintoBuilder.
 */
public class LabirintoBuilder extends LabirintoPanelImagens implements MouseListener{

	/** The lab. */
	private Labirinto lab;
	
	/** The h. */
	private Heroi h;
	
	/** The e. */
	private Espada e;
	
	/** The d. */
	private ArrayList<Dragao> d;

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
	
	/** The saida existe. */
	private boolean saidaExiste;

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

	private char coordH;
	
	/** The coord d. */
	private char coordD;
	private char coordS;
	private int construir;

	/**
	 * Instantiates a new labirinto builder.
	 */
	public LabirintoBuilder(){
		super();
		lab = new Labirinto();
		h = new Heroi();
		d = new ArrayList<Dragao>();
		e = new Espada();
		heroiAnt = null;
		espadaAnt = null;
		construir = -1;

		coordH = 's';
		coordD = 's';

		dimBotaoX = 100;
		dimBotaoY = 49;

		heroiExiste = false;
		espadaExiste = false;
		saidaExiste = false;
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

		g.setColor(Color.white);

		if(heroiExiste==false || espadaExiste==false || saidaExiste==false || d.size()==0)
			construir = -1;
		else
			construir = 0;
		
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
					
					else if(lab.getSimboloPosicao(i, j) == lab.S){						
						g.drawImage(ponto, Xini, Yini, Xfim, Yfim, 0, 0, ponto.getWidth(), ponto.getHeight(), null);
						
						switch(coordS){
						case 'n':
							g.drawImage(saidaNorte, Xini, Yini, Xfim, Yfim, 0, 0, saidaNorte.getWidth(), saidaNorte.getHeight(), null);
							break;
						case 's':
							g.drawImage(saidaSul, Xini, Yini, Xfim, Yfim, 0, 0, saidaSul.getWidth(), saidaSul.getHeight(), null);
							break;
						case 'e':
							g.drawImage(saidaEste, Xini, Yini, Xfim, Yfim, 0, 0, saidaEste.getWidth(), saidaEste.getHeight(), null);
							break;
						case 'o':
							g.drawImage(saidaOeste, Xini, Yini, Xfim, Yfim, 0, 0, saidaOeste.getWidth(), saidaOeste.getHeight(), null);
							break;
						}						
					}

					else if(lab.getSimboloPosicao(i, j) == lab.H){
						g.drawImage(ponto, Xini, Yini, Xfim, Yfim, 0, 0, ponto.getWidth(), ponto.getHeight(), null);

						switch(coordH){
						case 'n':
							g.drawImage(heroiNorte, Xini, Yini, Xfim, Yfim, 0, 0, heroiNorte.getWidth(), heroiNorte.getHeight(), null);
							break;
						case 's':
							g.drawImage(heroiSul, Xini, Yini, Xfim, Yfim, 0, 0, heroiSul.getWidth(), heroiSul.getHeight(), null);
							break;
						case 'e':
							g.drawImage(heroiEste, Xini, Yini, Xfim, Yfim, 0, 0, heroiEste.getWidth(), heroiEste.getHeight(), null);
							break;
						case 'o':
							g.drawImage(heroiOeste, Xini, Yini, Xfim, Yfim, 0, 0, heroiOeste.getWidth(), heroiOeste.getHeight(), null);
							break;
						}
					}

					else if(lab.getSimboloPosicao(i, j) == lab.A){
						g.drawImage(ponto, Xini, Yini, Xfim, Yfim, 0, 0, ponto.getWidth(), ponto.getHeight(), null);

						switch(coordH){
						case 'n':
							g.drawImage(heroiArmNorte, Xini, Yini, Xfim, Yfim, 0, 0, heroiArmNorte.getWidth(), heroiArmNorte.getHeight(), null);
							break;
						case 's':
							g.drawImage(heroiArmSul, Xini, Yini, Xfim, Yfim, 0, 0, heroiArmSul.getWidth(), heroiArmSul.getHeight(), null);
							break;
						case 'e':
							g.drawImage(heroiArmEste, Xini, Yini, Xfim, Yfim, 0, 0, heroiArmEste.getWidth(), heroiArmEste.getHeight(), null);
							break;
						case 'o':
							g.drawImage(heroiArmOeste, Xini, Yini, Xfim, Yfim, 0, 0, heroiArmOeste.getWidth(), heroiArmOeste.getHeight(), null);
							break;
						}
					}

					else if(lab.getSimboloPosicao(i, j) == lab.E){
						g.drawImage(ponto, Xini, Yini, Xfim, Yfim, 0, 0, ponto.getWidth(), ponto.getHeight(), null);
						g.drawImage(espada, Xini, Yini, Xfim, Yfim, 0, 0, espada.getWidth(), espada.getHeight(), null);
					}

					else if(lab.getSimboloPosicao(i, j) == lab.D){
						g.drawImage(ponto, Xini, Yini, Xfim, Yfim, 0, 0, ponto.getWidth(), ponto.getHeight(), null);

						switch(coordD){
						case 'n':
							g.drawImage(dragaoNorte, Xini, Yini, Xfim, Yfim, 0, 0, dragaoNorte.getWidth(), dragaoNorte.getHeight(), null);
							break;
						case 's':
							g.drawImage(dragaoSul, Xini, Yini, Xfim, Yfim, 0, 0, dragaoSul.getWidth(), dragaoSul.getHeight(), null);
							break;
						case 'e':
							g.drawImage(dragaoEste, Xini, Yini, Xfim, Yfim, 0, 0, dragaoEste.getWidth(), dragaoEste.getHeight(), null);
							break;
						case 'o':
							g.drawImage(dragaoOeste, Xini, Yini, Xfim, Yfim, 0, 0, dragaoOeste.getWidth(), dragaoOeste.getHeight(), null);
							break;
						}
					}

					else if(lab.getSimboloPosicao(i, j) == lab.F){
						g.drawImage(ponto, Xini, Yini, Xfim, Yfim, 0, 0, ponto.getWidth(), ponto.getHeight(), null);
						g.drawImage(espada, Xini, Yini, Xfim, Yfim, 0, 0, espada.getWidth(), espada.getHeight(), null);

						switch(coordD){
						case 'n':
							g.drawImage(dragaoNorte, Xini, Yini, Xfim, Yfim, 0, 0, dragaoNorte.getWidth(), dragaoNorte.getHeight(), null);
							break;
						case 's':
							g.drawImage(dragaoSul, Xini, Yini, Xfim, Yfim, 0, 0, dragaoSul.getWidth(), dragaoSul.getHeight(), null);
							break;
						case 'e':
							g.drawImage(dragaoEste, Xini, Yini, Xfim, Yfim, 0, 0, dragaoEste.getWidth(), dragaoEste.getHeight(), null);
							break;
						case 'o':
							g.drawImage(dragaoOeste, Xini, Yini, Xfim, Yfim, 0, 0, dragaoOeste.getWidth(), dragaoOeste.getHeight(), null);
							break;
						}
					}

					else if(lab.getSimboloPosicao(i, j) == lab.d){
						g.drawImage(ponto, Xini, Yini, Xfim, Yfim, 0, 0, ponto.getWidth(), ponto.getHeight(), null);
						g.drawImage(dragaoAdorm, Xini, Yini, Xfim, Yfim, 0, 0, dragaoAdorm.getWidth(), dragaoAdorm.getHeight(), null);
					}
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
				if(x > 0 && x < labirinto.length-1 && y >0 && y < labirinto.length-1){
					if(labirinto[y][x] == '.')
						break;
					else if(labirinto[y][x] == 'H'){
						labirinto[y][x] = '.';
						heroiExiste = false;
						heroiAnt = null;
						h = null;
					}
					else if(labirinto[y][x] == 'E'){
						labirinto[y][x] = '.';
						espadaExiste = false;
						espadaAnt = null;
						e = null;
					}
					else if(labirinto[y][x] == 'D'){
						labirinto[y][x] = '.';
						int i = procurarDragao(y,x);

						if(i > -1)
							d.remove(i);
					}
					else
						labirinto[y][x] = '.';
				}
				break;

			case 'H':
				if(x > 0 && x < labirinto.length-1 && y >0 && y < labirinto.length-1){
					if(labirinto[y][x] == 'H'){
						labirinto[y][x] = '.';
						heroiExiste = false;
						heroiAnt = null;
						h = null;
					}
					else if(labirinto[y][x] == 'E' && !heroiExiste){
						labirinto[y][x] = 'H';
						espadaExiste = false;
						espadaAnt = null;
						e = null;
						heroiExiste = true;
						heroiAnt = new Point(y,x);
						h = new Heroi(y,x);
					}
					else if(labirinto[y][x] == 'D'){
						labirinto[y][x] = 'D';
						int i = procurarDragao(y,x);

						if(i > -1)
							d.remove(i);

						heroiExiste = true;
						heroiAnt = new Point(y,x);
						h = new Heroi(y,x);
					}
					else if(!heroiExiste){
						labirinto[y][x] = 'H';
						heroiExiste = true;
						heroiAnt = new Point(y,x);
						h = new Heroi(y,x);
					}
				}
				break;

			case 'D':
				if(x > 0 && x < labirinto.length-1 && y >0 && y < labirinto.length-1){
					if(labirinto[y][x] == 'D'){
						labirinto[y][x] = '.';
						int i = procurarDragao(y,x);

						if(i > -1)
							d.remove(i);
					}
					else if(labirinto[y][x] == 'H'){
						labirinto[y][x] = 'D';
						heroiExiste = false;
						heroiAnt = null;
						h = null;
						d.add(new Dragao(y,x));
					}
					else if(labirinto[y][x] == 'E'){
						labirinto[y][x] = 'D';
						espadaExiste = false;
						espadaAnt = null;
						e = null;
						d.add(new Dragao(y,x));
					}
					else if(labirinto[y][x] == 'X'){
						labirinto[y][x] = 'D';
						d.add(new Dragao(y,x));
					}
					else{
						labirinto[y][x] = 'D';
						d.add(new Dragao(y,x));
					}
				}
				break;

			case 'E':
				if(x > 0 && x < labirinto.length-1 && y >0 && y < labirinto.length-1){
					if(labirinto[y][x] == 'X' && !saidaExiste){
						labirinto[y][x] = 'E';
						espadaExiste = true;
						espadaAnt = new Point(y,x);
						e = new Espada(y,x);
					}
					else if(labirinto[y][x] == 'E'){
						labirinto[y][x] = '.';
						espadaExiste = false;
						espadaAnt = null;
						e = null;
					}
					else if(labirinto[y][x] == 'H' && !espadaExiste){
						labirinto[y][x] = 'E';
						espadaExiste = true;
						espadaAnt = new Point(y,x);
						e = new Espada(y,x);
						heroiExiste = false;
						heroiAnt = null;
						h = null;
					}
					else if(labirinto[y][x] == 'D' && !saidaExiste){
						labirinto[y][x] = 'D';
						int i = procurarDragao(y,x);

						if(i > -1)
							d.remove(i);

						espadaExiste = true;
						espadaAnt = new Point(y,x);
						e = new Espada(y,x);
					}
					else if(!espadaExiste){
						labirinto[y][x] = 'E';
						espadaExiste = true;
						espadaAnt = new Point(y,x);
						e = new Espada(y,x);
					}
				}
				break;
			case 'X':
				if(x > 0 && x < labirinto.length-1 && y >0 && y < labirinto.length-1){
					if(labirinto[y][x] == 'E'){
						labirinto[y][x] = 'X';
						espadaExiste = false;
						espadaAnt = null;
						e = null;
					}
					else if(labirinto[y][x] == 'H'){
						labirinto[y][x] = 'X';
						heroiExiste = false;
						heroiAnt = null;
						h = null;
					}
					else if(labirinto[y][x] == 'D'){
						labirinto[y][x] = 'X';
						int i = procurarDragao(y,x);

						if(i > -1)
							d.remove(i);
					}
					else if(labirinto[y][x] == 'X'){
						labirinto[y][x] = '.';
					}
					else 
						labirinto[y][x] = 'X';
				}
				break;
			case 'S':
				if(x == 0 && y != 0 && y != labirinto.length-1){
					if(labirinto[y][x] == 'S'){
						labirinto[y][x] = 'X';
						saidaExiste = false;
					}else if(!saidaExiste){
						labirinto[y][x] = 'S';
						saidaExiste = true;
						setCoordS(y, x);
					}
				}
				else if(x == labirinto.length-1 && y != 0 && y != labirinto.length-1){
					if(labirinto[y][x] == 'S'){
						labirinto[y][x] = 'X';
						saidaExiste = false;
					}else if(!saidaExiste){
						labirinto[y][x] = 'S';
						saidaExiste = true;
						setCoordS(y, x);
					}
				}
				else if(y == 0 && x != 0 && x != labirinto.length-1){
					if(labirinto[y][x] == 'S'){
						labirinto[y][x] = 'X';
						saidaExiste = false;
					}else if(!saidaExiste){
						labirinto[y][x] = 'S';
						saidaExiste = true;
						setCoordS(y, x);
					}
				}
				else if(y == labirinto.length-1 && x != 0 && x != labirinto.length-1){
					if(labirinto[y][x] == 'S'){
						labirinto[y][x] = 'X';
						saidaExiste = false;
					}else if(!saidaExiste){
						labirinto[y][x] = 'S';
						saidaExiste = true;
						setCoordS(y, x);
					}
				}
				break;
			}

			repaint();
		}
		else{
			if(entre(X, PosCaminho.x, PosCaminho.x+dimBotaoX) && entre(Y, PosCaminho.y, PosCaminho.y+dimBotaoY))
				s = '.';
			else if(entre(X, PosHeroi.x, PosHeroi.x+dimBotaoX) && entre(Y, PosHeroi.y, PosHeroi.y+dimBotaoY))
				s = 'H';
			else if(entre(X, PosDragao.x, PosDragao.x+dimBotaoX) && entre(Y, PosDragao.y, PosDragao.y+dimBotaoY))
				s = 'D';
			else if(entre(X, PosEspada.x, PosEspada.x+dimBotaoX) && entre(Y, PosEspada.y, PosEspada.y+dimBotaoY))
				s = 'E';
			else if(entre(X, PosParede.x, PosParede.x+dimBotaoX) && entre(Y, PosParede.y, PosParede.y+dimBotaoY))
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
	private boolean entre(int x, int xi, int xf) {

		return (x >= xi && x <= xf);
	}

	/**
	 * Sets the labirinto.
	 *
	 * @param lab the new labirinto
	 */
	public void setLabirinto(char[][] lab){
		this.labirinto = lab;
		this.lab.criarLabirinto(labirinto, new Heroi(0,0), new ArrayList<Dragao>(), new Espada(0,0));
	}

	/**
	 * Sets the simbolo.
	 *
	 * @param p the new simbolo
	 */
	public void setSimbolo(char p){
		s = p;
	}

	/**
	 * Procurar dragao.
	 *
	 * @param y the y
	 * @param x the x
	 * @return the int
	 */
	public int procurarDragao(int y, int x){
		for(int i = 0; i<d.size(); i++){
			if(x == d.get(i).getColuna() && y == d.get(i).getLinha())
				return i;
		}

		return -1;
	}

	public char[][] getLabirinto(){
		return labirinto;
	}

	public Espada getEspada(){
		return e;
	}

	public Heroi getHeroi(){
		return h;
	}

	public ArrayList<Dragao> getDragoes(){
		return d;
	}

	public void setCoordH(char c){
		coordH = c;
	}


	public void setCoordD(int c){
		if(c == 0)
			coordD = 'n';
		else if(c == 1)
			coordD = 's';
		else if(c == 2)
			coordD = 'e';
		else if(c == 3)
			coordD = 'o';
	}

	public void setBoolean(){
		heroiExiste = false;
		espadaExiste = false;
		saidaExiste = false;
		d = new ArrayList<Dragao>();

		coordH = 's';
		coordD = 's';
	}

	public int getConstruir(){
		return construir;
	}
	
	public void setCoordS(int y, int x){
		int dimens = lab.getDimensao();
		if(x == 0){
			if(y > 0 && y < dimens){
				coordS = 'o';
			}
		}
		else if(x == dimens-1){
			if(y > 0 && y < dimens){
				coordS = 'e';
			}
		}
		else if(y == 0){
			if(x > 0 && x < dimens){
				coordS = 'n';
			}
		}
		else if(y == dimens-1){
			if(x > 0 && x < dimens){
				coordS = 's';
			}
		}
	}
}
