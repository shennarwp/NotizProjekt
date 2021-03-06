package notizverwaltung.util;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import notizverwaltung.i18n.I18nMessagesUtil;
import notizverwaltung.model.interfaces.BearbeitungszustandFX;
import notizverwaltung.model.interfaces.KategorieFX;
import notizverwaltung.validators.StringValidator;

/**
 * Stellt Werkzeugmethoden für die GUI zur Verfuegung:
 *
 * -Anzeigen von Error-/Warning-Dialogen
 * -Ueberprüfung von Inhalten der ObservableLists
 *
 * @author Michelle Blau
 * @version 08.09.2018
 *
 */

public class FXUtil {


    /**
     * Zeigt einen Fehlerdialog an.
     * @param titel Der Titel des Dialogs.
     * @param header Die Ueberschrift, die ueber der Nachricht angezeigt wird.
     * @param message Die Nachricht, die in dem Dialog angezeigt wird.
     */
    public static void showErrorDialog(String titel, String header,
                                       String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titel);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Zeigt Warnung; die max. Anzahl an Kategorien/Notizen/Bearbeitungszuständen
     * ist in der DB erreicht
     * @param header Dialog-Ueberschrift
     */
    public static void showZuVieleElementeWarning(String header){
        Alert dialog = new Alert(Alert.AlertType.WARNING);

        dialog.setTitle(I18nMessagesUtil.getWarningTitel());
        dialog.setHeaderText(header);
        dialog.showAndWait();
    }


    /**
     * Validiert die eingegebenen Daten.
     *
     * @param errorMessage leerer String, falls Nutzereingabe gueltig
     * @return true wenn die Nutzer-Eingabe gueltig ist, sonst false.
     */
    public static boolean isInputValid(String errorMessage) {

        if (StringValidator.isStringLeerOderNull(errorMessage)) {
            return true;
        } else {
            showErrorDialog(I18nMessagesUtil.
                            getErrorUngueltigeFelderString(),
                    I18nMessagesUtil.getMessageKorrigiereUngueltigeFelderString(),
                    errorMessage);

            return false;
        }
    }


    /**
     * Prueft, ob ein uebergebener Name eines Bearbeitungszustands bereits in der GUI existiert
     * (ignoriert Groß-/Kleinschreibung)
     *
     * @param zustandName Name des Zustands
     * @param guiZustandListe ObservableList aus der GUI
     * @return true, wenn es einen Zustand mit diesem Namen schon in der GUI gibt, sonst false
     */
    public static boolean isZustandNameInListe(String zustandName, ObservableList<BearbeitungszustandFX> guiZustandListe){
        zustandName = zustandName.toLowerCase();
        String vergleichsName = null;

        for(BearbeitungszustandFX zustandFX : guiZustandListe){
            vergleichsName = zustandFX.getName().getValue();
            vergleichsName = vergleichsName.toLowerCase();

            if(vergleichsName.equals(zustandName)){
                return true;
            }
        }
        return false;
    }


    /**
     * Prüft, ob ein uebergebener Name eine Kategorie bereits in der GUI existiert
     * (ignoriert Groß-/Kleinschreibung)
     *
     * @param kategorieName Name der Kategorie
     * @param guiKategorieListe ObservableList aus der GUI
     * @return true, wenn es eine Kategorie mit diesem Namen schon in der GUI gibt, sonst false
     */
    public static boolean isKategorieNameInListe(String kategorieName, ObservableList<KategorieFX> guiKategorieListe) {
        kategorieName = kategorieName.toLowerCase();
        String vergleichsName = null;

        for(KategorieFX kategorieFX : guiKategorieListe){
            vergleichsName = kategorieFX.getKategorieName().getValue();
            vergleichsName = vergleichsName.toLowerCase();

            if(vergleichsName.equals(kategorieName)){
                return true;
            }
        }
        return false;
    }
}
