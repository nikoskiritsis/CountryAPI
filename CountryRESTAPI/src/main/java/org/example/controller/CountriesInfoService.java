package org.example.controller;

import java.util.ArrayList;
import java.util.List;
import org.example.model.Country;
import org.example.model.Countries;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

// ------------- ΚΛΑΣΗ Η ΟΠΟΙΑ ΥΛΟΠΟΙΕΙ ΤΙΣ 4 ΛΕΙΤΟΥΡΓΙΕΣ ΤΟΥ ΠΡΩΤΟΥ ΜΕΡΟΥΣ ΤΗΣ ΕΡΓΑΣΙΑΣ ------------- //
public class CountriesInfoService {
	
	// ΣΥΝΑΡΤΗΣΗ ΓΙΑ ΝΑ ΛΑΒΟΥΜΕ ΤΑ ΔΕΔΟΜΕΝΑ ΑΠΟ ΤΟ API
	private Countries getAPIData(String category, String parameter) {
		try {
		       // BUILD URL ΑΝΑΛΟΓΑ ΜΕ ΤΗΝ ΛΕΙΤΟΥΡΓΙΑ ΠΟΥ ΕΠΙΘΥΜΟΥΜΕ
			  URL domain = new URL("https", "restcountries.com", "/v3.1/"); // ΑΡΧΙΚΟ URL
			  URL url; // ΔΗΛΩΝΟΥΜΕ ΤΟ ΤΕΛΙΚΟ URL
			  if(parameter != null)  // ΑΝ ΕΧΟΥΜΕ 2 ΠΑΡΑΜΕΤΡΟΥΣ ΣΤΗΝ ΣΥΝΑΡΤΗΣΗ
			  {
				  url =  new URL(domain + category + "/" + parameter + "?fields=name,currencies,capital,population,region"); // ΤΟ URL ΘΑ ΧΤΙΣΤΕΙ ΕΤΣΙ, ΕΠΙΣΗΣ ΩΣ FIELDS ΛΑΜΒΑΝΟΥΜΕ ΜΟΝΟ Ο'ΤΙ ΑΠΑΙΤΕΙΤΑΙ
			  }
			  else // ΑΝ ΕΧΟΥΜΕ ΜΟΝΟ 1 ΠΑΡΑΜΕΤΡΟ
			  {
				  url = new URL(domain + category + "?fields=name,currencies,capital,population,region"); // ΤΟ URL ΘΑ ΧΤΙΣΤΕΙ ΧΩΡΙΣ ΤΗΝ 2η ΠΑΡΑΜΕΤΡΟ
				  
			  }
		      //------------ ΤΕΛΟΣ URL BUILDER ------------//
			  
			  // ------------ ΛΗΨΗ ΚΑΙ ΑΠΟΣΕΙΡΙΟΠΟΙΗΣΗ ΟΛΩΝ ΤΩΝ ΔΕΔΟΜΕΝΩΝ ΧΩΡΩΝ ------------
			  HttpURLConnection connection = (HttpURLConnection) url.openConnection(); // ΔΗΛΩΝΩ ΜΕΤΑΒΛΗΤΗ CONNECTION ΠΟΥ ΑΝΤΙΠΡΟΣΩΠΕΥΕΙ ΤΗΝ ΕΠΙΚΟΙΝΩΝΙΑ ΜΕ ΤΟ URL  
		      connection.setRequestMethod("GET"); // ΘΕΤΩ ΣΤΗΝ ΕΠΙΚΟΙΝΩΝΙΑ ΤΗΝ ΜΕΘΟΔΟ GET ΩΣΤΕ ΝΑ ΛΑΒΩ ΔΕΔΟΜΕΝΑ
		      int responseCode = connection.getResponseCode(); // ΔΗΛΩΝΩ ΜΕΤΑΒΛΗΤΗ RESPONSE CODE ΠΟΥ ΑΠΟΘΗΚΕΥΕΙ ΤΟΝ 3ΨΗΦΙΟ ΑΡΙΘΜΟ ΑΠΟΚΡΙΣΗΣ ΑΠΟ ΤΟ URL
		      if (responseCode == HttpURLConnection.HTTP_OK) { // ΑΝ ΕΙΝΑΙ ΟΚ Η ΣΥΝΔΕΣΗ, ΑΡΑ responseCode = 200
		                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream())); // ΧΡΗΣΙΜΟΠΟΙΩ ΤΗΝ BufferedReader KAI InputStreamReader ΓΙΑ ΝΑ ΔΙΑΒΑΣΩ ΚΑΤΑΛΛΗΛΑ ΤΗΝ ΡΟΗ ΔΕΔΟΜΕΝΩΝ ΧΑΡΑΚΤΗΡΑ ΜΕΣΩ ΤΟΥ CONNECTION
		                StringBuilder response = new StringBuilder(); // ΔΗΛΩΝΩ ΜΕΤΑΒΛΗΤΗ RESPONSE ΤΥΠΟΥ STRINGBUILDER ΠΟΥ ΘΑ ΑΠΟΘΗΚΕΥΤΕΙ Η ΑΠΑΝΤΗΣΗ
		                String line; // ΜΕΤΑΒΛΗΤΗ LINE ΠΟΥ ΘΑ ΠΕΡΝΑΩ ΚΑΘΕ ΓΡΑΜΜΗ ΤΗΣ ΑΠΟΚΡΙΣΗΣ ΠΟΥ ΕΧΕΙ ΛΑΒΕΙ Ο READER
		                response.append("{ \"countryList\":"); 
		                while ((line = reader.readLine()) != null) { // ΟΣΟ ΥΠΑΡΧΟΥΝ ΓΡΑΜΜΕΣ ΓΙΑ ΝΑ ΔΙΑΒΑΣΤΟΥΝ
		                    response.append(line); // ΤΟΠΟΘΕΤΟΥΝΤΑΙ ΣΤΟ RESPONSE
		                }
		                reader.close(); // ΚΛΕΙΝΩ ΤΟ READER
		                response.append("}");
		                
		                // ΤΟ RESPONSE ΓΙΝΕΤΑΙ DESERIALIATION ΑΠΟ JSON ΚΑΙ ΜΕΤΑΤΡΕΠΕΤΑΙ ΣΕ ΑΝΤΙΚΕΙΜΕΝΑ ΤΗΣ ΚΛΑΣΗΣ COUNTRIES 
		                return new Gson().fromJson(response.toString(), Countries.class);
		       } 
		       else {
		                System.err.println("Failed to retrieve countries data. HTTP Error: " + responseCode); // ΑΝ ΤΟ RESPONSE CODE ΔΕΝ ΕΙΝΑΙ ΕΓΚΥΡΟ, ΤΥΠΩΝΕΤΑΙ ΜΗΝΥΜΑ ΣΦΑΛΜΑΤΟΣ ΜΕ ΤΟ RESPONSECODE
		       }
		     } catch (IOException e) {
		           e.printStackTrace(); // ΑΝ ΣΥΜΒΕΙ ΣΦΑΛΜΑ ΚΑΠΟΥ ΣΤΟΝ ΚΩΔΙΚΑ Η ΕΝΤΟΛΗ ΘΑ ΜΑΣ ΠΕΙ ΠΟΥ ΑΚΡΙΒΩΣ ΕΓΙΝΕ
		     }
		   
		  return null;
	}
	
	// ------- 1. ΑΝΑΚΤΗΣΗ ΛΙΣΤΑΣ ΟΛΩΝ ΤΩΝ ΧΩΡΩΝ ------- //
	public List<Country> getAllCountries() {
		 Countries allCountries = getAPIData("all", null); // ΚΑΛΩ ΤΗΝ getAPIData ΜΕ ΤΗΝ ΚΑΤΗΓΟΡΙΑ "ALL" ΓΙΑ ΝΑ ΛΑΒΩ ΤΑ ΔΕΔΟΜΕΝΑ ΟΛΩΝ ΤΩΝ ΧΩΡΩΝ, ΚΑΙ ΤΑ ΑΠΟΘΗΚΕΥΩ ΣΕ Countries Objects
		 List<Country> countryList = new ArrayList<>(allCountries.getCountryList().size()); // ΔΗΜΙΟΥΡΓΩ ΤΗΝ ΛΙΣΤΑ ΧΩΡΩΝ ΠΟΥ ΘΑ ΕΠΙΣΤΡΑΦΕΙ, Η ΟΠΟΙΑ ΘΑ ΕΧΕΙ ΜΕΓΕΘΟΣ ΙΣΟ ΜΕ ΤΟ ΠΛΗΘΟΣ ΤΩΝ ΧΩΡΩΝ Π ΛΑΒΑΜΕ
		 for(Country country : allCountries.getCountryList()) // For Loop ΓΙΑ ΝΑ ΠΑΡΩ ΤΙΣ ΠΛΗΡΟΦΟΡΙΕΣ ΚΑΘΕ ΧΩΡΑΣ ΚΑΙ ΝΑ ΤΙΣ ΑΠΟΘΗΚΕΥΩ ΣΤΗΝ ΛΙΣΤΑ countryList
		 {
			 countryList.add(new Country( country.getName(), country.getCapital(), country.getCurrencies(), country.getPopulation(), country.getRegion() ));
		 }
		 
		 return countryList;
	}
	
	// ------- 2. ΑΝΑΚΤΗΣΗ ΠΛΗΡΟΦΟΡΙΩΝ ΓΙΑ ΜΙΑ ΣΥΓΚΕΚΡΙΜΕΝΗ ΧΩΡΑ ΜΕ ΤΗ ΧΡΗΣΗ ΤΟΥ ΟΝΟΜΑΤΟΣ ΤΗΣ ------- //
	public List<Country> getCountryByName(String name) {
		Countries nameCountries = getAPIData("name", name); // ΚΑΛΩ ΤΗΝ getAPIData ΜΕ ΤΗΝ ΚΑΤΗΓΟΡΙΑ "name" ΚΑΙ ΠΑΡΑΜΕΤΡΟ ΤΟ ΟΝΟΜΑ ΤΗΣ ΧΩΡΑΣ ΠΟΥ ΕΠΙΘΥΜΩ
		List<Country> countryList = new ArrayList<>(nameCountries.getCountryList().size());
		for(Country country : nameCountries.getCountryList())
		{
			countryList.add(new Country( country.getName(), country.getCapital(), country.getCurrencies(), country.getPopulation(), country.getRegion() ));
		}
		
		return countryList;
	}
	
	// ------- 3. ΑΝΑΚΤΗΣΗ ΛΙΣΤΑΣ ΧΩΡΩΝ ΠΟΥ ΜΙΛΟΥΝ ΜΙΑ ΣΥΓΚΕΚΡΙΜΕΝΗ ΓΛΩΣΣΑ ------- //
	public List<Country> getCountriesByLanguage(String language) {
		Countries langCountries = getAPIData("lang", language); // ΚΑΑΛΩ ΤΗΝ getAPIData ΜΕ ΤΗΝ ΚΑΤΗΓΟΡΙΑ "lang" ΚΑΙ ΠΑΡΑΜΕΤΡΟ ΤΗΝ ΓΛΩΣΣΑ ΤΩΝ ΧΩΡΩΝ ΠΟΥ ΕΠΙΘΥΜΩ
		List<Country> countryList = new ArrayList<>(langCountries.getCountryList().size());
		for(Country country : langCountries.getCountryList())
		{
			countryList.add(new Country( country.getName(), country.getCapital(), country.getCurrencies(), country.getPopulation(), country.getRegion() ));
		}
		
		return countryList;
	}
	
	// ------- 4. ΑΝΑΚΤΗΣΗ ΛΙΣΤΑΣ ΧΩΡΩΝ ΠΟΥ ΧΡΗΣΙΜΟΠΟΙΟΥΝ ΕΝΑ ΣΥΓΚΕΚΡΙΜΕΝΟ ΝΟΜΙΣΜΑ ------- //
	public List<Country> getCountriesByCurrency(String currency) {
		Countries currCountries = getAPIData("currency", currency); // ΚΑΛΩ ΤΗΝ getAPIData ΜΕ ΤΗΝ ΚΑΤΗΓΟΡΙΑ "currency" ΚΑΙ ΠΑΡΑΜΕΤΡΟ ΤΟ ΝΟΜΙΣΜΑ ΤΩΝ ΧΩΡΩΝ ΠΟΥ ΕΠΙΘΥΜΩ
		List<Country> countryList = new ArrayList<>(currCountries.getCountryList().size());
		for(Country country : currCountries.getCountryList())
		{
			countryList.add(new Country( country.getName(), country.getCapital(), country.getCurrencies(), country.getPopulation(), country.getRegion() ));
		}
		
		return countryList;
	}
	
	

}