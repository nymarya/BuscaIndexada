package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import sbi_project.Engine;
import sbi_project.Search;
import sbi_project.SearchAnd;
import sbi_project.SearchOr;

import java.util.ArrayList;

// classe com as interfaces graficas do sistema
public class simpleSearchView extends JFrame{	

	/**
	 * ID
	 */
	private static final long serialVersionUID = 1L;
	
	// Instancia de engine
	private Engine engine;
	// Instancia de searchAnd
	private SearchAnd searchAnd;
	// Instancia de searchOr
	private SearchOr searchOr;
	
	//Componentes
	private JTextField searchBar;
	private JButton btnSearch;
	
	//Componentes index
	private JButton btnIndexar;
	private JButton btnAdvancedSearch;
	private JButton btnIndice;
	private JButton btnListar;
	
	// da index
	private JPanel panelBuscaSimples;
	private JTextField txtBusca;
	
	// da listagem
	private DefaultListModel modelo;
	
	
	
	/**
	 * Metodo construtor
	 * @param engine Instancia de Engine
	 * @param search Instancia de Search
	 */
	public simpleSearchView(Engine engine, SearchAnd searchAnd, SearchOr searchOr)
	{
		this.engine = engine;
		this.searchAnd = searchAnd;
		this.searchOr  = searchOr;
		this.setTitle("SBI");
		indexView();
	}
	
	/*
	 * Exibe interface grafica com tela inicial (menu)
	 */
	public void indexView()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		panelBuscaSimples = new JPanel();
		panelBuscaSimples.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelBuscaSimples);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panelBuscaSimples.setLayout(gbl_contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(112, 128, 144));
		panel.setLayout(null);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		panelBuscaSimples.add(panel, gbc_panel);
		
		
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
		btnBuscaAvancada.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					advancedSearchView frame = new advancedSearchView(engine, searchAnd, searchOr);
					frame.setVisible(true);
					windowClosing();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			
		});
		btnBuscaAvancada.setForeground(new Color(255, 255, 255));
		btnBuscaAvancada.setBackground(new Color(95, 158, 160));
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
		
		
		modelo = new DefaultListModel();
		JList resultBusca = new JList(modelo);
		resultBusca.setVisibleRowCount(10);
		resultBusca.setBackground(new Color(245, 245, 245));
		resultBusca.setBounds(53, 78, 475, 290);
		panel.add(resultBusca);
		
		txtBusca = new JTextField();
		txtBusca.setFont(new Font("Open Sans", Font.PLAIN, 12));
		txtBusca.setBounds(64, 397, 319, 28);
		panel.add(txtBusca);
		txtBusca.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				if( !(txtBusca.getText().isEmpty()) ){
					// limpa lista
					modelo.clear();
					ArrayList<String> result = searchOr.search(txtBusca.getText());
					for( String element : result ){
						modelo.addElement(element);
					}
				}
				
			}
		});
		
		btnBuscar.setBackground(new Color(255, 255, 255));
		btnBuscar.setFont(new Font("Open Sans", Font.BOLD, 13));
		btnBuscar.setBounds(402, 397, 108, 26);
		panel.add(btnBuscar);
		
		JButton btnBuscaSimples = new JButton("Busca Simples");

		btnBuscaSimples.setForeground(new Color(255, 255, 255));
		btnBuscaSimples.setBackground(new Color(112, 128, 144));
		btnBuscaSimples.setFont(new Font("Open Sans", Font.BOLD, 13));
		btnBuscaSimples.setBounds(0, 0, 143, 45);
		panel.add(btnBuscaSimples);
		
	}
	
	public void windowClosing() {
		  this.dispose();
	}
	
	
}
