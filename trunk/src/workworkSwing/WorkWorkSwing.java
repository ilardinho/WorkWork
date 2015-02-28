package workworkSwing;

import java.awt.Desktop;
import java.awt.Window;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Collection;

import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

import fi.jyu.mit.gui.AbstractChooser;
import fi.jyu.mit.gui.IStringListChooser;
import fi.jyu.mit.gui.IStringTable;
import fi.jyu.mit.gui.ListChooser;
import fi.jyu.mit.gui.SelectionChangeListener;
import fi.jyu.mit.gui.StringTable;
import fi.jyu.mit.gui.TableEditListener;
import fi.jyu.mit.gui.EditPanel;
//import workworkGUI.EditPanel;
import workworkGUI.TulostusDialogi;
import workworkGUI.Versio;
import workwork.Asiakas;
import workwork.SailoException;
import workwork.Tilaus;
import workwork.WorkWork;

/**
 *
 */
public class WorkWorkSwing {

    private JTable tableMenneetTilaukset;
    private JTable tableLaskutus;
    private JTable tableTilaukset;
    private StringTable tableTilauksetAsiakas;
    private JTable tableMenneetTilauksetAsiakas;
    private JTextPane textPaneKirjoitus;
    private AbstractChooser<String> comboboxHaku;
    private Asiakas asiakasKohdalla;
    private Asiakas editAsiakas;
    private final static Asiakas apuAsiakas = new Asiakas();
    private final WorkWork work;
    private AbstractChooser<Asiakas> listAsiakkaat;
    private JComponent panelAsiakastiedot;
    private EditPanel editAsiakasKentta[];
    private JTextArea areaTilaus = new JTextArea();
    private final StringTable stringTable = new StringTable();

    /**
     * Alustetaan luokka
     */
    public WorkWorkSwing() {
        work = new WorkWork();

    }

    /**
     * alustellaan
     */
    public void alusta() {
        textPaneKirjoitus.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                hae(0);
            }
        });
        listAsiakkaat
                .addSelectionChangeListener(new SelectionChangeListener<Asiakas>() {
                    @Override
                    public void selectionChange(
                            IStringListChooser<Asiakas> sender) {

                        naytaAsiakas();
                    }
                });

        tableTilauksetAsiakas.addTableEditListener(new TableEditListener() {
            @Override
            public String tableEdit(IStringTable sender, int row, int column,
                    Object s) {
                return setTilaus(sender, row, column, s);
            }
        });
        luoNayttoAsiakas();
        luoNayttoAsiakasTilaus();
        hae(0);

    }

    /**
     * @param ismo 
     * @return null onnistuessaan
     * 
     */
    public String lueTiedosto(String ismo) {
        try {
            alusta();
            work.lueTiedostosta(ismo);
            hae(0);
            return null;
        } catch (SailoException e) {
            hae(0);
            lisaaAsiakas();
            return e.getMessage();

        }

    }

    private void luoNayttoAsiakas() {
        panelAsiakastiedot.removeAll();
        int n = apuAsiakas.getKenttia() - apuAsiakas.ekaKentta();
        editAsiakasKentta = new EditPanel[n];
        for (int i = 0, k = apuAsiakas.ekaKentta(); k < apuAsiakas.getKenttia(); k++, i++) {
            EditPanel edit = new EditPanel();
            edit.setCaption(apuAsiakas.getKysymys(k));
            editAsiakasKentta[i] = edit;
            edit.setName("ej" + k);
            edit.getEdit().setName("tj" + k);
            panelAsiakastiedot.add(edit);
            edit.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    kasitteleMuutosAsiakkaaseen((JTextField) e.getComponent());
                }
            });

        }
    }

    private void luoNayttoAsiakasTilaus() {
        // tableTilauksetAsiakas.clear();
        naytaTilaukset();
    }

    /**
     * 
     */
    protected void naytaAsiakas() {
        int ind = listAsiakkaat.getSelectedIndex();
        if (ind < 0)
            return;
        setAsiakasKohdalla(listAsiakkaat.getSelectedObject());

        laitaAsiakas();
        // laitaTilaus();
    }

    // private void laitaTilaus() {
    // if (asiakasKohdalla == null)
    // return;
    // areaTilaus.setText("");
    // PrintStream os = TextAreaOutputStream.getTextPrintStream(areaTilaus);
    // tulostaTilaus(os, asiakasKohdalla);
    // }

    private void laitaAsiakas() {

        if (asiakasKohdalla == null)
            return;
        for (int i = 0, k = asiakasKohdalla.ekaKentta(); k < asiakasKohdalla
                .getKenttia(); k++, i++) {
            editAsiakasKentta[i].setText(asiakasKohdalla.anna(k));

        }
        naytaTilaukset();
    }

    /**
     * Haetaan asiakkaiden tietoja
     * @param asiakasnro 
     
     */
    protected void hae(int asiakasnro) {
        int asno = asiakasnro;
        if (asno < 0 && asiakasKohdalla != null)
            asno = asiakasKohdalla.getAsiakasNro();
        int k =  apuAsiakas.ekaKentta();
        String hakuehto = textPaneKirjoitus.getText();
        if (hakuehto.indexOf('*') < 0) {
            hakuehto = "*" + hakuehto + "*";
        }
        Collection<Asiakas> tulos = work.etsi(hakuehto, k);
            
        listAsiakkaat.clear();

        int index = 0, i = 0;
        for (Asiakas asiakas : tulos) {
            if (asiakas.getAsiakasNro() == asno)
                index = i;
            listAsiakkaat.add(asiakas.getNimi(), asiakas);
            i++;
        }

        listAsiakkaat.setSelectedIndex(index);
    }

    /**
     * @return combobox
     */
    public AbstractChooser<String> getcomboboxHaku() {
        return comboboxHaku;
    }

    /**
     * @param comboboxHaku
     */
    public void setcomboboxHaku(AbstractChooser<String> comboboxHaku) {
        this.comboboxHaku = comboboxHaku;
    }

    /**
     * Avaa selaimeen ohjeen(t‰ll‰ hetkell‰ suunnitelman)
     */
    public static void ohje() {
        Desktop desktop = Desktop.getDesktop();
        try {
            URI uri = new URI(
                    "https://trac.cc.jyu.fi/projects/ohj2k12/wiki/suunnitelmat/miiljall");
            desktop.browse(uri);
        } catch (URISyntaxException e) {
            return;
        } catch (IOException e) {
            return;
        }
    }

    /**
     * Poistellaan asiakkaita
     */
    public void poistaAsiakas() {
        work.poista(asiakasKohdalla.getAsiakasNro());
        int index = listAsiakkaat.getSelectedIndex();
        hae(0);
        listAsiakkaat.setSelectedIndex(index);

    }

    /**
     * Poistetaan tilaus
     */
    public void poistaTilaus() {
        int rivi = tableTilauksetAsiakas.getSelectedRow();
        if (rivi < 0)
            return;
        Tilaus tilaus = (Tilaus) tableTilauksetAsiakas.getSelectedObject();
        if (tilaus == null)
            return;
        work.poistaTilaus(tilaus);
        naytaTilaukset();
        if (rivi >= tableTilauksetAsiakas.getRowCount())
            rivi = tableTilauksetAsiakas.getRowCount() - 1;

    }

    /**
     * lis‰t‰‰n tilaus
     */
    public void lisaaTilaus() {

        if (asiakasKohdalla == null)
            return;
        tarkistaMuutos(asiakasKohdalla.getAsiakasNro());
        Tilaus tilaus = new Tilaus(asiakasKohdalla.getAsiakasNro());
        tilaus.identifioi();
        work.lisaa(tilaus);
        // laitaTilaus();
        naytaTilaukset();
        tableTilauksetAsiakas.selectCell(
                tableTilauksetAsiakas.getRowCount() - 1, 0);
    }

    /**
     * 
     */
    protected void naytaTilaukset() {
        if (asiakasKohdalla == null)
            return;
        tableTilauksetAsiakas.clear();
        tableTilauksetAsiakas.getTable().getColumnModel().getColumn(0)
                .setMinWidth(100);

        List<Tilaus> tilaukset = work.annaTilaukset(asiakasKohdalla);
        for (Tilaus tilaus : tilaukset) {
            int r = tableTilauksetAsiakas.addRow();
            tableTilauksetAsiakas.setObjectAt(tilaus, r);
            for (int k = tilaus.ekaKentta(); k < tilaus.getKenttia(); k++) {
                String jono = tilaus.anna(k);
                tableTilauksetAsiakas.setValueAtModel(jono, r,
                        k - tilaus.ekaKentta());
            }
        }
    }

    /**
     * tulostusnappiOK
     */
    public static void tulostusOK() {

        JOptionPane.showMessageDialog(null, "Enp‰ ossaa tulostaa perkele");

    }

    /**
     * Nƒytt‰‰ nykyisen version tiedot
     */
    public static void versio() {
        Versio versio = new Versio();
        versio.setVisible(true);
    }

    /**
     * Hoitaa tulostuksen
     */
    public static void tulosta() {
        TulostusDialogi tulostusDialogi = new TulostusDialogi();
        tulostusDialogi.setVisible(true);
    }

    /**
     * Hoitaa tulostuksen
     * 
     * @param asiakasKohdalla2
     * @param os
     * @param asiakasKohdalla 
     */
    public void tulostaTilaus(PrintStream os, Asiakas asiakasKohdalla) {
        List<Tilaus> tilaukset = work.annaTilaukset(asiakasKohdalla);
        for (Tilaus tilaus : tilaukset)
            tilaus.tulosta(os);
    }

    // ============================================================0

    /**
     * @param tableTilaukset2
     */
    public void setTableTilaukset(JTable tableTilaukset2) {
        this.tableTilaukset = tableTilaukset2;
    }

    /**
     * @param tableTilaukset
     * @return tableTilaukset
     */
    public StringTable getTableTilaukset(StringTable tableTilaukset) {
        return tableTilaukset;

    }

    /**
     * @param tableTilauksetAsiakas
     */
    public void setTableTilauksetAsiakas(StringTable tableTilauksetAsiakas) {
        this.tableTilauksetAsiakas = tableTilauksetAsiakas;

    }

    /**
     * @param tableTilauksetAsiakas
     * @return tableTilauksetAsiakas
     */
    public StringTable getTableTilauksetAsiakas(
            StringTable tableTilauksetAsiakas) {
        return tableTilauksetAsiakas;

    }

    /**
     * @param tableLaskutus
     */
    public void setTableLaskutus(JTable tableLaskutus) {
        this.tableLaskutus = tableLaskutus;
    }

    /**
     * @param tableLaskutus
     * @return asf
     */
    public JTable getTableLaskutus(JTable tableLaskutus) {
        return tableLaskutus;
    }

    /**
     * @param tableMenneetTilaukset
     */
    public void setTableMenneetTilaukset(JTable tableMenneetTilaukset) {
        this.tableMenneetTilaukset = tableMenneetTilaukset;

    }

    /**
     * @param tableMenneetTilaukset
     * @return fd
     */
    public JTable getTableMenneetTilaukset(JTable tableMenneetTilaukset) {
        return tableMenneetTilaukset;
    }

    /**
     * @param tableMenneetTilauksetAsiakas
     */
    public void setTableMenneetTilauksetAsiakas(
            JTable tableMenneetTilauksetAsiakas) {
        this.tableMenneetTilauksetAsiakas = tableMenneetTilauksetAsiakas;

    }

    /**
     * @param tableMenneetTilauksetAsiakas
     * @return this.tableMenneetTilauksetAsiakas
     */
    public JTable getTableMenneetTilauksetAsiakas(
            JTable tableMenneetTilauksetAsiakas) {
        return tableMenneetTilauksetAsiakas;

    }

    /**
     * Tallentaa ja sulukeepi ohjelman
     * 
     * @param windows
     */
    public void lopeta() {
        try {
            work.tallenna();
        } catch (SailoException ex) {
            JOptionPane.showMessageDialog(null,
                    "Vihtu en osaa tallentaa! oikeesti! ");

        }
        System.exit(0);
    }

    /**
     * @param panelAsiakastiedot
     */
    public void setPanelAsiakastiedot(JComponent panelAsiakastiedot) {
        this.panelAsiakastiedot = panelAsiakastiedot;

    }

    /**
     * @param panelAsiakastiedot
     * @return Asiakasstiedot
     */
    public JComponent getPanelAsiakastiedot() {
        return panelAsiakastiedot;

    }

    /**
     * @param textPaneKirjoitus
     */
    public void setTextPaneKirjoitus(JTextPane textPaneKirjoitus) {
        this.textPaneKirjoitus = textPaneKirjoitus;

    }

    /**
     * @param textPaneKirjoitus
     * @return textPaneKirjoitus
     */
    public JTextPane getTextPaneKirjoitus(JTextPane textPaneKirjoitus) {

        return textPaneKirjoitus;
    }

    /**
     * @param listAsiakkaat
     */
    public void setListAsiakkaat(AbstractChooser<Asiakas> listAsiakkaat) {
        this.listAsiakkaat = listAsiakkaat;

    }

    /**
     * @return listAsiakkaat k
     */
    public AbstractChooser<Asiakas> getListAsiakkaat() {
        return listAsiakkaat;
    }

    /**
     * @param edit
     */
    protected void kasitteleMuutosAsiakkaaseen(JTextField edit) {
        if (asiakasKohdalla == null)
            luoUusiAsiakas();
        if (editAsiakas == null)
            try {
                setEditAsiakas(asiakasKohdalla.clone());
            } catch (CloneNotSupportedException e) {
                // jaosifj
            }
        String muutos = edit.getText();
        int muutettu = Integer.parseInt(edit.getName().substring(2));
        editAsiakas.aseta(muutettu, muutos);
        // TODO: kahtellaan
    }

    private void setEditAsiakas(Asiakas r2d2) {
        editAsiakas = r2d2;
    }

    /**
     * Tallentaa tiedot
     * @param asiakasnumero asiakkaan numero
     * @return  nullia
     */
    public String tallenna(int asiakasnumero) {
        int asno = asiakasnumero;
        try {
            if (muuttunut()) {
                work.korvaaTaiLisaa(editAsiakas);
                asiakasKohdalla = editAsiakas;
                if (asiakasKohdalla != null && asno < 0) {
                    asno = asiakasKohdalla.getAsiakasNro();
                }
                hae(asno);
            }
            setEditAsiakas(null);
            work.tallenna();
            return null;
        } catch (SailoException ex) {
            JOptionPane.showMessageDialog(null,
                    "Vihtu en osaa tallentaa! oikeesti! ");
            return ex.getMessage();
        }
    }

    /**
     * @return -1
     */
    public String tallenna() {
        return tallenna(-1);
    }

    private boolean muuttunut() {
        if (asiakasKohdalla == null)
            return false;
        if (editAsiakas == null)
            return false;
        return !asiakasKohdalla.equals(editAsiakas);
    }

    /**
     * @param asiakasnumero
     */
    public void tarkistaMuutos(int asiakasnumero) {
        if (muuttunut()) {
            int vastaus = JOptionPane.showConfirmDialog(null,
                    "Tallennetaanko?", "Tiedot muuttuneet!",
                    JOptionPane.YES_NO_OPTION);
            if (vastaus == JOptionPane.YES_OPTION)
                tallenna(asiakasnumero);
        }

    }

    /**
     * lis‰t‰‰n asiakas
     */
    public void lisaaAsiakas() {
        luoUusiAsiakas();
        laitaAsiakas();
        listAsiakkaat.clearSelection();
    }

    private void luoUusiAsiakas() {
        Asiakas uusi = new Asiakas();
        uusi.identifioi();
        setAsiakasKohdalla(uusi);
    }

    private void setAsiakasKohdalla(Asiakas aaa) {
        int asno = -1;
        if (aaa != null)
            asno = aaa.getAsiakasNro();
        tarkistaMuutos(asno);
        asiakasKohdalla = aaa;
        setEditAsiakas(null);
    }

    /**
     * @param sender
     * @param row
     * @param column
     * @param s
     * @return no ei miti‰ ny
     */
    public String setTilaus(IStringTable sender, int row, int column, Object s) {
        Tilaus tilaus = (Tilaus) sender.getObjectAt(row);
        if (tilaus == null)
            return "Ei oleee";
        String kokeilu = tilaus
                .aseta(column + tilaus.ekaKentta(), s.toString());
        if (kokeilu == null)
            work.setTilausMuutos();
        return kokeilu;

    }

    // public String setTilaus(IStringTable sender, int row, int column, Object
    // s) {
    // Tilaus tilaus = (Tilaus)sender.getObjectAt(row);
    // if ( tilaus == null ) return "Ei tilauksia";
    // String virhe = tilaus.aseta(column+tilaus.ekaKentta(), s.toString());
    // if ( virhe == null ) work.setTilausMuutos();
    // return virhe;
    // }
}