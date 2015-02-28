package workwork;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import fi.jyu.mit.ohj2.Tiedosto;

/**
 * @author ilardinho
 *
 */

public class Tilaukset implements Iterable<Tilaus> {

    private boolean muutettu = false;
    private final Collection<Tilaus> alkiot = new ArrayList<Tilaus>();
    private String tiedostonPerusNimi = "";

    /**
     * Tilausten alustaminen
     */
    public Tilaukset() {
//     
    }

    /**
     * Lisää uuden tilauksen
     * @param tilaus 
     */
    public void lisaa(Tilaus tilaus) {
        alkiot.add(tilaus);
        muutettu = true;
    }

    /**
     * Palauttaa tilausten lukumäärän
     * @return tilausten lukumäärä
     */
    public int getLkm() {
        return alkiot.size();
    }

    /**
     * Iteraattori kaikkien tilausten läpikäymiseen
     * @return tilausiteraattori    
     */
    @Override
    public Iterator<Tilaus> iterator() {
        return alkiot.iterator();
    }

    /**
     * 
     * </pre> 
     * @param tunnusnro 
     * @return löydetyt alkiot
     */
    public List<Tilaus> annaTilaukset(int tunnusnro) {
        List<Tilaus> loydetyt = new ArrayList<Tilaus>();
        for (Tilaus tilde : alkiot)
            if (tilde.getAsiakastunnus() == tunnusnro)
                loydetyt.add(tilde);
        return loydetyt;
    }

    /**
     * Testiohjelma harrastuksille
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Tilaukset tilaukset = new Tilaukset();
        Tilaus maalaus1 = new Tilaus();
        maalaus1.vastaaMaalaus(2);
        Tilaus maalaus2 = new Tilaus();
        maalaus2.vastaaMaalaus(1);
        Tilaus maalaus3 = new Tilaus();
        maalaus3.vastaaMaalaus(2);
        Tilaus maalaus4 = new Tilaus();
        maalaus4.vastaaMaalaus(5);

        tilaukset.lisaa(maalaus1);
        tilaukset.lisaa(maalaus2);
        tilaukset.lisaa(maalaus3);
        tilaukset.lisaa(maalaus2);
        tilaukset.lisaa(maalaus4);

        System.out.println("============= Tilaukset testi =================");

        List<Tilaus> tilaus2 = tilaukset.annaTilaukset(2);

        for (Tilaus tilaus : tilaus2) {
            System.out.print(tilaus.getAsiakastunnus() + " ");
            tilaus.tulosta(System.out);
        }

    }

    /**
     * @throws SailoException
     */
    public void tallenna() throws SailoException {
        if (!muutettu)
            return;

        File fbak = new File(getBakNimi());
        File ftied = new File(getTiedostonNimi());
        fbak.delete(); // if ... System.err.println("Ei voi tuhota");
        ftied.renameTo(fbak); // if ... System.err.println("Ei voi nimetä");

        PrintWriter fo = Tiedosto.avaa_kirjoittamista_varten(ftied.getName());
        if (fo == null)
            throw new SailoException("Tiedosto " + ftied.getName()
                    + " ei aukea");
        try {
            for (Tilaus tilaus : this) {
                fo.println(tilaus.toString());
            }
        } finally {
            fo.close();
        }

        muutettu = false;
    }

    void setTiedostonPerusNimi(String ismo) {
        tiedostonPerusNimi = ismo;
    }

    @SuppressWarnings("unused")
    private String getTiedostonPerusNimi() {
        return tiedostonPerusNimi;
    }

    private String getBakNimi() {
        return tiedostonPerusNimi + ".tilbak";
    }
    
    /**
     * @param ismo 
     * @throws SailoException
     */
    public void lueTiedostosta(String ismo) throws SailoException {
        muutettu = true;
        setTiedostonPerusNimi(ismo);
        BufferedReader fi = Tiedosto.avaa_lukemista_varten(getTiedostonNimi());
        if (fi == null) throw new SailoException("Tiedosto " + getTiedostonNimi() + " ei aukea");

        String rivi;
        try {
            while ((rivi = fi.readLine()) != null) { 
                rivi = rivi.trim();
                if ("".equals(rivi) || rivi.charAt(0) == ';') continue;
                Tilaus tilaus = new Tilaus(); 
                tilaus.parse(rivi); 
                lisaa(tilaus);
            }
            muutettu = false;

        } catch (IOException e) {
            throw new SailoException("Ongelmia tiedoston kanssa: " + e.getMessage());
        } finally {
            try {
                fi.close();
            } catch (IOException e) {
                throw new SailoException("Tiedoston sulkeminen ei onnistu: " + e.getMessage()); 
            }
        }
    }
    /**
     * @return tiedostonnimi
     */
    public String getTiedostonNimi() {
        return tiedostonPerusNimi + ".til";
    }
    /**
     *muutos toteen
     */
    public void setMuutos(){
        muutettu = true;
    }
    /**
     * @param tilaus
     * @return retttttu
     */
    public boolean poista(Tilaus tilaus){
        boolean ret = alkiot.remove(tilaus);
        if (ret) muutettu = true;
        return ret;
    }
    /**
     * @param asiakasNro
     * @return ännä
     */
    public int poista(int asiakasNro){
        int n = 0;
        for (Iterator<Tilaus> it = alkiot.iterator(); it.hasNext();){
            Tilaus tilaus = it.next();
            if(tilaus.getAsiakastunnus() == asiakasNro){
                it.remove();
                n++;
            }
        }
        if (n > 0) muutettu = true;
        return n;
    }
}