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
	
	/** The heroi frente. */
	protected static BufferedImage heroiFrente;
	
	/** The heroi tras. */
	protected static BufferedImage heroiTras;
	
	/** The heroi armado. */
	protected static BufferedImage heroiArmado;
	
	/** The dragao. */
	protected static BufferedImage dragao;
	
	/** The ponto. */
	protected static BufferedImage ponto;
	
	/** The saida. */
	protected static BufferedImage saida;
	
	/** The espada. */
	protected static BufferedImage espada;
	
	/**
	 * Instantiates a new labirinto panel imagens.
	 */
	public LabirintoPanelImagens(){
		
		 try {
			 heroiFrente =  ImageIO.read(new File("imagens\\heroiFrente.png")); 
			 heroiTras =  ImageIO.read(new File("imagens\\heroiTras.png"));
			 heroiArmado = ImageIO.read(new File("imagens\\heroiArmado.png"));
			 parede =  ImageIO.read(new File("imagens\\parede.jpg")); 
			 dragao =  ImageIO.read(new File("imagens\\dragao.jpg")); 
			 ponto =  ImageIO.read(new File("imagens\\ponto.jpg")); 
			 saida =  ImageIO.read(new File("imagens\\saida.png")); 
			 espada =  ImageIO.read(new File("imagens\\pistola.jpg")); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 
	}
	

}
