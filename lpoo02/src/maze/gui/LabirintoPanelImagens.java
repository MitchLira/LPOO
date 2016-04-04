/*
 * 
 */
package maze.gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
// TODO: Auto-generated Javadoc

/**
 * The Class LabirintoPanelImagens.
 */
public class LabirintoPanelImagens extends JPanel{

	/** The parede. */
	protected static BufferedImage parede;

	protected static BufferedImage heroiNorte;
	protected static BufferedImage heroiArmNorte;
	protected static BufferedImage heroiSul;
	protected static BufferedImage heroiArmSul;
	protected static BufferedImage heroiOeste;
	protected static BufferedImage heroiArmOeste;
	protected static BufferedImage heroiEste;
	protected static BufferedImage heroiArmEste;
	
	protected static BufferedImage dragaoNorte;
	protected static BufferedImage dragaoSul;
	protected static BufferedImage dragaoEste;
	protected static BufferedImage dragaoOeste;
	protected static BufferedImage dragaoAdorm;
	
	protected static BufferedImage saidaNorte;
	protected static BufferedImage saidaSul;
	protected static BufferedImage saidaEste;
	protected static BufferedImage saidaOeste;

	/** The ponto. */
	protected static BufferedImage ponto;

	/** The espada. */
	protected static BufferedImage espada;

	/**
	 * Instantiates a new labirinto panel imagens.
	 */
	public LabirintoPanelImagens(){
		

		try {
			heroiNorte = ImageIO.read(new File("imagens\\heroiNorte.png"));
			heroiArmNorte = ImageIO.read(new File("imagens\\heroiArmNorte.png"));
			heroiSul = ImageIO.read(new File("imagens\\heroiSul.png"));
			heroiArmSul = ImageIO.read(new File("imagens\\heroiArmSul.png"));
			heroiOeste = ImageIO.read(new File("imagens\\heroiOeste.png"));
			heroiArmOeste= ImageIO.read(new File("imagens\\heroiArmOeste.png"));
			heroiEste = ImageIO.read(new File("imagens\\heroiEste.png"));
			heroiArmEste = ImageIO.read(new File("imagens\\heroiArmEste.png"));
			
			espada =  ImageIO.read(new File("imagens\\espada.png"));
			
			dragaoNorte = ImageIO.read(new File("imagens\\dragaoNorte.png"));
			dragaoSul = ImageIO.read(new File("imagens\\dragaoSul.png"));
			dragaoEste = ImageIO.read(new File("imagens\\dragaoEste.png"));
			dragaoOeste = ImageIO.read(new File("imagens\\dragaoOeste.png"));
			dragaoAdorm = ImageIO.read(new File("imagens\\dragonSleep.png"));
			
			saidaNorte = ImageIO.read(new File("imagens\\saidaAbertaNorte.png"));
			saidaSul = ImageIO.read(new File("imagens\\saidaAbertaSul.png"));
			saidaEste = ImageIO.read(new File("imagens\\saidaAbertaEste.png"));
			saidaOeste = ImageIO.read(new File("imagens\\saidaAbertaOeste.png"));
			
			parede =  ImageIO.read(new File("imagens\\parede.jpg")); 
			
			ponto =  ImageIO.read(new File("imagens\\ponto.jpg")); 
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
