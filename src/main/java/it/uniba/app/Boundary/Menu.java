package it.uniba.app.Boundary;

import java.io.IOException;

/**
 * <<Boundary>>
 * Questa classe gestisce l'interfaccia del menu per l'utente nel gioco Ataxx.
 * Fornisce metodi per visualizzare l'aiuto, confermare l'uscita e pulire lo schermo.
 */
public final class Menu {

    private Menu() {
    }

    /**
     * funzione per pulire il terminale.
    */
    public static void clearScreen() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (IOException | InterruptedException e) {
            // Gestione dell'eccezione se non è possibile pulire il terminale
            System.out.println("Impossibile pulire il terminale.");
        }
    }

    /**
 * Visualizza il menu di aiuto principale. Mostra un messaggio di benvenuto e
 * una lista di comandi disponibili nel menu principale. Permette all'utente
 * di tornare al menu principale digitando "/indietro".
 *
 * @param tastiera L'istanza di Tastiera utilizzata per leggere l'input dell'utente.
 */
public static void helpPrincipale(final Tastiera tastiera) {
    System.out.println(Costanti.BENVENUTO_HELP);
    System.out.println(Costanti.MENU_COMANDI_PRINCIPALE);
    String aiuto = tastiera.readString("\nPer poter digitare un comando tornare al menu principale /indietro: ");
    if (aiuto.equalsIgnoreCase("/indietro")) {
        clearScreen();
        return;
    }
}

/**
 * Chiede all'utente di confermare se vuole uscire dal programma. Continua a chiedere
 * fino a quando l'utente non inserisce una risposta valida ("si" o "no").
 *
 * @param tastiera L'istanza di Tastiera utilizzata per leggere l'input dell'utente.
 * @return true se l'utente conferma di voler uscire, false se l'utente decide di non uscire.
 */
public static boolean esci(final Tastiera tastiera) {
    while (true) {
        String stringa = tastiera.readString(Costanti.CONFERMA_ESCI);
        if (stringa.equalsIgnoreCase("si")) {
            return true; // Esce dalla funzione, indicando che l'utente vuole uscire
        } else if (stringa.equalsIgnoreCase("no")) {
            return false; // Esce dalla funzione, indicando che l'utente non vuole uscire
        } else {
            System.out.println("Inserisci solo 'si' o 'no'.");
        }
    }
}

/**
 * Visualizza il menu di aiuto del gioco. Mostra un messaggio di benvenuto e
 * una lista di comandi disponibili durante il gioco.
 *
 * @param tastiera L'istanza di Tastiera utilizzata per leggere l'input dell'utente.
 */
public static void helpGioco(final Tastiera tastiera) {
    System.out.println(Costanti.BENVENUTO_HELP);
    System.out.print(Costanti.MENU_COMANDI_GIOCO);
}

}
