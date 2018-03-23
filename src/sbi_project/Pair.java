package sbiproject;

/**
 * Classe que forma pares de objetos.
 * @author Mayra D. de Azevedo
 *
 * @param <F> Primeiro elemento do par
 * @param <S> Segundo elemento do par
 */
public class Pair<F,S> {

  private F first;
  private S second;

  /** 
   * Constrói objeto da classe Pair.
   */
  public Pair() {
    
  }
  
  /**
   * Constrói objeto para a classe Pair a partir de dois objetos.
   * @param first Primeiro elemento do par
   * @param second Segundo elemento do par
   */
  public Pair(F first, S second) {
    this.first = first;
    this.second = second;
  }

  /*  Recupera primeiro elemento
   * @return O primeiro
   */
  public F getFirst() {
    return first;
  }

  /**
   * Atualiza primeiro elemento.
   * @param first O novo primeiro objeto.
   */
  public void setFirst(F first) {
    this.first = first;
  }

  /**
   * Retorna o segundo elemento do par.
   * @return Segundo elemento.
   */
  public S getSecond() {
    return second;
  }

  /**
   * Atualiza segundo elemento do par.
   * @param second O segundo elemento.
   */
  public void setSecond(S second) {
    this.second = second;
  }
  
  
}
