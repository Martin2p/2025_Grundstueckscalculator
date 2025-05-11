package src;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLController {
	//Deklarationen
	
		@FXML private Stage meineStage;
		@FXML private TextField lengthIn;
		@FXML private TextField widthIn;
		@FXML private TextField qmPriceIn;
		@FXML private Label grundflaeche;
		@FXML private Label nettopreis;
		@FXML private Label inklprovision;
		@FXML private Label endpreis;
	
	//die Methode setzt die Bühne auf den übergebenen Wert
	public void setMeineStage(Stage meineStage) {
		this.meineStage = meineStage;
	}
	
	//die allgemeine Berechnen-Methode
	@FXML protected void berechnen(ActionEvent event) {
		
		//Ausgabe der Berechnungen unter Verwendung der Methoden
		grundflaeche.setText(flaecheBerechnen() + " m²");
		nettopreis.setText(nettoPreis() + " €");
		
		//Provision und Endpreis werden auf 2 Nachkommastellen gekürzt angezeigt
		inklprovision.setText(String.format("%.2f",provisionsPreis()) + " €");
		endpreis.setText(String.format("%.2f", bruttoPreis());
	}

	//die Methode zur Berechnung der Fläche
	public double flaecheBerechnen() {
		
		//falls 0 oder ein anderer ungültiger Wert eingeben wird - Fehler abfangen
		try {
			double length = Double.parseDouble(lengthIn.getText());
			double width = Double.parseDouble(widthIn.getText());
		
		//auf Ganze Zahl runden
		return Math.round(length * width);
		}
		catch (Exception e) {
			return 0;
		}
	}
	
	//die Methode für den Netto-Grundstückspreis
	private double nettoPreis() {
		
		//falls 0 oder ein anderer ungültiger Wert eingeben wird  - Fehler abfangen
		double qmPrice = Double.parseDouble(qmPriceIn.getText());
		
		try {
			return flaecheBerechnen() * qmPrice;
		}
		catch (Exception e) {
			return 0;
		}
	}
	
	//die Methode zur Berechnung des Grundstückspreises inkl. Provision
	private double provisionsPreis() {
		
		//Provision 5%
		final double PROVISION = 1.05;
			
		return PROVISION * nettoPreis();
	}
	
	//die Methode
	private double bruttoPreis() {
		//Mehrwertsteuer 19%
		final double MWST = 1.19;
		
		return (MWST * provisionsPreis());
	}
	
	//die Methode zum Beenden
	@FXML protected void beendenKlick(ActionEvent event) {
		Platform.exit();
	}
	
	//die Methode neu -> Clear
	@FXML protected void reset(ActionEvent event) {
		Alert meinDialog = new Alert(AlertType.INFORMATION, "Die Daten werden gelöscht.");
		//den Text setzen
		meinDialog.setHeaderText("Bitte beachten");
		
		//den Dialog anzeigen
		meinDialog.showAndWait();
		
		//Feldinhalte mit leer überschreiben
		lengthIn.setText(null);
		widthIn.setText(null);
		qmPriceIn.setText(null);
		grundflaeche.setText(null);
		nettopreis.setText(null);
		inklprovision.setText(null);
		endpreis.setText(null);
	}
	
	//Info
	@FXML protected void infoKlick(ActionEvent event) {
		Alert info = new Alert(AlertType.INFORMATION, "Von Martin Tastler");
		info.setHeaderText("Grundstückscalculator Version 1.0");
		info.show();
	}
}
