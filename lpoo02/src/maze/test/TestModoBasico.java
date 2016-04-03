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

	char[][] m3 = { { 'X', 'X', 'X', 'S', 'X' },
			{ 'X', '.', '.', 'H', 'X' },
			{ 'X', '.', 'X', '.', 'X' },
			{ 'X', 'E', '.', 'D', 'X' },
			{ 'X', 'X', 'X', 'X', 'X' } };
	char[][] m4 = { { 'X', 'X', 'X', 'X', 'X' },
			{ 'X', '.', '.', 'H', 'X' },
			{ 'X', '.', 'X', '.', 'X' },
			{ 'X', 'E', '.', 'D', 'X' },
			{ 'X', 'X', 'S', 'X', 'X' } };
	
	char[][] m5 = { { 'X', 'X', 'X', 'X', 'X' },
			{ 'X', '.', '.', 'H', 'X' },
			{ 'X', '.', 'X', '.', 'X' },
			{ 'S', '.', 'E', 'D', 'X' },
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
		assertEquals('H',hero.getSimbolo());
		assertEquals(new Point(2, 3), hero.getHeroiPosicao());
		maze.moverHeroi(hero, 'n');
		assertEquals('H',hero.getSimbolo());
		assertEquals(new Point(1, 3), hero.getHeroiPosicao());
		maze.moverHeroi(hero,  'o');
		assertEquals('H',hero.getSimbolo());
		assertEquals(new Point(1, 2), hero.getHeroiPosicao());
		maze.moverHeroi(hero, 'e');
		assertEquals('H',hero.getSimbolo());
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
		assertEquals('H',hero.getSimbolo());
		maze.moverHeroi(hero,  's');
		assertEquals('H',hero.getSimbolo());
		maze.moverHeroi(hero, 'o');
		assertEquals('H',hero.getSimbolo());
		assertEquals(new Point(2, 3), hero.getHeroiPosicao());
		maze.moverHeroi(hero, 'e');
		assertEquals('H',hero.getSimbolo());
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
		assertEquals('A',hero.getSimbolo());
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
		maze.criarLabirinto();
		Heroi heroi = new Heroi();
		Espada esp = new Espada(6,1);
		assertEquals(new Point(1,1), heroi.getHeroiPosicao());
		assertEquals(new Point(6,1), esp.getEspPos());
		esp.setEspada();
		assertEquals(new Point(0,0), esp.getEspPos());

		Dragao drag = maze.getDragoes().get(0);

		drag.setSimbolo();
		assertEquals('D', drag.getSimbolo());

		assertEquals(new Point(1,1), heroi.getHeroiPosicao());
		heroi.moveHeroiSul();
		assertEquals(new Point(2,1), heroi.getHeroiPosicao());
		heroi.moveHeroiNorte();
		assertEquals(new Point(1,1), heroi.getHeroiPosicao());
		assertEquals('H', heroi.getSimbolo());
		assertEquals(new Point(1,8),drag.getDragaoPosicao());
		assertEquals('S', maze.getSimboloPosicao(5, 9));


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


	@Test
	public void TestDragao()
	{
		hero = new Heroi(1,1);
		dragoes.add(dragao);
		maze.criarLabirinto(m2, hero, dragoes, esp);

		Dragao d = dragoes.get(0);
		d.DragaoDorme();
		assertEquals('d', d.getSimbolo());

		d.DragaoAcorda();
		assertEquals('D',d.getSimbolo());
		d.DragEsp();
		assertEquals('F', d.getSimbolo());

		d.setPosicaoDragao(2, 1);
		assertEquals(new Point(2,1), d.getDragaoPosicao());

		d.setLinha(3);
		assertEquals(3, d.getLinha());
		d.setColuna(2);
		assertEquals(2, d.getColuna());

		d.mantemPosicaoDragao();
		assertEquals(new Point(3,2), d.getDragaoPosicao());

		d.moveDragaoNorte();
		assertEquals(new Point(2,2), d.getDragaoPosicao());
		d.moveDragaoSul();
		assertEquals(new Point(3,2), d.getDragaoPosicao());
		d.moveDragaoDireita();
		assertEquals(new Point(3,3), d.getDragaoPosicao());
		d.moveDragaoEsquerda();
		assertEquals(new Point(3,2), d.getDragaoPosicao());

		Dragao drag = new Dragao();
		assertEquals(1,drag.getLinha());
	}

	@Test
	public void TestLabDefault()
	{
		maze.criarLabirinto();
		hero = new Heroi(1,1);
		esp = new Espada(6,1);
		dragao = new Dragao(1,8);
		dragoes.add(dragao);

		assertEquals(10,maze.getDimensao());

		hero.setPosicaoHeroi(7, 1);
		maze.moverHeroi(hero, 'n');
		assertEquals(6, hero.getLinha());
		hero.setPosicaoHeroi(5, 1);
		maze.moverHeroi(hero, 's');
		assertEquals(5, hero.getLinha());
		hero.setPosicaoHeroi(6, 2);
		maze.moverHeroi(hero, 'o');
		assertEquals(2, hero.getColuna());

		assertFalse(maze.lutaPossivel(dragao));
		dragao.setPosicaoDragao(4, 8);
		hero.setPosicaoHeroi(3, 8);
		maze.HeroivsDragao(dragao);

	}

	@Test
	public void TestModoJogoToInt()
	{
		assertEquals(1,maze.modoJogotoInt("Basico"));
		assertEquals(2,maze.modoJogotoInt("Aleatorio"));
		assertEquals(3,maze.modoJogotoInt("Adormecido"));
		assertEquals(-1,maze.modoJogotoInt("Basi"));
	}
	
	@Test public void TestLab3()
	{
		dragoes.add(dragao);
		maze.criarLabirinto(m3, hero, dragoes, esp);
		hero.ativaArma();
		dragoes.get(0).DragaoMorre();
		maze.moverHeroi(hero, 'n');
		assertTrue(hero.getVitoria());
		
	}
	
	@Test public void TestLab4()
	{
		dragoes.add(dragao);
		maze.criarLabirinto(m4, hero, dragoes, esp);
		dragoes.get(0).DragaoMorre();
		hero.setPosicaoHeroi(3, 2);
		maze.moverHeroi(hero, 'o');
		maze.moverHeroi(hero, 'e');
		maze.moverHeroi(hero, 's');
		assertTrue(hero.getVitoria());
		
	}
	
	@Test public void TestLab5()
	{
		dragoes.add(dragao);
		maze.criarLabirinto(m5, hero, dragoes, esp);
		dragoes.get(0).DragaoMorre();
		hero.setPosicaoHeroi(3, 1);
		maze.moverHeroi(hero, 'e');
		maze.moverHeroi(hero, 'o');
		maze.moverHeroi(hero, 'o');
		assertTrue(hero.getVitoria());
		
	}


}
