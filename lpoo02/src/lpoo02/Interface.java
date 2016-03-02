package lpoo02;

import java.util.Scanner;

public class Interface {
	
	private char[][] labirinto;
	private Heroi heroi = new Heroi();
	private Dragao dragao = new Dragao();

	public static void main(String[] args) {
		
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
			T = new Scanner(System.in);
		}
		System.out.print("\n");
		
		Labirinto labirinto = new Labirinto();
		Heroi heroi = new Heroi();
		Dragao dragao = new Dragao();
		
		labirinto.criarLabirinto();
		labirinto.displayLabirinto();
		
		int jogadas = 1;
		while(heroi.getVida() == true){
			System.out.print("\nJogada " + jogadas);
			System.out.print("\nIntroduza o comando: ");
			Scanner S = new Scanner(System.in);
			
			if(S.hasNext()){
				char coordenada = S.next().charAt(0);
				labirinto.moverHeroi(heroi, dragao, coordenada);
				
				if(t != 1)
					labirinto.moverDragao(dragao, heroi, t);
				
				if(heroi.getVida() == false)
				{
					System.out.println("GAME OVER! \n");
					System.exit(1);
				}
				labirinto.displayLabirinto();
			}
		}
	}

}

