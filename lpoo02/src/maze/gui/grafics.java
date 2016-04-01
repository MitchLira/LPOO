package maze.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JTextField;

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

	private JFrame frame;
	private JTextField txtLabirinto;
	private JTextField DimensaoLabirinto;
	private JTextField NrDragoes;
	
	private Labirinto lab;
	private Interface inter;
	
	//botoes
	private JButton ButtonNorte;
	private JButton ButtonOeste;
	private JButton ButtonSul;
	private JButton ButtonEste;
	
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
					window.frame.setVisible(true);
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
		frame.setVisible(visible);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 34));
		frame.setBounds(100, 100, 1276, 731);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		LabirintoPanel panel_final = new LabirintoPanel();
		panel_final.setBounds(58, 167, 517, 465);
		panel_final.setFocusable(true);
		frame.getContentPane().add(panel_final);
		
		
		
		ButtonNorte = new JButton("Norte");
		ButtonNorte.setForeground(Color.BLACK);
		ButtonNorte.setEnabled(false);
		ButtonNorte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MoveHeroi('n');
				panel_final.repaint();
			}
		});
		ButtonNorte.setBounds(826, 442, 106, 52);
		frame.getContentPane().add(ButtonNorte);
		
		ButtonOeste = new JButton("Oeste");
		ButtonOeste.setBounds(756, 512, 106, 52);
		ButtonOeste.setEnabled(false);
		ButtonOeste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MoveHeroi('o');
				panel_final.repaint();
			}
		});
		frame.getContentPane().add(ButtonOeste);
		
		ButtonEste = new JButton("Este");
		ButtonEste.setBounds(878, 512, 106, 52);
		ButtonEste.setEnabled(false);
		ButtonEste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MoveHeroi('e');
				panel_final.repaint();
			}
		});
		frame.getContentPane().add(ButtonEste);
		
		ButtonSul = new JButton("Sul");
		ButtonSul.setEnabled(false);
		ButtonSul.setBounds(826, 576, 106, 52);
		frame.getContentPane().add(ButtonSul);
		ButtonSul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MoveHeroi('s');
				panel_final.repaint();
			}
		});
		
		
		txtLabirinto = new JTextField();
		txtLabirinto.setEditable(false);
		txtLabirinto.setHorizontalAlignment(SwingConstants.CENTER);
		txtLabirinto.setFont(new Font("Swis721 BlkEx BT", Font.BOLD | Font.ITALIC, 17));
		txtLabirinto.setText(" LABIRINTO");
		txtLabirinto.setBounds(493, 37, 308, 60);
		frame.getContentPane().add(txtLabirinto);
		txtLabirinto.setColumns(10);
		
		JLabel lblDimensoDoLabirinto = new JLabel("Dimens\u00E3o do labirinto");
		lblDimensoDoLabirinto.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblDimensoDoLabirinto.setBounds(712, 158, 211, 43);
		frame.getContentPane().add(lblDimensoDoLabirinto);
		
		DimensaoLabirinto = new JTextField();
		DimensaoLabirinto.setBounds(935, 167, 131, 28);
		frame.getContentPane().add(DimensaoLabirinto);
		DimensaoLabirinto.setColumns(10);
		
		NrDragoes = new JTextField();
		NrDragoes.setColumns(10);
		NrDragoes.setBounds(935, 223, 131, 28);
		frame.getContentPane().add(NrDragoes);
		
		JLabel lblNmeroDeDrages = new JLabel("N\u00FAmero de drag\u00F5es");
		lblNmeroDeDrages.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNmeroDeDrages.setBounds(712, 214, 211, 43);
		frame.getContentPane().add(lblNmeroDeDrages);
		
		JButton ButtonSair = new JButton("Sair");
		ButtonSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		ButtonSair.setBounds(686, 364, 140, 52);
		frame.getContentPane().add(ButtonSair);
				
		JLabel lblModoDeJogo = new JLabel("Modo de jogo");
		lblModoDeJogo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblModoDeJogo.setBounds(712, 273, 211, 43);
		frame.getContentPane().add(lblModoDeJogo);
		
		JComboBox MododeJogo = new JComboBox();
		MododeJogo.setModel(new DefaultComboBoxModel(new String[] {"Basico", "Aleatorio", "Adormecido"}));
		MododeJogo.setSelectedIndex(-1);
		MododeJogo.setBounds(935, 281, 131, 35);
		frame.getContentPane().add(MododeJogo);
		
		JButton ButtonJogar = new JButton("Jogar");
		ButtonJogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				lab = new Labirinto();
				
				String mode = (String)MododeJogo.getSelectedItem();
				modoJogo = lab.modoJogotoInt(mode);
				
				int dimensaolab = Integer.parseInt(DimensaoLabirinto.getText());
				int nrDragoes = Integer.parseInt(NrDragoes.getText());
				
				ArrayList<Point> posLivres = lab.GerarLabirinto(dimensaolab);
				lab.ColocarCarateres(posLivres, nrDragoes);
				panel_final.setLabirinto(lab);
				
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
		ButtonJogar.setBounds(899, 364, 140, 52);
		frame.getContentPane().add(ButtonJogar);
		
		lblEtiquetaEstado = new JLabel("Inicio!");
		lblEtiquetaEstado.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEtiquetaEstado.setBounds(58, 637, 106, 44);
		frame.getContentPane().add(lblEtiquetaEstado);
		
		panel_final.repaint();
	}
	
	
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
				lblEtiquetaEstado.setText("Perdeu!");
			else
				lblEtiquetaEstado.setText("Venceu!");
			
			ButtonEste.setEnabled(false);
			ButtonNorte.setEnabled(false);
			ButtonSul.setEnabled(false);
			ButtonOeste.setEnabled(false);
		}
	}
}
