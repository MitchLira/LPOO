package maze.test;

import static org.junit.Assert.*;
import org.junit.Test;

import maze.logic.Dragao;
import maze.logic.Heroi;
import maze.logic.Labirinto;
import maze.logic.Point;

public class TestModoBasico {
	char[][] m1 = { { 'X', 'X', 'X', 'X', 'X' }, 
					{ 'X', '.', '.', 'H', 'S' },
					{ 'X', '.', 'X', '.', 'X' }, 
					{ 'X', 'E', '.', 'D', 'X' },
					{ 'X', 'X', 'X', 'X', 'X' } 
				};
	
	Labirinto maze = new Labirinto();
	Heroi heroi = new Heroi(3,1);
	Dragao dragao = new Dragao(3,3);

	//alinea a
	@Test
	public void testMoverHeroiCelulalivre() {
		maze.criarLabirinto(m1);
		maze.moverHeroi(heroi, dragao, 's');
		assertEquals(new Point(3,2), heroi.getHeroiPosicao());
		maze.moverHeroi(heroi, dragao, 'n');
		assertEquals(new Point(3,1), heroi.getHeroiPosicao());
		maze.moverHeroi(heroi, dragao, 'o');
		assertEquals(new Point(2,1), heroi.getHeroiPosicao());	

		}
	//alinea b
	@Test
	public void testMoverHeroiParede() {
		maze.criarLabirinto(m1);
		maze.moverHeroi(heroi, dragao, 'e');
		assertEquals(new Point(3,1), heroi.getHeroiPosicao());
	}
	
	//alinea c
	@Test
	public void testMoverHeroiEspada() {	
		maze.criarLabirinto(m1);
		heroi.setLinha(2);
		heroi.setColuna(1);
		maze.moverHeroi(heroi, dragao, 's');
		assertEquals(new Point(1,3), heroi.getHeroiPosicao());
		assertEquals(true, heroi.getArma());
	}
	
	//alinea d
	@Test
	public void testMoverHeroiDragaosemArma() {	
		maze.criarLabirinto(m1);
		maze.moverHeroi(heroi, dragao, 's');
		maze.moverHeroi(heroi, dragao, 's');
		assertEquals(false, heroi.getArma());
		assertEquals(false, heroi.getVida());
	}
}
