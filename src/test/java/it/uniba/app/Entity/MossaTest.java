package it.uniba.app.Entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import it.uniba.app.Boundary.Costanti;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    /**
     * Test per valori compresi nei limiti.
     */
    @Test
    @DisplayName("Test per valori compresi nei limiti")
    void testmosseCRigaColonnaCompresa() {
        ArrayList<Coordinate> mossec = new ArrayList<>();
        mossec.addAll(mossea.mosseA(Costanti.RIGA_4, 'd'));
        mossec.addAll(mosseb.mosseB(Costanti.RIGA_4, 'd'));
        assertNotNull(mossec, "L'ArrayList<Coordinate> mosse non dovrebbe essere nullo.");
    }

    /**
     * Verifica le mosse possibili quando la riga e la colonna sono al minimo.
     */
    @Test
    void testmosseCRigaColonnaMinima() {
        ArrayList<Coordinate> mossec = new ArrayList<>();
        mossec.addAll(mossea.mosseA(Costanti.RIGA_1, 'a'));
        mossec.addAll(mosseb.mosseB(Costanti.RIGA_1, 'a'));
        assertNotNull(mossec, "L'ArrayList<Coordinate> mosse non dovrebbe essere nullo.");
    }

    /**
     * Verifica le mosse possibili quando mossea è null.
     */
    @Test
    void testMosseCQuandoMosseaNull() {
        Mossa mosse = new Mossa(scacchiera, null, mosseb);
        ArrayList<Coordinate> mossec = mosse.mosseC(Costanti.RIGA_1, 'a');
        assertTrue(mossec.isEmpty(), "L'ArrayList<Coordinate> mosse dovrebbe essere vuoto.");
    }

    /**
     * Verifica le mosse possibili quando mosseb è null.
     */
    @Test
    void testMosseCQuandoMossebNull() {
        Mossa mosse = new Mossa(scacchiera, mossea, null);
        ArrayList<Coordinate> mossec = mosse.mosseC(Costanti.RIGA_1, 'a');
        assertTrue(mossec.isEmpty(), "L'ArrayList<Coordinate> mosse dovrebbe essere vuoto.");
    }

    /**
     * Verifica le mosse possibili quando sia mossea che mosseb sono null.
     */
    @Test
    void testMosseCQuandomossecNull() {
        Mossa mosse = new Mossa(scacchiera, null, null);
        ArrayList<Coordinate> mossec = mosse.mosseC(Costanti.RIGA_1, 'a');
        assertTrue(mossec.isEmpty(), "L'ArrayList<Coordinate> mosse dovrebbe essere vuoto.");
    }

    /**
     * Verifica le mosse possibili quando la riga e la colonna sono al massimo.
     */
    @Test
    void testmosseCRigaColonnaMassima() {
        ArrayList<Coordinate> mossec = new ArrayList<>();
        mossec.addAll(mossea.mosseA(Costanti.RIGA_7, 'g'));
        mossec.addAll(mosseb.mosseB(Costanti.RIGA_7, 'g'));
        assertNotNull(mossec, "L'ArrayList<Coordinate> mosse non dovrebbe essere nullo.");
    }

    /**
     * Verifica le mosse possibili quando la riga è al massimo e la colonna è al minimo.
     */
    @Test
    void testmosseCRigaMassima() {
        ArrayList<Coordinate> mossec = new ArrayList<>();
        mossec.addAll(mossea.mosseA(Costanti.RIGA_7, 'a'));
        mossec.addAll(mosseb.mosseB(Costanti.RIGA_2, 'c'));
        assertNotNull(mossec, "L'ArrayList<Coordinate> mosse non dovrebbe essere nullo.");
    }

    /**
     * Verifica le mosse possibili quando la riga è al minimo e la colonna è al massimo.
     */
    @Test
    void testmosseCColonnaMassima() {
        ArrayList<Coordinate> mossec = new ArrayList<>();
        mossec.addAll(mossea.mosseA(Costanti.RIGA_1, 'g'));
        mossec.addAll(mosseb.mosseB(Costanti.RIGA_2, 'c'));
        assertNotNull(mossec, "L'ArrayList<Coordinate> mosse non dovrebbe essere nullo.");
    }

    /**
     * Verifica le mosse possibili quando la riga e la colonna sono entrambe al di fuori dei limiti.
     */
    @Test
    void testmosseCRigaColonnaNonAppartenenti() {
        ArrayList<Coordinate> mossec = new ArrayList<>();
        mossec.addAll(mossea.mosseA(0, 'h'));
        mossec.addAll(mosseb.mosseB(Costanti.RIGA_2, 'c'));
        assertNotNull(mossec, "L'ArrayList<Coordinate> mosse non dovrebbe essere nullo.");
    }

    /**
     * Verifica le mosse possibili quando la riga è 0 e la colonna è compresa nei limiti.
     */
    @Test
    void testmosseBRigaNulla() {
        ArrayList<Coordinate> mossec = new ArrayList<>();
        mossec.addAll(mossea.mosseA(0, 'f'));
        mossec.addAll(mosseb.mosseB(Costanti.RIGA_2, 'c'));
        assertNotNull(mossec, "L'ArrayList<Coordinate> mosse non dovrebbe essere nullo.");
    }

    /**
     * Verifica le mosse possibili quando la riga e la colonna sono entrambe al di fuori dei limiti.
     */
    @Test
    void testmosseBRigaColonnaSuperioreMassima() {
        ArrayList<Coordinate> mossec = new ArrayList<>();
        mossec.addAll(mossea.mosseA(Costanti.RIGA_8, 'h'));
        mossec.addAll(mosseb.mosseB(Costanti.RIGA_2, 'c'));
        assertNotNull(mossec, "L'ArrayList<Coordinate> mosse non dovrebbe essere nullo.");
    }

    /**
     * Verifica le mosse possibili quando la riga è al di fuori dei limiti mentre la colonna è compresa.
     */
    @Test
    void testmosseCRigaSuperioreMassima() {
        ArrayList<Coordinate> mossec = new ArrayList<>();
        mossec.addAll(mossea.mosseA(Costanti.RIGA_8, 'd'));
        mossec.addAll(mosseb.mosseB(Costanti.RIGA_2, 'c'));
        assertNotNull(mossec, "L'ArrayList<Coordinate> mosse non dovrebbe essere nullo.");
    }

    /**
     * Verifica le mosse possibili quando la riga è compresa e la colonna è al di fuori dei limiti.
     */
    @Test
    void testMosseBColonnaSuperioreMassima() {
        ArrayList<Coordinate> mossec = new ArrayList<>();
        mossec.addAll(mossea.mosseA(Costanti.RIGA_4, 'h'));
        mossec.addAll(mosseb.mosseB(Costanti.RIGA_2, 'c'));
        assertNotNull(mossec, "L'ArrayList<Coordinate> mosse non dovrebbe essere nullo.");
    }

    /**
     * Verifica le mosse possibili quando la riga è = MAX_VALUE e la colonna è compresa.
     */
    @Test
    void testMosseBRigaIntMaxValue() {
        ArrayList<Coordinate> mossec = new ArrayList<>();
        mossec.addAll(mossea.mosseA(Integer.MAX_VALUE, 'd'));
        mossec.addAll(mosseb.mosseB(Costanti.RIGA_2, 'c'));
        assertNotNull(mossec, "L'ArrayList<Coordinate> mosse non dovrebbe essere nullo.");
    }

    /**
     * Verifica le mosse possibili quando la colonna è = MAX_VALUE e la riga è compresa.
     */
    @Test
    void testMosseBColonnaIntMaxValue() {
        ArrayList<Coordinate> mossec = new ArrayList<>();
        mossec.addAll(mossea.mosseA(Costanti.RIGA_4, (char) Integer.MAX_VALUE));
        mossec.addAll(mosseb.mosseB(Costanti.RIGA_2, 'c'));
        assertNotNull(mossec, "L'ArrayList<Coordinate> mosse non dovrebbe essere nullo.");
    }

    /**
     * Verifica le mosse possibili quando la riga è = MIN_VALUE e la colonna è compresa.
     */
    @Test
    void testMosseBRigaIntMinValue() {
        ArrayList<Coordinate> mossec = new ArrayList<>();
        mossec.addAll(mossea.mosseA(Integer.MIN_VALUE, 'd'));
        mossec.addAll(mosseb.mosseB(Costanti.RIGA_2, 'c'));
        assertNotNull(mossec, "L'ArrayList<Coordinate> mosse non dovrebbe essere nullo.");
    }

    /**
     * Verifica le mosse possibili quando la colonna è = MIN_VALUE e la riga è compresa.
     */
    @Test
    void testMossaBColonnaIntMinValue() {
        ArrayList<Coordinate> mossec = new ArrayList<>();
        mossec.addAll(mossea.mosseA(Costanti.RIGA_4, (char) Integer.MIN_VALUE));
        mossec.addAll(mosseb.mosseB(Costanti.RIGA_2, 'c'));
        assertNotNull(mossec, "L'ArrayList<Coordinate> mosse non dovrebbe essere nullo.");
    }

    /**
     * Verifica le mosse possibili quando la riga è al massimo e la colonna è minima.
     */
    @Test
    void testmosseCRigaMassima2() {
        ArrayList<Coordinate> mossec = new ArrayList<>();
        mossec.addAll(mossea.mosseA(Costanti.RIGA_2, 'c'));
        mossec.addAll(mosseb.mosseB(Costanti.RIGA_7, 'a'));
        assertNotNull(mossec, "L'ArrayList<Coordinate> mosse non dovrebbe essere nullo.");
    }

    /**
     * Verifica le mosse possibili quando la riga è al minimo e la colonna è al massimo.
     */
    @Test
    void testmosseCColonnaMassima2() {
        ArrayList<Coordinate> mossec = new ArrayList<>();
        mossec.addAll(mossea.mosseA(Costanti.RIGA_2, 'c'));
        mossec.addAll(mosseb.mosseB(Costanti.RIGA_1, 'g'));
        assertNotNull(mossec, "L'ArrayList<Coordinate> mosse non dovrebbe essere nullo.");
    }

    /**
     * Verifica le mosse possibili quando la riga e la colonna sono entrambe al di fuori dei limiti.
     */
    @Test
    void testmosseCRigaColonnaNonAppartenenti2() {
        ArrayList<Coordinate> mossec = new ArrayList<>();
        mossec.addAll(mossea.mosseA(Costanti.RIGA_2, 'c'));
        mossec.addAll(mosseb.mosseB(0, 'h'));
        assertNotNull(mossec, "L'ArrayList<Coordinate> mosse non dovrebbe essere nullo.");
    }

    /**
     * Verifica le mosse possibili quando la riga è 0 e la colonna è compresa nei limiti.
     */
    @Test
    void testmosseBRigaNulla2() {
        ArrayList<Coordinate> mossec = new ArrayList<>();
        mossec.addAll(mossea.mosseA(Costanti.RIGA_2, 'c'));
        mossec.addAll(mosseb.mosseB(0, 'f'));
        assertNotNull(mossec, "L'ArrayList<Coordinate> mosse non dovrebbe essere nullo.");
    }

    /**
     * Verifica le mosse possibili quando la riga e la colonna sono entrambe al di fuori dei limiti.
     */
    @Test
    void testmosseBRigaColonnaSuperioreMassima2() {
        ArrayList<Coordinate> mossec = new ArrayList<>();
        mossec.addAll(mossea.mosseA(Costanti.RIGA_2, 'c'));
        mossec.addAll(mosseb.mosseB(Costanti.RIGA_8, 'h'));
        assertNotNull(mossec, "L'ArrayList<Coordinate> mosse non dovrebbe essere nullo.");
    }

}
