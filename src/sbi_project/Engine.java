package sbi_project;

import java.io.IOException;

//importa classe Trie de Tree
import Tree.Trie;
import Tree.Node;
import Tree.Index;

// Classe com os metodos e atributos correspondentes a indexacao de arquivos e palavras no sistema
public class Engine {

	// Banco de dados (com arvores e listas)
	private DataBase db;
	// arvore (ED) com palavras indisponiveis para a busca
	private Trie blacklist;
	
	
	public Engine( DataBase db ){
		this.db = db;
		char[] alfa = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i','j', 'k', 'l', 'm', 
				'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x','y', 'z'};
		
		blacklist = new Trie( alfa );
	}
	
	/**
	 * Metodo para adicionar arquivo blacklist
	 * @param file Endereco do arquivo
	 * @return Retorna se arquivo foi adicionado corretamente
	 * @throws IOException 
	 */
	public boolean addBlacklist( String file ) throws IOException{
		
		Parser p = new Parser(file);
		Pair<String, Integer> palavra = new Pair<String, Integer>();
		while( p.hasNext() ) {
			palavra = p.next();
			
			if( palavra != null) {
				blacklist.insertWord(palavra.getFirst(), palavra.getSecond(), file);
			}
				
		}
		p.close();
		
		// adicionando o endereco do arquivo inserido na lista
		return true;
	}
	
	/**
	 * Metodo para remover arquivo blacklist
	 * @param file Endereco do arquivo
	 */
	public void removeBlacklist( String file ){
		// stub
	}
	
	/**
	 * Metodo para adicionar palavra a blacklist
	 * @param word Palavra a ser adicionada
	 */
	public void addBlacklist( char word ){
		// stub
		
	}
	
	/**
	 * Metodo para remover palavra da blacklist
	 * @param word Palavra a ser removida
	 */
	public void removeBlacklist( char word ){
		// stub
	}
	
	/**
	 * Metodo para adicionar arquivos ao sistema
	 * @param file Endereco do arquivo a ser adicionado
	 * @return Retorna se foi adicionado corretamente
	 * @throws IOException 
	 */
	public boolean addFile( String file ) throws IOException{
		
		Parser p = new Parser(file);
		Pair<String, Integer> palavra = new Pair<String, Integer>();
		while( p.hasNext() ) {
			palavra = p.next();

			if( palavra != null) {
				//Checa se palavra está na blacklist
				Node proibida = blacklist.search(palavra.getFirst());
				
				//Se não está, faz a inserção
				if(proibida == null) {
					//Busca palavra no banco de dados
					Node resultado = db.searchNode(palavra.getFirst());
					if( resultado != null) {
						//Se já houver palavra naquele arquivo e naquela linha,
						//atualiza número de ocorrencias
						Index indice = resultado.getIndice(file, palavra.getSecond());
						if( indice != null) {
							int ocorrencias = indice.getOcorrencia();
							indice.setOcorrencia(++ocorrencias);
						} else {
							//cria novo indice
							indice = new Index(palavra.getSecond(), file, 1);
						}
					} else {
						db.addWord(palavra.getFirst(), palavra.getSecond(), file);
					}
					
					
				}
			}
			
			
			//System.out.println(palavra);
		}
		p.close();
		
		// adicionando o endereco do arquivo inserido na lista
		return db.addFile(file);
		 
	}
	
	/**
	 * Metodo para remover arquivos ao sistema
	 * @param file Endereço do arquivo a ser removido
	 */
	public void removeFile( String file ){
		// stub
	}
	
	/**
	 * Metodo para validar se arquivos existe e esta correto
	 * @param file Enderecao do arquivo a ser validado
	 */
	public void validateFile( String file ){
		// stub
	}
	
	/**
	 * Metodo para atualizar um arquivo do sistema
	 * @param file Endereco do arquivo a ser atualizado
	 */
	public void updateFile( String file ){
		// stub
	}
	
	
}