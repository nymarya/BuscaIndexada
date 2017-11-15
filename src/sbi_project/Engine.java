package sbi_project;

import java.io.IOException;

//importa classe Trie de Tree
import Tree.Trie;

// Classe com os metodos e atributos correspondentes a indexacao de arquivos e palavras no sistema
public class Engine {

	// Banco de dados (com arvores e listas)
	private DataBase db;
	// arvore (ED) com palavras indisponiveis para a busca
	private Trie blacklist;
	
	
	public Engine( DataBase db ){
		this.db = db;
		try {
			Parser p = new Parser("/home/mayra/Progs/BuscaIndexada/data/microsoft.txt");
			String palavra = "";
			while( p.hasNext() ) {
				palavra = p.next();
				//System.out.println("aaa");
				System.out.println(palavra);
			}
			System.out.println("saiu");
			p.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo para adicionar arquivo blacklist
	 * @param file Endereco do arquivo
	 */
	public void addBlacklist( String file ){
		// stub
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
	 */
	public void addFile( String file ){
		// stub
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
