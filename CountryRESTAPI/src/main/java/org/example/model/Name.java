package org.example.model;

// ---------- MIA ΚΛΑΣΗ ΓΙΑ ΝΑ ΑΠΟΘΗΚΕΥΩ ΤΟ ΟΝΟΜΑ ΤΗΣ ΧΩΡΑΣ ΚΑΙ ΣΥΓΚΕΚΡΙΜΕΝΑ ΤΑ ΠΕΔΙΑ common ΚΑΙ official -------- //
public class Name {

    private String common;

    private String official;

    public String getCommon() {  // GETTER FUNCTION ΓΙΑ ΤΟ ΠΕΔΙΟ COMMON ΠΟΥ ΕΙΝΑΙ ΙΔΙΩΤΙΚΟ
        return common;
    }

    public void setCommon(String common) { // SETTER FUNCTION ΓΙΑ ΤΟ ΠΕΔΙΟ COMMON ΠΟΥ ΕΙΝΑΙ ΙΔΙΩΤΙΚΟ
        this.common = common;
    }

    public String getOfficial() { // GETTER FUNCTION ΓΙΑ ΤΟ ΠΕΔΙΟ OFFICIAL ΠΟΥ ΕΙΝΑΙ ΙΔΙΩΤΙΚΟ
        return official;
    }

    public void setOfficial(String official) { // SETTER FUNCTION ΓΙΑ ΤΟ ΠΕΔΙΟ OFFICIAL ΠΟΥ ΕΙΝΑΙ ΙΔΙΩΤΙΚΟ
        this.official = official;
    }
    
    //----- ΣΥΝΑΡΤΗΣΗ ΠΟΥ ΕΚΤΥΠΩΝΕΙ ΤΑ ΟΝΟΜΑΤΑ ΤΗΣ ΧΩΡΑΣ ΟΤΑΝ ΚΑΛΕΙΤΑΙ ----//
    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("Common Name: ").append(common);
    	sb.append('\n');
    	sb.append("Official Name: ").append(official);
    	return sb.toString();
    }
}