package it.uniba.app.Entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test per la classe Giocatore.
 */
class GiocatoreTest {

    private Pedina pedina;
    private Giocatore giocatore;

    /**
     * Inizializza le variabili comuni a tutti i test.
     */
    @BeforeEach
    void setUp() {
        // Crea un oggetto Coordinate valido (ad esempio, (0,0)).
        Coordinate coordinate = new Coordinate(0, 0);
        pedina = new Pedina("X", coordinate);
        giocatore = new Giocatore(pedina, "Mario");
    }

    /**
     * Test per il costruttore con parametri.
     */
    @Test
    void testCostruttoreConParametri() {
        assertEquals("Mario", giocatore.getNome(), "Nome del giocatore dovrebbe essere 'Mario'");
    }

    /**
     * Test per il costruttore di copia.
     */
    @Test
    void testCostruttoreDiCopia() {
        Giocatore giocatoreCopia = new Giocatore(giocatore);
        assertEquals("Mario", giocatoreCopia.getNome(), "Nome del giocatore copiato dovrebbe essere 'Mario'");
    }

}
