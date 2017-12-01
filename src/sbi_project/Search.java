package sbi_project;

import java.util.ArrayList;
import java.util.Collections;

import Tree.Index;
import Tree.TreeException;

// Classe abstrata com os metodos e atributos necessarios para realizar uma busca/pesquisa
public abstract class Search {

	// Banco de dados (com arvores e listas)
	protected DataBase db;
	// Dado a ser buscado
	private String data;
	// Lista com ocorrencias da palavra buscada
	protected ArrayList<String> ocorrencias;

	/**
	 *Metodo construtor
	 * @param db Banco de dados do sistema
	 */
	public Search( DataBase db ){
		this.db = db;
		ocorrencias = new ArrayList<>();
	}
			
	/**
	 * Metodo usado para realizar uma busca
	 * @param data Palavra a ser buscada
	 * @return Ocorrencia em que palavra(s)-chave foi encontrada
	 */
	public abstract ArrayList<String> search( String data ) throws TreeException;
	
	/**
	 * Recebe uma lista de índices e uma palavra associada a estes índices
	 * que devem ser adicionado na lista de ocorrências e exibidas na interface
	 * gráfica com a frase já pronta
	 * @param indices  os indices da palavra
	 * @param word   a palavra
	 */
	public void ordenaResultados ( ArrayList<Index> indices, String word )
	{
		this.ocorrencias.clear();
		ArrayList<Index> ocor = new ArrayList<Index>();
		String arquivo;
		Integer ocorrencias;
		/**
		 * Separar os arquivos e a quantidade de ocorrências dessa palavra
		 * no arquivo.
		 */
		for ( int j=0; j < indices.size(); j++)
		{

			arquivo = indices.get(j).getFilename();
			ocorrencias = indices.get(j).getOcorrencia();
			
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

		for (int k = 0; k < ocor.size(); k++)
		{
			for(int j = 0; j < indices.size(); j++)
			{
				Index ind = indices.get(j);

				if (ind.getFilename().equals(ocor.get(k).getFilename()))
				{
					this.ocorrencias.add(ind.montarFrase(word));
				}
					
			}
		}
	}
	
}
