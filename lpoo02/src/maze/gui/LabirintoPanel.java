/*
 * 
 */
package maze.gui;

import java.awt.Graphics;
import java.io.IOException;
import java.awt.Color;

import javax.swing.JPanel;

import maze.logic.Labirinto;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

// TODO: Auto-generated Javadoc
/**
 * The Class LabirintoPanel.
 */
public class LabirintoPanel extends LabirintoPanelImagens implements KeyListener{

	/** The lab. */
	private Labirinto lab;
	
	/** The labirinto. */
	private char labirinto[][];
	private char coordH;
	private char coordD;

	/**
	 * Instantiates a new labirinto panel.
	 */
	public LabirintoPanel(){
		super();
		coordH = 's';
		coordD = 's';
	}
	
	

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
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
						g.drawImage(espada, Xini, Yini, Xfim, Yfim, 0, 0, saida.getWidth(), saida.getHeight(), null);
						
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

	/**
	 * Sets the labirinto.
	 *
	 * @param lab the new labirinto
	 */
	public void setLabirinto(Labirinto lab){
		this.lab = lab;
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
	
	/**
	 * Sets the labirinto.
	 *
	 * @param lab the new labirinto
	 */
	public void setLabirinto(char[][] lab){
		this.labirinto = lab;
		this.lab.criarLabirintoP(labirinto);
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
