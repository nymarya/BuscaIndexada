package sbi_project;

import java.util.ArrayList;

// Classe filha (heranca) de Search
// Busca por ocorrencias em que pelo menos uma das palavras aparecem
public class SearchOr extends Search {

	/**
	 * Metodo constutor
	 * @param db Banco de dados
	 */
	public SearchOr( DataBase db ){
		super(db);
	}
	
	@Override
	public ArrayList<String> search(String data) {
		// 1. busca na arvore, retorna nó terminal
		// 2. recupera do node terminal os arquivos e linhas
		
		// antes de adicionar elementos, limpa a lista
		
		ocorrencias.clear();
		
		ocorrencias.add("microsoft.txt: 1 ocorrencia da palavra 'computador' na linha 1");
		ocorrencias.add("apple.txt: 1 ocorrencia da palavra 'computador' na linha 1");
		return ocorrencias;
	}


}
