package notizverwaltung.model.classes;


import notizverwaltung.exceptions.IntIstNegativException;
import notizverwaltung.exceptions.StringIsEmptyException;
import notizverwaltung.model.interfaces.Kategorie;

import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.*;

/**
 * Testklasse fuer die Klasse KategorieImpl
 *
 * @author Tobias Gottschalk
 * @author Michelle Blau
 *
 * @version 1.0
 */
class KategorieImplTest {


    /**
     * Testet ob die Methode auf einen negativen Integer
     * Methode: setKategorieID
     * Erwartet: IntIstNegativException
     */
    @Test
    void setKategorieID() {
        Kategorie kategorie = new KategorieImpl();
        assertThrows(IntIstNegativException.class, () -> {kategorie.setKategorieID(-1);});
    }

    /**
     * Testet ob die Methode auf einen leeren String
     * Methode: setKategorieName
     * Erwartet: StringIsEmptyException
     */
    @Test
    void setKategorieName() {
        String leererstring = " ";
        Kategorie kategorie = new KategorieImpl();
        assertThrows(StringIsEmptyException.class, () -> {kategorie.setKategorieName(leererstring);});
    }



    /**
     * Testet, was bei Übergabe einer Kategorie mit gleicher ID passiert
     * Methode: equals()
     * ->Erwartet wird der Rückgabewert true
     */
    @Test
    void equalsKategorienSindGleich(){
        Kategorie kat1 = new KategorieImpl();
        kat1.setKategorieID(244);
        Kategorie kat2 = new KategorieImpl();
        kat2.setKategorieID(244);

        boolean ergebnis = kat1.equals(kat2);

        assertEquals(true, ergebnis);
    }

    /**
     * Testet, was bei Übergabe einer Kategorie mit ungleicher ID passiert
     * Methode: equals()
     * ->Erwartet wird der Rückgabewert false
     */
    @Test
    void equalsKategorienSindUngleich(){
        Kategorie kat1 = new KategorieImpl();
        kat1.setKategorieID(249);
        Kategorie kat2 = new KategorieImpl();
        kat2.setKategorieID(244);

        boolean ergebnis = kat1.equals(kat2);

        assertEquals(false, ergebnis);
    }
}