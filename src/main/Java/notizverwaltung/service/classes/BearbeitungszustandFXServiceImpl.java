package notizverwaltung.service.classes;

import notizverwaltung.builders.ModelObjectBuilder;
import notizverwaltung.model.interfaces.Bearbeitungszustand;
import notizverwaltung.model.interfaces.BearbeitungszustandFX;
import notizverwaltung.service.interfaces.BearbeitungszustandFXService;
import notizverwaltung.validators.ObjectValidator;

import java.util.ArrayList;
import java.util.List;

/**
 *Klasse fuer BearbeitungszustandFXServiceImpl. Implementiert das Interface BearbeitungszustandFXService.
 * @author Kevin Engelhardt
 * @version 1.0
 */

public class BearbeitungszustandFXServiceImpl implements BearbeitungszustandFXService {

    /**
     * Methode wandelt Bearbeitungszustand in BearbeitungszustandFX um
     * @param bearbeitungszustand
     * @return BearbeitungszustandFX
     */
    @Override
    public BearbeitungszustandFX wrapBearbeitungszustand(Bearbeitungszustand bearbeitungszustand) {
        ObjectValidator.checkObObjectNullIst(bearbeitungszustand);
        BearbeitungszustandFX bearbeitungszustandFX = ModelObjectBuilder.getBearbeitungszustandFXObjekt();

        bearbeitungszustandFX.setBearbeitungsZustandID(bearbeitungszustand.getBearbeitungsZustandID());
        bearbeitungszustandFX.setName(bearbeitungszustand.getName());
        //bearbeitungszustandFX.setPosition(bearbeitungszustand.getPosition());
        return bearbeitungszustandFX;

    }

    /**
     * Methode wandelt BearbeitungszustandFX in Bearbeitungszustand um
     * @param bearbeitungszustandFX
     * @return Bearbeitungszustand
     */

    @Override
    public Bearbeitungszustand unwrapBearbeitungszustandFX(BearbeitungszustandFX bearbeitungszustandFX) {
        ObjectValidator.checkObObjectNullIst(bearbeitungszustandFX);
        Bearbeitungszustand bearbeitungszustand = ModelObjectBuilder.getBearbeitungszustandObject();

        bearbeitungszustand.setBearbeitungszustandID(bearbeitungszustandFX.getBearbeitungsZustandID().getValue());
        bearbeitungszustand.setName(bearbeitungszustandFX.getName().getValue());
        bearbeitungszustand.setPosition(bearbeitungszustandFX.getPosition().getValue());

        return bearbeitungszustand;


    }

    /**
     * Methode wandelt BearbeitungszustandList in BearbeitungszustandFXList um
     * @param bearbeitungszustandListe
     * @return List von BearbeitungszustandFX
     */

    @Override
    public List<BearbeitungszustandFX> convertInBearbeitungszustandFXList(List<Bearbeitungszustand> bearbeitungszustandListe) {
        ObjectValidator.checkObObjectNullIst(bearbeitungszustandListe);
        List<BearbeitungszustandFX> bearbeitungszustandFXListe = new ArrayList<>();

        for(Bearbeitungszustand bearbeitungszustand : bearbeitungszustandListe){
            BearbeitungszustandFX bearbeitungszustandFX = wrapBearbeitungszustand(bearbeitungszustand);
            bearbeitungszustandFXListe.add(bearbeitungszustandFX);
        }
        return bearbeitungszustandFXListe;
    }
}
