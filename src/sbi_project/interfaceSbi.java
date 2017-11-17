package sbi_project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;

public class interfaceSbi extends JFrame {

	private JPanel contentPane;
	private JTextField txtBusca;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					interfaceSbi frame = new interfaceSbi();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public interfaceSbi() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(112, 128, 144));
		panel.setLayout(null);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);
		
		JButton btnIndexar = new JButton("Indexar Arquivo");
		btnIndexar.setForeground(new Color(255, 255, 255));
		btnIndexar.setFont(new Font("Open Sans", Font.BOLD, 13));
		btnIndexar.setBackground(new Color(95, 158, 160));
		btnIndexar.setBounds(140, 0, 137, 45);
		panel.add(btnIndexar);
		
		JButton btnBuscaAvancada = new JButton("Busca Avan\u00E7ada");
		btnBuscaAvancada.setForeground(new Color(255, 255, 255));
		btnBuscaAvancada.setBackground(new Color(95, 158, 160));
		btnBuscaAvancada.setFont(new Font("Open Sans", Font.BOLD, 13));
		btnBuscaAvancada.setBounds(276, 0, 149, 45);
		panel.add(btnBuscaAvancada);
		
		JButton btnIndice = new JButton("Indice Remissivo");
		btnIndice.setForeground(new Color(255, 255, 255));
		btnIndice.setBackground(new Color(95, 158, 160));
		btnIndice.setFont(new Font("Open Sans", Font.BOLD, 13));
		btnIndice.setBounds(425, 0, 149, 45);
		panel.add(btnIndice);
		
		JList resultBusca = new JList();
		resultBusca.setBackground(new Color(245, 245, 245));
		resultBusca.setBounds(53, 78, 475, 290);
		panel.add(resultBusca);
		
		txtBusca = new JTextField();
		txtBusca.setFont(new Font("Open Sans", Font.PLAIN, 12));
		txtBusca.setBounds(64, 397, 319, 28);
		panel.add(txtBusca);
		txtBusca.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBackground(new Color(255, 255, 255));
		btnBuscar.setFont(new Font("Open Sans", Font.BOLD, 13));
		btnBuscar.setBounds(402, 397, 108, 26);
		panel.add(btnBuscar);
		
		JButton btnBuscaSimples = new JButton("Busca Simples");
		btnBuscaSimples.setForeground(new Color(255, 255, 255));
		btnBuscaSimples.setBackground(new Color(0, 128, 128));
		btnBuscaSimples.setFont(new Font("Open Sans", Font.BOLD, 13));
		btnBuscaSimples.setBounds(0, 0, 143, 45);
		panel.add(btnBuscaSimples);
	}
}
