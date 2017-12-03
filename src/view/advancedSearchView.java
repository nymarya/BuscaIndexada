package view;


import java.awt.Color;
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
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Tree.TreeException;
import sbi_project.Engine;
import sbi_project.SearchAnd;
import sbi_project.SearchOr;

import javax.swing.JTextField;

/**
 * Classe responsável pela tela de busca avançada
 *
 */
public class advancedSearchView extends JFrame {

	// Instancia de engine
	private Engine engine;
	// Instancia de searchAnd
	private SearchAnd searchAnd;
	// Instancia de searchOr
	private SearchOr searchOr;


	private JPanel contentPane;
	private JTextField txtBusca;
	private DefaultListModel modelo;


	/**
	 * Cria tela.
	 */
	public advancedSearchView( Engine engine, SearchAnd searchAnd, SearchOr searchOr ) {
		this.engine = engine;
		this.searchAnd = searchAnd;
		this.searchOr  = searchOr;
		this.setTitle("SBI");
		indexView();
	}

	/**
	 * Forma tela
	 */
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

		modelo = new DefaultListModel();
		JList resultBusca = new JList(modelo);
		resultBusca.setVisibleRowCount(10);
		resultBusca.setBackground(new Color(245, 245, 245));
		resultBusca.setBounds(53, 78, 475, 230);
		panel.add(resultBusca);


		txtBusca = new JTextField();
		txtBusca.setFont(new Font("Open Sans", Font.PLAIN, 13));
		txtBusca.setBounds(64, 357, 440, 28);
		panel.add(txtBusca);
		txtBusca.setColumns(10);

		JButton btnBuscaAnd = new JButton("Busca AND");
		btnBuscaAnd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if( !(txtBusca.getText().isEmpty()) ){
					// limpa lista
					modelo.clear();
					ArrayList<String> result;
					try {
						result = searchAnd.search(txtBusca.getText());

						for( String element : result ){
							modelo.addElement(element);
						}
					} catch (TreeException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}


				}

			}
		});
		btnBuscaAnd.setFont(new Font("Open Sans", Font.PLAIN, 13));
		btnBuscaAnd.setBounds(376, 400, 115, 23);
		panel.add(btnBuscaAnd);

		JButton btnBuscaOr = new JButton("Busca OR");
		btnBuscaOr.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if( !(txtBusca.getText().isEmpty()) ){
					// limpa lista
					modelo.clear();
					ArrayList<String> result;

					try 
					{
						result = searchOr.search(txtBusca.getText());
						for( String element : result )
							modelo.addElement(element);

					}catch (TreeException e1) 
					{
						e1.printStackTrace();
					}				

				}

			}
		});
		btnBuscaOr.setFont(new Font("Open Sans", Font.PLAIN, 13));
		btnBuscaOr.setBounds(251, 400, 115, 23);
		panel.add(btnBuscaOr);

	}


	public void windowClosing() {
		System.out.println("teste");
		this.dispose();
	}


}
