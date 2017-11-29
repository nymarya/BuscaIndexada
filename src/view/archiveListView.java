package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Tree.TreeException;
import sbi_project.Engine;
import sbi_project.SearchAnd;
import sbi_project.SearchOr;

public class archiveListView extends JFrame {


	// Instancia de engine
	private Engine engine;
	// Instancia de searchAnd
	private SearchAnd searchAnd;
	// Instancia de searchOr
	private SearchOr searchOr;

	private JPanel contentPane;
	
	// da listagem
	private DefaultListModel modelo;
	
	private JList listArchives;
	
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
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 400);
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
		
		modelo = new DefaultListModel();
		listArchives = new JList(modelo);
		listArchives.setVisibleRowCount(10);
		listArchives.setBackground(new Color(245, 245, 245));
		listArchives.setBounds(10, 10, 300, 300);
		panel.add(listArchives);
		
		// limpa lista
		modelo.clear();
		ArrayList<String> result = engine.listFile();
		for( String element : result ){
			modelo.addElement(element);
		}
		
		JButton btnRemoveArchive = new JButton("Remover");
		btnRemoveArchive.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// recupera elemento selecionado
				String selectedValue = (String) listArchives.getSelectedValue();
				if( selectedValue != null ) {
					
					// chama metodo remover da Engine
					try {
						engine.removeFile(selectedValue);
					} catch (IOException | TreeException e1) {
						e1.printStackTrace();
					}
					
				} else {
					JOptionPane.showMessageDialog(null, "Selecione o arquivo que deseja remover");
				}
				// atualizar modelo jList
				int selectedIndex = listArchives.getSelectedIndex();
				modelo.removeElementAt(selectedIndex);
				
			}
		});
		btnRemoveArchive.setBackground(new Color(255, 255, 255));
		btnRemoveArchive.setFont(new Font("Open Sans", Font.BOLD, 13));
		btnRemoveArchive.setBounds(330, 90, 108, 26);
		panel.add(btnRemoveArchive);
		
		
		JButton btnUpdateArchive = new JButton("Atualizar");
		btnUpdateArchive.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// recupera elemento selecionado
				String selectedValue = (String) listArchives.getSelectedValue();
				if( selectedValue != null ) {
					
					// chama metodo atualizar da Engine
					try {
						engine.updateFile(selectedValue);
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
		btnUpdateArchive.setBounds(330, 190, 108, 26);
		panel.add(btnUpdateArchive);
		
		
	}
	
}
