package it.uniba.app;

import java.io.IOException;

/**
 * <<Boundary>>
 * Questa classe gestisce l'interfaccia del menu per l'utente nel gioco Ataxx.
 * Fornisce metodi per visualizzare l'aiuto, confermare l'uscita e pulire lo schermo.
 */
final class Menu {

    private Menu() {
    }

    // funzione per pulire il terminale
    public static void clearScreen() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (IOException | InterruptedException e) {
            // Gestione dell'eccezione se non è possibile pulire il terminale
            System.out.println("Impossibile pulire il terminale.");
        }
    }

    public static void helpPrincipale(final Tastiera tastiera) {

        System.out.println(Costanti.BENVENUTO_HELP);
        System.out.println(Costanti.MENU_COMANDI_PRINCIPALE);
        String aiuto = tastiera.readString("\nPer poter digitare un comando tornare al menu principale /indietro: ");
        if (aiuto.equalsIgnoreCase("/indietro")) {
            clearScreen();
            return;

        }
    }

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

    public static void helpGioco(final Tastiera tastiera) {
        System.out.println(Costanti.BENVENUTO_HELP);
        System.out.print(Costanti.MENU_COMANDI_GIOCO);
    }
}
