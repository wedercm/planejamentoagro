package br.com.planejamentoagro.helper;

import java.io.File;

public class DiretoriosHelper {
	public static boolean deleteDirectory(File path) {
	    if( path.exists() ) {
	      File[] files = path.listFiles();
	      if (files == null) {
	          return true;
	      }
	      for(int i=0; i<files.length; i++) {
	         if(files[i].isDirectory()) {
	           deleteDirectory(files[i]);
	         }
	         else {
	           files[i].delete();
	         }
	      }
	    }
	    return( path.delete() );
	  }
	public static boolean renameFile(String oldFile, String newFile)
	{		
		File file = new File(oldFile);
		if(file.exists())
		{
			File file2 = new File(newFile);		
			return file.renameTo(file2);
		}else return false;
	}
}
