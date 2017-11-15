package sbi_project;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

// classe com as interfaces graficas do sistema
public class InterfaceView extends JFrame{

	/**
	 * ID
	 */
	private static final long serialVersionUID = 1L;
	
	// Instancia de engine
	private Engine engine;
	// Instancia de searchAnd
	private Search search;
	// Instancia de searchOr
	private SearchOr searchOr;
	
	//Componentes
	private JTextField searchBar;
	private JButton btnSearch;
	
	//Componentes indexação
	private JButton btnInserir;
	private JButton btnRemover;
	private JButton btnListar;
	
	/**
	 * Metodo construtor
	 * @param engine Instancia de Engine
	 * @param search Instancia de Search
	 */
	public InterfaceView(Engine engine, SearchAnd searchAnd, SearchOr searchOr)
	{
		this.engine = engine;
		this.search = searchAnd;
		this.setTitle("SBI");
		showSearchBar();	
		indexPanel();
	}
	
	/*
	 * Exibe interface grafica com tela inicial (menu)
	 */
	public void showSearchBar()
	{
		JPanel pnlSearch = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.HORIZONTAL;	
		c.weighty = 1;
		c.weightx = 0.5;
		c.insets = new Insets(10,10,10,10);
		
		c.gridx = 0;
	    c.gridy = 0; 
	    searchBar = new JTextField(20);
	    pnlSearch.add(searchBar, c);
	    
	    c.gridx = 1;
	    btnSearch = new JButton(" Find ");
	    pnlSearch.add(btnSearch, c);
	    
	    this.add(pnlSearch, BorderLayout.SOUTH);
	    
	    this.setSize(350, 250);
	    this.setLocationRelativeTo(null);
	    
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	}
	
	public void indexPanel ()
	{
		JPanel pnlIndex = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.HORIZONTAL;	
		c.weighty = 0;
		c.weightx = 0;
		c.insets = new Insets(10,10,10,10);
	
		c.gridx = 0;
		c.gridy = 0;
		btnInserir = new JButton(" Inserir ");
		pnlIndex.add(btnInserir, c);
		
		c.gridy = 1;
		btnListar = new JButton(" Listar ");
		pnlIndex.add(btnListar, c);
		
		c.gridy = 2;
		btnRemover = new JButton(" Remover ");
		pnlIndex.add(btnRemover, c);
		
		this.add(pnlIndex, BorderLayout.EAST);
	}
	
}
