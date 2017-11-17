package sbi_project;

import view.simpleSearchView;

// Classe com as caracteristicas gerais do sistema
public class SearchSystem {

	
	
	// metodo main
	public static void main(String[] args) {
		
		// instancia do banco de dados
		DataBase db   = new DataBase();
		// instancia da engine, passando o bd
		Engine engine = new Engine( db );
		// instancia da busca "and", passando o bd
		SearchAnd searchAnd = new SearchAnd( db );
		// instancia da busca "or", passando o bd
		SearchOr  searchOr  = new SearchOr( db );
		// instancia da view, passando a engine, searchAnd e searchOr 
		simpleSearchView viewSbi = new simpleSearchView(engine, searchAnd, searchOr );
		
		// exibe a tela inicial para usuario 
		viewSbi.setVisible(true);
		
		
		
//		Teste a = new Teste();
//		a.setVisible(true);
		
	}
	
}
