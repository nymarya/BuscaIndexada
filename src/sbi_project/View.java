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
public class View extends JFrame{

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
	
	/**
	 * Metodo construtor
	 * @param engine Instancia de Engine
	 * @param search Instancia de Search
	 */
	public View( Engine engine, SearchAnd searchAnd, SearchOr searchOr ) {
		
		this.engine = engine;
		this.search = searchAnd;
		this.setTitle("SBI");
	}
	
	
	/*
	 * Exibe interface grafica com tela inicial (menu)
	 */
	public void show(){
		
		JPanel pnlSearch = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.HORIZONTAL;	
		c.weighty = 1;
		c.weightx = 0.5;
		c.insets = new Insets(10,10,10,10);
		
		c.gridx = 0;
	    c.gridy = 0; 
	    searchBar = new JTextField("");
	    pnlSearch.add(searchBar, c);
	    
	    c.gridy = 1;
	    btnSearch = new JButton("");
	    pnlSearch.add(btnSearch, c);
	    
	    this.add(pnlSearch, BorderLayout.SOUTH);
	    
	    setSize(500, 600);
	    setLocationRelativeTo(null);
	    
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
