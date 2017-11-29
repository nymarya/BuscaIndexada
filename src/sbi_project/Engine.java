package sbi_project;

import java.io.IOException;
import java.util.ArrayList;

//importa classe Trie de Tree
import Tree.Trie;
import Tree.Node;
import Tree.TreeException;
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
	 * @throws TreeException 
	 */
	public boolean addBlacklist( String file ) throws IOException, TreeException{
		
		Parser p = new Parser(file);
		Pair<String, Integer> palavra = new Pair<String, Integer>();
		while( p.hasNext() ) {
			palavra = p.next();
			
			if( palavra != null) {
				blacklist.insertWord(palavra.getFirst(), palavra.getSecond(), file);
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
	public boolean addFile( String file ) throws IOException, TreeException{
		
		Parser p = new Parser(file);
		Pair<String, Integer> token = new Pair<String, Integer>();
		while( p.hasNext() ) {
			token = p.next();

			if( token != null) {
				//Recupera palavra e linha
				String word = token.getFirst().toLowerCase();
				int line = token.getSecond();
				
				//Checa se palavra está na blacklist
				Node forbidden = blacklist.search(word);
				
				//Se não está, faz a inserção
				if(forbidden == null) {
					//Adiciona palavra no banco de dados
					db.addWord(word, line, file);
						
				}
			}
			
		}
		p.close();
		
		// adicionando o endereco do arquivo inserido na lista
		return db.addFile(file);
		 
	}
	
	/**
	 * Metodo para remover arquivos ao sistema
	 * @param file Endereço do arquivo a ser removido
	 */
	public void removeFile( String file ) throws IOException, TreeException{
		
		db.list();
		
		// remove palavras associadas ao arquivo removido
		Parser p = new Parser(file);
		Pair<String, Integer> token = new Pair<String, Integer>();
		
		// percorre arquivo recuperando palavras
		while( p.hasNext() ) {
			
			token = p.next();

			if( token != null) {
				//Recupera palavra e linha
				String word = token.getFirst().toLowerCase();
				int line = token.getSecond();
				
				// busca a palavra na arvore
				Node node = db.searchNode(word);
				if( node != null ){
					ArrayList<Index> indices = node.getIndices();
					
					// se s� tiver um indice, remove o node - a palavra da arvore
					if( indices.size() == 1 ){
						db.removeWord(word);
						
					}
					// senao percorre os indices do n� e remove indice associado ao arquivo
					else {
						
						// percorre os indices associados � palavra
						for( Index index : indices ){
							
							// verifica se eh o indice do arquivo e linha analisados
							if( index.getArquivo() == file && index.getLinha() == line ){
								
								// remove o indice do arrayList associado ao arquivo
								node.removeIndice(index);
								
							}
						}
					
					}
							
				}
				
			}
		}
		p.close();
		
		System.out.println("DEPOIS DA REMOCAO");
		
		
		db.list();
		// retira arquivo da lista de arquivos
		db.removeFile(file);
		
		
	}
	
	/**
	 * Metodo para recuperar todos os arquivos ao sistema
	 * @return Lista com arquivos
	 */
	public  ArrayList<String> listFile(  ){
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
	 */
	public void updateFile( String file ){
		// stub
	}
	
	public void list() {
		db.list();
	}
	
	
	
}