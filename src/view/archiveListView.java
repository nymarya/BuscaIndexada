package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Tree.TreeException;
import sbi_project.Engine;
import sbi_project.Pair;
import sbi_project.Parser;
import sbi_project.SearchAnd;
import sbi_project.SearchOr;

import javax.swing.JList;
import javax.swing.JOptionPane;

public class archiveListView extends JFrame {

	// Instancia de engine
	private Engine engine;
	// Instancia de searchAnd
	private SearchAnd searchAnd;
	// Instancia de searchOr
	private SearchOr searchOr;


	private JPanel contentPane;

	// da listagem
	private DefaultListModel<String> modelo;
	
	private JList listArchives;
	
	private ArrayList<String> archiveList;
	
	/**
	 * Create the frame.
	 */
	public archiveListView( Engine engine, SearchAnd searchAnd, SearchOr searchOr ) {
		this.engine = engine;
		this.searchAnd = searchAnd;
		this.searchOr  = searchOr;
		this.setTitle("SBI");
		indexView();
	}

	public void indexView() {

		//Serializa engine ao fechar aplicação
		WindowListener exitListener = new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				//persiste dados em arquivo
				try {

					Engine eng = engine;
					FileOutputStream fileOut = new FileOutputStream(new File("./data/bd.ser").getCanonicalPath());
					ObjectOutputStream out = new ObjectOutputStream(fileOut);
					out.writeObject(eng);
					out.close();
					fileOut.close();
				} catch (IOException i) {
					i.printStackTrace();
				}

				System.exit(0);
			}
		};
		this.addWindowListener(exitListener);

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
		
		JButton btnIndice = new JButton("Lista Arquivos");
		btnIndice.setForeground(new Color(255, 255, 255));
		btnIndice.setBackground(new Color(112, 128, 144));
		btnIndice.setFont(new Font("Open Sans", Font.BOLD, 13));
		btnIndice.setBounds(425, 0, 149, 45);
		panel.add(btnIndice);

		modelo = new DefaultListModel();
		listArchives = new JList(modelo);
		listArchives.setVisibleRowCount(10);
		listArchives.setBackground(new Color(245, 245, 245));
		listArchives.setBounds(50, 80, 480, 250);
		panel.add(listArchives);
		
		// limpa lista
		modelo.clear();
		try {
			archiveList = engine.listFile();
			for( String element : archiveList ){
				
				// recupera apenas nome do arquivo
				//String archiveName = element;
				//int index = archiveName.lastIndexOf('/');
				//archiveName = archiveName.substring(index+1);
				
				int index = element.lastIndexOf("/");
				String archiveName = element.substring(index+1);
				
				
				// contador da quantidade de palavras
				int count = 0;
				
				Parser p = new Parser(element);
				Pair<String, Integer> token = new Pair<String, Integer>();
				
				// percorre arquivo recuperando palavras
				while( p.hasNext() ) {
					
					token = p.next();

					if( token != null) {
						count++;
					}
				}
				
				modelo.addElement(archiveName + " " +count +" palavras");
				
			}
			
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
		
		JButton btnRemoveArchive = new JButton("Remover");
		btnRemoveArchive.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// recupera elemento selecionado
				int selectedIndex = listArchives.getSelectedIndex();
				if( selectedIndex >= 0 ) {
					
					// chama metodo remover da Engine
					try {
						engine.removeFile( archiveList.get(selectedIndex) );
					} catch (IOException | TreeException e1) {
						e1.printStackTrace();
					}
					
				} else {
					JOptionPane.showMessageDialog(null, "Selecione o arquivo que deseja remover");
				}
				// atualizar modelo jList
				modelo.removeElementAt(selectedIndex);
				
			}
		});
		btnRemoveArchive.setBackground(new Color(255, 255, 255));
		btnRemoveArchive.setFont(new Font("Open Sans", Font.BOLD, 13));
		btnRemoveArchive.setBounds(150, 400, 108, 26);
		panel.add(btnRemoveArchive);
		
		
		JButton btnUpdateArchive = new JButton("Atualizar");
		btnUpdateArchive.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// recupera elemento selecionado
				int selectedIndex = listArchives.getSelectedIndex();
				if( selectedIndex >= 0 ) {
					
					// chama metodo atualizar da Engine
					try {
						engine.updateFile( archiveList.get(selectedIndex) );
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (TreeException e1) {
						e1.printStackTrace();
					}

					
				} else {
					JOptionPane.showMessageDialog(null, "Selecione o arquivo que deseja atualizar");
				}
				
			}
		});
		btnUpdateArchive.setBackground(new Color(255, 255, 255));
		btnUpdateArchive.setFont(new Font("Open Sans", Font.BOLD, 13));
		btnUpdateArchive.setBounds(300, 400, 108, 26);
		panel.add(btnUpdateArchive);

		
		
	}


	public void windowClosing() {
		this.dispose();
	}

}