
import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Sync {
	
	
	//get le path d'un fichier
	public static StringBuilder geturl(String s) { 
		char [] ta = s.toCharArray();
            StringBuilder sb = new StringBuilder();
            sb.append(ta[0]);
            sb.append(ta[1]);
			 File f = new File(s);
			 int len = f.toPath().getNameCount();
            for(int i =0;i<len;i++){
	         sb.append("/"+f.toPath().getName(i));
             }
		return sb;
		}	
		
    //copy un fichier
	public static String C_file( StringBuilder dir, StringBuilder tar,String tf,String tf2 ){
		String fn=new File(tf).getName();
		StringBuilder sb = new StringBuilder();
		char [] t1 = dir.toString().toCharArray();
		char [] t2 = tf2.toCharArray();
	    sb=geturl(tf2);
		File f = new File(dir.toString());
		int len = f.toPath().getNameCount();
        for(int i =0;i<len;i++){
        	if(before(fn,f.toPath().getName(i).toString(),f)){
        	if((!f.toPath().getName(i).toString().equals(fn))){
        	sb.append("/"+f.toPath().getName(i));
        	}
        	}	
        }
        Path source = Paths.get(dir.toString());
        Path Target = Paths.get(sb.toString());
       	try {
			Files.copy(source, Target, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sb.toString();
		}
	
	//suprime un fichier
	public static void del_file(JTable tab ,JTable tab3) {
		
		for(int i =0;i<=tab.getRowCount()-1;i++){
   			if(tab3.getValueAt(i, 2).toString()=="true"){
   				if(!(tab.getValueAt(i, 0)==null)){
   				if(tab3.getValueAt(i, 0).toString()=="true"){
   					JOptionPane.showMessageDialog(null,"can not resolve this Action:\n"+"Copy/Delete this File"+new File(tab.getValueAt(i, 0).toString()).getName(),"Attention",0);
   				}
   				else{
   					Aplication.syn=true;
   					if(new File(tab.getValueAt(i, 0).toString()).isDirectory()){
   						del_rep(new File(tab.getValueAt(i, 0).toString()));
   					}
   					else{
   					StringBuilder f=Sync.geturl(tab.getValueAt(i, 0).toString());
   					
   					Path fd = Paths.get(f.toString());
   					try {
   					    Files.delete(fd);
   					 
   					} catch (NoSuchFileException x) {
   					    System.err.format("%s: no such" + " file or directory%n", fd);
   					} catch (DirectoryNotEmptyException x) {
   					    System.err.format("%s not empty%n", fd);
   					} catch (IOException x) {
   					    // File permission problems are caught here.
   					    System.err.println(x);
   					}
   				}
   				}
   			}	
   			}
   		}
	}
	
	//suprime un repertoire
	public static void del_rep(File f) {
		
		if(f.isDirectory()){
		if(f.listFiles()!= null){
			for(File f1:f.listFiles()){
				del_rep(f1);
			}	
		}
		StringBuilder url=Sync.geturl(f.toString());
		Path fd = Paths.get(url.toString());
			try {
			    Files.delete(fd);
			} catch (NoSuchFileException x) {
			    System.err.format("%s: no such" + " file or directory%n", fd);
			} catch (DirectoryNotEmptyException x) {
			    System.err.format("%s not empty%n", fd);
			} catch (IOException x) {
			    // File permission problems are caught here.
			    System.err.println(x);
			}
		}
		else{
		StringBuilder url=Sync.geturl(f.toString());
		Path fd = Paths.get(url.toString());
			try {
			    Files.delete(fd);
			} catch (NoSuchFileException x) {
			    System.err.format("%s: no such" + " file or directory%n", fd);
			} catch (DirectoryNotEmptyException x) {
			    System.err.format("%s not empty%n", fd);
			} catch (IOException x) {
			    // File permission problems are caught here.
			    System.err.println(x);
			}
	}
	}
	
	//copy un repertoire
	public static void Copy_dir( StringBuilder dir, StringBuilder tar,String tf,String tf2) {
		
		String fn=new File(tf).getName();
		StringBuilder sb = new StringBuilder();
		char [] t1 = dir.toString().toCharArray();
		char [] t2 = tf2.toCharArray();
	    sb=geturl(tf2);
		File f = new File(dir.toString());
		int len = f.toPath().getNameCount();
        for(int i =0;i<len;i++){
        	if(before(fn,f.toPath().getName(i).toString(),f)){
        	if((!f.toPath().getName(i).toString().equals(fn))){
        	sb.append("/"+f.toPath().getName(i));
        	}
        	}	
        }
        Path source = Paths.get(dir.toString());
        Path Target = Paths.get(sb.toString());
       	try {
			Files.copy(source, Target, StandardCopyOption.REPLACE_EXISTING);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   if(f.listFiles()!=null){
		   for(File f2:f.listFiles()){
			   if(!(f2.isDirectory())){
				   String s=f2.getAbsolutePath();
				   Path source1 = Paths.get(s);
			        Path Target1 = Paths.get(sb.toString()+"/"+f2.getName());  
			    	try {
						Files.copy(source1, Target1, StandardCopyOption.REPLACE_EXISTING);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			   }
			   else{
				   if(!(f2.getName().equals(new File(tf2).getName()))){
				   StringBuilder sb1= new StringBuilder(f2.toString());
				   Copy_dir( sb1,  tar, tf, tf2);
				   }
			   }
		   }
	   }
        
	}
	
	public static boolean before( String name,String bn,File f) {
		char[] t=f.toString().toCharArray();
		for(int i=0;i<f.toPath().getNameCount();i++){
			if(f.toPath().getName(i).toString().equals(name)){
			return true;	
			}
			if(f.toPath().getName(i).toString().equals(bn)){
				return false;	
				}
		}
		return true;
	}
	}
