package sbi_project;

import java.util.ArrayList;

// Classe filha (heranca) de Search. 
// Busca por ocorrencias em que todas as palavras aparecem
public class SearchAnd extends Search {

	/**
	 * Metodo constutor
	 * @param db Banco de dados
	 */
	public SearchAnd( DataBase db ){
		super(db);
	}
	
	@Override
	public ArrayList<String> search(String data) {
		
		// 1. busca na arvore, retorna node terminal
		// 2. recupera do node terminal os arquivos e linhas
		
		// antes de adicionar elementos, limpa a lista
		
		ocorrencias.clear();
		
		ocorrencias.add("microsoft.txt: 1 ocorrencia das palavras 'computador' e 'inovacao' na linha 1");
		ocorrencias.add("apple.txt: 1 ocorrencia das palavras 'computador' e 'inovacao' na linha 1");
		return ocorrencias;
	}

}
