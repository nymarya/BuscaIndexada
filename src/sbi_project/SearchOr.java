package sbi_project;


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
	public String search(String data) {
		// 1. busca na arvore, retorna nó terminal
		// 2. recupera do nó terminal os arquivos e linhas
		
		
		return "microsoft.txt: 1 ocorrência da palavra 'computador' na linha 1"
				+ "\napple.txt:​ 1 ocorrência​ ​da​ ​palavra​ ​'computador' na​ ​linha​ 1";
	}

	// microsoft.txt:​ ​ ​ 1 ​ ​ ocorrência​ ​ da​ ​ palavra​ ​ ‘computador’​ ​ na​ ​ linha​ ​ 1 

}
