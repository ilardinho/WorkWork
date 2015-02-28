package workworkGUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;

import workworkSwing.WorkWorkSwing;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author ilardinho
 * 10.2.2012
 */
public class TulostusDialogi extends JDialog {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();

    /**
     * Launch the application.
     * @param args 
     */
    public static void main(String[] args) {
        try {
            TulostusDialogi dialog = new TulostusDialogi();
            dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public TulostusDialogi() {
        setTitle("Tulosta tiedot...");
        setBounds(100, 100, 400, 600);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));
        {
            JTextArea tulostusTekstiArea = new JTextArea();
            tulostusTekstiArea.setText("Seppo Ismo" + " Metsätie 1" + " 12345"
                    + " Tampere" + " +12-12324");
            contentPanel.add(tulostusTekstiArea, BorderLayout.CENTER);
        }
        {
            JPanel painikePane = new JPanel();
            painikePane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(painikePane, BorderLayout.SOUTH);
            {
                JButton tulostaButton = new JButton("Tulosta");
                tulostaButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        WorkWorkSwing.tulostusOK();
                    }
                });
                tulostaButton.setActionCommand("Tulosta");
                painikePane.add(tulostaButton);
                getRootPane().setDefaultButton(tulostaButton);
            }
            {
                JButton peruutaButton = new JButton("Peruuta");
                peruutaButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        dispose();
                    }
                });
                peruutaButton.setActionCommand("Peruuta");
                painikePane.add(peruutaButton);
            }
        }
    }

    

}
