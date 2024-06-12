package it.uniba.app.Entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

}
