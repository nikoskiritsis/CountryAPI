package org.example.model;

// --------- ΚΛΑΣΗ COUNTRY ΣΤΗΝ ΟΠΟΙΑ ΑΠΟΘΗΚΕΥΟΝΤΑΙ ΟΙ ΠΛΗΡΟΦΟΡΙΕΣ ΠΟΥ ΘΕΛΟΥΜΕ ΓΙΑ ΚΑΘΕ ΧΩΡΑ ------//

import java.util.List; // import ΓΙΑ ΝΑ ΔΗΜΙΟΥΡΓΗΣΩ ΛΙΣΤΑ

public class Country {

    private Name name;
    
    private Object currencies;

    private double population;
    
    private String region;
    
    private List<String> capital; // Η ΠΡΩΤΕΥΟΥΣΑ ΕΙΝΑΙ ΣΕ ΜΟΡΦΗ ΠΙΝΑΚΑ ΣΤΟ API ΓΙ'ΑΥΤΟ ΔΗΜΙΟΥΡΓΩ ΛΙΣΤΑ

    // CONSTRUCTOR ΚΛΑΣΗΣ
    public Country(Name name, List<String> capital, Object currencies, double population, String region) {
    	this.name = name;
    	this.capital = capital;
    	this.currencies = currencies;
    	this.population = population;
    	this.region = region;

    }

    public Name getName() {  // GETTER FUNCTION ΓΙΑ ΤΟ NAME
        return name;
    }
    
    public Object getCurrencies() { // GETTER FUNCTION ΓΙΑ ΟΛΑ ΤΑ ΣΤΟΙΧΕΙΑ ΤΟΥ ΝΟΜΙΣΜΑΤΟΣ
    	return currencies;
    }
    
    public String getShortname() { // GETTER FUNCTION ΓΙΑ ΤΗ ΣΥΝΤΟΜΟΓΡΑΦΙΑ ΤΟΥ ΟΝΟΜΑΤΟΣ ΕΝΟΣ ΝΟΜΙΣΜΑΤΟΣ
    	String shortname;
    	// ΕΤΣΙ ΔΙΑΙΡΩ ΤΟ {SHORTNAME={name=CurrName, symbol=$} ΣΕ SHORTNAME= name=CurrName (1 ΜΕΡΟΣ) ΚΑΙ symbol=$ (2 ΜΕΡΟΣ)
    	String[] ArrOfStr = currencies.toString().replace("{", "").replace("}","").split("," , 2);
    	// ΚΑΝΩ ΤΟΜΗ ΣΤΟ 1ο ΜΕΡΟΣ ΣΤΟ = ΑΡΑ ΠΡΟΚΥΠΤΕΙ SHORTNAME ΚΑΙ name=CurrName
    	String[] Short = ArrOfStr[0].split("=", 2);
    	// ΚΡΑΤΑΩ ΤΟ SHORTNAME ΚΑΙ ΤΟ ΑΠΟΘΗΚΕΥΩ ΣΤΗΝ ΜΕΤΑΒΛΗΤΗ shortname ΤΗΝ ΟΠΟΙΑ ΕΠΙΣΤΡΕΦΩ ΜΕ ΤΟ return shortname
    	shortname = Short[0];
    	
    	return shortname;
    }
    
    public String getCurrName() { // GETTER FUNCTION ΓΙΑ ΤΟ ΠΛΗΡΕΣ ΟΝΟΜΑ ΕΝΟΣ ΝΟΜΙΣΜΑΤΟΣ
    	String currname;
    	// ΕΤΣΙ ΔΙΑΙΡΩ ΤΟ {SHORTNAME={name=CurrName, symbol=$} ΣΕ SHORTNAME= name=CurrName (1 ΜΕΡΟΣ) ΚΑΙ symbol=$ (2 ΜΕΡΟΣ)
    	String[] ArrOfStr = currencies.toString().replace("{","").replace("}","").split("," , 2);
    	// ΚΑΝΩ ΤΟΜΗ ΣΤΟ 1ο ΜΕΡΟΣ ΣΤΟ = ΑΡΑ ΠΡΟΚΥΠΤΕΙ SHORTNAME ΚΑΙ name=CurrName
    	String[] Short = ArrOfStr[0].split("=", 2);
    	// ΚΡΑΤΑΩ ΤΟ name=CurrName ΚΑΙ ΤΟ ΚΑΝΩ SPLIT ΣΤΟ = ΓΙΑ ΝΑ ΚΡΑΤΗΣΩ ΤΟ CurrName
    	String[] Curr = Short[1].split("=", 2);
    	// ΚΡΑΤΑΩ ΤΟ CurrName ΚΑΙ ΤΟ ΑΠΟΘΗΚΕΥΩ ΣΤΗΝ ΜΕΤΑΒΛΗΤΗ currname ΤΗΝ ΟΠΟΙΑ ΚΑΙ ΕΠΙΣΤΡΕΦΩ
    	currname = Curr[1];
    	
    	return currname;
    }
    
    public String getSymbol() { // GETTER FUNCTION ΓΙΑ ΤΟ ΣΥΜΒΟΛΟ ΕΝΟΣ ΝΟΜΙΣΜΑΤΟΣ
    	String symbol;
    	// ΕΤΣΙ ΔΙΑΙΡΩ ΤΟ {SHORTNAME={name=CurrName, symbol=$} ΣΕ SHORTNAME= name=CurrName (1 ΜΕΡΟΣ) ΚΑΙ symbol=$ (2 ΜΕΡΟΣ)
    	String[] ArrOfStr = currencies.toString().replace("{","").replace("}","").split("," , 2);
    	// ΔΙΑΙΡΩ ΤΟ symbol=$ ΣΕ symbol και $
    	String[] ArrOfStr2 = ArrOfStr[1].split("=", 2);
    	// ΑΠΟΘΗΚΕΥΩ ΤΟ ΣΥΜΒΟΛΟ ΣΤΗΝ ΜΕΤΑΒΛΗΤΗ SYMBOL ΤΗΝ ΟΠΟΙΑ ΚΑΙ ΕΠΙΣΤΡΕΦΩ
    	symbol = ArrOfStr2[1];
    	
    	return symbol;
    }

    public double getPopulation() { // GETTER FUNCTION ΓΙΑ ΤΟΝ ΠΛΗΘΥΣΜΟ
        return population;
    }
    
    public String getRegion() { // GETTER FUNCTION ΓΙΑ ΤΗΝ ΗΠΕΙΡΟ
    	return region;
    }
    
    public List<String> getCapital() { // GETTER FUNCTION ΓΙΑ ΤΗΝ ΠΡΩΤΕΥΟΥΣΑ
    	return capital;
    }
    
    // ΣΥΝΑΡΤΗΣΗ ΠΟΥ ΕΚΤΥΠΩΝΕΙ ΟΛΕΣ ΤΙΣ ΑΠΑΡΑΙΤΗΤΕΣ ΠΛΗΡΟΦΟΡΙΕΣ ΜΙΑΣ ΧΩΡΑΣ
    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	sb.append(name);
    	sb.append('\n');
    	sb.append("Capital: ").append(capital);
    	sb.append('\n');
    	sb.append("Currency: ").append(currencies);
    	sb.append('\n');
    	sb.append("Population: ").append(population);
    	sb.append('\n');
    	sb.append("Region: ").append(region);
    	sb.append('\n');
    	sb.append("----------------");
    	return sb.toString();
    }
}