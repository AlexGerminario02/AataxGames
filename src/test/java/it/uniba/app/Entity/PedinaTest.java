package it.uniba.app.Entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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

    /**
     * Test per il metodo equals.
     */
    @Test
    void testEquals() {
        Pedina samePedina = new Pedina("X", coord1);
        assertEquals(pedina1, samePedina, "Pedina1 should be equal to"
        + "another Pedina with the same attributes");  // Verifica che due pedine con gli stessi attributi sono uguali
    }

    /**
     * Test per il metodo equals con oggetti diversi.
     */
    @Test
    void testNotEquals() {
        assertNotEquals(pedina1, pedina2, "Pedina1 should not be equal"
       + " to Pedina2 with different attributes");  // Verifica che due pedine con attributi diversi non sono uguali
    }

    /**
     * Test per il metodo hashCode.
     */
    @Test
    void testHashCode() {
        Pedina samePedina = new Pedina("X", coord1);
        assertEquals(pedina1.hashCode(), samePedina.hashCode(), "HashCode should be"
        + "the same for Pedina with same attributes");  // Verifica che il hashCode sia lo stesso per oggetti uguali
    }

    /**
     * Test per il metodo toString.
     */
    @Test
    void testToString() {
        String expected = "Pedina{carattere=X, coordinate=(b  1 )}";
        assertEquals(expected, pedina1.toString(), "toString should return the expected string representation");
    }
}
