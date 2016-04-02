package maze.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

public class Grafics {

	private JFrame menu;
	private JFrame RandomLab;
	private JFrame newLab;

	private JButton Iniciar;
	private JButton CriarLabirinto;
	private JButton Sair;

	private JButton ButtonNorte;
	private JButton ButtonSul;
	private JButton ButtonEste;
	private JButton ButtonOeste;
	private JButton ButtonJogar;
	private JButton ButtonMenu;

	private JTextField DimensaoLabirinto;
	private JTextField NrDragoes;
	private LabirintoPanel panel_final;
	private Labirinto lab;
	private Interface inter;

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
		//janela de jogar com tabuleiro aleatorio
		RandomLab = new JFrame();
		RandomLab.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 34));
		RandomLab.setBounds(100, 100, 795, 538);
		RandomLab.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		RandomLab.getContentPane().setLayout(null);

		//janela de menu inicial
		menu = new JFrame();
		menu.setAlwaysOnTop(true);
		menu.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 34));
		menu.setBounds(100, 100, 600, 400);
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.getContentPane().setLayout(null);
		menu.setVisible(true);
		menu.setResizable(false);

		//janela de criacao de labirinto
		JFrame newLab = new JFrame();
		newLab.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 34));
		newLab.setBounds(100, 100, 1256, 720);
		newLab.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		newLab.getContentPane().setLayout(null);

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

				case KeyEvent.VK_LEFT: MoveHeroi('o');
				break;
				case KeyEvent.VK_RIGHT: MoveHeroi('e');
				break;
				case KeyEvent.VK_UP:  MoveHeroi('n');
				break;
				case KeyEvent.VK_DOWN: MoveHeroi('s'); 
				break;

				}

				panel_final.repaint(); 
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



		ButtonNorte = new JButton("Norte");
		ButtonNorte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MoveHeroi('n');
			}
		});
		ButtonNorte.setBounds(585, 117, 120, 49);
		RandomLab.getContentPane().add(ButtonNorte);

		ButtonOeste = new JButton("Oeste");
		ButtonOeste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MoveHeroi('o');
			}
		});
		ButtonOeste.setBounds(523, 177, 120, 49);
		RandomLab.getContentPane().add(ButtonOeste);

		ButtonEste = new JButton("Este");
		ButtonEste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MoveHeroi('e');
			}
		});
		ButtonEste.setBounds(649, 177, 120, 49);
		RandomLab.getContentPane().add(ButtonEste);

		ButtonSul = new JButton("Sul");
		ButtonSul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MoveHeroi('s');
			}
		});
		ButtonSul.setBounds(585, 237, 120, 49);
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
				ButtonEste.setFocusable(false);
				ButtonNorte.setEnabled(true);
				ButtonNorte.setFocusable(false);
				ButtonSul.setEnabled(true);
				ButtonSul.setFocusable(false);
				ButtonOeste.setEnabled(true);
				ButtonOeste.setFocusable(false);

				lblEtiquetaEstado.setText("Pode Jogar!");

				panel_final.repaint();
			}
		});
		ButtonJogar.setBounds(553, 337, 100, 49);
		RandomLab.getContentPane().add(ButtonJogar);
		
		ButtonMenu = new JButton("Menu");
		ButtonMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.setVisible(true);
				RandomLab.setVisible(false);
			}
		});
		ButtonMenu.setBounds(653, 337, 100, 49);
		RandomLab.getContentPane().add(ButtonMenu);

		panel_final.repaint();
	}

	private Object stener() {
		// TODO Auto-generated method stub
		return null;
	}

	/* Configuracao da janela de criacao de labirinto */









	/*
	 * Funcao responsavel pelos movimentos do dragao e do heroi*/
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

			panel_final.repaint();

			panel_final.setFocusable(false);
			ButtonEste.setEnabled(false);
			ButtonNorte.setEnabled(false);
			ButtonSul.setEnabled(false);
			ButtonOeste.setEnabled(false);
		}
	}
}
