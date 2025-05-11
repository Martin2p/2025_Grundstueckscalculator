package src;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class FXMLController {
	//Deklarationen
	
		@FXML private TextArea editor;
		
		private Stage meineStage;
		
	//die Methode zum Beenden
	@FXML protected void beendenKlick(ActionEvent event) {
		Platform.exit();
	}
	
	//die Methode neu -> Clear
	@FXML protected void neuKlick(ActionEvent event) {
		Alert meinDialog = new Alert(AlertType.INFORMATION, "Die Daten werden gelöscht.");
		//den Text setzen
		meinDialog.setHeaderText("Bitte beachten");
		//den Dialog anzeigen
		meinDialog.showAndWait();
		editor.clear();
	}
	
	//Info
	@FXML protected void infoKlick(ActionEvent event) {
		Alert info = new Alert(AlertType.INFORMATION, "Von Martin Tastler");
		info.setHeaderText("Grundstückscalculator Version 1.0");
		info.show();
	}
	
	//die Methode setzt die Bühne auf den übergebenen Wert
		public void setMeineStage(Stage meineStage) {
			this.meineStage = meineStage;
		}
		
}
