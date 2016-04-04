package maze.gui;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;

import maze.cli.Interface;
import maze.logic.Dragao;
import maze.logic.Heroi;
import maze.logic.Labirinto;
import maze.logic.Point;


// TODO: Auto-generated Javadoc
/**
 * The Class Grafics.
 */
public class Grafics {

	/** The menu. */
	private JFrame menu;

	/** The Random lab. */
	private JFrame RandomLab;

	/** The new lab. */
	private JFrame newLab;

	/** The Iniciar. */
	//janelas
	private JButton Iniciar;

	/** The Criar labirinto. */
	private JButton CriarLabirinto;

	/** The Sair. */
	private JButton Sair;

	/**  The Panel Buttons. */
	private JPanel panelButtons;

	/** The Button norte. */
	//botoes
	private JButton ButtonNorte;

	/** The Button sul. */
	private JButton ButtonSul;

	/** The Button este. */
	private JButton ButtonEste;

	/** The Button oeste. */
	private JButton ButtonOeste;

	/** The Button menu. */
	private JButton ButtonMenu;

	/** The Button parede. */
	private JButton ButtonParede;

	/** The Button heroi. */
	private JButton ButtonHeroi;

	/** The Button dragao. */
	private JButton ButtonDragao;

	/** The Button espada. */
	private JButton ButtonEspada;

	/** The Button caminho. */
	private JButton ButtonCaminho;

	/** The Button saida. */
	private JButton ButtonSaida;

	/** The Button menu2. */
	private JButton ButtonMenu2;

	/** The Button jogar Novo Jogo. */
	private JButton ButtonNovoJogo;
	
	/** The Button criar labirinto. */

	private JButton ButtonCriarLabirinto;
	private JButton ButtonCriarLabirinto2;

	/** The Button jogar3. */
	private JButton ButtonJogar3;

	/** The Dimensao labirinto. */
	private JTextField DimensaoLabirinto;

	/** The Nr dragoes. */
	private JTextField NrDragoes;

	/** The lbl etiqueta estado. */
	private JLabel lblEtiquetaEstado;
	private JLabel EtiquetaCriacao;

	/** The panel_final. */
	private LabirintoPanel panel_final;

	/** The panel_create. */
	private LabirintoBuilder panel_create;
	/** The lab. */
	private Labirinto lab;

	/** The inter. */
	private Interface inter;

	/** The labirinto. */
	private char labirinto[][];

	/** The n vezes. */
	private int nVezes;
	private int criarNVezes;


	/** The modo jogo. */
	private int modoJogo;

	/**
	 * Launch the application.
	 *
	 * @param args the arguments
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

	
	/**
	 * Sets the visible.
	 *
	 * @param b the new visible
	 */
	public void setVisible(boolean b) {
		menu.setVisible(b);

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
		criarNVezes = 0;



		//janela jogar aleatorio
		RandomLab = new JFrame();
		RandomLab.setBounds(100, 100, 920, 630);
		RandomLab.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		RandomLab.setVisible(false);
		JPanel panelGeneral = new JPanel();
		RandomLab.getContentPane().add(panelGeneral, BorderLayout.CENTER);
		RandomLab.setMinimumSize(new Dimension(920, 630));



		//janela de criacao de labirinto
		newLab = new JFrame();
		newLab.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 34));
		newLab.setBounds(100, 100, 800, 550);
		newLab.setMinimumSize(new Dimension(700, 450));
		newLab.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		newLab.getContentPane().setLayout(null);


		//** Configuracao menu **//

		//botao criar labirinto
		ButtonCriarLabirinto = new JButton("Criar Labirinto");
		ButtonCriarLabirinto.setForeground(Color.BLACK);
		ButtonCriarLabirinto.setEnabled(true);
		ButtonCriarLabirinto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				menu.setVisible(false);
				newLab.setVisible(true);
				ButtonCriarLabirinto2.doClick();
				ButtonParede.doClick();
			}
		});
		ButtonCriarLabirinto.setBounds(220, 300, 150, 52);
		menu.getContentPane().add(ButtonCriarLabirinto);

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
		MododeJogo.setModel(new DefaultComboBoxModel(new String[] {"Estatico", "Aleatorio", "Aleatorio/Adormecido"}));
		MododeJogo.setSelectedIndex(0);
		MododeJogo.setBounds(321, 170, 131, 35);
		menu.getContentPane().add(MododeJogo);

		//botao de iniciar na janela menu
		Iniciar = new JButton("Iniciar");
		Iniciar.setForeground(Color.BLACK);
		Iniciar.setEnabled(true);
		Iniciar.setVisible(true);
		Iniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				int l = Integer.parseInt(DimensaoLabirinto.getText()); 	//tamanho
				int drags = Integer.parseInt(NrDragoes.getText());		//nr dragoes
				int dg = l-5;

				if(l < 5){
					JOptionPane.showMessageDialog(menu, "Dimensão impossível!");
				}
				else if(drags > dg){
					JOptionPane.showMessageDialog(menu, "Número de dragões impossível!");
				}
				else if(drags <= 0)
					JOptionPane.showMessageDialog(menu, "Tem de ter pelo menos um dragão!");
				else{
					
					if(l > 25)
						JOptionPane.showMessageDialog(menu, "Dimensões acima de 25 podem tornar labirinto impercetível!");
						
					ButtonNovoJogo.setEnabled(true);
					ButtonNovoJogo.setVisible(true);

					nVezes = 0;
					menu.setVisible(false);
					RandomLab.setVisible(true);

					ButtonNovoJogo.doClick();
				}
			}
		});
		Iniciar.setBounds(50, 300, 150, 52);
		menu.getContentPane().add(Iniciar);

		//botao de saida na janela menu
		Sair = new JButton("Sair");
		Sair.setForeground(Color.BLACK);
		Sair.setEnabled(true);
		Sair.setVisible(true);
		Sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		Sair.setBounds(390, 300, 150, 52);
		menu.getContentPane().add(Sair);



		panel_final = new LabirintoPanel();
		panel_final.setBackground(Color.LIGHT_GRAY);
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


		panelButtons = new JPanel();
		panelButtons.setBackground(UIManager.getColor("Button.background"));
		panelButtons.setFocusable(true);
		panelButtons.addKeyListener(new KeyListener() {

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

		lblEtiquetaEstado = new JLabel("Inicio!");
		lblEtiquetaEstado.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEtiquetaEstado.setBounds(62, 555, 106, 44);
		GroupLayout gl_panelGeneral = new GroupLayout(panelGeneral);
		gl_panelGeneral.setHorizontalGroup(
				gl_panelGeneral.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelGeneral.createSequentialGroup()
						.addGap(24)
						.addComponent(panel_final, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(panelButtons, GroupLayout.PREFERRED_SIZE, 387, GroupLayout.PREFERRED_SIZE)
						.addGap(21))
				.addGroup(gl_panelGeneral.createSequentialGroup()
						.addGap(69)
						.addComponent(lblEtiquetaEstado, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(602, Short.MAX_VALUE))
				);
		gl_panelGeneral.setVerticalGroup(
				gl_panelGeneral.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelGeneral.createSequentialGroup()
						.addGap(27)
						.addComponent(panel_final, GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblEtiquetaEstado, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addGap(6))
				.addGroup(Alignment.LEADING, gl_panelGeneral.createSequentialGroup()
						.addGap(137)
						.addComponent(panelButtons, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
						.addGap(171))
				);

		ButtonNovoJogo = new JButton("NovoJogo");
		ButtonNovoJogo.setBounds(73, 243, 94, 40);
		ButtonNovoJogo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(nVezes == 0)
					criarOutroLab();

				modoJogo = lab.modoJogotoInt((String)MododeJogo.getSelectedItem());

				panel_final.repaint();
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

		ButtonNorte = new JButton("Norte");
		ButtonNorte.setBounds(150, 53, 94, 51);
		ButtonNorte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MoveHeroi('n');
				panel_final.setCoordH('n');
			}
		});
		ButtonNorte.setFocusable(false);
		panelButtons.setLayout(null);
		panelButtons.add(ButtonNorte);

		ButtonSul = new JButton("Sul");
		ButtonSul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MoveHeroi('s');
				panel_final.setCoordH('s');
			}
		});
		ButtonSul.setBounds(150, 179, 94, 51);
		ButtonSul.setFocusable(false);
		panelButtons.add(ButtonSul);

		ButtonOeste = new JButton("Oeste");
		ButtonOeste.setBounds(73, 117, 94, 51);
		ButtonOeste.setFocusable(false);
		panelButtons.add(ButtonOeste);
		ButtonOeste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MoveHeroi('o');
				panel_final.setCoordH('o');
			}
		});

		ButtonEste = new JButton("Este");
		ButtonEste.setBounds(229, 115, 94, 51);
		ButtonEste.setFocusable(false);
		ButtonEste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MoveHeroi('e');
				panel_final.setCoordH('e');
			}
		});

		panelButtons.add(ButtonEste);

		JButton ButtonMenu = new JButton("Menu");
		ButtonMenu.setBounds(229, 243, 94, 40);
		ButtonMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RandomLab.setVisible(false);
				menu.setVisible(true);
				lab = new Labirinto();
			}
		});
		panelButtons.add(ButtonMenu);





		panelButtons.add(ButtonNovoJogo);
		GroupLayout gl_panelLabirinto = new GroupLayout(panel_final);
		gl_panelLabirinto.setHorizontalGroup(
				gl_panelLabirinto.createParallelGroup(Alignment.LEADING)
				.addGap(0, 300, Short.MAX_VALUE)
				);
		gl_panelLabirinto.setVerticalGroup(
				gl_panelLabirinto.createParallelGroup(Alignment.LEADING)
				.addGap(0, 480, Short.MAX_VALUE)
				);
		panel_final.setLayout(gl_panelLabirinto);
		panelGeneral.setLayout(gl_panelGeneral);


		//** Configuracao Cria Labirinto **//

		panel_create = new LabirintoBuilder();
		panel_create.setBounds(5, 5, 497, 500);
		panel_create.setBackground(Color.LIGHT_GRAY);
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
				panel_create.setSimbolo('.');
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
				panel_create.setSimbolo('S');
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
				panel_create.setSimbolo('H');
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
				panel_create.setSimbolo('D');
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
				panel_create.setSimbolo('E');
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
				panel_create.setSimbolo('X');
			}
		});
		ButtonParede.setBounds(650, 119, 100, 49);
		newLab.getContentPane().add(ButtonParede);

		EtiquetaCriacao = new JLabel("Construa Labirinto!");
		EtiquetaCriacao.setFont(new Font("Tahoma", Font.PLAIN, 12));
		EtiquetaCriacao.setBounds(585, 295, 160, 49);
		EtiquetaCriacao.setVisible(true);
		GroupLayout gl_panelGeneral1 = new GroupLayout(panelGeneral);
		gl_panelGeneral1.setHorizontalGroup(
				gl_panelGeneral1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelGeneral1.createSequentialGroup()
						.addGap(24)
						.addComponent(panel_final, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(panelButtons, GroupLayout.PREFERRED_SIZE, 387, GroupLayout.PREFERRED_SIZE)
						.addGap(21))
				.addGroup(gl_panelGeneral1.createSequentialGroup()
						.addGap(69)
						.addComponent(lblEtiquetaEstado, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(602, Short.MAX_VALUE))
				);
		gl_panelGeneral1.setVerticalGroup(
				gl_panelGeneral1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelGeneral1.createSequentialGroup()
						.addGap(27)
						.addComponent(panel_final, GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblEtiquetaEstado, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addGap(6))
				.addGroup(Alignment.LEADING, gl_panelGeneral1.createSequentialGroup()
						.addGap(137)
						.addComponent(panelButtons, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
						.addGap(171))
				);
		newLab.add(EtiquetaCriacao);

		ButtonJogar3 = new JButton("Jogar");
		ButtonJogar3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(panel_create.getConstruir() == -1){
					JOptionPane.showMessageDialog(menu, "Tem de ter pelo menos um elemento de cada tipo!");
				}
				else{
					panel_create.setLabirinto(labirinto);
					lab.criarLabirinto(labirinto, panel_create.getHeroi(), panel_create.getDragoes(), panel_create.getEspada());

					if(lab.checkExitReachable(labirinto)){
						ButtonParede.setEnabled(false);
						ButtonCaminho.setEnabled(false);
						ButtonHeroi.setEnabled(false);
						ButtonDragao.setEnabled(false);
						ButtonEspada.setEnabled(false);
						ButtonSaida.setEnabled(false);

						panel_final.setFocusable(true);

						newLab.setVisible(false);
						RandomLab.setVisible(true);
						nVezes++;

						ButtonNovoJogo.doClick();
						ButtonNovoJogo.setEnabled(false);
						ButtonNovoJogo.setVisible(false);

						panel_final.requestFocus();
						panel_create.setBoolean();
						panel_final.setLabirinto(lab);
					}
					else{
						JOptionPane.showMessageDialog(menu, "Construiu labirinto sem solução!");
					}
				}
			}
		});
		ButtonJogar3.setBounds(595, 395, 100, 49);
		newLab.getContentPane().add(ButtonJogar3);

		ButtonCriarLabirinto2 = new JButton("Criar Novo");
		ButtonCriarLabirinto2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){	
				panel_create.setBoolean();

				ButtonCaminho.setEnabled(true);
				ButtonEspada.setEnabled(true);
				ButtonHeroi.setEnabled(true);
				ButtonDragao.setEnabled(true);
				ButtonParede.setEnabled(true);
				ButtonSaida.setEnabled(true);
				ButtonParede.doClick();

				lab = new Labirinto();

				modoJogo = lab.modoJogotoInt((String)MododeJogo.getSelectedItem());

				int dim = Integer.parseInt(DimensaoLabirinto.getText());
				labirinto = new char[dim][dim];

				for(int i=0; i<dim; i++){
					for(int j=0; j<dim; j++){
						if (i == 0)
							labirinto[i][j] = 'X';
						else if(i == dim-1)
							labirinto[i][j] = 'X';
						else if(j == 0)
							labirinto[i][j] = 'X';
						else if(j == dim-1)
							labirinto[i][j] = 'X';
						else 
							labirinto[i][j] = '.';
					}
				}

				panel_create.setLabirinto(labirinto);
				panel_create.repaint();
				panel_create.requestFocus();
			}
		});
		ButtonCriarLabirinto2.setBounds(540, 450, 100, 49);
		newLab.getContentPane().add(ButtonCriarLabirinto2);

		ButtonMenu2 = new JButton("Menu");
		ButtonMenu2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				menu.setVisible(true);
				newLab.setVisible(false);
				lab = new Labirinto();
			}
		});
		ButtonMenu2.setBounds(650, 450, 100, 49);
		newLab.getContentPane().add(ButtonMenu2);
		newLab.getContentPane().add(panel_create);

	}

	/**
	 * Stener.
	 *
	 * @return the object
	 */
	private Object stener() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * Funcao responsavel pelos movimentos do dragao e do heroi
	 * 
	 * */

	/**
	 * Move heroi.
	 *
	 * @param c the c
	 */
	public void MoveHeroi(char c){
		Heroi h = lab.getHeroi();
		ArrayList<Dragao> d = lab.getDragoes();
		lab.moverHeroi(h, c);
		
		Random r = new Random();
		int dirc;
		int adorm;

		for(int i=0; i<d.size(); i++){
			lab.HeroivsDragao(d.get(i));

			if(modoJogo != 1 && (modoJogo == 2 || modoJogo == 3)){
				dirc = r.nextInt(5);
				adorm = r.nextInt(4);

				lab.moverDragao(d.get(i), h, modoJogo, dirc, adorm);
				lab.HeroivsDragao(d.get(i));

				panel_final.setCoordD(dirc);
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

	/**
	 * Criar outro lab.
	 */
	public void criarOutroLab(){
		lab = new Labirinto();
		ArrayList<Point> posLivres = lab.GerarLabirinto(Integer.parseInt(DimensaoLabirinto.getText()));
		lab.ColocarCarateres(posLivres, Integer.parseInt(NrDragoes.getText()));
	}


}
