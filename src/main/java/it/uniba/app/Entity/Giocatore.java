package it.uniba.app.Entity;

/**
 * <<Entity>>
 * Questa classe rappresenta un giocatore nel gioco Ataxx.
 * Un giocatore è caratterizzato da una pedina e un nome.
 *  
 */
public class Giocatore {
    // Attributi del giocatore

    /** La pedina associata al giocatore. */
    private Pedina pedina;

    /** Il nome del giocatore. */
    private String nome;

    /**
     * Costruisce un nuovo giocatore con la pedina e il nome specificati.
     *
     * @param copia la pedina associata al giocatore
     * @param nomet il nome del giocatore
     */
    public Giocatore(final Pedina copia, final String nomet) {
        this.pedina = new Pedina(copia); // Usa il costruttore di copia per creare una nuova istanza di Pedina
        this.nome = nomet;
    }


 /**
  * Costruttore di copia della classe Giocatore.
  * @param copia
  */
    public Giocatore(final Giocatore copia) {
        this.pedina = new Pedina(copia.getPedina()); // Usa i Pedina per creare una nuova istanza di Pedina
        this.nome = copia.nome;
    }


    /**
     * Restituisce la pedina associata al giocatore.
     *
     * @return la pedina associata al giocatore
     */
    public Pedina getPedina() {
        return new Pedina(pedina);
    }

    /**
     * Imposta la pedina associata al giocatore.
     *
     * @param copia la pedina da associare al giocatore
     */
    public void setPedina(final Pedina copia) {
        this.pedina = new Pedina(copia);
    }

    /**
     * Restituisce il nome del giocatore.
     *
     * @return il nome del giocatore
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il nome del giocatore.
     *
     * @param nomet il nome da assegnare al giocatore
     */
    public void setNome(final String nomet) {
        this.nome = nomet;
    }
}
