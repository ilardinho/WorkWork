package workwork;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Comparator;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * @author ilardinho
 *
 */
public class Asiakas implements Cloneable {
    
    private String nimi = "";
    private String katuosoite = "";
    private String postinumero = "";
    private String postiosoite = "";
    private String ynimi = "";
    private String ykatuosoite = "";
    private String ypostinumero = "";
    private String ypostiosoite = "";
    private String puhelin = "";
    private String tyopuhelin = "";
    private String lisatietoja = "";
    private String sahkoposti = "";
    private int asiakasNro = 0;

    private static int seuraavaNro = 1;

    /**
     * Testipääohjelma
     * @param args
     */
    public static void main(String args[]) {
         Asiakas ismo = new Asiakas(), seppo = new Asiakas(), atik = new
         Asiakas();
         atik.identifioi();
         ismo.identifioi();
         seppo.identifioi();
         ismo.tulosta(System.out);
         ismo.vastaaSeppoIsmo();
         ismo.tulosta(System.out);
        
         seppo.vastaaSeppoIsmo();
         seppo.tulosta(System.out);
        
         seppo.vastaaSeppoIsmo();
         seppo.tulosta(System.out);
         atik.tulosta(System.out);
    }
    /**
     * @return asiakastietokenttien lukumäärä
     */
    public int getKenttia() {
        return 12;
    }
    /**
     * @param k
     * @return joo
     */
    public String anna(int k) {
        switch (k) {
        case 0:
            return "" + nimi;
        case 1:
            return "" + katuosoite;
        case 2:
            return "" + postinumero;
        case 3:
            return "" + postiosoite;
        case 4:
            return "" + puhelin;
        case 5:
            return "" + asiakasNro;
        case 6:
            return "" + ynimi;
        case 7:
            return "" + ykatuosoite;
        case 8:
            return "" + ypostinumero;
        case 9:
            return "" + ypostiosoite;
        case 10:
            return "" + tyopuhelin;
        case 11:
            return "" + sahkoposti;
        default:
            return "";
        }
    }

    /**
     * @param k
     * @return kentän kysymys
     */
    public String getKysymys(int k) {
        switch (k) {
        case 0:
            return "Nimi";
        case 1:
            return "Katuosoite";
        case 2:
            return "Postinumero";
        case 3:
            return "Postitoimipaikka";
        case 4:
            return "Puhelin";
        case 5:
            return "Asiakastunnus";
        case 6:
            return "Yritys";
        case 7:
            return "Yrityksen osoite";
        case 8:
            return "Postinumero";
        case 9:
            return "Postitoimipaikka";
        case 10:
            return "Puhelin";
        case 11:
            return "Sähköposti";
        default:
            return "";
        }
    }

    /**
     * @param k
     * @param jono
     * @return jep
     */
    public String aseta(int k, String jono) {
        String apupapu = jono.trim();
        StringBuffer sb = new StringBuffer(apupapu);
        switch (k) {
        case 0:
            nimi = apupapu;
            return null;
        case 1:
            katuosoite = apupapu;
            return null;
        case 2:
            postinumero = apupapu;
            return null;
        case 3:
            postiosoite = apupapu;
            return null;
        case 4:
            puhelin = apupapu;
            return null;
        case 5:
            setAsiakasNro(Mjonot.erota(sb, '§', getAsiakasNro()));
            return null;
        case 6:
            ynimi = apupapu;
            return null;
        case 7:
            ykatuosoite = apupapu;
            return null;
        case 8:
            ypostinumero = apupapu;
            return null;
        case 9:
            ypostiosoite = apupapu;
            return null;
        case 10:
            tyopuhelin = apupapu;
            return null;
        case 11:
            sahkoposti = apupapu;
            return null;
        default:
            return "keijokeke";
        }
    }
    /**
     * @return nummero ensimmäiseen kenttaan
     */
    public int ekaKentta() {
        return 0;
    }

    
    /**
     * Annetaan asiakkaalle asiakastunnus
    * @return atunnus eli asiakkaan identifioiva numero
    */
    public int identifioi() {
        asiakasNro = seuraavaNro;
        seuraavaNro++;
        return asiakasNro;

    }

    

    /**
     * Palauttaa asiakkaan nimen
     * @return asiakkaan nimi
     */
    public String getNimi() {
        return nimi;
    }

    /**
     * palauttaa asiakkaan katuosoitteen
     * @return katuosoite
     */
    public String getKatuosoite() {
        return katuosoite;
    }

    /**
     * Asiakkaan postinumero
     * @return postinumero
     */
    public String getPostinumero() {
        return postinumero;
    }

    /**
     * Palauttaa asiakkaan postiosoitteen
     * @return postiosoite
     */
    public String getPostiosoite() {
        return postiosoite;
    }

    /**
     * Palauttaa asiakkaan puhelinnumeron
     * @return puhelin
     */
    public String getPuhelin() {
        return puhelin;
    }


     /**
     *
     * annetaan jäsenelle arvot testaamista varten
     */
     public void vastaaSeppoIsmo() {
     nimi = "Seppo Ismo ";
     katuosoite = "Metsätie 1";
     postinumero = "12345";
     postiosoite = "Tampere";
     puhelin = "12-12324";
     tyopuhelin = "";
     ykatuosoite = "";
     ypostinumero = "";
     ypostiosoite = "";
     lisatietoja = "";
     }

//    public String toString() {
//        return ""  + nimi  + "| "
//                + katuosoite + "| " + postinumero + "| " + postiosoite + "| "
//                + puhelin + "| " + tyopuhelin + "| " + getAsiakasNro() + "| " + ykatuosoite + "| "
//                + ypostinumero + "| " + ypostiosoite + "| " + lisatietoja;
//    }

    /*
     * asetetaan asiakasnumero
     */
    private void setAsiakasNro(int anro) {
        asiakasNro = anro;
        if (asiakasNro >= seuraavaNro)
            seuraavaNro = asiakasNro + 1;
    }

    /**
     * @return palauttaa asiakkaan numeron
     */
    public int getAsiakasNro(){
        return asiakasNro;
    }
//    /**
//     * @param rivi josta erotetaan asiakkaan tiedot
//     */
//    public void parse(String rivi) {
//        StringBuffer sb = new StringBuffer(rivi);
//        setAsiakasNro(Mjonot.erota(sb, '|', getAsiakasNro()));
//        nimi = Mjonot.erota(sb, '|', nimi);
//        katuosoite = Mjonot.erota(sb, '|', katuosoite);
//        postinumero = Mjonot.erota(sb, '|', postinumero);
//        postiosoite = Mjonot.erota(sb, '|', postiosoite);
//        puhelin = Mjonot.erota(sb, '|', puhelin);
//        tyopuhelin = Mjonot.erota(sb, '|', tyopuhelin);
//        ykatuosoite = Mjonot.erota(sb, '|', ykatuosoite);
//        ypostiosoite = Mjonot.erota(sb, '|', ypostiosoite);
//        ypostinumero = Mjonot.erota(sb, '|', ypostinumero);
//        lisatietoja = Mjonot.erota(sb, '|', lisatietoja);
//    }
 
    /**
     * Tulostetaan asikkaan tiedot
     * @param os tietovirta johon tulostetaan 
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }

    /**
     * Tulostetaan asiakkaan tiedot
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println("Nimi:  " + nimi + " Asiakastunnus:  "
                + asiakasNro);
        out.println("Osoite:  " + katuosoite + " " + postinumero + " "
                + postiosoite);
        if (!ynimi.equals(""))
            out.println("Yritys:  " + ynimi);
        if (!ykatuosoite.equals(""))
            out.println("Yosoite:  " + ykatuosoite + " " + ypostinumero + " "
                    + ypostiosoite);
        out.println("Puhelin:  " + puhelin + " " + tyopuhelin);
        if (!lisatietoja.equals(""))
            out.println(lisatietoja);

    }
    /**
     * @param asiakas
     * @return true jos sama
     */
    public boolean equals(Asiakas asiakas){
        for(int apu = 0; apu < getKenttia(); apu++)
            if (!anna(apu).equals(asiakas.anna(apu))) return false;
        return true;
    }
    
    public Asiakas clone() throws CloneNotSupportedException{
        Asiakas uusi;
        uusi = (Asiakas)super.clone();
        return uusi;
    }

    /**
     * @param rivi josta erotetaan asiakkaan tiedot
     */
    public void parse(String rivi) {
        StringBuffer sb = new StringBuffer(rivi);
        for (int k = 0; k < getKenttia(); k++)
            aseta(k, Mjonot.erota(sb, '|'));
    }
    public String toString() {
        StringBuffer sb = new StringBuffer("");
        String erotin = "";
        for (int k = 0; k < getKenttia(); k++) {
            sb.append(erotin);
            sb.append(anna(k));
            erotin = "|";
        }
        return sb.toString();
    }

}
