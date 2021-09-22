/**
 * Die Klasse KeySet erzeugt ein Schlüsselpaar
 */
public class KeySet {

    private PublicKey publicKey;
    private PrivateKey privateKey;

    /**
     * Hier wird die Werte von d, g und e gleich berechnet
     * @param p eine Primzahl, die von Nutzer definiert
     * @param q eine Primzahl, die von Nutzer definiert
     */
    public KeySet(int p, int q){
        int g = p*q;
        int phi = (p-1)*(q-1);
        int e = 0;
        int d = 0;
        /**
         * Hier wird e berechnet, so dass e und phi hat einen ggt vom 1
         */
        for (e = 2; e < phi; e++) {
            if (gcd(e, phi) == 1)
                break;
            }
        /**
         * Hier wird d berechnet, so dass (d*e) mod (phi) = 1
         */
        for (int i = 0; i <= 9; i++){
            int x = 1 + (i * phi);

            if (x % e == 0){
                d = x/e;
                break;
            }
        }

        this.publicKey = new PublicKey(e,g);
        this.privateKey = new PrivateKey(d,g);
        }

    /**
     * Die funktion berechnet den GGT von 2 Zahlen
     * @param n1 eine natürliche Zahl
     * @param n2 eine natürliche Zahl
     * @return
     */
    static int gcd(int n1, int n2){
            if (n1 == 0){
                return n2;
            } else return gcd(n2 % n1, n1);
    }

    /**
     * Getter für einen öffentlichen Schlüssel
     * @return der offentliche Schlüssel
     */
    public PublicKey getPublicKey(){
        return publicKey;
    }

    /**
     * Getter für einen privaten Schlüssel
     * @return der private Schlüüsekl
     */

    public PrivateKey getPrivateKey(){
        return privateKey;
    }

    /**
     * Die Funktion gibt die Werte von Zahlen von einem Schlüsselpaar auf der Console zurück
     * @return ein String mit den Werten von d, g und e.
     */
    @Override public String toString(){
        return "Keyset: Public Key: {" + String.valueOf(getPublicKey().getE()) + ", " + String.valueOf(getPublicKey().getG())  + "} || Private Key: {"
                + String.valueOf(getPrivateKey().getD()) + ", " + String.valueOf(getPrivateKey().getG()) +"}";
    }
}
