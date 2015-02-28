package workworkGUI;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import workworkSwing.WorkWorkSwing;

import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import workworkGUI.Main;

/**
 * @author Ilari Jalli
 * versiooni 10.2.2012
 */
public class MenuPalkki extends JPanel {

    /**
     * Menupalkin numero yksi
     */
    private static final long serialVersionUID = 1L;

    private final Action actionUusiAsiakas = new ActionUusiAsiakas();
    private final Action actionUusiTilaus = new ActionUusiTilaus();
//    private final Action actionPoistaAsiakas = new ActionPoistaAsiakas();
    private final Action actionPoistaTilaus = new ActionPoistaTilaus();

    /**
     * Menupalkki
     */
    public MenuPalkki() {
        setLayout(new BorderLayout(0, 0));

        JMenuBar tiedostoAvaa = new JMenuBar();
        tiedostoAvaa.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(tiedostoAvaa, BorderLayout.WEST);

        JMenu menuTiedosto = new JMenu("Tiedosto");
        menuTiedosto.setHorizontalAlignment(SwingConstants.TRAILING);
        tiedostoAvaa.add(menuTiedosto);

        JMenuItem menuLisaaAsiakas = new JMenuItem("Lis‰‰ asiakas");
        menuLisaaAsiakas.setAction(actionUusiAsiakas);
        menuLisaaAsiakas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
                InputEvent.CTRL_MASK));
        menuTiedosto.add(menuLisaaAsiakas);
        JMenuItem menuLisaaTilaus = new JMenuItem("Lis‰‰ tilaus");
        menuLisaaTilaus.setAction(actionUusiTilaus);
        menuLisaaTilaus.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,
                InputEvent.CTRL_MASK));
        menuTiedosto.add(menuLisaaTilaus);

        JMenuItem menuPoista = new JMenuItem("Poista asiakas");
//        menuPoista.setAction(actionPoistaAsiakas);
        menuPoista.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
        menuPoista.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
               getWorkworkSwing().poistaAsiakas();
            }
        });
        menuTiedosto.add(menuPoista);
        JMenuItem menuPoistaTilaus = new JMenuItem("Poista Tilaus");
        menuPoistaTilaus.setAction(actionPoistaTilaus);
        menuPoistaTilaus.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
        menuPoistaTilaus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
               getWorkworkSwing().poistaTilaus();
            }
        });
        menuTiedosto.add(menuPoistaTilaus);
        JMenuItem menuTulosta = new JMenuItem("Tulosta");
        menuTulosta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                WorkWorkSwing.tulosta();
            }
        });
        menuTiedosto.add(menuTulosta);

        JMenuItem menuLopeta = new JMenuItem("Lopeta");
        menuLopeta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                getWorkworkSwing().lopeta();
            }
        });
        menuTiedosto.add(menuLopeta);

        // ----------------------------------

        JMenu menuOhje = new JMenu("Ohje");
        menuOhje.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                WorkWorkSwing.ohje();
            }
        });
        menuOhje.setHorizontalAlignment(SwingConstants.TRAILING);
        tiedostoAvaa.add(menuOhje);

        // ----------------------------------

        JMenu menuTietoja = new JMenu("Tietoja");
        menuTietoja.setHorizontalAlignment(SwingConstants.TRAILING);
        tiedostoAvaa.add(menuTietoja);

        JMenuItem menuVersio = new JMenuItem("Versio");
        menuVersio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                WorkWorkSwing.versio();
            }
        });
        menuTietoja.add(menuVersio);

      
    }

    private class ActionUusiAsiakas extends AbstractAction {
        private static final long serialVersionUID = 1L;

        public ActionUusiAsiakas() {
            putValue(NAME, "Uusi asiakas");
            putValue(SHORT_DESCRIPTION, "Lis‰‰ uuden asiakkaan");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            getWorkworkSwing().lisaaAsiakas();

        }
    }

    private class ActionUusiTilaus extends AbstractAction {
        private static final long serialVersionUID = 1L;

        public ActionUusiTilaus() {
            putValue(NAME, "Uusi tilaus");
            putValue(SHORT_DESCRIPTION, "Lis‰‰ tilauksen");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            getWorkworkSwing().lisaaTilaus();

        }
    }
//
//    private class ActionPoistaAsiakas extends AbstractAction {
//        private static final long serialVersionUID = 1L;
//
//        public ActionPoistaAsiakas() {
//putValue(NAME, "Poista asiakas");
//putValue(SHORT_DESCRIPTION, "Poistaa valitun asiakkaan");
//        }
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            getWorkworkSwing().poistaAsiakas();
//            
//        }
//
//    }
    private class ActionPoistaTilaus extends AbstractAction {
        private static final long serialVersionUID = 1L;

        public ActionPoistaTilaus() {
putValue(NAME, "Poista tilaus");
putValue(SHORT_DESCRIPTION, "Poistaa valitun tilauksen");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            getWorkworkSwing().poistaTilaus();
            
        }

    }

    /**
     * @return jepjep
     */
    public WorkWorkSwing getWorkworkSwing() {
        return Main.workworkSwing;
    }
}
