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
	
	Heroi heroi = new Heroi();
	Dragao dragao = new Dragao();

	@Test
	public void testMoverHeroi() {
		Labirinto maze = new Labirinto();
		maze.criarLabirinto(m1);
		maze.moverHeroi(heroi, dragao, 's');
		assertEquals(new Point(1, 3), heroi.getHeroiPosicao());
	}

	@Test
	public void testMoverDragao() {
		fail("Not yet implemented");
	}

}
