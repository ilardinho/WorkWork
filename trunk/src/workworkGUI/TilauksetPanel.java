package workworkGUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridLayout;

/**
 * @author ilardinho
 *
 */
public class TilauksetPanel extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Create the panel.
     */
    public TilauksetPanel() {
        setLayout(new GridLayout(1, 0, 0, 0));
        
        JLabel label_1 = new JLabel("Tilaustunnus");
        add(label_1);
        
        JLabel label_7 = new JLabel("Asiakastunnus");
        add(label_7);
        
        JLabel label_8 = new JLabel("Työosoite");
        add(label_8);
        
        JLabel label_2 = new JLabel("Työkuvaus");
        add(label_2);
        
        JLabel label = new JLabel("Hinta");
        add(label);
        
        JLabel label_6 = new JLabel("Laskutettu");
        add(label_6);
        
        JLabel label_3 = new JLabel("Maksettu");
        add(label_3);
        
        JLabel label_4 = new JLabel("Lisätiedot");
        add(label_4);
        
        JLabel label_5 = new JLabel("Saldo");
        add(label_5);

    }

}
