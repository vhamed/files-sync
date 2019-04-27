import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class cop_set extends JDialog {
    static boolean chang=Aplication.size;
    static JLabel tex;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			cop_set dialog = new cop_set();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public cop_set() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(cop_set.class.getResource("/sync/icons/105690211025884527.png")));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblChoisirUneVariante = new JLabel("Choisir une variante :");
		lblChoisirUneVariante.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblChoisirUneVariante.setBounds(10, 28, 128, 14);
		contentPanel.add(lblChoisirUneVariante);
		
		final JLabel date = new JLabel("");
		date.setIcon(new ImageIcon(cop_set.class.getResource("/sync/icons/clock.png")));
		date.setBounds(10, 53, 39, 35);
		date.setEnabled(!chang);
		contentPanel.add(date);
		
		final JLabel size = new JLabel("");
		size.setEnabled(false);
		size.setIcon(new ImageIcon(cop_set.class.getResource("/sync/icons/cmpByContent.png")));
		size.setBounds(10, 109, 39, 35);
		size.setEnabled(chang);
		contentPanel.add(size);
		
		JButton btnNewButton = new JButton("Date du fichier");
		btnNewButton.setToolTipText("reconna\u00EEtre les fichiers identiques \u00E0 l'aide leur date");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!chang){
					size.setEnabled(true);
					date.setEnabled(false);
					tex.setText("Reconnaître les fichiers identiques à l'aide de leur taille.");
				}
				else{
					size.setEnabled(false);
					date.setEnabled(true);
					tex.setText("Reconnaître les fichiers identiques à l'aide de leur date.");
	
				}
				chang=!chang;
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(59, 63, 121, 23);
		contentPanel.add(btnNewButton);
		
		JButton btnTailleDuFichier = new JButton("Taille du fichier");
		btnTailleDuFichier.setToolTipText("reconna\u00EEtre les fichiers identiques \u00E0 l'aide leur taille");
		btnTailleDuFichier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!chang){
					size.setEnabled(true);
					date.setEnabled(false);
					tex.setText("Reconnaître les fichiers identiques à l'aide de leur taille.");
				}
				else{
					size.setEnabled(false);
					date.setEnabled(true);
					tex.setText("Reconnaître les fichiers identiques à l'aide de leur date.");
				}
				chang=!chang;
			}
		});
		btnTailleDuFichier.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnTailleDuFichier.setBounds(59, 121, 121, 23);
		contentPanel.add(btnTailleDuFichier);
		
	   tex = new JLabel("Reconna\u00EEtre les fichiers identiques \u00E0 l'aide de leur taille et de leur date.");
		tex.setFont(new Font("Tahoma", Font.BOLD, 11));
		tex.setBounds(10, 190, 414, 14);
		contentPanel.add(tex);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setToolTipText("confirmer votre variante");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Aplication.size=size.isEnabled();
					
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setToolTipText("annuler");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
