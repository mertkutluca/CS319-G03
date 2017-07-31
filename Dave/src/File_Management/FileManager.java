package File_Management;

import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;

import javax.imageio.ImageIO;
 

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;

public class FileManager {
	
    private char[][] mapObjectList;
    private ArrayList<Integer> highScores;
public FileManager(int x, int y){
	mapObjectList=new char[9][50];
        highScores=new ArrayList();
}

public void readMapFile(String filePath) {
	FileReader fr;
    //Try-with-resource  
	try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
		for(int i=0;i<9;i++){
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
public void readHighScore(String filepath){
    FileReader fr;
    //Try-with-resource  
	try(BufferedReader br = new BufferedReader(new FileReader(filepath))){
		 
                for(int i=0;i<10;i++){
                    highScores.add(Integer.parseInt(br.readLine()));
                }
	}catch(FileNotFoundException e){
		//GameApplication.alertFileNotFound(filePath);
		System.out.println("FileNotFound");
	}catch(IOException e){
		//GameApplication.alertIOException(filePath);
		System.out.println("IOException");
	}
}
public void addHighScore(int score){
    highScores.add(score);
    Collections.sort(highScores);
    highScores.remove(0);
}
public void writeHighScores(String filepath) throws FileNotFoundException{
		BufferedWriter bw = null;
		FileWriter fw = null;
		PrintWriter writer = new PrintWriter(filepath);          
                writer.print("");
                writer.close();
		try {
			fw = new FileWriter(filepath);
			bw = new BufferedWriter(fw);
                        
			for(int i=0;i<10;i++){
				bw.write(String.valueOf(highScores.get(i))+"\n");
			}

			System.out.println("Done");

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
	
}
public void addScore(String filepath,int score) throws FileNotFoundException{
    readHighScore(filepath);
    addHighScore(score);
    writeHighScores(filepath);
}
public ArrayList<Integer> getHighScores(){
    return highScores;
}
public char[][] getMapObjects(){
	return mapObjectList;
}
}
