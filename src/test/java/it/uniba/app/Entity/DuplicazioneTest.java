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
 * Test suite per la classe Duplicazione, che verifica il calcolo delle mosse possibili.
 */
class DuplicazioneTest {

    private static Duplicazione duplicazione;
    private static Mossa mossa;
    private static Pedina[][] scacchiera;

    @BeforeAll
    static void setup() {
        scacchiera = new Pedina[Costanti.RIGA_7][Costanti.RIGA_7];
        mossa = new Mossa(scacchiera, duplicazione, null);
        duplicazione = new Duplicazione(mossa);
    }

    @Test
    @DisplayName("Test per valori compresi")
    void testmosseARigaColonnaCompresa() {
        ArrayList<Coordinate> mosse = duplicazione.mosseA(Costanti.RIGA_4, 'd');
        assertNotNull(mosse, "Le mosse non dovrebbero essere null");
    }

    @Test
    @DisplayName("Test per valori minimi")
    void testmosseARigaColonnaMinima() {
        ArrayList<Coordinate> mosse = duplicazione.mosseA(1, 'a');
        assertNotNull(mosse, "Le mosse non dovrebbero essere null");
    }

    @Test
    @DisplayName("Test per valori massimi")
    void testmosseARigaColonnaMassima() {
        ArrayList<Coordinate> mosse = duplicazione.mosseA(Costanti.RIGA_7, 'g');
        assertNotNull(mosse, "Le mosse non dovrebbero essere null");
    }

    @Test
    @DisplayName("Test per riga massima e colonna minima")
    void testmosseARigaMassima() {
        ArrayList<Coordinate> mosse = duplicazione.mosseA(Costanti.RIGA_7, 'a');
        assertNotNull(mosse, "Le mosse non dovrebbero essere null");
    }

    @Test
    @DisplayName("Test per riga minima e colonna massima")
    void testmosseAColonnaMassima() {
        ArrayList<Coordinate> mosse = duplicazione.mosseA(Costanti.RIGA_1, 'g');
        assertNotNull(mosse, "Le mosse non dovrebbero essere null");
    }

    @Test
    @DisplayName("Test per valori fuori dai limiti")
    void testmosseARigaColonnaNonAppartenenti() {
        ArrayList<Coordinate> mosse = duplicazione.mosseA(0, 'h');
        assertEquals(new ArrayList<>(), mosse, "Le mosse dovrebbero essere una lista vuota");
    }

    @Test
    @DisplayName("Test per riga zero e colonna valida")
    void testmosseARigaNulla() {
        ArrayList<Coordinate> mosse = duplicazione.mosseA(0, 'f');
        assertEquals(new ArrayList<>(), mosse, "Le mosse dovrebbero essere una lista vuota");
    }

    @Test
    @DisplayName("Test per riga e colonna superiori ai limiti")
    void testmosseARigaColonnaSuperioreMassima() {
        ArrayList<Coordinate> mosse = duplicazione.mosseA(Costanti.RIGA_8, 'h');
        assertEquals(new ArrayList<>(), mosse, "Le mosse dovrebbero essere una lista vuota");
    }

    @Test
    @DisplayName("Test per riga superiore ai limiti e colonna valida")
    void testmosseARigaSuperioreMassima() {
        ArrayList<Coordinate> mosse = duplicazione.mosseA(Costanti.RIGA_8, 'd');
        assertEquals(new ArrayList<>(), mosse, "Le mosse dovrebbero essere una lista vuota");
    }

    @Test
    @DisplayName("Test per riga valida e colonna superiore ai limiti")
    void testMosseAColonnaSuperioreMassima() {
        ArrayList<Coordinate> mosse = duplicazione.mosseA(Costanti.RIGA_4, 'h');
        assertEquals(new ArrayList<>(), mosse, "Le mosse dovrebbero essere una lista vuota");
    }

    @Test
    @DisplayName("Test per riga con valore massimo di Integer e colonna valida")
    void testMosseARigaIntMaxValue() {
        ArrayList<Coordinate> mosse = duplicazione.mosseA(Integer.MAX_VALUE, 'd');
        assertTrue(mosse.isEmpty(), "Le mosse dovrebbero essere una lista vuota");
    }

    @Test
    @DisplayName("Test per colonna con valore massimo di Integer e riga valida")
    void testMosseAColonnaIntMaxValue() {
        ArrayList<Coordinate> mosse = duplicazione.mosseA(Costanti.RIGA_4, (char) Integer.MAX_VALUE);
        assertTrue(mosse.isEmpty(), "Le mosse dovrebbero essere una lista vuota");
    }

    @Test
    @DisplayName("Test per riga con valore minimo di Integer e colonna valida")
    void testMosseARigaIntMinValue() {
        ArrayList<Coordinate> mosse = duplicazione.mosseA(Integer.MIN_VALUE, 'd');
        assertTrue(mosse.isEmpty(), "Le mosse dovrebbero essere una lista vuota");
    }

    @Test
    @DisplayName("Test per colonna con valore minimo di Integer e riga valida")
    void testMosseAColonnaIntMinValue() {
        ArrayList<Coordinate> mosse = duplicazione.mosseA(Costanti.RIGA_4, (char) Integer.MIN_VALUE);
        assertTrue(mosse.isEmpty(), "Le mosse dovrebbero essere una lista vuota");
    }
}
