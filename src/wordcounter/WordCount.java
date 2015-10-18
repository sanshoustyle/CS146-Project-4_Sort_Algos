package wordcounter;

import java.io.IOException;

/**
 * Elton Vinh and Thien Van
 * CS 146 Data Structures and Algorithms
 * Fall 2015
 * Department of Computer Science
 * San Jose State University
 * 
 * An executable that counts the words in a files and prints out the counts in
 * descending order.
 */
public class WordCount {

	private static int totalCount = 0;
	
    protected static DataCount<String>[] countWords(DataCounter<String> dataStruct, String file) {
        DataCounter<String> counter = dataStruct;

        try {
            FileWordReader reader = new FileWordReader(file);
            String word = reader.nextWord();
            while (word != null) {
                counter.incCount(word);
                word = reader.nextWord();
            }
        } catch (IOException e) {
            System.err.println("Error processing " + file + e);
            System.exit(1);
        }

        @SuppressWarnings("unchecked")
		DataCount<String>[] counts = (DataCount<String>[]) counter.getCounts();
        //sortByDescendingCount(counts);
        
        // sum up the total words
        for (DataCount<String> c : counts)
        {
        	totalCount += c.count;
        }
        
        return counts;
    }
    
    // only print relevant words

    private static void printSortedWords(DataCount<String>[] counts) {

    	for (DataCount<String> c : counts)
        {
        	System.out.println(c.count + " \t" + c.data);
        }
    	System.out.println("Unique words: " + counts.length);
    }
    /**
     * TODO Replace this comment with your own.
     * 
     * Sort the count array in descending order of count. If two elements have
     * the same count, they should be in alphabetical order (for Strings, that
     * is. In general, use the compareTo method for the DataCount.data field).
     * 
     * This code uses insertion sort. You should modify it to use a different
     * sorting algorithm. NOTE: the current code assumes the array starts in
     * alphabetical order! You'll need to make your code deal with unsorted
     * arrays.
     * 
     * The generic parameter syntax here is new, but it just defines E as a
     * generic parameter for this method, and constrains E to be Comparable. You
     * shouldn't have to change it.
     * 
     * @param counts array to be sorted.
     */
    private static <E extends Comparable<? super E>> void insertionSort(
            DataCount<E>[] counts) {
    	int k, pos, arraySize;
    	DataCount<E> tmp;
    	
    	arraySize = counts.length;
    	for ( pos = 1; pos < arraySize; pos++ )
    	{
    		tmp = counts[pos];
    		for ( k = pos; k > 0 && tmp.compareTo(counts[k-1]) < 0; k-- )
    			counts [k] = counts[k-1];
    		counts[k] = (DataCount<E>) tmp;
    	}
    }

    // protected insertionSort that works on sub-arrays. (For quickSort method)
    protected static < E extends Comparable< ? super E > >
    void insertionSort(DataCount<E>[] counts, int start, int stop)
    {
    	int k, pos;
    	DataCount<E> tmp;
    	
    	// we are not testing for ranges to keep times down - private so ok
    	for( pos = start + 1; pos <= stop; pos++ )
    	{
    		tmp = counts[pos];
    		for(k = pos; k > 0 && tmp.compareTo(counts[k-1]) < 0; k-- )
    			counts[k] = counts[k-1];
    		counts[k] = tmp;
    	}
    }
    
    // QuickSort and helpers --------------------------
    // median3 sorts counts[left], counts[center], and counts[right]
    // it leaves the smallest in counts[left], the largest in counts[right]
    // and median (the pivot) is moved "out-of-the-way" in counts[right-1].
    // (counts[center] has what used to be in counts[right-1]
    protected static < E extends Comparable< ? super E > >
    DataCount<E> median3(DataCount<E>[] counts, int left, int right)
    {
    	int center;
    	DataCount<E> tmp;
    	
    	// swaps are done in-line for speed; each compound line in a swap
    	center = (left + right) / 2;
    	if(counts[center].compareTo(counts[left]) < 0)
    	{	
    		tmp = counts[center]; counts[center] = counts[left]; counts[left] = tmp;	
    	}
    	if(counts[right].compareTo(counts[left]) < 0)
    	{	
    		tmp = counts[right]; counts[right] = counts[left]; counts[left] = tmp;	
    	}
    	if(counts[center].compareTo(counts[left]) < 0)
    	{	
    		tmp = counts[right]; counts[right] = counts[center]; counts[center] = tmp;	
    	}
    	
    	tmp = counts[center]; counts[center] = counts[right-1]; counts[right-1] = tmp;
    	
    	return counts[right-1];
    }
    
    protected static int QS_RECURSION_LIMIT = 15;
    
    public static boolean setRecursionLimit(int newLim)
    {
    	if (newLim < 2 || newLim > 1000)
    		return false;
    	QS_RECURSION_LIMIT = newLim;
    	return true;
    }
    
    private static <E extends Comparable<? super E>> void quickSort(
            DataCount<E>[] counts) 
    {
        quickSort(counts, 0, counts.length - 1 );
    }
    
    protected static <E extends Comparable<? super E>> void quickSort(
    		DataCount<E>[] counts, int left, int right)
    {
    	DataCount<E> pivot, tmp;
    	int i, j;
    	
    	if( left + QS_RECURSION_LIMIT <= right )
    	{
    		pivot = median3(counts, left, right);
    		for( i = left, j = right - 1; ; )
    		{
    			while( counts[++i].compareTo(pivot) < 0 )
    				;
    			while( pivot.compareTo(counts[--j]) < 0 )
    				;
    			if ( i < j )
    			{
    				tmp = counts[i]; counts[i] = counts[j]; counts[j] = tmp;
    			}
    			else
    				break;
    		}
    		
    		// restore pivot
    		tmp = counts[i]; counts[i] = counts[right - 1]; counts[right - 1] = tmp;
    		
    		// recursive calls on smaller sub-groups
    		quickSort(counts, left, i - 1);
    		quickSort(counts, i + 1, right);
    	}
    	else
    		// non-recursive escape value - insertion sort
    		insertionSort(counts, left, right);
    }
    
    // mergesort and helpers
    // input array 1: clent[leftPos] ... client[rightPos-1]
    // input array 2: client[rightPos] ... client[rightStop]
    // working[] array supplied by client to avoid local allocation
    protected static < E extends Comparable< ? super E > >
    void merge( DataCount<E>[] client, E[] working,
    		int leftPos, int rightPos, int rightStop)
    {
    	int leftStop, workingPos, arraySize;
    	
    	workingPos = leftPos;
    	leftStop = rightPos - 1;
    	arraySize = rightStop - leftPos + 1;
    	
    	// as soon as we reach the end of either input array, stop
    	while(leftPos <= leftStop && rightPos <= rightStop)
    		if(client[leftPos].compareTo(client[rightPos]) < 0 )
    			working[workingPos++] = client[leftPos++].data;
    		else
    			working[workingPos++] = client[rightPos++].data;
    	
    	// merge is over; copy the remainder of one or the other input array
    	while(leftPos <= leftStop)
    		working[workingPos++] = client[leftPos++].data;
    	while(rightPos <= rightStop)
    		working[workingPos++] = client[rightPos++].data;
    	
    	// copy back into client array
    	for( ; arraySize > 0; arraySize--, rightStop-- )
    		client[rightStop].data = working[rightStop];
    }
    
    // mergesort internal function
    protected static < E extends Comparable< ? super E > >
    void mergeSort(DataCount<E>[] counts, E[] working, int start, int stop)
    {
    	int rightStart;
    	
    	if ( stop - start < 1 )
    		return;
    	
    	rightStart = (start + stop)/2 + 1;
    	mergeSort(counts, working, start, rightStart - 1);
    	mergeSort(counts, working, rightStart, stop);
    	merge(counts, working, start, rightStart, stop);
    }
    
    // mergesort driver
	private static <E extends Comparable<? super E>> void mergeSort(
            DataCount<E>[] counts) {
    	if ( counts.length < 2 )
    		return;
    	
		@SuppressWarnings("unchecked")
		E[] working = (E[])new Comparable[counts.length];
    	mergeSort(counts, working, 0, counts.length - 1);
    }
    
    public static void main(String[] args) {
    	
        if (args.length != 3) {
            System.err.println(" Incorrect number of arguments");
            System.err.println(" Usage: ");
            System.err.println("\tjava WordCount  [ -b | -a | -h ] [ -is | -qs | -ms ] <filename>");
            System.exit(1);
        }
       
        DataCounter<String> dataStruct = null;
        DataCount<String>[] wordCounts = null;
    
        if (args[0].compareTo("-b")==0) {
        	dataStruct = new BinarySearchTree<String>();
        	wordCounts = countWords(dataStruct, args[2]);
        }
        else if (args[0].compareTo("-a")==0) {
        	dataStruct = new AvlTree<String>();
        	wordCounts = countWords(dataStruct, args[2]);
        }
        else if (args[0].compareTo("-h")==0) {
        	dataStruct = new HashTable();
        	wordCounts = countWords(dataStruct, args[2]);
        }
        else {
            System.err.println("\tSaw "+args[0]+" instead of [ -b | -a | -h ] as first argument");
            System.exit(1);
        }
        
        if (args[1].compareTo("-is")==0) {
        	insertionSort(wordCounts);
        }
        else if (args[1].compareTo("-qs")==0) {
        	quickSort(wordCounts);
        }
        else if (args[1].compareTo("-ms")==0) {
        	mergeSort(wordCounts);
        }
        else {
            System.err.println("\tSaw "+args[0]+" instead of [ -is | -qs | -ms ] as second argument");
            System.exit(1);
        }
        
        //printSortedWords(wordCounts);
    }
}
