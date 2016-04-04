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

	/** The heroi norte. */
	protected static BufferedImage heroiNorte;
	
	/** The heroi arm norte. */
	protected static BufferedImage heroiArmNorte;
	
	/** The heroi sul. */
	protected static BufferedImage heroiSul;
	
	/** The heroi arm sul. */
	protected static BufferedImage heroiArmSul;
	
	/** The heroi oeste. */
	protected static BufferedImage heroiOeste;
	
	/** The heroi arm oeste. */
	protected static BufferedImage heroiArmOeste;
	
	/** The heroi este. */
	protected static BufferedImage heroiEste;
	
	/** The heroi arm este. */
	protected static BufferedImage heroiArmEste;
	
	/** The dragao norte. */
	protected static BufferedImage dragaoNorte;
	
	/** The dragao sul. */
	protected static BufferedImage dragaoSul;
	
	/** The dragao este. */
	protected static BufferedImage dragaoEste;
	
	/** The dragao oeste. */
	protected static BufferedImage dragaoOeste;
	
	/** The dragao adorm. */
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
