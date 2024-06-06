package it.uniba.app.Control;

import java.util.ArrayList;
import java.util.List;
//import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import it.uniba.app.Boundary.Costanti;
import it.uniba.app.Boundary.Menu;
import it.uniba.app.Boundary.Tastiera;
import it.uniba.app.Entity.Coordinate;
import it.uniba.app.Entity.Giocatore;
import it.uniba.app.Entity.Pedina;
import it.uniba.app.Entity.Tavoliere;

/**
 * <<Control>>
 *
 * Classe che rappresenta una partita.
 * Gestisce il flusso del gioco e l'interazione con i giocatori.
 */
public class Partita {
    private List<String> storiaMosse;
    //costanti per le dimensioni del tavoliere
    public static final int RIGA = 1;
    public static final int COLONNA = 7;
    public static final char A = 'a';
    public static final char B = 'b';
    public static final int TIME = 6;
    //private static final String MESSAGGIO_GIOCO = "Inserisci coordinate o comandi del menu: ";
    private static final String MSG_ABBANDONA_PARTITA = "Sei sicuro di voler abbandonare la partita? (si/no) \n";
    private static final String CONFERMA_ABBANDONO = " ha abbandonato la partita.";
    private boolean uscitaRichiesta = false;
    private boolean abbandono = false;
    private Giocatore giocatore2;
    private Giocatore giocatore1;
    private Tavoliere tavoliere;
    private Tastiera tastiera;
    private int turno;

    /**
     * Costruttore della classe Partita.
     *
     * @param giocatoret1
     * @param giocatoret2
     * @param tavolieret
     */
    public Partita(final Giocatore giocatoret1, final Giocatore giocatoret2, final Tavoliere tavolieret) {
        this.giocatore1 = new Giocatore(giocatoret1); //  per creare una nuova istanza di Giocatore
        this.giocatore2 = new Giocatore(giocatoret2); //per creare una nuova istanza di Giocatore
        this.tavoliere = new Tavoliere(tavolieret); // ia una copia difensiva dei suoi elementi interni.
        this.storiaMosse = new ArrayList<>();
        this.tastiera = new Tastiera();
        this.turno = 1;
    }

    /**
     * Funzione che Avvia la partita.
     */

    public boolean avviaPartita() {
        if (uscitaRichiesta) {
            return true;
            // L'uscita è stata richiesta
        }
        if (abbandono) {
           return false;
        }
        tavoliere.inizializzaPedine(RIGA, RIGA, COLONNA, COLONNA);
        tavoliere.visualizzaTavolierePieno();
        String coordinateInput = "";
        while (!partitaFinita() && !uscitaRichiesta && !abbandono) {
            System.out.println("Turno " + (turno % 2 == 1 ? "Giocatore Nero" : "Giocatore Bianco") + ":");
            System.out.println("Inserisci le coordinate (es. a1-a2) o un comando: ");
            coordinateInput = tastiera.readString("Coordinate: ");
            if (coordinateInput.startsWith("/")) {
                gestisciComando(coordinateInput);
            } else if (validaCoordinate(coordinateInput)) {
                gestisciCoordinate(coordinateInput);
            } else {
                System.out.println("Input inserito non valido. Riprova!");
            }
        }

        return partitaFinita();
       // Ritorna true se la partita è finita
    }

/**
 * Metodo per validare la colonna in input.
 * @param colonna
 * @return
 */
private boolean validaColonna(final char colonna) {
    return colonna >= 'a' && colonna <= 'g';
}

/**
 * Metodo per validare la riga in input.
 * @param riga
 * @return
 */
 private boolean validaRiga(final int riga) {
    //eliminazione dei numeri magici con riferimento alle costanti
    return riga >= Costanti.RIGAI && riga <= Costanti.RIGAF;
}


/**
 * Modifica il metodo validaCoordinate per accettare 4 parametri separati.
 * Modifica il metodo validaCoordinate per accettare una stringa vettoriale come
 * coordinata.
 * @param coordinate
 * @return
 */
private boolean validaCoordinate(final String coordinate) {
    // Verifica che la stringa abbia il formato corretto
    if (!coordinate.matches("^[a-g][1-7]-[a-g][1-7]$")) {
        return false;
    }

    // Estrai le coordinate dalla stringa
    String[] coordinateArray = coordinate.split("-");
    char colonnaPedina = coordinateArray[0].charAt(0);
    int rigaPedina = Character.getNumericValue(coordinateArray[0].charAt(1));
    char colonnaCella = coordinateArray[1].charAt(0);
    int rigaCella = Character.getNumericValue(coordinateArray[1].charAt(1));

    // Verifica che le coordinate siano valide
    if (!validaRiga(rigaPedina) || !validaRiga(rigaCella) || !validaColonna(colonnaPedina)
            || !validaColonna(colonnaCella)) {
        return false;
    }

    // Converte le colonne in numeri interi
    int colPedina = colonnaPedina - 'a' + 1;
    int colCella = colonnaCella - 'a' + 1;

    // Crea gli oggetti Coordinate per le coordinate di partenza e di destinazione
    Coordinate from = new Coordinate(rigaPedina, colPedina);
    Coordinate to = new Coordinate(rigaCella, colCella);

    // Chiama il metodo mossaValida() per verificare la validità delle coordinate
    return mossaValida(from, to);
}


/**
 * Verifica se le coordinate inserite in input sono disponibili nel Tavoliere.
 * @param from
 * @param to
 * @return
 */
    public boolean mossaValida(final Coordinate from, final Coordinate to) {
        // Verifica se le coordinate di partenza e di destinazione sono all'interno del
        // tavoliere
        if (from.getRiga() < 1 || from.getRiga() > Costanti.RIGAF || to.getRiga() < 1 || to.getRiga()
         > Costanti.RIGAF || from.getColonna() < 1 || from.getColonna() > Costanti.RIGAF || to.getColonna() < 1
                || to.getColonna() > Costanti.RIGAF) {
            return false;
        }

        // Calcola la distanza in righe e colonne tra le coordinate di partenza e di
        // destinazione
        int distanzaRiga = Math.abs(from.getRiga() - to.getRiga());
        int distanzaColonna = Math.abs(from.getColonna() - to.getColonna());
        if (distanzaRiga > 2 || distanzaColonna > 2
        || (distanzaRiga == 0 && distanzaColonna == 0)) {
            return false;
        }

        // Verifica se la casella di destinazione è vuota
        if (!tavoliere.posizioneVuota(to.getRiga() - 1, to.getColonna() - 1)) {
            return false;
        }

        // Se tutte le condizioni sono soddisfatte, la mossa è valida
        return true;
    }

    // Cattura le pedine avversarie dopo una mossa
    private void catturaPedine(final Giocatore giocatore, final Coordinate to) {
        int riga = to.getRiga();
        int colonna = to.getColonna(); // Assumendo che colonna sia già un intero che rappresenta l'indice

        // Definizione dei delta per le righe e le colonne
        int[] deltaRighe = {-1, -1, -1, 0, 0, 1, 1, 1 };
        int[] deltaColonne = {-1, 0, 1, -1, 1, -1, 0, 1 };

        // Verifica le pedine adiacenti
        for (int i = 0; i < deltaRighe.length; i++) {
            int nuovaRiga = riga + deltaRighe[i];
            int nuovaColonna = colonna + deltaColonne[i];

            // Verifica se la nuova posizione è valida
            if (nuovaRiga >= 1 && nuovaRiga <= COLONNA && nuovaColonna >= 1 && nuovaColonna <= COLONNA) {
                char nuovaColonnaChar = (char) ('a' + nuovaColonna - 1); // Converti l'indice di colonna in carattere
                Pedina adiacente = tavoliere.getPedina(nuovaRiga, nuovaColonnaChar);

                // Se la casella adiacente contiene una pedina avversaria
                if (adiacente != null && adiacente.getCarattere() != giocatore.getPedina().getCarattere()) {
                    // Verifica se la pedina è bloccata
                    if (adiacente.getCarattere() != 'X') {
                        // Converte la pedina avversaria nel colore del giocatore corrente
                        adiacente.setCarattere(giocatore.getPedina().getCarattere());
                    }
                }
            }
        }
    }

     /**
     * Verifica se la mossa che il giocatore effettua è valida.
     * Se la mossa è una duplicazione
     * @param giocatore
     * @param from
     * @param to
     * @return
     */
    public boolean eseguiMossa(final Giocatore giocatore, final Coordinate from, final Coordinate to) {
        // Verifica se la mossa è valida
        if (!mossaValida(from, to)) {
            return false;
        }

        // Recupera la pedina dalla posizione di partenza
        Pedina pedina = tavoliere.getPedina(from.getRiga(), (char) ('a' + from.getColonna() - 1));
        if (pedina == null || pedina.getCarattere() != giocatore.getPedina().getCarattere()) {
            return false;
        }

        // Verifica le distanze di riga e colonna
        int distanzaRiga = Math.abs(from.getRiga() - to.getRiga());
        int distanzaColonna = Math.abs(from.getColonna() - to.getColonna());

        boolean mossaEseguita = false;

        // Se la mossa è una duplicazione
        if (distanzaRiga <= 1 && distanzaColonna <= 1) {
            mossaEseguita = tavoliere.setPedina(new Pedina(pedina.getCarattere(), to), to.getRiga(), to.getColonna());
        } else if (distanzaRiga <= 2 && distanzaColonna <= 2) {
            // Se la mossa è un salto
            mossaEseguita = tavoliere.setPedina(new Pedina(pedina.getCarattere(), to), to.getRiga(), to.getColonna());
            tavoliere.setPedina(null, from.getRiga(), from.getColonna());
        }
        if (mossaEseguita) {
            catturaPedine(giocatore, to);
        }
        return mossaEseguita;
    }



    private boolean partitaFinita() {
        // Implementa la logica per determinare se la partita è finita
        return false;
    }

    private char getPedinaGiocatoreCorrente(final int turnot) {
               return (turno % 2 == 1) ? 'N' : 'R';
    }

    /**
     * Calcola se un giocatore ha mosse disponibili.
     *
     * @param turnot
     */
    public boolean giocatoreHaMosseDisponibili(final int turnot) {
        char pedinaCorrente = getPedinaGiocatoreCorrente(turno);
        for (int riga = 1; riga <= Costanti.RIGAF; riga++) {
            for (char colonna = 'a'; colonna <= 'g'; colonna++) {
                Pedina pedina = tavoliere.getPedina(riga, colonna);
                if (pedina != null && pedina.getCarattere() == pedinaCorrente) {
                    ArrayList<Coordinate> mosseC = tavoliere.mosseC(riga, colonna);
                    if (!mosseC.isEmpty()) {
                        return true; // Se c'è almeno una mossa disponibile, il gioco può continuare
                    }
                }
            }
        }
        return false; // Nessuna mossa disponibile per il giocatore corrente
}
    /**
     * Funzione che gestisce i comandi del menu.
     *
     * @param comando
     */
    private void gestisciComando(final String comando) {
        // Implementa la logica per gestire i comandi del menu
        switch (comando.toLowerCase()) {
            case "/help":
            case "-h":
            case "--help":
                Menu.helpGioco(tastiera);
                break;
            case "/vuoto":
                tavoliere.visualizzaTavoliereVuoto();
                break;
            case "/tavoliere":
                tavoliere.visualizzaTavolierePieno();
                break;
            case "/qualimosse":
                 // Coordinate delle pedine del giocatore 1 (X)
                 Giocatore giocatoreCorrente = turno % 2 == 1 ? giocatore1 : giocatore2;
                 tavoliere.stampaMosseDisponibili(giocatoreCorrente);
                 //tavoliere.stampaMosseDisponibili(mossea, mosseb);
                break;
            case "/mosse":
                mostraMosseGiocate();
                break;
                case "/abbandona":
                boolean confermaAbbandono = false;
                while (!confermaAbbandono) {
                    String conferma = tastiera.readString(MSG_ABBANDONA_PARTITA);
                    if (conferma.equalsIgnoreCase("si")) {
                        // Determina quale giocatore ha abbandonato
                        if (tavoliere.getTurno() % 2 == 0) {
                            System.out.println("Il giocatore " + giocatore2.getNome() + CONFERMA_ABBANDONO);
                        } else {
                            System.out.println("Il giocatore " + giocatore1.getNome() + CONFERMA_ABBANDONO);
                        }
                        // Determina il giocatore opposto
                        Giocatore giocatoreOpposto = (tavoliere.getTurno() % 2 == 0) ? giocatore1 : giocatore2;
                        int numeroPedineGiocatoreOpposto = tavoliere
                                .contaPedine(giocatoreOpposto.getPedina().getCarattere(), tavoliere);
                        System.out.println("Il giocatore " + giocatoreOpposto.getNome() + " ha vinto per "
                                + numeroPedineGiocatoreOpposto + " a 0.");
                        abbandono = true;
                        confermaAbbandono = true;
                        try {
                        TimeUnit.SECONDS.sleep(TIME); // Attendi 6 secondi
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // Pulisci lo schermo dopo 6 secondi
                    Menu.clearScreen();
                    } else if (conferma.equalsIgnoreCase("no")) {
                        confermaAbbandono = true;
                    } else {
                        System.out.println("Inserisci 'si' per abbandonare la partita o 'no' per continuare.");
                    }
                }
                break;
            case "/esci":
                uscitaRichiesta = Menu.esci(tastiera);
                break;
            default:
                System.out.println("Comando non valido. Riprova.");
        }
    }

    private void gestisciCoordinate(final String coordinate) {
        if (!coordinate.matches("[a-g][1-7]-[a-g][1-7]")) {
            System.out.println(
                    "Formato delle coordinate non valido. Inserisci le coordinate nel formato corretto (es. a1-a2).");
            return;
        }

        String[] coordinateArray = coordinate.split("-");
        char colonnaPedina = coordinateArray[0].charAt(0);
        int rigaPedina = Character.getNumericValue(coordinateArray[0].charAt(1));
        char colonnaCella = coordinateArray[1].charAt(0);
        int rigaCella = Character.getNumericValue(coordinateArray[1].charAt(1));

        int col1 = Character.toLowerCase(colonnaPedina) - 'a' + 1;
        int col2 = Character.toLowerCase(colonnaCella) - 'a' + 1;

        Coordinate from = new Coordinate(rigaPedina, col1);
        Coordinate to = new Coordinate(rigaCella, col2);

        Giocatore giocatoreCorrente = (turno % 2 == 1) ? giocatore1 : giocatore2;

        boolean successo = eseguiMossa(giocatoreCorrente, from, to);
        if (successo) {
            Menu.clearScreen();
            System.out.println("Muovo la pedina da (" + rigaPedina + ", " + colonnaPedina + ") a (" + rigaCella + ", "
                    + colonnaCella + ")");
            tavoliere.visualizzaTavolierePieno();
            // Aggiungi la mossa alla lista delle mosse giocate
            String mossa = coordinate + " (" + (turno % 2 == 1 ? "N" : "R") + ")";
            storiaMosse.add(mossa);
            turno++;
        } else {
            System.out.println("Movimento non valido. Riprova.");
        }
    }
    /**
     * Funzione per resettare lo stato della partita.
     */
    public void reset() {
    uscitaRichiesta = false;
    abbandono = false;
    }

    /*
     * Funzione per mostrare le mosse giocate.
     */
    private void mostraMosseGiocate() {
        if (storiaMosse.isEmpty()) {
            System.out.println("Nessuna mossa è stata giocata finora.");
        } else {
            String header = String.format("| %-5s | %-40s |", "N.", "Mossa");
            String separator = new String(new char[header.length()]).replace("\0", "-");

            System.out.println(separator);
            System.out.println(header);
            System.out.println(separator);
            int counter = 1;
            for (String mossa : storiaMosse) {
                System.out.println(String.format("| %-5d | %-40s |", counter++, mossa));
            }
            System.out.println(separator);
        }
    }
}
