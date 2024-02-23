package org.example.model;

// ---------- ΚΛΑΣΗ ΠΟΥ ΠΕΡΙΕΧΕΙ ΛΙΣΤΑ ΑΠΟ ΑΝΤΙΚΕΙΜΕΝΑ ΤΗΣ ΚΛΑΣΗΣ COUNTRY -------- //
import java.util.List;

public class Countries {

    private List<Country> countryList;

    public List<Country> getCountryList() { // GETTER FUNCTION ΤΗΣ ΛΙΣΤΑΣ
        return countryList;
    }

    public void setCountryList(List<Country> countryList) { // SETTER FUNCTION ΤΗΣ ΛΙΣΤΑΣ
        this.countryList = countryList;
    }
}