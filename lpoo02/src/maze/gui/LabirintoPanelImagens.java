package maze.gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
public class LabirintoPanelImagens extends JPanel{

	protected static BufferedImage parede;
	protected static BufferedImage heroiFrente;
	protected static BufferedImage heroiTras;
	protected static BufferedImage heroiArmado;
	protected static BufferedImage dragao;
	protected static BufferedImage ponto;
	protected static BufferedImage saida;
	protected static BufferedImage espada;
	
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
