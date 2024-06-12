package it.uniba.app.Control;

import java.util.ArrayList;
import java.util.List;

import it.uniba.app.Boundary.Costanti;
import it.uniba.app.Boundary.Menu;
import it.uniba.app.Boundary.StampaTavoliere;
import it.uniba.app.Boundary.Tastiera;
import it.uniba.app.Entity.Blocca;
import it.uniba.app.Entity.Coordinate;
import it.uniba.app.Entity.Duplicazione;
import it.uniba.app.Entity.Giocatore;
import it.uniba.app.Entity.Mossa;
import it.uniba.app.Entity.Pedina;
import it.uniba.app.Entity.Salto;
import it.uniba.app.Entity.Tavoliere;
/**
 * <<Control>>: Questa classe rappresenta una partita del gioco.
 * Gestisce lo svolgimento della partita, le mosse dei giocatori,
 * il controllo della validità delle mosse e il calcolo del vincitore.
 */
public class Partita {

    private List<String> storiaMosse;

    // Costanti per le dimensioni del tavoliere
    public static final int RIGA = 1;
    public static final int COLONNA = 7;
    // variabile di istanza per contare il tempo di gioco
    private long tempoInizioPartita;

    // Variabili di stato della partita
    private boolean uscitaRichiesta = false;
    private boolean abbandono = false;

    // Giocatori e tavoliere della partita

    private Giocatore giocatore2;
    private Giocatore giocatore1;
    private Tavoliere tavoliere;
    private  Blocca blocca;
    private Mossa mossaPartita;
    private Duplicazione mossaa;
    private Salto mossab;
    private List<Coordinate> caselleBloccateb = new ArrayList<>();

    // Tastiera per l'input utente e variabile per il turno
    private Tastiera tastiera;
    private int turno;
    private boolean partitaInCorso;

    // Altri attributi e metodi della classe Partita...

    public final boolean isPartitaInCorso() {
        return partitaInCorso;
    }
  /**
     * Costruttore della classe Partita.
     *
     * @param giocatoret1 il giocatore 1
     * @param giocatoret2 il giocatore 2
     * @param tavolieret il tavoliere di gioco
     * @param bloccat l'oggetto per gestire il blocco delle caselle
     * @param mossata l'oggetto per gestire la duplicazione delle mosse
     * @param mossatb l'oggetto per gestire il salto delle mosse
     */
    public Partita(final Giocatore giocatoret1, final Giocatore giocatoret2, final Tavoliere tavolieret,
    final Blocca bloccat, final Duplicazione mossata, final Salto mossatb) {
this.giocatore1 = new Giocatore(giocatoret1);
this.giocatore2 = new Giocatore(giocatoret2);
this.tavoliere = new Tavoliere(tavolieret);
this.blocca = bloccat;
Pedina[][] scacchiera = tavoliere.getScacchiera();
this.tastiera = new Tastiera();
this.turno = 1;
this.tempoInizioPartita = System.currentTimeMillis();
this.storiaMosse = new ArrayList<>();
this.caselleBloccateb = new ArrayList<>();

// Inizializza la scacchiera
this.mossaPartita = new Mossa(scacchiera, null, null);
// Inizializza mossaa e mossab con l'oggetto mossa già inizializzato
this.mossaa = new Duplicazione(mossaPartita);
this.mossab = new Salto(mossaPartita);
// Reinizializza l'oggetto mossa con gli oggetti duplicazione e salto validi
this.mossaPartita = new Mossa(scacchiera, mossaa, mossab);
}



    /**
     * Blocca una casella nel tavoliere.
     *
     * @param coordinateb le coordinate della casella da bloccare
     */
    public final void bloccaCasella(final Coordinate coordinateb) {
        if (!caselleBloccateb.contains(coordinateb)) {
            caselleBloccateb.add(coordinateb);
        }
    }
    /**
     * Avvia la partita.
     *
     * @param caselleBloccate le caselle inizialmente bloccate
     * @return true se l'uscita è stata richiesta, altrimenti false
     */
    public final boolean avviaPartita(final List<Coordinate> caselleBloccate) {
        if (!caselleBloccate.isEmpty()) {
            this.caselleBloccateb.addAll(caselleBloccate); // Aggiungi le caselle bloccate iniziali all'elenco

            for (Coordinate coordinate : caselleBloccate) {
                tavoliere.inizializzaCaselleBloccate(coordinate);
                blocca.bloccaCasella(coordinate);
            }
        }
        boolean ritorno = false;
        Menu.clearScreen();
        tavoliere.inizializzaPedine(RIGA, RIGA, COLONNA, COLONNA);
        System.out.println(Costanti.GAME);
        StampaTavoliere.visualizzaTavolierePieno(tavoliere, blocca);
        String coordinateInput = "";
        while (!partitaFinita() && !uscitaRichiesta && !abbandono) {

            System.out.println("Turno " + (Math.abs(turno) % 2 == 1 ? "Giocatore Nero" : "Giocatore Bianco") + ":");
            // Controlla se il giocatore corrente ha mosse disponibili
            if (!giocatoreHaMosseDisponibili(turno)) {
                System.out.println("Non hai mosse disponibili. Il turno passa al giocatore avversario.");
                turno++;
                continue; // Passa il turno senza richiedere input
            }
            System.out.println("Inserisci le coordinate (es. a1-a2) o un comando: ");
            coordinateInput = tastiera.readString("\u001B[34m" + "Digita: " + "\u001B[0m");

            if (coordinateInput.startsWith("/")) {
                gestisciComando(coordinateInput);
            } else if (validaCoordinate(coordinateInput)) {
                gestisciCoordinate(coordinateInput);
            } else {
                Menu.clearScreen();
                System.out.println(Costanti.GAME);
                StampaTavoliere.visualizzaTavolierePieno(tavoliere, blocca);
                System.out.println("Input inserito non valido. Riprova!");
            }
        }

        if ((!partitaFinita() && abbandono)) {
            blocca.resettaCelleBloccate(caselleBloccateb);
            ritorno = false;
        } else if (!partitaFinita() && uscitaRichiesta) {
            return true;
        } else if (partitaFinita()) {
            Menu.clearScreen();
            calcolaVincitore();
            reset(caselleBloccateb);
        }

        return ritorno;
    }

/**
 * Verifica se una colonna è valida nel tavoliere.
 *
 * @param colonna la colonna da verificare
 * @return true se la colonna è valida, altrimenti false
 */
    private boolean validaColonna(final char colonna) {
        return colonna >= 'a' && colonna <= 'g';
    }

/**
 * Verifica se una riga è valida nel tavoliere.
 *
 * @param riga la riga da verificare
 * @return true se la riga è valida, altrimenti false
 */
    private boolean validaRiga(final int riga) {
        return riga >= 1 && riga <= Costanti.RIGAF;
    }

/**
 * Verifica se una stringa di coordinate è valida.
 *
 * @param coordinate le coordinate da verificare
 * @return true se le coordinate sono valide, altrimenti false
 */
    private boolean validaCoordinate(final String coordinate) {
        // Verifica che la stringa abbia il formato corretto
        if (!coordinate.matches("^[a-g][1-7]-[a-g][1-7]$")) {
            return false;
        }

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
 * Gestisce le coordinate inserite dall'utente.
 *
 * @param coordinate le coordinate da gestire
 */
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

        Giocatore giocatoreCorrente = (Math.abs(turno) % 2 == 1) ? giocatore1 : giocatore2;

        boolean successo = eseguiMossa(giocatoreCorrente, from, to);
        if (successo) {
            Menu.clearScreen();
            System.out.println(Costanti.GAME);
            StampaTavoliere.visualizzaTavolierePieno(tavoliere, blocca);

            // Aggiungi la mossa alla lista delle mosse giocate
            String mossa = turno + ". " + coordinate + " (" + (Math.abs(turno) % 2 == 1
            ? Costanti.PEDINA_NERA : Costanti.PEDINA_BIANCA) + ")";
            storiaMosse.add(mossa);
            turno++;
        } else {
            System.out.println("Movimento non valido. Riprova.");
        }
    }
/**
 * Esegue una mossa nel tavoliere.
 *
 * @param giocatore il giocatore che esegue la mossa
 * @param from le coordinate di partenza della mossa
 * @param to le coordinate di destinazione della mossa
 * @return true se la mossa è stata eseguita con successo, altrimenti false
 */
public final boolean eseguiMossa(final Giocatore giocatore, final Coordinate from, final Coordinate to) {
    // Verifica se la mossa è valida
    if (!mossaValida(from, to)) {
        System.out.println("Mossa non valida da " + from + " a " + to);
        return false;
    }

    // Recupera la pedina dalla posizione di partenza
    Pedina pedina = tavoliere.getPedina(from.getRiga(), (char) ('a' + from.getColonna() - 1));
    if (pedina == null || !pedina.getCarattere().equals(giocatore.getPedina().getCarattere())) {
        System.out.println("Pedina non trovata o non corrispondente al giocatore");
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
        mossaEseguita = tavoliere.setPedina(new Pedina(pedina.getCarattere(), to), to.getRiga(), to.getColonna());
        // Rimuovi la pedina dalla posizione di partenza
        tavoliere.setPedina(null, from.getRiga(), from.getColonna());
    }

    // Se la mossa è stata eseguita, cattura le pedine avversarie adiacenti
    if (mossaEseguita) {
        catturaPedine(giocatore, to);
    }

    return mossaEseguita;
}

/**
 * Cattura le pedine avversarie dopo una mossa.
 *
 * @param giocatore il giocatore che ha eseguito la mossa
 * @param to le coordinate della casella in cui è stata spostata la pedina
 */
private void catturaPedine(final Giocatore giocatore, final Coordinate to) {
    int riga = to.getRiga();
    int colonna = to.getColonna(); // Assumendo che colonna sia già un intero che rappresenta l'indice

    // Definizione dei delta per le righe e le colonne
    int[] deltaRighe = {-1, -1, -1, 0, 0, 1, 1, 1};
    int[] deltaColonne = {-1, 0, 1, -1, 1, -1, 0, 1};

    // Verifica le pedine adiacenti
    for (int i = 0; i < deltaRighe.length; i++) {
        int nuovaRiga = riga + deltaRighe[i];
        int nuovaColonna = colonna + deltaColonne[i];

        // Verifica se la nuova posizione è valida
        if (nuovaRiga >= 1 && nuovaRiga <= Tavoliere.RIGAFINALE
                && nuovaColonna >= 1 && nuovaColonna <= Tavoliere.COLONNAFINALE) {
            char nuovaColonnaChar = (char) ('a' + nuovaColonna - 1); // Converti l'indice di colonna in carattere
            Pedina adiacente = tavoliere.getPedina(nuovaRiga, nuovaColonnaChar);

            // Se la casella adiacente contiene una pedina avversaria
            if (adiacente != null && !adiacente.getCarattere().equalsIgnoreCase(giocatore.getPedina().getCarattere())) {
                // Verifica se la pedina non è bloccata e la casella adiacente non è bloccata
                Coordinate nuovaCoord = new Coordinate(nuovaRiga, nuovaColonna);

                // Verifica se la pedina avversaria è adiacente a una casella bloccata
                boolean adiacenteABloccata = false;
                for (int j = 0; j < deltaRighe.length; j++) {
                    int adiacenteRiga = nuovaRiga + deltaRighe[j];
                    int adiacenteColonna = nuovaColonna + deltaColonne[j];
                    Coordinate adiacenteCoord = new Coordinate(adiacenteRiga, adiacenteColonna);
                    if (blocca.isBloccata(adiacenteCoord)) {
                        adiacenteABloccata = true;
                        break;
                    }
                }
                // Se la pedina avversaria non è bloccata, la casella adiacente non è bloccata,
                // e la pedina avversaria non è adiacente a una casella bloccata
                if (!adiacenteABloccata && !blocca.isBloccata(nuovaCoord) && !adiacente.getCarattere()
                .equals(Costanti.PEDINA_X)) {
                    // Converte la pedina avversaria nel colore del giocatore corrente
                    adiacente.setCarattere(giocatore.getPedina().getCarattere());
                    tavoliere.setPedina(adiacente, nuovaRiga, nuovaColonna); // Aggiorna la pedina nel tavoliere
                }
            }
       }
    }
}
/**
 * Verifica se una mossa è valida.
 *
 * @param from le coordinate di partenza della mossa
 * @param to le coordinate di destinazione della mossa
 * @return true se la mossa è valida, altrimenti false
 */
public final boolean mossaValida(final Coordinate from, final Coordinate to) {
    // Verifica se le coordinate di partenza e di destinazione sono all'interno del tavoliere
    if (from.getRiga() < 1 || from.getRiga() > Costanti.RIGAF
            || to.getRiga() < 1 || to.getRiga() > Costanti.RIGAF
            || from.getColonna() < 1 || from.getColonna() > Costanti.COLONNAF
            || to.getColonna() < 1 || to.getColonna() > Costanti.COLONNAF) {
        return false;
    }

    // Calcola la distanza in righe e colonne tra le coordinate di partenza e di destinazione
    int distanzaRiga = Math.abs(from.getRiga() - to.getRiga());
    int distanzaColonna = Math.abs(from.getColonna() - to.getColonna());


    // Verifica se la mossa viola le regole del gioco:
    // - La mossa non può superare una distanza di 2 caselle in orizzontale o verticale
    // - La mossa non può essere una casella di partenza e destinazione uguali
    if (distanzaRiga > 2 || distanzaColonna > 2
            || (distanzaRiga == 0 && distanzaColonna == 0)) {
        return false;
    }

    // Verifica se la casella di destinazione è vuota
    if (!tavoliere.posizioneVuota(to.getRiga() - 1, to.getColonna() - 1)) {
        return false;
    }

    // Verifica se la casella di destinazione è bloccata
    if (blocca.isBloccata(to)) {
        return false;
    }

    // Se tutte le condizioni sono soddisfatte, la mossa è valida
   return true;
}
    /**
     * Verifica se la partita è finita.
     *
     * @return true se la partita è finita, altrimenti false
     */
    public final boolean partitaFinita() {
        for (int riga = 1; riga <= Costanti.RIGAF; riga++) {
            for (char colonna = 'a'; colonna <= 'g'; colonna++) {
                if (tavoliere.getPedina(riga, colonna) == null) {
                    return false; // Se c'è almeno una casella vuota, il tavoliere non è pieno
                }
            }
        }
        return true;
    }
/**
 * Ottiene il carattere della pedina del giocatore corrente in base al turno.
 *
 * @param turnot il turno corrente
 * @return il carattere della pedina del giocatore corrente ('N' per Nero, 'R' per Rosso)
 */
    private String getPedinaGiocatoreCorrente(final int turnot) {
        return (Math.abs(turno) % 2 == 1) ? Costanti.PEDINA_NERA : Costanti.PEDINA_BIANCA;
    }
    /**
     * Verifica se il giocatore corrente ha mosse disponibili.
     *
     * @param turnot il turno di gioco
     * @return true se il giocatore ha mosse disponibili, altrimenti false
     */
public final boolean giocatoreHaMosseDisponibili(final int turnot) {
    // Verifica che l'oggetto mossa sia inizializzato
    if (mossaPartita == null) {
        throw new IllegalStateException("L'oggetto Mossa non è stato inizializzato.");
    }

    String pedinaCorrente = getPedinaGiocatoreCorrente(turno);
    for (int riga = 1; riga <= Costanti.RIGAF; riga++) {
        for (char colonna = 'a'; colonna <= 'g'; colonna++) {
            Pedina pedina = tavoliere.getPedina(riga, colonna);
            if (pedina != null && pedina.getCarattere().equals(pedinaCorrente)) {
                ArrayList<Coordinate> mosseC = mossaPartita.mosseC(riga, colonna);
                if (!mosseC.isEmpty()) {
                    return true; // Se c'è almeno una mossa disponibile, il gioco può continuare
                }
            }
        }
    }
    return false; // Nessuna mossa disponibile per il giocatore corrente
}


/**
 * Calcola il vincitore della partita in base al numero di pedine catturate.
 *
 * @return il giocatore vincitore, o null in caso di pareggio
 */
    private Giocatore calcolaVincitore() {
        int pedineGiocatore1 = tavoliere.contaPedine(giocatore1.getPedina().getCarattere(), tavoliere);
        int pedineGiocatore2 = tavoliere.contaPedine(giocatore2.getPedina().getCarattere(), tavoliere);

        if (pedineGiocatore1 > pedineGiocatore2) {
            System.out.println();
            System.out.println(Costanti.ALIENO);
            System.out.println("La partita è finita. sei stato rapito da: " + giocatore1.getNome());
            System.out.println("Punteggio: " + giocatore1.getNome() + " " + pedineGiocatore1 + " - "
                    + giocatore2.getNome() + " " + pedineGiocatore2);
            return giocatore1;
        } else if (pedineGiocatore2 > pedineGiocatore1) {
            System.out.println();
            System.out.println(Costanti.ALIENO);
            System.out.println("La partita è finita. sei stato rapito da: " + giocatore2.getNome());
            System.out.println("Punteggio: " + giocatore2.getNome() + " " + pedineGiocatore2 + " - "
                    + giocatore1.getNome() + " " + pedineGiocatore1);
            return giocatore2;
        } else {
            System.out.println("La partita è finita con un pareggio.");
            System.out.println("Punteggio: " + giocatore1.getNome() + " " + pedineGiocatore1 + " - "
                    + giocatore2.getNome() + " " + pedineGiocatore2);
            return null;
        }
    }

/**
 * Gestisce i comandi inseriti durante il gioco.
 *
 * @param comando il comando da gestire
 */
    private void gestisciComando(final String comando) {
        switch (comando.toLowerCase()) {
            case "/help":
            case "-h":
            case "--help":
                Menu.clearScreen();
                System.out.println(Costanti.GAME);
                StampaTavoliere.visualizzaTavolierePieno(tavoliere, blocca);
                Menu.helpGioco(tastiera);
                break;
            case "/vuoto":
                Menu.clearScreen();
                System.out.println(Costanti.GAME);
                StampaTavoliere.visualizzaTavoliereVuoto();
                break;
            case "/tavoliere":
                Menu.clearScreen();
                System.out.println(Costanti.GAME);
                StampaTavoliere.visualizzaTavolierePieno(tavoliere, blocca);
                break;
            case "/qualimosse":
                Menu.clearScreen();
                Giocatore giocatoreCorrente = Math.abs(turno) % 2 == 1 ? giocatore1 : giocatore2;
                // Stampa le mosse disponibili per tutte le pedine del giocatore corrente
                System.out.println(Costanti.GAME);
                StampaTavoliere.stampaMosseDisponibili(giocatoreCorrente, tavoliere, blocca, mossaPartita);
                break;
            case "/mosse":
                Menu.clearScreen();
                System.out.println(Costanti.GAME);
                StampaTavoliere.visualizzaTavolierePieno(tavoliere, blocca);
                mostraMosseGiocate();
                break;
            case "/abbandona":
                boolean confermaAbbandono = false;
                //! significa fin tanto che è diverso da true.
                while (!confermaAbbandono) {
                    String conferma = tastiera.readString(Costanti.MSG_ABBANDONA_PARTITA);
                    if (conferma.equalsIgnoreCase("si")) {
                        Menu.clearScreen();
                        System.out.println(Costanti.ALIENI);
                        System.out.println();
                        System.out.println(Costanti.RED
                        + "Vi stiamo osservando. La vostra forza sarà messa alla prova. Non arrendetevi mai"
                        + Costanti.ANSI_RESET);
                        if (turno % 2 == 0) {
                            System.out.println("Il giocatore " + giocatore2.getNome() + Costanti.CONFERMA_ABBANDONO);
                        } else {
                            System.out.println("Il giocatore " + giocatore1.getNome() + Costanti.CONFERMA_ABBANDONO);
                        }
                        Giocatore giocatoreOpposto = (turno % 2 == 0) ? giocatore1 : giocatore2;
                        int numeroPedineGiocatoreOpposto = tavoliere
                        .contaPedine(giocatoreOpposto.getPedina().getCarattere(), tavoliere);
                        System.out.println("Il giocatore " + giocatoreOpposto.getNome() + " ha vinto per "
                                + numeroPedineGiocatoreOpposto + " a 0.");
                        abbandono = true; //mi serve nel metodo avviapartita
                        confermaAbbandono = true;
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
            case "/tempo":
                Menu.clearScreen();
                System.out.println(Costanti.GAME);
                StampaTavoliere.visualizzaTavolierePieno(tavoliere, blocca);
                stampaTempoDiGioco();
                break;

            default:
                Menu.clearScreen();
                System.out.println(Costanti.GAME);
                StampaTavoliere.visualizzaTavolierePieno(tavoliere, blocca);
                System.out.println("Comando non valido. Riprova.");
        }
    }
/*
 * Mostra le mosse giocate finora durante la partita.
 */

 private void mostraMosseGiocate() {
    if (storiaMosse.isEmpty()) {
        System.out.println(Menu.indent("Nessuna mossa è stata giocata finora.", Costanti.TIME6));
    } else {
        String header = String.format("║ %-5s | %-40s ║", "N.", "Mossa");
        String separator = new String(new char[header.length()]).replace("\0", "═");

        StringBuilder giocoMosse = new StringBuilder();
        giocoMosse.append(Costanti.RED).append("╔").append(separator.substring(1, separator.length() - 1)).append("╗\n")
                  .append("║").append(Costanti.ANSI_RESET).append(String.format(" %-5s | %-40s ", "N.", "Mossa"))
                  .append(Costanti.RED).append("║\n")
                  .append("╠").append(separator.substring(1, separator.length() - 1)).append("╣\n");

        int counter = 1;
        for (String mossa : storiaMosse) {
            giocoMosse.append("║").append(Costanti.ANSI_RESET)
                      .append(String.format(" %-5d | %-40s ", counter++, mossa)).append(Costanti.RED).append("║\n");
        }
        giocoMosse.append("╚").append(separator.substring(1, separator.length() - 1))
        .append("╝").append(Costanti.ANSI_RESET);

        System.out.println(giocoMosse.toString());
        System.out.println(Costanti.SEPARATORE_2 + Costanti.ANSI_RESET);
    }
}

/**
 * Reimposta lo stato della partita.
 *
 * @param caselleBloccate la lista delle caselle bloccate da reimpostare
 */
 // Reimposta lo stato della partita
 public void reset(final List<Coordinate> caselleBloccate) {
    uscitaRichiesta = false;
    abbandono = false;
    turno = 1;
    caselleBloccate.clear();
}

/**
 * Stampa il tempo di gioco trascorso finora.
 */
private void stampaTempoDiGioco() {
    long tempoTrascorso = System.currentTimeMillis() - tempoInizioPartita;
    long ore = tempoTrascorso / Costanti.ORE;
    long minuti = (tempoTrascorso % Costanti.ORE) / Costanti.MINUTI;
    long secondi = ((tempoTrascorso % Costanti.ORE) % Costanti.MINUTI) / Costanti.SECONDI;

    String tempoFormattato = String.format("%02d:%02d:%02d", ore, minuti, secondi);

    String giocoTempo = Costanti.RED + "╔════════════════════════╗\n"
            + "║" + Costanti.ANSI_RESET + "    Tempo di gioco      " + Costanti.RED + "║\n"
            + "║" + Costanti.ANSI_RESET + "        " + tempoFormattato + "        " + Costanti.RED + "║\n"
            + "╚════════════════════════╝" + Costanti.ANSI_RESET;

    System.out.println(Menu.indent(giocoTempo, Costanti.TIME7));
    System.out.println(Costanti.SEPARATORE_2 + Costanti.ANSI_RESET);
}


/**
 * Effettua il parsing di una stringa di coordinate e restituisce l'oggetto Coordinate corrispondente.
 *
 * @param coordinateInput la stringa contenente le coordinate
 * @return l'oggetto Coordinate corrispondente, null se la stringa non è valida
 */
    public static Coordinate parseCoordinate(final String coordinateInput) {
        if (coordinateInput.length() < 2) {
            return null;
        }
        char colonnaChar = Character.toLowerCase(coordinateInput.charAt(0));
        int riga;
        try {
            riga = Integer.parseInt(coordinateInput.substring(1));
        } catch (NumberFormatException e) {
            return null;
        }

        if (colonnaChar < 'a' || colonnaChar > 'g' || riga < 1 || riga > Costanti.RIGAF) {
            return null;
        }

        int colonna = colonnaChar - 'a';

        return new Coordinate(riga, colonna);
    }
/**
 * Gestisce il comando di bloccaggio di una casella.
 *
 * @param input il comando di bloccaggio
 * @param caselleDaBloccare la lista delle caselle attualmente bloccate
 * @param tavoliere il tavoliere di gioco
 * @param blocca l'oggetto Blocca per il bloccaggio delle caselle
 */
public static void gestioneBlocca(final String input, final List<Coordinate> caselleDaBloccare,
final Tavoliere tavoliere, final Blocca blocca) {
if (input.toLowerCase().startsWith("/blocca")) {
String[] parts = input.split(" ");
if (parts.length > 1) {
String coordString = parts[1].trim();
Coordinate coordinate = Partita.parseCoordinate(coordString);
if (coordinate != null && Partita.isValidCoordinate(coordString)) {
if (caselleDaBloccare.size() < Costanti.LIMITE_BLOCCA) {
// Controlla se la casella è già bloccata
if (!caselleDaBloccare.contains(coordinate)) {
if (blocca.bloccaCasella(coordinate)) {
caselleDaBloccare.add(coordinate);
System.out.println("Casella bloccata con successo: " + coordinate);
} else {
System.out.println("Errore nel bloccare la casella: " + coordinate);
}
} else {
System.out.println("Casella già bloccata: " + coordinate);
}
} else {
System.out.println("Non puoi bloccare più di 9 caselle.");
}
} else {
System.out.println(
"Coordinata non valida. Usa il comando /blocca seguito da una coordinata valida.");
}
} else {
System.out.println("Comando non valido. Usa il comando /blocca seguito da una coordinata.");
}
// Stampa le caselle attualmente bloccate
System.out.print("Caselle attualmente bloccate: ");
for (int i = 0; i < caselleDaBloccare.size(); i++) {
if (i != 0) {
System.out.print(", ");
}
System.out.print(caselleDaBloccare.get(i));
}
System.out.println();
 }
}
/**
 * Verifica se una stringa rappresenta una coordinata valida.
 *
 * @param coordinate la stringa da verificare
 * @return true se la stringa rappresenta una coordinata valida, altrimenti false
 */
    public static boolean isValidCoordinate(final String coordinate) {
        // Aggiungi la logica di validazione della coordinata, ad esempio
        return coordinate.matches("^[a-g][1-7]$"); // Supponendo che le coordinate valide siano da a1 a h8
    }
}
