package wordcounter;
/**
 * @author Elton Vinh and Thien Van
 * CS 146 Data Structures and Algorithms
 * Fall 2015
 * Department of Computer Science
 * San Jose State University
 * 
 * Stub code for an implementation of a DataCounter that uses a AVL Tree as
 * its backing data structure. Inherits from BinarySearchTree.
 * 
 */
public class AvlTree<E extends Comparable< ? super E > >
extends BinarySearchTree<E>
{
	// public methods of AVL Tree	
	public AvlTree() 
	{
        super();
	}
    
	public int heightOf(BSTNode a)
	{
		return a == null ? -1 : a.height;
	}
    
    // need to override incCount to include the "check and adjust balance" algorithm
    public void incCount(E data) { 
    	overallRoot = _incCount(data, overallRoot);
    }
    
    private BSTNode _incCount(E x, BSTNode t)
    {
        if( t == null )
            t = new BSTNode( x );
        
        else if( x.compareTo( t.data ) < 0 )
        {
            t.left = (_incCount( x, t.left ));
            if( heightOf( t.left ) - heightOf( t.right ) == 2 )
                if( x.compareTo( t.getLeft().data ) < 0 )
                    t = rotateWithLeftChild( t );
                else
                    t = doubleWithLeftChild( t );
        }
        else if( x.compareTo( t.data ) > 0 )
        {
            t.right = (_incCount( x, t.right ));
            if( heightOf( t.right ) - heightOf( t.left ) == 2 )
                if( x.compareTo( t.right.data ) > 0 )
                    t = rotateWithRightChild( t );
                else
                    t = doubleWithRightChild( t );
        }
        else
        	t.count++;  // Duplicate; increase count for data
        
        t.height = (Math.max( heightOf( t.left ), heightOf( t.right) ) ) + 1;
        
        return t;
	}
    
 // rotation algorithms implemented from Project 2 (Thien)
 	protected BSTNode rotateWithLeftChild(
 			BSTNode k2 )
 	{
 		BSTNode k1 = k2.left;
 		k2.left = k1.right;
 		k1.right = k2;
 		k2.height = ( Math.max( heightOf(k2.left), heightOf(k2.right) ) + 1);
 		k1.height = ( Math.max( heightOf(k1.left), k2.height ) + 1);
 		
 		//System.out.println("Single left rotation: " + k2.data);
 		
 		return k1;
 	}
 	
 	protected BSTNode rotateWithRightChild(
 			BSTNode k2 )
 	{
 		BSTNode k1 = k2.right;
 		k2.right = k1.left;
 		k1.left = k2;
 		k2.height =  Math.max( heightOf(k2.left), heightOf(k2.right) ) + 1;
 		k1.height = Math.max( heightOf(k1.right), k2.height ) + 1;
 		
 		//System.out.println("Single right rotation: " + k2.data);
 		
 		return k1;
 	}
 	
 	protected BSTNode doubleWithLeftChild(
 			BSTNode k3 )
 	{
 		//System.out.println("Double left right rotation starting: " + k3.data);
 		
 		k3.left = rotateWithRightChild(k3.left);
 		return rotateWithLeftChild(k3);
 	}
 	
 	protected BSTNode doubleWithRightChild(
 			BSTNode k3 )
 	{
 		//System.out.println("Double right left rotation starting: " + k3.data);

 		k3.right = rotateWithLeftChild(k3.right);
 		return rotateWithRightChild(k3);
 	}
    
}
