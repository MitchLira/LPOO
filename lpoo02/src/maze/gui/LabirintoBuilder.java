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
	private char labirinto[][];
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
	
	private int YBloco;
	private int XBloco;

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
		int X = g.getX();
		int Y = g.getY();

		if(X > Xinicial){
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

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	private boolean between(int x, int xi, int xf) {

		return (x >= xi && x <= xf);
	}
	
	public void setLabirinto(char[][] lab){
		this.labirinto = lab;
	}
}
