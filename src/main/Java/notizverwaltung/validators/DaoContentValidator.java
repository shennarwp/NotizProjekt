package notizverwaltung.validators;

import notizverwaltung.builders.ServiceObjectBuilder;
import notizverwaltung.model.interfaces.Bearbeitungszustand;
import notizverwaltung.model.interfaces.Kategorie;
import notizverwaltung.model.interfaces.Notiz;
import notizverwaltung.service.classes.NotizServiceImpl;
import notizverwaltung.service.interfaces.BearbeitungszustandService;
import notizverwaltung.service.interfaces.KategorieService;
import notizverwaltung.service.interfaces.NotizService;

import java.util.List;

/**
 * Überprüft, ob noch Notizen in der Datenbank exisiteren, die einer bestimmten Kategorie oder
 * einem bestimmten Bearbeitungszustand zugeordnet sind
 *
 * @author Michelle Blau
 */
public class DaoContentValidator {


    /**
     * Prüft, ob es Notizen in der Datenbank gibt, die eine übergebene Kategorie nutzen
     *
     * @param kategorie zu der festgestellt werden soll, ob Notizen dieser zugeordnet sind
     * @return true, wenn es zugeordnete Notizen gibt, sonst false
     */
    public static boolean isNotizMitKategorieVorhanden(Kategorie kategorie) {
        KategorieService kategorieService = ServiceObjectBuilder.getKategorieService();
        int id = kategorie.getKategorieID();
        long anzahlNotizen = kategorieService.getAnzahlNotizenInKategorie(id);

        if (anzahlNotizen == 0){
            return false;
        }else{
            return true;
        }
    }


    /**
     * Prüft, ob es Notizen in der Datenbank gibt, die einen übergebenen Bearbeitungszustand nutzen
     *
     * @param bearbeitungszustand zu dem festgestellt werden soll, ob Notizen diesem zugeordnet sind
     * @return true, wenn es zugeordnete Notizen gibt, sonst false
     */
    public static boolean isNotizMitBearbeitungszustandVorhanden(Bearbeitungszustand bearbeitungszustand){
        BearbeitungszustandService bearbeitungszustandService = ServiceObjectBuilder.getBearbeitungszustandService();
        int id = bearbeitungszustand.getBearbeitungsZustandID();
        long anzahlNotizen = bearbeitungszustandService.getAnzahlNotizenInBearbeitungszustand(id);

        if (anzahlNotizen == 0){
            return false;
        }else{
            return true;
        }
    }

}
