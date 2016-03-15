package maze.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

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
	ArrayList<Dragao> dragoes = new ArrayList<Dragao>();
	Espada esp = new Espada(1, 3);

	// alinea a
	@Test
	public void testMoverheroCelulalivre() {
		dragoes.add(dragao);
		maze.criarLabirinto(m1, hero, dragoes, esp);
		maze.moverHeroi(hero, 's', interf);
		assertEquals(new Point(3, 2), hero.getHeroiPosicao());
		maze.moverHeroi(hero, 'n', interf);
		assertEquals(new Point(3, 1), hero.getHeroiPosicao());
		maze.moverHeroi(hero,  'o', interf);
		assertEquals(new Point(2, 1), hero.getHeroiPosicao());
		maze.moverHeroi(hero, 'e', interf);
		assertEquals(new Point(3, 1), hero.getHeroiPosicao());

	}

	// alinea b
	@Test
	public void testMoverheroParede() {
		dragoes.add(dragao);
		maze.criarLabirinto(m1, hero, dragoes, esp);
		maze.moverHeroi(hero,  'n', interf);
		assertEquals(new Point(3, 1), hero.getHeroiPosicao());
		maze.moverHeroi(hero, 'e', interf);
		assertEquals(new Point(3, 1), hero.getHeroiPosicao());
		maze.moverHeroi(hero,  's', interf);
		maze.moverHeroi(hero, 'o', interf);
		assertEquals(new Point(3, 2), hero.getHeroiPosicao());
		maze.moverHeroi(hero, 'e', interf);
		assertEquals(new Point(3, 2), hero.getHeroiPosicao());
	}

	// alinea c
	@Test
	public void testMoverheroEspada() {
		dragoes.add(dragao);
		maze.criarLabirinto(m1, hero, dragoes, esp);
		hero.setLinha(2);
		hero.setColuna(1);
		maze.moverHeroi(hero, 's', interf);
		assertEquals(new Point(1, 3), hero.getHeroiPosicao());
		assertEquals(true, hero.getArma());

	}

	// alinea d
	@Test
	public void testMoverheroDragaosemArma() {
		dragoes.add(dragao);
		maze.criarLabirinto(m1, hero, dragoes, esp);
		maze.moverHeroi(hero, 's', interf);
		maze.HeroivsDragao(dragao);
		assertEquals(false, hero.getVida());
	}

	// alinea e
	@Test
	public void testMoverheroDragacomArma() {
		dragoes.add(dragao);
		maze.criarLabirinto(m1, hero, dragoes, esp);
		hero.ativaArma();
		maze.moverHeroi(hero, 's', interf);
		maze.HeroivsDragao(dragao);
		assertEquals(true, hero.getVida());
		assertEquals(false, dragao.getVida());
	}

	// alinea f
	@Test
	public void testheroVitoria() {
		dragoes.add(dragao);
		maze.criarLabirinto(m1, hero, dragoes, esp);
		hero.ativaArma();
		maze.moverHeroi(hero, 's', interf);
		maze.HeroivsDragao(dragao);
		hero.setPosicaoHeroi(1, 3);
		maze.moverHeroi(hero, 'e', interf);
		assertEquals(new Point(4,1), hero.getHeroiPosicao());
	}
	// alinea g
	@Test
	public void testheroVitoriaSemSucesso() {
		dragoes.add(dragao);
		maze.criarLabirinto(m1, hero, dragoes, esp);
		maze.moverHeroi(hero, 'e', interf);
		assertEquals(new Point(3,1), hero.getHeroiPosicao());
		assertEquals(false, hero.getVitoria());
	}
	// alinea g
		@Test
		public void testheroVitoriaSemSucessoDragaoVivo() {
			dragoes.add(dragao);
			maze.criarLabirinto(m1, hero, dragoes, esp);
			hero.ativaArma();
			maze.moverHeroi(hero, 'e', interf);
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
			
			maze.moverHeroi(hero,  'n', interf);
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
