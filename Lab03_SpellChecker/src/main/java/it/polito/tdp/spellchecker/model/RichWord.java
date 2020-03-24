package it.polito.tdp.spellchecker.model;

public class RichWord {
	
	String parola;
	boolean corretta;
	public RichWord(String parola) {
		super();
		this.parola = parola;
		this.corretta = false;
	}
	public String getParola() {
		return parola;
	}
	
	public boolean isCorretta() {
		return corretta;
	}
	public void setCorretta(boolean corretta) {
		this.corretta = corretta;
	}
	
	
	
	

}
