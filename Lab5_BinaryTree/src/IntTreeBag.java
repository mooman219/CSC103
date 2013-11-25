// File: IntTreeBag.java from the package edu.colorado.collections

// The implementation of most methods in this file is left as a student
// exercise from Section 9.5 of "Data Structures and Other Objects Using Java"

// Check with your instructor to see whether you should put this class in
// a package. At the moment, it is declared as part of edu.colorado.collections:
//package edu.colorado.collections;

//import edu.colorado.nodes.BTNode<Integer>; 

/******************************************************************************
 * This class is a homework assignment;
 * An <CODE>IntTreeBag</CODE> is a collection of int numbers.
 *
 * <dl><dt><b>Limitations:</b> <dd>
 *   Beyond <CODE>Integer.MAX_VALUE</CODE> elements, <CODE>countOccurrences</CODE>,
 *   and <CODE>size</CODE> are wrong. 
 *
 * <dt><b>Outline of Java Source Code for this class:</b><dd>
 *   <A HREF="../edu/colorado/collections/IntTreeBag.java">
 *   http://www.cs.colorado.edu/~main/edu/colorado/collections/IntTreeBag.java
 *   </A>
 *
 * <dt><b>Note:</b><dd>
 *   This file contains only blank implementations ("stubs")
 *   because this is a Programming Project for my students.
 *
 * @version
 *   Jan 24, 1999
 *
 * @see IntArrayBag
 * @see IntLinkedBag
 ******************************************************************************/
public class IntTreeBag implements Cloneable
{
    // Invariant of the IntTreeBag class:
    //   1. The elements in the bag are stored in a binary search tree.
    //   2. The instance variable root is a reference to the root of the
    //      binary search tree (or null for an empty tree).
    private BTNode<Integer> root;   


    /**
     * Insert a new element into this bag.
     * @param <CODE>element</CODE>
     *   the new element that is being inserted
     * <dt><b>Postcondition:</b><dd>
     *   A new copy of the element has been added to this bag.
     * @exception OutOfMemoryError
     *   Indicates insufficient memory a new BTNode<Integer>.
     **/
    //*********************************************************************
    public void add(int element)
    {
        BTNode<Integer> node = new BTNode<Integer>(element, null, null);
        if(root == null) {
            root = node;
        } else {
            BTNode<Integer> cursor = root;
            while(cursor != null) {
                if(cursor.getData().compareTo(element) > 0) {
                    if(cursor.getLeft() == null) {
                        cursor.setLeft(node);
                        cursor = null;
                    } else {
                        cursor = cursor.getLeft();
                    }
                } else if(cursor.getData().compareTo(element) < 0) {
                    if(cursor.getRight() == null) {
                        cursor.setRight(node);
                        cursor = null;
                    } else {
                        cursor = cursor.getRight();
                    }
                } else {
                    System.out.println("Duplicate Element");
                }
            }
        }
    }
    //*********************************************************************  


    /**
     * Remove one copy of a specified element from this bag.
     * @param <CODE>target</CODE>
     *   the element to remove from the bag
     * <dt><b>Postcondition:</b><dd>
     *   If <CODE>target</CODE> was found in the bag, then one copy of
     *   <CODE>target</CODE> has been removed and the method returns true. 
     *   Otherwise the bag remains unchanged and the method returns false. 
     **/
    //*********************************************************************
    public boolean remove(int target)
    {
        if(root != null) {
            boolean fromRight = false;
            BTNode<Integer> precursor = null;
            BTNode<Integer> cursor = root;
            while(cursor != null) {
                // This means the target is to the left of the current node.
                if(cursor.getData().compareTo(target) > 0) {
                    precursor = cursor;
                    cursor = cursor.getLeft();
                    fromRight = false;
                // This means the target is to the left of the current node.
                } else if(cursor.getData().compareTo(target) < 0) {
                    precursor = cursor;
                    cursor = cursor.getRight();
                    fromRight = true;
                // This statement means we have found our node.
                } else {
                    /*
                     * This checks if nothing came before the cursor and the
                     * cursor is a leaf. If it is, then we must be in a tree of
                     * one node and it is safe to remove the root.
                     */
                    if(precursor == null && cursor.isLeaf()) {
                        root = null;
                    /*
                     * Since we know something came before the cursor, if
                     * the cursor itself is a leaf, we remove the cursor
                     * from the precursor. The boolean fromRight keeps track
                     * of which node to remove from the precursor. It is
                     * used to avoid doing another compare.
                     */
                    } else if(cursor.isLeaf()) {
                        if(fromRight) {
                            precursor.setRight(null);
                        } else {
                            precursor.setLeft(null);
                        }
                    /*
                     * Now we know that there is a precursor and our cursor
                     * 
                     * isn't a leaf.
                     * For the last two conditional statements, we're
                     * looking for the best leaf to replace the data in the
                     * cursor.
                     * 
                     * If for example, the left node fromm the cursor is not
                     * null, we'll get the rightmost data from that node and
                     * replace it with the cursor's data. This will keep the
                     * tree in order without moving any nodes.
                     */
                    } else if(cursor.getLeft() != null) {
                        cursor.setData(cursor.getLeft().getRightmostData());
                        cursor.getLeft().removeRightmost();
                    } else {
                        cursor.setData(cursor.getRight().getLeftmostData());
                        cursor.getLeft().removeLeftmost();
                    }
                    return true;
                }
            }
        }
        return false;
    }

    //*********************************************************************    
    /**
     * Determine the number of elements in this bag.
     * @param - none
     * @return
     *   the number of elements in this bag
     **/ 
    //*********************************************************************                          
    public long size( )
    {
        return BTNode.treeSize(root);
    }
    //*********************************************************************
}

