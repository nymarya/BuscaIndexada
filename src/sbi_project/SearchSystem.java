package sbi_project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import view.simpleSearchView;

/**
 * Classe com as caracteristicas gerais do sistema
 *
 * @authors Gabriel A. Souza, Jaine B. Rannow, Mayra D. Azevedo
 */
public class SearchSystem {

	// metodo main
	public static void main(String[] args) {

		// instancia do banco de dados
		DataBase db   = new DataBase();
		// instancia da engine, passando o bd
		Engine engine = new Engine( db );
		
		try {
			FileInputStream fileIn = new FileInputStream(new File("./data/bd.ser").getCanonicalPath());
			ObjectInputStream in = new ObjectInputStream(fileIn);
			Engine e = (Engine) in.readObject();
			engine = e;
			db = engine.getDB();
			in.close();
			fileIn.close();
		} catch( FileNotFoundException f) {
			//Continua execução normalmente se o arquivo ainda n existe
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
			return;
		}


		// instancia da busca "and", passando o bd
		SearchAnd searchAnd = new SearchAnd( db );
		// instancia da busca "or", passando o bd
		SearchOr  searchOr  = new SearchOr( db );
		// instancia da view, passando a engine, searchAnd e searchOr 
		simpleSearchView viewSbi = new simpleSearchView(engine, searchAnd, searchOr );

		// exibe a tela inicial para usuario 
		viewSbi.setVisible(true);
	}

}
