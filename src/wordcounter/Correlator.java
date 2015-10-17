package wordcounter;

/**
 * Elton Vinh and Thien Van
 * CS 146 Data Structures and Algorithms
 * Fall 2015
 * Department of Computer Science
 * San Jose State University
 * 
 * An executable that counts the words in a files and prints out the counts in
 * descending order. You will need to modify this file.
 */
public class Correlator {

	private static int totalCount1 = 0, totalCount2 = 0;
	private static double errorSum = 0;
	
    private static void correlator(DataCount<String>[] file1, DataCount<String>[] file2) {
        // sum up the total words
        for (DataCount<String> c : file1)
        {
        	totalCount1 += c.count;
        }
        for (DataCount<String> c : file2)
        {
        	totalCount2 += c.count;
        }
    	
        // calculate the difference
        for (DataCount<String> c1: file1)
    		if ((double) c1.count/totalCount1 <= 0.01 && (double) c1.count/totalCount1 >= 0.0001)
        	for (DataCount<String> c2: file2)
        		if ((double) c2.count/totalCount2 <= 0.01 && (double) c2.count/totalCount2 >= 0.0001)
        			if( c1.data.equals(c2.data) )
        			{
        				//System.out.println("Match with word: " + c1.data + " freq1 = " + (double) c1.count/totalCount1 
        				//		+ ", freq2 = " + (double) c2.count/totalCount2);
        				errorSum += Math.pow( (double) c1.count/totalCount1 - (double) c2.count/totalCount2, 2.);
        			}
        System.out.println("Error Sum is: " + errorSum);
    }

    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println(" Incorrect number of arguments");
            System.err.println(" Usage: ");
            System.err.println("\tjava Correlator  [ -b | -a | -h ] <filename1> <filename2>");
            System.exit(1);
        }
       
        DataCounter<String> dataStruct = null;
        DataCount<String>[] wordCounts1 = null;
        DataCount<String>[] wordCounts2 = null;
    
        if (args[0].compareTo("-b")==0) {
        	dataStruct = new BinarySearchTree<String>();
        	wordCounts1 = WordCount.countWords(dataStruct, args[1]);
        	wordCounts2 = WordCount.countWords(dataStruct, args[2]);
        }
        else if (args[0].compareTo("-a")==0) {
        	dataStruct = new BinarySearchTree<String>(); //replace with AVLTree once completed
        	wordCounts1 = WordCount.countWords(dataStruct, args[1]);
        	wordCounts2 = WordCount.countWords(dataStruct, args[2]);
        }
        else if (args[0].compareTo("-h")==0) {
        	dataStruct = new HashTable();
        	wordCounts1 = WordCount.countWords(dataStruct, args[1]);
        	wordCounts2 = WordCount.countWords(dataStruct, args[2]);
        }
        else {
            System.err.println("\tSaw "+args[0]+" instead of [ -b | -a | -h ] as first argument");
            System.exit(1);
        }
        
        correlator(wordCounts1, wordCounts2);
        
        //System.out.println("Total words in document 1: " + totalCount1);
        //System.out.println("Total words in document 2: " + totalCount2);
  
        
    }
}
