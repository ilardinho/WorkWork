package workworkGUI;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * @author ilardinho
 *
 */
public class TiedotPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private static final String VERSIO = "2.1";

    private static final String teksti = "<html>\n"
            + "<h1>J\u00C4SENREKISTERI</h1>\n"
            + "<p></p>\n"
            + "<p style=\"font-size:1.1em; text-align:center\">Versio "
            + VERSIO
            + "</p>\n"
            + "<p></p>\n"
            + "<p style=\"font-size:1.3em; text-align:center\"><i>Vesa Lappalainen</i></p>\n"
            + "</html>\n";

    /**
    * Create the panel.
    */
    public TiedotPanel() {
        setLayout(new BorderLayout(0, 0));

        JLabel labelTiedot = new JLabel(teksti);
        labelTiedot.setOpaque(true);
        labelTiedot.setBackground(new Color(255, 255, 153));
        labelTiedot.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelTiedot);

    }

}
