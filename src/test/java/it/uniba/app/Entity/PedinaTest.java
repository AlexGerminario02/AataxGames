package it.uniba.app.Entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test per la classe Pedina.
 */
 class PedinaTest {

    private Pedina pedina1;
    private Pedina pedina2;
    private Coordinate coord1;
    private Coordinate coord2;

    /**
     * Inizializza le variabili comuni a tutti i test.
     */
    @BeforeEach
    void setUp() {
        coord1 = new Coordinate(1, 1);
        coord2 = new Coordinate(2, 2);
        pedina1 = new Pedina("X", coord1);
        pedina2 = new Pedina("O", coord2);
    }

    /**
     * Test per il costruttore e i metodi getter.
     */
    @Test
    void testCostruttore() {
        assertNotNull(pedina1, "Pedina1 should not be null");  // Verifica che l'oggetto pedina sia stato creato
    }

    /**
     * Test per il costruttore di copia.
     */
    @Test
    void testCostruttoreDiCopia() {
        Pedina copiaPedina = new Pedina(pedina1);
        assertEquals(pedina1, copiaPedina, "Copied Pedina should be equal to the original");
    }

    /**
     * Test per i metodi getter.
     */
    @Test
    void testGetCarattere() {
        assertEquals("X", pedina1.getCarattere(), "Character should be 'X'");
    }

    /**
     * Test per i metodi setter.
     */
    @Test
    void testSetCarattere() {
        pedina1.setCarattere("Y");
        assertEquals("Y", pedina1.getCarattere(), "Character should be 'Y' after setCarattere");
    }

    /**
     * Test per i metodi getter.
     */
    @Test
    void testGetCoordinate() {
        Coordinate expected = new Coordinate(1, 1);
        assertEquals(expected, pedina1.getCoordinate(), "Coordinates should be (1, 1)");
    }

    /**
     * Test per i metodi setter.
     */
    @Test
    void testSetCoordinate() {
        pedina1.setCoordinate(coord2);
        assertEquals(coord2, pedina1.getCoordinate(), "Coordinates should be"
        + "(2, 2) after setCoordinate");  // Verifica che il setCoordinate cambia le coordinate correttamente
    }
}
