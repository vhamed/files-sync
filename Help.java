import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.GridLayout;
import java.awt.CardLayout;

public class Help extends JDialog {
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Help dialog = new Help();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Help() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Help.class.getResource("/sync/icons/about.png")));
		setTitle("About");
		setBounds(100, 100, 583, 422);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("01-Choisis votre r\u00E9pertoires.");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 36, 226, 14);
		contentPanel.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(20, 61, 537, 64);
		contentPanel.add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Help.class.getResource("/sync/icons/selectionner.png")));
		panel.add(label);
		
		JLabel lblcompareThem = new JLabel("02-Comparer.");
		lblcompareThem.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblcompareThem.setBounds(10, 125, 125, 14);
		contentPanel.add(lblcompareThem);
		
		JLabel lblSelectSynchronizationSettings = new JLabel("03-Choisir votre parm\u00E9tres.");
		lblSelectSynchronizationSettings.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblSelectSynchronizationSettings.setBounds(10, 197, 301, 14);
		contentPanel.add(lblSelectSynchronizationSettings);
		
		JLabel lblPressSynchronizeTo = new JLabel("04-Taper Synchroniser. ");
		lblPressSynchronizeTo.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblPressSynchronizeTo.setBounds(10, 272, 274, 14);
		contentPanel.add(lblPressSynchronizeTo);
		
		JLabel lblBasicUsage = new JLabel("");
		lblBasicUsage.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBasicUsage.setBounds(10, 11, 85, 14);
		contentPanel.add(lblBasicUsage);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 150, 186, 39);
		contentPanel.add(panel_1);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(Help.class.getResource("/sync/icons/comp2.png")));
		panel_1.add(label_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 213, 47, 48);
		contentPanel.add(panel_2);
		panel_2.setLayout(new CardLayout(0, 0));
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(Help.class.getResource("/sync/icons/set_syn.png")));
		panel_2.add(label_2, "name_11271244187530");
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(10, 301, 186, 39);
		contentPanel.add(panel_3);
		panel_3.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon(Help.class.getResource("/sync/icons/syn2.png")));
		panel_3.add(label_3);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	
	public void printLines(int nbLines){
		for (int i = 0; i < nbLines; i++) {
			System.out.println("add lines to the code ");
		}
		return ;
	}
}
	
