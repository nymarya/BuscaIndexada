package sbiproject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;


/**
 * Classe responsável por ler arquivos e separar palavras
 *
 * @authors Gabriel A. Souza, Jaine B. Rannow, Mayra D. Azevedo
 */
public class Parser {

  // Endereco do arquivo a ser tokenizado 
  private String file;
  private FileReader freader;
  
  //Lê caracteres, arrays e linhas
  private BufferedReader breader;

  //Auxiliar para ler arquivos.
  private String line;
  //Guarda os tokens da string
  private Deque<String> tokens;
  
  //mantém a linha atual
  private int lineCount;
  

  /**
   * Metodo construtor.
   * @param file Endereco do arquivo
   * @throws IOException Exceção
   */
  public Parser(String file)throws IOException {
    this.file = file;
    fReader = new FileReader(file);
    bReader = new BufferedReader(fReader);
    line = bReader.readLine();
    
    tokens = new ArrayDeque<String>();
    String[] parts = line.replace("\u200B", "").split("[\\s\\d\\p{Punct}]+");
    for (String p : parts) {
      tokens.addLast(p);
    }
    lineCount = 1;
  }

  /**
   * Retorna uma palavra do arquivo.
   *
   * @return  Uma par formado por palavra e sua respectiva linha
   *          ou nulo se não há mais tokens.
   */
  public Pair<String, Integer> next() throws IOException {

    //Se tokenizer não tem mais tokens, pega proxima lina
    while (tokens.isEmpty()) {
      line = bReader.readLine();
      lineCount++;
      if (line == null) {
        return null;
      }
      
      String[] parts = line.replace("\u200B", "").split("[\\s\\d\\p{Punct}]+");
      for (String p : parts) {
        tokens.addLast(p);
      }
    }
    
    Pair<String, Integer> token = new Pair<String, Integer>(tokens.removeFirst(), lineCount);

    return token;
  }
  
  /**
   * Verifica se ainda existem palavras.
   *
   * @return  Verdadeiro se ainda há palavras, falso caso contrário.
   */
  public boolean hasNext() {
    return (!tokens.isEmpty() || line != null);
  }
    
  /**
   * Fecha streams.
   */
  public void close() throws IOException {
    breader.close();
    freader.close();
  }
}
