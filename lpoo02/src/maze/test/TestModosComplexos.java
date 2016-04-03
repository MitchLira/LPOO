/*
 * 
 */
package maze.test;

import static org.junit.Assert.*;

import org.junit.Test;

import maze.cli.Interface;
import maze.logic.Dragao;
import maze.logic.Espada;
import maze.logic.Heroi;
import maze.logic.Labirinto;
import maze.logic.Point;

// TODO: Auto-generated Javadoc
/**
 * The Class TestModosComplexos.
 */
public class TestModosComplexos {
	
	/** The m1. */
	char[][] m1 = { { 'X', 'X', 'X', 'X', 'X' }, { 'X', '.', '.', 'H', 'S' },
			{ 'X', '.', 'X', '.', 'X' }, { 'X', 'E', '.', 'D', 'X' },
			{ 'X', 'X', 'X', 'X', 'X' } };
	
	/** The interf. */
	Interface interf = new Interface();
	
	/** The maze. */
	Labirinto maze = new Labirinto();
	
	/** The heroi. */
	Heroi heroi = new Heroi(3, 1);
	
	/** The dragao. */
	Dragao dragao = new Dragao(3, 3);
	
	/** The esp. */
	Espada esp = new Espada(1, 3);
	
	/**
	 * Test tamanho labirinto.
	 */
	@Test
	public void testTamanhoLabirinto() {
		maze.GerarLabirinto(11);
		
	}


}
