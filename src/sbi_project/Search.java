package sbi_project;

import java.util.ArrayList;
import java.util.Collections;

import Tree.Index;
import Tree.TreeException;

/**
 * Classe abstrata com os metodos e atributos necessarios para realizar uma busca/pesquisa
 *
 * @authors Gabriel A. Souza, Jaine B. Rannow, Mayra D. Azevedo
 */
public abstract class Search {

	// Banco de dados (com arvores e listas)
	protected DataBase db;
	// Dado a ser buscado
	private String data;
	// Lista com ocorrencias da palavra buscada
	protected ArrayList<String> occurrences;

	/**
	 * Metodo construtor
	 * @param db Banco de dados do sistema
	 */
	public Search( DataBase db ){
		this.db = db;
		occurrences = new ArrayList<>();
	}
			
	/**
	 * Metodo usado para realizar uma busca
	 * @param data Palavra a ser buscada
	 * @return Ocorrencia em que palavra(s)-chave foi encontrada
	 * @throws TreeException
	 */
	public abstract ArrayList<String> search( String data ) throws TreeException;
	
	/**
	 * Recebe uma lista de índices e uma palavra associada a estes índices
	 * que devem ser adicionado na lista de ocorrências e exibidas na interface
	 * gráfica com a frase já pronta
	 * @param indexes  os indices da palavra
	 * @param word   a palavra
	 */
	public void sortResults ( ArrayList<Index> indexes, String word ) {
		
		ArrayList<Index> occurrencesAux = new ArrayList<Index>();
		String file = '';
		Integer occurrences = 0;
		/**
		 * Separar os arquivos e a quantidade de ocorrências dessa palavra
		 * no arquivo.
		 */
		for ( int j=0; j < indexes.size(); j++) {

			file = indexes.get(j).getFilename();
			occurrencesAux = indexes.get(j).get();
			
			// Verificar se o arquivo já foi rotulado
			boolean found = false;
			for ( int k=0; k < occurrencesAux.size() && !found; k++ ) {
				Index aux = ocor.get(k);
				if (aux.getFile().equals(file)) {
					aux.setOccurrence(aux.getOccurrence()+occurrences);
					found = true;
				}							
			}
			
			// Se o arquivo ainda não foi indentificado, adicionar
			if (!found){
				occurrencesAux.add(new Index(file, occurrences));
			}
		}
		
		// Ordena os resultados da busca 
		Collections.sort(occurrencesAux);
		Collections.sort(indexes);

		for (int k = 0; k < occurrencesAux.size(); k++) {
			for(int j = 0; j < indexes.size(); j++) {
				Index index = indexes.get(j);

				if (index.getFilename().equals(occurrencesAux.get(k).getFilename())) {
					this.occurrences.add(index.formResponse(word));
				}
					
			}
		}
	}
	
}
