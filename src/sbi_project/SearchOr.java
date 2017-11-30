package sbi_project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
		ArrayList<Index> ocor = new ArrayList<Index>();
		
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
			
				/**
				 * Separar os arquivos e a quantidade de ocorrências dessa palavra
				 * no arquivo.
				 */
				for ( int j=0; j < indices.size(); j++)
				{
					System.out.println("indices: " + j);
					String arquivo = indices.get(j).getFilename();
					Integer ocorrencias = indices.get(j).getOcorrencia();
					
					System.out.println(arquivo);
					System.out.println(ocorrencias);
					/**
					 * Verificar se o arquivo já foi rotulado
					 */
					Index aux;
					boolean achou = false;
					for ( int k=0; k < ocor.size() && !achou; k++ )
					{
						aux = ocor.get(k);
						if (aux.getArquivo().equals(arquivo))
						{
							aux.setOcorrencia(aux.getOcorrencia()+ocorrencias);
							achou = true;
						}							
					}
					
					/**
					 * Se o arquivo ainda não foi indentificado, adicionar
					 */
					if (!achou)
						ocor.add(new Index(arquivo, ocorrencias));
				}
				
				/**
				 * Ordena os resultados da busca
				 */
				Collections.sort(ocor);
				Collections.sort(indices);
				System.out.println(ocor.size());
				for (int k = 0; k < ocor.size(); k++)
				{
					for(int j = 0; j < indices.size(); j++)
					{
						Index ind = indices.get(j);
						System.out.println(ocor.get(k).getFilename());
						System.out.println(ind.getArquivo());
						if (ind.getFilename().equals(ocor.get(k).getFilename()))
						{
							this.ocorrencias.add(ind.montarFrase(words[i]));
							System.out.println(ind.montarFrase(words[i]));
						}
							
					}
				}
			}
		}
		
		return ocorrencias;
	}
	
	
}
	