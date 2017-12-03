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
	 * @throws TreeException 
	 * 
	 */
	@Override
	public ArrayList<String> search(String data) throws TreeException {
		this.ocorrencias.clear();
		
		//Guarda arquivos
		ArrayList<String> arquivos = new ArrayList<String>();
		//Guarda índices
		HashMap<String, ArrayList<Index>> indTemporario = new HashMap<String, ArrayList<Index>>();
		
		// Separa as palavras
		String [] words = data.split("\\s+");
		
		/**
		 * Verifica se todas as palavras buscadas existem no banco de dados
		 * e adiciona, todos os índices associados a cada palavra em um 
		 * hashmap, caso alguma das palavras não seja encontrada, a busca 
		 * AND não pode ser executada e então retorna null;
		 */
//		for ( int i =0; i < words.length; i++)
//		{
//			//Buscar Palavra
//			Node node = db.searchNode(words[i]);
//			if ( node != null )
//				indTemporario.put(words[i], node.getIndices());
//			else return null;
//		}
		
		//Busca primeira palavra na árvore
		Node node = db.searchNode(words[0]);
		indTemporario.put(words[0], node.getIndices());
		
		//Adiciona todas as ocorrencias da palavra
		for( Index index : node.getIndices() ) {
			
			if( !arquivos.contains(index.getArquivo() )) {

				//Armazena arquivo
				arquivos.add(index.getArquivo());
			}
		}
		
		//Atualiza restante das ocorrencias
		for(int i =1; i < words.length; i++)
		{
			//Busca palavra na árvore
			node = db.searchNode(words[i]);
			ArrayList<Index> indices = node.getIndices();
			indTemporario.put(words[i], indices);
			
			//Para cada arquivo, verifica se ele contem o restante das palavras
			//Se não contem, ele é removido
			Iterator<String> it = arquivos.iterator();
			while (it.hasNext())
			{
				String key = it.next();
				
				//flag verificar se deve remover ocorrencia
				boolean shouldDelete = true;
				for( int j = 0; j < indices.size(); j++) {
					
					//Verifica se palavra está no arquivo
					Index index = indices.get(j);
					if( index.getArquivo().equals(key) ) {
						shouldDelete = false;
					} 

				}
				
				if( shouldDelete)
					it.remove();
			}
		}
		
		/**
		 * Imprimir os índices em que contém os arquivos 
		 * em comum
		 */
		for ( int i=0; i < words.length; i++ )
		{
			ArrayList<Index> indicesTemp = indTemporario.get(words[i]);
			ArrayList<Index> indicesRestantes = new ArrayList<Index>();
			Iterator<Index> it = indicesTemp.iterator();
			
			while ( it.hasNext() )
			{
				Index ind = it.next();
				if ( arquivos.contains(ind.getArquivo()) )
					indicesRestantes.add(ind);
			}
			
			this.ordenaResultados(indicesRestantes, words[i]);
		}
		
		return ocorrencias;
	}

}
