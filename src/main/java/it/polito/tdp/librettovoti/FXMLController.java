package it.polito.tdp.librettovoti;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;

import it.polito.tdp.librettovoti.model.Libretto;
import it.polito.tdp.librettovoti.model.Voto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Libretto model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtEsame;

    @FXML
    private TextField txtVoto;

   // @FXML
    //private TextField txtData;
    @FXML
    private DatePicker pickerEsame;

    @FXML
    private TextArea txtResult;

    @FXML
    void handleInserisci(ActionEvent event) {
    	//Leggi e controlla i dati
    	String nomeEsame=txtEsame.getText();
    	if(nomeEsame.length()==0) {
    		txtResult.setText("ERRORE: nome esame vuoto");
    		return;
    	}
    	
    	String votoEsame=txtVoto.getText();
    	if(votoEsame.length()==0) {
    		txtResult.setText("ERRORE: campo voto vuoto");
    		return;
    	}
    	int votoInt=0;
    	try {
    		votoInt=Integer.parseInt(votoEsame);
    	}catch(NumberFormatException ex) {
    		txtResult.setText("ERRORE: il voto deve essere numerico");
    		return;
    	}
    	if(votoInt<18 || votoInt>30) {
    		txtResult.setText("ERRORE: il voto deve essere compreso tra 18 e 30");
    		return;
    	}
    	
    	/*
    	String dataEsame=txtData.getText();
    	if(dataEsame.length()==0) {
    		txtResult.setText("ERRORE: campo data vuoto");
    		return;
    	}
    	LocalDate data;
    	try {
    		data=LocalDate.parse(dataEsame);
    	}catch(DateTimeParseException ex) {
    		txtResult.setText("ERRORE: formato data errato");
    		return;
    	}*/
    	LocalDate data=pickerEsame.getValue();
    	if(data==null) {
    		txtResult.setText("ERRORE: la data è obbligatoria");
    		return;
    	}
    	
    	//Esegui l'azione
    	Voto voto=new Voto(nomeEsame, votoInt, data);
    	model.add(voto);
    	
    	//Aggiorna risultati (nella view)
    	txtResult.setText(model.toString());
    	txtEsame.clear();
    	txtVoto.clear();
    	//txtData.clear();
    	pickerEsame.setValue(null);
    }
    
    public void setModel(Libretto model) {
    	this.model=model;
    }

    @FXML
    void initialize() {
        assert txtEsame != null : "fx:id=\"txtEsame\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtVoto != null : "fx:id=\"txtVoto\" was not injected: check your FXML file 'Scene.fxml'.";
        assert pickerEsame != null : "fx:id=\"pickerEsame\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}