package sbi_project;


// classe com as interfaces graficas do sistema
public class View {

	// Instancia de engine
	private Engine engine;
	// Instancia de searchAnd
	private Search search;
	// Instancia de searchOr
	private SearchOr searchOr;
	
	/**
	 * Metodo construtor
	 * @param engine Instancia de Engine
	 * @param search Instancia de Search
	 */
	public View( Engine engine, SearchAnd searchAnd, SearchOr searchOr ) {
		
		this.engine = engine;
		this.search = search;
		
	}
	
	
	/*
	 * Exibe interface grafica com tela inicial (menu)
	 */
	public void show(){
		// stub
	}
	
}
