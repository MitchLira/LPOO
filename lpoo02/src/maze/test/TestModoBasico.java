package maze.test;

import static org.junit.Assert.*;

import org.junit.Test;

import maze.cli.Interface;
import maze.logic.Dragao;
import maze.logic.Heroi;
import maze.logic.Labirinto;
import maze.logic.Point;
import maze.logic.Espada;

public class TestModoBasico {
	char[][] m1 = { { 'X', 'X', 'X', 'X', 'X' }, { 'X', '.', '.', 'H', 'S' },
			{ 'X', '.', 'X', '.', 'X' }, { 'X', 'E', '.', 'D', 'X' },
			{ 'X', 'X', 'X', 'X', 'X' } };
	Interface interf = new Interface();
	Labirinto maze = new Labirinto();
	Heroi heroi = new Heroi(3, 1);
	Dragao dragao = new Dragao(3, 3);
	Espada esp = new Espada(1, 3);

	// alinea a
	@Test
	public void testMoverHeroiCelulalivre() {
		maze.criarLabirinto(m1, heroi, dragao, esp);
		maze.moverHeroi(heroi, dragao, 's', interf);
		assertEquals(new Point(3, 2), heroi.getHeroiPosicao());
		maze.moverHeroi(heroi, dragao, 'n', interf);
		assertEquals(new Point(3, 1), heroi.getHeroiPosicao());
		maze.moverHeroi(heroi, dragao, 'o', interf);
		assertEquals(new Point(2, 1), heroi.getHeroiPosicao());
		maze.moverHeroi(heroi, dragao, 'e', interf);
		assertEquals(new Point(3, 1), heroi.getHeroiPosicao());

	}

	// alinea b
	@Test
	public void testMoverHeroiParede() {
		maze.criarLabirinto(m1, heroi, dragao, esp);
		maze.moverHeroi(heroi, dragao, 'n', interf);
		assertEquals(new Point(3, 1), heroi.getHeroiPosicao());
		maze.moverHeroi(heroi, dragao, 'e', interf);
		assertEquals(new Point(3, 1), heroi.getHeroiPosicao());
		maze.moverHeroi(heroi, dragao, 's', interf);
		maze.moverHeroi(heroi, dragao, 'o', interf);
		assertEquals(new Point(3, 2), heroi.getHeroiPosicao());
		maze.moverHeroi(heroi, dragao, 'e', interf);
		assertEquals(new Point(3, 2), heroi.getHeroiPosicao());
	}

	// alinea c
	@Test
	public void testMoverHeroiEspada() {
		maze.criarLabirinto(m1, heroi, dragao, esp);
		heroi.setLinha(2);
		heroi.setColuna(1);
		maze.moverHeroi(heroi, dragao, 's', interf);
		assertEquals(new Point(1, 3), heroi.getHeroiPosicao());
		assertEquals(true, heroi.getArma());

	}

	// alinea d
	@Test
	public void testMoverHeroiDragaosemArma() {
		maze.criarLabirinto(m1, heroi, dragao, esp);
		maze.moverHeroi(heroi, dragao, 's', interf);
		maze.HeroivsDragao();
		assertEquals(false, heroi.getVida());
	}

	// alinea e
	@Test
	public void testMoverHeroiDragacomArma() {
		maze.criarLabirinto(m1, heroi, dragao, esp);
		heroi.ativaArma();
		maze.moverHeroi(heroi, dragao, 's', interf);
		maze.HeroivsDragao();
		assertEquals(true, heroi.getVida());
		assertEquals(false, dragao.getVida());
	}

	// alinea f
	@Test
	public void testHeroiVitoria() {
		maze.criarLabirinto(m1, heroi, dragao, esp);
		heroi.ativaArma();
		maze.moverHeroi(heroi, dragao, 's', interf);
		maze.HeroivsDragao();
		heroi.mudaPosicaoHeroi(1, 3);
		maze.moverHeroi(heroi, dragao, 'e', interf);
		assertEquals(new Point(4,1), heroi.getHeroiPosicao());
	}
	// alinea g
	@Test
	public void testHeroiVitoriaSemSucesso() {
		maze.criarLabirinto(m1, heroi, dragao, esp);
		maze.moverHeroi(heroi, dragao, 'e', interf);
		assertEquals(new Point(3,1), heroi.getHeroiPosicao());
		assertEquals(false, heroi.getVitoria());
	}
	// alinea g
		@Test
		public void testHeroiVitoriaSemSucessoDragaoVivo() {
			maze.criarLabirinto(m1, heroi, dragao, esp);
			heroi.ativaArma();
			maze.moverHeroi(heroi, dragao, 'e', interf);
			assertEquals(new Point(3,1), heroi.getHeroiPosicao());
			assertEquals(false, heroi.getVitoria());
		}
}
