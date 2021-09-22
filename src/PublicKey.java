/**
 * Die Klasse enthält Informationen von einem Öffentlichen Schlüssel
 */
public class PublicKey {

    private int e;
    private int g;

    /**
     * Der Konstruktor erzeugt einen öffentlichen Schlüssel mit 2 zahlen e und g
     * @param e eine Zahl von einem öffentlichen Schlüssel
     * @param g eine Zahl von einem öffentlichen Schlüssel
     */
    public PublicKey(int e, int g){
        this.e = e;
        this.g = g;
    }

    /**
     * Getter für den Wert e
     * @return e
     */
    public int getE() {
        return e;
    }
    /**
     * Getter für den Wert d
     * @return d
     */
    public int getG() {
        return g;
    }

}
