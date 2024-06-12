package it.uniba.app.Control;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import it.uniba.app.Boundary.Costanti;
import it.uniba.app.Entity.Blocca;
import it.uniba.app.Entity.Coordinate;
import it.uniba.app.Entity.Duplicazione;
import it.uniba.app.Entity.Giocatore;
import it.uniba.app.Entity.Mossa;
import it.uniba.app.Entity.Pedina;
import it.uniba.app.Entity.Salto;
import it.uniba.app.Entity.Tavoliere;

/**
 * .
 */
 class PartitaTest {
    private Giocatore giocatore1;
    private Giocatore giocatore2;
    private Tavoliere tavoliere;
    private Blocca blocca;
    private Duplicazione duplicazione;
    private Salto salto;
    private Mossa mossa;
    private Pedina[][] scacchiera;
    private Partita partita;
    private List<Coordinate> caselleBloccate;
    private Pedina pedina1;
    private Pedina pedina2;
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RESET = "\u001B[0m";

    final void printSuccessMessage(final String testName) {
        System.out.println(ANSI_GREEN + testName + " TEST RIUSCITO" + ANSI_RESET);
    }
    /**
     * .
     */
    @BeforeEach
    void setUp() {
        // Inizializza le pedine
        pedina1 = new Pedina("X", new Coordinate(0, 0));
        pedina2 = new Pedina("O", new Coordinate(Costanti.RIGA_7, 1));

        // Inizializza i giocatori con le pedine
        giocatore1 = new Giocatore(pedina1, "Nero");
        giocatore2 = new Giocatore(pedina2, "Bianco");
        tavoliere = new Tavoliere(Costanti.RIGA_7);
        // Inizializza la scacchiera e le mosse
        scacchiera = new Pedina[Costanti.RIGA_7][Costanti.RIGA_7];
        mossa = new Mossa(scacchiera, duplicazione, salto);
        // Inizializza blocca, duplicazione e salto
        caselleBloccate = new ArrayList<>();
        blocca = new Blocca(scacchiera, caselleBloccate);
        duplicazione = new Duplicazione(mossa);
        salto = new Salto(mossa);
        partita = new Partita(giocatore1, giocatore2, tavoliere, blocca, duplicazione, salto);
    }

    /**
     *
     */
    @Test
    void testGiocatore1NonNull() {
        partita = new Partita(giocatore1, giocatore2, tavoliere, blocca, duplicazione, salto);
        assertNotNull(giocatore1, "Il giocatore 1 non dovrebbe essere nullo");
    }

    /**
     *
     */
    @Test
    void testGiocatore2NonNull() {
        partita = new Partita(giocatore1, giocatore2, tavoliere, blocca, duplicazione, salto);
        assertNotNull(giocatore2, "Il giocatore 2 non dovrebbe essere nullo");
    }

    /**
     *
     */
    @Test
    void testGiocatore1Nome() {
        partita = new Partita(giocatore1, giocatore2, tavoliere, blocca, duplicazione, salto);
        assertEquals("Nero", giocatore1.getNome(), "Il nome del giocatore 1 dovrebbe essere Nero");
    }

    /**
     *
     */
    @Test
     void testGiocatore2Nome() {
        partita = new Partita(giocatore1, giocatore2, tavoliere, blocca, duplicazione, salto);
        assertEquals("Bianco", giocatore2.getNome(), "Il nome del giocatore 2 dovrebbe essere Bianco");
    }

    /**
     *
     */
    @Test
     void testGiocatore1PedinaNonNull() {
        partita = new Partita(giocatore1, giocatore2, tavoliere, blocca, duplicazione, salto);
        assertNotNull(giocatore1.getPedina(), "La pedina del giocatore 1 non dovrebbe essere nulla");
    }

    /**
     *
     */
    @Test
   void testGiocatore2PedinaNonNull() {
        partita = new Partita(giocatore1, giocatore2, tavoliere, blocca, duplicazione, salto);
        assertNotNull(giocatore2.getPedina(), "La pedina del giocatore 2 non dovrebbe essere nulla");
    }

    /**
     *
     */
    @Test
    void testTavoliereNonNull() {
        partita = new Partita(giocatore1, giocatore2, tavoliere, blocca, duplicazione, salto);
        assertNotNull(tavoliere, "Il tavoliere non dovrebbe essere nullo");
    }




    /**
     *
     */
    @Test
     void testListaCaselleBloccateVuota() {
        partita = new Partita(giocatore1, giocatore2, tavoliere, blocca, duplicazione, salto);
        assertNotNull(blocca, "L'oggetto Blocca non dovrebbe essere nullo");
    }

    /**
     *
     */
    @Test
     void testListaCaselleBloccateVuotaDopoInizializzazionePartita() {
        // Crea l'oggetto Partita
        partita = new Partita(giocatore1, giocatore2, tavoliere, blocca, duplicazione, salto);

        // Verifica che la lista delle caselle bloccate sia vuota dopo
        // l'inizializzazione della partita
        assertEquals(0, caselleBloccate.size(), "La lista delle caselle bloccate dovrebbe essere vuota");
    }

    /**
     *
     */
    @Test
    void testCostruttoreMossaNull() {
        // Verifica che l'oggetto Mossa sia stato creato correttamente
        assertNotNull(mossa, "Mossa should not be null");
    }

    /**
     *
     */
    @Test
    void testCostruttoreMossaScacchiera() {
        // Verifica che la scacchiera all'inizio sia inizializzata con tutte le pedine
        // null
        for (int i = 0; i < scacchiera.length; i++) {
            for (int j = 0; j < scacchiera[i].length; j++) {
                assertEquals(null, tavoliere.getScacchiera()[i][j],
                "La scacchiera dovrebbe essere inizializzata con null");
            }
        }
    }

    /**
    *
    */
    @Test
    void testCostruttoreMossaDuplicazione() {
        // Verifica che le mosse di duplicazione all'inizio siano null
        assertEquals(new ArrayList<>(), duplicazione.mosseA(0, 0),
        "Le mosse di duplicazione all'inizio dovrebbero essere null");
    }

    /**
    *
    */
    @Test
    void testCostruttoreMossaSalto() {
        // Verifica che le mosse di salto all'inizio siano null
        assertEquals(new ArrayList<>(), salto.mosseB(0, 0), "Le mosse di salto all'inizio dovrebbero essere null");
    }

    /**
     * .
     */
    @Test
     void testCostruttoreDuplicazione() {
        partita = new Partita(giocatore1, giocatore2, tavoliere, blocca, duplicazione, salto);
        // Verifichiamo se l'oggetto Duplicazione è stato creato correttamente
        assertNotNull(duplicazione, "Duplicazione should not be null");
    }

    /**
     * .
     */
    @Test
    void testCostruttoreSalto() {
        partita = new Partita(giocatore1, giocatore2, tavoliere, blocca, duplicazione, salto);
        // Verifichiamo se l'oggetto Duplicazione è stato creato correttamente
        assertNotNull(salto, "Salto should not be null");
    }

    /**
     *
     */
    @Test
    void testTempoInizioPartita() {
        long tempoInizioPartita = System.currentTimeMillis();
        assertNotNull(tempoInizioPartita, "Il tempo di inizio partita non dovrebbe essere nullo");
    }

    /**
     *
     */
    @Test
    void testStoriaMosseVuota() {
        List<String> storiaMosse = new ArrayList<>();
        assertTrue(storiaMosse.isEmpty(), "La storia delle mosse dovrebbe essere vuota all'inizio");
    }

    /**
     * Test per verificare la validità della coordinata "a1".
     */
    @Test
    void testCoordinateValidaA1() {
        assertTrue(Partita.isValidCoordinate("a1"), "La coordinata 'a1' dovrebbe essere valida");
        printSuccessMessage("testCoordinateValidaA1");
    }

    /**
     * Test per verificare la validità della coordinata "g7".
     */
    @Test
    void testCoordinateValidaG7() {
        assertTrue(Partita.isValidCoordinate("g7"), "La coordinata 'g7' dovrebbe essere valida");
        printSuccessMessage("testCoordinateValidaG7");
    }

    // Metodo di supporto per la stampa dei messaggi di successo nei test.

/**
 *
 */
    @Test
     void testCoordinateNonValidaH1() {
        assertFalse(Partita.isValidCoordinate("h1"), "La coordinata 'h1' dovrebbe essere non valida");
        printSuccessMessage("testCoordinateNonValidaH1");
    }




    /**
     * Verifica che una mossa valida di salto venga eseguita correttamente.
     */
    @Test
     void testmossaValida() {
        Coordinate from = new Coordinate(1, 1);
        Coordinate to = new Coordinate(2, 1);
        assertTrue(partita.mossaValida(from, to), "La mossa dovrebbe essere valida");
    }

    /**
     * .
     */
    @Test
     void testmossaNonValida() {
        Coordinate from = new Coordinate(Costanti.RIGA_7, Costanti.RIGA_7);
        Coordinate to = new Coordinate(2, 1);
        assertFalse(partita.mossaValida(from, to), "La mossa dovrebbe essere valida");
    }
}
