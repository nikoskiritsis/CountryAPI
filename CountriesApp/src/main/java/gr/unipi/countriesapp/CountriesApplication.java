package gr.unipi.countriesapp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import java.util.List;
import org.example.model.*;
import org.example.controller.*;

public class CountriesApplication extends Application {

	public static void main(String[] args) {
        	  launch(args);
        }
	
	@Override
	public void start(Stage primaryStage) {
		// ΘΕΤΩ ΤΙΤΛΟ ΠΑΡΑΘΥΡΟΥ
		primaryStage.setTitle("CountriesAPP");
		// ΔΗΜΙΟΥΡΓΩ ΕΝΑ ΠΛΑΙΣΙΟ ΤΥΠΟΥ Pane ΓΙΑ ΤΟ ΠΑΡΑΘΥΡΟ ΜΟΥ
		Pane root = new Pane();
		
		// ΔΗΜΙΟΥΡΓΩ ΜΙΑ ΛΙΣΤΑ ΠΟΥ ΑΠΟΘΗΚΕΥΕΙ ΤΙΣ ΤΕΛΕΥΤΑΙΕΣ 5 ΑΝΑΖΗΤΗΣΕΙΣ ΤΟΥ ΧΡΗΣΤΗ
		Label searchesLabel = new Label("Last 5 searches: ");                      // ΕΤΙΚΕΤΑ ΠΟΥ ΕΜΦΑΝΙΖΕΤΑΙ ΠΑΝΩ ΑΠΟ ΤΟ COMBOBOX
		searchesLabel.setLayoutX(500);                                            // ΤΟΠΟΘΕΤΩ ΤΗΝ ΕΤΙΚΕΤΑ ΣΤΟ ΠΑΡΑΘΥΡΟ
		searchesLabel.setLayoutY(380);
		root.getChildren().add(searchesLabel);                                  // ΤΗΝ ΒΑΖΩ ΝΑ ΕΜΦΑΝΙΣΤΕΙ ΣΤΟ ΠΑΡΑΘΥΡΟ ΕΙΣΑΓΟΝΤΑΣ ΤΗΝ ΣΤΟ root
		ObservableList<String> lastfive = FXCollections.observableArrayList(); // ΔΗΜΙΟΥΡΓΩ ΤΗΝ ΛΙΣΤΑ
		ComboBox<String> searchesComboBox = new ComboBox();                   // ΔΗΜΙΟΥΡΓΩ ΤΟ COMBOBOX
		searchesComboBox.setLayoutX(500);                                    // ΤΟΠΟΘΕΤΩ ΤΟ COMBOBOX ΣΤΟ ΠΑΡΑΘΥΡΟ
		searchesComboBox.setLayoutY(400);
		root.getChildren().add(searchesComboBox);                          // ΒΑΖΩ ΤΟ COMBOBOX ΝΑ ΕΜΦΑΝΙΣΤΕΙ ΣΤΟ ΠΑΡΑΘΥΡΟ
		
		// ΚΟΥΜΠΙ ΓΙΑ ΝΑ ΑΝΑΚΤΗΣΩ ΤΙΣ ΠΛΗΡΟΦΟΡΙΕΣ ΓΙΑ ΟΛΕΣ ΤΙΣ ΧΩΡΕΣ
		Button btn1 = new Button();                            // ΔΗΜΙΟΥΡΓΩ ΤΟ ΚΟΥΜΠΙ
		btn1.setText("Get Info Of All Countries");            // ΘΕΤΩ ΤΟ ΚΕΙΜΕΝΟ ΠΟΥ ΘΑ ΕΜΦΑΝΙΣΤΕΙ ΠΑΝΩ ΣΤΟ ΚΟΥΜΠΙ
		btn1.setLayoutX(850);                                // ΤΟΠΟΘΕΤΩ ΤΟ ΚΟΥΜΠΙ ΣΤΟ ΠΑΡΑΘΥΡΟ
		btn1.setLayoutY(100);
		root.getChildren().add(btn1);                       // ΒΑΖΩ ΤΟ ΚΟΥΜΠΙ ΝΑ ΕΜΦΑΝΙΣΤΕΙ ΣΤΟ ΠΑΡΑΘΥΡΟ
		btn1.setOnAction(new EventHandler<ActionEvent>() { // ΣΥΝΑΡΤΗΣΗ ΠΟΥ ΕΚΤΕΛΕΙΤΑΙ ΟΤΑΝ ΠΑΤΗΘΕΙ ΤΟ ΚΟΥΜΠΙ
			
			@Override
			public void handle(ActionEvent event) 
			{
				final CountriesInfoService countriesDBService = new CountriesInfoService(); // ΔΗΜΙΟΥΡΓΩ ΜΕΤΑΒΛΗΤΗ ΠΟΥ ΜΟΥ ΔΙΝΕΙ ΠΡΟΣΒΑΣΗ ΣΤΟ ΠΡΟΓΡΑΜΜΑ CountriesInfoService
				final List<Country> results = countriesDBService.getAllCountries();        // ΚΑΛΩ ΤΗ ΣΥΝΑΡΤΗΣΗ getAllCountries ΜΕΣΩ ΤΟΥ CountriesInfoService
				lastfive.add("1.All");                                                    // ΒΑΖΩ ΤΟ ΟΝΟΜΑ ΤΗΣ ΕΠΙΛΟΓΗΣ ΤΟΥ ΧΡΗΣΤΗ ΣΤΗ ΛΙΣΤΑ
				if(lastfive.size() > 5) {                                                // ΕΠΕΙΔΗ ΠΡΕΠΕΙ ΝΑ ΑΠΟΘΗΚΕΥΕΙ ΜΟΝΟ ΤΙΣ ΤΕΛΕΥΤΑΙΕΣ 5 ΑΝΑΖΗΤΗΣΕΙΣ ΤΟΥ ΧΡΗΣΤΗ, ΑΝ ΥΠΕΡΒΕΙ ΤΟ ΜΕΓΕΘΟΣ 5
					lastfive.remove(0);                                                 // ΑΦΑΙΡΕΙΣ ΤΗΝ ΠΡΩΤΗ ΑΝΑΖΗΤΗΣΗ
				}
				searchesComboBox.setItems(lastfive);                                  // ΒΑΖΩ ΤΑ ΠΕΡΙΕΧΟΜΕΝΑ ΤΟΥ lastfive ΣΤΟ COMBOBOX 
				results.forEach(System.out::println);                                // ΕΚΤΥΠΩΝΟΝΤΑΙ ΟΙ ΠΛΗΡΟΦΟΡΙΕΣ ΓΙΑ ΟΛΕΣ ΤΙΣ ΧΩΡΕΣ
			}
		});
		
		// ΚΟΥΜΠΙ ΓΙΑ ΝΑ ΑΝΑΚΤΗΣΩ ΤΙΣ ΠΛΗΡΟΦΟΡΙΕΣ ΓΙΑ ΧΩΡΕΣ ΒΑΣΗ ΟΝΟΜΑΤΟΣ -- ΜΕΤΑ ΕΝΕΡΓΟΠΟΙΩ ΚΑΙ ΑΛΛΟ ΚΟΥΜΠΙ
		Button btn2 = new Button();
		btn2.setLayoutX(600);
		btn2.setLayoutY(100);
		btn2.setText("Get Info Of Country By Name");
		root.getChildren().add(btn2);
		btn2.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) 
			{
				Label countryName = new Label("Country Name");
				TextField NameInput = new TextField();
				Button btn21 = new Button();
				countryName.setLayoutX(600);
				countryName.setLayoutY(150);
				NameInput.setLayoutX(600);
				NameInput.setLayoutY(170);
				btn21.setLayoutX(600);
				btn21.setLayoutY(210);
				btn21.setText("Search");
				root.getChildren().add(countryName);
				root.getChildren().add(NameInput);
				root.getChildren().add(btn21);
				btn21.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event)
					{
						final CountriesInfoService countriesDBService = new CountriesInfoService();
						final List<Country> results = countriesDBService.getCountryByName(NameInput.getText());
						lastfive.add("2."+NameInput.getText());
						if(lastfive.size() > 5) {
							lastfive.remove(0);
						}
						searchesComboBox.setItems(lastfive);
						results.forEach(System.out::println);	
						
					}
				});
			}
			
		});
		
		// ΚΟΥΜΠΙ ΓΙΑ ΝΑ ΑΝΑΚΤΗΣΩ ΤΙΣ ΠΛΗΡΟΦΟΡΙΕΣ ΓΙΑ ΧΩΡΕΣ ΒΑΣΗ ΓΛΩΣΣΑΣ
		Button btn3 = new Button();
		btn3.setText("Get Info of Countries By Language");
		btn3.setLayoutX(320);
		btn3.setLayoutY(100);
		root.getChildren().add(btn3);
		btn3.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) 
			{
				Label countryLang = new Label("Country Language");
				TextField LangInput = new TextField();
				Button btn31 = new Button();
				countryLang.setLayoutX(320);
				countryLang.setLayoutY(150);
				LangInput.setLayoutX(320);
				LangInput.setLayoutY(170);
				btn31.setLayoutX(320);
				btn31.setLayoutY(210);
				btn31.setText("Search");
				root.getChildren().add(countryLang);
				root.getChildren().add(LangInput);
				root.getChildren().add(btn31);
				btn31.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event)
					{
						final CountriesInfoService countriesDBService = new CountriesInfoService();
						final List<Country> results = countriesDBService.getCountriesByLanguage(LangInput.getText());
						lastfive.add("3." + LangInput.getText());
						if(lastfive.size() > 5) {
							lastfive.remove(0);
						}
						searchesComboBox.setItems(lastfive);
						results.forEach(System.out::println);
						
					}
				});
			}		
		});
		
		// ΚΟΥΜΠΙ ΓΙΑ ΝΑ ΑΝΑΚΤΗΣΩ ΤΙΣ ΠΛΗΡΟΦΟΡΙΕΣ ΒΑΣΗ ΝΟΜΙΣΜΑΤΟΣ
		Button btn4 = new Button();
		btn4.setText("Get Info of Countries By Currency");
		btn4.setLayoutX(50);
		btn4.setLayoutY(100);
		root.getChildren().add(btn4);
		btn4.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) 
			{
				Label countryCurr = new Label("Country Currency");
				TextField CurrInput = new TextField();		
				Button btn41 = new Button();
				countryCurr.setLayoutX(50);
				countryCurr.setLayoutY(150);
				CurrInput.setLayoutX(50);
				CurrInput.setLayoutY(170);
				btn41.setLayoutX(50);
				btn41.setLayoutY(210);
				btn41.setText("Search");
				root.getChildren().add(countryCurr);
				root.getChildren().add(CurrInput);
				root.getChildren().add(btn41);
				btn41.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event)
					{
						final CountriesInfoService countriesDBService = new CountriesInfoService();
						final List<Country> results = countriesDBService.getCountriesByCurrency(CurrInput.getText());
						lastfive.add("4." + CurrInput.getText());
						if(lastfive.size() > 5) {
							lastfive.remove(0);
						}
						searchesComboBox.setItems(lastfive);
						results.forEach(System.out::println);						
					}
				});
			}		
		});
		
		// ΟΤΑΝ ΚΑΠΟΙΑ ΕΠΙΛΟΓΗ ΚΛΙΚΑΡΕΤΑΙ ΑΠΟ ΤΟ COMBOBOX
		searchesComboBox.setOnAction(event -> {
			String selected = searchesComboBox.getValue(); // ΔΗΜΙΟΥΡΓΩ ΕΝΑ STRING ΠΟΥ ΛΑΜΒΑΝΕΙ ΩΣ ΕΙΣΟΔΟ ΤΗ ΛΕΞΗ ΠΟΥ ΚΛΙΚΑΡΑ ΑΠΟ ΤΟ COMBOBOX
			if(selected.contains("1.")) // ΑΝ Η ΕΙΣΟΔΟΣ ΠΕΡΙΕΧΕΙ ΤΟ "1." ΚΑΤΑΛΑΒΑΙΝΩ ΟΤΙ ΚΛΗΘΗΚΕ ΤΟ "ALL" ΑΡΑ ΣΤΗ ΣΥΝΕΧΕΙΑ ΘΑ ΞΑΝΑΚΑΛΕΣΩ ΤΗΝ ΕΠΙΛΟΓΗ ΓΙΑ All Countries
			{
				final CountriesInfoService countriesDBService = new CountriesInfoService();
				final List<Country> results = countriesDBService.getAllCountries();
				lastfive.add("1.All");
				if(lastfive.size() > 5) {
					lastfive.remove(0);
				}
				searchesComboBox.setItems(lastfive);
				results.forEach(System.out::println);	
			}
			else if(selected.contains("2.")) // ΑΝ Η ΕΙΣΟΔΟΣ ΠΕΡΙΕΧΕΙ ΤΟ "2." ΚΑΤΑΛΑΒΑΙΝΩ ΟΤΙ ΚΛΗΘΗΚΕ ΕΠΙΛΟΓΗ ΧΩΡΑΣ ΒΑΣΗ ΟΝΟΜΑΤΟΣ ΟΠΟΤΕ ΚΑΛΩ ΤΗΝ getCountryByName
			{
				final CountriesInfoService countriesDBService = new CountriesInfoService();
				selected = selected.replace("2.",""); // ΒΓΑΖΩ ΤΟ "2." ΑΠΟ ΤΟ STRIΝG ΩΣΤΕ ΝΑ ΜΠΕΙ ΜΟΝΟ ΤΟ ΟΝΟΜΑ ΣΤΗΝ ΣΥΝΑΡΤΗΣΗ getCountryByName
				final List<Country> results = countriesDBService.getCountryByName(selected);
				lastfive.add("2." + selected); // ΤΟ ΞΑΝΑΠΡΟΣΘΕΤΩ ΣΤΟ lastfive ΩΣΤΕ ΝΑ ΤΟ ΚΑΤΑΛΑΒΩ ΑΝ ΞΑΝΑΚΑΛΕΣΤΕΙ
				if(lastfive.size() > 5) {
					lastfive.remove(0);
				}
				searchesComboBox.setItems(lastfive);
				results.forEach(System.out::println);	
			}
			else if(selected.contains("3."))
			{
				final CountriesInfoService countriesDBService = new CountriesInfoService();
				selected = selected.replace("3.",""); // ΑΝ Η ΕΙΣΟΔΟΣ ΠΕΡΙΕΧΕΙ ΤΟ "3." ΚΑΤΑΛΑΒΑΙΝΩ ΟΤΙ ΚΛΗΘΗΚΕ ΕΠΙΛΟΓΗ ΧΩΡΩΝ ΒΑΣΕΙ ΓΛΩΣΣΑΣ ΟΠΟΤΕ ΚΑΛΩ ΤΗΝ getCountriesByLanguage
				final List<Country> results = countriesDBService.getCountriesByLanguage(selected);
				lastfive.add("3." + selected);
				if(lastfive.size() > 5) {
					lastfive.remove(0);
				}
				searchesComboBox.setItems(lastfive);
				results.forEach(System.out::println);
			}
			else
			{
				final CountriesInfoService countriesDBService = new CountriesInfoService();
				selected = selected.replace("4.",""); // ΑΝ Η ΕΙΣΟΔΟΣ ΠΕΡΙΕΧΕΙ ΤΟ "4." ΚΑΤΑΛΑΒΑΙΝΩ ΟΤΙ ΚΛΗΘΗΚΕ ΕΠΙΛΟΓΗ ΧΩΡΩΝ ΒΑΣΕΙ ΝΟΜΙΣΜΑΤΟΣ ΟΠΟΤΕ ΚΑΛΩ ΤΗΝ getCountriesByCurrency
				final List<Country> results = countriesDBService.getCountriesByCurrency(selected);
				lastfive.add("4." + selected);
				if(lastfive.size() > 5) {
					lastfive.remove(0);
				}
				searchesComboBox.setItems(lastfive);
				results.forEach(System.out::println);
			}
			
		});
		
        // ΔΗΜΙΟΥΡΓΩ ΤΟ SCENE ΤΟΥ ΠΑΡΑΘΥΡΟΥ ΜΕ ΔΙΑΣΤΑΣΕΙΣ 1080x720
        Scene scene = new Scene(root, 1080, 720);
        primaryStage.setScene(scene);

        // ΕΝΤΟΛΗ ΩΣΤΕ ΝΑ ΕΜΦΑΝΙΣΤΕΙ ΤΟ ΠΑΡΑΘΥΡΟ
        primaryStage.show();
		
	}
	
	}