package Tree;

import java.io.Serializable;


/**
 * Representa os dados necessários para o índice
 * remissivo de palavras. Funciona em conjunto com
 * a classe Node.
 * @author gabriel
 * @param <T>
 *
 */
public class Index implements Serializable, Comparable<Index>
{
	//uid
	private static final long serialVersionUID = 3L;
	private int linha;        // linha do arquivo que a palavra se encontra
	private String arquivo;   // arquivo em que a palavra se encontra
	private int ocorrencia;   // Ocorrências da palavra em uma linha
	
	/**
	 * Construtor 
	 */
	public Index ()
	{	}
  
	/**
	 * Construtor com parâmetros
	 * @param linha
	 * @param arquivo
	 * @param ocorrencia
	 */
	public Index(int linha, String arquivo, int ocorrencia) {
		this.linha = linha;
		this.arquivo = arquivo;
		this.ocorrencia = ocorrencia;
	}
	
	/**
	 * Construtor com parâmetros
	 * @param arquivo
	 * @param ocorrencia
	 */
	public Index(String arquivo, int ocorrencia) {
		this.linha = -1;
		this.arquivo = arquivo;
		this.ocorrencia = ocorrencia;
	}
  
	/**
	 * Getters and Setters 
	 */
	
	public int getLine() {
		return linha;
	}

	public void setLine(int linha) {
		this.linha = linha;
	}

	public String getFile() {
		return arquivo;
	}

	/**
	 * Retorna nome do arquivo, sem endereço absoluto
	 * @return Nome do arquivo
	 */
	public String getFilename() {
		int index = 0;	
		if( arquivo.contains("\\") ) {
			index = arquivo.lastIndexOf("\\")+1;
		} else if( arquivo.contains("/") ){
			index = arquivo.lastIndexOf("/")+1;
		}
		
		return arquivo.substring(index);
	}
	
	/**
	 * Atualiza arquivo
	 * @param arquivo
	 */
	public void setFile(String arquivo) {
		this.arquivo = arquivo;
	}

	/**
	 * Recupera número de ocorrências
	 * @return ocorrencia
	 */
	public int getOccurrence() {
		return ocorrencia;
	}

	/**
	 * Atualiza ocorrência
	 * @param ocorrencia
	 */
	public void setOccurrence(int ocorrencia) {
		this.ocorrencia = ocorrencia;
	}
	
	/**
	 * Incrementa o número de ocorrências
	 */
	public void incrementaOcorrencia() {
		this.ocorrencia++;
	}
	
	/**
	 * Recebe uma palavra e monta a frase que deve
	 * ser mostrada na listagem com base nos dados
	 * armazenados no índice.
	 * @param word  a palvra que está relacionada com este índice
	 * @return  a frase para ser mostrada na listagem
	 */
	public String formResponse ( String word )
	{
		StringBuffer aux = new StringBuffer();
		
		aux.append(this.getFilename() + ": ");
		aux.append(this.getOccurrence());
		aux.append(" ocorrencia(s) da palavra '");
		aux.append(word);
		aux.append("' na linha ");
		aux.append(this.getLine());
		
		return aux.toString();
	}

	/**
	 * Sobrescrita do metodo de comparação
	 */
	@Override
	public int compareTo(Index o)
	{
		if ( this.getOccurrence() > o.getOccurrence())
			return -1;
		else if (this.getOccurrence() < o.getOccurrence())
			return 1;
		else if (this.getOccurrence() == o.getOccurrence())
		{
			return this.getFile().compareTo(o.getFile());
		}
		
		return 0;
	}	
}
