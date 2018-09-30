package notizverwaltung.model.classes;

import javafx.scene.paint.Color;
import notizverwaltung.exceptions.IntIstNegativException;
import notizverwaltung.exceptions.ObjectIstNullException;
import notizverwaltung.exceptions.StringIsEmptyException;
import notizverwaltung.model.interfaces.Kategorie;
import notizverwaltung.validators.IntValidator;
import notizverwaltung.validators.ObjectValidator;
import notizverwaltung.validators.StringValidator;

import javax.persistence.*;


/**
 * @author Shenna RWP
 * @author Michelle Blau
 */
@Entity
@Table(name = "Kategorie")
public class KategorieImpl implements Kategorie
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "KategorieID", nullable = false, unique = true)
    private int kategorieID;

    @Column(name = "KategorieName")
    private String kategorieName;

    //TODO: Derzeit stimmt mit Attribut farbe irgendwas in der Datenbank nicht, deswegen auskommentiert. Bei Konstruktoraufruf von NotizImpl MIT farbe gibt die DB eine Exception
//    @Column(name = "Farbe")
//    private Color farbe;

    public KategorieImpl(String kategorieName) throws StringIsEmptyException {
        StringValidator.checkObStringLeerOderNullIst(kategorieName);
        setKategorieName(kategorieName);
    }

//    public KategorieImpl(String kategorieName, Color farbe) throws StringIsEmptyException, ObjectIstNullException {
//        StringValidator.checkObStringLeerOderNullIst(kategorieName);
//        setKategorieName(kategorieName);
//
//        this.kategorieName = kategorieName;
//        this.farbe = farbe;
//        setFarbe(farbe);
//    }

    public KategorieImpl(){

    }


    @Override
    public int getKategorieID() {

        return this.kategorieID;
    }

    @Override
    public void setKategorieID(int kategorieID) throws IntIstNegativException {
        IntValidator.checkObIntNullOderNegativIst(kategorieID);
        this.kategorieID = kategorieID;
    }

    @Override
    public String getKategorieName() {
        return this.kategorieName;
    }

    @Override
    public void setKategorieName(String kategorieName) throws StringIsEmptyException {
        StringValidator.checkObStringLeerOderNullIst(kategorieName);
        this.kategorieName = kategorieName;
    }

    @Override
    public Color getFarbe() {
//        return this.farbe;
        return Color.DARKGREEN;
    }

    @Override
    public void setFarbe(Color farbe) throws ObjectIstNullException {
//        ObjectValidator.checkObObjectNullIst(farbe);
//        this.farbe = farbe;
    }


    /**
     * Kategorien sind gleich, wenn sie die gleiche ID haben
     * Vergleicht 2 Kategorien
     * @param object zu vergleichende Kategorie
     * @return true, wenn Kategorien gleich, sonst false
     *
     * @author Michelle Blau
     */
    @Override
    public boolean equals(Object object){
        if (object instanceof KategorieImpl){
            KategorieImpl vergleichsKategorie = (KategorieImpl) object;

            if (this.kategorieID == vergleichsKategorie.getKategorieID()){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return getKategorieName();
    }
}
