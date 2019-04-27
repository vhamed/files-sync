
import java.io.File;
import java.io.FileFilter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

import javax.sound.midi.Instrument;
import javax.swing.table.DefaultTableModel;

public class CustomModel {
	static Object [] rows =  new Object[3] ;
	static Object [] rows1 =  new Object[4] ;
	public static DefaultTableModel getModel(LinkedList<File > listFiles,File file2,DefaultTableModel model){  
		for (File f : listFiles) {
			if (f.isDirectory()){
				if( ( Belong(f,file2)==false)){    //cette repertoire n'existe pas dans le 2eme repertoire
					rows[0]=f.getAbsolutePath();
					rows[1]=f.getName();
					if(!(f.length()==0)){   
						rows[2]=f.length();
					}
					else{
						rows[2]=null;
					}
					model.addRow(rows);
				}
				else{ //cette rep est existe deja dans le 2eme rep
					LinkedList<File> fs=new LinkedList<File>();//mettre le contenu de répertoire dans une list
					if (f.listFiles()!=null) {
						for (File f1 : f.listFiles()) {								
						fs.add(f1);							
						}
					}
					getModel(fs,file2,model);
				}
			
			}
			else{
				if(Aplication.size){ // comparison les fichiers par leur taille
				if(!(modif2(f,file2))){	
					rows[0]=f.getAbsolutePath();
					rows[1]=f.getName();  
					rows[2]=f.length();
					model.addRow(rows);
				}
				}
				else{ // comparison les fichiers par leur date
					if(!(modif(f,file2))){	
						rows[0]=f.getAbsolutePath();
						rows[1]=f.getName();  
						rows[2]=f.length();
						model.addRow(rows);
					}	
				}
			}
		}
		return model;
	}
	
	//vérifier si le repertoire est existe ou pas
	public static boolean Belong(File file1,File file2 ){
		boolean in = false;
		for(File f : file2.listFiles()){
			if(f.getName().equals(file1.getName())){
				return true;
			}
		}
		if(in==false){
			for(File f2 : file2.listFiles()){
				if(f2.isDirectory()){
					if(f2.listFiles()!= null){
						in=Belong(file1,f2);
						if(in==true){
							return true;
						}
					}
				
				}
			}
		}
		return in;
	}
	//vérifier si la date de folder est changé ou non et si le fichier est existe ou pas
	public static boolean modif(File f,File file ){
		boolean val= false;
			for(File f1 : file.listFiles()){
				if(!f1.isDirectory()){
	        	if(f.getName().equals(f1.getName())){
	        		if(f.lastModified()==f1.lastModified()){
	        			val= true;
	        			break;
	        		}
        		else{
	        			val= false; 
	        		}
	        	}
			}
			}
			if(val==false){
				for(File f2 : file.listFiles()){
					if(f2.isDirectory()){
						if(f2.listFiles()!= null){
						val=modif(f,f2);
						if(val==true){
							break;
						}
						}
					}
				}	
			}

		return val;
	}
	
	
	
	
	
	//vérifier si la taille de folder est changé ou non et si le fichier est existe ou pas
	public static boolean modif2(File f,File file ){
		boolean val= false;
			for(File f1 : file.listFiles()){
				if(!f1.isDirectory()){
	        	if(f.getName().equals(f1.getName())){
	        		if(f.length()==f1.length()){
	        			val= true;
	        			break;
	        		}
        		else{
	        			val= false; 
	        		}
	        	}
			}
			}
			if(val==false){
				for(File f2 : file.listFiles()){
					if(f2.isDirectory()){
						if(f2.listFiles()!= null){
						val=modif2(f,f2);
						if(val==true){
							break;
						}
						}
					}
				}	
			}

		return val;
	}

}
