/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NaiveBayes;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author Pablo
 */
public class TextManagement {
    
    public List<Double> getProbabilities(List<String> words)
    {
        return null;
    }
    
    public List<String> tokenize(String text)
    {
        String[] words = text.split("\\W+");
        List<String> finalWords = cleanWords(words);
        return finalWords;
    }
    
    /**
     * Removes not important words for the classifier, such as conjunctions.
     * In this case, all 3 or less characters words will be removed.
     * @param words
     * @return 
     */
    private List<String> cleanWords(String[] words)
    {
        List<String> cleanedWords = new ArrayList();
        for (String word : words) {
            if (word.length() > 3)
                cleanedWords.add(word);
        }
        return cleanedWords;
    }
}
