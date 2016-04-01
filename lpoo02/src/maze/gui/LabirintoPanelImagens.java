package maze.gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
public class LabirintoPanelImagens extends JPanel{

	protected static BufferedImage parede;
	protected static BufferedImage heroi;
	protected static BufferedImage dragao;
	protected static BufferedImage ponto;
	protected static BufferedImage saida;
	protected static BufferedImage espada;
	
	public LabirintoPanelImagens(){
		
		 try {
			 heroi =  ImageIO.read(new File("imagens\\heroi.png")); 
			 parede =  ImageIO.read(new File("imagens\\parede.jpg")); 
			 dragao =  ImageIO.read(new File("imagens\\dragao.jpg")); 
			 ponto =  ImageIO.read(new File("imagens\\ponto.jpg")); 
			 saida =  ImageIO.read(new File("imagens\\saida.png")); 
			 espada =  ImageIO.read(new File("imagens\\espada.jpg")); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 
	}
	

}
