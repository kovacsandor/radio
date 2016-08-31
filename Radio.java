/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package radio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
        for (int i = 0; i < rogzitesekNapokSzerint.size(); i++) {
            System.out.println(rogzitesekNapokSzerint.get(i).uzenet);
        }
        System.out.println("5. feladat:");

        PrintWriter adaas = new PrintWriter(new FileWriter("adaas.txt"));
        int nedikNap = 1;
        int nedikKarakter = 0;
        boolean karakterBeallitva = false;

        for (int i = 0; i < rogzitesekNapokSzerint.size(); i++) {
            if (nedikNap > 12) {
                break;
            }
            if (nedikKarakter == 90) {
                i = 0;
                nedikKarakter = 0;
                nedikNap++;
                adaas.println("");
                continue;
            }

            if (rogzitesekNapokSzerint.get(i).nap == nedikNap) {
                String karakter = String.valueOf(rogzitesekNapokSzerint.get(i).uzenet.charAt(nedikKarakter));
                if (!karakter.equals("#")) {
                    adaas.print(karakter);
                    karakterBeallitva = true;
                    nedikKarakter++;
                    continue;
                }
            }
            if (rogzitesekNapokSzerint.get(i).nap > nedikNap) {
                if (!karakterBeallitva) {
                    adaas.print("#");
                    
                }
                karakterBeallitva = false;
                i = 0;
//                nedikKarakter++;
                
            }
        }

        adaas.close();
        
        
//        6. Készítsen függvényt szame néven az alábbi algoritmus alapján! A függvény egy karaktersorozathoz hozzárendeli az igaz vagy a hamis értéket. A függvény elkészítésekor az algoritmusban megadott változóneveket használja! Az elkészített függvényt a következő feladat megoldásánál felhasználhatja. 
        
        
//        7. Olvassa be egy nap és egy rádióamatőr sorszámát, majd írja a képernyőre a megfigyelt egyedek számát (a kifejlett és kölyök egyedek számának összegét)! Ha nem volt ilyen feljegyzés, a „Nincs ilyen feljegyzés” szöveget jelenítse meg! Ha nem volt megfigyelt egyed vagy számuk nem állapítható meg, a „Nincs információ” szöveget jelenítse meg! Amennyiben egy számot közvetlenül # jel követ, akkor a számot tekintse nem megállapíthatónak! 
        System.out.println("7. feladat:");
        int egyNapSorszama = Integer.parseInt(getInput("Adja meg a nap sorszámát! "));
        int egyRadioAmatorSorszama = Integer.parseInt(getInput("Adja meg a rádióamatőr sorszámát! "));
//        char egyNapSorszama = getInput("Adja meg a nap sorszámát! ").charAt(0);
//        char egyRadioAmatorSorszama = getInput("Adja meg a nap sorszámát! ").charAt(0);
        String szo = "";
        int megfigyeltEgyedekSzama = 0;
        for (int i = 0; i < rogzitesek.size(); i++) {
            if (rogzitesek.get(i).nap == egyNapSorszama && rogzitesek.get(i).radioAmator == egyRadioAmatorSorszama) {
                szo = rogzitesek.get(i).uzenet.substring(0, 5);
            }
        }
        System.out.println("szo = " + szo);

        String kar1 = "";
        String kar2 = "";
        boolean elsoBeallitva = false;
        for (int i = 0; i < szo.length(); i++) {
            
            if (szo.charAt(i) == ' ' ) {
                System.out.println(kar1 + " + " + kar2);
                break;
            } else if (szo.charAt(i) == '#' ) {
                System.out.println("Nem megállapítható");
                break;
            } else if (szo.charAt(i) == '/') {
                elsoBeallitva = true;
            } else if (szo.charAt(i)>'0' || szo.charAt(i)<'9') {
                if(elsoBeallitva) {
                    kar2 += szo.charAt(i);
                } else {
                    kar1 += szo.charAt(i);
                }
            } 
        }
               

//        szame(uzenet);
        
//        for (int i = 0; i < rogzitesek.size(); i++) {
//            System.out.println(rogzitesek.get(i).nap + ". nap, " + rogzitesek.get(i).radioAmator + " sz. rádióamatőr \n" + "Üzenet: " + rogzitesek.get(i).uzenet);
//        }
    }

    private static boolean szame(String szo) {
        boolean valasz = true;
        for (int i = 0; i < szo.length(); i++) {
            if (szo.charAt(i)<'0' || szo.charAt(i)>'9') {
                valasz = false;
            }
        }
        return valasz;
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

    private static String getInput(String prompt) {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

        System.out.print(prompt);
        System.out.flush();

        try {
            return stdin.readLine();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
