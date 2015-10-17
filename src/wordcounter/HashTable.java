package wordcounter;

import java.util.LinkedList;

/**
 * @author Ron Mak edited by Elton Vinh and Thien Van
 * CS 146 Data Structures and Algorithms
 * Fall 2015
 * Department of Computer Science
 * San Jose State University
 * 
 * Stub code for an implementation of a DataCounter that uses a hash table as
 * its backing data structure. We included this stub so that it's very clear
 * that HashTable works only with Strings, whereas the DataCounter interface is
 * generic.  You need the String contents to write your hashcode code.
 */
public class HashTable implements DataCounter<String> {
    /**
     * Inner class that holds key and value data to be put into hash table.
     * Also holds the count of the value to be used in WordCount and DataCounter implementations
     */
	private class Cell {
		private String value;
		private String key;
		private int count;
		
		public Cell(String value) {
			this.count = 1;
			this.value = value;
			this.key = value;
		}

		public String getValue() {
			return this.value;
		}
		public String getkey() {
			return this.key;
		}
		public int getCount() {
			return count;
		}
		public void incCount() {
			this.count++;;
		}
	}
	
	static final int INIT_TABLE_SIZE = 7919;	// with 97, times are similar BST. 7919 faster
	static final double INIT_MAX_LAMBDA = 1.5;
	
	private LinkedList<Cell>[] table;
	private int count;
	private int tableSize;
	private double maxLambda;
	

	public HashTable() {
	   this(INIT_TABLE_SIZE);
	}
	
	public HashTable(int size) {
		count = 0;
		if (size < INIT_TABLE_SIZE)
			tableSize = INIT_TABLE_SIZE;
		else
			tableSize = nextPrime(size);
		
		intializeTable();
		maxLambda = INIT_MAX_LAMBDA;
	}
	
	@SuppressWarnings("unchecked")
	private void intializeTable() {
		table = new LinkedList[tableSize];
		for(int i = 0;i<tableSize;i++) {
			if(table[i] == null)
				table[i] = new LinkedList<Cell>();
		}
	}
	
	/** {@inheritDoc} */
	public DataCount<String>[] getCounts() {
		@SuppressWarnings("unchecked")
		DataCount<String>[] counter =  new DataCount[count];
    	int k = 0;
    	for(int i = 0; i < table.length; i++) {
    		if(!table[i].isEmpty()) {
    			for(int j = 0;j < table[i].size();j++) {
    				counter[k]= new DataCount<String>(table[i].get(j).getValue(), table[i].get(j).getCount());
    				k++;
    			}
    		}
    	}

        return counter;
    }

    /** {@inheritDoc} */
    public int getSize() {
        // TODO Auto-generated method stub
    	return count;
    }

    /** {@inheritDoc} */
    public void incCount(String data) {
        // TODO Auto-generated method stub
    	Cell newCell = new Cell(data);
		int index = -1;

		if(contains(newCell.getkey())) {
			newCell = get(newCell.getkey());
			newCell.incCount();
		}
		else {
			index = hashingFunction(newCell.getkey());
			table[index].add(newCell);
			count++;
		}
		
		if ( count > maxLambda * tableSize )
			reSize();
		
    }

    public Boolean contains(String key) {
		Boolean found = false;
    	int index = hashingFunction(key);
		
    	for(int i = 0; i < table[index].size(); i++) {
			if(table[index].get(i).getkey().equals(key)) {
				found = true;
			}
		}
			
		return found;
    }
	
    public Cell get(String key) {
		int index = hashingFunction(key);
		Cell targetValue = null;
		for(int i = 0; i < table[index].size(); i++) { //searching linked list at index for key value
			if(table[index].get(i).getkey().equals(key)) {
				targetValue = table[index].get(i);
				break;
			}
		}
		return targetValue;
	}
	
    private int hashingFunction(String key)
	{
		int index;
		
		index = _hashingFunction(key) % tableSize;
		if (index < 0 )
			index += tableSize;
		
		return index;
	}
	
	// helper method to calculate hash
	private int _hashingFunction( String key )
	{
		int index = 0;
		char [] val = key.toCharArray();
		
		for ( int i = 0; i < key.length(); i++ )
			index = 31 * index + val[i];
		
		return index;
	}

	private void reSize()
	{
		// save old list and size then can allocate freely
		LinkedList<Cell>[] oldTable = table;
		int oldTableSize = tableSize;
		
		tableSize = nextPrime(2*oldTableSize);
		
		// allocate a larger, empty array
		intializeTable();
		
		// use the reinsert() algorithm to re-enter old data
		for (int i = 0; i < oldTableSize; i++ )
			for ( int j = 0; j < oldTable[i].size(); j++)
				reInsert( oldTable[i].get(j) );
	}

    //helper function to re-insert data into new table
    private void reInsert(Cell cell) {
		int index = hashingFunction(cell.getkey());
		table[index].add(cell);
    }
    
	public boolean setMaxLambda( double lam )
	{
		if ( lam < .1 || lam > 100.)
			return false;
		maxLambda = lam;
		return true;
	}

	private static int nextPrime( int n )
	{
		int k, candidate, loopLim;
		
		// loop doesn't work for 2 or 3
		if ( n <= 2 )
			return 2;
		else if ( n == 3 )
			return 3;
		
		for ( candidate = (n%2 == 0)? n+1 : n ; true ; candidate += 2)
		{
			// all primes > 3 are of the form 6k +/- 1
			loopLim = (int)( (Math.sqrt((double)candidate) + 1)/6);
			
			// we know it is odd. check for divisibility by 3
			if ( candidate % 3 == 0)
				continue;
			
			// now we can check for divisibility by 6k +/1 1 up to sqrt
			for ( k = 1; k <= loopLim; k++ )
			{
				if (candidate % (6*k - 1) == 0)
					break;
				if (candidate % (6*k + 1) == 0)
					break;
			}
			if ( k > loopLim)
				return candidate;
		}
	}

}
