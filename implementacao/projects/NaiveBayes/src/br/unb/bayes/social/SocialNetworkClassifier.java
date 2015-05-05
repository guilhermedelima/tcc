package br.unb.bayes.social;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ptstemmer.Stemmer;
import ptstemmer.Stemmer.StemmerType;
import ptstemmer.exceptions.PTStemmerException;
import ptstemmer.support.PTStemmerUtilities;

import br.unb.bayes.util.IsFileReader;
import de.daslaboratorium.machinelearning.classifier.BayesClassifier;
import de.daslaboratorium.machinelearning.classifier.Classifier;

public class SocialNetworkClassifier {

	private static final Integer MAX_MEMORY = 2000;
	public static final String STOPWORDS_BASE = "/stopwords.txt";
	public static final String SAMPLEVOC_BASE = "/samplevoc.txt";
	public static final String ENTITIES_BASE = "/namedEntities.txt";

	private PoliticianType type;
	private Classifier<String, MessageType> classifier;
	private Stemmer stemmer;
	private Set<String> stopWords;

	public SocialNetworkClassifier(PoliticianType type) throws PTStemmerException{
		this.type = type;
		
		this.classifier = new BayesClassifier<String, MessageType>();
		this.classifier.setMemoryCapacity(MAX_MEMORY);
		
		this.stemmer = Stemmer.StemmerFactory(StemmerType.SAVOY);
		
		this.stopWords = new HashSet<String>();
		this.stopWords.addAll(IsFileReader.readFile(STOPWORDS_BASE));
		
		learnProcess();
	}
	
	public PoliticianType getType(){
		return type;
	}
	
	public List<String> Stemming(String sentence){
		List<String> stemmerTokens;
		String[] tokens;
		
		stemmerTokens = new ArrayList<String>();
		tokens = PTStemmerUtilities.removeDiacritics(sentence).toLowerCase().replaceAll("[@#?!,.]", " ").split("\\s+");
		
		for(String token : tokens){
			if( !stopWords.contains(token) && token.length() > 0 )
				stemmerTokens.add( stemmer.getWordStem(token) );
		}
		
		return stemmerTokens;
	}

	private void learnProcess(){

		List<String> positive;
		List<String> negative;
		
		positive = IsFileReader.readFile(type.getPositiveFile());
		negative = IsFileReader.readFile(type.getNegativeFile());

		for(String sentence : positive){
			classifier.learn(MessageType.POSITIVE, Stemming(sentence));
		}
		
		for(String sentence : negative){
			classifier.learn(MessageType.NEGATIVE, Stemming(sentence));
		}
	}

	public MessageType getClassification(String sentence){
		List<String> tokens = Stemming(sentence);
		
//		System.out.println(
//				((BayesClassifier<String, MessageType>)classifier).classifyDetailed(tokens).toString());

		return classifier.classify(tokens).getCategory();
	}
}
