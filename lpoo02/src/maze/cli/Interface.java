/**  
* Interface.java - classe da interface do labirinto  
* @author  Miguel Lira e Miriam Goncalves 
*/

package maze.cli;

import java.util.Scanner;

import maze.logic.Labirinto;

// TODO: Auto-generated Javadoc
/**
 * The Class Interface.
 */
public class Interface {
	
	//Faz os prints para escolher o modo de jogo
	/**
	 * Funcao que permite fazer display para a consola e receber o modo de jogo .
	 *
	 * @return t que indica o modo de jogo
	 */
	public int modojogo()
	{
		System.out.println("Niveis de jogo: ");
		System.out.println("1 - Dragao parado");
		System.out.println("2 - Dragao com movimento aleatorio");
		System.out.println("3 - Dragao com movimento aleatorio intercalado com dormir");
		System.out.print("Indique um nivel de jogo: ");

		Scanner T = new Scanner(System.in);
		int t = T.nextInt();

		while(t != 1 && t != 2 && t != 3){
			System.out.print("Opcao invalida!");
			System.out.print("\nIndique um nivel de jogo: ");
			T.nextLine();
			t = T.nextInt();
		}
		T.nextLine();
		
		System.out.print("\n");
		return t;
	}
	
	//Faz o print de vencedor
	/**
	 * Funcao que faz display da mensagem de vitoria.
	 */
	public void venceu(){
		System.out.println("WINNER!");
	}
	
	//Faz o print de perdedor
	/**
	 * Funcao que faz display da mensagem de derrota.
	 */
	public void perdeu(){
		System.out.println("GAME OVER!");
	}
	
	//Faz os prints de cada jogada
	/**
	 * Funcao que faz display do numero de jogada a decorrer e recebe a direcao para onde quer mover o heroi.
	 *
	 * @param jogada inteiro que indica a jogada
	 * @return S direcao que indica para onde quer mover o heroi
	 */
	public Scanner fazJogada(int jogada){				
		System.out.print("\nJogada " + jogada);
		System.out.print("\nIntroduza comando: ");
		Scanner S = new Scanner(System.in);
		
		return S;
	}
	
	//Faz prints de heroi atacado
	/**
	 * Funcao que faz display do heroi atacado sem arma.
	 */
	public void heroiAtacado(){
		System.out.println("Heroi sem arma atacado!");
	}
	
	//Faz display do labirinto
	/**
	 * Funcao que faz display do labirinto para a consola.
	 *
	 * @param lab labirinto
	 */
	public void displayLabirinto(Labirinto lab){
		System.out.print(lab);
	}
	
	//tamanho labirinto
	/**
	 * Funcao que permite receber o tamanho do labirinto requerido pelo utilizador.
	 *
	 * @return t inteiro que indica o tamanho do labirinto
	 */
	public int tamanhoLabirinto()
	{
		System.out.println("Introduza o tamanho do labirinto: ");
		Scanner T = new Scanner(System.in);
		int t = T.nextInt();

		while(t < 9){
			System.out.print("Opcao invalida!");
			System.out.print("\nIndique tamanho do labirinto superior a 6! ");
			System.out.println("Introduza o tamanho do labirinto: ");
			T.nextLine();
			t = T.nextInt();
		}
		T.nextLine();
		
		System.out.print("\n");
		return t;
		
	}
	
	//numero de dragoes
	/**
	 * Funcao que permite receber o numero de dragoes que o utilizador quer no labirinto.
	 *
	 * @return t inteiro que indica o numero de dragoes
	 */
	public int nrDragoes()
	{
		System.out.println("Introduza o numero de dragoes: ");
		Scanner T = new Scanner(System.in);
		int t = T.nextInt();

		while(t <= 0){
			System.out.print("Opcao invalida!");
			System.out.print("\nIndique um numero superior a 0! ");
			System.out.println("Introduza o numero de dragoes: ");
			T.nextLine();
			t = T.nextInt();
		}
		T.nextLine();
		
		System.out.print("\n");
		return t;
	}
	
	/**
	 * Funcao que permite receber se o utilizador quer jogar na consola ou na parte grafica.
	 *
	 * @return m inteiro que indica o modo de jogo
	 */
	public int nrModo(){
		int m;
		
		System.out.println("Modo de jogo: ");
		System.out.println("1 - Grafica");
		System.out.println("2 - Consola");
		System.out.print("Escolha uma opcao: ");
		Scanner T = new Scanner(System.in);
		m = T.nextInt();
		
		while(m != 1 && m != 2){
			System.out.println("Opcao invalida!");
			System.out.print("Escolha opcao valida:  ");
			T.nextLine();
			m = T.nextInt();
		}
		T.nextLine();
		System.out.println("");
		
		return m;
	}
}

