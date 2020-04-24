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

	public List<RichWord> spellCheckTextLinear(List<String> inputTextList) {

		List<RichWord> lSbagliate = new LinkedList<RichWord>();

		for (String s : inputTextList) {
			RichWord r = new RichWord(s);

			if (this.dizionario.contains(s)) {
				r.setCorretta(true);
			}
			
			if(r.isCorretta()) {
				lSbagliate.add(r);
			}

		}
		

		return lSbagliate;
	}
	
	public List<RichWord> spellCheckTextDichotomic(List<String> inputTextList) {
		List<RichWord> lSbagliate = new LinkedList<RichWord>();
		
		
		for (String s : inputTextList) {
			
			RichWord r = new RichWord(s);
			/*<0 se s<d/2-1
			 * =0 se s=d/2-1
			 * >0 se s>d/2-1
			 */
			if(s.compareTo(dizionario.get((dizionario.size()/2)-1))==0) {
				r.setCorretta(true);
			}
			if(s.compareTo(dizionario.get((dizionario.size()/2)-1))<0) {
				
				List<String> dizionario1 = new LinkedList<String>();
				/*for(int i=(dizionario.size()/2)-1; i>=0; i--) {
					dizionario1.add(dizionario.get(i));
				}*/
				dizionario1 = dizionario.subList(0, (dizionario.size()/2)-1);
				
				if (dizionario1.contains(s)) {
					r.setCorretta(true);
				}
				
				
			}
			if(s.compareTo(dizionario.get((dizionario.size()/2)-1))>0) {
				List<String> dizionario1 = new LinkedList<String>();
				dizionario1 = dizionario.subList((dizionario.size()/2)-1, dizionario.size());
				
				if (dizionario1.contains(s)) {
					r.setCorretta(true);
				}
				
			}

			if(!r.isCorretta()) {
				lSbagliate.add(r);
			}
			
			

		}
		
		return lSbagliate;
	}

}
