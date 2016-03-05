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
	
	Heroi heroi = new Heroi(3,1);
	Dragao dragao = new Dragao(3,3);

	@Test
	public void testMoverHeroi() {
		Labirinto maze = new Labirinto();
		maze.criarLabirinto(m1);
		maze.moverHeroi(heroi, dragao, 's');
		assertEquals(new Point(3,2), heroi.getHeroiPosicao());
		maze.moverHeroi(heroi, dragao, 'n');
		assertEquals(new Point(3,1), heroi.getHeroiPosicao());
		maze.moverHeroi(heroi, dragao, 'e');
		assertEquals(new Point(3,1), heroi.getHeroiPosicao());
		maze.moverHeroi(heroi, dragao, 'o');
		assertEquals(new Point(2,1), heroi.getHeroiPosicao());
		maze.moverHeroi(heroi, dragao, 'e');
		assertEquals(new Point(3,1), heroi.getHeroiPosicao());
		heroi.ativaArma();
		}

	@Test
	public void testMoverDragao() {
		Labirinto maze = new Labirinto();
		maze.criarLabirinto(m1);
		maze.moverDragao(dragao, heroi, 0);
		Point p1 = new Point(1,3);
		assertEquals(p1, dragao.getDragaoPosicao());
	}

}
