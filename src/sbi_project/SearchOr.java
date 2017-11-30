package sbi_project;

import java.util.ArrayList;
import java.util.HashMap;

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

		//Separa as palavras para serem buscadas
		String [] words = data.split("\\s+");
		
		/**
		 * Busca OR - lista todas as ocorrências das palavras buscadas,
		 * caso uma palavra esteja em um arquivo, isto já é suficiente
		 * para ela ser apresentada.
		 */
		
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
				 * Se uma palavra for encontrada então colocar 
				 * arquivar as ocorrencias dela.
				 */
				indices = busca.getIndices();
				for ( int j=0; j < indices.size(); j++)
				{
					/**
					 * Montar frase de listagem
					 */
					Index ind = indices.get(j);
					String aux = ind.montarFrase(words[i]);
					
					ocorrencias.add(aux.toString());
				}
			}
		}
		
		return ocorrencias;
	}
	
	
}
