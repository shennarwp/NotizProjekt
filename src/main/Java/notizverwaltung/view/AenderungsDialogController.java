package notizverwaltung.view;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import notizverwaltung.MainApp;
import notizverwaltung.builders.ModelObjectBuilder;
import notizverwaltung.builders.ServiceObjectBuilder;
import notizverwaltung.constants.DAOKonstanten;
import notizverwaltung.i18n.I18nMessagesUtil;
import notizverwaltung.model.interfaces.Bearbeitungszustand;
import notizverwaltung.model.interfaces.Kategorie;
import notizverwaltung.model.interfaces.Notiz;
import notizverwaltung.service.classes.BearbeitungszustandServiceImpl;
import notizverwaltung.service.classes.KategorieServiceImpl;
import notizverwaltung.service.classes.NotizServiceImpl;
import notizverwaltung.service.interfaces.BearbeitungszustandService;
import notizverwaltung.service.interfaces.KategorieService;
import notizverwaltung.service.interfaces.NotizService;
import notizverwaltung.util.DateUtil;
import notizverwaltung.util.FXUtil;
import notizverwaltung.validators.DaoContentValidator;
import notizverwaltung.validators.ObjectValidator;
import notizverwaltung.validators.StringValidator;

import java.time.LocalDate;
import java.util.Date;

/**
 * Stellt Funktionalität für die Dialog-Fenster zur Verfügung, welche Kategorien/Notizen/Bearbeitungszustände erzeugen/ändern/löschen
 *  TODO falls Zeit, den AenderungsDialogController in mehrere Controller-Klassen aufteilen... aber nur am Ende des Projekts
 *  TODO System.out.printlns rausnehmen, sobald fertig geschrieben und getestet
 *
 * @author Michelle Blau
 * @version 12.09.2018
 */

public class AenderungsDialogController {

    private MainApp mainApp;
    private Stage dialogStage;

    private NotizService notizService = ServiceObjectBuilder.getNotizService();
    private KategorieService kategorieService = ServiceObjectBuilder.getKategorieService();
    private BearbeitungszustandService bearbeitungszustandService = ServiceObjectBuilder.getBearbeitungszustandService();


    //_______________Notiz_____________//
    @FXML
    TextField notizNameField;

    @FXML
    TextArea notizBeschreibungTextArea;

    @FXML
    DatePicker notizFaelligkeitDatePicker;

    @FXML
    CheckBox notizPrioritaetCheckBox;

    //_______________Choice-Boxen__________________//
    @FXML
    ChoiceBox<Kategorie> kategorieChoiceBox;

    @FXML
    ChoiceBox<Bearbeitungszustand> bearbeitungszustandChoiceBox;

    @FXML
    ChoiceBox<Notiz> notizChoiceBox;

    //_______________Kategorie______________//
    @FXML
    TextField kategorieNameField;

    //_______________Bearbeitungszustand______________//
    @FXML
    TextField bearbeitungszustandNameField;


    /**
     * Setzt Referenz auf das Hauptprogramm, initialisiert anschließend ChoiceBoxen, falls diese vorhanden sind
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        if(!ObjectValidator.isObjectNull(bearbeitungszustandChoiceBox)){
            bearbeitungszustandChoiceBox.getItems().addAll(mainApp.getBearbeitungszustandListe());
        }
        if(!ObjectValidator.isObjectNull(kategorieChoiceBox)){
            kategorieChoiceBox.getItems().addAll(mainApp.getKategorieListe());
        }
        if(!ObjectValidator.isObjectNull(notizChoiceBox)){
            notizChoiceBox.getItems().addAll(mainApp.getNotizListe());

            notizChoiceBox.getSelectionModel()
                    .selectedIndexProperty()
                    .addListener(getNotizChoiceBoxListener());
        }
    }


    /**
     * Setzt Referenz auf die aufgerufene dialogStage, also das geöffnete Dialogfenster
     *
     * @param dialogStage
     */
    public void setDialogStage (Stage dialogStage){
        this.dialogStage = dialogStage;

    }



    /**
     * Schließt Stage/Dialogfenster beim Klicken auf den "Abbrechen" Button
     */
    @FXML
    private void handleBtnCancel(){
        this.dialogStage.close();
    }


    /**
     * Ändert bestehende Notiz, wenn auf "Anwenden" Button geklickt wird, oder ruft Error-Dialog auf, wenn
     * Nutzereingaben falsch. Die Änderung wird direkt in die Datenbank übernommen
     *
     */
    @FXML
    private void handleBtnAendereNotiz(){

        if (FXUtil.isInputValid(validateNotizAendern())) {

            Notiz zuAenderndeNotiz = notizChoiceBox.getValue();
            String neuerName = notizNameField.getText();
            Kategorie neueKategorie = kategorieChoiceBox.getValue();
            String neueBeschreibung = notizBeschreibungTextArea.getText();
            LocalDate neueFaelligkeit = notizFaelligkeitDatePicker.getValue();
            boolean neuePrioritaet = notizPrioritaetCheckBox.isSelected();

            zuAenderndeNotiz.setTitle(neuerName);
            zuAenderndeNotiz.setKategorieID(neueKategorie.getKategorieID());
            zuAenderndeNotiz.setBeschreibung(neueBeschreibung);
            zuAenderndeNotiz.setFaelligkeit(DateUtil.convertLocalDateInDate(neueFaelligkeit));
            zuAenderndeNotiz.setPrioritaet(neuePrioritaet);

            notizService.updateNotiz(zuAenderndeNotiz);

            System.out.println("Notiz wurde geändert in Liste und DB:" + mainApp.getNotizListe());
            dialogStage.close();
        }
    }



    /**
     * Ändert bestehende Kategorie, wenn auf "Anwenden" Button geklickt wird, oder ruft Error-Dialog auf, wenn
     * Nutzereingaben falsch. Die Änderung wird direkt in die Datenbank übernommen
     */
    @FXML
    private void handleBtnAendereKategorie(){
        if (FXUtil.isInputValid(validateKategorieAendern())) {

            Kategorie zuAenderndeKategorie = kategorieChoiceBox.getValue();
            String neuerName = kategorieNameField.getText();

            zuAenderndeKategorie.setKategorieName(neuerName);

            kategorieService.updateKategorie(zuAenderndeKategorie);

            System.out.println("Kategorie wurde geändert in Liste und DB: "+mainApp.getKategorieListe());
            dialogStage.close();
        }
    }



    /**
     * Ändert bestehenden Bearbeitungszustand, wenn auf "Anwenden" Button geklickt wird, oder ruft Error-Dialog auf, wenn
     * Nutzereingaben falsch
     */
    @FXML
    private void handleBtnAendereBearbeitungszustand(){

        if (FXUtil.isInputValid(validateBearbeitungszustandAendern())) {

            Bearbeitungszustand zuAendernderZustand = bearbeitungszustandChoiceBox.getValue();
            String neuerName = bearbeitungszustandNameField.getText();

            zuAendernderZustand.setName(neuerName);

            bearbeitungszustandService.updateBearbeitungszustand(zuAendernderZustand);

            System.out.println("Bearbeitungszustand geändert in Liste und DB: "+ mainApp.getBearbeitungszustandListe());
            dialogStage.close();
        }
    }



    /**
     * Validiert die Eingabefelder fuer die Änderung einer Notiz.
     * @return Fehlermeldungen, wenn Validierungsfehler aufgetreten sind, oder ein
     * leerer String.
     *
     */
    private String validateNotizAendern(){
        Notiz bestehendeNotiz = notizChoiceBox.getValue();
        String notizName = notizNameField.getText();
        Kategorie kategorie = kategorieChoiceBox.getValue();
        String beschreibung = notizBeschreibungTextArea.getText();
        LocalDate localDateFaelligkeit = notizFaelligkeitDatePicker.getValue();

        String errorMessage = "";

        if (ObjectValidator.isObjectNull(bestehendeNotiz)) {
            errorMessage += I18nMessagesUtil.getErrorBestehendeNotizUngueltig() + "\n";
        }
        if (StringValidator.isStringLeerOderNull(notizName)) {
            errorMessage += I18nMessagesUtil.getErrorNotiznameUngueltig() + "\n";
        }
        if (ObjectValidator.isObjectNull(kategorie)) {
            errorMessage += I18nMessagesUtil.getErrorBestehendeKategorieUngueltig() + "\n";
        }
        if (StringValidator.isStringLeerOderNull(beschreibung)) {
            errorMessage += I18nMessagesUtil.getErrorNotizbeschreibungUngueltig() + "\n";
        }
        if (ObjectValidator.isObjectNull(localDateFaelligkeit)) {
            errorMessage += I18nMessagesUtil.getErrorNotizFaelligkeitUngueltig() + "\n";
        }

        return errorMessage;
    }



    /**
     * Validiert die Eingabefelder zur Änderung einer bestehenden Kategorie.
     * @return Fehlermeldungen, wenn Validierungsfehler aufgetreten sind, oder ein
     * leerer String.
     */
    private String validateKategorieAendern() {
        String kategorieName = kategorieNameField.getText();
        Kategorie bestehendeKategorie = kategorieChoiceBox.getValue();

        String errorMessage = "";

        if(ObjectValidator.isObjectNull(bestehendeKategorie)){
            errorMessage += I18nMessagesUtil.getErrorBestehendeKategorieUngueltig() + "\n";
        }
        if (StringValidator.isStringLeerOderNull(kategorieName)) {
            errorMessage += I18nMessagesUtil.getErrorKategorienameUngueltig() + "\n";
        }

        return errorMessage;
    }



    /**
     * Validiert die Eingabefelder zur Änderung eines Bearbeitungszustands.
     * @return Fehlermeldungen, wenn Validierungsfehler aufgetreten sind, oder ein
     * leerer String.
     */
    private String validateBearbeitungszustandAendern() {

        String bearbeitungszustandName = bearbeitungszustandNameField.getText();
        Bearbeitungszustand bestehenderBearbeitungszustand = bearbeitungszustandChoiceBox.getValue();
        String errorMessage = "";

        if(ObjectValidator.isObjectNull(bestehenderBearbeitungszustand)){
            errorMessage += I18nMessagesUtil.getErrorBestehenderBearbeitungszustandUngueltig() + "\n";
        }
        if (StringValidator.isStringLeerOderNull(bearbeitungszustandName)) {
            errorMessage += I18nMessagesUtil.getErrorBearbeitungszustandnameUngueltig() + "\n";
        }

        return errorMessage;
    }


    /**
     * Erstellt einen ChangeListener, der die Attribute einer Notiz in die GUI übernimmt, sobald
     * eine Notiz in der ChoiceBox ausgewählt wird
     * @return lambda - Schreibt die Attribute der gewählten Notiz in die GUI
     */
    private ChangeListener<Number> getNotizChoiceBoxListener(){

        ChangeListener<Number> lambda = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                int aktuelleAuswahl = (Integer) newValue;

                Notiz gewaehlteNotiz = notizChoiceBox.getItems().get(aktuelleAuswahl);
                Date notizFaelligkeit = gewaehlteNotiz.getFaelligkeit();


                notizNameField.setText(gewaehlteNotiz.getTitle());
                notizBeschreibungTextArea.setText(gewaehlteNotiz.getBeschreibung());
                kategorieChoiceBox.setValue(kategorieService.getKategorie(gewaehlteNotiz.getKategorieID()));
                notizFaelligkeitDatePicker.setValue(DateUtil.convertDateInLocalDate(notizFaelligkeit));
                notizPrioritaetCheckBox.setSelected(gewaehlteNotiz.getPrioritaet());

            }
        };

        return lambda;
    }

}
