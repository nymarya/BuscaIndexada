package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sbi_project.Engine;
import sbi_project.SearchAnd;
import sbi_project.SearchOr;

import javax.swing.JTextField;

public class advancedSearchView extends JFrame {

	// Instancia de engine
	private Engine engine;
	// Instancia de searchAnd
	private SearchAnd searchAnd;
	// Instancia de searchOr
	private SearchOr searchOr;
	
	
	private JPanel contentPane;
	private JTextField textField;


	/**
	 * Create the frame.
	 */
	public advancedSearchView( Engine engine, SearchAnd searchAnd, SearchOr searchOr ) {
		this.engine = engine;
		this.searchAnd = searchAnd;
		this.searchOr  = searchOr;
		this.setTitle("SBI");
		indexView();
	}
	
	public void indexView() {
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
		
		JButton btnBuscaSimples = new JButton("Busca Simples");
		btnBuscaSimples.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					simpleSearchView frame = new simpleSearchView(engine, searchAnd, searchOr);
					frame.setVisible(true);
					windowClosing();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			
		});
		btnBuscaSimples.setForeground(new Color(255, 255, 255));
		btnBuscaSimples.setBackground(new Color(95, 158, 160));
		btnBuscaSimples.setFont(new Font("Open Sans", Font.BOLD, 13));
		btnBuscaSimples.setBounds(0, 0, 143, 45);
		panel.add(btnBuscaSimples);
		
		JButton btnIndexar = new JButton("Indexar Arquivo");
		btnIndexar.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					engineView frame = new engineView(engine, searchAnd, searchOr);
					frame.setVisible(true);
					windowClosing();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			
		});
		btnIndexar.setForeground(new Color(255, 255, 255));
		btnIndexar.setFont(new Font("Open Sans", Font.BOLD, 13));
		btnIndexar.setBackground(new Color(95, 158, 160));
		btnIndexar.setBounds(140, 0, 137, 45);
		panel.add(btnIndexar);
		
		JButton btnBuscaAvancada = new JButton("Busca Avan\u00E7ada");
		btnBuscaAvancada.setForeground(new Color(255, 255, 255));
		btnBuscaAvancada.setBackground(new Color(112, 128, 144));
		btnBuscaAvancada.setFont(new Font("Open Sans", Font.BOLD, 13));
		btnBuscaAvancada.setBounds(276, 0, 149, 45);
		panel.add(btnBuscaAvancada);
		
		JButton btnIndice = new JButton("Indice Remissivo");
		btnIndice.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					remissiveIndexView frame = new remissiveIndexView(engine, searchAnd, searchOr);
					frame.setVisible(true);
					windowClosing();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			
		});
		btnIndice.setForeground(new Color(255, 255, 255));
		btnIndice.setBackground(new Color(95, 158, 160));
		btnIndice.setFont(new Font("Open Sans", Font.BOLD, 13));
		btnIndice.setBounds(425, 0, 149, 45);
		panel.add(btnIndice);
		
		textField = new JTextField();
		textField.setFont(new Font("Open Sans", Font.PLAIN, 13));
		textField.setBounds(100, 214, 391, 30);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnBuscaAnd = new JButton("Busca AND");
		btnBuscaAnd.setFont(new Font("Open Sans", Font.PLAIN, 13));
		btnBuscaAnd.setBounds(376, 255, 115, 23);
		panel.add(btnBuscaAnd);
		
		JButton btnBuscaOr = new JButton("Busca OR");
		btnBuscaOr.setFont(new Font("Open Sans", Font.PLAIN, 13));
		btnBuscaOr.setBounds(251, 255, 115, 23);
		panel.add(btnBuscaOr);
		
	}
	
	
	public void windowClosing() {
		  this.dispose();
	}
	
	
}