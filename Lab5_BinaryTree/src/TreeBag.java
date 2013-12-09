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
public class TreeBag<T extends Comparable<T>> implements Cloneable
{
    // Invariant of the IntTreeBag class:
    //   1. The elements in the bag are stored in a binary search tree.
    //   2. The instance variable root is a reference to the root of the
    //      binary search tree (or null for an empty tree).
    private BTNode<T> root;   


    /**
     * Insert a new element into this bag.
     * @param <CODE>element</CODE>
     *   the new element that is being inserted
     * <dt><b>Postcondition:</b><dd>
     *   A new copy of the element has been added to this bag.
     * @exception OutOfMemoryError
     *   Indicates insufficient memory a new BTNode<Integer>.
     * @exception IllegalArgumentException
     *   Indicates the given element was null or an employee with the
     *   same information already existed.
     **/
    //*********************************************************************
    public void add(T element)
    {
        if(element == null) {
            throw new IllegalArgumentException("add(): Null element");
        }
        BTNode<T> node = new BTNode<T>(element, null, null);
        if(root == null) {
            root = node;
        } else {
            BTNode<T> cursor = root;
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
                    throw new IllegalArgumentException("add(): Duplicate Element: " + element);
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
    public boolean remove(T target)
    {
        if(root != null && target != null) {
            boolean fromRight = false;
            BTNode<T> precursor = null;
            BTNode<T> cursor = root;
            while(cursor != null) {
                // This means the target is to the left of the cursor.
                if(cursor.getData().compareTo(target) > 0) {
                    precursor = cursor;
                    cursor = cursor.getLeft();
                    fromRight = false;
                // This means the target is to the right of the cursor.
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
                     * isn't a leaf.
                     * 
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
    
    /**
     * Gets the target element.
     * @param <CODE>target</CODE>
     *   the element to find in the bag.
     * <dt><b>Precondition:</b><dd>
     *   If <CODE>target</CODE> was not found in the bag, then this returns
     *   null. This also returns null if the root is null.
     *  @return
     *     the target element.
     **/
    //*********************************************************************
    public T get(T target)
    {
        BTNode<T> result = get(target, root);
        return result == null ? null : result.getData();
    }
    
    /**
     * Traverses the tree in a recursive manor.
     * 
     * @param target Target element to compare against.
     * @param node The node to traverse from.
     * @return The node containing the target element. If no node was found,
     *         this returns null.
     */
    private BTNode<T> get(T target, BTNode<T> node) {
        if(node == null || target == null) {
            return null;
        } else if(node.getData().compareTo(target) > 0) {
            return get(target, node.getLeft());
        } else if(node.getData().compareTo(target) < 0) {
            return get(target, node.getRight());
        } else {
            return node;
        }
    }
    //*********************************************************************

    /**
     * Displays the elements in this bag.
     * @param - none
     **/ 
    //*********************************************************************                          
    public void display( )
    {
        if(root != null) {
            root.inorderPrint();
        }
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

