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

	protected static BufferedImage heroi;
	protected static BufferedImage heroiNorte;
	protected static BufferedImage heroiArmNorte;
	protected static BufferedImage heroiSul;
	protected static BufferedImage heroiArmSul;
	protected static BufferedImage heroiOeste;
	protected static BufferedImage heroiArmOeste;
	protected static BufferedImage heroiEste;
	protected static BufferedImage heroiArmEste;

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
			heroi = ImageIO.read(new File("imagens\\heroiSul.png"));
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
	
	public void setHeroiImagem(char c, boolean arm){
		try {
			switch(c){
			case 'n':
				if(arm)
					heroi = ImageIO.read(new File("imagens\\heroiArmNorte.png"));
				else
					heroi = ImageIO.read(new File("imagens\\heroiNorte.png"));
				break;
			case 's':
				if(arm)
					heroi = ImageIO.read(new File("imagens\\heroiArmSul.png"));
				else
					heroi = ImageIO.read(new File("imagens\\heroiSul.png"));
				break;
			case 'o':
				if(arm)
					heroi = ImageIO.read(new File("imagens\\heroiArmOeste.png"));
				else
					heroi = ImageIO.read(new File("imagens\\heroiOeste.png"));
				break;
			case 'e':
				if(arm)
					heroi = ImageIO.read(new File("imagens\\heroiArmEste.png"));
				else
					heroi = ImageIO.read(new File("imagens\\heroiEste.png"));
				break;
			} 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
