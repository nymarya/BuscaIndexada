package sbi_project;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import Tree.Index;
import Tree.Node;
import Tree.TreeException;

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

	/**
	 * @throws TreeException 
	 * 
	 */
	@Override
	public ArrayList<String> search(String data) throws TreeException {

		//Guarda arquivos e strings
		ArrayList<String> arquivos = new ArrayList<String>();
		
		String [] words = data.split("\\s+");

		//Busca primeira palavra na árvore
		Node node = db.searchNode(words[0]);

		//Adiciona todas as ocorrencias da palavra no HashMap
		for( Index index : node.getIndices() ) {
			
			if( !arquivos.contains(index.getArquivo() )) {

				//Armazena arquivo e string no HashMap
				arquivos.add(index.getArquivo());
			}
		}


		//Atualiza restante das ocorrencias
		for(int i =1; i < words.length; i++)
		{
			//Busca palavra na árvore
			node = db.searchNode(words[i]);
			ArrayList<Index> indices = node.getIndices();

			
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
			
			Iterator<Index> itInd = indices.iterator();
			while ( itInd.hasNext() )
			{
				Index ind = itInd.next();
				//flag verificar se deve remover indice
				boolean shouldDelete = true;
				for ( int k=0; k < arquivos.size(); k++)
				{
					String arq = arquivos.get(k);
					if ( arq.equals(ind.getArquivo()) )
						shouldDelete = false;
				}
				
				if ( shouldDelete )
					itInd.remove();
			}
			
			//Ordenar índices
			this.ordenaResultados(indices, words[i]);
		}
		
		return ocorrencias;
	}

}
