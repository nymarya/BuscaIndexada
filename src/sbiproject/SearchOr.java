package sbiproject;

import Tree.Index;
import Tree.Node;
import Tree.TreeException;

import java.util.ArrayList;

/**
 * Classe filha (heranca) de Search
 * Busca por ocorrencias em que pelo menos uma das palavras aparecem
 *
 * @authors Gabriel A. Souza, Jaine B. Rannow, Mayra D. Azevedo
 */
public class SearchOr extends Search {

  /**
   * Metodo constutor.
   * @param db Banco de dados
   */
  public SearchOr(DataBase db) {
    super(db);
  }
  
  /**
   * Metodo usado para realizar uma busca.
   * @param data Palavra a ser buscada
   * @return Ocorrencia em que palavra(s)-chave foi encontrada
   * @throws TreeException Exceção
   */
  @Override
  public ArrayList<String> search(String data) throws TreeException {
    this.occurrences.clear();
    //Separa as palavras para serem buscadas
    String [] words = data.split("\\s+");
    
    /**
     * Busca OR - lista todas as ocorrências das palavras buscadas,
     * caso uma palavra esteja em um arquivo, isto já é suficiente
     * para ela ser apresentada.
     */
    ArrayList<Index> indexes = new ArrayList<Index>();
    
    // Busca as palavras 
    for (int i = 0; i < words.length; i++) {
      
      Node node = db.searchNode(words[i]);
      if (node != null) {
        /**
         * Se uma palavra for encontrada então arquivar as ocorrencias dela.
         * Depois ordenar os índices de acordo com a quantidade de palavras 
         * encontradas nas linhas.
         */
        indexes = node.getIndexes();
        this.sortResults(indexes, words[i]);
      }
    }
    
    return occurrences;
  }
  
  
}
  
