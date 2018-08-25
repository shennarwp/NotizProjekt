package main.java.notizverwaltung.i18n;

import java.io.File;
import java.util.ResourceBundle;

/**
 * Kapselt die Zugriffe auf die Ressourcendateien fuer die Internationalisierung. In diesem
 * kleinen Beispiel wird nur eine Ressourcendatei verwendet, doch es kann durchaus sinnvoll
 * sein, mehrere solcher Dateien zu definieren, z.B. fuer Beschriftungen, Fehlermeldungen usw. 
 * @author christopher
 *
 */
public class I18nUtil {
	/**
	 * Der Basisname fuer die Ressourcendatei. 
	 */
	private static final String I18N_BASENAME_MESSAGES ="main.resources.messages" ;
	private static final String I18N_BASENAME_MENU ="main.resources.menu" ;


	/**
	 * Die Ressourcendatei fuer alle Texte.
	 */
	private static ResourceBundle resourceBundleMessages;
	private static ResourceBundle resourceBundleMenu;




	/**
	 * Der Initialisierungsblock laedt die Ressourcendateien, so dass wir darauf zugreifen
	 * koennen.
	 */
	static {
		resourceBundleMessages = ResourceBundle.getBundle
				(I18N_BASENAME_MESSAGES);

		resourceBundleMenu = ResourceBundle.getBundle
				(I18N_BASENAME_MENU);
	}



	/**
	 * Ermoeglicht den Zugriff auf die Ressourcendatei fuer die Texte. 
	 * @return Das ResourceBundle mit den Texten.
	 */
	public static ResourceBundle getMessagesResourceBundle() {
		return resourceBundleMessages;
	}

	public static ResourceBundle getMenuResourceBundle() {
		return resourceBundleMenu;
	}
}