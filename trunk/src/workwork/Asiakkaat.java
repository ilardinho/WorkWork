package workwork;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import javax.swing.JOptionPane;

import fi.jyu.mit.ohj2.Tiedosto;
import fi.jyu.mit.ohj2.WildChars;

/**
 * Asiakkaat jossa lisäillään asiakkaita
 * @author ilardinho
 *
 */
public class Asiakkaat implements Iterable<Asiakas> {
    private int MAX_asiakkaita = Integer.MAX_VALUE;
    private int lkm = 0;
    private Asiakas alkiot[] = new Asiakas[100];
    private String tiedostonPerusNimi = "";
    private boolean muutettu = false;
    private String kokoNimi = "";

    /**
     * Oletusmuodostaja
     */
    public Asiakkaat() {
        // Attribuuttien oma alustus riittää
    }

    /**
     * @param koko
     */
    public Asiakkaat(int koko) {
        MAX_asiakkaita = koko;
        alkiot = new Asiakas[MAX_asiakkaita];
    }

    /**
     * testipääohjelma
     * @param args ei käytössä
     */
    public static void main(String args[]) {
        Asiakkaat asiakkaat = new Asiakkaat();

        Asiakas ismo = new Asiakas(), seppo = new Asiakas(), atik = new Asiakas();
        atik.identifioi();
        ismo.identifioi();
        seppo.identifioi();
        ismo.vastaaSeppoIsmo();
        seppo.vastaaSeppoIsmo();
        atik.vastaaSeppoIsmo();
        asiakkaat.lisaa(atik);
        asiakkaat.lisaa(seppo);
        asiakkaat.lisaa(ismo);

        System.out.println("========== Asiakkaat testi ==============");

        for (int i = 0; i < asiakkaat.getLkm(); i++) {
            Asiakas asiakas = asiakkaat.anna(i);
            System.out.println("Jäsen nro: " + i);
            asiakas.tulosta(System.out);
        }
    }

    /**
     * palautetaan viite i jäseneen
     * @param i monesko jäsen
     * @return viite indeksiin i
     * @throws IndexOutOfBoundsException
     */
    public Asiakas anna(int i) throws IndexOutOfBoundsException {
        if (i < 0 || lkm <= i)
            throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
        return alkiot[i];
    }

    /**
     * lukumäärän hakeminen
     * @return lukumäärä
     */
    public int getLkm() {

        return lkm;
    }

    /**
     * lisätään asiakas listaan, tarvittaessa lisätään alkioiden määrää
     * @param asiakas joka lisätään
     */
    public void lisaa(Asiakas asiakas) {
        muutettu = true;
        if (lkm >= alkiot.length)
            kasvata();
        // throw new SailoException("Liikaa alkioita");
        alkiot[lkm] = asiakas;
        lkm++;

    }

    /**
     * kasvatetaan taulukon kokoa viidellä
     */
    public void kasvata() {
        Asiakas[] kasvatettu = new Asiakas[alkiot.length + 5];
        int i = 0;
        while (i < alkiot.length) {
            kasvatettu[i] = alkiot[i];
            i++;
        }
        alkiot = kasvatettu;
    }

    /**
     * @param tallennusjalukunimi
     */
    public void setTiedostonPerusNimi(String tallennusjalukunimi) {
        tiedostonPerusNimi = tallennusjalukunimi;
    }

    /**
     * @return tiedoston nimen josta luetaan
     */
    public String getTiedostonPerusNimi() {
        return tiedostonPerusNimi;
    }

    /**
     * @param tallennusjalukunimi 
     * @throws SailoException
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * #import java.io.File;
     * #import java.util.Iterator;
     *  Asiakkaat asiakkaat = new Asiakkaat();
     *  Asiakas ismo = new Asiakas(), ismo2 = new Asiakas();
     *  ismo.vastaaSeppoIsmo();
     *  ismo2.vastaaSeppoIsmo();
     *  String tiedNimi = "data";
     *  File ftied = new File(tiedNimi+".dat");
     *  ftied.delete();
     *  asiakkaat.lueTiedostosta(tiedNimi); #THROWS SailoException
     *  asiakkaat.lisaa(ismo);
     *  asiakkaat.lisaa(ismo2);
     *  // asiakkaat.talleta();
     *  asiakkaat = new Asiakkaat();           // Poistetaan vanhat luomalla uusi
     *  asiakkaat.lueTiedostosta(tiedNimi);  // johon ladataan tiedot tiedostosta.
     *  Iterator<Asiakas> i = asiakkaat.iterator();
     *  i.next().toString() === ismo.toString();
     *  i.next().toString() === ismo2.toString();
     *  i.hasNext() === false;
     *  asiakkaat.lisaa(ismo);
     *  // asiakkaat.talleta();
     *  ftied.delete() === true;
     *  File fbak = new File(tiedNimi+".bak");
     *  fbak.delete() === true;
     * </pre>
     * */
    public void lueTiedostosta(String tallennusjalukunimi)
            throws SailoException {
        setTiedostonPerusNimi(tallennusjalukunimi);
        BufferedReader fi = Tiedosto
                .avaa_lukemista_varten(getTiedostonPerusNimi());
        if (fi == null)
            throw new SailoException("Tiedosto " + getTiedostonPerusNimi()
                    + " ei aukea");

        try {
            kokoNimi = fi.readLine();
            if (kokoNimi == null)
                throw new SailoException("Kerhon nimi puuttuu");
            String rivi = fi.readLine();
            if (rivi == null)
                throw new SailoException("Maksimikoko puuttuu");

            while ((rivi = fi.readLine()) != null) {
                rivi = rivi.trim();
                if ("".equals(rivi) || rivi.charAt(0) == ';')
                    continue;
                Asiakas asiakas = new Asiakas();
                asiakas.parse(rivi);
                lisaa(asiakas);
            }
            muutettu = false;

        } catch (IOException e) {
            throw new SailoException("Ongelmia tiedoston kanssa: "
                    + e.getMessage());
        } finally {
            try {
                fi.close();
            } catch (IOException e) {
                throw new SailoException("Tiedoston sulkeminen ei onnistu: "
                        + e.getMessage());
            }
        }
    }

    /**
     * Tallennetaan tiedostoon
     * @throws SailoException
     */
    public void tallenna() throws SailoException {
        if (!muutettu)
            return;

        File fbak = new File(getBakNimi());
        File ftied = new File(getTiedostonPerusNimi());
        fbak.delete(); // if .. System.err.println("Ei voi tuhota");
        ftied.renameTo(fbak); // if .. System.err.println("Ei voi nimetä");

        PrintWriter fo = Tiedosto.avaa_kirjoittamista_varten(ftied.getName());
        if (fo == null)
            throw new SailoException("Tiedosto " + ftied.getName()
                    + " ei aukea");

        try {
            fo.println(getKokoNimi());
            fo.println(alkiot.length);
            for (Asiakas asiakas : this) {
                fo.println(asiakas.toString());
            }
            // } catch ( IOException e ) { // ei heitä poikkeusta
            // throw new SailoException("Tallettamisessa ongelmia: " +
            // e.getMessage());
        } finally {
            fo.close();
        }

        muutettu = false;
    }

    /**
     * @param asiakas
     * @throws SailoException
     */
    public void korvaaTaiLisaa(Asiakas asiakas) throws SailoException {
        int nummero = asiakas.getAsiakasNro();
        for (int i = 0; i < lkm; i++) {
            if (alkiot[i].getAsiakasNro() == nummero) {
                alkiot[i] = asiakas;
                muutettu = true;
                return;
            }
        }
        lisaa(asiakas);
    }

    /**
     * @return koko nimi
     */
    public String getKokoNimi() {
        return kokoNimi;
    }

    private String getBakNimi() {
        return tiedostonPerusNimi + ".bak";
    }

    /**
     * @param asno
     * @return 1
     */
    public int poista(int asno) {
        int ind = etsiAsno(asno);
        if (ind < 0)
            return 0;
        lkm--;
        for (int i = ind; i < lkm; i++)
            alkiot[i] = alkiot[i + 1];
        alkiot[lkm] = null;
        muutettu = true;
        return 1;
    }

    /**
     * @param id
     * @return -1
     */
    public int etsiAsno(int id) {
        for (int i = 0; i < lkm; i++)
            if (id == alkiot[i].getAsiakasNro())
                return i;
        return -1;
    }

    // ===================================================
    /**
     * @author ilardinho
     * ÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄSLAFÖSAKFÄÖKAÄSÖK
     */
    public class AsiakkaatIterator implements Iterator<Asiakas> {
        private int kohdalla = -1;

        /**
         * Onko olemassa vielä seuraavaa jäsentä
         * @see java.util.Iterator#hasNext()
         * @return true jos on vielä jäseniä
         */
        @Override
        public boolean hasNext() {
            // if ( kohdalla + 1 >= lkm ) return false;
            // return true;
            return kohdalla + 1 < getLkm();
        }

        /**
         * Annetaan seuraava asiakas
         * @return asiakas
         * @throws NoSuchElementException
         */
        @Override
        public Asiakas next() throws NoSuchElementException {
            if (!hasNext())
                throw new NoSuchElementException("Ei oo");
            kohdalla++;
            return anna(kohdalla);
        }

        /**
         * @throws UnsupportedOperationException aina
         * @see java.util.Iterator#remove()
         */
        @Override
        public void remove() throws UnsupportedOperationException {
            throw new UnsupportedOperationException(">*");
        }
    }

    @Override
    public Iterator<Asiakas> iterator() {
        return new AsiakkaatIterator();
    }

    /**
     * @param hakuehto
     * @param k
     * @return löydetyt
     */
    public Collection<Asiakas> etsi(String hakuehto, int k) {
        List<Asiakas> loytyneet = new ArrayList<Asiakas>();
        for (Asiakas asiakas : this) {
            if (WildChars.onkoSamat(asiakas.anna(k), hakuehto))
                loytyneet.add(asiakas);
        }
        //
        return loytyneet;
    }

}
