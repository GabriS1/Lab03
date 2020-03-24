package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Dictionary {

	List<String> dizionario;

	public Dictionary() {
		super();
		this.dizionario = new LinkedList<String>();
	}

	public void loadDictionary(String language) {

		if(language.compareTo("Italian")==0) {
			try {
				FileReader fr = new FileReader("src/main/resources/Italian.txt");
				BufferedReader br = new BufferedReader(fr);
				String word;
				while ((word = br.readLine()) != null) {
					// Aggiungere parola alla struttura dati
					this.dizionario.add(word);
				}
				br.close();
			} catch (IOException e) {
				System.out.println("Errore nella lettura del file");
			}

		}
		
		if(language.compareTo("English")==0) {
			try {
				FileReader fr = new FileReader("src/main/resources/English.txt");
				BufferedReader br = new BufferedReader(fr);
				String word;
				while ((word = br.readLine()) != null) {
					// Aggiungere parola alla struttura dati
					this.dizionario.add(word);
				}
				br.close();
			} catch (IOException e) {
				System.out.println("Errore nella lettura del file");
			}

		}

		
	}

	public List<RichWord> spellCheckText(List<String> inputTextList) {

		List<RichWord> l = new LinkedList<RichWord>();
		List<RichWord> lSbagliate = new LinkedList<RichWord>();

		for (String s : inputTextList) {
			RichWord r = new RichWord(s);

			if (this.dizionario.contains(s)) {
				r.setCorretta(true);
			}

			l.add(r);

		}
		
		for(RichWord r: l) {
			if(r.isCorretta()==false) {
				lSbagliate.add(r);
			}
		}

		return lSbagliate;
	}

}
