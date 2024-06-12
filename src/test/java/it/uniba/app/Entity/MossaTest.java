package it.uniba.app.Entity;

import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Test;

import it.uniba.app.Boundary.Costanti;



import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;


/**
 * Test per la classe Mossa.
 */
final class MossaTest {
    private static Duplicazione mossea;
    private static Salto mosseb;
    private static Pedina[][] scacchiera;
    private static Mossa mossa;

    /**
     * Metodo di setup per i test, cioè istanzia un oggetto Mossa.
     */
    @BeforeAll
    static void setup() {
        scacchiera = new Pedina[Costanti.RIGA_7][Costanti.RIGA_7];
        mossa = new Mossa(scacchiera, mossea, mosseb);
        mossea = new Duplicazione(mossa);
        mosseb = new Salto(mossa);
    }

    /**
     * Test per copia difensiva della scacchiera.
     */
    void testCopiaDifensivaScacchiera() {
    // Assume Costanti.RIGA_7 is a constant defining the size of the board
    Pedina[][] scacchieraOriginale = new Pedina[Costanti.RIGA_7][Costanti.RIGA_7];
    Tavoliere mossas = new Tavoliere(Costanti.RIGA_7);

    Pedina[][] scacchieraCopia = mossas.getScacchiera();

    // Check if the copied board is not the same reference as the original board
    assertNotSame(scacchieraOriginale, scacchieraCopia,
    "The copied board should not be the same reference as the original.");


    // Additional checks to validate the content of the copied board
    // Assuming testDimensioniScacchiera and testRigheScacchiera are methods to validate further
    testDimensioniScacchiera(scacchieraOriginale, scacchieraCopia);
    testRigheScacchiera(scacchieraOriginale, scacchieraCopia);
}

    /**
     * Test delle dimensioni della scacchiera.
     */
    void testDimensioniScacchiera(final Pedina[][] scacchieraOriginale, final Pedina[][] scacchieraCopia) {
        assertEquals(scacchieraOriginale.length, scacchieraCopia.length,
                "Le dimensioni della scacchiera dovrebbero corrispondere.");
    }

    /**
     * Test delle righe della scacchiera.
     */
    void testRigheScacchiera(final Pedina[][] scacchieraOriginale, final Pedina[][] scacchieraCopia) {
        for (int i = 0; i < scacchieraOriginale.length; i++) {
            assertArrayEquals(scacchieraOriginale[i], scacchieraCopia[i],
                    "I contenuti delle righe della scacchiera dovrebbero corrispondere.");
        }
    }

    /**
     * Test del costruttore di Duplicazione.
     */
    @Test
    void testCostruttoreDuplicazione() {
        Mossa mossas = new Mossa(scacchiera, mossea, mosseb);
        Duplicazione duplicazione = new Duplicazione(mossas);
        assertNotNull(duplicazione, "L'oggetto Duplicazione dovrebbe essere stato creato correttamente.");
    }

    /**
     * Test del costruttore di Salto.
     */
    @Test
    void testCostruttoreSalto() {
        Mossa mossas = new Mossa(scacchiera, mossea, mosseb);
        Salto salto = new Salto(mossas);
        assertNotNull(salto, "L'oggetto Salto dovrebbe essere stato creato correttamente.");
    }

}
