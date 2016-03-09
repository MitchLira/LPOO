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
	char[][] m1 = { { 'X', 'X', 'X', 'X', 'X' },
			{ 'X', '.', '.', 'H', 'S' },
			{ 'X', '.', 'X', '.', 'X' },
			{ 'X', 'E', '.', 'D', 'X' },
			{ 'X', 'X', 'X', 'X', 'X' } };
	
	char[][] m2 = { { 'X', 'X', 'X', 'X', 'X' },
				{ 'X', 'H', '.', '.', 'S' },
				{ 'X', '.', 'X', '.', 'X' },
				{ 'X', 'E', '.', 'D', 'X' },
				{ 'X', 'X', 'X', 'X', 'X' } };
	
	Interface interf = new Interface();
	Labirinto maze = new Labirinto();
	Heroi hero = new Heroi(3, 1);
	Dragao dragao = new Dragao(3, 3);
	Espada esp = new Espada(1, 3);

	// alinea a
	@Test
	public void testMoverheroCelulalivre() {
		maze.criarLabirinto(m1, hero, dragao, esp);
		maze.moverHeroi(hero, dragao, 's', interf);
		assertEquals(new Point(3, 2), hero.getHeroiPosicao());
		maze.moverHeroi(hero, dragao, 'n', interf);
		assertEquals(new Point(3, 1), hero.getHeroiPosicao());
		maze.moverHeroi(hero, dragao, 'o', interf);
		assertEquals(new Point(2, 1), hero.getHeroiPosicao());
		maze.moverHeroi(hero, dragao, 'e', interf);
		assertEquals(new Point(3, 1), hero.getHeroiPosicao());

	}

	// alinea b
	@Test
	public void testMoverheroParede() {
		maze.criarLabirinto(m1, hero, dragao, esp);
		maze.moverHeroi(hero, dragao, 'n', interf);
		assertEquals(new Point(3, 1), hero.getHeroiPosicao());
		maze.moverHeroi(hero, dragao, 'e', interf);
		assertEquals(new Point(3, 1), hero.getHeroiPosicao());
		maze.moverHeroi(hero, dragao, 's', interf);
		maze.moverHeroi(hero, dragao, 'o', interf);
		assertEquals(new Point(3, 2), hero.getHeroiPosicao());
		maze.moverHeroi(hero, dragao, 'e', interf);
		assertEquals(new Point(3, 2), hero.getHeroiPosicao());
	}

	// alinea c
	@Test
	public void testMoverheroEspada() {
		maze.criarLabirinto(m1, hero, dragao, esp);
		hero.setLinha(2);
		hero.setColuna(1);
		maze.moverHeroi(hero, dragao, 's', interf);
		assertEquals(new Point(1, 3), hero.getHeroiPosicao());
		assertEquals(true, hero.getArma());

	}

	// alinea d
	@Test
	public void testMoverheroDragaosemArma() {
		maze.criarLabirinto(m1, hero, dragao, esp);
		maze.moverHeroi(hero, dragao, 's', interf);
		maze.HeroivsDragao();
		assertEquals(false, hero.getVida());
	}

	// alinea e
	@Test
	public void testMoverheroDragacomArma() {
		maze.criarLabirinto(m1, hero, dragao, esp);
		hero.ativaArma();
		maze.moverHeroi(hero, dragao, 's', interf);
		maze.HeroivsDragao();
		assertEquals(true, hero.getVida());
		assertEquals(false, dragao.getVida());
	}

	// alinea f
	@Test
	public void testheroVitoria() {
		maze.criarLabirinto(m1, hero, dragao, esp);
		hero.ativaArma();
		maze.moverHeroi(hero, dragao, 's', interf);
		maze.HeroivsDragao();
		hero.setPosicaoHeroi(1, 3);
		maze.moverHeroi(hero, dragao, 'e', interf);
		assertEquals(new Point(4,1), hero.getHeroiPosicao());
	}
	// alinea g
	@Test
	public void testheroVitoriaSemSucesso() {
		maze.criarLabirinto(m1, hero, dragao, esp);
		maze.moverHeroi(hero, dragao, 'e', interf);
		assertEquals(new Point(3,1), hero.getHeroiPosicao());
		assertEquals(false, hero.getVitoria());
	}
	// alinea g
		@Test
		public void testheroVitoriaSemSucessoDragaoVivo() {
			maze.criarLabirinto(m1, hero, dragao, esp);
			hero.ativaArma();
			maze.moverHeroi(hero, dragao, 'e', interf);
			assertEquals(new Point(3,1), hero.getHeroiPosicao());
			assertEquals(false, hero.getVitoria());
		}
		
		@Test
		public void TestheroDefault(){
			Heroi heroi = new Heroi();
			Dragao drag = new Dragao();
			
			maze.criarLabirinto();
			assertEquals(new Point(1,1), heroi.getHeroiPosicao());
			
			drag.setSimbolo();
			assertEquals('D', drag.getSimbolo());
			
			maze.moverHeroi(hero, drag, 'n', interf);
//			drag.moveDragaoNorte();
//			assertEquals(new Point(1,2), drag.getDragaoPosicao());
//			drag.DragaoMorre();
//			assertEquals(false, drag.getVida());
//			drag.moveDragaoSul();
//			assertEquals(new Point(1,3), drag.getDragaoPosicao());
//			drag.moveDragaoDireita();
//			assertEquals(new Point(2,3), drag.getDragaoPosicao());
//			drag.moveDragaoEsquerda();
//			assertEquals(new Point(1,3), drag.getDragaoPosicao());
		}
}
