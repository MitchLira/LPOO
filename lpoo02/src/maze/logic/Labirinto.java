/**  
* Labirinto.java - classe labirinto
* @author  Miguel Lira e Miriam Goncalves 
*/
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




// TODO: Auto-generated Javadoc
enum ModoJogo {
	Basico, Aleatorio, Adormecido
}

/**
 * The Class Labirinto.
 */
public class Labirinto {
	
	/** The labirinto. */
	private char[][] labirinto;
	
	/** The heroi. */
	private Heroi heroi;
	
	/** The esp. */
	private Espada esp;
	
	/** The dragoes. */
	private ArrayList<Dragao> dragoes;
	
	/** The modo jogo. */
	private ModoJogo modoJogo;

	/** The Constant P. */
	//caracteres
	public static final char P = '.';
	
	/** The Constant E. */
	public static final char E = 'E';
	
	/** The Constant S. */
	public static final char S = 'S';
	
	/** The Constant F. */
	public static final char F = 'F';
	
	/** The Constant X. */
	public static final char X = 'X';
	
	/** The Constant H. */
	public static final char H = 'H';
	
	/** The Constant D. */
	public static final char D = 'D';
	
	/** The Constant d. */
	public static final char d = 'd';
	
	/** The Constant A. */
	public static final char A = 'A';

	/**
	 * Construtor do labirinto recebendo todos os parametros do labirinto.
	 *
	 * @param labir labirinto
	 * @param heroi heroi do labirinto
	 * @param dragoes ArrayList de dragoes do labirinto
	 * @param espada espada do labirinto
	 */
	public void criarLabirinto(char[][] labir, Heroi heroi, ArrayList<Dragao> dragoes, Espada espada) {
		labirinto = labir;
		this.heroi = heroi;
		this.dragoes = dragoes;
		this.esp = espada;
	}

	/**
	 * Criador Labirinto recebendo uma labirinto já pre definido.
	 *
	 * @param lab labirinto
	 */
	public void criarLabirintoP(char[][] lab){
		this.labirinto = lab;
	}
	
	/**
	 * Criador de labirinto que recebe labirinto e desenha este.
	 *
	 * @param lab labirinto
	 * @return labirinto criado
	 */
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
	
	/**
	 * Criador do Labirinto por default.
	 *
	 * @return labirinto default
	 */
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
		labirinto[3][8] = E;
		labirinto[5][9] = S;
		labirinto[1][8] = D;

		heroi = new Heroi(1,1);
		Dragao dragao = new Dragao(1,8);
		dragoes.add(dragao);
		esp.setEspadaPosicao(3, 8);
		return lab;
	}
	
	/**
	 * Funcao auxiliar de checkExitReachable que verifica as celulas visitadas e as suas vizinhas recursivamente.
	 *
	 * @param lab labirinto
	 * @param i linha do labirinto
	 * @param j coluna do labirinto
	 * @param visited labirinto que tem as celulas ja visitadas
	 */
	private void visit(char[][] lab, int i, int j, boolean [][] visited) {
		if (i < 0 || i >= lab.length || j < 0 || j >= lab.length)
			return;
		if (lab[i][j] == 'X' || visited[i][j])
			return;
		visited[i][j] = true;
		visit(lab, i-1, j, visited);
		visit(lab, i+1, j, visited);
		visit(lab, i, j-1, visited);
		visit(lab, i, j+1, visited);
	}
	
	/**
	 * Verifica se o labirinto recebido tem um caminho ate a saida.
	 *
	 * @param lab labirinto
	 * @return boleano que indica se existe um caminho ate a saida
	 */
	public boolean checkExitReachable(char [][] lab) {
		Point p = findPos(lab, 'S');
		boolean [][] visited = new boolean[lab.length] [lab.length];
		
		visit(lab, p.y, p.x, visited);
		
		for (int i = 0; i < lab.length; i++)
			for (int j = 0; j < lab.length; j++)
				if (lab[i][j] != 'X' && ! visited[i][j])
					return false;
		
		return true; 
	}
	
	/**
	 * Gerar labirinto aleatorio.
	 *
	 * @param n tamanho do labirinto
	 * @return posLivres ArrayList que contem as posicoes ja visitadas
	 */
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

	/**
	 * Cria um labirinto so com paredes e caminho.
	 *
	 * @param n tamanho do labirinto
	 * @param flags posicoes visitadas
	 * @param posLivres posicoes livres
	 */
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

	/**
	 * Verifica as condicoes necessarias para a criacao do labirinto nomeadamente criacao da diagonais e quadrados.
	 *
	 * @param n tamanho do labirinto
	 * @param direcao para a qual se vai mover
	 * @param posVizinha posicao da celula que se vai mexer
	 * @return boleano que indica se esta celula se pode mover ou nao
	 */
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

	/**
	 * Encontra posicao de um char no labirinto.
	 *
	 * @param maze labirinto
	 * @param c char que indica o simbolo a ser procurado
	 * @return point se encontrado, null se nao encontrar
	 */
	public Point findPos(char [][] maze, char c) {
		for (int x = 0; x < maze.length; x++)			
			for (int y = 0; y < maze.length; y++)
				if (maze[y][x] == c)
					return new Point(y, x);
		return null;		
	}
	
	/**
	 * Colocar carateres aleatoriamente no labirinto criado aleatoriamente.
	 *
	 * @param posLivres posicoes livres do labirinto nomeadamente caminho
	 * @param nrDragoes a colocar no labirinto
	 * @return labirinto com os carateres ja colocados
	 */
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

	/**
	 * Funcao para mover o dragao aleatoriamente no labirinto.
	 *
	 * @param dragao do labirinto
	 * @param heroi do labirinto
	 * @param mode modo de jogo se os dragoes se mexem e podem adormecer, ou sao estaticos
	 * @param dirc direcao do dragao
	 * @param adorm se esta a dormir ou nao
	 */
	public void moverDragao(Dragao dragao, Heroi heroi, int mode, int dirc, int adorm) { // 0 - N; 1
		// - S; 2 -
		// E; 3 - O
		
 
		if (dragao.getVida() == true) {
			int direcao = dirc;						 	// para ter maior probabilidade de virar
														// em certas direcoes
			int adormecer = adorm;						// decidir se adormece ou nao || 0, 2,
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
				else if(labirinto[dragao.getLinha()][dragao.getColuna()+1] == E)
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
				else if(labirinto[dragao.getLinha()][dragao.getColuna()-1] == E)
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

	/**
	 * Funcao que move o heroi no labirinto verificando todas as condicoes.
	 *
	 * @param heroi do labirinto
	 * @param coordenada para onde o heroi se pretende mover
	 */
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

	/**
	 * Funcao que verifica se o Dragao e Heroi tao adjacente criando uma luta.
	 *
	 * @param dragao the dragao
	 * @return boleano que indica se estes estao adjacentes
	 */
	public boolean lutaPossivel(Dragao dragao) {
		Point heroiPos = heroi.getHeroiPosicao();
		Point DragaoPos = dragao.getDragaoPosicao();

		if (heroiPos.adjacentTo(DragaoPos))
			return true;

		return false;
	}

	/**
	 * Funcao que trata de verificar quem ganha no caso de o dragao e o heroi estarem adjacentes.
	 *
	 * @param dragao do labirinto
	 */
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

	/**
	 * Funcao que verifica se todos os dragoes do labirinto estao mortos.
	 *
	 * @return boleano que indica se estao todos mortos ou nao
	 */
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	/* 
	 * Converte o labirinto para uma string
	 */
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

	/**
	 * Funcao que converte o modoJogo escolhido na parte grafica para inteiro.
	 *
	 * @param modo recebido pela interface
	 * @return inteiro que indica o tipo de modo de jogo
	 */
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

	/**
	 * Funcao que permite o desenvolvimento do jogo.
	 *
	 * @param lab labirinto
	 * @param interf interface
	 * @param mode modo de jogo
	 */
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
						Random r = new Random();
						int dirc = r.nextInt(5);
						int adorm = r.nextInt(4);
						
						lab.moverDragao(dragao, heroi, mode, dirc, adorm);
						lab.HeroivsDragao(dragao);
					}

					if (heroi.getVitoria() == true) {
						interf.venceu();
						System.exit(0);
					}

					if (heroi.getVida() == false) {
						interf.perdeu();
						System.exit(1);
					}
				}
				interf.displayLabirinto(lab);

			}
		}
	}

	/**
	 * Get heroi do labirinto.
	 *
	 * @return heroi do labirinto
	 */
	public Heroi getHeroi(){
		return this.heroi;
	}

	/**
	 * Get ArrayList dos dragoes do labirinto.
	 *
	 * @return ArrayList dos dragoes do labirinto
	 */
	public ArrayList<Dragao> getDragoes(){
		return this.dragoes;
	}

	/**
	 * Get modo de jogo.
	 *
	 * @return modoJogo do jogo
	 */
	public ModoJogo getModoJogo() {
		return modoJogo;
	}

	/**
	 * Get dimensao do labirinto.
	 *
	 * @return tamanho do labirinto
	 */
	public int getDimensao(){
		return labirinto.length;
	}

	/**
	 * Get simbolo da posicao do labirinto.
	 *
	 * @param i linha do labirinto
	 * @param j coluna do labirinto
	 * @return char do simbolo da posicao dada
	 */
	public char getSimboloPosicao(int i, int j){
		return labirinto[i][j];
	}

	/**
	 * Funcao main 			.
	 *
	 * @param args the arguments
	 */
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
