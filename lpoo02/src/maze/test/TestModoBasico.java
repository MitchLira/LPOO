/*
 * 
 */
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

// TODO: Auto-generated Javadoc
/**
 * The Class TestModoBasico.
 */
public class TestModoBasico {
	
	/** The m1. */
	char[][] m1 = { { 'X', 'X', 'X', 'X', 'X' },
			{ 'X', '.', '.', 'H', 'S' },
			{ 'X', '.', 'X', '.', 'X' },
			{ 'X', 'E', '.', 'D', 'X' },
			{ 'X', 'X', 'X', 'X', 'X' } };

	/** The m2. */
	char[][] m2 = { { 'X', 'X', 'X', 'X', 'X' },
			{ 'X', 'H', '.', '.', 'S' },
			{ 'X', '.', 'X', '.', 'X' },
			{ 'X', 'E', '.', 'D', 'X' },
			{ 'X', 'X', 'X', 'X', 'X' } };

	/** The m3. */
	char[][] m3 = { { 'X', 'X', 'X', 'S', 'X' },
			{ 'X', '.', '.', 'H', 'X' },
			{ 'X', '.', 'X', '.', 'X' },
			{ 'X', 'E', '.', 'D', 'X' },
			{ 'X', 'X', 'X', 'X', 'X' } };
	
	/** The m4. */
	char[][] m4 = { { 'X', 'X', 'X', 'X', 'X' },
			{ 'X', '.', '.', 'H', 'X' },
			{ 'X', '.', 'X', '.', 'X' },
			{ 'X', 'E', '.', 'D', 'X' },
			{ 'X', 'X', 'S', 'X', 'X' } };
	
	/** The m5. */
	char[][] m5 = { { 'X', 'X', 'X', 'X', 'X' },
			{ 'X', '.', '.', 'H', 'X' },
			{ 'X', '.', 'X', '.', 'X' },
			{ 'S', '.', 'E', 'D', 'X' },
			{ 'X', 'X', 'X', 'X', 'X' } };

	/** The interf. */
	Interface interf = new Interface();
	
	/** The maze. */
	Labirinto maze = new Labirinto();
	
	/** The hero. */
	Heroi hero = new Heroi(1, 3);
	
	/** The dragao. */
	Dragao dragao = new Dragao(3, 3);
	
	/** The dragoes. */
	ArrayList<Dragao> dragoes = new ArrayList<Dragao>();
	
	/** The esp. */
	Espada esp = new Espada(1, 3);
	Espada esp5 = new Espada(3,2);

	/**
	 * Test moverhero celulalivre.
	 */
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

	/**
	 * Test moverhero parede.
	 */
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

	/**
	 * Test moverhero espada.
	 */
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

	/**
	 * Test moverhero dragaosem arma.
	 */
	// alinea d
	@Test
	public void testMoverheroDragaosemArma() {
		dragoes.add(dragao);
		maze.criarLabirinto(m1, hero, dragoes, esp);
		maze.moverHeroi(hero, 's');
		maze.HeroivsDragao(dragao);
		assertEquals(false, hero.getVida());
	}

	/**
	 * Test moverhero dragacom arma.
	 */
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

	/**
	 * Testhero vitoria.
	 */
	// alinea f
	@Test
	public void testheroVitoria() {
		dragoes.add(dragao);
		maze.criarLabirinto(m1, hero, dragoes, esp);
		hero.setLinha(2);
		hero.setColuna(1);
		maze.moverHeroi(hero, 's');
		assertEquals('A',hero.getSimbolo());
		maze.moverHeroi(hero, 'e');
		maze.HeroivsDragao(dragao);
		assertEquals(true, hero.getVida());
		assertEquals(false, dragao.getVida());
		maze.HeroivsDragao(dragao);
		hero.setPosicaoHeroi(1, 3);
		maze.moverHeroi(hero, 'e');
		assertEquals(new Point(1,4), hero.getHeroiPosicao());
	}
	
	/**
	 * Testhero vitoria sem sucesso.
	 */
	// alinea g
	@Test
	public void testheroVitoriaSemSucesso() {
		dragoes.add(dragao);
		maze.criarLabirinto(m1, hero, dragoes, esp);
		maze.moverHeroi(hero, 'e');
		assertEquals(new Point(1,3), hero.getHeroiPosicao());
		assertEquals(false, hero.getVitoria());
	}
	
	/**
	 * Testhero vitoria sem sucesso dragao vivo.
	 */
	// alinea g
	@Test
	public void testHeroArmadoVitoriaSemSucessoDragaoVivo() {
		dragoes.add(dragao);
		maze.criarLabirinto(m1, hero, dragoes, esp);
		hero.ativaArma();
		maze.moverHeroi(hero, 'e');
		assertEquals(new Point(1,3), hero.getHeroiPosicao());
		assertEquals(false, hero.getVitoria());
	}

	/**
	 * Test carateres default.
	 */
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
	
	/**
	 * Test pontos.
	 */
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


	/**
	 * Test dragao.
	 */
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

	/**
	 * Test dragao mover celulas livres.
	 */
	@Test
	public void TestMoverDragaoCelulasLivres()
	{
		dragoes.add(dragao);
		maze.criarLabirinto(m1, hero, dragoes, esp);
		maze.moverDragao(dragao, hero, 2, 0, 0);
		assertEquals(new Point(2,3), dragao.getDragaoPosicao());
		maze.moverDragao(dragao, hero, 2, 1, 0);
		assertEquals(new Point(3,3), dragao.getDragaoPosicao());
		maze.moverDragao(dragao, hero, 2, 3, 0);
		assertEquals(new Point(3,2), dragao.getDragaoPosicao());
		maze.moverDragao(dragao, hero, 2, 2, 0);
		assertEquals(new Point(3,3), dragao.getDragaoPosicao());
	}
	/**
	 * Test dragao mover contra paredes
	 */
	@Test
	public void TestMoverDragaoParedes()
	{
		dragoes.add(dragao);
		maze.criarLabirinto(m1, hero, dragoes, esp);
		maze.moverDragao(dragao, hero, 2, 2, 0);
		assertEquals(new Point(3,3), dragao.getDragaoPosicao());
		maze.moverDragao(dragao, hero, 2, 1, 0);
		assertEquals(new Point(3,3), dragao.getDragaoPosicao());
		maze.moverDragao(dragao, hero, 2, 0, 0);
		maze.moverDragao(dragao, hero, 2, 3, 0);
		assertEquals(new Point(2,3), dragao.getDragaoPosicao());
		dragao.setPosicaoDragao(1, 3);
		maze.moverDragao(dragao, hero, 2, 0, 0);
		assertEquals(new Point(1,3), dragao.getDragaoPosicao());
		
		hero.setPosicaoHeroi(1, 1);
		hero.setPosicaoHeroi(1, 3);
		maze.moverDragao(dragao, hero, 2, 2, 0);
		assertEquals(new Point(1,3), dragao.getDragaoPosicao());
	}
	/**
	 * Test dragao mover para espada
	 */
	@Test
	public void TestMoverDragaoEspada()
	{
		dragoes.add(dragao);
		maze.criarLabirinto(m1, hero, dragoes, esp);
		dragao.setLinha(2);
		dragao.setColuna(1);
		maze.moverDragao(dragao, hero, 2, 1, 0);
		assertEquals('F',dragao.getSimbolo());
		maze.moverDragao(dragao, hero, 2, 2, 0);
		assertEquals('D', dragao.getSimbolo());
		
	}
	
	@Test
	public void TestMoverDragaoSaidaOeste()
	{
		esp5 = new Espada(3,2);
		maze.criarLabirinto(m5, hero, dragoes,esp5);
		dragao.setPosicaoDragao(3,1);
		maze.moverDragao(dragao, hero, 2, 2, 0);
		assertEquals('F', dragao.getSimbolo());
		maze.moverDragao(dragao, hero, 2, 3, 0);
		assertEquals(new Point(3,1), dragao.getDragaoPosicao());
		dragao.setPosicaoDragao(3, 1);
		maze.moverDragao(dragao, hero, 2, 3, 0);
		assertEquals(new Point(3,1), dragao.getDragaoPosicao());
		dragao.setPosicaoDragao(3, 3);
		maze.moverDragao(dragao, hero, 2, 3, 0);
		assertEquals(new Point(3,2), dragao.getDragaoPosicao());
	}
	
	@Test
	public void TestMoverDragaoSaidaSul()
	{
		esp = new Espada(2,1);
		dragao = new Dragao(3,1);
		dragoes.add(dragao);
		maze.criarLabirinto(m4, hero, dragoes,esp);
		maze.moverDragao(dragao, hero, 2, 2, 0);
		assertEquals(new Point(3,2),dragao.getDragaoPosicao());
		maze.moverDragao(dragao, hero, 2, 1, 0);
		assertEquals(new Point(3,2),dragao.getDragaoPosicao());
		dragao.setPosicaoDragao(1, 1);
		maze.moverDragao(dragao, hero, 2, 1, 0);
		dragao.DragEsp();
		assertEquals(dragao.getDragaoPosicao(), esp.getEspPos());
		maze.moverDragao(dragao, hero, 2, 1, 0);
		assertEquals(new Point(3,1),dragao.getDragaoPosicao());
	}

	
	@Test
	public void TestMoverDragaoSaidaNorte()
	{
		hero = new Heroi(3,3);
		esp = new Espada(2,1);
		dragao = new Dragao(3,1);
		dragoes.add(dragao);
		maze.criarLabirinto(m3, hero, dragoes,esp);
		maze.moverDragao(dragao, hero, 2, 0, 0);
		assertEquals(dragao.getDragaoPosicao(), esp.getEspPos());
		maze.moverDragao(dragao, hero, 2, 0, 0);
		assertEquals(new Point(1,1), dragao.getDragaoPosicao());
		dragao.setPosicaoDragao(1, 3);
		maze.moverDragao(dragao, hero, 2, 0, 0);
		assertEquals(new Point(1,3), dragao.getDragaoPosicao());
	}
	/**
	 * Test dragao vs heroi
	 */
	@Test
	public void TestDragaoVsHeroi()
	{
		dragoes.add(dragao);
		maze.criarLabirinto(m1, hero, dragoes, esp);
		maze.moverDragao(dragao, hero, 2, 0, 0);
		maze.HeroivsDragao(dragao);
		assertTrue(dragao.getVida());
		assertFalse(hero.getVida());
	}
	
	
	/**
	 * Test lab default.
	 */
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

	/**
	 * Test modo jogo to int.
	 */
	@Test
	public void TestModoJogoToInt()
	{
		assertEquals(1,maze.modoJogotoInt("Basico"));
		assertEquals(2,maze.modoJogotoInt("Aleatorio"));
		assertEquals(3,maze.modoJogotoInt("Adormecido"));
		assertEquals(-1,maze.modoJogotoInt("Basi"));
	}
	
	/**
	 * Test lab3.
	 */
	@Test public void TestLab3()
	{
		dragoes.add(dragao);
		maze.criarLabirinto(m3, hero, dragoes, esp);
		hero.ativaArma();
		dragoes.get(0).DragaoMorre();
		maze.moverHeroi(hero, 'n');
		assertTrue(hero.getVitoria());
		
	}
	
	/**
	 * Test lab4.
	 */
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
	
	/**
	 * Test lab5.
	 */
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
