package it.uniba.app.Boundary;

import java.io.IOException;

/**
 * <<Boundary>>
 * Questa classe gestisce l'interfaccia del menu per l'utente nel gioco Ataxx.
 * Fornisce metodi per visualizzare l'aiuto, confermare l'uscita e
 * pulire lo schermo.
 *  
 */
public final class Menu {

    private Menu() {
    }

    /**
     * Funzione per pulire il terminale.
     */
    public static void clearScreen() {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            // Controllo se si sta eseguendo all'interno di Visual Studio Code
            if (System.getenv("TERM_PROGRAM") != null && System.getenv("TERM_PROGRAM").equals("vscode")) {
                // Utilizza la sequenza di escape ANSI per pulire lo schermo in Visual Studio Code
                System.out.print("\u001b[2J\u001b[H");
                return;
            } else {
                // Utilizza il comando cls per pulire lo schermo in cmd
                try {
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                } catch (IOException | InterruptedException e) {
                    // Gestione dell'eccezione se non è possibile pulire il terminale
                    System.out.println("Impossibile pulire il terminale.");
                }
            }
        } else {
            // Utilizza il comando clear per pulire lo schermo in Git Bash e altri terminali Unix-like
            try {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            } catch (IOException | InterruptedException e) {
                // Gestione dell'eccezione se non è possibile pulire il terminale
                System.out.println("Impossibile pulire il terminale.");
            }
        }
    }

    /**
     * Visualizza il menu di aiuto principale. Mostra un messaggio di benvenuto e
     * una lista di comandi disponibili nel menu principale. Permette all'utente
     * di tornare al menu principale digitando "/indietro".
     *
     * @param tastiera L'istanza di Tastiera utilizzata per leggere l'input
     *                 dell'utente.
     */
    public static void helpPrincipale(final Tastiera tastiera) {
        clearScreen();
        System.out.println(Costanti.BENVENUTO_HELP);
        System.out.println(Costanti.MENU_COMANDI_PRINCIPALE);
    }

    /**
     * Chiede all'utente di confermare se vuole uscire dal programma. Continua a
     * chiedere
     * fino a quando l'utente non inserisce una risposta valida ("si" o "no").
     *
     * @param tastiera L'istanza di Tastiera utilizzata per leggere l'input
     *                 dell'utente.
     * @return true se l'utente conferma di voler uscire, false se l'utente decide
     *         di non uscire.
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
     * @param tastiera L'istanza di Tastiera utilizzata per leggere l'input
     *                 dell'utente.
     */
    public static void helpGioco(final Tastiera tastiera) {
        System.out.println(Costanti.BENVENUTO_HELP);
        System.out.print(Costanti.MENU_COMANDI_GIOCO);
    }



    /**
     * Metodo per stampare il benvenuto con i colori.
     */
    public static void stampaBenvenuto() {
        System.out.println(Costanti.BENVENUTO_ASCII);
        System.out.println(Costanti.INTRODUZIONE);
        System.out.println(Costanti.REGOLE);
    }

/**
     * Indenta il testo con un numero specificato di spazi.
     * @param text
     * @param spaces
     * @return
     */
    public static String indent(final String  text, final int spaces) {
        String indent = " ".repeat(spaces);
        String[] lines = text.split("\n");
        StringBuilder indentedText = new StringBuilder();
        for (String line : lines) {
            indentedText.append(indent).append(line).append("\n");
        }
        return indentedText.toString();
    }

}
