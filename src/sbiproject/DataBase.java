package sbiproject;

// importa classe Trie de Tree
import Tree.Node;
import Tree.TreeException;
import Tree.Trie;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Classe que representa o banco de dados do sistema.
 *
 * @authors Gabriel A. Souza, Jaine B. Rannow, Mayra D. Azevedo
 */
public class DataBase implements  Serializable {

  //
  private static final long serialVersionUID = 4533597686629028541L;
  // Arvore (ED) com todos os arquivos inseridos
  private ArrayList<String> files;
  // Arvore (ED) com todas as palavras dos arquivos inseridos
  private Trie words;
  
  /** Constrói objeto.
   */  
  public DataBase() {
    files = new ArrayList<>();

    char[] letters = { '-','a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i','j', 'k', 'l', 'm', 
      'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x','y', 'z', 'à', 'á', 'â', 'ã',
      'ç', 'è', 'é', 'ê', 'ì', 'í', 'î', 'ò', 'ó', 'ô','õ', 'ù', 'ú', 'û'};
    words = new Trie(letters);
  }
  
  /** Metodo para adicionar arquivos na arvore.
   * @param file Endereco do arquivo a ser adicionado
   */
  public boolean addFile(String file) {
    
    if (files.contains(file)) {
      return false;
    } else {
      return files.add(file);
    }
    

  }
  
  /* Metodo para remover arquivo da arvore.
   * @param file Endereco do arquivo a ser removido
   */
  public void removeFile(String file) {
    files.remove(file); 
  }
  
  /** Metodo para adicionar palavras na arvore
   * @param word Palavra a ser adicionada
   * @throws TreeException Exceção.
   */
  public void addWord(String word, int line, String file) throws TreeException {
    words.insertWord(word, line, file);
    
  }
  
  /** Metodo para remover palavra da arvore.
   * @param word Palavra a ser removida
   * @throws TreeException Exceção.
   */
  public void removeWord(String word) throws TreeException {
    words.remove(word);
  }
  
  /**
   * Busca palavra no banco de dados.
   * @param word Palavra que será buscada
   * @return Palavra baixa.
   * @throws TreeException Exceção
   */
  public Node searchNode(String word) throws TreeException {
    return words.search(word);
  }
  
  /**
   * Lista palavras da árvore.
   */
  public void list() {
    words.listTree(words.getRoot(),  new StringBuffer());
  }

  /**  Retorna arquivos.
   * @return Todos os arquivos do banco
   */
  public ArrayList<String> getFiles() {
    return files;
  }
  
  
  
}
