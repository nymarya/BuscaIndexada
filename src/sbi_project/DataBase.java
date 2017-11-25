package sbi_project;

// importa classe Trie de Tree
import Tree.Trie;

// importa ArrayList
import java.util.ArrayList;

// Classe que representa o banco de dados do sistema
public class DataBase {

	// Arvore (ED) com todos os arquivos inseridos
	private ArrayList<String> files;
	// Arvore (ED) com todas as palavras dos arquivos inseridos
	private Trie words;
	
	
	public DataBase(  ){
		files = new ArrayList<>();
	}
	
	/**
	 * Metodo para adicionar arquivos na arvore
	 * @param file Endereco do arquivo a ser adicionado
	 */
	public boolean addFile( String file ){
		
		return files.add(file);

	}
	
	/**
	 * Metodo para remover arquivo da arvore
	 * @param file Endereco do arquivo a ser removido
	 */
	public void removeFile( String file ){
		
		files.remove(file);
		
	}
	
	/**
	 * Metodo para adicionar palavras na arvore
	 * @param word Palavra a ser adicionada
	 */
	public void addWord( String word, int line, String file){
		
		words.insertWord(word, line, file);
	}
	
	/**
	 * Metodo para remover palavra da arvore
	 * @param word Palavra a ser removida
	 */
	public void removeWord( String word ){
		// stub
	}	
	
}
