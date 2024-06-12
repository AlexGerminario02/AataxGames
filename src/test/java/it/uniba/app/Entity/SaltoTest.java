package it.uniba.app.Entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import it.uniba.app.Boundary.Costanti;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Classe per test sulla classe Salto presente nel package Entity.
 */
 class SaltoTest {
    private static Salto salto;
    private static Mossa mossa;
    private static Pedina[][] scacchiera;

    /**
     * Metodo di setup per i test, cioè istanzia un oggetto Salto e un oggetto Mossa.
     */
    @BeforeAll
    static void setup() {
        scacchiera = new Pedina[Costanti.RIGA_7][Costanti.RIGA_7];
        mossa = new Mossa(scacchiera, null, salto);
        salto = new Salto(mossa);
    }

    /**
     * Verifica le mosse possibili quando la riga e la colonna sono comprese nei limiti.
     */
    @Test
    @DisplayName("Test per valori compresi nei limiti")
    void testmosseBRigaColonnaCompresa() {
        ArrayList<Coordinate> mosse = salto.mosseB(Costanti.RIGA_4, 'd');
        assertNotNull(mosse, "L'ArrayList<Coordinate> mosse non dovrebbe essere nullo");
    }

    /**
     * Test per il caso in cui riga e colonna estremi inferiori pari a 1 e 'a'.
     */
    @Test
    @DisplayName("Riga e colonna estremi inferiori pari a 1 e 'a'")
    void testmosseBRigaColonnaEstremiInferiori() {
        setup();
        ArrayList<Coordinate> mosse = salto.mosseB(1, 'a');
        assertNotNull(mosse, "L'ArrayList<Coordinate> mosse non dovrebbe essere nullo");
    }

    /**
     * Verifica le mosse possibili quando la riga e la colonna sono al minimo.
     */
    @Test
    void testmosseBRigaColonnaMinima() {
        ArrayList<Coordinate> mosse = salto.mosseB(1, 'a');
        assertNotNull(mosse, "L'ArrayList<Coordinate> mosse non dovrebbe essere nullo");
    }

    /**
     * Verifica le mosse possibili quando la riga e la colonna sono al massimo.
     */
    @Test
    void testmosseBRigaColonnaMassima() {
        ArrayList<Coordinate> mosse = salto.mosseB(Costanti.RIGA_7, 'g');
        assertNotNull(mosse, "L'ArrayList<Coordinate> mosse non dovrebbe essere nullo");
    }

    /**
     * Verifica le mosse possibili quando la riga è al massimo e la colonna è al minimo.
     */
    @Test
    void testmosseBRigaMassima() {
        ArrayList<Coordinate> mosse = salto.mosseB(Costanti.RIGA_7, 'a');
        assertNotNull(mosse, "L'ArrayList<Coordinate> mosse non dovrebbe essere nullo");
    }

    /**
     * Verifica le mosse possibili quando la riga è al minimo e la colonna è al massimo.
     */
    @Test
    void testmosseBColonnaMassima() {
        ArrayList<Coordinate> mosse = salto.mosseB(Costanti.RIGA_1, 'g');
        assertNotNull(mosse, "L'ArrayList<Coordinate> mosse non dovrebbe essere nullo");
    }

    /**
     * Verifica le mosse possibili quando la riga e la colonna sono entrambe al di fuori dei limiti.
     */
    @Test
    void testmosseBRigaColonnaNonAppartenenti() {
        ArrayList<Coordinate> mosse = salto.mosseB(0, 'h');
        assertEquals(new ArrayList<>(), mosse, "Le mosse dovrebbero essere una lista vuota");
    }

    /**
     * Verifica le mosse possibili quando la riga è = 0 e la colonna è compresa nei limiti.
     */
    @Test
    void testmosseBRigaNulla() {
        ArrayList<Coordinate> mosse = salto.mosseB(0, 'f');
        assertEquals(new ArrayList<>(), mosse, "Le mosse dovrebbero essere una lista vuota");
    }

    /**
     * Verifica le mosse possibili quando la riga e la colonna sono entrambe al di fuori dei limiti.
     */
    @Test
    void testmosseBRigaColonnaSuperioreMassima() {
        ArrayList<Coordinate> mosse = salto.mosseB(Costanti.RIGA_8, 'h');
        assertEquals(new ArrayList<>(), mosse, "Le mosse dovrebbero essere una lista vuota");
    }

    /**
     * Verifica le mosse possibili quando la riga è al di fuori dei limiti invece la colonna è compresa.
     */
    @Test
    void testmosseBRigaSuperioreMassima() {
        ArrayList<Coordinate> mosse = salto.mosseB(Costanti.RIGA_8, 'd');
        assertEquals(new ArrayList<>(), mosse, "Le mosse dovrebbero essere una lista vuota");
    }

    /**
     * Verifica le mosse possibili quando la riga è compresa e la colonna è al di fuori dei limiti.
     */
    @Test
    void testMosseBColonnaSuperioreMassima() {
        ArrayList<Coordinate> mosse = salto.mosseB(Costanti.RIGA_4, 'h');
        assertEquals(new ArrayList<>(), mosse, "Le mosse dovrebbero essere una lista vuota");
    }

    /**
     * Verifica le mosse possibili quando la riga è = MAX_VALUE e la colonna è compresa.
     */
    @Test
    void testMosseBRigaIntMaxValue() {
        ArrayList<Coordinate> mosse = salto.mosseB(Integer.MAX_VALUE, 'd');
        assertTrue(mosse.isEmpty(), "Le mosse dovrebbero essere una lista vuota");
    }

    /**
     * Verifica le mosse possibili quando la colonna è = MAX_VALUE e la riga è compresa.
     */
    @Test
    void testMosseBColonnaIntMaxValue() {
        ArrayList<Coordinate> mosse = salto.mosseB(Costanti.RIGA_4, (char) Integer.MAX_VALUE);
        assertTrue(mosse.isEmpty(), "Le mosse dovrebbero essere una lista vuota");
    }

    /**
     * Verifica le mosse possibili quando la riga è = MIN_VALUE e la colonna è compresa.
     */
    @Test
    void testMosseBRigaIntMinValue() {
        ArrayList<Coordinate> mosse = salto.mosseB(Integer.MIN_VALUE, 'd');
        assertTrue(mosse.isEmpty(), "Le mosse dovrebbero essere una lista vuota");
    }

    /**
     * Verifica le mosse possibili quando la colonna è = MIN_VALUE e la riga è compresa.
     */
    @Test
    void testMossaBColonnaIntMinValue() {
        ArrayList<Coordinate> mosse = salto.mosseB(Costanti.RIGA_4, (char) Integer.MIN_VALUE);
        assertTrue(mosse.isEmpty(), "Le mosse dovrebbero essere una lista vuota");
    }
}
