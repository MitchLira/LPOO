package maze.gui;

import java.awt.Graphics;
import java.io.IOException;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import maze.logic.Labirinto;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class LabirintoPanel extends LabirintoPanelImagens implements KeyListener{

	private Labirinto lab;

	public LabirintoPanel(){
		super();



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
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}



}
