package sbi_project;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

//importa classe Trie de Tree
import Tree.Trie;
import Tree.Index;
import Tree.Node;
import Tree.TreeException;

/**
 * Classe com os metodos e atributos correspondentes a indexacao de arquivos e palavras no sistema
 * Realiza comunicação entre interface e banco de dados.
 *
 * @authors Gabriel A. Souza, Jaine B. Rannow, Mayra D. Azevedo
 */
public class Engine implements Serializable{

	
	
	private static final long serialVersionUID = 3680429638771644168L;
	// Banco de dados (com arvores e listas)
	private DataBase db;
	// arvore (ED) com palavras que formam a lista negra
	private Trie forbiddenWords;
	
	
	public Engine( DataBase db ){
		this.db = db;

		char[] letters = { '-','a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i','j', 'k', 'l', 'm', 
				'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x','y', 'z', 'à', 'á', 'â', 'ã',
				'ç', 'è', 'é', 'ê', 'ì', 'í', 'î', 'ò', 'ó', 'ô','õ', 'ù', 'ú', 'û'};
		
		forbiddenWords = new Trie( letters );
	}
	
	/**
	 * Metodo para adicionar arquivo blacklist
	 * @param file Endereco do arquivo
	 * @return Retorna se arquivo foi adicionado corretamente
	 * @throws IOException 
	 * @throws TreeException 
	 */
	public boolean addBlacklist( String file ) throws IOException, TreeException{
		
		Parser p = new Parser(file);
		Pair<String, Integer> word = new Pair<String, Integer>();
		while( p.hasNext() ) {
			word = p.next();
			
			if( word != null) {
				forbiddenWords.insertWord(word.getFirst(), word.getSecond(), file);
			}
				
		}
		p.close();
		
		return true;
	}
	
	/**
	 * Metodo para remover arquivo blacklist
	 * @param file Endereco do arquivo
	 */
	public void removeBlacklist( String file ){
		
		
		
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
	 * @throws TreeException 
	 */
	public boolean addFile( String file ) throws IOException, TreeException {
		
		boolean isAdd = db.addFile(file);
		if( isAdd ) {
			Parser parser = new Parser(file);
			Pair<String, Integer> token = new Pair<String, Integer>();
			while( parser.hasNext() ) {
				token = parser.next();

				if( token != null) {
					//Recupera palavra e linha
					String word = token.getFirst().toLowerCase();
					int line = token.getSecond();
					
					//Checa se palavra está na blacklist
					Node forbidden = forbiddenWords.search(word);
					
					//Se não está, faz a inserção
					if(forbidden == null) {
						//Adiciona palavra no banco de dados
						db.addWord(word, line, file);
							
					}
				}
				
			}
			p.close();
			
			return true;
			
		} else {
			return false;
		}
		
		

		 
	}
	
	/**
	 * Metodo para remover arquivos ao sistema
	 * @param file Endereço do arquivo a ser removido
	 */
	public void removeFile( String file ) throws IOException, TreeException {
		
		
		// remove palavras associadas ao arquivo removido
		Parser parser = new Parser(file);
		Pair<String, Integer> token = new Pair<String, Integer>();
		
		// percorre arquivo recuperando palavras
		while( parser.hasNext() ) {
			
			token = parser.next();

			if( token != null) {
				//Recupera palavra e linha
				String word = token.getFirst().toLowerCase();
				int line = token.getSecond();
				
				// busca a palavra na arvore
				Node node = db.searchNode(word);
				if( node != null ){
					ArrayList<Index> indexes = node.getIndexes();
					
					// se só tiver um indice, remove o node - a palavra da arvore
					if( indexes.size() == 1 ){
						db.removeWord(word);
					}
					// senao percorre os indices do nó e remove indice associado ao arquivo
					else if( indexes.size() != 0 ){
						
						Index target = null;
						
						// percorre os indices associados à palavra
						for( Index index : indexes ){
							
							// verifica se eh o indice do arquivo e linha analisados
							if( index.getFile() == file && index.getLine() == line ){
								
								// remove o indice do arrayList associado ao arquivo
								target = index;
								
							}
						}
						node.removeIndex(target);
					}
							
				}
				
			}
		}
		p.close();
				
		
		// retira arquivo da lista de arquivos
		db.removeFile(file);
		
		
	}
	
	/**
	 * Metodo para recuperar todos os arquivos ao sistema
	 * @return Lista com arquivos
	 * @throws IOException 
	 */
	public  ArrayList<String> listFiles(  ) throws IOException {
		
		return db.getFiles();
				
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
	 * @throws IOException 
	 * @throws TreeException 
	 */
	public void updateFile( String file ) throws IOException, TreeException{
		
		db.list();

		
		Parser parser = new Parser(file);
		Pair<String, Integer> token = new Pair<String, Integer>();
		
		// percorre arquivo recuperando palavras
		while( parser.hasNext() ) {
			
			token = parser.next();

			if( token != null) {
				//Recupera palavra e linha
				String word = token.getFirst().toLowerCase();
				int line = token.getSecond();
				
				// busca a palavra na arvore
				Node node = db.searchNode(word);
				
				// se nao encontrar palavra
				if( node == null ) {
					db.addWord(word, line, file);
				} else {
					
					// recupera indices da palavra
					ArrayList<Index> indexes = node.getIndexes();
					
					boolean found = false;
					
					for( Index index : indexes ) {
						
						// verifica se eh o indice do arquivo e linha analisados
						if( index.getFile() == file && index.getLine() == line ) {
							found = true;
						}
						
					}
					
					if( !found ) {
						db.addWord(word, line, file);
					}
					
					
				}
				
				
				
				
			}
		}
		p.close();
		
		
		db.list();

		
	}
	
	/**
	 * Lista todas as palavras no banco de dados.
	 */
	public void list() {
		db.list();
	}
	
	/**
	 * Retorna o banco de dados usado pela engine.
	 * @return Banco de dados.
	 */
	public DataBase getDB() {
		return db;
	}
	
	
	
}
