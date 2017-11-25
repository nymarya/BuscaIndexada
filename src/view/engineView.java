package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import sbi_project.Engine;
import sbi_project.Search;
import sbi_project.SearchAnd;
import sbi_project.SearchOr;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JCheckBox;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

public class engineView extends JFrame {

	// Instancia de engine
	private Engine engine;
	// Instancia de searchAnd
	private SearchAnd searchAnd;
	// Instancia de searchOr
	private SearchOr searchOr;
	
	
	private JPanel contentPane;
	private JTextField txtArchive;

	/**
	 * Create the frame.
	 */
	public engineView( Engine engine, SearchAnd searchAnd, SearchOr searchOr ) {
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
		btnIndexar.setForeground(new Color(255, 255, 255));
		btnIndexar.setFont(new Font("Open Sans", Font.BOLD, 13));
		btnIndexar.setBackground(new Color(112, 128, 144));
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
		
		txtArchive = new JTextField();
		txtArchive.setEditable(false);
		txtArchive.setBounds(112, 140, 194, 33);
		panel.add(txtArchive);
		txtArchive.setColumns(10);
		
		JButton btnProcurar = new JButton("Procurar");
		btnProcurar.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// Trecho de código baseado em:
				// http://www.codejava.net/java-se/swing/show-simple-open-file-dialog-using-jfilechooser
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				int result = fileChooser.showOpenDialog(getParent());
				File selectedFile = null;
				if (result == JFileChooser.APPROVE_OPTION) {
					selectedFile = fileChooser.getSelectedFile();
				    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
				}
				// Fim do trecho de código
				
				txtArchive.setText(selectedFile.getAbsolutePath());
				
			}
		});
		
		
		btnProcurar.setBounds(316, 140, 109, 33);
		panel.add(btnProcurar);
		
		JLabel lblBusqueOArquivo = new JLabel("Busque o arquivo que deseja adicionar \u00E0 base de busca");
		lblBusqueOArquivo.setFont(new Font("Open Sans", Font.PLAIN, 14));
		lblBusqueOArquivo.setForeground(new Color(255, 255, 255));
		lblBusqueOArquivo.setBounds(92, 103, 382, 26);
		panel.add(lblBusqueOArquivo);
		
		JLabel lblSelecioneOTipo = new JLabel("Como deseja adicionar este arquivo?");
		lblSelecioneOTipo.setFont(new Font("Open Sans", Font.PLAIN, 14));
		lblSelecioneOTipo.setForeground(new Color(255, 255, 255));
		lblSelecioneOTipo.setBounds(92, 202, 255, 26);
		panel.add(lblSelecioneOTipo);
		
		JRadioButton radioBtnBlacklist = new JRadioButton("Blacklist");
		radioBtnBlacklist.setFont(new Font("Open Sans", Font.PLAIN, 12));
		radioBtnBlacklist.setBounds(112, 235, 130, 33);
		panel.add(radioBtnBlacklist);
		
		JRadioButton radioBtnInclusoNaBusca = new JRadioButton("Incluso na busca");
		radioBtnInclusoNaBusca.setFont(new Font("Open Sans", Font.PLAIN, 12));
		radioBtnInclusoNaBusca.setBounds(112, 275, 130, 33);
		panel.add(radioBtnInclusoNaBusca);
		
		// adicionando buttons no mesmo grupo
		ButtonGroup bg = new ButtonGroup();
		bg.add(radioBtnInclusoNaBusca);
		bg.add(radioBtnBlacklist);
		
		
		JButton btnAdd = new JButton("Indexar");
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				boolean verify = false;
				// Chamando metodos de adicionar da engine
				if( radioBtnInclusoNaBusca.isSelected() ){
					try {
						verify = engine.addFile(txtArchive.getText());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if( radioBtnBlacklist.isSelected() ){
					try {
						verify = engine.addBlacklist(txtArchive.getText());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Selecione como deseja adicionar o arquivo!");
				}
				
				if( verify == true ){
					JOptionPane.showMessageDialog(null, "Arquivo adicionado com sucesso!");
				} else {
					JOptionPane.showMessageDialog(null, "Não foi possível adicionar este arquivo =/");
				}
				
				txtArchive.setText("");
				bg.clearSelection();
				
			}
		});
		
		btnAdd.setFont(new Font("Open Sans", Font.PLAIN, 14));
		btnAdd.setBounds(316, 342, 109, 39);
		panel.add(btnAdd);
		
	}
	
	
	public void windowClosing() {
		  this.dispose();
	}
	
	
}
