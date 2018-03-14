package sbi_project;

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
	 * @param first
	 * @param second
	 */
	public Pair() {
		
	}
	
	/**
	 * Constrói objeto para a classe Pair a partir de dois objetos.
	 * @param first
	 * @param second
	 */
	public Pair(F first, S second) {
		this.first = first;
		this.second = second;
	}

	/**
	 * @return the first
	 */
	public F getFirst() {
		return first;
	}

	/**
	 * @param first the first to set
	 */
	public void setFirst(F first) {
		this.first = first;
	}

	/**
	 * @return the second
	 */
	public S getSecond() {
		return second;
	}

	/**
	 * @param second the second to set
	 */
	public void setSecond(S second) {
		this.second = second;
	}
	
	
}
