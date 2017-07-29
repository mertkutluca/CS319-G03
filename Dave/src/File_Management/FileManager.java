package File_Management;

import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;

import javax.imageio.ImageIO;
 

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileManager {

	private char[][] mapObjectList;
public FileManager(int x, int y){
	mapObjectList=new char[6][50];
}

public void readMapFile(String filePath) {
	FileReader fr;
    //Try-with-resource  
	try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
		for(int i=0;i<6;i++){
			String str=br.readLine();
			for(int j=0;j<50;j++){
				 mapObjectList[i][j]=str.charAt(j);
			}
		}
	}catch(FileNotFoundException e){
		//GameApplication.alertFileNotFound(filePath);
		System.out.println("FileNotFound");
	}catch(IOException e){
		//GameApplication.alertIOException(filePath);
		System.out.println("IOException");
	}
}
public char[][] getMapObjects(){
	return mapObjectList;
}
}
