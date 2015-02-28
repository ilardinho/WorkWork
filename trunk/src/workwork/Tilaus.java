package workwork;

import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JOptionPane;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * @author ilardinho
 *
 */
public class Tilaus implements Cloneable {
    private int asiakasNro = 0;
    private String tyoOsoite = "";
    private String tyoKuvaus = "";
    private int hinta = 0;
    private String laskutettu = "";
    private String maksettu = "";
    private String lisatiedot = "";
    private int saldo = 0;
    private static int seuraavaNro = 1;
    private int tilausnumero;
    private int seuraavaTilausnumero;
    private String pvm = "";
    private String erapva = "";

    /**
     * Testiohjelma tilaukselle.
     * @param args ei k�yt�ss�
     */
    public static void main(String[] args) {
        Tilaus tilaus = new Tilaus();
        tilaus.vastaaMaalaus(2);
        tilaus.tulosta(System.out);
    }

    /**
     * @param nro
     */
    public void vastaaMaalaus(int nro) {
        asiakasNro = nro;
        hinta = 1000000000;
        tyoOsoite = "Mets�tie 1 12345 Tampere ";
        tyoKuvaus = "Maalaus";
        maksettu = "ei";
        laskutettu = "ei";
        saldo = -2;
        lisatiedot = "kylm� keli";
    }

    /**
     * @param tilausrivi
     */
    public void parse(String tilausrivi) {
        StringBuffer sb = new StringBuffer(tilausrivi);
        for (int k = 0; k < getKenttia(); k++)
            aseta(k, Mjonot.erota(sb, '|'));
        // setTilausnumero(Mjonot.erota(sb, '|', getTilausnumero()));
        // asiakasNro = Mjonot.erota(sb, '|', asiakasNro);
        // tyoOsoite = Mjonot.erota(sb, '|', tyoOsoite);
        // tyoKuvaus = Mjonot.erota(sb, '|', tyoKuvaus);
        // hinta = Mjonot.erota(sb, '|', hinta);
        // laskutettu = Mjonot.erota(sb, '|', laskutettu);
        // maksettu = Mjonot.erota(sb, '|', maksettu);
        // lisatiedot = Mjonot.erota(sb, '|', lisatiedot);
        // saldo = Mjonot.erota(sb, '|', saldo);;
    }

    private void setTilausnumero(int nro) {
        tilausnumero = nro;
        if (tilausnumero >= seuraavaTilausnumero)
            seuraavaTilausnumero = tilausnumero + 1;
    }

    /**
     * Alustetaan Tilaus.
     */
    public Tilaus() {
        // Viel� ei tarvita mit��n
    }

    /**
     * identifioidaan tilaus :)
     * @return tilaustunnus
     */
    public int identifioi() {
        tilausnumero = seuraavaNro;
        seuraavaNro++;
        return tilausnumero;

    }

    /**
     * @return tilaustunnus
     */
    public int getTilausnumero() {
        return tilausnumero;
    }

    /**
     * tulostetaan tilaus
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        JOptionPane.showMessageDialog(null, "Tilaus tulostukseen tultiin");
        tulosta(new PrintStream(os));
    }

    /**
     * alustetaan tilaus j�senelle
     * @param hermanni 
     */
    public Tilaus(int hermanni) {
        this.asiakasNro = hermanni;
    }

    /**
     * @return asiakastunnus
     * 
     */
    public int getAsiakastunnus() {
        return asiakasNro;

    }

    /**
     *  tulostetaan tilaus
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(tilausnumero + " " + tyoKuvaus + " " + tyoOsoite);
        // out.println(hinta + " " + laskutettu + " " + maksettu + " " + saldo);
        // out.println(lisatiedot);
    }

    /**
     * @return palauttaa tilauskenttien m��r�n
     */
    public int getKenttia() {
        return 9;
    }

    /**
     * @param k kent�n numero
     * @param s merkkijono
     * @return nullia tai sitten virhe!
     */
    public String aseta(int k, String s) {
        String st = s.trim();
        StringBuffer sb = new StringBuffer(st);
        switch (k) {
        case 0:
            setTilausnumero(Mjonot.erota(sb, '$', getTilausnumero()));
            return null;
        case 1:
            asiakasNro = Mjonot.erota(sb, '$', asiakasNro);
            return null;
        case 2:
            pvm = st;
            return null;
        case 3:
            tyoOsoite = st;
            return null;
        case 4:
            tyoKuvaus = st;
            return null;
        case 5:
            lisatiedot = st;
            return null;
        case 6:
            try {
                hinta = Mjonot.erotaEx(sb, '�', hinta);
            } catch (NumberFormatException ex) {
                return "ei v�rki hinta" + ex.getMessage();
            }
            return null;
        case 7:
            laskutettu = st;
            return null;
        case 8:
            maksettu = st;
            return null;
        case 9:
            erapva = st;
            return null;
        default:
            return "�sh";
        }

    }

    public String toString() {
        // return "" + getTilausnumero() + "|" + asiakasNro + "|" + tyoOsoite
        // + "|" + tyoKuvaus + "|" + lisatiedot;
        StringBuffer sb = new StringBuffer("");
        String erotin = "";
        for (int k = 0; k < getKenttia(); k++) {
            sb.append(erotin);
            sb.append(anna(k));
            erotin = "|";
        }
        return sb.toString();
    }

    /**
     * @param k -numeroinen kentt� haussa!
     * @return kent�n tiedot
     */
    public String anna(int k) {
        switch (k) {
        case 0:
            return "" + tilausnumero;
        case 1:
            return "" + asiakasNro;
        case 2:
            return "" + pvm;
        case 3:
            return "" + tyoOsoite;
        case 4:
            return "" + tyoKuvaus;
        case 5:
            return "" + lisatiedot;
        case 6:
            return "" + hinta;
        case 7:
            return "" + laskutettu;
        case 8:
            return "" + maksettu;
        case 9:
            return "" + erapva;
        default:
            return "shiiieeet";
        }
    }

    public Tilaus clone() throws CloneNotSupportedException {// :EE
        return (Tilaus) super.clone();
    }

    /**
     * @return eka kentt�
     */
    public int ekaKentta() {
        return 0;
    }

    /**
     * @param k
     * @return kent�n Nimi
     */
    public String getKysymys(int k) {
        switch (k) {
        case 0:
            return "tilausnumero";
        case 1:
            return "asiakasNro";
        case 2:
            return "pvm";
        case 3:
            return "tyoOsoite";
        case 4:
            return "tyoKuvaus";
        case 5:
            return "lisatiedot";
        case 6:
            return "hinta";
        case 7:
            return "laskutettu";
        case 8:
            return "maksettu";
        case 9:
            return "erapva";
        default:
            return "�sh";
        }
    }
}
