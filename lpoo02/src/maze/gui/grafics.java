package maze.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JProgressBar;
import javax.swing.DefaultComboBoxModel;

import maze.cli.Interface;
import maze.logic.Heroi;
import maze.logic.Labirinto;

public class grafics {

	private JFrame frame;
	private JTextField textField;
	private JTextField txtLabirinto;
	private JTextField textField_1;
	private JTextField textField_2;
	private Labirinto lab;
	private Interface inter;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					grafics window = new grafics();
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
	public grafics() {
		initialize();
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
		
		JButton btnNewButton = new JButton("Norte");
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setEnabled(true);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lab.moverHeroi(lab.getHeroi(),'n', inter);
			}
		});
		btnNewButton.setBounds(826, 442, 106, 52);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Oeste");
		btnNewButton_1.setBounds(756, 512, 106, 52);
		btnNewButton_1.setEnabled(true);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lab.moverHeroi(lab.getHeroi(),'o', inter);
			}
		});
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Este");
		btnNewButton_2.setBounds(878, 512, 106, 52);
		btnNewButton_2.setEnabled(true);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lab.moverHeroi(lab.getHeroi(),'e', inter);
			}
		});
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnSul = new JButton("Sul");
		btnSul.setEnabled(true);
		btnSul.setBounds(826, 576, 106, 52);
		frame.getContentPane().add(btnSul);
		btnSul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lab.moverHeroi(lab.getHeroi(),'s', inter);
			}
		});
		
		textField = new JTextField();
		textField.setBounds(24, 130, 635, 498);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		txtLabirinto = new JTextField();
		txtLabirinto.setFont(new Font("Swis721 BlkEx BT", Font.BOLD | Font.ITALIC, 17));
		txtLabirinto.setText("         LABIRINTO");
		txtLabirinto.setBounds(493, 37, 308, 60);
		frame.getContentPane().add(txtLabirinto);
		txtLabirinto.setColumns(10);
		
		JLabel lblDimensoDoLabirinto = new JLabel("Dimens\u00E3o do labirinto");
		lblDimensoDoLabirinto.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblDimensoDoLabirinto.setBounds(712, 158, 211, 43);
		frame.getContentPane().add(lblDimensoDoLabirinto);
		
		textField_1 = new JTextField();
		textField_1.setBounds(935, 167, 131, 28);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(935, 223, 131, 28);
		frame.getContentPane().add(textField_2);
		
		JLabel lblNmeroDeDrages = new JLabel("N\u00FAmero de drag\u00F5es");
		lblNmeroDeDrages.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNmeroDeDrages.setBounds(712, 214, 211, 43);
		frame.getContentPane().add(lblNmeroDeDrages);
		
		JButton btnNewButton_3 = new JButton("Sair");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnNewButton_3.setBounds(686, 364, 140, 52);
		frame.getContentPane().add(btnNewButton_3);
		
		
		
		JLabel lblModoDeJogo = new JLabel("Modo de jogo");
		lblModoDeJogo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblModoDeJogo.setBounds(712, 273, 211, 43);
		frame.getContentPane().add(lblModoDeJogo);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Basico", "Aleatorio", "Adormecido"}));
		comboBox.setSelectedIndex(-1);
		comboBox.setBounds(935, 281, 131, 35);
		frame.getContentPane().add(comboBox);
		
		JButton btnJogar = new JButton("Jogar");
		btnJogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lab = new Labirinto();
				int modoJogo =lab.modoJogotoInt(lblModoDeJogo.getText());
				lab.GerarLabirinto(Integer.parseInt((lblDimensoDoLabirinto.getText())));
				//lab.jogar(lab, inter, modoJogo);
				textField.setText(lab.toString());
			}
		});
		btnJogar.setBounds(899, 364, 140, 52);
		frame.getContentPane().add(btnJogar);
	}
}
