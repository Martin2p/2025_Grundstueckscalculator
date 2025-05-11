package src;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFX_GrundstueckGUI extends Application {

	@Override
	public void start(Stage meineBuehne) throws Exception {
		
		//eine Instanz von FXMLLoader erzeugen
		FXMLLoader grundstueckrechner = new FXMLLoader(getClass().getResource("fxml_Grundstueckspreise.fxml"));
		
		//die Datei laden
		Parent root = grundstueckrechner.load();
		
		//Kontroller beschaffen
		FXMLController trController = grundstueckrechner.getController();
		
		//und die Bühne übergeben
		trController.setMeineStage(meineBuehne);
		
		//Scene erstellen
		Scene meineScene = new Scene(root, 600, 400);
		
		//den Titel über stage setzen
		meineBuehne.setTitle("Grundstückscalculator");
		//die Szene setzen
		meineBuehne.setScene(meineScene);
		//Fenstergröße fix setzen
		meineBuehne.setResizable(false);
		//anzeigen
		meineBuehne.show();
	}
	public static void main(String[] args) {
		//der Start
		launch(args);
	}
}
