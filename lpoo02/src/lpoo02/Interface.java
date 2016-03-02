package lpoo02;

import java.util.Scanner;

public class Interface {

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
			T = new Scanner(System.in);
		}

		System.out.print("\n");
		
		return t;
	}
	public static void main(String[] args) {
	}
}

