package it.uniba.app.Entity;

/**
 * <<Entity>>
 * Questa classe rappresenta un giocatore nel gioco Ataxx.
 * Un giocatore è caratterizzato da una pedina e un nome.
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
     * @param pedinat la pedina associata al giocatore
     * @param nomet il nome del giocatore
     */
    public Giocatore(final Pedina pedinat, final String nomet) {
        this.pedina = pedinat;
        this.nome = nomet;
    }

    // Metodi getter e setter

    /**
     * Restituisce la pedina associata al giocatore.
     *
     * @return la pedina associata al giocatore
     */
    public Pedina getPedina() {
        return pedina;
    }

    /**
     * Imposta la pedina associata al giocatore.
     *
     * @param pedinat la pedina da associare al giocatore
     */
    public void setPedina(final Pedina pedinat) {
        this.pedina = pedinat;
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
