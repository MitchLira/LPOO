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
	Heroi hero = new Heroi(1, 3);
	Dragao dragao = new Dragao(3, 3);
	ArrayList<Dragao> dragoes = new ArrayList<Dragao>();
	Espada esp = new Espada(1, 3);

	// alinea a
	@Test
	public void testMoverheroCelulalivre() {
		dragoes.add(dragao);
		maze.criarLabirinto(m1, hero, dragoes, esp);
		maze.moverHeroi(hero, 's');
		assertEquals(new Point(2, 3), hero.getHeroiPosicao());
		maze.moverHeroi(hero, 'n');
		assertEquals(new Point(1, 3), hero.getHeroiPosicao());
		maze.moverHeroi(hero,  'o');
		assertEquals(new Point(1, 2), hero.getHeroiPosicao());
		maze.moverHeroi(hero, 'e');
		assertEquals(new Point(1, 3), hero.getHeroiPosicao());

	}

	// alinea b
	@Test
	public void testMoverheroParede() {
		dragoes.add(dragao);
		maze.criarLabirinto(m1, hero, dragoes, esp);
		maze.moverHeroi(hero,  'n');
		assertEquals(new Point(1, 3), hero.getHeroiPosicao());
		maze.moverHeroi(hero, 'e');
		assertEquals(new Point(1, 3), hero.getHeroiPosicao());
		maze.moverHeroi(hero,  's');
		maze.moverHeroi(hero, 'o');
		assertEquals(new Point(2, 3), hero.getHeroiPosicao());
		maze.moverHeroi(hero, 'e');
		assertEquals(new Point(2, 3), hero.getHeroiPosicao());
	}

	// alinea c
	@Test
	public void testMoverheroEspada() {
		dragoes.add(dragao);
		maze.criarLabirinto(m1, hero, dragoes, esp);
		hero.setLinha(2);
		hero.setColuna(1);
		maze.moverHeroi(hero, 's');
		assertEquals(new Point(3, 1), hero.getHeroiPosicao());
		assertEquals(true, hero.getArma());

	}

	// alinea d
	@Test
	public void testMoverheroDragaosemArma() {
		dragoes.add(dragao);
		maze.criarLabirinto(m1, hero, dragoes, esp);
		maze.moverHeroi(hero, 's');
		maze.HeroivsDragao(dragao);
		assertEquals(false, hero.getVida());
	}

	// alinea e
	@Test
	public void testMoverheroDragacomArma() {
		dragoes.add(dragao);
		maze.criarLabirinto(m1, hero, dragoes, esp);
		hero.ativaArma();
		maze.moverHeroi(hero, 's');
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
		maze.moverHeroi(hero, 's');
		maze.HeroivsDragao(dragao);
		hero.setPosicaoHeroi(1, 3);
		maze.moverHeroi(hero, 'e');
		assertEquals(new Point(1,4), hero.getHeroiPosicao());
	}
	// alinea g
	@Test
	public void testheroVitoriaSemSucesso() {
		dragoes.add(dragao);
		maze.criarLabirinto(m1, hero, dragoes, esp);
		maze.moverHeroi(hero, 'e');
		assertEquals(new Point(1,3), hero.getHeroiPosicao());
		assertEquals(false, hero.getVitoria());
	}
	// alinea g
		@Test
		public void testheroVitoriaSemSucessoDragaoVivo() {
			dragoes.add(dragao);
			maze.criarLabirinto(m1, hero, dragoes, esp);
			hero.ativaArma();
			maze.moverHeroi(hero, 'e');
			assertEquals(new Point(1,3), hero.getHeroiPosicao());
			assertEquals(false, hero.getVitoria());
		}
		
		@Test
		public void TestCarateresDefault(){
			Heroi heroi = new Heroi();
			
			Espada esp = new Espada(6,1);
			
			maze.criarLabirinto();
			assertEquals(new Point(1,1), heroi.getHeroiPosicao());
			
			Dragao drag = maze.getDragoes().get(0);
			
			drag.setSimbolo();
			assertEquals('D', drag.getSimbolo());
			
			maze.moverHeroi(hero,  'n');


//			drag.DragaoMorre();
//			assertEquals(false, drag.getVida());
//			drag.moveDragaoSul();
//			assertEquals(new Point(1,3), drag.getDragaoPosicao());
//			drag.moveDragaoDireita();
//			assertEquals(new Point(2,3), drag.getDragaoPosicao());
//			drag.moveDragaoEsquerda();
//			assertEquals(new Point(1,3), drag.getDragaoPosicao());
//			drag.mantemPosicaoDragao();
//			assertEquals(new Point(1,3), drag.getDragaoPosicao());
//			drag.DragaoDorme();
//			assertEquals('d', drag.getSimbolo());
//			drag.DragaoAcorda();
//			assertEquals('D', drag.getSimbolo());
//			drag.DragEsp();
//			assertEquals('F', drag.getSimbolo());
//			drag.setPosicaoDragao(1, 1);
//			assertEquals(new Point(1,1), drag.getDragaoPosicao());
//			
//			
//			assertEquals(new Point(6,1), esp.getEspPos());
//			esp.setEspada();
//			assertEquals(new Point(0,0), esp.getEspPos());
		}
		@Test
		public void TestPontos()
		{
			Point p = new Point(0,0);
			assertEquals(true, p.adjacentTo(new Point(0,1)));
			assertEquals(true, p.adjacentTo(new Point(0,-1)));
			assertTrue(p.adjacentTo(new Point(1,0)));
			assertTrue(p.adjacentTo(new Point(-1,0)));
			assertFalse(p.adjacentTo(new Point(1,1)));
			assertTrue(p.iguais(new Point(0,0)));
			assertFalse(p.iguais(new Point(0,1)));
			assertFalse(p.iguais(new Point(1,1)));
			assertFalse(p.iguais(new Point(1,0)));
			
			assertEquals(true, p.equals(new Point(0,0)));
			assertFalse(p.equals(new Point(0,1)));
			assertFalse(p.equals(new Point(1,1)));
			assertFalse(p.equals(new Point(1,0)));
	
			Point impar = new Point(1,1);
			Point par = new Point(4,2);
			Point ip = new Point(3,2);
			Point ip1 = new Point(2,3);
			assertTrue(impar.temCoordImpares());
			assertFalse(p.temCoordImpares());
			assertFalse(par.temCoordImpares());
			assertFalse(ip.temCoordImpares());
			assertFalse(ip1.temCoordImpares());
			Heroi  h= new Heroi();
			assertFalse(p.equals(h));
		}
		
}
