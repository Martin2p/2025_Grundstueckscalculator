package src;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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
		
		String flaeche = ("Grundstücksfläche: " + flaecheBerechnen() + " m²");
		String preis = ("Der finale Grundstückspreis beträgt: \n" + preisBerechnen() + " €");
		
		ausgabe.setText(flaeche + "\n" + preis);
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
	
	//die Methode zur Berechnung des Grundstückspreises
	private double preisBerechnen() {
		
		try {
			double qmeter = flaecheBerechnen();
			double qmPrice = Double.parseDouble(qmPriceIn.getText());
			
			 //Provision 5%
			final double PROVISION = 1.05;
			
			//Mehrwertsteuer 19%
			final double MWST = 1.19;
			
			//Grundstueckspreis berechnen
			double preisGrundst = qmPrice * qmeter;
							
			//Provisionsaufschlag berechnen
			double aufschlag = preisGrundst * PROVISION;
						
			//finaler Grundstueckspreis inkl. Provision und Mehrwertsteuer
			double finalPreis = aufschlag * MWST;
			
			return Math.round(finalPreis);
		}
		catch (Exception e) {
			return 0;
		}
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
