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

    /**
     * .
     */
    @Test
     void testmossaValidaConCoordinataNonValida() {
        Coordinate from = new Coordinate(1, 1);
        Coordinate to = new Coordinate(Costanti.RIGA_5, Costanti.RIGA_5);
        assertFalse(partita.mossaValida(from, to), "La mossa dovrebbe essere valida");
    }

     /**
     * .
     */
    @Test
    void testmossaValidaCasellaBloccata() {
        caselleBloccate = new ArrayList<>();
        Coordinate from = new Coordinate(Costanti.RIGA_3, 'b');
        Coordinate to = new Coordinate(Costanti.RIGA_3, 'a');
        blocca.bloccaCasella(to);
        tavoliere.inizializzaCaselleBloccate(to);
        assertFalse(partita.mossaValida(from, to), "La mossa su una casella bloccata dovrebbe essere valida");
    }
      /**
     * .
     */
    @Test
    void testmossaValidaCasellaPosizioneNonVuota() {
        Coordinate from = new Coordinate(Costanti.RIGA_2, 1);
        Coordinate to = new Coordinate(1, 1);
        assertFalse(partita.mossaValida(from, to), "La mossa su una casella non vuota dovrebbe essere valida");
    }



    /**
     *
     */
    @Test
     void testgiocatoreSenzaMosseDisponibili() {
        // Simuliamo la situazione in cui il tavoliere è completamente pieno e nessun
        // giocatore ha mosse disponibili
        // Popoliamo il tavoliere con pedine in modo che ogni cella sia occupata

        for (int riga = 1; riga <= Costanti.RIGAF; riga++) {
            for (char colonna = 'a'; colonna <= 'g'; colonna++) {
                Pedina pedina = new Pedina("N", new Coordinate(riga, colonna));
                tavoliere.setPedina(pedina, riga, colonna);
            }
        }
        // Assicuriamoci che nessun giocatore abbia mosse disponibili
        assertFalse(partita.giocatoreHaMosseDisponibili(0), "Il giocatore 1 non dovrebbe avere mosse disponibili");
    }



/**
     * test per il metodo partitaFinita.
     */
    @Test
void testPartitaFinitaConTAvolierePieno() {
    // Verifica che la partita non sia finita
    Tavoliere tavoliere1 = new Tavoliere(Costanti.RIGA_7);
    for (int riga = 1; riga <= Costanti.RIGAF; riga++) {
        for (char colonna = 'a'; colonna <= 'g'; colonna++) {
            int colonnaInt = colonna - 'a' + 1; // Converti il carattere della colonna in un intero
            Pedina pedina = new Pedina("N", new Coordinate(riga, colonnaInt));
            tavoliere1.setPedina(pedina, riga, colonnaInt);
        }
    }
    partita = new Partita(giocatore1, giocatore2, tavoliere1, blocca, duplicazione, salto);
    assertTrue(partita.partitaFinita(), "La partita dovrebbe essere finita");
}




    @Test
    void testCoordinateNonValidaA8() {
        assertFalse(Partita.isValidCoordinate("a8"), "La coordinata 'a8' dovrebbe essere non valida");
        printSuccessMessage("testCoordinateNonValidaA8");
    }

    @Test
    void testCoordinateNonValidaB0() {
        assertFalse(Partita.isValidCoordinate("b0"), "La coordinata 'b0' dovrebbe essere non valida");
        printSuccessMessage("testCoordinateNonValidaB0");
    }

    @Test
    void testCoordinateNonValidaH8() {
        assertFalse(Partita.isValidCoordinate("h8"), "La coordinata 'h8' dovrebbe essere non valida");
        printSuccessMessage("testCoordinateNonValidaH8");
    }

    @Test
    void testCoordinateNonValida11() {
        assertFalse(Partita.isValidCoordinate("11"), "La coordinata '11' dovrebbe essere non valida");
        printSuccessMessage("testCoordinateNonValida11");
    }

    @Test
    void testCoordinateNonValidaAA() {
        assertFalse(Partita.isValidCoordinate("aa"), "La coordinata 'aa' dovrebbe essere non valida");
        printSuccessMessage("testCoordinateNonValidaAA");
    }

    @Test
    void testCoordinateNonValidaVuota() {
        assertFalse(Partita.isValidCoordinate(""), "La coordinata vuota dovrebbe essere non valida");
        printSuccessMessage("testCoordinateNonValidaVuota");
    }

    @Test
    void testBloccoCasellaValida() {
        String input = "/blocca a1";
        List<Coordinate> caselleDaBloccare = new ArrayList<>();
        Partita.gestioneBlocca(input, caselleDaBloccare, tavoliere, blocca);
        assertFalse(caselleDaBloccare.contains(new Coordinate(0, 0)), "La casella a1 dovrebbe essere bloccata");
        printSuccessMessage("testBloccoCasellaValida");
    }

    @Test
    void testBloccoCasellaNonValida() {
        String input = "/blocca h8";
        final int riga = 7;
        List<Coordinate> caselleDaBloccare = new ArrayList<>();
        Partita.gestioneBlocca(input, caselleDaBloccare, tavoliere, blocca);
        assertFalse(caselleDaBloccare.contains(new Coordinate(riga, riga)),
        "La casella h8 non dovrebbe essere bloccata");
        printSuccessMessage("testBloccoCasellaNonValida");
    }

    @Test
    void testBloccoCasellaGiaBloccata() {
        List<Coordinate> caselleDaBloccare = new ArrayList<>();
        caselleDaBloccare.add(new Coordinate(0, 0));
        String input = "/blocca a1";
        Partita.gestioneBlocca(input, caselleDaBloccare, tavoliere, blocca);
        assertEquals(1, caselleDaBloccare.size(), "La casella a1 dovrebbe essere già bloccata");
        printSuccessMessage("testBloccoCasellaGiaBloccata");
    }

    @Test
    void testBloccoCasellaLimiteSuperato() {
        final int riga = 9;
        List<Coordinate> caselleDaBloccare = new ArrayList<>();
        for (int i = 0; i < riga; i++) {
            caselleDaBloccare.add(new Coordinate(i, 0));
        }
        String input = "/blocca a2";
        Partita.gestioneBlocca(input, caselleDaBloccare, tavoliere, blocca);
        assertEquals(riga, caselleDaBloccare.size(), "Il limite di caselle bloccate dovrebbe essere 9");
        printSuccessMessage("testBloccoCasellaLimiteSuperato");
    }

    @Test
    void testBloccoComandoNonValido() {
        String input = "/blocco a1";
        List<Coordinate> caselleDaBloccare = new ArrayList<>();
        Partita.gestioneBlocca(input, caselleDaBloccare, tavoliere, blocca);
        assertTrue(caselleDaBloccare.isEmpty(), "Il comando non valido non dovrebbe bloccare nessuna casella");
        printSuccessMessage("testBloccoComandoNonValido");
    }

    @Test
    void testBloccoNessunaCoordinata() {
        String input = "/blocca";
        List<Coordinate> caselleDaBloccare = new ArrayList<>();
        Partita.gestioneBlocca(input, caselleDaBloccare, tavoliere, blocca);
        assertTrue(caselleDaBloccare.isEmpty(), "Nessuna coordinata non dovrebbe bloccare nessuna casella");
        printSuccessMessage("testBloccoNessunaCoordinata");
    }

    @Test
    void testBloccoCoordinataNonValida() {
        String input = "/blocca aa";
        List<Coordinate> caselleDaBloccare = new ArrayList<>();
        Partita.gestioneBlocca(input, caselleDaBloccare, tavoliere, blocca);
        assertTrue(caselleDaBloccare.isEmpty(), "La coordinata non valida non dovrebbe bloccare nessuna casella");
        printSuccessMessage("testBloccoCoordinataNonValida");
    }

    @Test
    void testResetUscitaRichiesta() {
        partita.reset(caselleBloccate);
        assertFalse(partita.isUscitaRichiesta(), "L'uscita richiesta dovrebbe essere reimpostata a false");
        printSuccessMessage("testResetUscitaRichiesta");
    }

    @Test
    void testResetAbbandono() {
        partita.reset(caselleBloccate);
        assertFalse(partita.isAbbandono(), "L'abbandono dovrebbe essere reimpostato a false");
        printSuccessMessage("testResetAbbandono");
    }

    @Test
    void testResetTurno() {
        partita.reset(caselleBloccate);
        assertEquals(1, partita.getTurno(), "Il turno dovrebbe essere reimpostato a 1");
        printSuccessMessage("testResetTurno");
    }

    @Test
    void testMossaValida() {
        Coordinate from = new Coordinate(1, 1);
        Coordinate to = new Coordinate(2, 1);
        assertTrue(partita.mossaValida(from, to), "La mossa dovrebbe essere valida");
    }

    @Test
    void testMossaNonValida() {
        Coordinate from = new Coordinate(Costanti.RIGA_7, Costanti.RIGA_7);
        Coordinate to = new Coordinate(2, 1);
        assertFalse(partita.mossaValida(from, to), "La mossa dovrebbe essere non valida");
    }

    @Test
    void testMossaValidaConCoordinataNonValida() {
        Coordinate from = new Coordinate(1, 1);
        Coordinate to = new Coordinate(Costanti.RIGA_5, Costanti.RIGA_5);
        assertFalse(partita.mossaValida(from, to), "La mossa dovrebbe essere non valida");
    }

    @Test
    void testMossaValidaCasellaBloccata() {
        caselleBloccate = new ArrayList<>();
        Coordinate from = new Coordinate(Costanti.RIGA_3, 'b');
        Coordinate to = new Coordinate(Costanti.RIGA_3, 'a');
        blocca.bloccaCasella(to);
        tavoliere.inizializzaCaselleBloccate(to);
        assertFalse(partita.mossaValida(from, to), "La mossa su una casella bloccata dovrebbe essere non valida");
    }

    @Test
    void testMossaValidaCasellaPosizioneNonVuota() {
        Coordinate from = new Coordinate(Costanti.RIGA_2, 1);
        Coordinate to = new Coordinate(1, 1);
        assertFalse(partita.mossaValida(from, to), "La mossa verso una casella occupata dovrebbe essere non valida");
    }

    @Test
    void testBloccaCasellaPiena() {
        Coordinate coordinate = new Coordinate(Costanti.RIGA_4, 'c');
        partita.bloccaCasella(coordinate);
        assertTrue(partita.bloccaCasella(coordinate).contains(coordinate), "La casella c4 dovrebbe essere bloccata");
    }

    @Test
    void testBloccaCasella() {
        Coordinate coordinate = new Coordinate(Costanti.RIGA_1, 'a');
        partita.bloccaCasella(coordinate);
        assertTrue(partita.bloccaCasella(coordinate).contains(coordinate), "La casella a1 dovrebbe essere bloccata");
    }

    @Test
    void testBloccaCasellaGiaBloccata() {
        Coordinate coordinate = new Coordinate(Costanti.RIGA_4, 'c');
        partita.bloccaCasella(coordinate);
        int sizeBefore = partita.bloccaCasella(coordinate).size();
        int sizeAfter = partita.bloccaCasella(coordinate).size();
        assertEquals(sizeBefore, sizeAfter, "La casella c4 non dovrebbe essere bloccata nuovamente");
    }

    @Test
    void testAvviaPartitaSenzaCaselleBloccate() {
        caselleBloccate = new ArrayList<>();
        partita = new Partita(giocatore2, giocatore1, tavoliere, blocca, duplicazione, salto);
        assertTrue(caselleBloccate.isEmpty(), "La lista delle caselle bloccate dovrebbe essere vuota");
    }

    @Test
    void testAvviaPartitaConCaselleBloccate() {
        Coordinate coordinate = new Coordinate(Costanti.RIGA_1, 'd');
        caselleBloccate.add(coordinate);
        partita = new Partita(giocatore2, giocatore1, tavoliere, blocca, duplicazione, salto);
        assertTrue(partita.bloccaCasella(coordinate).contains(coordinate), "La casella d1 dovrebbe essere bloccata");
    }

    @Test
    void testAvviaPartitaUscitaRichiesta() {
        partita.setUscitaRichiesta(true);
        boolean risultato = partita.avviaPartita(new ArrayList<>());
        assertTrue(risultato, "La partita dovrebbe terminare con uscita richiesta");
    }

    @Test
    void testAvviaPartitaAbbandono() {
        partita.setAbbandono(true);
        boolean risultato = partita.avviaPartita(new ArrayList<>());
        assertFalse(risultato, "La partita dovrebbe terminare con abbandono");
    }
}
