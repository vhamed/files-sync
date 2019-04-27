import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.SystemColor;

import javax.swing.border.EtchedBorder;
import javax.swing.UIManager;


public class Aplication extends JFrame implements ActionListener{
	static boolean syn=false;
	static boolean size=false;
	int sa;
	static public boolean change=true;
	static boolean C_dg;
	static boolean C_gd;
	JLabel fcoun_1;
	JLabel repcount_1;
	JLabel file_2;
	JLabel rep_2;
	protected static final String Nimbus_lf = "Nimbus";
	private boolean Allow_Row_Selection = true;
	private boolean Allow_Column_Selection = true;
	public LinkedList<File> listFilesSource=new LinkedList<>();
	public DefaultTableModel model = new DefaultTableModel();
	public DefaultTableModel model2 = new DefaultTableModel();
	public DefaultTableModel model3 = new DefaultTableModel();
	public DefaultTableModel model4 = new DefaultTableModel();	
	public Object[] col ={"Dossier relatif", "Nom", "Taille"};	
	public Object[] col2 ={"Dossier relatif", "Nom", "Taille"};
	public Object[] col3 ={"Copy", "No","Size"};
	public JFileChooser fc = new JFileChooser();
	public JFileChooser fc2 = new JFileChooser();
	public static JLabel sup_droit;
	public static  JLabel droit_gauche;
	public static JLabel gauche_droit;
	public static  JLabel sup_gauche;
	private JPanel contentPane;
	private JTextField tf;
	private JTextField tf2;
	static public JTable tab3;
	static public JTable tab;
	static public JTable tab2;
	public static LinkedList<File > files=new LinkedList<File>();
	public static LinkedList<String> types=new LinkedList<String>();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					Aplication frame = new Aplication();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame
	 * 
	 */
	public Aplication() {
		setTitle("Synchronisation");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 666, 545);
		this.setLocationRelativeTo(null);		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(SystemColor.control);
		setJMenuBar(menuBar);		
		JMenu mnNewMenu = new JMenu("Fichier");
		menuBar.add(mnNewMenu);		
		JMenuItem Nouveau = new JMenuItem("Effacer");
		//Clean text 
		Nouveau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tf.setText("");
				tf2.setText("");
				DefaultTableModel mode = new DefaultTableModel();
				DefaultTableModel mode2 = (DefaultTableModel) tab3.getModel();
				mode2.setRowCount(0);
				mode.setColumnIdentifiers(col);
				tab.setModel(mode);
				tab2.setModel(mode);
				tab3.setModel(mode2);
				int nbf=0;
    			int nbr=0;
    			file_2.setText(Integer.toString(nbf)+"File");
    			rep_2.setText(Integer.toString(nbr)+"Repertoire");
    			fcoun_1.setText(Integer.toString(nbf)+"File");
    			repcount_1.setText(Integer.toString(nbr)+"Repertoire");
			}
		});
		Nouveau.setIcon(new ImageIcon(Aplication.class.getResource("/sync/icons/gnome_edit_clear.png")));
		Nouveau.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK));
		mnNewMenu.add(Nouveau);
		JSeparator separator = new JSeparator();
		mnNewMenu.add(separator);
		JMenuItem Quiter = new JMenuItem("Quitter");
		//Exit App
		Quiter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		Quiter.setIcon(new ImageIcon(Aplication.class.getResource("/sync/icons/exit11.png")));
		mnNewMenu.add(Quiter);
		
		JMenu mnParamtres = new JMenu("Param\u00E9tres");
		menuBar.add(mnParamtres);
		
		JMenuItem mntmComparer = new JMenuItem("Comparer");
		mntmComparer.setIcon(new ImageIcon(Aplication.class.getResource("/sync/icons/compare_small.png")));
		mntmComparer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new cop_set().setVisible(true);
			}
		});
		mnParamtres.add(mntmComparer);
		
		JMenuItem mntmSynchroniser = new JMenuItem("Synchroniser");
		mntmSynchroniser.setIcon(new ImageIcon(Aplication.class.getResource("/sync/icons/synchronization-arrows-couple -small.png")));
		mntmSynchroniser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Sync_Setting().setVisible(true);
			}
		});
		mnParamtres.add(mntmSynchroniser);
		
		JMenu mnHelp = new JMenu("Aide");
		menuBar.add(mnHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		//About frame 
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Help().setVisible(true);
			}
		});
		mntmAbout.setIcon(new ImageIcon(Aplication.class.getResource("/sync/icons/about.png")));
		mnHelp.add(mntmAbout);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 11, 615, 54);
		contentPane.add(panel);
		panel.setLayout(null);
		JButton comparer = new JButton("Comparer");
		comparer.setToolTipText("Comparer");

		//Compare button Action 
		comparer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int nb_file=0;
    			int nb_rep=0;
    			file_2.setText(Integer.toString(nb_file)+"File");
    			rep_2.setText(Integer.toString(nb_rep)+"Repertoire");
    			fcoun_1.setText(Integer.toString(nb_file)+"File");
    			repcount_1.setText(Integer.toString(nb_rep)+"Repertoire");
				if(new File(tf.getText()).exists()&& new File(tf2.getText()).exists() ){
				model.setRowCount(0);
				model2.setRowCount(0);
				model4=(DefaultTableModel) tab3.getModel();
				model4.setRowCount(0);
				tab.setModel(model);
				tab2.setModel(model2);
				tab3.setModel(model4);
				int ts;
				Object [] row2 =  new Object[3] ;
				File file=new File(tf.getText());
				LinkedList<File> fs=new LinkedList<File>();
				File file2=new File(tf2.getText());
				LinkedList<File> fs2=new LinkedList<>();
                if(tf.getText().equals("")||tf2.getText().equals("")){
                	JOptionPane.showMessageDialog(null,"select the files you want to compare ","Attention",0);
                }
                else{
				if (tf.getText().equals(tf2.getText())){
					JOptionPane.showMessageDialog(null,"you selected the same file :"+tf.getText()+"\n"+tf2.getText(),"Attention",0);
					}
					else{
			
					for(File f:file.listFiles()){
					if(!(f.getName().equals(new File(tf2.getText()).getName()))){	
		  
					      fs.add(f);
						
						 }
					}
					tab.setModel(CustomModel.getModel(fs, file2, model));
					int si1=tab.getRowCount();
					sa=si1;
					  for(int i =1;i<=tab.getRowCount();i++){
			        	  row2[0]= null;
				          row2[1]=null;
				          row2[2]= null;
				          model2.addRow(row2);
			        }
					  tab2.setModel(model2);  
					fs.clear();
					for(File f2:file2.listFiles()){
						if(!(f2.getName().equals(new File(tf.getText()).getName()))){	
						fs2.add(f2);
						}
						}
					tab2.setModel( CustomModel.getModel(fs2,file,model2));
					fs2.clear();
					  if(tab.getRowCount()<=tab2.getRowCount()){
				        	ts=tab2.getRowCount();
				        }
				        else{
				        	ts=tab.getRowCount();
				        }
				        for(int i =0;i<=ts-1;i++){
				        	Object [] row =  new Object[4] ;
				        	if(i<si1){
				        		if(gauche_droit.isEnabled()){			        	     
				        		         row[0]= Boolean.TRUE;
				        		         }
				        	else{
				        		row[0]= Boolean.FALSE;
				        		}
								         row[1]= new ImageIcon(Aplication.class.getResource("/sync/icons/so_update_right_small.png"));
						                 row[2]=Boolean.FALSE;
						                 row[3]=new ImageIcon(Aplication.class.getResource("/sync/icons/sup_v.png"));       
				        	}
				        	else{
				        		if(gauche_droit.isEnabled()){			        	     
			        		         row[0]= Boolean.FALSE;
			        		         }
			        	else{
			        		row[0]= Boolean.TRUE;
			        		}
									row[1]= new ImageIcon(Aplication.class.getResource("/sync/icons/so_update_left_small.png"));
							        row[2]=Boolean.FALSE;
							        row[3]=new ImageIcon(Aplication.class.getResource("/sync/icons/sup_b.png")) ;	
				        		}
						        model4.addRow(row);
					        	}
				       
				        tab3.setModel(model4);
					    tab3.addMouseListener(new MouseAdapter() {
					    	  public void mouseClicked(MouseEvent e) {
					    		    if (e.getClickCount() !=0) {
					    		      JTable target = (JTable)e.getSource();
					    		      int row = target.getSelectedRow();
					    		      int col = target.getSelectedColumn();
					    		      if(col==0){
					    		    	  if(tab3.getValueAt(row, col)==Boolean.TRUE){
					    		    		  tab3.setValueAt(Boolean.FALSE, row, 2);
					    		    	  }
					    		      }
					    		      else{
					    		    	  if(col==2){
					    		    		  if(tab3.getValueAt(row, col)==Boolean.TRUE){
						    		    		  tab3.setValueAt(Boolean.FALSE, row, 0);
						    		    	  } 
					    		    	  } 
					    		      }					    		   
					    		    }
					    		  }
					    		});
					     
					        
					   
				        Object [] ro =  new Object[3] ;
				        DefaultTableModel model5 =(DefaultTableModel) tab.getModel();
				        for(int i=0;i<tab2.getRowCount();i++){
				        	if(!(i<tab.getRowCount())){
				        	ro[0]=null;
				        	ro[1]=null;
				        	ro[2]=null;
				        model5.addRow(ro);
				        	}
				        }
				        tab.setModel(model5);
				if(!(tab.getRowCount()==0)){
					int nbf=0;
					int nbr=0;
					fcoun_1.setVisible(true);
					repcount_1.setVisible(true);
					for(int i=0;i<tab.getRowCount();i++){
						if(!(tab.getValueAt(i, 0)==null)){	
						if(new File(tab.getValueAt(i, 0).toString()).isDirectory()){
							nbr++;
						}
						else{
							nbf++;
						}
						}
					}
					fcoun_1.setText(Integer.toString(nbf)+"File");
					repcount_1.setText(Integer.toString(nbr)+"Repertoire");
				}
				
				if(!(tab2.getRowCount()==0)){
					int nbf=0;
					int nbr=0;
					file_2.setVisible(true);
					rep_2.setVisible(true);
					for(int i=0;i<tab2.getRowCount();i++){
					if(!(tab2.getValueAt(i, 0)==null)){	
						if(new File(tab2.getValueAt(i, 0).toString()).isDirectory()){
							nbr++;
						}
						else{
							nbf++;
						}
					}
					file_2.setText(Integer.toString(nbf)+"File");
					rep_2.setText(Integer.toString(nbr)+"Repertoire");
				}
				}
				}
		}
                }
                else{JOptionPane.showMessageDialog(null,"vous avez sélectionné des faux fichiers","Attention",0);}
		}
		}
		
				);
		comparer.setIcon(new ImageIcon(Aplication.class.getResource("/sync/icons/105690211025884527.png")));
		comparer.setFont(new Font("Segoe UI", Font.BOLD, 16));
		comparer.setBounds(26, 11, 186, 39);
		panel.add(comparer);
		
		JButton btnSynchronise = new JButton("Synchroniser");
		btnSynchronise.setToolTipText("Synchroniser");
		
		//Sync button action
		btnSynchronise.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int taile1= tab.getRowCount();
				int taile2= tab2.getRowCount();
				if ((taile1==0)&&(taile2==0)){
					JOptionPane.showMessageDialog(null,"aucun fichier à Synchroniser ");	
					}
			   	else{
			   		if(gauche_droit.isEnabled()){
			   		for(int i =0;i<tab.getRowCount();i++){
			   			if(tab3.getValueAt(i, 0).toString()=="true"){
			   		      if(!(tab.getValueAt(i, 0)==null)){		   				
			   			 String s = tab.getValueAt(i, 0).toString();
						 String s2 =tf2.getText();
						 File f = new File(s);
					     String fn=f.getName();
						 StringBuilder sour = Sync.geturl(s);
						 String t=tf2.getText();
                         t=Sync.C_file(new StringBuilder(s), new StringBuilder(tf2.getText()), tf.getText(),tf2.getText());
						 StringBuilder tar = Sync.geturl(t);
				         if((f.isDirectory())){
				        	 syn=true;
				        	 Sync.Copy_dir(sour, tar,tf.getText(),tf2.getText());
				         }
				         else{
				        	 Path sourc = Paths.get(sour.toString());
				        	 Path Target = Paths.get(tar.toString());
				        	
				        	 if(t==tf2.getText()){
				        		 Target = Paths.get(tar.toString()+"/"+fn);
				        		                 }
							 
				        	try {
								Files.copy(sourc, Target, StandardCopyOption.REPLACE_EXISTING);
								syn=true;
								
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				     		
				         }
				        }
			   		}
			   		
			   		}
			   		}
			   		if(droit_gauche.isEnabled()){
					for(int i =0;i<tab2.getRowCount();i++){
			   			if(tab3.getValueAt(i, 0).toString()=="true"){
			   		      if(!(tab2.getValueAt(i, 0)==null)){		   				
			   			 String s = tab2.getValueAt(i, 0).toString();
						 String s2 =tf.getText();
						 File f = new File(s);
					     String fn=f.getName();
						 StringBuilder sour = Sync.geturl(s);
						 String t=tf.getText();
						 t=Sync.C_file(new StringBuilder(s), new StringBuilder(tf.getText()), tf2.getText(),tf.getText());
						 StringBuilder tar = Sync.geturl(t);
				         if((f.isDirectory())){
				        	 syn=true;
				        	 Sync.Copy_dir(sour, tar,tf2.getText(),tf.getText());
				         }
				         else{
				        	 Path sourc = Paths.get(sour.toString());
				        	 Path Target = Paths.get(tar.toString());
				        	
				        	 if(t==tf.getText()){
				        		 Target = Paths.get(tar.toString()+"/"+fn);
				        		                 }
							 
				        	try {
								Files.copy(sourc, Target, StandardCopyOption.REPLACE_EXISTING);
								syn=true;
								
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				     		
				         }
				        }
			   		}
			   		
			   		}
			   	}	
			   		if(sup_gauche.isEnabled()){
			   		Sync.del_file(tab, tab3);
			   		}
			   		else{
			   		Sync.del_file(tab2, tab3);
			   		}
			   		if(syn==true){
						JOptionPane.showMessageDialog(null,"Synchronisation est terminer avec success ","Information",1);
				        }
				        else{
				        	JOptionPane.showMessageDialog(null,"la synchronisation est Non implémenté","Information",1);	
				        }
						
						syn=false;
			   		}
		        
				}
			}
			   	
						
				
		);
		btnSynchronise.setIcon(new ImageIcon(Aplication.class.getResource("/sync/icons/synchronization-arrows-couple22.png")));
		btnSynchronise.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnSynchronise.setBounds(405, 11, 186, 39);
		panel.add(btnSynchronise);
		
		JButton button_3 = new JButton("");
		
		//Sync setting button action
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Sync_Setting().setVisible(true);
			}
		});
		button_3.setToolTipText("Synchronisation Param\u00E9tres");
		button_3.setIcon(new ImageIcon(Aplication.class.getResource("/sync/icons/settings-work-tool22.png")));
		button_3.setBounds(326, 11, 40, 39);
		panel.add(button_3);		
		
		JButton button = new JButton("");
		//paramétre de comparé button action
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			new cop_set().setVisible(true);	
			}
		});
		button.setIcon(new ImageIcon(Aplication.class.getResource("/sync/icons/settings-work-tool1.png")));
		button.setToolTipText("Comparaison Param\u00E9tres");
		button.setBounds(242, 11, 40, 39);
		panel.add(button);
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(10, 76, 615, 60);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton Change = new JButton("");
		Change.setToolTipText("permuter les direction");
		//Change button Action 
		Change.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String s1=file_2.getText();
				String s2=rep_2.getText();
				file_2.setText(fcoun_1.getText());
				rep_2.setText(repcount_1.getText());
				repcount_1.setText(s2);
				fcoun_1.setText(s1);
				Object [] r =  new Object[4] ;
				DefaultTableModel mo2 = new DefaultTableModel();
				mo2=(DefaultTableModel) tab3.getModel();
				mo2.setRowCount(0);
				DefaultTableModel mo = new DefaultTableModel();
				String s = tf.getText();
				tf.setText(tf2.getText());
				tf2.setText(s);
				mo=(DefaultTableModel) tab2.getModel();
				tab2.setModel(tab.getModel());
				tab.setModel(mo);
				for(int i=0;i<tab.getRowCount();i++){
					if (change){	
						if(i<sa){
							if(!sens()){
								r[0]= Boolean.TRUE;
							}else{
								r[0]= Boolean.FALSE;
							}
							r[1]= new ImageIcon(Aplication.class.getResource("/sync/icons/so_update_left_small.png"));
							r[2]=Boolean.FALSE;
							r[3]=new ImageIcon(Aplication.class.getResource("/sync/icons/sup_b.png")) ;	
						}else{
							if(sens())
								r[0]= Boolean.TRUE;
							else
								r[0]= Boolean.FALSE;
							r[1]= new ImageIcon(Aplication.class.getResource("/sync/icons/so_update_right_small.png"));
							r[2]=Boolean.FALSE;
							r[3]=new ImageIcon(Aplication.class.getResource("/sync/icons/sup_v.png"));
						}
					}else{
						if(i>=sa){
							if(!sens())
								r[0]= Boolean.TRUE;
							else
								r[0]= Boolean.FALSE;
							r[1]= new ImageIcon(Aplication.class.getResource("/sync/icons/so_update_left_small.png"));
							r[2]=Boolean.FALSE;
					        r[3]=new ImageIcon(Aplication.class.getResource("/sync/icons/sup_b.png")) ;	
						}else{
							if(sens())
								r[0]= Boolean.TRUE;
							else
								r[0]= Boolean.FALSE;
							r[1]= new ImageIcon(Aplication.class.getResource("/sync/icons/so_update_right_small.png"));
							r[2]=Boolean.FALSE;
							r[3]=new ImageIcon(Aplication.class.getResource("/sync/icons/sup_v.png"));
						}	
					}
					mo2.addRow(r);
				}
				change=!(change);
			}
		});
		Change.setIcon(new ImageIcon(Aplication.class.getResource("/sync/icons/exchange-arrows.png")));
		Change.setBounds(282, 8, 43, 49);
		panel_1.add(Change);
		
		JLabel lab1 = new JLabel("Glisser & D\u00E9poser");
		lab1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lab1.setBounds(59, 8, 114, 14);
		panel_1.add(lab1);
		
		//Parcourir button action 
		JButton Parcourir2 = new JButton("Parcourir");
		Parcourir2.setToolTipText("S\u00E9lectionner le 2eme dossier");
		Parcourir2.setFont(new Font("Segoe UI", Font.BOLD, 12));
		Parcourir2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fc2.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fc2.setApproveButtonText("Select Folder");
				if(fc2.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
					tf2.setText(fc2.getSelectedFile().getAbsolutePath());
				}
			}
		});
		Parcourir2.setBounds(488, 25, 96, 23);
		panel_1.add(Parcourir2);
		
		JLabel lblDragDrob = new JLabel("Glisser & D\u00E9poser");
		lblDragDrob.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblDragDrob.setBounds(362, 8, 125, 14);
		panel_1.add(lblDragDrob);
		
		JButton btnBrowse = new JButton("Parcourir");
		btnBrowse.setToolTipText("S\u00E9lectionner le 1ere dossier");
		btnBrowse.setFont(new Font("Segoe UI", Font.BOLD, 12));
		
		//Browse button action
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fc.setApproveButtonText("Select Folder");
				if(fc.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
					tf.setText(fc.getSelectedFile().getAbsolutePath());
				}
			}
		});
		btnBrowse.setBounds(176, 25, 96, 23);
		panel_1.add(btnBrowse);
		
		tf = new JTextField();
		tf.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		tf.setBackground(Color.WHITE);
		tf.setBounds(20, 28, 153, 20);
		panel_1.add(tf);
		tf.setColumns(10);	
		tf2 = new JTextField();
		tf2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		tf2.setColumns(10);
		tf2.setBounds(335, 26, 153, 20);
		panel_1.add(tf2);
		model2.setColumnIdentifiers(col2);
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(10, 147, 237, 280);
		contentPane.add(panel_2);
		panel_2.setLayout(new GridLayout(1, 0, 0, 0));
		
		JScrollPane sp = new JScrollPane();
		sp.setFont(new Font("Segoe UI", Font.BOLD, 12));
		panel_2.add(sp);
		
		tab = new JTable();
		tab.setShowGrid(false);
		tab.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Relative folder", "Name", "Size"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		sp.setViewportView(tab);
		model.setColumnIdentifiers(col);
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(403, 147, 237, 280);
		contentPane.add(panel_3);
		panel_3.setLayout(new GridLayout(1, 0, 0, 0));
		
		JScrollPane sp2 = new JScrollPane();
		sp2.setFont(new Font("Segoe UI", Font.BOLD, 12));
		panel_3.add(sp2);
		tab2 = new JTable();
		tab2.setShowGrid(false);
		tab2.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Relative folder", "Name", "Size"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		sp2.setViewportView(tab2);
	    sp.setVerticalScrollBarPolicy(sp.VERTICAL_SCROLLBAR_NEVER);sp.getVerticalScrollBar().setModel(sp2.getVerticalScrollBar().getModel());	    
	    model3=new DefaultTableModel();
	    model3.setColumnIdentifiers(new String[]{"Delete", "Image", "Copy"});		
	    
	    tab3=new JTable(new DefaultTableModel(
		    	new Object[][] {
		    	},
		    	new String[] {
		    		"Copy File", "", "Delete File", ""
		    	}
		    ) {
		    	Class[] columnTypes = new Class[] {
		    		Boolean.class, ImageIcon.class, Boolean.class, ImageIcon.class
		    	};
		    	public Class getColumnClass(int columnIndex) {
		    		return columnTypes[columnIndex];
		    	}
		    });
	    tab3.setColumnSelectionAllowed(true);
	    tab3.setCellSelectionEnabled(true);
	    tab3.setVisible(true);
	    tab3.setShowGrid(false);
	    model4=(DefaultTableModel) tab3.getModel();
	    JPanel panel_4 = new JPanel();
	    panel_4.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	    panel_4.setBounds(248, 147, 150, 280);
	    contentPane.add(panel_4);
	    panel_4.setLayout(new GridLayout(1, 0, 0, 0));
	    
	    JScrollPane sp3 = new JScrollPane();
	    sp3.setViewportView(tab3);
	    sp3.setVerticalScrollBarPolicy(sp3.VERTICAL_SCROLLBAR_NEVER);sp3.getVerticalScrollBar().setModel(sp2.getVerticalScrollBar().getModel());
	    panel_4.add(sp3);
	    
	    gauche_droit = new JLabel("");
	    gauche_droit.setToolTipText("copy/create les fichier \u00E0 partir de gauche a droite ");
	    gauche_droit.setBounds(232, 431, 40, 39);
	    contentPane.add(gauche_droit);
	    gauche_droit.setIcon(new ImageIcon(Aplication.class.getResource("/sync/icons/so_create_right.png")));
	    
	    sup_gauche = new JLabel("");
	    sup_gauche.setToolTipText("Suppression de fichiers \u00E0 partir de repertoire gauche");
	    sup_gauche.setBounds(275, 431, 40, 39);
	    contentPane.add(sup_gauche);
	    sup_gauche.setIcon(new ImageIcon(Aplication.class.getResource("/sync/icons/so_delete_right.png")));
	    
	    sup_droit = new JLabel("");
	    sup_droit.setToolTipText("Suppression de fichiers \u00E0 partir de repertoire droite");
	    sup_droit.setEnabled(false);
	    sup_droit.setBounds(317, 431, 40, 39);
	    contentPane.add(sup_droit);
	    sup_droit.setIcon(new ImageIcon(Aplication.class.getResource("/sync/icons/so_delete_left.png")));
	    
	    droit_gauche = new JLabel("");
	    droit_gauche.setToolTipText("copy/create les fichier \u00E0 partir de droite a gauche ");
	    droit_gauche.setEnabled(false);
	    droit_gauche.setBounds(358, 431, 40, 39);
	    contentPane.add(droit_gauche);
	    droit_gauche.setIcon(new ImageIcon(Aplication.class.getResource("/sync/icons/so_create_left.png")));
	    
	    JPanel panel_5 = new JPanel();
	    panel_5.setBackground(UIManager.getColor("Button.background"));
	    panel_5.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	    panel_5.setBounds(10, 431, 215, 44);
	    contentPane.add(panel_5);
	    panel_5.setLayout(null);
	    
	    fcoun_1 = new JLabel("");
	    fcoun_1.setIcon(new ImageIcon(Aplication.class.getResource("/sync/icons/sheet.png")));
	    fcoun_1.setBounds(10, 11, 74, 22);
	    fcoun_1.setVisible(false);
	    panel_5.add(fcoun_1);
	    
	    repcount_1 = new JLabel("");
	    repcount_1.setIcon(new ImageIcon(Aplication.class.getResource("/sync/icons/folder.png")));
	    repcount_1.setBounds(94, 11, 130, 22);
	    repcount_1.setVisible(false);
	    panel_5.add(repcount_1);
	    
	    JPanel panel_6 = new JPanel();
	    panel_6.setBackground(UIManager.getColor("Button.background"));
	    panel_6.setLayout(null);
	    panel_6.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	    panel_6.setBounds(401, 431, 224, 44);
	    contentPane.add(panel_6);
	    
	    file_2 = new JLabel("");
	    file_2.setIcon(new ImageIcon(Aplication.class.getResource("/sync/icons/sheet.png")));
	    file_2.setBounds(10, 11, 74, 22);
	    file_2.setVisible(false);
	    panel_6.add(file_2);
	    
	    rep_2 = new JLabel("");
	    rep_2.setIcon(new ImageIcon(Aplication.class.getResource("/sync/icons/folder.png")));
	    rep_2.setBounds(94, 11, 130, 22);
	    rep_2.setVisible(false);
	    panel_6.add(rep_2);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
	}
	
	public static boolean sens(){
		return gauche_droit.isEnabled();
	}
		
	
}