package lpoo02;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestModoBasico {
	char[][] m1 = { { 'X', 'X', 'X', 'X', 'X' }, 
					{ 'X', ' ', ' ', 'H', 'S' },
					{ 'X', ' ', 'X', ' ', 'X' }, 
					{ 'X', 'E', ' ', 'D', 'X' },
					{ 'X', 'X', 'X', 'X', 'X' } 
				};
	
	public static class Point {		
		private int x, y;
		
		public int getX() {
			return x;
		}
		
		public int getY() {
			return y;
		}
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public boolean adjacentTo(Point p) {
			return Math.abs(p.x - this.x) + Math.abs(p.y - this.y) == 1;
		}
	}
	
	Heroi heroi = new Heroi();
	Dragao dragao = new Dragao();

	@Test
	public void testMoverDragao() {
		Labirinto maze = new Labirinto();
		maze.criarLabirinto(m1);
		//maze.moverDragao(dragao, heroi, 1);
		assertEquals(new Point(1, 3), new Point(dragao.getLinha(),dragao.getColuna()));
	}

	@Test
	public void testMoverHeroi() {
		fail("Not yet implemented");
	}

}
