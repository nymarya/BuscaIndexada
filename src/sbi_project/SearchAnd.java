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
		HashMap<String, String> arquivos = new HashMap<String, String>();

		String [] words = data.split("\\s+");

		//Busca primeira palavra na árvore
		Node node = db.searchNode(words[0]);

		//Adiciona todas as ocorrencias da primeira palavra no HashMap
		for( Index index : node.getIndices() ) {
			//Cria string a ser armazenada
			String resposta = index.getArquivo() + ": '" + words[0] +"' x" +index.getOcorrencia();
			resposta += " (" + index.getLinha() + ")";

			if( !arquivos.containsKey(index.getArquivo() )) {

				//Armazena arquivo e string no HashMap
				arquivos.put(index.getArquivo(), resposta);
			} else {
				//Atualiza string
				resposta += ";" + arquivos.get(index.getArquivo());
				arquivos.put(index.getArquivo(), resposta);
			}
		}

		//Atualiza restante das ocorrencias
		for(int i =1; i < words.length; i++) {
			//Busca palavra na árvore
			node = db.searchNode(words[i]);
			ArrayList<Index> indices = node.getIndices();

			
			//Para cada arquivo, verifica se ele contem o restante das palavras
			//Se não contem, ele é removido do HashMap
			Iterator<String> it = arquivos.keySet().iterator();
			while (it.hasNext())
			{
				String key = it.next();
				
				//flag verificar se deve remover ocorrencia
				boolean shouldDelete = true;
				for( int j = 0; j < indices.size(); j++) {
					
					//Verifica se palavra está no arquivo
					Index index = indices.get(j);
					if( index.getArquivo() == key) {
						String resposta = index.getArquivo() + ": '" + words[i] +"' x" +index.getOcorrencia();
						resposta += " (" + index.getLinha() + ")";

						//Atualiza string
						resposta += ";" + arquivos.get(index.getArquivo());
						arquivos.put(index.getArquivo(), resposta);
						
						shouldDelete = false;
					} 

				}
				
				if( shouldDelete)
					it.remove();

			}

			

		}
		// antes de adicionar elementos, limpa a lista

		ocorrencias.clear();

		Collection<String> resultados = arquivos.values();
		for( String s : resultados) {
			String [] respostas = s.split(";");
			for( String r : respostas)
				ocorrencias.add(r);
		}
		return ocorrencias;
	}

}
