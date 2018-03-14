package sbi_project;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import Tree.Index;
import Tree.Node;
import Tree.TreeException;


/**
 * Classe filha (heranca) de Search. 
 * Busca por ocorrencias em que todas as palavras aparecem
 * 
 * @authors Gabriel A. Souza, Jaine B. Rannow, Mayra D. Azevedo
 */
public class SearchAnd extends Search {

	/**
	 * Metodo constutor
	 * @param db Banco de dados
	 */
	public SearchAnd( DataBase db ){
		super(db);
	}

	/**
	 * Metodo usado para realizar uma busca
	 * @param data Palavra a ser buscada
	 * @return Ocorrencia em que palavra(s)-chave foi encontrada
	 * @throws TreeException
	 */
	@Override
	public ArrayList<String> search(String data) throws TreeException {
		this.occurrences.clear();
		
		//Guarda arquivos
		ArrayList<String> files = new ArrayList<String>();
		//Guarda índices
		HashMap<String, ArrayList<Index>> temp = new HashMap<String, ArrayList<Index>>();
		
		// Separa as palavras
		String [] words = data.split("\\s+");
		
		/**
		 * Verifica se todas as palavras buscadas existem no banco de dados
		 * e adiciona, todos os índices associados a cada palavra em um 
		 * hashmap, caso alguma das palavras não seja encontrada, a busca 
		 * AND não pode ser executada e então retorna null;
		 */
		
		//Busca primeira palavra na árvore
		Node node = db.searchNode(words[0]);
		temp.put(words[0], node.getIndexes());
		
		//Adiciona todas as ocorrencias da palavra
		for( Index index : node.getIndexes() ) {
			
			if( !files.contains(index.getFile() )) {

				//Armazena arquivo
				files.add(index.getFile());
			}
		}
		
		//Atualiza restante das ocorrencias
		for(int i =1; i < words.length; i++)
		{
			//Busca palavra na árvore
			node = db.searchNode(words[i]);
			ArrayList<Index> indexes = node.getIndices();
			temp.put(words[i], indexes);
			
			//Para cada arquivo, verifica se ele contem o restante das palavras
			//Se não contem, ele é removido
			Iterator<String> it = files.iterator();
			while (it.hasNext())
			{
				String key = it.next();
				
				//flag verificar se deve remover ocorrencia
				boolean shouldDelete = true;
				for( int j = 0; j < indexes.size(); j++) {
					
					//Verifica se palavra está no arquivo
					Index index = indexes.get(j);
					if( index.getFile().equals(key) ) {
						shouldDelete = false;
					} 

				}
				
				if( shouldDelete) {
					it.remove();
				}
			}
		}
		
		//Imprimir os índices em que contém os arquivos 
		// em comum
		for ( int i=0; i < words.length; i++ )
		{
			ArrayList<Index> aux = temp.get(words[i]);
			ArrayList<Index> remaining = new ArrayList<Index>();
			Iterator<Index> it = aux.iterator();
			
			while ( it.hasNext() )
			{
				Index index = it.next();
				if ( files.contains(index.getArquivo()) )
					remaining.add(index);
			}
			
			this.sortResults(remaining, words[i]);
		}
		
		return occurrences;
	}

}
