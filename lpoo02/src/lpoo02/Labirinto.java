package lpoo02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Labirinto {

	private char[][] labirinto;
	private Heroi heroi = new Heroi();
	private Dragao dragao = new Dragao();

	public char[][] criarLabirinto(char[][] lab)
	{
		return lab;
	};
	public char[][] criarLabirinto(){
		labirinto = new char[10][10];

		labirinto[1][1] = 'H';
		labirinto[3][1] = 'D';
		labirinto[8][1] = 'E';
		labirinto[5][9] = 'S';

		//Primeira Linha
		for(int i=0; i<10; i++){
			labirinto[0][i] = 'X';
		}

		//Ultima linha
		for(int p=0; p<10; p++){
			labirinto[9][p] = 'X';
		}

		//Primeira coluna
		for(int t=0; t<10; t++){
			labirinto[t][0] = 'X';
		}

		//Ultima coluna
		for(int q=0; q<10; q++){
			if(labirinto[q][9] != 'S'){
				labirinto[q][9] = 'X';
			}
		}

		for(int i=2; i<9; i++){
			if(i!=5)
				labirinto[i][2] = 'X';
		}

		for(int i=2; i<9; i++){
			if(i!=5){
				labirinto[i][2] = 'X';
				labirinto[i][3] = 'X';
			}
		}

		for(int i = 2; i<8; i++){
			if(i!=5)
				labirinto[i][5] = 'X';

			labirinto[i][7] = 'X';
		}

		for(int i=0; i<10; i++){
			for(int p=0; p<10; p++){
				if(labirinto[i][p] != 'X' && labirinto[i][p] != 'H' && labirinto[i][p] != 'S' && labirinto[i][p] != 'D' && labirinto[i][p] != 'E')
					labirinto[i][p] = '.';
			}
		}

		return labirinto;
	}

	public void displayLabirinto(){
		for(int i=0; i<10; i++){
			for(int j=0; j<10; j++){
				System.out.print(labirinto[i][j] + " ");
			}

			System.out.print("\n");
		}
	}
	public void moverDragao(Dragao dragao, Heroi heroi, int t)
	{   //0 - N; 1 - S; 2 - E; 3 - O
		Random r = new Random();
		if(dragao.getVida() == true) {
			int direcao = r.nextInt(8);		//para ter maior probabilidade de virar em certas direcoes
			int adormecer = r.nextInt(4);	//decidir se adormece ou nao 0 -> Nao || 1 -> Sim

			if (direcao == 0) {									//NORTE
				dragao.DragaoAcorda();

				labirinto[dragao.getLinha()][dragao.getColuna()] = 'D';

				if (labirinto[dragao.getLinha() - 1][dragao.getColuna()] == '.') {

					labirinto[dragao.getLinha()][dragao.getColuna()] = '.';
					labirinto[dragao.getLinha() - 1][dragao.getColuna()] = 'D';
					dragao.moveDragaoNorte();

				} else if (labirinto[dragao.getLinha() - 1][dragao.getColuna()] == 'E') {

					labirinto[dragao.getLinha()][dragao.getColuna()] = '.';
					labirinto[dragao.getLinha() - 1][dragao.getColuna()] = 'F';
					dragao.moveDragaoNorte();

				} else if (labirinto[dragao.getLinha() - 1][dragao.getColuna()] == 'H') {

					if (heroi.getArma() == true) {
						dragao.DragaoMorre();
						labirinto[dragao.getLinha()][dragao.getColuna()] = '.';
					} else
						heroi.heroiMorre();

				} else if (labirinto[dragao.getLinha() - 1][dragao.getColuna()] == 'S'
						|| labirinto[dragao.getLinha() - 1][dragao.getColuna()] == 'X') {

					labirinto[dragao.getLinha()][dragao.getColuna()] = 'D';
					dragao.mantemPosicaoDragao();

				}

				if(t == 3 && adormecer == 1){
					labirinto[dragao.getLinha()][dragao.getColuna()] = 'd';
					dragao.DragaoDorme();
				}

			} else if (direcao == 1) {								//SUL
				if (labirinto[dragao.getLinha() + 1][dragao.getColuna()] == '.') {

					labirinto[dragao.getLinha()][dragao.getColuna()] = '.';
					labirinto[dragao.getLinha() + 1][dragao.getColuna()] = 'D';
					dragao.moveDragaoSul();

				} else if (labirinto[dragao.getLinha() + 1][dragao.getColuna()] == 'E') {

					labirinto[dragao.getLinha()][dragao.getColuna()] = '.';
					labirinto[dragao.getLinha() + 1][dragao.getColuna()] = 'F';
					dragao.moveDragaoSul();

				} else if (labirinto[dragao.getLinha() + 1][dragao.getColuna()] == 'H') {

					if (heroi.getArma() == true) {
						dragao.DragaoMorre();
						labirinto[dragao.getLinha()][dragao.getColuna()] = '.';
					} else
						heroi.heroiMorre();

				} else if (labirinto[dragao.getLinha() + 1][dragao.getColuna()] == 'S'
						|| labirinto[dragao.getLinha() + 1][dragao.getColuna()] == 'X') {

					labirinto[dragao.getLinha()][dragao.getColuna()] = 'D';
					dragao.mantemPosicaoDragao();

				}

				if(t == 3 && adormecer == 1){
					labirinto[dragao.getLinha()][dragao.getColuna()] = 'd';
					dragao.DragaoDorme();
				}

			} else if (direcao == 2 || direcao == 5 || direcao == 6) {			//ESTE
				if (labirinto[dragao.getLinha()][dragao.getColuna() + 1] == '.') {

					labirinto[dragao.getLinha()][dragao.getColuna()] = '.';
					labirinto[dragao.getLinha()][dragao.getColuna() + 1] = 'D';
					dragao.moveDragaoDireita();
				} else if (labirinto[dragao.getLinha()][dragao.getColuna() + 1] == 'E') {

					labirinto[dragao.getLinha()][dragao.getColuna()] = '.';
					labirinto[dragao.getLinha()][dragao.getColuna() + 1] = 'F';
					dragao.moveDragaoDireita();
				} else if (labirinto[dragao.getLinha()][dragao.getColuna() + 1] == 'H') {

					if (heroi.getArma() == true) {
						dragao.DragaoMorre();
						labirinto[dragao.getLinha()][dragao.getColuna()] = '.';
					} else
						heroi.heroiMorre();

				} else if (labirinto[dragao.getLinha()][dragao.getColuna() + 1] == 'S'
						|| labirinto[dragao.getLinha()][dragao.getColuna() + 1] == 'X') {

					labirinto[dragao.getLinha()][dragao.getColuna()] = 'D';
					dragao.mudaPosicaoDragao(dragao.getLinha(),	dragao.getColuna());

				}

				if(t == 3 && adormecer == 1){
					labirinto[dragao.getLinha()][dragao.getColuna()] = 'd';
					dragao.DragaoDorme();
				}

			} else if (direcao == 3 || direcao == 7 || direcao == 8) {			//OESTE
				if (labirinto[dragao.getLinha()][dragao.getColuna() - 1] == '.') {

					labirinto[dragao.getLinha()][dragao.getColuna()] = '.';
					labirinto[dragao.getLinha()][dragao.getColuna() - 1] = 'D';
					dragao.moveDragaoEsquerda();
				} else if (labirinto[dragao.getLinha()][dragao.getColuna() - 1] == 'E') {

					labirinto[dragao.getLinha()][dragao.getColuna()] = '.';
					labirinto[dragao.getLinha()][dragao.getColuna() - 1] = 'F';
					dragao.moveDragaoEsquerda();

				} else if (labirinto[dragao.getLinha()][dragao.getColuna() - 1] == 'H') {

					if (heroi.getArma() == true) {
						dragao.DragaoMorre();
						labirinto[dragao.getLinha()][dragao.getColuna()] = '.';

					} else
						heroi.heroiMorre();

				} else if (labirinto[dragao.getLinha()][dragao.getColuna() - 1] == 'S'
						|| labirinto[dragao.getLinha()][dragao.getColuna() - 1] == 'X') {

					labirinto[dragao.getLinha()][dragao.getColuna()] = 'D';
					dragao.mantemPosicaoDragao();
				}

				if(t == 3 && adormecer == 1){
					labirinto[dragao.getLinha()][dragao.getColuna()] = 'd';
					dragao.DragaoDorme();
				}

			} else if (direcao == 4) {
				dragao.mudaPosicaoDragao(dragao.getLinha(), dragao.getColuna());
				labirinto[dragao.getLinha()][dragao.getColuna()] = 'D';

				if(t == 3 && adormecer == 1){
					labirinto[dragao.getLinha()][dragao.getColuna()] = 'd';
					dragao.DragaoDorme();
				}
			}
		}
	}

	public void moverHeroi(Heroi heroi, Dragao dragao, char coordenada)
	{
		if(coordenada == 'n')
		{
			if(labirinto[heroi.getLinha()-1][heroi.getColuna()] == '.')
			{
				labirinto[heroi.getLinha()][heroi.getColuna()] = '.';
				labirinto[heroi.getLinha()-1][heroi.getColuna()] = 'H';
				heroi.moveHeroiNorte();
			}
			else if(labirinto[heroi.getLinha()-1][heroi.getColuna()] == 'E')
			{
				labirinto[heroi.getLinha()][heroi.getColuna()] = '.';
				labirinto[heroi.getLinha()-1][heroi.getColuna()] = 'H';
				heroi.ativaArma();
				heroi.moveHeroiNorte();
			}
			else if(labirinto[heroi.getLinha()-1][heroi.getColuna()] == 'D' 
					&& heroi.getArma() == false && dragao.getAdormecido() == false)
			{
				System.out.println("Heroi, sem arma, atacado!");
				heroi.heroiMorre(); //ENDGAME
				labirinto[heroi.getLinha()][heroi.getColuna()] = '.';
			}
			else if((labirinto[heroi.getLinha()-1][heroi.getColuna()] == 'D' 
					|| labirinto[heroi.getLinha()-1][dragao.getColuna()] == 'd') && heroi.getArma() == true)
			{
				dragao.DragaoMorre();
				labirinto[dragao.getLinha()][dragao.getColuna()] = '.';
				labirinto[heroi.getLinha()][heroi.getColuna()] = '.';
				labirinto[heroi.getLinha()-1][heroi.getColuna()] = 'H';
				heroi.moveHeroiNorte();
			}
			else if(labirinto[heroi.getLinha()-1][heroi.getColuna()] == 'F')
			{
				System.out.println("Heroi, sem arma, atacado!");
				heroi.heroiMorre();//ENDGAME
				labirinto[heroi.getLinha()][heroi.getColuna()] = '.';
			}
			else if(labirinto[heroi.getLinha()-1][heroi.getColuna()] == 'S' && dragao.getVida() == false)
			{
				heroi.heroiGanha();
				labirinto[heroi.getLinha()][heroi.getColuna()] = '.';
				labirinto[heroi.getLinha()][heroi.getColuna()-1] = 'H';
				System.out.println("WINNER");
				displayLabirinto();
				System.exit(0);
			}
		}
		else if(coordenada == 's')
		{
			if(labirinto[heroi.getLinha()+1][heroi.getColuna()] == '.')
			{
				labirinto[heroi.getLinha()][heroi.getColuna()] = '.';
				labirinto[heroi.getLinha()+1][heroi.getColuna()] = 'H';
				heroi.moveHeroiSul();
			}
			else if(labirinto[heroi.getLinha()+1][heroi.getColuna()] == 'E')
			{
				labirinto[heroi.getLinha()][heroi.getColuna()] = '.';
				labirinto[heroi.getLinha()+1][heroi.getColuna()] = 'H';
				heroi.ativaArma();
				heroi.moveHeroiSul();
			}
			else if(labirinto[heroi.getLinha()+1][heroi.getColuna()] == 'D' && heroi.getArma() == false)
			{
				System.out.println("Heroi, sem arma, atacado!");
				heroi.heroiMorre(); //ENDGAME
				labirinto[heroi.getLinha()][heroi.getColuna()] = '.';
			}
			else if((labirinto[heroi.getLinha()+1][heroi.getColuna()] == 'D' 
					|| labirinto[heroi.getLinha()+1][heroi.getColuna()] =='d') && heroi.getArma() == true)
			{
				labirinto[dragao.getLinha()][dragao.getColuna()] = '.';
				dragao.DragaoMorre();
				labirinto[heroi.getLinha()][heroi.getColuna()] = '.';
				labirinto[heroi.getLinha()+1][heroi.getColuna()] = 'H';
				heroi.moveHeroiSul();
			}
			else if(labirinto[heroi.getLinha()+1][heroi.getColuna()] == 'F')
			{
				System.out.println("Heroi, sem arma, atacado!");
				heroi.heroiMorre(); //ENDGAME
				labirinto[heroi.getLinha()][heroi.getColuna()] = '.';
			}
			else if(labirinto[heroi.getLinha()+1][heroi.getColuna()] == 'S' && dragao.getVida() == false)
			{
				heroi.heroiGanha();
				labirinto[heroi.getLinha()][heroi.getColuna()] = '.';
				labirinto[heroi.getLinha()+1][heroi.getColuna()] = 'H';
				System.out.println("WINNER");
				displayLabirinto();
				System.exit(0);
			}
		}
		else if(coordenada == 'e')
		{
			if(labirinto[heroi.getLinha()][heroi.getColuna()+1] == '.')
			{
				labirinto[heroi.getLinha()][heroi.getColuna()] = '.';
				labirinto[heroi.getLinha()][heroi.getColuna()+1] = 'H';
				heroi.moveHeroiDireita();
			}
			else if(labirinto[heroi.getLinha()][heroi.getColuna()+1] == 'E')
			{
				labirinto[heroi.getLinha()][heroi.getColuna()] = '.';
				labirinto[heroi.getLinha()][heroi.getColuna()+1] = 'H';
				heroi.ativaArma();
				heroi.moveHeroiDireita();
			}
			else if(labirinto[heroi.getLinha()][heroi.getColuna()+1] == 'D' && heroi.getArma() == false)
			{
				System.out.println("Heroi, sem arma, atacado!");
				heroi.heroiMorre(); //ENDGAME
				labirinto[heroi.getLinha()][heroi.getColuna()] = '.';
			}
			else if((labirinto[heroi.getLinha()][heroi.getColuna()+1] == 'D' 
					|| labirinto[heroi.getLinha()][heroi.getColuna()+1] == 'd') && heroi.getArma() == true)
			{
				labirinto[dragao.getLinha()][dragao.getColuna()] = '.';
				dragao.DragaoMorre();
				labirinto[heroi.getLinha()][heroi.getColuna()] = '.';
				labirinto[heroi.getLinha()][heroi.getColuna()+1] = 'H';
				heroi.moveHeroiDireita();
			}
			else if(labirinto[heroi.getLinha()][heroi.getColuna()+1] == 'F')
			{
				System.out.println("Heroi, sem arma, atacado!");
				heroi.heroiMorre(); //ENDGAME
				labirinto[heroi.getLinha()][heroi.getColuna()] = '.';
			}
			else if(labirinto[heroi.getLinha()][heroi.getColuna()+1] == 'S' && dragao.getVida() == false)
			{
				heroi.heroiGanha();
				labirinto[heroi.getLinha()][heroi.getColuna()] = '.';
				labirinto[heroi.getLinha()][heroi.getColuna()+1] = 'H';
				System.out.println("WINNER");
				displayLabirinto();
				System.exit(0);
			}
		}
		else if(coordenada == 'o')
		{
			if(labirinto[heroi.getLinha()][heroi.getColuna()-1] == '.')
			{
				labirinto[heroi.getLinha()][heroi.getColuna()] = '.';
				labirinto[heroi.getLinha()][heroi.getColuna()-1] = 'H';
				heroi.moveHeroiEsquerda();
			}
			else if(labirinto[heroi.getLinha()][heroi.getColuna()-1] == 'E')
			{
				labirinto[heroi.getLinha()][heroi.getColuna()] = '.';
				labirinto[heroi.getLinha()][heroi.getColuna()-1] = 'H';
				heroi.ativaArma();
				heroi.moveHeroiEsquerda();
			}
			else if(labirinto[heroi.getLinha()][heroi.getColuna()-1] == 'D' 
					&& heroi.getArma() == false && dragao.getAdormecido() == false)
			{
				System.out.println("Heroi, sem arma, atacado!");
				heroi.heroiMorre(); //ENDGAME
				labirinto[heroi.getLinha()][heroi.getColuna()] = '.';
			}
			else if((labirinto[heroi.getLinha()][heroi.getColuna()-1] == 'D' 
					|| labirinto[heroi.getLinha()][heroi.getColuna()-1] =='d') && heroi.getArma() == true)
			{
				labirinto[dragao.getLinha()][dragao.getColuna()] = '.';
				dragao.DragaoMorre();
				labirinto[heroi.getLinha()][heroi.getColuna()] = '.';
				labirinto[heroi.getLinha()][heroi.getColuna()-1] = 'H';
				heroi.moveHeroiEsquerda();
			}
			else if(labirinto[heroi.getLinha()][heroi.getColuna()-1] == 'F')
			{
				System.out.println("Heroi, sem arma, atacado!");
				heroi.heroiMorre(); //ENDGAME
				labirinto[heroi.getLinha()][heroi.getColuna()] = '.';
			}
			else if(labirinto[heroi.getLinha()][heroi.getColuna()-1] == 'S' && dragao.getVida() == false)
			{
				heroi.heroiGanha();
				labirinto[heroi.getLinha()][heroi.getColuna()] = '.';
				labirinto[heroi.getLinha()][heroi.getColuna()-1] = 'H';
				System.out.println("WINNER");
				displayLabirinto();
				System.exit(0);
			}
		}
	}
	public static void main(String[] args) {
		Interface mm = new Interface();
		
		int t = mm.modojogo();

		Labirinto labirinto = new Labirinto();
		labirinto.criarLabirinto();
		labirinto.displayLabirinto();

		int jogadas = 1;
		while(labirinto.heroi.getVida() == true){
			System.out.print("\nJogada " + jogadas);
			System.out.print("\nIntroduza o comando: ");
			Scanner S = new Scanner(System.in);

			if(S.hasNext()){
				char coordenada = S.next().charAt(0);
				labirinto.moverHeroi(labirinto.heroi, labirinto.dragao, coordenada);

				if(t != 1)
					labirinto.moverDragao(labirinto.dragao, labirinto.heroi, t);

				if(labirinto.heroi.getVida() == false)
				{
					System.out.println("GAME OVER! \n");
					System.exit(1);
				}
				labirinto.displayLabirinto();
			}
		}
	}
}

