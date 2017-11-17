package sbi_project;

// Classe com as caracteristicas gerais do sistema
public class System {

	
	
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
		InterfaceView a = new InterfaceView(engine, searchAnd, searchOr );
		
		// exibe a tela inicial para usuario 
		//view.show();
		a.setVisible(true);

		
	}
	
}
