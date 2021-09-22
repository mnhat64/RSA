import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Die Klasse erzeugt die Operation von RSA (Verschlüsselung und Entschlüsselung)
 */
public class RSA  {
    /**
     * Gibt auf der Console aus, welche Operation zur Verfügung stehen
     */
    public void option(){
        boolean stop = false;
        while(!stop){
            System.out.println("Please choose a function:");
            System.out.println("1 - Key Generator");
            System.out.println("2 - Encrypt E-Mail");
            System.out.println("3 - Decrypt E-Mail");

            boolean validInput = false;
            int numberInput = 0;

            Scanner sc = new Scanner(System.in);
            /**
             * Prüft die Gültigkeit der Eingabe von Nutzer. Die Zahl sollte von 1 bis 3 sein.
             */
            while (!validInput) {
                try {
                    numberInput = Integer.parseInt(sc.nextLine());
                    if (numberInput < 1 || numberInput > 3) {
                        System.out.println("Please choose again, the number should be either 1, 2 or 3:");
                    } else validInput = true;
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a number");
                }
            }

            if (numberInput == 1) {
                keyGenerator();
            } else if (numberInput == 2) {
                encrypt();
            } else if (numberInput == 3) {
                decrypt();
            }

            System.out.println("Do you want to continue? (y/n)");
            String input = sc.nextLine();
            if (input == "n"){
                stop = true;
            } else stop = false;

        }
    }

    /**
     * Die Funktion prüft, ob eine Zahl prim ist
     * @param n die Zahl, die überprüft werden soll
     * @return
     */

    private boolean primeCheck (int n){
        if (n == 0 || n == 1){
            return false;
        } else if (n == 2){
            return true;
        } else {
            for (int i = 2; i < Math.sqrt(n); i++){
                if (n % i == 0){
                    return false;
                }
            }
        } return true;
    }


    /**
     * Die Funktion nimmt die von Nutzer definierten Werte von p und q und prüft die Gültigkeit der Eingabe mittels
     * primeCheck, hier werden die restlichen Werte (d, g und e) berechnet, wenn ein Objekt von der Klasse KeySet erzeugt wird
     */
    private void keyGenerator(){
        Scanner sc = new Scanner(System.in);

        System.out.println("The Key Generator:");

        System.out.println("Please enter a prime number for p: ");
        int p = sc.nextInt();
        while(!primeCheck(p)){
            System.out.println("p should be a prime number");
            p = sc.nextInt();
        }

        System.out.println("Please enter a prime number for q: ");
        int q = sc.nextInt();
        while(!primeCheck(q)){
            System.out.println("q should be a prime number");
            q = sc.nextInt();
        }

        KeySet keySet = new KeySet(p,q);
        System.out.println(keySet.toString());
    }

    /**
     * Die Funktion ist verantwortlich für die Verschlüsselung
     */
    private void encrypt(){

        /**
         * Datenpfad wird von dem Nutzer eingegeben und die Funktion überprüft, ob ein Datei von solchem Link existiert.
         * Die Werte von e und g werden von Nutzer eingegeben, die kann man im KeyGenerator berechen lassen.
         */
        Scanner sc = new Scanner(System.in);

        System.out.println("The E-Mail Encryptor:");
        System.out.println("Please enter the path to the file you want to encrypt:");

        String path = sc.nextLine();
        while(!new File(path).isFile()){
            System.out.println("The file could not be found, please try again");
            path = sc.nextLine();
        }

        System.out.println("Please enter the value e of your public key values:");
        int e = sc.nextInt();

        System.out.println("Enter the value g of your public key values:");
        int g = sc.nextInt();

        PublicKey publicKey = new PublicKey(e,g);

        try{
            File email = new File(path);
            File result = new File("encrypted.txt");
            try{
                result.createNewFile();
            } catch (IOException error){
                System.out.println("Error by creating new file");
            }

            /**
             * FileInputStream liest den Data als ein Stream von Bits
             */

            FileInputStream inputStream = new FileInputStream(email);

            /**
             * InputStreamReade interpretiert die eingelesene Daten als ein Stream von String/Char
             */
            InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.US_ASCII);
            /**
             * BufferedReader soll man aussuchen, wenn die Anzahl der zu lesende Charakter groß ist.
             */
            BufferedReader bufferedReader = new BufferedReader(streamReader);

            FileWriter fileWriter = new FileWriter(result);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
           int i = 0;
            while( ( i = bufferedReader.read()) != -1 ){
                /**
                 * Hier wird die Formel von der Aufgabestellung verwendet und die berechneten Werte zu Charakter umwandeln und in dem Ausgabefile
                 * aufgeschrieben.
                 */
                long a = (long) ((Math.pow(i,e)) % g);

                bufferedWriter.write(String.valueOf(a));
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            bufferedReader.close();
        } catch(FileNotFoundException error2){
            System.out.println("Can not find the file");
        } catch (IOException error3){
            System.out.println("Can not read or write the file");
        }
    }

    /**
     * Die Funktion ist verantwortlich für die Entschlüsselung
     */
    private void decrypt(){
        Scanner sc = new Scanner(System.in);

        /**
         * Datenpfad wird von dem Nutzer eingegeben und die Funktion überprüft, ob ein Datei von solchem Link existiert.
         * Die Werte von d und g werden von Nutzer eingegeben, die kann man im KeyGenerator berechen lassen.
         */

        System.out.println("E-Mail Decryptor:");
        System.out.println("Please choose a file to decrypt:");

        String path = sc.nextLine();
        while(!new File(path).isFile()){
            System.out.println("The file could not be found, please try again");
            path = sc.nextLine();
        }

        System.out.println("Please enter the d value of your private key");
        int d = sc.nextInt();

        System.out.println("Please enter the g value of your private key");
        int g = sc.nextInt();

        PrivateKey privateKey = new PrivateKey(d,g);

        try{
            File entcryptedFile = new File(path);
            File result = new File("decrypted.txt");

            try{
                result.createNewFile();
            } catch (IOException e){
                System.out.println("Can not create file");
            }


            FileReader fileReader = new FileReader (entcryptedFile );
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            FileOutputStream outputStream = new FileOutputStream(result);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.US_ASCII);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

            String i;
            while((i = bufferedReader.readLine()) != null){


                BigInteger i1 = BigInteger.valueOf(Integer.parseInt(i));
                BigInteger d1 = BigInteger.valueOf(d);
                BigInteger g1 = BigInteger.valueOf(g);


                BigInteger a = i1.modPow(d1, g1);


                bufferedWriter.write((char)a.intValue());

            }
            bufferedReader.close();
            bufferedWriter.close();
        } catch (FileNotFoundException e){
            System.out.println("File could not be found");
        } catch (IOException e){
            System.out.println("Can not read/write file");
        }
    }
}
