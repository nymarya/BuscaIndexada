package sbi_project;

import java.io.BufferedReader;
import java.io.FileReader;

// Classe para separar arquivos em palavras (tokens)
public class Parser {

	// Endereco do arquivo a ser tokenizado 
	private String file;
	private String token;
	private BufferedReader bReader;
	private FileReader fReader;
	
	
	/**
	 * Metodo construtor
	 * @param file Endereco do arquivo
	 */
	public Parser( String file ){
		this.file = file;
	}
	
}
