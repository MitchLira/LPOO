package lpoo02;

import static org.junit.Assert.*;
import org.junit.Test;

public class LabirintoTest {
	char[][] m1 = { { 'X', 'X', 'X', 'X', 'X' }, { 'X', ' ', ' ', 'H', 'S' },
			{ 'X', ' ', 'X', ' ', 'X' }, { 'X', 'E', ' ', 'D', 'X' },
			{ 'X', 'X', 'X', 'X', 'X' } };
	Heroi heroi = new Heroi();
	Dragao dragao = new Dragao();

	@Test
	public void testMoverDragao() {
		Labirinto maze = new Labirinto();
		maze.criarLabirinto(m1);
		maze.moverDragao(dragao, heroi, 1);
		dragao.mantemPosicaoDragao();
	}

	@Test
	public void testMoverHeroi() {
		fail("Not yet implemented");
	}

}
