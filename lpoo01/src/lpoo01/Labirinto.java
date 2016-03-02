package lpoo01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Labirinto {

	private static char[][] labirinto;
	private static Heroi heroi = new Heroi();
	private static Dragao dragao = new Dragao();

	public static char[][] criarLabirinto(){
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

	public static void displayLabirinto(char[][] lab){
		for(int i=0; i<10; i++){
			for(int j=0; j<10; j++){
				System.out.print(lab[i][j] + " ");
			}

			System.out.print("\n");
		}
	}
	public static void moverDragao(Dragao dragao, Heroi heroi, int t)
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
					dragao.mudaPosicaoDragao(dragao.getLinha() - 1,	dragao.getColuna());
					
				} else if (labirinto[dragao.getLinha() - 1][dragao.getColuna()] == 'E') {
					
					labirinto[dragao.getLinha()][dragao.getColuna()] = '.';
					labirinto[dragao.getLinha() - 1][dragao.getColuna()] = 'F';
					dragao.mudaPosicaoDragao(dragao.getLinha() - 1, dragao.getColuna());
					
				} else if (labirinto[dragao.getLinha() - 1][dragao.getColuna()] == 'H') {
					
					if (heroi.getArma() == true) {
						dragao.DragaoMorre();
						labirinto[dragao.getLinha()][dragao.getColuna()] = '.';
					} else
						heroi.heroiMorre();
					
				} else if (labirinto[dragao.getLinha() - 1][dragao.getColuna()] == 'S'
						|| labirinto[dragao.getLinha() - 1][dragao.getColuna()] == 'X') {
					
					labirinto[dragao.getLinha()][dragao.getColuna()] = 'D';
					dragao.mudaPosicaoDragao(dragao.getLinha(), dragao.getColuna());
					
				}
				
				if(t == 3 && adormecer == 1){
					labirinto[dragao.getLinha()][dragao.getColuna()] = 'd';
					dragao.DragaoDorme();
				}
				
			} else if (direcao == 1) {								//SUL
				if (labirinto[dragao.getLinha() + 1][dragao.getColuna()] == '.') {
					
					labirinto[dragao.getLinha()][dragao.getColuna()] = '.';
					labirinto[dragao.getLinha() + 1][dragao.getColuna()] = 'D';
					dragao.mudaPosicaoDragao(dragao.getLinha() + 1,	dragao.getColuna());
					
				} else if (labirinto[dragao.getLinha() + 1][dragao.getColuna()] == 'E') {
					
					labirinto[dragao.getLinha()][dragao.getColuna()] = '.';
					labirinto[dragao.getLinha() + 1][dragao.getColuna()] = 'F';
					dragao.mudaPosicaoDragao(dragao.getLinha() + 1,	dragao.getColuna());
					
				} else if (labirinto[dragao.getLinha() + 1][dragao.getColuna()] == 'H') {
					
					if (heroi.getArma() == true) {
						dragao.DragaoMorre();
						labirinto[dragao.getLinha()][dragao.getColuna()] = '.';
					} else
						heroi.heroiMorre();
					
				} else if (labirinto[dragao.getLinha() + 1][dragao.getColuna()] == 'S'
						|| labirinto[dragao.getLinha() + 1][dragao.getColuna()] == 'X') {
					
					labirinto[dragao.getLinha()][dragao.getColuna()] = 'D';
					dragao.mudaPosicaoDragao(dragao.getLinha(),	dragao.getColuna());
					
				}
				
				if(t == 3 && adormecer == 1){
					labirinto[dragao.getLinha()][dragao.getColuna()] = 'd';
					dragao.DragaoDorme();
				}
				
			} else if (direcao == 2 || direcao == 5 || direcao == 6) {			//ESTE
				if (labirinto[dragao.getLinha()][dragao.getColuna() + 1] == '.') {
					
					labirinto[dragao.getLinha()][dragao.getColuna()] = '.';
					labirinto[dragao.getLinha()][dragao.getColuna() + 1] = 'D';
					dragao.mudaPosicaoDragao(dragao.getLinha(),	dragao.getColuna() + 1);
					
				} else if (labirinto[dragao.getLinha()][dragao.getColuna() + 1] == 'E') {
					
					labirinto[dragao.getLinha()][dragao.getColuna()] = '.';
					labirinto[dragao.getLinha()][dragao.getColuna() + 1] = 'F';
					dragao.mudaPosicaoDragao(dragao.getLinha(),	dragao.getColuna() + 1);

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
					dragao.mudaPosicaoDragao(dragao.getLinha(), dragao.getColuna() - 1);

				} else if (labirinto[dragao.getLinha()][dragao.getColuna() - 1] == 'E') {
					
					labirinto[dragao.getLinha()][dragao.getColuna()] = '.';
					labirinto[dragao.getLinha()][dragao.getColuna() - 1] = 'F';
					dragao.mudaPosicaoDragao(dragao.getLinha(),	dragao.getColuna() - 1);

				} else if (labirinto[dragao.getLinha()][dragao.getColuna() - 1] == 'H') {
					
					if (heroi.getArma() == true) {
						dragao.DragaoMorre();
						labirinto[dragao.getLinha()][dragao.getColuna()] = '.';

					} else
						heroi.heroiMorre();
					
				} else if (labirinto[dragao.getLinha()][dragao.getColuna() - 1] == 'S'
						|| labirinto[dragao.getLinha()][dragao.getColuna() - 1] == 'X') {
					
					labirinto[dragao.getLinha()][dragao.getColuna()] = 'D';
					dragao.mudaPosicaoDragao(dragao.getLinha(),	dragao.getColuna());
					
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
	
	public static void moverHeroi(Heroi heroi, Dragao dragao, char coordenada)
	{
		if(coordenada == 'n')
		{
			if(labirinto[heroi.getLinha()-1][heroi.getColuna()] == '.')
			{
				labirinto[heroi.getLinha()][heroi.getColuna()] = '.';
				labirinto[heroi.getLinha()-1][heroi.getColuna()] = 'H';
				heroi.mudaPosicaoHeroi(heroi.getLinha()-1, heroi.getColuna());
			}
			else if(labirinto[heroi.getLinha()-1][heroi.getColuna()] == 'E')
			{
				labirinto[heroi.getLinha()][heroi.getColuna()] = '.';
				labirinto[heroi.getLinha()-1][heroi.getColuna()] = 'H';
				heroi.ativaArma();
				heroi.mudaPosicaoHeroi(heroi.getLinha()-1, heroi.getColuna());
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
				heroi.mudaPosicaoHeroi(heroi.getLinha()-1, heroi.getColuna());
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
				displayLabirinto(labirinto);
				System.exit(0);
			}
		}
		else if(coordenada == 's')
		{
			if(labirinto[heroi.getLinha()+1][heroi.getColuna()] == '.')
			{
				labirinto[heroi.getLinha()][heroi.getColuna()] = '.';
				labirinto[heroi.getLinha()+1][heroi.getColuna()] = 'H';
				heroi.mudaPosicaoHeroi(heroi.getLinha()+1, heroi.getColuna());
			}
			else if(labirinto[heroi.getLinha()+1][heroi.getColuna()] == 'E')
			{
				labirinto[heroi.getLinha()][heroi.getColuna()] = '.';
				labirinto[heroi.getLinha()+1][heroi.getColuna()] = 'H';
				heroi.ativaArma();
				heroi.mudaPosicaoHeroi(heroi.getLinha()+1, heroi.getColuna());
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
				heroi.mudaPosicaoHeroi(heroi.getLinha()+1, heroi.getColuna());
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
				displayLabirinto(labirinto);
				System.exit(0);
			}
		}
		else if(coordenada == 'e')
		{
			if(labirinto[heroi.getLinha()][heroi.getColuna()+1] == '.')
			{
				labirinto[heroi.getLinha()][heroi.getColuna()] = '.';
				labirinto[heroi.getLinha()][heroi.getColuna()+1] = 'H';
				heroi.mudaPosicaoHeroi(heroi.getLinha(), heroi.getColuna()+1);
			}
			else if(labirinto[heroi.getLinha()][heroi.getColuna()+1] == 'E')
			{
				labirinto[heroi.getLinha()][heroi.getColuna()] = '.';
				labirinto[heroi.getLinha()][heroi.getColuna()+1] = 'H';
				heroi.ativaArma();
				heroi.mudaPosicaoHeroi(heroi.getLinha(), heroi.getColuna()+1);
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
				heroi.mudaPosicaoHeroi(heroi.getLinha(), heroi.getColuna()+1);
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
				displayLabirinto(labirinto);
				System.exit(0);
			}
		}
		else if(coordenada == 'o')
		{
			if(labirinto[heroi.getLinha()][heroi.getColuna()-1] == '.')
			{
				labirinto[heroi.getLinha()][heroi.getColuna()] = '.';
				labirinto[heroi.getLinha()][heroi.getColuna()-1] = 'H';
				heroi.mudaPosicaoHeroi(heroi.getLinha(), heroi.getColuna()-1);
			}
			else if(labirinto[heroi.getLinha()][heroi.getColuna()-1] == 'E')
			{
				labirinto[heroi.getLinha()][heroi.getColuna()] = '.';
				labirinto[heroi.getLinha()][heroi.getColuna()-1] = 'H';
				heroi.ativaArma();
				heroi.mudaPosicaoHeroi(heroi.getLinha(), heroi.getColuna()-1);
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
				heroi.mudaPosicaoHeroi(heroi.getLinha(), heroi.getColuna()-1);
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
				displayLabirinto(labirinto);
				System.exit(0);
			}
		}
	}
	public static void main(String[] args) {
		
		System.out.println("Modos de jogo:");
		System.out.println("1 - Dragao parado");
		System.out.println("2 - Dragao com movimento aleatorio");
		System.out.println("3 - Dragao com movimento aleatorio intercalado com dormir");
		System.out.print("Indique um modo de jogo: ");
		Scanner T = new Scanner(System.in);
		
		int t = T.nextInt();
		
		while(t != 1 && t != 2 && t != 3){
			System.out.print("Opcao invalida!");
			System.out.print("\nIndique um modo de jogo: ");
			T = new Scanner(System.in);
		}
		System.out.print("\n");
		
		labirinto = criarLabirinto();
		displayLabirinto(labirinto);
		int jogadas = 1;
		while (heroi.getVida() == true) {
			System.out.print("\nJogada " + jogadas);
			System.out.print("\nIntroduza o comando: ");
			Scanner S = new Scanner(System.in);
			if (S.hasNext()) {
				char coordenada = S.next().charAt(0);
				moverHeroi(heroi, dragao, coordenada);
				
				if(t != 1)
					moverDragao(dragao, heroi, t);
				
				if(heroi.getVida() == false)
				{
					System.out.println("GAME OVER! \n");
					System.exit(1);
				}
				displayLabirinto(labirinto);
			}
			jogadas++;
		}

	}

}