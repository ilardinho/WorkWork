package workworkGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JLabel;

/**
 * @author ilardinho
 *
 */
public class Paaikkuna extends JFrame {

	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
	private final JPanel panelNappulat = new JPanel();
	private final JSplitPane keskustaTaustasplitPane = new JSplitPane();
	private final JPanel hakuTaustapanel = new JPanel();
	private final JSplitPane oikeaTietoIkkunasplitPane = new JSplitPane();
	private final JPanel hakuYlapanel = new JPanel();
	private final JScrollPane vasenHakuListascrollPane = new JScrollPane();
	private final JList list = new JList();
	private final JPanel henkiloTiedotpanel = new JPanel();
	private final JPanel tilauksetpanel = new JPanel();
	private final JLabel asiakasTiedotlabel = new JLabel("Asiakastiedot");
	private final JLabel tilausTiedotlabel = new JLabel("Tilaustiedot");
	private final JScrollPane tilauksetscrollPane = new JScrollPane();
	private final JSplitPane splitPane = new JSplitPane();
	private final JScrollPane scrollPane = new JScrollPane();
	private final JPanel panel = new JPanel();
	private final JPanel boxpanel = new JPanel();
	private final EditPanel editPanelNimi = new EditPanel(("Nimi"));
	private final EditPanel editPanelKatuosoite = new EditPanel(("Katuosoite"));
	private final EditPanel editPanelPostinumero = new EditPanel(("Postinumero"));
	private final EditPanel editPanelPostitoimipaikka = new EditPanel(("Postitoimipaikka"));
	private final EditPanel editPanelPuhelin = new EditPanel(("Puhelin"));
	private final JPanel panel_1 = new JPanel();
	private final EditPanel editPanelAtunnus = new EditPanel(("Asiakastunnus"));
	private final EditPanel editPanelYritysNimi = new EditPanel(("Yritys"));
	private final EditPanel editPanelYritysKatuosoite = new EditPanel(("Yrityksen osoite"));
	private final EditPanel editPanelYritysPostinro = new EditPanel(("Postinumero"));
	private final EditPanel editPanelYritysPostitoimipaikka = new EditPanel(("Postitoimipaikka"));
	private final EditPanel editPanelYritysPuhelin = new EditPanel(("Puhelin"));
	private final EditPanel editPanelSpostiOsoite = new EditPanel(("Sähköposti"));

	/**
	 * Launch the application.
	 * @param args 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Paaikkuna frame = new Paaikkuna();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Paaikkuna() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		contentPane.add(panelNappulat, BorderLayout.SOUTH);
		
		contentPane.add(keskustaTaustasplitPane, BorderLayout.CENTER);
		hakuTaustapanel.setMaximumSize(new Dimension(50, 50));
		hakuTaustapanel.setMinimumSize(new Dimension(100, 100));
		
		keskustaTaustasplitPane.setLeftComponent(hakuTaustapanel);
		hakuTaustapanel.setLayout(new BorderLayout(0, 0));
		hakuYlapanel.setMinimumSize(new Dimension(50, 50));
		
		hakuTaustapanel.add(hakuYlapanel, BorderLayout.NORTH);
		hakuYlapanel.setLayout(new BoxLayout(hakuYlapanel, BoxLayout.Y_AXIS));
		
		hakuTaustapanel.add(vasenHakuListascrollPane, BorderLayout.CENTER);
		
		vasenHakuListascrollPane.setViewportView(list);
		oikeaTietoIkkunasplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		
		keskustaTaustasplitPane.setRightComponent(oikeaTietoIkkunasplitPane);
		henkiloTiedotpanel.setMinimumSize(new Dimension(100, 100));
		
		oikeaTietoIkkunasplitPane.setLeftComponent(henkiloTiedotpanel);
		henkiloTiedotpanel.setLayout(new BorderLayout(0, 0));
		
		henkiloTiedotpanel.add(asiakasTiedotlabel, BorderLayout.NORTH);
		
		henkiloTiedotpanel.add(scrollPane, BorderLayout.CENTER);
		
		scrollPane.setViewportView(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		panel.add(boxpanel, BorderLayout.NORTH);
		boxpanel.setLayout(new BoxLayout(boxpanel, BoxLayout.Y_AXIS));
		editPanelNimi.setCaption("Terve\r\n");
		
		editPanelNimi.setTextFieldText("Ismo Ismola");
		boxpanel.add(editPanelNimi);
		boxpanel.add(editPanelKatuosoite);
		boxpanel.add(editPanelPostinumero);
		
		boxpanel.add(editPanelPostitoimipaikka);
		
		boxpanel.add(editPanelPuhelin);
		
		boxpanel.add(editPanelAtunnus);
		
		boxpanel.add(editPanelYritysNimi);
		
		boxpanel.add(editPanelYritysKatuosoite);
		
		boxpanel.add(editPanelYritysPostinro);
		
		boxpanel.add(editPanelYritysPostitoimipaikka);
		
		boxpanel.add(editPanelYritysPuhelin);
		
		boxpanel.add(editPanelSpostiOsoite);
		
		oikeaTietoIkkunasplitPane.setRightComponent(tilauksetpanel);
		tilauksetpanel.setLayout(new BorderLayout(0, 0));
		
		tilauksetpanel.add(tilausTiedotlabel, BorderLayout.NORTH);
		
		tilauksetpanel.add(tilauksetscrollPane, BorderLayout.CENTER);
		
		tilauksetscrollPane.setViewportView(panel_1);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		
		contentPane.add(splitPane, BorderLayout.NORTH);
	}

}
