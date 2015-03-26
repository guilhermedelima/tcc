package br.unb.bayes.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class IsFileReader {

	public static List<String> readFile(String fileName){

		BufferedReader reader;
		InputStream isFile;
		List<String> list;
		String line;

		try{

			list = new ArrayList<String>();

			isFile = IsFileReader.class.getResourceAsStream(fileName);
			reader = new BufferedReader(new InputStreamReader(isFile));

			while( (line = reader.readLine()) != null ){
				list.add(line);
			}

		}catch(IOException ex){
			System.out.println(ex.getMessage());
			return null;
		}

		return list; 
	}
}
