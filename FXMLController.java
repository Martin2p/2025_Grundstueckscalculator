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
		@FXML private Label ausgabe;
	
		
	//die Methode setzt die Bühne auf den übergebenen Wert
	public void setMeineStage(Stage meineStage) {
		this.meineStage = meineStage;
	}
	
	
	//die allgemeine Berechnen-Methode
	@FXML protected void berechnen(ActionEvent event) {
		
		//Ausgabe der Berechnungen unter Verwendung der Methoden
		String flaeche = ("Grundstücksfläche: " + flaecheBerechnen() + " m²");
		String netto = ("Nettopreis: " + nettoPreis() + " €");
		
		//Provision und Endpreis werden auf 2 Nachkommastellen gekürzt angezeigt
		String provision = ("Preis inklusive Provision: \n" + String.format("%.2f",provisionsPreis()) + " €");
		String endPreis = ("Preis inkl. Provision und Mehrwertsteuer: \n" + String.format("%.2f", bruttoPreis()) + " €");
	
		ausgabe.setText(flaeche + "\n" + netto + "\n" + provision + "\n" + endPreis);
	}
	
	
	//die Methode zur Berechnung der Fläche
	public double flaecheBerechnen() {
		
		try {
			double length = Double.parseDouble(lengthIn.getText());
			double width = Double.parseDouble(widthIn.getText());
		
		return Math.round(length * width);
		}
		catch (Exception e) {
			return 0;
		}
	}
	
	//die Methode für den Netto-Grundstückspreis
	private double nettoPreis() {
		
		//falls 0 oder ein anderer ungültiger Wert eingeben wird
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
		lengthIn.setText(null);
		widthIn.setText(null);
		qmPriceIn.setText(null);
		ausgabe.setText(null);
	}
	
	
	//Info
	@FXML protected void infoKlick(ActionEvent event) {
		Alert info = new Alert(AlertType.INFORMATION, "Von Martin Tastler");
		info.setHeaderText("Grundstückscalculator Version 1.0");
		info.show();
	}
}
