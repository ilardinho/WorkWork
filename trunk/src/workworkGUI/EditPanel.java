package workworkGUI;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Dimension;
import javax.swing.SwingConstants;

/**
 * @author ilardinho
 *
 */
public class EditPanel extends JPanel {
	
    /**
     * E uhi
     */
    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JLabel labelTieto = new JLabel();
   
    /**
     * Create the panel.
     * @param nimi nimikenttä
     */
    public EditPanel(String nimi) {
            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
           
            JLabel labelTieto = new JLabel(nimi);
            labelTieto.setHorizontalAlignment(SwingConstants.TRAILING);
            labelTieto.setPreferredSize(new Dimension(100, 14));
            add(labelTieto);
           
            JLabel fill = new JLabel("");
            fill.setPreferredSize(new Dimension(5, 0));
            add(fill);
           
            textField = new JTextField();
            textField.setMinimumSize(new Dimension(150, 20));
            add(textField);
            textField.setColumns(10);

    }

    /**
     * @return tekstin
     */
    public String getCaption() {
            return textField.getText();
    }
    /**
     * @param text asettaa tekstin
     */
    public void setCaption(String text) {
            textField.setText(text);
    }
    /**
     * @return dsta
     */
    public String getTextFieldText() {
            return textField.getText();
    }
    /**
     * @param text_1 sdg
     */
    public void setTextFieldText(String text_1) {
            textField.setText(text_1);
    }

/**
 * @param labelTieto kebab
 */
public void setLabelTieto(JLabel labelTieto) {
    this.labelTieto = labelTieto;
}

/**
 * @return rulla
 */
public JLabel getLabelTieto() {
    return labelTieto;
}


   
}