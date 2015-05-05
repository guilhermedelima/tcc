package br.unb.bayes;

import java.util.Scanner;

import ptstemmer.exceptions.PTStemmerException;
import br.unb.bayes.social.PoliticianType;
import br.unb.bayes.social.SocialNetworkClassifier;



public class BayesTest {

	public static void main(String[] args) throws PTStemmerException{
		SocialNetworkClassifier bayes;
		Scanner scan;
		String type, line;
		
		scan = new Scanner(System.in);
		
		type = scan.nextLine();
		
		bayes = new SocialNetworkClassifier(type.equals("dilma") ? PoliticianType.DILMA : PoliticianType.AECIO);
		
		while( scan.hasNext() ){
			line = scan.nextLine();
			
			System.out.println( bayes.getClassification(line).getName() );
		}
		
		scan.close();
	}

}
