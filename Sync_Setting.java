import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.Toolkit;

public class Sync_Setting extends JDialog {

	private final JPanel contentPanel = new JPanel();
    private final JButton right;
    private final JButton left;
    private final JButton c_right;
    private final JButton s_right;
    private final JButton c_left;
    private final JButton s_left;
    boolean v_right=false;
    boolean v_left=true;
    boolean modif=false;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Sync_Setting dialog = new Sync_Setting();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Sync_Setting() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Sync_Setting.class.getResource("/sync/icons/small setting.png")));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("Button.background"));
		panel.setBounds(10, 11, 414, 218);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblDirectionOfSynchronization = new JLabel("Direction of Synchronization :");
		lblDirectionOfSynchronization.setIcon(new ImageIcon(Sync_Setting.class.getResource("/sync/icons/arrows22.png")));
		lblDirectionOfSynchronization.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDirectionOfSynchronization.setBounds(10, 11, 280, 24);
		panel.add(lblDirectionOfSynchronization);
		
	    left = new JButton("");
		left.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modif=true;
				v_right=!(v_right);
				right.setEnabled(!(v_right));
				s_right.setEnabled(!(v_right));
				c_right.setEnabled(!(v_right));
			}
		});
		left.setIcon(new ImageIcon(Sync_Setting.class.getResource("/sync/icons/fast-forward333.png")));
		left.setBounds(20, 46, 39, 40);
		panel.add(left);
		
		right = new JButton("");
		
		right.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modif=true;
				left.setEnabled(!(v_left));
				s_left.setEnabled(!(v_left));
				c_left.setEnabled(!(v_left));
				v_left=!(v_left);
				
			}
		});
		right.setIcon(new ImageIcon(Sync_Setting.class.getResource("/sync/icons/arrows333333.png")));
		right.setBounds(338, 46, 39, 40);
		panel.add(right);
		
		JLabel lblLeftToRight = new JLabel("left to right");
		lblLeftToRight.setBounds(62, 56, 72, 24);
		panel.add(lblLeftToRight);
		
		JLabel lblRightToLeft = new JLabel("right to left");
		lblRightToLeft.setBounds(274, 56, 72, 24);
		panel.add(lblRightToLeft);
		
		JLabel label = new JLabel("Cat\u00E9gorie :");
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(10, 97, 72, 25);
		panel.add(label);
		
		JButton button_2 = new JButton("");
		button_2.setIcon(new ImageIcon(Sync_Setting.class.getResource("/sync/icons/cat_right_only.png")));
		button_2.setToolTipText("Cet \u00E9l\u00E9ment existe seulement a gauche");
		button_2.setBounds(20, 116, 39, 40);
		panel.add(button_2);
		
		JButton button_3 = new JButton("");
		button_3.setIcon(new ImageIcon(Sync_Setting.class.getResource("/sync/icons/clock.png")));
		button_3.setToolTipText("different last modified");
		button_3.setBounds(179, 116, 39, 40);
		panel.add(button_3);
		
		JButton button_4 = new JButton("");
		button_4.setIcon(new ImageIcon(Sync_Setting.class.getResource("/sync/icons/cat_left_only.png")));
		button_4.setToolTipText("Cet \u00E9l\u00E9ment existe seulement a droite");
		button_4.setBounds(338, 116, 39, 40);
		panel.add(button_4);
		
		JLabel lblAction = new JLabel("Action :");
		lblAction.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAction.setBounds(10, 167, 72, 25);
		panel.add(lblAction);
		
	    c_left = new JButton("");
		c_left.setIcon(new ImageIcon(Sync_Setting.class.getResource("/sync/icons/so_create_right.png")));
		c_left.setBounds(81, 168, 40, 39);
		panel.add(c_left);
		
		 s_left = new JButton("");
		s_left.setIcon(new ImageIcon(Sync_Setting.class.getResource("/sync/icons/so_delete_right.png")));
		s_left.setBounds(131, 168, 40, 39);
		panel.add(s_left);
		
	    s_right = new JButton("");
		s_right.setIcon(new ImageIcon(Sync_Setting.class.getResource("/sync/icons/so_delete_left.png")));
		s_right.setBounds(237, 168, 40, 39);
		panel.add(s_right);
		
		c_right = new JButton("");
		c_right.setIcon(new ImageIcon(Sync_Setting.class.getResource("/sync/icons/so_create_left.png")));
		c_right.setBounds(287, 167, 40, 39);
		panel.add(c_right);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setToolTipText("confirmer votre param\u00E9tres");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						DefaultTableModel model4;
						model4=(DefaultTableModel) Aplication.tab3.getModel();
					if(modif){	
						if(!(v_right)){
							Aplication.sup_droit.setEnabled(true);
							Aplication.droit_gauche.setEnabled(true);
							Aplication.sup_gauche.setEnabled(false);
							Aplication.gauche_droit.setEnabled(false);
							
						}
						else{
							Aplication.sup_gauche.setEnabled(true);
							Aplication.gauche_droit.setEnabled(true);
							Aplication.sup_droit.setEnabled(false);
							Aplication.droit_gauche.setEnabled(false);
						}
					}
					if(Aplication.gauche_droit.isEnabled()){
						for (int i=0;i<Aplication.tab3.getRowCount();i++){
							if(Aplication.tab3.getValueAt(i, 1).toString().equals("file:/C:/Users/DOC/workspace/Project/bin/sync/icons/so_update_right_small.png")){
								Aplication.tab3.setValueAt(Boolean.TRUE, i, 0);
							}
							else{
								Aplication.tab3.setValueAt(Boolean.FALSE, i, 0);
								Aplication.tab3.setValueAt(Boolean.FALSE, i, 2);
							}
						}
					}
					else{
						for (int i=0;i<Aplication.tab3.getRowCount();i++){
							if(Aplication.tab3.getValueAt(i, 1).toString().equals("file:/C:/Users/DOC/workspace/Project/bin/sync/icons/so_update_right_small.png")){
								Aplication.tab3.setValueAt(Boolean.FALSE, i, 0);
								Aplication.tab3.setValueAt(Boolean.FALSE, i, 2);
							}
							else{
								Aplication.tab3.setValueAt(Boolean.TRUE, i, 0);
							}
						}
					}
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