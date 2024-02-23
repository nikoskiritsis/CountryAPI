package org.example;

import org.example.controller.*;
import org.example.model.Country;
import java.util.List;

//------------- ΚΛΑΣΗ Η ΟΠΟΙΑ ΤΕΣΤΑΡΕΙ ΜΕΣΩ ΤΗΣ MAIN ΤΙΣ 4 ΛΕΙΤΟΥΡΓΙΕΣ ΤΟΥ ΠΡΩΤΟΥ ΜΕΡΟΥΣ ΤΗΣ ΕΡΓΑΣΙΑΣ ------------- //
public class CountryInfoRetriever {
	
	public static void main(String[] args) {
		
		CountriesInfoService test = new CountriesInfoService();
		
		/* ΤΕΣΤΑΡΩ ΑΝ ΛΕΙΤΟΥΡΓΕΙ Η ΚΛΑΣΗ ALLCOUNTRIES */
		List<Country> allcountries = test.getAllCountries();
		allcountries.forEach(System.out::println);
		
		
		/* ΤΕΣΤΑΡΩ ΑΝ ΛΕΙΤΟΥΡΓΕΙ Η ΚΛΑΣΗ CountryByName */
		List<Country> namecountries = test.getCountryByName("greece");
		namecountries.forEach(System.out::println);
		
		
		/* ΤΕΣΤΑΡΩ ΑΝ ΛΕΙΤΟΥΡΓΕΙ Η ΚΛΑΣΗ CountriesByLanguage */
		List<Country> langcountries = test.getCountriesByLanguage("greek");
		langcountries.forEach(System.out::println);
		
		/*TEΣΤΑΡΩ ΑΝ ΛΕΙΤΟΥΡΓΕΙ Η ΚΛΑΣΗ CountriesByCurrency */
		List<Country> currcountries = test.getCountriesByCurrency("cop");
		currcountries.forEach(System.out::println);
		
	}

}