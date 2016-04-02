package maze.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JTextField;

import java.awt.Component;
import java.awt.Font;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JProgressBar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;

import maze.cli.Interface;
import maze.logic.Heroi;
import maze.logic.Dragao;
import maze.logic.Labirinto;
import maze.logic.Point;

import javax.swing.SwingConstants;

import com.sun.org.apache.xalan.internal.xsltc.dom.AbsoluteIterator;

public class Grafics {

	private JFrame menu;
	private JFrame RandomLab;
	private JFrame newLab;

	//janelas
	private JButton Iniciar;
	private JButton CriarLabirinto;
	private JButton Sair;

	//botoes
	private JButton ButtonNorte;
	private JButton ButtonSul;
	private JButton ButtonEste;
	private JButton ButtonOeste;
	private JButton ButtonJogar;
	private JButton ButtonMenu;
	private JButton ButtonParede;
	private JButton ButtonHeroi;
	private JButton ButtonDragao;
	private JButton ButtonEspada;
	private JButton ButtonCaminho;
	private JButton ButtonSaida;
	private JButton ButtonMenu2;
	private JButton ButtonJogar2;

	private JTextField DimensaoLabirinto;
	private JTextField NrDragoes;
	
	private Labirinto lab;
	private Interface inter;
	private char labirinto[][];

	private LabirintoPanel panel_final;

	private LabirintoPanel panel_create;
	
	private JLabel lblEtiquetaEstado;

	private int modoJogo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Grafics window = new Grafics();
					window.menu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Grafics() {
		initialize();
	}

	public void setVisible(boolean visible) {
		menu.setVisible(visible);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//janela de menu inicial
		menu = new JFrame();
		menu.setAlwaysOnTop(true);
		menu.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 34));
		menu.setBounds(100, 100, 600, 400);
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.getContentPane().setLayout(null);
		menu.setVisible(true);
		menu.setResizable(false);
		
		//janela de jogar com tabuleiro aleatorio
		RandomLab = new JFrame();
		RandomLab.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 34));
		RandomLab.setBounds(100, 100, 795, 538);
		RandomLab.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		RandomLab.getContentPane().setLayout(null);

		//janela de criacao de labirinto
		newLab = new JFrame();
		newLab.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 34));
		newLab.setBounds(100, 100, 800, 550);
		newLab.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		newLab.getContentPane().setLayout(null);

		/*
		 * Configuracao do menu
		 * */
		JLabel lblDimensoDoLabirinto = new JLabel("Dimens\u00E3o do labirinto");
		lblDimensoDoLabirinto.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblDimensoDoLabirinto.setBounds(100, 40, 211, 43);
		menu.getContentPane().add(lblDimensoDoLabirinto);

		DimensaoLabirinto = new JTextField();
		DimensaoLabirinto.setText("11");
		DimensaoLabirinto.setBounds(321, 50, 131, 28);
		menu.getContentPane().add(DimensaoLabirinto);
		DimensaoLabirinto.setColumns(10);

		JLabel lblNmeroDeDrages = new JLabel("N\u00FAmero de drag\u00F5es");
		lblNmeroDeDrages.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNmeroDeDrages.setBounds(100, 100, 211, 43);
		menu.getContentPane().add(lblNmeroDeDrages);

		NrDragoes = new JTextField();
		NrDragoes.setText("1");
		NrDragoes.setColumns(10);
		NrDragoes.setBounds(321, 110, 131, 28);
		menu.getContentPane().add(NrDragoes);

		JLabel lblModoDeJogo = new JLabel("Modo de jogo");
		lblModoDeJogo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblModoDeJogo.setBounds(100, 160, 211, 43);
		menu.getContentPane().add(lblModoDeJogo);

		JComboBox MododeJogo = new JComboBox();
		MododeJogo.setModel(new DefaultComboBoxModel(new String[] {"Basico", "Aleatorio", "Adormecido"}));
		MododeJogo.setSelectedIndex(0);
		MododeJogo.setBounds(321, 170, 131, 35);
		menu.getContentPane().add(MododeJogo);

		//botao de iniciar na janela menu
		Iniciar = new JButton("Iniciar");
		Iniciar.setForeground(Color.BLACK);
		Iniciar.setEnabled(true);
		Iniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RandomLab.setVisible(true);
				ButtonJogar.doClick();
				menu.setVisible(false);
			}
		});
		Iniciar.setBounds(50, 300, 150, 52);
		menu.getContentPane().add(Iniciar);

		//botao de criacao de labirinto no menu
		CriarLabirinto = new JButton("Criar Labirinto");
		CriarLabirinto.setForeground(Color.BLACK);
		CriarLabirinto.setEnabled(true);
		CriarLabirinto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				menu.setVisible(false);
				newLab.setVisible(true);
				ButtonJogar2.doClick();
			}
		});
		CriarLabirinto.setBounds(220, 300, 150, 52);
		menu.getContentPane().add(CriarLabirinto);

		//botao de saida na janela menu
		Sair = new JButton("Sair");
		Sair.setForeground(Color.BLACK);
		Sair.setEnabled(true);
		Sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		Sair.setBounds(390, 300, 150, 52);
		menu.getContentPane().add(Sair);

		panel_final = new LabirintoPanel();
		panel_final.setBounds(0, 0, 497, 492);
		panel_final.setFocusable(true);
		panel_final.addKeyListener(new KeyListener() {

			/* Código para teclado */
			@Override
			public void keyPressed(KeyEvent e) {
				switch(e.getKeyCode()){

				case KeyEvent.VK_LEFT: 
					ButtonOeste.doClick();
					break;
				case KeyEvent.VK_RIGHT: 
					ButtonEste.doClick();
					break;
				case KeyEvent.VK_UP:  
					ButtonNorte.doClick();
					break;
				case KeyEvent.VK_DOWN: 
					ButtonSul.doClick();
					break;

				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}});
		RandomLab.getContentPane().add(panel_final);

		lblEtiquetaEstado = new JLabel("Inicio!");
		lblEtiquetaEstado.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEtiquetaEstado.setBounds(62, 555, 106, 44);
		RandomLab.getContentPane().add(lblEtiquetaEstado);

		/*
		 * Configuracao de Jogo Aleatorio
		 * */
		ButtonNorte = new JButton("Norte");
		ButtonNorte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MoveHeroi('n');
			}
		});
		ButtonNorte.setBounds(585, 117, 120, 49);
		ButtonNorte.setFocusable(false);
		RandomLab.getContentPane().add(ButtonNorte);

		ButtonOeste = new JButton("Oeste");
		ButtonOeste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MoveHeroi('o');
			}
		});
		ButtonOeste.setBounds(523, 177, 120, 49);
		ButtonOeste.setFocusable(false);
		RandomLab.getContentPane().add(ButtonOeste);

		ButtonEste = new JButton("Este");
		ButtonEste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MoveHeroi('e');
			}
		});
		ButtonEste.setBounds(649, 177, 120, 49);
		ButtonEste.setFocusable(false);
		RandomLab.getContentPane().add(ButtonEste);

		ButtonSul = new JButton("Sul");
		ButtonSul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MoveHeroi('s');
			}
		});
		ButtonSul.setBounds(585, 237, 120, 49);
		ButtonSul.setFocusable(false);
		RandomLab.getContentPane().add(ButtonSul);

		ButtonJogar = new JButton("Novo Jogo");
		ButtonJogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				lab = new Labirinto();

				modoJogo = lab.modoJogotoInt((String)MododeJogo.getSelectedItem());

				ArrayList<Point> posLivres = lab.GerarLabirinto(Integer.parseInt(DimensaoLabirinto.getText()));
				lab.ColocarCarateres(posLivres, Integer.parseInt(NrDragoes.getText()));
				panel_final.setLabirinto(lab);

				panel_final.setFocusable(true);
				panel_final.requestFocus();

				//ativar botoes para mexer heroi
				ButtonEste.setEnabled(true);
				ButtonNorte.setEnabled(true);
				ButtonSul.setEnabled(true);
				ButtonOeste.setEnabled(true);

				lblEtiquetaEstado.setText("Pode Jogar!");

				panel_final.repaint();
			}
		});
		ButtonJogar.setBounds(553, 337, 100, 49);
		ButtonJogar.setFocusable(false);
		RandomLab.getContentPane().add(ButtonJogar);

		ButtonMenu = new JButton("Menu");
		ButtonMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.setVisible(true);
				RandomLab.setVisible(false);
			}
		});
		ButtonMenu.setBounds(653, 337, 100, 49);
		ButtonMenu.setFocusable(false);
		RandomLab.getContentPane().add(ButtonMenu);
		
		
		
		
		
		/*
		 * Configuracao da Criacao do Labirinto
		 *
		 * */
		
		panel_create = new LabirintoPanel();
		panel_create.setBounds(5, 5, 497, 500);
		panel_create.setBackground(Color.BLACK);
		panel_create.setFocusable(true);
		
		
		ButtonCaminho = new JButton("Caminho");
		ButtonCaminho.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ButtonParede.setBackground(Color.LIGHT_GRAY);
				ButtonCaminho.setBackground(Color.BLUE);
				ButtonHeroi.setBackground(Color.LIGHT_GRAY);
				ButtonDragao.setBackground(Color.LIGHT_GRAY);
				ButtonEspada.setBackground(Color.LIGHT_GRAY);
				ButtonSaida.setBackground(Color.LIGHT_GRAY);
			}
		});
		ButtonCaminho.setBounds(540, 10, 100, 49);
		newLab.getContentPane().add(ButtonCaminho);
		
		ButtonSaida = new JButton("Saida");
		ButtonSaida.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ButtonParede.setBackground(Color.LIGHT_GRAY);
				ButtonCaminho.setBackground(Color.LIGHT_GRAY);
				ButtonHeroi.setBackground(Color.LIGHT_GRAY);
				ButtonDragao.setBackground(Color.LIGHT_GRAY);
				ButtonEspada.setBackground(Color.LIGHT_GRAY);
				ButtonSaida.setBackground(Color.BLUE);
			}
		});
		ButtonSaida.setBounds(650, 10, 100, 49);
		newLab.getContentPane().add(ButtonSaida);
		
		ButtonHeroi = new JButton("Heroi");
		ButtonHeroi.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ButtonParede.setBackground(Color.LIGHT_GRAY);
				ButtonCaminho.setBackground(Color.LIGHT_GRAY);
				ButtonHeroi.setBackground(Color.BLUE);
				ButtonDragao.setBackground(Color.LIGHT_GRAY);
				ButtonEspada.setBackground(Color.LIGHT_GRAY);
				ButtonSaida.setBackground(Color.LIGHT_GRAY);
			}
		});
		ButtonHeroi.setBounds(540, 65, 100, 49);
		newLab.getContentPane().add(ButtonHeroi);
		
		ButtonDragao = new JButton("Dragao");
		ButtonDragao.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ButtonParede.setBackground(Color.LIGHT_GRAY);
				ButtonCaminho.setBackground(Color.LIGHT_GRAY);
				ButtonHeroi.setBackground(Color.LIGHT_GRAY);
				ButtonDragao.setBackground(Color.BLUE);
				ButtonEspada.setBackground(Color.LIGHT_GRAY);
				ButtonSaida.setBackground(Color.LIGHT_GRAY);
			}
		});
		ButtonDragao.setBounds(650, 65, 100, 49);
		newLab.getContentPane().add(ButtonDragao);
		
		ButtonEspada = new JButton("Espada");
		ButtonEspada.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ButtonParede.setBackground(Color.LIGHT_GRAY);
				ButtonCaminho.setBackground(Color.LIGHT_GRAY);
				ButtonHeroi.setBackground(Color.LIGHT_GRAY);
				ButtonDragao.setBackground(Color.LIGHT_GRAY);
				ButtonEspada.setBackground(Color.BLUE);
				ButtonSaida.setBackground(Color.LIGHT_GRAY);
			}
		});
		ButtonEspada.setBounds(540, 119, 100, 49);
		newLab.getContentPane().add(ButtonEspada);
		
		ButtonParede = new JButton("Parede");
		ButtonParede.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ButtonParede.setBackground(Color.BLUE);
				ButtonCaminho.setBackground(Color.LIGHT_GRAY);
				ButtonHeroi.setBackground(Color.LIGHT_GRAY);
				ButtonDragao.setBackground(Color.LIGHT_GRAY);
				ButtonEspada.setBackground(Color.LIGHT_GRAY);
				ButtonSaida.setBackground(Color.LIGHT_GRAY);
			}
		});
		ButtonParede.setBounds(650, 119, 100, 49);
		newLab.getContentPane().add(ButtonParede);
		
		ButtonJogar2 = new JButton("Criar Novo");
		ButtonJogar2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int dim = Integer.parseInt(DimensaoLabirinto.getText());
				labirinto = new char[dim][dim];
				
				
			}
		});
		ButtonJogar2.setBounds(540, 450, 100, 49);
		newLab.getContentPane().add(ButtonJogar2);
		
		ButtonMenu2 = new JButton("Menu");
		ButtonMenu2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
			}
		});
		ButtonMenu2.setBounds(650, 450, 100, 49);
		newLab.getContentPane().add(ButtonMenu2);
		
		
		
//		panel_create.addMouseListener(new MouseListener(){
//			@Override
//			public void mouseClicked(MouseEvent e) {
//			}
//
//			@Override
//			public void mousePressed(MouseEvent e) {
//				x = e.getX();
//				y = e.getY();
//				panel_create.repaint();
//				
//			}
//
//			@Override
//			public void mouseReleased(MouseEvent e) {
//			}
//
//			@Override
//			public void mouseEntered(MouseEvent e) {
//			}
//
//			@Override
//			public void mouseExited(MouseEvent e) {
//			}	
//		});
		newLab.getContentPane().add(panel_create);
		
	}

	private Object stener() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*
	 * Funcao responsavel pelos movimentos do dragao e do heroi
	 * 
	 * */

	public void MoveHeroi(char c){
		Heroi h = lab.getHeroi();
		ArrayList<Dragao> d = lab.getDragoes();
		lab.moverHeroi(h, c);

		for(int i=0; i<d.size(); i++){
			lab.HeroivsDragao(d.get(i));

			if(modoJogo != -1 && modoJogo != 1){
				lab.moverDragao(d.get(i), h, modoJogo);
				lab.HeroivsDragao(d.get(i));
			}
		}

		if(h.getVida() == false || h.getVitoria() == true){
			if(h.getVida() == false)
			{
				lblEtiquetaEstado.setText("Perdeu!");
			}
			else
				lblEtiquetaEstado.setText("Venceu!");

			panel_final.setFocusable(false);
			ButtonEste.setEnabled(false);
			ButtonNorte.setEnabled(false);
			ButtonSul.setEnabled(false);
			ButtonOeste.setEnabled(false);
		}

		panel_final.repaint();
	}
}
