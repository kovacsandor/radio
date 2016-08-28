/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package radio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Kovács Andor
 */
public class Radio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here

//        1. Olvassa be és tárolja a veetel.txt fájl tartalmát! 
        BufferedReader bufferedReader = new BufferedReader(new FileReader("veetel.txt"));
        ArrayList<String> veetel = new ArrayList<>();
        String sor; // Miért kell beletenni változóba?

        while ((sor = bufferedReader.readLine()) != null) {
            veetel.add(sor);
        }

        ArrayList<Rogzites> rogzitesek = new ArrayList<>();
        int nap = 0;
        int radioAmator = 0;

        for (int i = 0; i < veetel.size(); i++) {

            if (veetel.get(i).length() < 6) {
                if (veetel.get(i).indexOf(" ") < 2) {
                    nap = Integer.parseInt(veetel.get(i).substring(0, 1));
                    radioAmator = Integer.parseInt(veetel.get(i).substring(2, veetel.get(i).length()));
                } else {
                    nap = Integer.parseInt(veetel.get(i).substring(0, 2));
                    radioAmator = Integer.parseInt(veetel.get(i).substring(3, veetel.get(i).length()));
                }
            } else {
                String uzenet = veetel.get(i);
                rogzitesek.add(new Rogzites(nap, radioAmator, uzenet));
            }
        }

//        2. Írja a képernyőre, hogy melyik rádióamatőr rögzítette az állományban szereplő első és melyik az utolsó üzenetet!
        System.out.println("2. feladat:");
        System.out.println("Az első üzenet rögzítője: " + rogzitesek.get(0).radioAmator);
        System.out.println("Az utolsó üzenet rögzítője: " + rogzitesek.get(rogzitesek.size() - 1).radioAmator);

//        3. Adja meg az összes olyan feljegyzés napját és a rádióamatőr sorszámát, amelynek szövegében a „farkas” karaktersorozat szerepel!
        System.out.println("3. feladat:");
        for (int i = 0; i < rogzitesek.size(); i++) {
            if (rogzitesek.get(i).uzenet.contains("farkas")) {
                System.out.println(rogzitesek.get(i).nap + " nap, " + rogzitesek.get(i).radioAmator + " rádióamatőr");
            }
        }

//        4. Készítsen statisztikát, amely megadja, hogy melyik napon hány rádióamatőr készített feljegyzést. Azok a napok 0 értékkel szerepeljenek, amikor nem született feljegyzés! Az eredmény a képernyőn jelenjen meg a napok sorszáma szerint növekvően! A megjelenítést a feladat végén látható minta szerint alakítsa ki! 
        ArrayList<Rogzites> rogzitesekNapokSzerint = new ArrayList<>();
        nap = 1;
        int radioAmatorNaponta = 0;

        System.out.println("4. feladat:");
        for (int i = 0; i < rogzitesek.size(); i++) {
            if (nap > 11) {
                break;
            }
            if (rogzitesek.get(i).nap == nap) {
                rogzitesekNapokSzerint.add(new Rogzites(rogzitesek.get(i).nap, rogzitesek.get(i).radioAmator, rogzitesek.get(i).uzenet));
                radioAmatorNaponta++;
            }
            if (i == rogzitesek.size() - 1) {
                System.out.println(nap + ". nap: " + radioAmatorNaponta + " rádióamatőr");
                nap++;
                radioAmatorNaponta = 0;
                i = 0;
            }
        }


//        5. A rögzített üzenetek alapján kísérelje meg helyreállítani az expedíció által küldött üzenetet! Készítse el az adaas.txt fájlt, amely napok szerinti sorrendben tartalmazza a küldött üzeneteket! Ha egy időpontban senkinél nem volt vétel, akkor azon a ponton a # jel szerepeljen! (Feltételezheti, hogy az azonos üzenethez tartozó feljegyzések között nincs ellentmondás.)
//        6. Készítsen függvényt szame néven az alábbi algoritmus alapján! A függvény egy karaktersorozathoz hozzárendeli az igaz vagy a hamis értéket. A függvény elkészítésekor az algoritmusban megadott változóneveket használja! Az elkészített függvényt a következő feladat megoldásánál felhasználhatja. 
//        7. Olvassa be egy nap és egy rádióamatőr sorszámát, majd írja a képernyőre a megfigyelt egyedek számát (a kifejlett és kölyök egyedek számának összegét)! Ha nem volt ilyen feljegyzés, a „Nincs ilyen feljegyzés” szöveget jelenítse meg! Ha nem volt megfigyelt egyed vagy számuk nem állapítható meg, a „Nincs információ” szöveget jelenítse meg! Amennyiben egy számot közvetlenül # jel követ, akkor a számot tekintse nem megállapíthatónak! 
//        for (int i = 0; i < rogzitesek.size(); i++) {
//            System.out.println(rogzitesek.get(i).nap + ". nap, " + rogzitesek.get(i).radioAmator + " sz. rádióamatőr \n" + "Üzenet: " + rogzitesek.get(i).uzenet);
//        }
    }

    private static class Rogzites {

        int nap;
        int radioAmator;
        String uzenet;

        public Rogzites(int nap, int radioAmator, String uzenet) {
            this.nap = nap;
            this.radioAmator = radioAmator;
            this.uzenet = uzenet;
        }
    }

}
