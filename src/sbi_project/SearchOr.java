package sbi_project;

import java.util.ArrayList;

import Tree.Index;
import Tree.Node;
import Tree.TreeException;

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
	public ArrayList<String> search(String data) throws TreeException {
		this.ocorrencias.clear();
		//Separa as palavras para serem buscadas
		String [] words = data.split("\\s+");
		
		/**
		 * Busca OR - lista todas as ocorrências das palavras buscadas,
		 * caso uma palavra esteja em um arquivo, isto já é suficiente
		 * para ela ser apresentada.
		 */
		
		System.out.println("BUscando: >>>>");
		for ( int f=0; f < words.length; f++)
			System.out.println(words[f]);
	
		Node busca;
		ArrayList<Index> indices;
		
		ocorrencias.clear();
		
		/**
		 * Busca as palavras
		 */
		for ( int i=0; i < words.length; i++)
		{
			busca = db.searchNode(words[i]);
			if ( busca != null )
			{
				/**
				 * Se uma palavra for encontrada então arquivar as ocorrencias dela.
				 * Depois ordenar os índices de acordo com a quantidade de palavras 
				 * encontradas nas linhas.
				 */
				indices = busca.getIndices();
				this.ordenaResultados(indices, words[i]);
			}
		}
		
		return ocorrencias;
	}
	
	
}
	