package workwork;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * WorkWork-luokka asiakkaiden hoitoon
 * @author ilardinho
 *
 */
public class WorkWork implements Iterable<Asiakas> {

    private Asiakkaat asiakkaat = new Asiakkaat();
    private Tilaukset tilaukset = new Tilaukset();

    /**
     * Palautaa asiakasm‰‰r‰n
     * @return asiakasm‰‰r‰
     */
    public int getAsiakkaat() {
        return asiakkaat.getLkm();
    }

    /**
     * Palautaa asiakkaiden m‰‰r‰n
     * @return asiakkaiden m‰‰r‰
     */
    public int getAsiakkaita() {
        return asiakkaat.getLkm();
    }

    /**
     * Lis‰‰ uuden j‰senen asiakkaan
     * @param asiakas 
     * @throws SailoException 
     */
    public void lisaa(Asiakas asiakas) throws SailoException {
        asiakkaat.lisaa(asiakas);
    }

    /**
     * List‰‰n uusi tilaus
     * @param tilaus 
     * @param har 
     */
    public void lisaa(Tilaus tilaus) {
        tilaukset.lisaa(tilaus);
    }

    /**
     * 
     * @param asiakas 
     * @throws SailoException 
     */
    public void korvaaTaiLisaa(Asiakas asiakas) throws SailoException {
        asiakkaat.korvaaTaiLisaa(asiakas);
    }

    /**
     * Haetaan asiakkaan tilaukset
     * @param asiakas 
     * @param jasen Asiakas jonka tilauksia haetaan
     * @return tietorakenne tilauksien viitteisiin
     */
    public List<Tilaus> annaTilaukset(Asiakas asiakas) {
        return tilaukset.annaTilaukset(asiakas.getAsiakasNro());
    }

    /**
     * Palautetaan asiakas
     * @param i monesko asiakas halutaan
     * @return viite asiakkaaseen
     * @throws IndexOutOfBoundsException 
     */
    public Asiakas annaAsiakas(int i) throws IndexOutOfBoundsException {
        return asiakkaat.anna(i);
    }

    /**
     * @param ismo 
     * @throws SailoException
     */
    public void lueTiedostosta(String ismo) throws SailoException {
        asiakkaat = new Asiakkaat();
        tilaukset = new Tilaukset();
        String tallennusjalukunimi = ismo;
        asiakkaat.setTiedostonPerusNimi(tallennusjalukunimi);
        tilaukset.setTiedostonPerusNimi(tallennusjalukunimi);
        asiakkaat.lueTiedostosta(tallennusjalukunimi);
        tilaukset.lueTiedostosta(tallennusjalukunimi);
    }

    /**
     * @throws SailoException
     */

    public void tallenna() throws SailoException {
        String virhe = "";
        try {
            asiakkaat.tallenna();
        } catch (SailoException ex) {
            virhe = ex.getMessage();
        }

        try {
            tilaukset.tallenna();
        } catch (SailoException ex) {
            virhe += ex.getMessage();
        }
        if (!"".equals(virhe))
            throw new SailoException(virhe);
    }

    /**
     * Testiohjelma workworkista
     * @param args ei k‰ytˆss‰
     */
    public static void main(String args[]) {
        WorkWork work = new WorkWork();

        try {

            Asiakas ismo = new Asiakas(), seppo = new Asiakas(), atik = new Asiakas();
            atik.identifioi();
            ismo.identifioi();
            seppo.identifioi();
            atik.vastaaSeppoIsmo();
            seppo.vastaaSeppoIsmo();
            ismo.vastaaSeppoIsmo();

            work.lisaa(ismo);
            work.lisaa(atik);
            work.lisaa(seppo);

            System.out.println("============= WORKWORK =================");

            for (int i = 0; i < work.getAsiakkaita(); i++) {
                Asiakas asiakas = work.annaAsiakas(i);
                System.out.println("Asiakas paikassa: " + i);
                asiakas.tulosta(System.out);
            }

        } catch (SailoException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Iterator<Asiakas> iterator() {
        return asiakasIterator();
    }

    /**
     * @return asiakkaat iterator
     */
    public Iterator<Asiakas> asiakasIterator() {
        return asiakkaat.iterator();

    }

    /**
     * muutetaan tilausta
     */
    public void setTilausMuutos() {
        tilaukset.setMuutos();
    }

    /**
     * @param asno 
     * @return retiisi
     */
    public int poista(int asno) {
        int ret = asiakkaat.poista(asno);
//        tilaukset.poista(asno);
        return ret;
    }

    /**
     * @param tilaus 
     */
    public void poistaTilaus(Tilaus tilaus) {
        tilaukset.poista(tilaus);

    }

    /**
     * @param hakuehto mill‰ haetaan
     * @param k
     * @return asiakkaat etsi
     */
    public Collection<Asiakas> etsi(String hakuehto, int k) {
        return asiakkaat.etsi(hakuehto, k);
    }
}
