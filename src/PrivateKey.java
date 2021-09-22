/**
 * Die Klasse enthält Informationen von einem privaten Schlüssel
 */

public class PrivateKey {
    private int d;
    private int g;

    /**
     * Der Konstruktor erzeugt einen privaten Schlüssel mit 2 zahlen d und g
     *
     * @param d eine Zahl von einem privaten Schlüssel
     * @param g eine Zahl von einem privaten Schlüssel
     */
    public PrivateKey(int d, int g) {
        this.d = d;
        this.g = g;
    }

    /**
     * Getter für den Wert d
     *
     * @return d
     */
    public int getD() {
        return d;
    }

    /**
     * Getter für den Wert g
     *
     * @return g
     */
    public int getG() {
        return g;
    }
}