package it.uniba.app.Entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Name: TavoliereTest.java.
 */
 class  TavoliereTest {

    private Tavoliere tavoliere;

    // Codice ANSI per il testo verde
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RESET = "\u001B[0m";

    /**
     * Inizializza il tavoliere prima di ogni test.
     */
    @BeforeEach
    void setUp() {
        tavoliere = Tavoliere.creaTavoliere();
    }

    // Metodo di utilità per stampare il messaggio di successo in verde
    private void printSuccessMessage(final String testName) {
        System.out.println(ANSI_GREEN + testName + " TEST RIUSCITO" + ANSI_RESET);
    }

    /**
     * Test per il metodo creaTavoliere.
     */
    @Test
    void testCreaTavoliere() {
        assertNotNull(tavoliere, "Tavoliere should not be null");
        printSuccessMessage("testCreaTavoliere - Tavoliere non nullo");
    }

    /**
     * Test per il metodo creaTavoliereDimensione.
     */
    @Test
    void testCreaTavoliereDimensione() {
        final int dim = 7;
        assertEquals(dim, tavoliere.getScacchiera().length, "Tavoliere dimension should be 7");
        printSuccessMessage("testCreaTavoliere - Dimensione corretta");
    }

    /**
     * Test per il metodo creaTavoliereDimensioneColonne.
     */
    @Test
    void testCreaTavoliereDimensioneColonne() {
        final int dim = 7;
        assertEquals(dim, tavoliere.getScacchiera()[0].length, "Tavoliere column dimension should be 7");
        printSuccessMessage("testCreaTavoliere - Dimensione colonne corretta");
    }

    /**
     * Test per il metodo contaPedine.
     */
    @Test
    void testContaPedineNera() {
        final String nera = "\u26C1";   // Pedina nera
        Pedina pedinaNera = new Pedina(nera, new Coordinate(1, 1));
        tavoliere.setPedina(pedinaNera, 1, 1);
        int count = tavoliere.contaPedine(nera, tavoliere);
        assertEquals(1, count, "Number of black pawns should be 1");
        printSuccessMessage("testContaPedine - Pedina nera contata correttamente");
    }

    /**
     * Test per il metodo contaPedine.
     */
    @Test
    void testContaPedineBianca() {
        final String bianca = "\u26C3"; // Pedina bianca
        Pedina pedinaBianca = new Pedina(bianca, new Coordinate(1, 2));
        tavoliere.setPedina(pedinaBianca, 1, 2);
        int count = tavoliere.contaPedine(bianca, tavoliere);
        assertEquals(1, count, "Number of white pawns should be 1");
        printSuccessMessage("testContaPedine - Pedina bianca contata correttamente");
    }

    /**
     * Test per il metodo getTurno.
     */
    @Test
    void testGetTurno() {
        assertEquals(1, tavoliere.getTurno(), "Turn should be 1");
        printSuccessMessage("testGetTurno");
    }

    /**
     * Test per il metodo getPedina.
     */
    @Test
    void testGetPedinaEsistente() {
        final String nera = "\u26C1";   // Pedina nera
        Pedina pedina = new Pedina(nera, new Coordinate(1, 1));
        tavoliere.setPedina(pedina, 1, 1);
        assertEquals(pedina, tavoliere.getPedina(1, 'a'), "Should return existing pedina");
        printSuccessMessage("testGetPedina - Pedina esistente");
    }

    /**
     * Test per il metodo getPedina.
     */
    @Test
    void testGetPedinaNonEsistente() {
        assertNull(tavoliere.getPedina(1, 'b'), "Should return null for non-existing pedina");
        printSuccessMessage("testGetPedina - Pedina non esistente");
    }

    /**
     * Test per il metodo setPedina.
     */
    @Test
    void testSetPedinaValida() {
        final String nera = "\u26C1";   // Pedina nera
        Pedina pedina = new Pedina(nera, new Coordinate(1, 1));
        assertTrue(tavoliere.setPedina(pedina, 1, 1), "Should return true for valid pedina");
        printSuccessMessage("testSetPedina - Pedina valida");
    }

    /**
     * Test per il metodo setPedina.
     */
    @Test
    void testSetPedinaNonValida() {
        final int dim2 = 8;
        final String nera = "\u26C1";   // Pedina nera
        Pedina pedina = new Pedina(nera, new Coordinate(1, 1));
        assertFalse(tavoliere.setPedina(pedina, dim2, dim2), "Should return false for invalid pedina");
        printSuccessMessage("testSetPedina - Pedina non valida");
    }

    /**
     * Test per il metodo posizioneVuota.
     */
    @Test
    void testPosizioneVuotaIniziale() {
        assertTrue(tavoliere.posizioneVuota(0, 0), "Initial position should be empty");
        printSuccessMessage("testPosizioneVuota - Posizione iniziale vuota");
    }

    /**
     * Test per il metodo posizioneVuota.
     */
    @Test
    void testPosizioneVuotaDopoInserimento() {
        final String nera = "\u26C1";   // Pedina nera
        Pedina pedina = new Pedina(nera, new Coordinate(1, 1));
        tavoliere.setPedina(pedina, 1, 1);
        assertFalse(tavoliere.posizioneVuota(0, 0), "Position should not be empty after insertion");
        printSuccessMessage("testPosizioneVuota - Posizione dopo inserimento");
    }

    /**
     * Test per il metodo inizializzaPedine.
     */
    @Test
    void testInizializzaPedineNera() {
        final int dim3 = 7;
        final String nera = "\u26C1";   // Pedina nera
        tavoliere.inizializzaPedine(1, 1, dim3, dim3);
        assertEquals(nera, tavoliere.getPedina(1, 'a').getCarattere(), "Initial black pedina should be correct");
        printSuccessMessage("testInizializzaPedine - Pedina nera");
    }

    /**
     * Test per il metodo inizializzaPedine.
     */
    @Test
    void testInizializzaPedineBianca() {
        final int dim3 = 7;
        final String bianca = "\u26C3"; // Pedina bianca
        tavoliere.inizializzaPedine(1, 1, dim3, dim3);
        assertEquals(bianca, tavoliere.getPedina(1, 'g').getCarattere(), "Initial white pedina should be correct");
        printSuccessMessage("testInizializzaPedine - Pedina bianca");
    }

    /**
     * Test per il metodo inizializzaPedine.
     */
    @Test
    void testInizializzaPedineNeraSeconda() {
        final int dim3 = 7;
        final String nera = "\u26C1";   // Pedina nera
        tavoliere.inizializzaPedine(1, 1, dim3, dim3);
        assertEquals(nera, tavoliere.getPedina(dim3, 'g').getCarattere(),
         "Second initial black pedina should be correct");
        printSuccessMessage("testInizializzaPedine - Seconda pedina nera");
    }

    /**
     * Test per il metodo inizializzaPedine.
     */
    @Test
    void testInizializzaPedineBiancaSeconda() {
        final int dim3 = 7;
        final String bianca = "\u26C3"; // Pedina bianca
        tavoliere.inizializzaPedine(1, 1, dim3, dim3);
        assertEquals(bianca, tavoliere.getPedina(dim3, 'a').getCarattere(),
        "Second initial white pedina should be correct");
        printSuccessMessage("testInizializzaPedine - Seconda pedina bianca");
    }

    /**
     * Test per il metodo inizializzaCaselleBloccate.
     */
    @Test
    void testInizializzaCaselleBloccateNulla() {
        final int dim3 = 3;
        final int dim4 = 4;
        Coordinate coordinateBloccata = new Coordinate(dim3, dim3);
        Tavoliere tavoliere2 = new Tavoliere(dim4);
        tavoliere2.inizializzaCaselleBloccate(coordinateBloccata);
        Pedina pedinaBloccata = tavoliere2.getPedina(dim3, (char) dim3);
        assertNull(pedinaBloccata, "Blocked position should be null");
        printSuccessMessage("testInizializzaCaselleBloccate - Casella bloccata nulla");
    }

    /**
     * Test per il metodo inizializzaCaselleBloccate.
     */
    @Test
    void testInizializzaCaselleBloccateNonBloccata() {
        final int dim4 = 4;
        final String x = "\u2B24"; // Pallino rosso: ⬤
        final int dim3 = 3;
        Coordinate coordinateBloccata = new Coordinate(dim3, dim3);
        Tavoliere tavoliere2 = new Tavoliere(dim4);
        tavoliere2.inizializzaCaselleBloccate(coordinateBloccata);
        Pedina pedinaNonBloccata = tavoliere2
        .getPedina(dim4, 'd'); // Supponendo che dim4, 'd' sia una posizione valida e non bloccata
        if (pedinaNonBloccata != null) {
            assertEquals(x, pedinaNonBloccata.getCarattere(), "Non-blocked position should be correct");
            printSuccessMessage("testInizializzaCaselleBloccate - Casella non bloccata corretta");
        }
    }

    /**
     * Test per il metodo resettaCaselleBloccate.
     */
    @SuppressWarnings("unused")
    @Test
    void testCostruttoreCopiaEsistenza() {
        Tavoliere tavoliereOriginale = Tavoliere.creaTavoliere();
        tavoliereOriginale.setPedina(new Pedina("\u26C1", new Coordinate(1, 1)), 1, 1); // ⛁
        Tavoliere tavoliereCopia = new Tavoliere(tavoliereOriginale);
        Pedina pedinaOriginale = tavoliereOriginale.getPedina(1, 'a');
        Pedina pedinaCopia = tavoliereCopia.getPedina(1, 'a');
        assertNotNull(pedinaCopia, "Copied pedina should exist");
        printSuccessMessage("testCostruttoreCopia - Esistenza pedina copia");
    }

    /**
     * Test per il metodo resettaCaselleBloccate.
     */
    @Test
    void testCostruttoreCopiaCarattere() {
        Tavoliere tavoliereOriginale = Tavoliere.creaTavoliere();
        tavoliereOriginale.setPedina(new Pedina("\u26C1", new Coordinate(1, 1)), 1, 1); // ⛁
        Tavoliere tavoliereCopia = new Tavoliere(tavoliereOriginale);
        Pedina pedinaOriginale = tavoliereOriginale.getPedina(1, 'a');
        Pedina pedinaCopia = tavoliereCopia.getPedina(1, 'a');
        assertEquals(pedinaOriginale.getCarattere(), pedinaCopia.getCarattere(),
         "Copied pedina character should be the same");
        printSuccessMessage("testCostruttoreCopia - Carattere pedina copia");
    }

    /**
     * Test per il metodo resettaCaselleBloccate.
     */
    @Test
    void testCostruttoreCopiaCoordinate() {
        Tavoliere tavoliereOriginale = Tavoliere.creaTavoliere();
        tavoliereOriginale.setPedina(new Pedina("\u26C1", new Coordinate(1, 1)), 1, 1); // ⛁
        Tavoliere tavoliereCopia = new Tavoliere(tavoliereOriginale);
        Pedina pedinaOriginale = tavoliereOriginale.getPedina(1, 'a');
        Pedina pedinaCopia = tavoliereCopia.getPedina(1, 'a');
        assertEquals(pedinaOriginale.getCoordinate(), pedinaCopia.getCoordinate(),
        "Copied pedina coordinates should be the same");
        printSuccessMessage("testCostruttoreCopia - Coordinate pedina copia");
    }

    /**
     * Test per il metodo resettaCaselleBloccate.
     */
    @Test
    void testCostruttoreCopiaOggettiDiversi() {
        Tavoliere tavoliereOriginale = Tavoliere.creaTavoliere();
        tavoliereOriginale.setPedina(new Pedina("\u26C1", new Coordinate(1, 1)), 1, 1); // ⛁
        Tavoliere tavoliereCopia = new Tavoliere(tavoliereOriginale);
        Pedina pedinaOriginale = tavoliereOriginale.getPedina(1, 'a');
        Pedina pedinaCopia = tavoliereCopia.getPedina(1, 'a');
        assertNotSame(pedinaOriginale, pedinaCopia, "Original and copied pedina should be different objects");
        printSuccessMessage("testCostruttoreCopia - Oggetti diversi");
    }
}
