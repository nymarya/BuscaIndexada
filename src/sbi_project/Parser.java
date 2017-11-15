package sbi_project;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

// Classe para separar arquivos em palavras (tokens)
public class Parser {

	// Endereco do arquivo a ser tokenizado 
	private String file;
	private FileReader fReader;

	//Provide for the efficient reading of characters, arrays, and lines.
	private BufferedReader bReader;

	//Guarda os tokens da string
	private StringTokenizer token;


	/**
	 * Metodo construtor
	 * @param file Endereco do arquivo
	 * @throws IOException 
	 */
	public Parser( String file ) throws IOException{
		this.file = file;
		fReader = new FileReader(file);
		bReader = new BufferedReader(fReader);
		token = new StringTokenizer( bReader.readLine() );
	}

	/**
	 * Retorna uma palavra do arquivo.
	 *
	 * @return  A word typed by the user, or null
	 *          if there are no more tokens.
	 */
	public String next() throws IOException{

		//Se tokenizer não tem mais tokens, pega proxima lina
		while( !token.hasMoreTokens() ){
			String line = bReader.readLine();
			if (line == null) {
				return null;
			}
			token = new StringTokenizer(line);
		}

		return token.nextToken();
	}
	
	/**
     * Verifica se ainda existem palavras
     *
     * @return  Verdadeiro se ainda há palavras, falso caso contrário.
     */
    public boolean hasNext(){
        return (token.hasMoreTokens());
}
}
