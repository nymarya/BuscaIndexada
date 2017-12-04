package view;

import java.awt.HeadlessException;

import javax.swing.JFrame;

import sbi_project.Engine;
import sbi_project.SearchAnd;
import sbi_project.SearchOr;


public abstract class viewSBI extends JFrame {

	// Instancia de engine
	private Engine engine;
	// Instancia de searchAnd
	private SearchAnd searchAnd;
	// Instancia de searchOr
	private SearchOr searchOr;
	
	
	public viewSBI(Engine engine, SearchAnd searchAnd, SearchOr searchOr) throws HeadlessException {
		this.engine = engine;
		this.searchAnd = searchAnd;
		this.searchOr = searchOr;
	}


	public Engine getEngine() {
		return engine;
	}


	public void setEngine(Engine engine) {
		this.engine = engine;
	}


	public SearchAnd getSearchAnd() {
		return searchAnd;
	}


	public void setSearchAnd(SearchAnd searchAnd) {
		this.searchAnd = searchAnd;
	}


	public SearchOr getSearchOr() {
		return searchOr;
	}


	public void setSearchOr(SearchOr searchOr) {
		this.searchOr = searchOr;
	}

	
	public abstract void indexView();
	
	public abstract void windowClosing();
	
}
