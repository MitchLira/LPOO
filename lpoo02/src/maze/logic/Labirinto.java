package maze.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import maze.cli.Interface;
import maze.gui.Grafics;




enum ModoJogo {
	Basico, Aleatorio, Adormecido
}

public class Labirinto {
	private char[][] labirinto;
	private Heroi heroi;
	private Espada esp;
	private ArrayList<Dragao> dragoes;
	private ModoJogo modoJogo;

	//caracteres
	public static final char P = '.';
	public static final char E = 'E';
	public static final char S = 'S';
	public static final char F = 'F';
	public static final char X = 'X';
	public static final char H = 'H';
	public static final char D = 'D';
	public static final char d = 'd';
	public static final char A = 'A';

	public void criarLabirinto(char[][] labir, Heroi heroi, ArrayList<Dragao> dragoes, Espada espada) {
		labirinto = labir;
		this.heroi = heroi;
		this.dragoes = dragoes;
		this.esp = espada;
	}

	public char[][] criarLabirinto() {

		dragoes = new ArrayList<Dragao>();
		esp = new Espada();

		char[][] lab = { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
				{ 'X', '.', 'X', '.', '.', '.', 'X', 'X', '.', 'X' },
				{ 'X', '.', 'X', '.', 'X', '.', 'X', 'X', '.', 'X' },
				{ 'X', '.', '.', '.', 'X', '.', 'X', 'X', '.', 'X' },
				{ 'X', 'X', 'X', 'X', 'X', '.', '.', '.', '.', 'X' },
				{ 'X', '.', 'X', 'X', 'X', 'X', '.', '.', '.', '.' },
				{ 'X', '.', '.', '.', 'X', 'X', '.', 'X', '.', 'X' },
				{ 'X', '.', 'X', '.', 'X', 'X', '.', 'X', '.', 'X' },
				{ 'X', 'X', 'X', '.', '.', '.', '.', 'X', '.', 'X' },
				{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };

		labirinto = lab;
		labirinto[1][1] = H;
		labirinto[6][1] = E;
		labirinto[5][9] = S;
		labirinto[1][8] = D;

		heroi = new Heroi(1,1);
		Dragao dragao = new Dragao(1,8);
		dragoes.add(dragao);
		esp.setEspadaPosicao(6, 1);
		return lab;
	}
	private void visit(char[][] m, int i, int j, boolean [][] visited) {
		if (i < 0 || i >= m.length || j < 0 || j >= m.length)
			return;
		if (m[i][j] == 'X' || visited[i][j])
			return;
		visited[i][j] = true;
		visit(m, i-1, j, visited);
		visit(m, i+1, j, visited);
		visit(m, i, j-1, visited);
		visit(m, i, j+1, visited);
	}
	
	private boolean checkExitReachable(char [][] maze) {
		Point p = findPos(maze, 'S');
		boolean [][] visited = new boolean[maze.length] [maze.length];
		
		visit(maze, p.y, p.x, visited);
		
		for (int i = 0; i < maze.length; i++)
			for (int j = 0; j < maze.length; j++)
				if (maze[i][j] != 'X' && ! visited[i][j] )
					return false;
		
		return true; 
	}
	public ArrayList<Point> GerarLabirinto(int n) {
		ArrayList<Point> PosLivres = new ArrayList<Point>();
		labirinto = new char[n][n];
		ArrayDeque<Point> PosVisitadas = new ArrayDeque<Point>();

		GeradorLabirintoInical(n, PosVisitadas, PosLivres);

		Random rand = new Random();
		int PosAleatoria = rand.nextInt(n - 2) + 1;
		Point PosAtual = null;

		int posSaida = (rand.nextInt(4));
		if (posSaida == 0) {
			labirinto[0][PosAleatoria] = S;
			PosAtual = new Point(1, PosAleatoria);
		}
		if (posSaida == 1) {
			labirinto[n - 1][PosAleatoria] = S;
			PosAtual = new Point(n - 2, PosAleatoria);
		}
		if (posSaida == 2) {
			labirinto[PosAleatoria][0] = S;
			PosAtual = new Point(PosAleatoria, 1);
		}
		if (posSaida == 3) {
			labirinto[PosAleatoria][n - 1] = S;
			PosAtual = new Point(PosAleatoria, n - 2);
		}
		labirinto[PosAtual.y][PosAtual.x] = P;
		PosVisitadas.addFirst(PosAtual);
		PosLivres.add(new Point(PosAtual.y, PosAtual.x));

		do {
			char vizinhasDisponiveis[] = new char[4];
			int nrvizinhasDisponiveis = 0;

			if (PosAtual.y > 1 && labirinto[PosAtual.y - 1][PosAtual.x] == X
					&& PodeMover(n, 'n', PosAtual))
				vizinhasDisponiveis[nrvizinhasDisponiveis++] = 'n';

			if (PosAtual.x > 1 && labirinto[PosAtual.y][PosAtual.x - 1] == X
					&& PodeMover(n, 'o', PosAtual))
				vizinhasDisponiveis[nrvizinhasDisponiveis++] = 'o';

			if (PosAtual.y < n - 2
					&& labirinto[PosAtual.y + 1][PosAtual.x] == X
					&& PodeMover(n, 's', PosAtual))
				vizinhasDisponiveis[nrvizinhasDisponiveis++] = 's';

			if (PosAtual.x < n - 2
					&& labirinto[PosAtual.y][PosAtual.x + 1] == X
					&& PodeMover(n, 'e', PosAtual))
				vizinhasDisponiveis[nrvizinhasDisponiveis++] = 'e';

			if (nrvizinhasDisponiveis > 0) {

				char coordenada = vizinhasDisponiveis[rand
				                                      .nextInt(nrvizinhasDisponiveis)];

				if (coordenada == 'n') {
					PosAtual.y--;
				} else if (coordenada == 's') {
					PosAtual.y++;
				} else if (coordenada == 'o') {
					PosAtual.x--;
				} else if (coordenada == 'e') {
					PosAtual.x++;
				}

				labirinto[PosAtual.y][PosAtual.x] = P;
				PosVisitadas.addFirst(new Point(PosAtual.y, PosAtual.x));
				PosLivres.add(new Point(PosAtual.y, PosAtual.x));
			} else {
				PosVisitadas.removeFirst();

				if (!PosVisitadas.isEmpty())
					PosAtual = PosVisitadas.getFirst();
			}

		} while (!PosVisitadas.isEmpty());

		if(checkExitReachable(labirinto) != true)
			GerarLabirinto(n);
		
		return PosLivres;
	}

	public void GeradorLabirintoInical(int n, ArrayDeque<Point> flags, ArrayList<Point> posLivres) {
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++) {

				if ((i % 2 != 0) && (j % 2 != 0) && (i < n - 1) && (j < n - 1)) {

					labirinto[i][j] = P;
					flags.addFirst(new Point(i, j));
					posLivres.add(new Point(i, j));
				} else
					labirinto[i][j] = X;
			}
	}

	public boolean PodeMover(int n, char direcao, Point posVizinha) {

		int x = posVizinha.x;
		int y = posVizinha.y;

		if (direcao == 'n') {

			if (x > 1) {
				if (labirinto[y - 1][x - 1] == P && labirinto[y][x - 1] == P)
					return false;
				else if (labirinto[y - 1][x - 1] == X
						&& labirinto[y - 2][x] == X
						&& labirinto[y - 2][x - 1] == P)
					return false;
				else if (labirinto[y - 2][x] == P
						&& labirinto[y - 2][x - 1] == P
						&& labirinto[y - 1][x - 1] == P)
					return false;

			}

			if (x < n - 2) {

				if (labirinto[y][x + 1] == P && labirinto[y - 1][x + 1] == P)
					return false;
				else if (labirinto[y - 1][x + 1] == X
						&& labirinto[y - 2][x] == X
						&& labirinto[y - 2][x + 1] == P)
					return false;
				if (labirinto[y - 2][x] == P && labirinto[y - 2][x + 1] == P
						&& labirinto[y - 1][x + 1] == P)
					return false;

			}
		} else if (direcao == 's') {

			if ((n % 2 == 0) && posVizinha.temCoordImpares()
					&& (y + 1 == n - 2) && labirinto[y - 1][x] == X)
				return false;

			if (x > 1) {
				if (labirinto[y][x - 1] == P && labirinto[y + 1][x - 1] == P)
					return false;
				else if (labirinto[y + 1][x - 1] == X
						&& labirinto[y + 2][x] == X
						&& labirinto[y + 2][x - 1] == P)
					return false;
				else if (labirinto[y + 2][x] == P
						&& labirinto[y + 2][x - 1] == P
						&& labirinto[y + 1][x - 1] == P)
					return false;

			}

			if (x < n - 2) {

				if (labirinto[y][x + 1] == P && labirinto[y + 1][x + 1] == P)
					return false;
				else if (labirinto[y + 1][x + 1] == X
						&& labirinto[y + 2][x] == X
						&& labirinto[y + 2][x + 1] == P)
					return false;
				if (labirinto[y + 2][x] == P && labirinto[y + 2][x + 1] == P
						&& labirinto[y + 1][x + 1] == P)
					return false;

			}

		} else if (direcao == 'o') {
			if (y > 1) {

				if (labirinto[y - 1][x] == P && labirinto[y - 1][x - 1] == P)
					return false;
				else if (labirinto[y - 1][x - 1] == X
						&& labirinto[y][x - 2] == X
						&& labirinto[y - 1][x - 2] == P)
					return false;
				else if (labirinto[y][x - 2] == P
						&& labirinto[y - 1][x - 2] == P
						&& labirinto[y - 1][x - 1] == P)
					return false;

			}

			if (y < n - 2) {

				if (labirinto[y + 1][x] == P && labirinto[y + 1][x - 1] == P)
					return false;
				else if (labirinto[y + 1][x - 1] == X
						&& labirinto[y][x - 2] == X
						&& labirinto[y + 1][x - 2] == P)
					return false;
				else if (labirinto[y][x - 2] == P
						&& labirinto[y + 1][x - 2] == P
						&& labirinto[y + 1][x - 1] == P)
					return false;

			}
		} else if (direcao == 'e') {
			if ((n % 2 == 0) && posVizinha.temCoordImpares()
					&& (x + 1 == n - 2) && labirinto[y][x - 1] == X)
				return false;

			if (y > 1) {

				if (labirinto[y - 1][x] == P && labirinto[y - 1][x + 1] == P)
					return false;
				else if (labirinto[y - 1][x + 1] == X
						&& labirinto[y][x + 2] == X
						&& labirinto[y - 1][x + 2] == P)
					return false;
				else if (labirinto[y][x + 2] == P
						&& labirinto[y - 1][x + 2] == P
						&& labirinto[y - 1][x + 1] == P)
					return false;

			}

			if (y < n - 2) {

				if (labirinto[y + 1][x] == P && labirinto[y + 1][x + 1] == P)
					return false;
				else if (labirinto[y + 1][x + 1] == X
						&& labirinto[y][x + 2] == X
						&& labirinto[y + 1][x + 2] == P)
					return false;
				else if (labirinto[y][x + 2] == P
						&& labirinto[y + 1][x + 2] == P
						&& labirinto[y + 1][x + 1] == P)
					return false;

			}
		}
		return true;
	}

	public Point findPos(char [][] maze, char c) {
		for (int x = 0; x < maze.length; x++)			
			for (int y = 0; y < maze.length; y++)
				if (maze[y][x] == c)
					return new Point(y, x);
		return null;		
	}
	

	public char[][] ColocarCarateres(ArrayList<Point> posLivres, int nrDragoes) {
		Random rand = new Random();
		int nrPosLivres = posLivres.size();
		dragoes = new ArrayList<Dragao>();
		
		int HeroiPos = rand.nextInt(nrPosLivres);
		Point HeroiP = new Point(posLivres.get(HeroiPos).y, posLivres.get(HeroiPos).x);
		heroi = new Heroi(HeroiP.y, HeroiP.x);
		labirinto[HeroiP.y][HeroiP.x] = H;
		nrPosLivres--;
		posLivres.remove(HeroiPos);


		int EspadaPos = rand.nextInt(nrPosLivres);
		Point EspadaP = new Point(posLivres.get(EspadaPos).y, posLivres.get(EspadaPos).x);
		esp = new Espada(EspadaP.y, EspadaP.x);
		labirinto[EspadaP.y][EspadaP.x] = E;
		nrPosLivres--;
		posLivres.remove(EspadaPos);

		
		for (int i = 0; i < nrDragoes; i++) {
			Dragao dragao;
			do {
				
				if (nrPosLivres <= 0)
					return labirinto;
				
				int DragaoPos = rand.nextInt(posLivres.size());
				Point DragaoP = new Point(posLivres.get(DragaoPos).y,
						posLivres.get(DragaoPos).x);
				dragao = new Dragao(DragaoP.y, DragaoP.x);
				nrPosLivres--;
				posLivres.remove(dragao);
			} while (lutaPossivel(dragao));
			
			
			labirinto[dragao.getLinha()][dragao.getColuna()] = D;
			dragoes.add(dragao);
		}
	
		if(checkExitReachable(labirinto) == false)
		{
			GerarLabirinto(labirinto.length);
		}
		if(findPos(labirinto, 'H') == null || findPos(labirinto, 'E') == null || findPos(labirinto,'S') == null)
		{
			ColocarCarateres(posLivres, nrDragoes);
		}
		for(Dragao d: dragoes)
		{
			if(d.getDragaoPosicao().adjacentTo(heroi.getHeroiPosicao()))
				ColocarCarateres(posLivres, nrDragoes);
		}
		return labirinto;
	}

	public char[][] criarLabirinto(char[][] lab) {

		labirinto = lab;
		dragoes = new ArrayList<Dragao>();

		for (int i = 0; i < labirinto.length; i++) {
			for (int j = 0; j < labirinto[i].length; j++) {
				if (labirinto[i][j] == D) {
					Dragao dragao = new Dragao(i,j);
					dragoes.add(dragao);
				} else if (labirinto[i][j] == S) {
					labirinto[i][j] = S;
				} else if (labirinto[i][j] == H) {
					heroi.setPosicaoHeroi(i, j);
				} else if (labirinto[i][j] == E) {
					esp.setEspadaPosicao(i, j);
				}
			}
		}
		return labirinto;
	}

	public void criarLabirintoP(char[][] lab){
		this.labirinto = lab;
	}
	
	public void moverDragao(Dragao dragao, Heroi heroi, int mode) { // 0 - N; 1
		// - S; 2 -
		// E; 3 - O
		Random r = new Random();
 
		if (dragao.getVida() == true) {
			int direcao = r.nextInt(5);		 	// para ter maior probabilidade de virar
			// em certas direcoes
			int adormecer = r.nextInt(4);		// decidir se adormece ou nao || 0, 2,
			// 3 -> Nao || 1 -> Sim

			Point EspPos = esp.getEspPos();
			Point DragPos = dragao.getDragaoPosicao();
			
			if(mode == 3 && adormecer == 1)
			{
				if(dragao.getSimbolo() != F)
				{
					dragao.DragaoDorme();
					labirinto[dragao.getLinha()][dragao.getColuna()] = d;
					dragao.mantemPosicaoDragao();
				}
				else
				{
					dragao.mantemPosicaoDragao();
					labirinto[dragao.getLinha()][dragao.getColuna()] = F;
				}
			}

			switch(direcao)
			{  
			case 0:// NORTE

				if(labirinto[dragao.getLinha()-1][dragao.getColuna()] == X)
				{
					dragao.mantemPosicaoDragao();
					labirinto[dragao.getLinha()][dragao.getColuna()] = dragao.getSimbolo();
				}
				else if(labirinto[dragao.getLinha()-1][dragao.getColuna()]== S)
				{
					dragao.mantemPosicaoDragao();
					labirinto[dragao.getLinha()][dragao.getColuna()] = dragao.getSimbolo();
				}
				else if(labirinto[dragao.getLinha()-1][dragao.getColuna()] == P)
				{
					if(dragao.getSimbolo() == F)
					{
						dragao.setSimbolo();
						labirinto[dragao.getLinha()][dragao.getColuna()] = E;
						labirinto[dragao.getLinha()-1][dragao.getColuna()] = D;
						dragao.moveDragaoNorte();
					}
					else
					{
						dragao.setSimbolo();
						labirinto[dragao.getLinha()][dragao.getColuna()] = P;
						labirinto[dragao.getLinha()-1][dragao.getColuna()] = D;
						dragao.moveDragaoNorte();
					}
				}
				else if(labirinto[dragao.getLinha()-1][dragao.getColuna()] == E)
				{
					dragao.DragEsp();
					labirinto[dragao.getLinha()][dragao.getColuna()] = P;
					labirinto[dragao.getLinha()-1][dragao.getColuna()] = dragao.getSimbolo();
					dragao.moveDragaoNorte();
				}
				break;
			case 1:// SUL

				if(labirinto[dragao.getLinha()+1][dragao.getColuna()] == X)
				{
					dragao.mantemPosicaoDragao();
					labirinto[dragao.getLinha()][dragao.getColuna()] = dragao.getSimbolo();
				}
				else if(labirinto[dragao.getLinha()+1][dragao.getColuna()]== S)
				{
					dragao.mantemPosicaoDragao();
					labirinto[dragao.getLinha()][dragao.getColuna()] = dragao.getSimbolo();
				}
				else if(labirinto[dragao.getLinha()+1][dragao.getColuna()] == P)
				{
					if(dragao.getSimbolo() == F)
					{
						dragao.setSimbolo();
						labirinto[dragao.getLinha()][dragao.getColuna()] = E;
						labirinto[dragao.getLinha()+1][dragao.getColuna()] = D;
						dragao.moveDragaoSul();
					}
					else
					{
						dragao.setSimbolo();
						labirinto[dragao.getLinha()][dragao.getColuna()] = P;
						labirinto[dragao.getLinha()+1][dragao.getColuna()] = D;
						dragao.moveDragaoSul();
					}
				}
				else if(labirinto[dragao.getLinha()+1][dragao.getColuna()] == E)
				{
					dragao.DragEsp();
					labirinto[dragao.getLinha()][dragao.getColuna()] = P;
					labirinto[dragao.getLinha()+1][dragao.getColuna()] = dragao.getSimbolo();
					dragao.moveDragaoSul();
				}
				break;
			case 2:// ESTE

				if(labirinto[dragao.getLinha()][dragao.getColuna()+1] == X)
				{
					dragao.mantemPosicaoDragao();
					labirinto[dragao.getLinha()][dragao.getColuna()] = dragao.getSimbolo();
				}
				else if(labirinto[dragao.getLinha()][dragao.getColuna()+1]== S)
				{
					dragao.mantemPosicaoDragao();
					labirinto[dragao.getLinha()][dragao.getColuna()] = dragao.getSimbolo();
				}
				else if(labirinto[dragao.getLinha()][dragao.getColuna()+1] == P)
				{
					if(dragao.getSimbolo() == F)
					{
						dragao.setSimbolo();
						labirinto[dragao.getLinha()][dragao.getColuna()] = E;
						labirinto[dragao.getLinha()][dragao.getColuna()+1] = D;
						dragao.moveDragaoDireita();
					}
					else
					{
						dragao.setSimbolo();
						labirinto[dragao.getLinha()][dragao.getColuna()] = P;
						labirinto[dragao.getLinha()][dragao.getColuna()+1] = D;
						dragao.moveDragaoDireita();
					}
				}
				else if(labirinto[dragao.getLinha()+1][dragao.getColuna()] == E)
				{
					dragao.DragEsp();
					labirinto[dragao.getLinha()][dragao.getColuna()] = P;
					labirinto[dragao.getLinha()][dragao.getColuna()+1] = dragao.getSimbolo();
					dragao.moveDragaoDireita();
				}
				break;
			case 3:// OESTE

				if(labirinto[dragao.getLinha()][dragao.getColuna()-1] == X)
				{
					dragao.mantemPosicaoDragao();
					labirinto[dragao.getLinha()][dragao.getColuna()] = dragao.getSimbolo();
				}
				else if(labirinto[dragao.getLinha()][dragao.getColuna()-1]== S)
				{
					dragao.mantemPosicaoDragao();
					labirinto[dragao.getLinha()][dragao.getColuna()] = dragao.getSimbolo();
				}
				else if(labirinto[dragao.getLinha()][dragao.getColuna()-1] == P)
				{
					if(dragao.getSimbolo() == F)
					{
						dragao.setSimbolo();
						labirinto[dragao.getLinha()][dragao.getColuna()] = E;
						labirinto[dragao.getLinha()][dragao.getColuna()-1] = D;
						dragao.moveDragaoEsquerda();
					}
					else
					{
						dragao.setSimbolo();
						labirinto[dragao.getLinha()][dragao.getColuna()] = P;
						labirinto[dragao.getLinha()][dragao.getColuna()-1] = D;
						dragao.moveDragaoEsquerda();
					}
				}
				else if(labirinto[dragao.getLinha()-1][dragao.getColuna()] == E)
				{
					dragao.DragEsp();
					labirinto[dragao.getLinha()][dragao.getColuna()] = P;
					labirinto[dragao.getLinha()][dragao.getColuna()-1] = dragao.getSimbolo();
					dragao.moveDragaoEsquerda();
				}
				break;
			}
		}

	}

	public void moverHeroi(Heroi heroi, char coordenada) {
		switch (coordenada) {
		case 'n': {
			if (labirinto[heroi.getLinha() - 1][heroi.getColuna()] == P) {
				labirinto[heroi.getLinha()][heroi.getColuna()] = P;
				labirinto[heroi.getLinha() - 1][heroi.getColuna()] = heroi.getSimbolo();
				heroi.moveHeroiNorte();
			} else if (labirinto[heroi.getLinha() - 1][heroi.getColuna()] == E) {
				labirinto[heroi.getLinha()][heroi.getColuna()] = P;
				heroi.ativaArma();
				labirinto[heroi.getLinha() - 1][heroi.getColuna()] = heroi.getSimbolo();
				heroi.moveHeroiNorte();
			} else if (labirinto[heroi.getLinha() - 1][heroi.getColuna()] == S && this.DragoesTodosMortos()) {
				heroi.heroiGanha();
				labirinto[heroi.getLinha()][heroi.getColuna()] = P;
				labirinto[heroi.getLinha() - 1][heroi.getColuna()] = heroi.getSimbolo();
				heroi.moveHeroiNorte();
			}

			break;
		}
		case 's': {
			if (labirinto[heroi.getLinha() + 1][heroi.getColuna()] == P) {
				labirinto[heroi.getLinha()][heroi.getColuna()] = P;
				labirinto[heroi.getLinha() + 1][heroi.getColuna()] = heroi.getSimbolo();
				heroi.moveHeroiSul();
			} else if (labirinto[heroi.getLinha() + 1][heroi.getColuna()] == E) {
				labirinto[heroi.getLinha()][heroi.getColuna()] = P;
				heroi.ativaArma();
				labirinto[heroi.getLinha() + 1][heroi.getColuna()] = heroi.getSimbolo();
				heroi.moveHeroiSul();
			} else if (labirinto[heroi.getLinha() + 1][heroi.getColuna()] == S && this.DragoesTodosMortos()) {
				heroi.heroiGanha();
				labirinto[heroi.getLinha()][heroi.getColuna()] = P;
				labirinto[heroi.getLinha() + 1][heroi.getColuna()] = heroi.getSimbolo();
				heroi.moveHeroiSul();
			}

			break;
		}
		case 'e': {
			if (labirinto[heroi.getLinha()][heroi.getColuna() + 1] == P) {
				labirinto[heroi.getLinha()][heroi.getColuna()] = P;
				labirinto[heroi.getLinha()][heroi.getColuna() + 1] = heroi.getSimbolo();
				heroi.moveHeroiDireita();
			}

			else if (labirinto[heroi.getLinha()][heroi.getColuna() + 1] == E) {
				labirinto[heroi.getLinha()][heroi.getColuna()] = P;
				heroi.ativaArma();
				labirinto[heroi.getLinha()][heroi.getColuna() + 1] = heroi.getSimbolo();
				heroi.moveHeroiDireita();
			} else if (labirinto[heroi.getLinha()][heroi.getColuna() + 1] == S && this.DragoesTodosMortos()) {
				heroi.heroiGanha();
				labirinto[heroi.getLinha()][heroi.getColuna()] = P;
				labirinto[heroi.getLinha()][heroi.getColuna() + 1] = heroi.getSimbolo();
				heroi.moveHeroiDireita();
			}

			break;
		}
		case 'o': {
			if (labirinto[heroi.getLinha()][heroi.getColuna() - 1] == P) {
				labirinto[heroi.getLinha()][heroi.getColuna()] = P;
				labirinto[heroi.getLinha()][heroi.getColuna() - 1] = heroi.getSimbolo();
				heroi.moveHeroiEsquerda();
			}

			else if (labirinto[heroi.getLinha()][heroi.getColuna() - 1] == E) {
				labirinto[heroi.getLinha()][heroi.getColuna()] = P;
				heroi.ativaArma();
				labirinto[heroi.getLinha()][heroi.getColuna() - 1] = heroi.getSimbolo();
				heroi.moveHeroiEsquerda();
			} else if (labirinto[heroi.getLinha()][heroi.getColuna() - 1] == S && this.DragoesTodosMortos()) {
				heroi.heroiGanha();
				labirinto[heroi.getLinha()][heroi.getColuna()] = P;
				labirinto[heroi.getLinha()][heroi.getColuna() - 1] = heroi.getSimbolo();
				heroi.moveHeroiEsquerda();
			}

			break;
		}
		}
	}

	public boolean lutaPossivel(Dragao dragao) {
		Point heroiPos = heroi.getHeroiPosicao();
		Point DragaoPos = dragao.getDragaoPosicao();

		if (heroiPos.x == DragaoPos.x
				&& Math.abs(heroiPos.y - DragaoPos.y) <= 1)
			return true;
		else if (heroiPos.y == DragaoPos.y
				&& Math.abs(heroiPos.x - DragaoPos.x) <= 1)
			return true;

		return false;
	}

	public void HeroivsDragao(Dragao dragao) {
		if (lutaPossivel(dragao)) {
			if (heroi.getArma()) {
				dragao.DragaoMorre();
				labirinto[dragao.getLinha()][dragao.getColuna()] = P;
				labirinto[heroi.getLinha()][heroi.getColuna()] = heroi.getSimbolo();

			} else if (!dragao.getAdormecido()) {
				labirinto[heroi.getLinha()][heroi.getColuna()] = P;
				heroi.heroiMorre();
				heroi.heroiPerde();
			}
		}
	}

	public boolean DragoesTodosMortos()
	{
		boolean mortos = true;
		for(Dragao dragao: dragoes)
		{
			if(dragao.getVida() == true)
				mortos = false;
		}
		return mortos;
	}

	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < labirinto.length; i++) {
			for (int j = 0; j < labirinto.length; j++) {
				s += labirinto[i][j] + " ";
			}
			s += "\n";
		}
		return s;
	}

	public int modoJogotoInt(String modo)
	{
		switch(modo)
		{
		case "Basico":
			return 1;
		case "Aleatorio":
			return 2;
		case "Adormecido":
			return 3;
		}
		return -1;

	}

	public void jogar(Labirinto lab, Interface interf, int mode) {


		int jogada = 1;
		interf.displayLabirinto(lab);
		while (heroi.getVida() == true) {
			Scanner S = interf.fazJogada(jogada);
			jogada++;

			if (S.hasNext()) {
				char coordenada = S.next().charAt(0);
				lab.moverHeroi(heroi, coordenada);

				for (Dragao dragao: dragoes) {
					lab.HeroivsDragao(dragao);

					if (mode != 1) {
						lab.moverDragao(dragao, heroi, mode);
						lab.HeroivsDragao(dragao);
					}

					if (heroi.getVitoria() == true) {
						interf.venceu();
						// interf.displayLabirinto(labirinto);
						System.exit(0);
					}

					if (heroi.getVida() == false) {
						interf.perdeu();
						// interf.displayLabirinto(labirinto);
						System.exit(1);
					}
				}
				interf.displayLabirinto(lab);

			}
		}
	}

	public Heroi getHeroi(){
		return this.heroi;
	}

	public ArrayList<Dragao> getDragoes(){
		return this.dragoes;
	}

	public ModoJogo getModoJogo() {
		return modoJogo;
	}

	public int getDimensao(){
		return labirinto.length;
	}

	public char getSimboloPosicao(int i, int j){
		return labirinto[i][j];
	}

	public static void main(String[] args) {

		Interface interf = new Interface();
		int mode = interf.nrModo();

		if(mode == 1){
			Grafics gra = new Grafics();
			gra.setVisible(true);
		}
		else{
			//colocar try e catch

			int nivel = interf.modojogo();
			Labirinto lab = new Labirinto();
			lab.criarLabirinto();
			lab.jogar(lab, interf, nivel);
		}

	}
}
