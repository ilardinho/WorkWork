package workworkGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.AbstractListModel;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JSplitPane;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JComboBox;

import workwork.Asiakas;
import workworkSwing.WorkWorkSwing;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import fi.jyu.mit.gui.AbstractChooser;
import fi.jyu.mit.gui.ComboBoxChooser;
import fi.jyu.mit.gui.GenListChooser;
import fi.jyu.mit.gui.ListChooser;
import fi.jyu.mit.gui.StringTable;


/**
 * @author ilardinho
 * versio 10.2.2012
 */
public class Main extends JFrame {

    private final JSplitPane keskustaTaustasplitPane = new JSplitPane();
    private final JSplitPane oikeaTietoIkkunasplitPane = new JSplitPane();
    private final JPanel henkiloTiedotpanel = new JPanel();
    private final JPanel tilauksetpanel = new JPanel();
    private final JLabel asiakasTiedotlabel = new JLabel("Asiakastiedot");
    private final JSplitPane splitPane = new JSplitPane();
    private final JPanel panelAsiakastiedot = new JPanel();
    private final JScrollPane scrollPaneAsiakasTiedot = new JScrollPane(panelAsiakastiedot);
    private final Box boxAsiakas = Box.createVerticalBox();
    private final EditPanel editPanelNimi = new EditPanel(("Nimi"));
    private final EditPanel editPanelKatuosoite = new EditPanel(("Katuosoite"));
    private final EditPanel editPanelPostinumero = new EditPanel(
            ("Postinumero"));
    private final EditPanel editPanelPostitoimipaikka = new EditPanel(
            ("Postitoimipaikka"));
    private final EditPanel editPanelPuhelin = new EditPanel(("Puhelin"));
    private final EditPanel editPanelAtunnus = new EditPanel(("Asiakastunnus"));
    private final EditPanel editPanelYritysNimi = new EditPanel(("Yritys"));
    private final EditPanel editPanelYritysKatuosoite = new EditPanel(
            ("Yrityksen osoite"));
    private final EditPanel editPanelYritysPostinro = new EditPanel(
            ("Postinumero"));
    private final EditPanel editPanelYritysPostitoimipaikka = new EditPanel(
            ("Postitoimipaikka"));
    private final EditPanel editPanelYritysPuhelin = new EditPanel(("Puhelin"));
    private final EditPanel editPanelSpostiOsoite = new EditPanel(
            ("Sähköposti"));
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private final JSplitPane splitPane_1 = new JSplitPane();
    private final JSplitPane splitPaneTilauksetPaaIkkuna = new JSplitPane();
    private final JSplitPane splitPaneTilauksetFinal = new JSplitPane();
    private final JPanel panelTilauksetNyt = new JPanel();
    private final JTable tableTilaukset = new JTable();
    private final JSplitPane splitPaneLaskutusPaaIkkuna = new JSplitPane();
    private final JPanel panel_4 = new JPanel();
    private final JLabel lblLaskutus = new JLabel("Laskutus");
    private final JScrollPane scrollPane_2 = new JScrollPane();
    private final JTable tableLaskutus = new JTable();
    private final JTable tableMenneetTilaukset = new JTable();
    private final JTable tableMenneetTilauksetAsiakas = new JTable();
    private final JLabel labelTilaukset = new JLabel("Avoimet Tilaukset");
    private final JLabel labelMenneetTilaukset = new JLabel("Menneet Tilaukset");
    private final JPanel panelTilauksetVanhat = new JPanel();
    private final JScrollPane scrollPaneTilauksetAvoimet = new JScrollPane();
    private final JScrollPane scrollPaneTilauksetVanhat = new JScrollPane();
    private final JPanel panelTilauksetHaku = new JPanel();
    private final JPanel panelLaskutusHaku = new JPanel();
    private final MenuPalkki menuPalkki = new MenuPalkki();
    private final JSplitPane splitPaneTilaukset = new JSplitPane();
    private final JPanel panelTilauksetAvoimet = new JPanel();
    private final JPanel panelTilauksetMenneet = new JPanel();
    private final JScrollPane scrollPaneTilauksetAsiakas = new JScrollPane();
    private final JScrollPane scrollPaneTilauksetMenneetAsiakas = new JScrollPane();
    private final JLabel lblAvoimetTilaukset = new JLabel("Avoimet tilaukset");
    private final JLabel lblMenneet = new JLabel("Menneet tilaukset");
    private final JPanel panelNappulatAlarivi = new JPanel();
    private final JPanel panelOikeaReunaNappula = new JPanel();
    private final JButton btnTallenna = new JButton("Tallenna");
    private final JPanel panelHakuAsiakas = new JPanel();
    private final JLabel lblHaku = new JLabel("Haku");
    private final JLabel lblHakuLaskutus = new JLabel("Haku");
    private final JLabel lblHakuTilaukset = new JLabel("Haku");
    private final JSplitPane splitPaneHaku = new JSplitPane();
    private final JSplitPane splitPaneHakuLaskutus = new JSplitPane();
    private final JSplitPane splitPaneHakuTilaukset = new JSplitPane();
    private final JPanel panelHakuApu = new JPanel();
    private final JPanel panelHakuApuTilaukset = new JPanel();
    private final JPanel panelHakuApuLaskutus = new JPanel();
    private final ComboBoxChooser comboBoxHaku = new ComboBoxChooser();
    private final JComboBox comboBoxHakuLaskutus = new JComboBox();
    private final JComboBox comboBoxHakuTilaukset = new JComboBox();
    private final JTextPane textPaneKirjoitus = new JTextPane();
    private final JTextPane textPaneKirjoitusTilaukset = new JTextPane();
    private final JTextPane textPaneKirjoitusLaskutus = new JTextPane();
    private final JList listHakuLaskutus = new JList();
    private final JList listHakuTilaukset = new JList();
    private final StringTable stringTable = new StringTable();
    private final GenListChooser<Asiakas> listAsiakkaat = new GenListChooser<Asiakas>();
    String ismo = "data";
    /**
     *
     */
    protected final static WorkWorkSwing workworkSwing = new WorkWorkSwing();

    /**
     * @return workworkSwing 
     */
    public static WorkWorkSwing getWorkWorkSwing() {
        return workworkSwing;

    }

    /**
     * Launch the application.
     * @param args
     */
    public static void main(final String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main frame = new Main();
                    frame.setVisible(true);
                    frame.lueTiedosto();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     */
    protected void lueTiedosto() {
        String virhe = workworkSwing.lueTiedosto(ismo);
        if (virhe != null)
                     return;
    }

    /**
     * Create the frame.
     */
    public Main() {
        workworkSwing.setListAsiakkaat(listAsiakkaat);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                workworkSwing.tallenna();
            }
        });
        workworkSwing.setTableTilaukset(tableTilaukset);
        workworkSwing.setTableTilauksetAsiakas(stringTable);
        workworkSwing.setTableLaskutus(tableLaskutus);
        workworkSwing.setListAsiakkaat(listAsiakkaat);
        workworkSwing.setTableMenneetTilaukset(tableMenneetTilaukset);
        workworkSwing
                .setTableMenneetTilauksetAsiakas(tableMenneetTilauksetAsiakas);
        workworkSwing.setPanelAsiakastiedot(panelAsiakastiedot);
        workworkSwing.setTableMenneetTilaukset(tableMenneetTilaukset);
        workworkSwing.setTableMenneetTilaukset(tableMenneetTilaukset);
        workworkSwing.setTableMenneetTilaukset(tableMenneetTilaukset);
        workworkSwing.setTableMenneetTilaukset(tableMenneetTilaukset);
        workworkSwing.setTableMenneetTilaukset(tableMenneetTilaukset);
        workworkSwing.setTextPaneKirjoitus(textPaneKirjoitus);
        workworkSwing.setPanelAsiakastiedot(boxAsiakas);
        workworkSwing.setcomboboxHaku(comboBoxHaku);

        setTitle("WorkWork 0.1");
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 803, 627);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
        contentPane.add(tabbedPane, BorderLayout.CENTER);

        // =========================================================================================================================================================
        // Asiakas-paneeli
        JPanel panelAsiakas = new JPanel();
        tabbedPane.addTab("Asiakas", null, panelAsiakas, null);
        panelAsiakas.setLayout(new BorderLayout(0, 0));
        keskustaTaustasplitPane.setOneTouchExpandable(true);

        panelAsiakas.add(keskustaTaustasplitPane, BorderLayout.CENTER);

        // ---------------------------
        oikeaTietoIkkunasplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);

        keskustaTaustasplitPane.setRightComponent(oikeaTietoIkkunasplitPane);
        henkiloTiedotpanel.setMinimumSize(new Dimension(100, 100));

        oikeaTietoIkkunasplitPane.setLeftComponent(henkiloTiedotpanel);
        henkiloTiedotpanel.setLayout(new BorderLayout(0, 0));
        henkiloTiedotpanel.setMaximumSize(new Dimension(50, 50));
        henkiloTiedotpanel.setMinimumSize(new Dimension(100, 260));

        henkiloTiedotpanel.add(asiakasTiedotlabel, BorderLayout.NORTH);

        henkiloTiedotpanel.add(scrollPaneAsiakasTiedot, BorderLayout.CENTER);

        scrollPaneAsiakasTiedot.setViewportView(panelAsiakastiedot);
        panelAsiakastiedot.setLayout(new BorderLayout(0, 0));

        panelAsiakastiedot.add(boxAsiakas, BorderLayout.NORTH);
        
        editPanelNimi.setCaption("Terve\r\n");

        editPanelNimi.setTextFieldText("Seppo Ismola");
        boxAsiakas.add(editPanelNimi);
        editPanelKatuosoite.setTextFieldText("Mets\u00E4tie 1");
        boxAsiakas.add(editPanelKatuosoite);
        editPanelPostinumero.setTextFieldText("12345  ");
        boxAsiakas.add(editPanelPostinumero);
        editPanelPostitoimipaikka.setTextFieldText("Tampere");

        boxAsiakas.add(editPanelPostitoimipaikka);
        editPanelPuhelin.setTextFieldText("12-12324");

        boxAsiakas.add(editPanelPuhelin);
        editPanelAtunnus.setTextFieldText("9483 ");

        boxAsiakas.add(editPanelAtunnus);

        boxAsiakas.add(editPanelYritysNimi);

        boxAsiakas.add(editPanelYritysKatuosoite);

        boxAsiakas.add(editPanelYritysPostinro);

        boxAsiakas.add(editPanelYritysPostitoimipaikka);

        boxAsiakas.add(editPanelYritysPuhelin);

        boxAsiakas.add(editPanelSpostiOsoite);
        // -------------------------------------
        // Asiakastietonäkymän tilaustiedot
        // avoimet

        oikeaTietoIkkunasplitPane.setRightComponent(tilauksetpanel);
        tilauksetpanel.setLayout(new BorderLayout(0, 0));

        splitPaneTilaukset.setOrientation(JSplitPane.VERTICAL_SPLIT);

        tilauksetpanel.add(splitPaneTilaukset);

        splitPaneTilaukset.setLeftComponent(panelTilauksetAvoimet);
        panelTilauksetAvoimet.setLayout(new BorderLayout(0, 0));
        panelTilauksetAvoimet.setMaximumSize(new Dimension(50, 50));
        panelTilauksetAvoimet.setMinimumSize(new Dimension(100, 100));

        panelTilauksetAvoimet.add(lblAvoimetTilaukset, BorderLayout.NORTH);

        panelTilauksetAvoimet.add(scrollPaneTilauksetAsiakas,
                BorderLayout.CENTER);

        scrollPaneTilauksetAsiakas.setViewportView(stringTable);
        stringTable.getTable().setModel(
                new DefaultTableModel(new Object[][] {
                		{ new Integer(35), new Integer(9483), "12.7.2012", "Tammitie 2 Tammisto", "maalaus",
                        "Paikalle klo 1300", "100", "ei", "ei", "22.12.2012" }

        }, new String[] { "Tilaustunnus", "Asiakastunnus", "Pvm", "Työosoite",
                "Työkuvaus", "Lisätiedot",  "hinta", "Laskutettu", "Maksettu", "Eräpäivä" }

        ));

  

        // -------------
        // menneet

        splitPaneTilaukset.setRightComponent(panelTilauksetMenneet);
        panelTilauksetMenneet.setLayout(new BorderLayout(0, 0));

        panelTilauksetMenneet.add(lblMenneet, BorderLayout.NORTH);

        panelTilauksetMenneet.add(scrollPaneTilauksetMenneetAsiakas,
                BorderLayout.CENTER);
        scrollPaneTilauksetMenneetAsiakas
                .setViewportView(tableMenneetTilauksetAsiakas);

        tableMenneetTilauksetAsiakas.setModel(new DefaultTableModel(
                new Object[][] { { new Integer(67), new Integer(9483),
                        "Leikkuu", "Paikalle klo 1300" },

                }, new String[] { "Tilaustunnus", "Asiakastunnus", "Pvm",
                        "Työnkuva", "Lisätiedot" }

        ));
        // ---------------
        // hakupaskat
        keskustaTaustasplitPane.setLeftComponent(panelHakuAsiakas);
        panelHakuAsiakas.setLayout(new BorderLayout(0, 0));

        panelHakuAsiakas.add(lblHaku, BorderLayout.NORTH);
        splitPaneHaku.setOrientation(JSplitPane.VERTICAL_SPLIT);

        panelHakuAsiakas.add(splitPaneHaku, BorderLayout.CENTER);

        splitPaneHaku.setLeftComponent(panelHakuApu);
        panelHakuApu.setLayout(new BorderLayout(0, 0));

        panelHakuApu.add(comboBoxHaku, BorderLayout.CENTER);
        comboBoxHaku.setSelectedIndex(0);
        comboBoxHaku.setKohteet(new String[] { "nimi",
                "asiakastunnus", "Yritys", "Katuosoite", "Postinumero",
                "Postitoimipaikka", "Puhelin" });

        panelHakuApu.add(textPaneKirjoitus, BorderLayout.SOUTH);

        splitPaneHaku.setRightComponent(workworkSwing.getListAsiakkaat());
        

        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);

        contentPane.add(splitPane, BorderLayout.NORTH);

        // =========================================================================================================================================================
        // Tilaukset-paneeli

        JPanel panelTilaukset = new JPanel();
        tabbedPane.addTab("Tilaukset", null, panelTilaukset, null);
        panelTilaukset.setLayout(new BorderLayout(0, 0));

        panelTilaukset.add(splitPaneTilauksetPaaIkkuna);
        splitPaneTilauksetPaaIkkuna.setOneTouchExpandable(true);

        splitPaneTilauksetFinal.setOrientation(JSplitPane.VERTICAL_SPLIT);

        splitPaneTilauksetPaaIkkuna.setRightComponent(splitPaneTilauksetFinal);

        splitPaneTilauksetFinal.setLeftComponent(panelTilauksetNyt);
        panelTilauksetNyt.setLayout(new BorderLayout(0, 0));
        panelTilauksetNyt.setMaximumSize(new Dimension(50, 50));
        panelTilauksetNyt.setMinimumSize(new Dimension(100, 200));

        panelTilauksetNyt.add(labelTilaukset, BorderLayout.NORTH);

        panelTilauksetNyt.add(scrollPaneTilauksetAvoimet, BorderLayout.CENTER);
        scrollPaneTilauksetAvoimet.setViewportView(tableTilaukset);

        tableTilaukset.setModel(new DefaultTableModel(new Object[][] {
                { new Integer(35), new Integer(9483), "12.7.2012",
                        "Metsätie 1 12345 Tampere", "Maalaus", new Integer(7),
                        "Paikalle klo 1300" },
                { new Integer(67), new Integer(9483), "12.7.2012",
                        "Tammi 2 12555 Tampere", "Leikkuu", new Integer(72),
                        "Paikalle klo 1300" },
                { new Integer(83), new Integer(9483), "12.7.2012",
                        "Metsätie 1 12345 Tampere", "Maalaus", new Integer(67),
                        "Paikalle klo 1300" },
                { new Integer(32), new Integer(9483), "12.7.2012",
                        "Metsätie 1 12345 Tampere", "Maalaus",
                        new Integer((int) 76.8), "Paikalle klo 1300" },

        }, new String[] { "Tilaustunnus", "Asiakastunnus", "Pvm", "Työosoite",
                "Työnkuva", "Hinta", "Lisätiedot" }

        ));

        // --------------------------------
        // vanhat tilaukset

        splitPaneTilauksetFinal.setRightComponent(panelTilauksetVanhat);
        panelTilauksetVanhat.setLayout(new BorderLayout(0, 0));

        panelTilauksetVanhat.add(labelMenneetTilaukset, BorderLayout.NORTH);

        panelTilauksetVanhat
                .add(scrollPaneTilauksetVanhat, BorderLayout.CENTER);
        scrollPaneTilauksetVanhat.setViewportView(tableMenneetTilaukset);
        tableMenneetTilaukset.setModel(new DefaultTableModel(new Object[][] {

        { new Integer(67), new Integer(9483), "12.7.2012",
                "Tammi 2 12555 Tampere", "Leikkuu", new Integer(72),
                "Paikalle klo 1300" },

        }, new String[] { "Tilaustunnus", "Asiakastunnus", "Pvm", "Työosoite",
                "Työnkuva", "Hinta", "Lisätiedot" }

        ));
        // --------------------------------
        // hakupalkki
        splitPaneTilauksetPaaIkkuna.setLeftComponent(panelTilauksetHaku);

        panelTilauksetHaku.setLayout(new BorderLayout(0, 0));

        panelTilauksetHaku.add(lblHakuTilaukset, BorderLayout.NORTH);
        splitPaneHakuTilaukset.setOrientation(JSplitPane.VERTICAL_SPLIT);

        panelTilauksetHaku.add(splitPaneHakuTilaukset, BorderLayout.CENTER);

        splitPaneHakuTilaukset.setLeftComponent(panelHakuApuTilaukset);
        panelHakuApuTilaukset.setLayout(new BorderLayout(0, 0));

        panelHakuApuTilaukset.add(comboBoxHakuTilaukset, BorderLayout.CENTER);
        comboBoxHakuTilaukset.setModel(new DefaultComboBoxModel(new String[] {
                "Tilaustunnus", "asiakastunnus", "Työnkuva" }));

        panelHakuApuTilaukset.add(textPaneKirjoitusTilaukset,
                BorderLayout.SOUTH);

        splitPaneHakuTilaukset.setRightComponent(listHakuTilaukset);
        listHakuTilaukset.setModel(new AbstractListModel() {
            private static final long serialVersionUID = 1L;
            String[] values = new String[] { "35", "67", "32", "83" };

            public int getSize() {
                return values.length;
            }

            public Object getElementAt(int index) {
                return values[index];
            }
        });

        // =========================================================================================================================================================
        // Laskutus-paneeli

        JPanel panelLaskutus = new JPanel();
        tabbedPane.addTab("Laskutus", null, panelLaskutus, null);
        panelLaskutus.setLayout(new BorderLayout(0, 0));

        panelLaskutus.add(splitPaneLaskutusPaaIkkuna, BorderLayout.CENTER);
        splitPaneLaskutusPaaIkkuna.setOneTouchExpandable(true);
        splitPaneLaskutusPaaIkkuna.setContinuousLayout(true);

        splitPaneLaskutusPaaIkkuna.setRightComponent(panel_4);
        panel_4.setLayout(new BorderLayout(0, 0));

        panel_4.add(lblLaskutus, BorderLayout.NORTH);

        panel_4.add(scrollPane_2, BorderLayout.CENTER);

        scrollPane_2.setViewportView(tableLaskutus);
        splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);

        tableLaskutus.setModel(new DefaultTableModel(new Object[][] {
                { new Integer(35), new Integer(9483), "7.2.2012", "Maalaus",
                        new Integer(7), "Ei" },
                { new Integer(67), new Integer(9483), "7.2.2012", "Leikkuu",
                        new Integer(72), "Ei" },
                { new Integer(83), new Integer(9483), "7.2.2012", "Maalaus",
                        new Integer(67), "Ei" },
                { new Integer(32), new Integer(9483), "7.2.2012", "Maalaus",
                        new Integer((int) 76.8), "Ei" },

        }, new String[] { "Tilaustunnus", "Asiakastunnus", "Eräpäivä",
                "Laskutettu", "Hinta", "Maksettu" }

        ));
        // --------------------------------
        // hakupalkki
        splitPaneLaskutusPaaIkkuna.setLeftComponent(panelLaskutusHaku);
        splitPaneLaskutusPaaIkkuna.setLeftComponent(panelLaskutusHaku);

        panelLaskutusHaku.setLayout(new BorderLayout(0, 0));

        panelLaskutusHaku.add(lblHakuLaskutus, BorderLayout.NORTH);
        splitPaneHakuLaskutus.setOrientation(JSplitPane.VERTICAL_SPLIT);

        panelLaskutusHaku.add(splitPaneHakuLaskutus, BorderLayout.CENTER);

        splitPaneHakuLaskutus.setLeftComponent(panelHakuApuLaskutus);
        panelHakuApuLaskutus.setLayout(new BorderLayout(0, 0));

        panelHakuApuLaskutus.add(comboBoxHakuLaskutus, BorderLayout.CENTER);
        comboBoxHakuLaskutus.setModel(new DefaultComboBoxModel(new String[] {
                "Tilaustunnus", "Asiakastunnus", "Laskutettu", "Eräpäivä",
                "Maksettu", }));

        panelHakuApuLaskutus.add(textPaneKirjoitusLaskutus, BorderLayout.SOUTH);

        splitPaneHakuLaskutus.setRightComponent(listHakuLaskutus);
        listHakuLaskutus.setModel(new AbstractListModel() {
            private static final long serialVersionUID = 1L;
            String[] values = new String[] { "35", "67", "32", "83" };

            public int getSize() {
                return values.length;
            }

            public Object getElementAt(int index) {
                return values[index];
            }
        });

        // =========================================================================================================================================================
        // Ylärivin toimintovalikot

        JPanel panelMenu = new JPanel();
        contentPane.add(panelMenu, BorderLayout.NORTH);
        panelMenu.setLayout(new BorderLayout(0, 0));

        panelMenu.add(menuPalkki);

        contentPane.add(panelNappulatAlarivi, BorderLayout.SOUTH);
        panelNappulatAlarivi.setLayout(new BorderLayout(0, 0));

        panelNappulatAlarivi.add(panelOikeaReunaNappula, BorderLayout.EAST);
        panelOikeaReunaNappula.setLayout(new BorderLayout(0, 0));
        btnTallenna.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                workworkSwing.tallenna();
            }
        });
        btnTallenna.setToolTipText("Tallentaa muokatut tiedot");

        panelOikeaReunaNappula.add(btnTallenna, BorderLayout.WEST);

        // =============================0

    }

}