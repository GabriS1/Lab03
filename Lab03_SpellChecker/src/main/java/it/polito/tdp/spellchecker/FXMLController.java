/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	@FXML
	private Dictionary d; // è il mio modello, il Dictionary

	private ObservableList<String> lingueList = FXCollections.observableArrayList("Italian", "English");

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ChoiceBox<String> btnLanguage;

	@FXML
	private TextArea txtInsert;

	@FXML
	private Button btnSpell;

	@FXML
	private TextArea txtResult;

	@FXML
	private TextField txtErrors;

	@FXML
	private Button btnClear;

	@FXML
	private TextField txtTime;

	@FXML
	void doClearText(ActionEvent event) {
		this.txtInsert.clear();
		this.txtResult.clear();
		this.txtErrors.clear();
		this.txtTime.clear();
	}

	@FXML
	void doSpellCheck(ActionEvent event) {
		String lingua = "";
		lingua = this.btnLanguage.getValue();
		this.d.loadDictionary(lingua);

		LinkedList<String> lInsert = new LinkedList<String>();

		String s = this.txtInsert.getText();
		
		
		String[] a = s.split(" ");
		for (int i = 0; i < a.length; i++) {
				a[i] = a[i].replaceAll("[.,\\/#!$%\\?^&\\*;:{}=\\-_'()\\[\\]\"]", "");
				String parola= a[i].toLowerCase();
				lInsert.add(parola);
		}
		
		
		

		List<RichWord> lSbagliate = new LinkedList<RichWord>();

		
		lSbagliate = this.d.spellCheckTextDichotomic(lInsert);
		
		
		String s1 = "";

		for (RichWord r : lSbagliate) {
			s1 += r.getParola() + "\n";
		}

		this.txtResult.setText(s1);
		this.txtErrors.setText("Il testo contiene: "+lSbagliate.size()+" errori");
		long d= System.currentTimeMillis();
		this.txtTime.setText("Spell checked completed in: "+Long.toString(d));

	}

	@FXML
	void initialize() {
		assert btnLanguage != null : "fx:id=\"btnLanguage\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtInsert != null : "fx:id=\"txtInsert\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnSpell != null : "fx:id=\"btnSpell\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtErrors != null : "fx:id=\"txtErrors\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'Scene.fxml'.";
		btnLanguage.setItems(lingueList);
		btnLanguage.setValue("English");
	}

	public void setModel(Dictionary dictionary) {
		this.d = dictionary;
	}
}
