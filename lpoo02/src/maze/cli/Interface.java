package maze.cli;

import java.util.Scanner;

public class Interface {
	
	//Faz os prints para escolher o modo de jogo
	public int modojogo()
	{
		System.out.println("Modos de jogo: ");
		System.out.println("1 - Dragao parado");
		System.out.println("2 - Dragao com movimento aleatorio");
		System.out.println("3 - Dragao com movimento aleatorio intercalado com dormir");
		System.out.print("Indique um modo de jogo: ");


		Scanner T = new Scanner(System.in);
		int t = T.nextInt();

		while(t != 1 && t != 2 && t != 3){
			System.out.print("Opcao invalida!");
			System.out.print("\nIndique um modo de jogo: ");
			T.nextLine();
			t = T.nextInt();
		}
		T.nextLine();
		
		System.out.print("\n");
		return t;
	}
	
	//Faz o print de vencedor
	public void venceu(){
		System.out.println("WINNER!");
	}
	
	//Faz o print de perdedor
	public void perdeu(){
		System.out.println("GAME OVER!");
	}
	
	//Faz os prints de cada jogada
	public Scanner fazJogada(int jogada){				
		System.out.print("\nJogada " + jogada);
		System.out.print("\nIntroduza comando: ");
		Scanner S = new Scanner(System.in);
		
		return S;
	}
	
	//Faz prints de heroi atacado
	public void heroiAtacado(){
		System.out.println("Heroi sem arma atacado!");
	}
	
	//Faz display do labirinto
	public void displayLabirinto(char[][] labirinto){
		int tamanho = labirinto[0].length;
		
		for(int i=0; i< tamanho; i++){
			for(int j=0; j< tamanho; j++){
				System.out.print(labirinto[i][j] + " ");
			}
			
			System.out.print("\n");
		}

	}
	
	
	
	public static void main(String[] args) {
		
	}
}

