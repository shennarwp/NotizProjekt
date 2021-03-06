package notizverwaltung.i18n;

import java.util.ResourceBundle;

/**
 * Kapselt die Zugriffe auf die Ressourcendateien fuer die Internationalisierung.
 *
 * @author christopher
 * @author Michelle Blau
 * @version 30.09.2018
 *
 */
public class I18nUtil {
	/**
	 * Die Basisnamen fuer die Ressourcendateien.
	 */
	private static final String I18N_BASENAME_MESSAGES ="i18n.messages" ;
	private static final String I18N_BASENAME_COMPONENTS ="i18n.components" ;
	private static final String I18N_BASENAME_DIALOG="i18n.dialog" ;


	/**
	 * Die Ressourcendateien.
	 */
	private static ResourceBundle resourceBundleMessages;
	private static ResourceBundle resourceBundleComponents;
	private static ResourceBundle resourceBundleDialog;




	/**
	 * Der Initialisierungsblock laedt die Ressourcendateien, so dass wir darauf zugreifen
	 * koennen.
	 */
	static {
		resourceBundleMessages = ResourceBundle.getBundle(I18N_BASENAME_MESSAGES);

		resourceBundleComponents = ResourceBundle.getBundle(I18N_BASENAME_COMPONENTS);

		resourceBundleDialog = ResourceBundle.getBundle(I18N_BASENAME_DIALOG);
	}



	/**
	 * Ermoeglicht den Zugriff auf die Ressourcendatei fuer die Texte. 
	 * @return Das ResourceBundle mit den Texten.
	 */
	public static ResourceBundle getMessagesResourceBundle() {
		return resourceBundleMessages;
	}

	/**
	 * Ermoeglicht den Zugriff auf die Ressourcendatei fuer die Komponenten.
	 * @return Das ResourceBundle mit den Komponenten.
	 */
	public static ResourceBundle getComponentsResourceBundle() {
		return resourceBundleComponents;
	}

	/**
	 * Ermoeglicht den Zugriff auf die Ressourcendatei fuer die Dialoge.
	 * @return Das ResourceBundle mit den Dialogen.
	 */
	public static ResourceBundle getDialogResourceBundle(){
		return resourceBundleDialog;
	}
}
