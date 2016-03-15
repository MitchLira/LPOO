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

public class Labirinto {
	private char[][] labirinto;
	private Heroi heroi;
	public Espada esp;
	private ArrayList<Dragao> dragoes;
	
	private static final char P = '.';
	private static final char E = 'E';
	private static final char S = 'S';
	private static final char F = 'F';
	private static final char X = 'X';
	private static final char H = 'H';
	private static final char D = 'D';

	public void criarLabirinto(char[][] labir, Heroi heroi, ArrayList<Dragao> dragoes,
			Espada espada) {
		labirinto = labir;
		this.heroi = heroi;
		this.dragoes = dragoes;
		this.esp = espada;
	}

	public char[][] criarLabirinto() {

		heroi = new Heroi();
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
		heroi.setPosicaoHeroi(1, 1);
		Dragao dragao = new Dragao(1,8);
		dragoes.add(dragao);
		esp.setEspadaPosicao(6, 1);
		return lab;
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

		return PosLivres;
	}

	public void GeradorLabirintoInical(int n, ArrayDeque<Point> flags,
			ArrayList<Point> posLivres) {
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

	public void ColocarCarateres(ArrayList<Point> posLivres) {
		Random rand = new Random();
		int nrPosLivres = posLivres.size();
		dragoes = new ArrayList<Dragao>();
		int nrDragoes = dragoes.size();
		
		int HeroiPos = rand.nextInt(nrPosLivres);
		Point HeroiP = new Point(posLivres.get(HeroiPos).y,
				posLivres.get(HeroiPos).x);
		heroi = new Heroi(HeroiP.x, HeroiP.y);
		labirinto[HeroiP.y][HeroiP.x] = H;
		nrPosLivres--;
		posLivres.remove(HeroiPos);

		int EspadaPos = rand.nextInt(nrPosLivres);
		Point EspadaP = new Point(posLivres.get(EspadaPos).y,
				posLivres.get(EspadaPos).x);
		esp = new Espada(EspadaP.x, EspadaP.y);
		labirinto[EspadaP.y][EspadaP.x] = E;
		nrPosLivres--;
		posLivres.remove(EspadaPos);
		
		for (int i = 0; i < (labirinto.length)/5; i++) {
			Dragao dragao;
			do {
				int DragaoPos = rand.nextInt(nrPosLivres);
				Point DragaoP = new Point(posLivres.get(DragaoPos).y,
				posLivres.get(DragaoPos).x);
				 dragao = new Dragao(DragaoP.x, DragaoP.y);
				 nrPosLivres--;
				 posLivres.remove(dragao);
			} while (lutaPossivel(dragao));
			labirinto[dragao.getLinha()][dragao.getColuna()] = D;
			dragoes.add(dragao);
		}
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

	public void moverDragao(Dragao dragao, Heroi heroi, int mode) { // 0 - N; 1
																	// - S; 2 -
																	// E; 3 - O
		Random r = new Random();

		if (dragao.getVida() == true) {
			int direcao = r.nextInt(5); // para ter maior probabilidade de virar
										// em certas direcoes
			int adormecer = r.nextInt(4); // decidir se adormece ou nao || 0, 2,
											// 3 -> Nao || 1 -> Sim

			Point EspPos = esp.getEspPos();
			Point DragPos = dragao.getDragaoPosicao();

			if (dragao.getAdormecido()) {
				dragao.DragaoAcorda();
				labirinto[dragao.getLinha()][dragao.getColuna()] = dragao
						.getSimbolo();
			} else if (mode == 3 && adormecer == 1) {
				dragao.DragaoDorme();
				labirinto[dragao.getLinha()][dragao.getColuna()] = dragao
						.getSimbolo();
			} else {
				dragao.DragaoAcorda();

				switch (direcao) {
				case 0: // Norte
				{
					if (labirinto[dragao.getLinha() - 1][dragao.getColuna()] == P) {

						if (EspPos.equals(DragPos)) {
							esp.simbol = E;
							esp.setEspada();
							dragao.setSimbolo();
							labirinto[dragao.getLinha()][dragao.getColuna()] = esp.simbol;
						} else {
							esp.simbol = E;
							dragao.setSimbolo();
							labirinto[dragao.getLinha()][dragao.getColuna()] = P;
						}

						labirinto[dragao.getLinha() - 1][dragao.getColuna()] = dragao
								.getSimbolo();
						dragao.moveDragaoNorte();

					} else if (labirinto[dragao.getLinha() - 1][dragao
							.getColuna()] == E) {
						dragao.DragEsp();

						labirinto[dragao.getLinha()][dragao.getColuna()] = P;
						labirinto[dragao.getLinha() - 1][dragao.getColuna()] = dragao
								.getSimbolo();
						dragao.moveDragaoNorte();

					} else if (labirinto[dragao.getLinha() - 1][dragao
							.getColuna()] == S
							|| labirinto[dragao.getLinha() - 1][dragao
									.getColuna()] == X) {

						labirinto[dragao.getLinha()][dragao.getColuna()] = dragao
								.getSimbolo();
						dragao.mantemPosicaoDragao();

					}

					// a mais mas por precaucao vai ficar
					if (mode == 3 && adormecer == 1) {
						if (EspPos.equals(DragPos))
							dragao.DragEsp();
						else
							dragao.DragaoDorme();

						labirinto[dragao.getLinha()][dragao.getColuna()] = dragao
								.getSimbolo();
					}

					break;
				}
				case 1: // Sul
				{
					if (labirinto[dragao.getLinha() + 1][dragao.getColuna()] == P) {

						if (EspPos.equals(DragPos)) {
							esp.simbol = E;
							esp.setEspada();
							dragao.setSimbolo();
							labirinto[dragao.getLinha()][dragao.getColuna()] = esp.simbol;
						} else {
							esp.simbol = E;
							dragao.setSimbolo();
							labirinto[dragao.getLinha()][dragao.getColuna()] = P;
						}

						labirinto[dragao.getLinha() + 1][dragao.getColuna()] = dragao
								.getSimbolo();
						dragao.moveDragaoSul();

					} else if (labirinto[dragao.getLinha() + 1][dragao
							.getColuna()] == E) {
						dragao.DragEsp();

						labirinto[dragao.getLinha()][dragao.getColuna()] = P;
						labirinto[dragao.getLinha() + 1][dragao.getColuna()] = F;
						dragao.moveDragaoSul();

					} else if (labirinto[dragao.getLinha() + 1][dragao
							.getColuna()] == S
							|| labirinto[dragao.getLinha() + 1][dragao
									.getColuna()] == X) {

						labirinto[dragao.getLinha()][dragao.getColuna()] = dragao
								.getSimbolo();
						dragao.mantemPosicaoDragao();

					}

					if (mode == 3 && adormecer == 1) {
						dragao.DragaoDorme();
						labirinto[dragao.getLinha()][dragao.getColuna()] = dragao
								.getSimbolo();
					}

					break;
				}

				case 2: // Este
				{
					if (labirinto[dragao.getLinha()][dragao.getColuna() + 1] == P) {

						if (EspPos.equals(DragPos)) {
							esp.simbol = E;
							esp.setEspada();
							dragao.setSimbolo();
							labirinto[dragao.getLinha()][dragao.getColuna()] = esp.simbol;
						} else {
							esp.simbol = E;
							dragao.setSimbolo();
							labirinto[dragao.getLinha()][dragao.getColuna()] = P;
						}

						labirinto[dragao.getLinha()][dragao.getColuna() + 1] = dragao
								.getSimbolo();
						dragao.moveDragaoDireita();
					} else if (labirinto[dragao.getLinha()][dragao.getColuna() + 1] == E) {
						dragao.DragEsp();

						labirinto[dragao.getLinha()][dragao.getColuna()] = P;
						labirinto[dragao.getLinha()][dragao.getColuna() + 1] = F;
						dragao.moveDragaoDireita();
					} else if (labirinto[dragao.getLinha()][dragao.getColuna() + 1] == S
							|| labirinto[dragao.getLinha()][dragao.getColuna() + 1] == X) {

						labirinto[dragao.getLinha()][dragao.getColuna()] = dragao
								.getSimbolo();
						dragao.mantemPosicaoDragao();

					}

					if (mode == 3 && adormecer == 1) {
						dragao.DragaoDorme();
						labirinto[dragao.getLinha()][dragao.getColuna()] = dragao
								.getSimbolo();
					}

					break;
				}
				case 3: // Oeste
				{
					if (labirinto[dragao.getLinha()][dragao.getColuna() - 1] == P) {

						if (EspPos.equals(DragPos)) {
							esp.simbol = E;
							esp.setEspada();
							dragao.setSimbolo();
							labirinto[dragao.getLinha()][dragao.getColuna()] = esp.simbol;
						} else {
							esp.simbol = E;
							dragao.setSimbolo();
							labirinto[dragao.getLinha()][dragao.getColuna()] = P;
						}

						labirinto[dragao.getLinha()][dragao.getColuna() - 1] = dragao
								.getSimbolo();
						dragao.moveDragaoEsquerda();
					} else if (labirinto[dragao.getLinha()][dragao.getColuna() - 1] == E) {
						dragao.DragEsp();

						labirinto[dragao.getLinha()][dragao.getColuna()] = P;
						labirinto[dragao.getLinha()][dragao.getColuna() - 1] = F;
						dragao.moveDragaoEsquerda();

					} else if (labirinto[dragao.getLinha()][dragao.getColuna() - 1] == S
							|| labirinto[dragao.getLinha()][dragao.getColuna() - 1] == X) {

						labirinto[dragao.getLinha()][dragao.getColuna()] = dragao
								.getSimbolo();
						dragao.mantemPosicaoDragao();
					}

					if (mode == 3 && adormecer == 1) {
						dragao.DragaoDorme();
						labirinto[dragao.getLinha()][dragao.getColuna()] = dragao
								.getSimbolo();
					}

					break;
				}
				case 4: // Nao se move
				{
					if (EspPos.equals(DragPos))
						dragao.DragEsp();
					else
						dragao.setSimbolo();

					dragao.mantemPosicaoDragao();
					labirinto[dragao.getLinha()][dragao.getColuna()] = dragao
							.getSimbolo();

					if (mode == 3 && adormecer == 1) {
						dragao.DragaoDorme();
						labirinto[dragao.getLinha()][dragao.getColuna()] = dragao
								.getSimbolo();
					}

					break;
				}
				}
			}
		}
	}

	public void moverHeroi(Heroi heroi, char coordenada,
			Interface interf) {
		switch (coordenada) {
		case 'n': {
			if (labirinto[heroi.getLinha() - 1][heroi.getColuna()] == P) {
				labirinto[heroi.getLinha()][heroi.getColuna()] = P;
				labirinto[heroi.getLinha() - 1][heroi.getColuna()] = heroi
						.getSimbolo();
				heroi.moveHeroiNorte();
			} else if (labirinto[heroi.getLinha() - 1][heroi.getColuna()] == E) {
				labirinto[heroi.getLinha()][heroi.getColuna()] = P;
				heroi.ativaArma();
				labirinto[heroi.getLinha() - 1][heroi.getColuna()] = heroi
						.getSimbolo();
				heroi.moveHeroiNorte();
			} else if (labirinto[heroi.getLinha() - 1][heroi.getColuna()] == S
					&& this.DragoesTodosMortos()) {
				heroi.heroiGanha();
				labirinto[heroi.getLinha()][heroi.getColuna()] = P;
				labirinto[heroi.getLinha()][heroi.getColuna() - 1] = heroi
						.getSimbolo();
				heroi.moveHeroiNorte();
			}

			break;
		}
		case 's': {
			if (labirinto[heroi.getLinha() + 1][heroi.getColuna()] == P) {
				labirinto[heroi.getLinha()][heroi.getColuna()] = P;
				labirinto[heroi.getLinha() + 1][heroi.getColuna()] = heroi
						.getSimbolo();
				heroi.moveHeroiSul();
			} else if (labirinto[heroi.getLinha() + 1][heroi.getColuna()] == E) {
				labirinto[heroi.getLinha()][heroi.getColuna()] = P;
				heroi.ativaArma();
				labirinto[heroi.getLinha() + 1][heroi.getColuna()] = heroi
						.getSimbolo();
				heroi.moveHeroiSul();
			} else if (labirinto[heroi.getLinha() + 1][heroi.getColuna()] == S
					&& this.DragoesTodosMortos()) {
				heroi.heroiGanha();
				labirinto[heroi.getLinha()][heroi.getColuna()] = P;
				labirinto[heroi.getLinha() + 1][heroi.getColuna()] = heroi
						.getSimbolo();
				heroi.moveHeroiSul();
			}

			break;
		}
		case 'e': {
			if (labirinto[heroi.getLinha()][heroi.getColuna() + 1] == P) {
				labirinto[heroi.getLinha()][heroi.getColuna()] = P;
				labirinto[heroi.getLinha()][heroi.getColuna() + 1] = heroi
						.getSimbolo();
				heroi.moveHeroiDireita();
			}

			else if (labirinto[heroi.getLinha()][heroi.getColuna() + 1] == E) {
				labirinto[heroi.getLinha()][heroi.getColuna()] = P;
				heroi.ativaArma();
				labirinto[heroi.getLinha()][heroi.getColuna() + 1] = heroi
						.getSimbolo();
				heroi.moveHeroiDireita();
			} else if (labirinto[heroi.getLinha()][heroi.getColuna() + 1] == S
					&& this.DragoesTodosMortos()) {
				heroi.heroiGanha();
				labirinto[heroi.getLinha()][heroi.getColuna()] = P;
				labirinto[heroi.getLinha()][heroi.getColuna() + 1] = heroi
						.getSimbolo();
				heroi.moveHeroiDireita();
			}

			break;
		}
		case 'o': {
			if (labirinto[heroi.getLinha()][heroi.getColuna() - 1] == P) {
				labirinto[heroi.getLinha()][heroi.getColuna()] = P;
				labirinto[heroi.getLinha()][heroi.getColuna() - 1] = heroi
						.getSimbolo();
				heroi.moveHeroiEsquerda();
			}

			else if (labirinto[heroi.getLinha()][heroi.getColuna() - 1] == E) {
				labirinto[heroi.getLinha()][heroi.getColuna()] = P;
				heroi.ativaArma();
				labirinto[heroi.getLinha()][heroi.getColuna() - 1] = heroi
						.getSimbolo();
				heroi.moveHeroiEsquerda();
			} else if (labirinto[heroi.getLinha()][heroi.getColuna() - 1] == S
					&& this.DragoesTodosMortos()) {
				heroi.heroiGanha();
				labirinto[heroi.getLinha()][heroi.getColuna()] = P;
				labirinto[heroi.getLinha()][heroi.getColuna() - 1] = heroi
						.getSimbolo();
				heroi.moveHeroiEsquerda();
			}

			break;
		}
		}
	}

	private boolean lutaPossivel(Dragao dragao) {
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
				labirinto[dragao.getLinha()][dragao.getColuna()] = P;
				labirinto[heroi.getLinha()][heroi.getColuna()] = heroi
						.getSimbolo();
				dragao.DragaoMorre();
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

	public void jogar(Labirinto lab, Interface interf, int mode) {

		ArrayList<Point> posLivres;
		posLivres = lab.GerarLabirinto(15);
		lab.ColocarCarateres(posLivres);
		interf.displayLabirinto(lab);

		int jogada = 1;
		while (heroi.getVida() == true) {
			Scanner S = interf.fazJogada(jogada);
			jogada++;

			if (S.hasNext()) {
				char coordenada = S.next().charAt(0);
				lab.moverHeroi(heroi, coordenada, interf);
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

	public static void main(String[] args) {
		Interface interf = new Interface();
		int mode = interf.modojogo();

		Labirinto lab = new Labirinto();
		lab.jogar(lab, interf, mode);

	}
}
