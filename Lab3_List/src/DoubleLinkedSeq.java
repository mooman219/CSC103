// File: DoubleLinkedSeq.java from the package edu.colorado.collections

// This is an assignment for students to complete after reading Chapter 4 of
// "Data Structures and Other Objects Using Java" by Michael Main.

// Check with your instructor to see whether you should put this class in
// a package. At the moment, it is declared as part of edu.colorado.collections:

/******************************************************************************
 * This class is a homework assignment;
 * A DoubleLinkedSeq</CODE> is a collection of double</CODE> numbers.
 * The sequence can have a special "current element," which is specified and
 * accessed through four methods that are not available in the sequence class
 * (start, getCurrent, advance and isCurrent).
 * <dl>
 * <dt><b>Limitations:</b> Beyond Int.MAX_VALUE</CODE> elements, the size</CODE> method
 * does not work.
 * <dt><b>Note:</b>
 * <dd>This file contains only blank implementations ("stubs") because this is a
 * Programming Project for my students.
 * <dt><b>Outline of Java Source Code for this class:</b>
 * <dd><A HREF="../../../../edu/colorado/collections/DoubleLinkedSeq.java">
 * http://www.cs.colorado.edu/~main/edu/colorado/collections/DoubleLinkedSeq.java </A>
 * </dl>
 * 
 * @version
 *          Jan 24, 1999
 ******************************************************************************/
public class DoubleLinkedSeq implements Cloneable {
    private Node<Double> head;  // pointer to first element in sequence
    private Node<Double> tail;  // pointer to last element in sequence
    private Node<Double> cursor;    // pointer to current element
    private Node<Double> precursor; // pointer to element just before current element
    private int manyNodes;  // number of elements in sequence

    /**
     * Initialize an empty sequence.
     * 
     * @param - none <dt><b>Postcondition:</b>
     *        <dd>This sequence is empty.
     **/
    public DoubleLinkedSeq() {
        head = null;
        tail = null;
        cursor = head;
        precursor = head;
        manyNodes = 0;
    }

    /**
     * Add a new element to this sequence, after the current element.
     * 
     * @param element</CODE>
     *        the new element that is being added <dt><b>Postcondition:</b>
     *        <dd>A new copy of the element has been added to this sequence. If there was
     *        a current element, then the new element is placed after the current element.
     *        If there was no current element, then the new element is placed at the end
     *        of the sequence. In all cases, the new element becomes the new current
     *        element of this sequence.
     * @exception OutOfMemoryError
     *            Indicates insufficient memory for a new node.
     **/
    public void addAfter(double element) {
        if(isCurrent()) {
            cursor.addNodeAfter(element);
            precursor = cursor;
            cursor = cursor.getLink();
        } else {
            if(tail == null) {
                tail = new Node<Double>(element, null);
                cursor = tail;
                precursor = tail;
                head = tail;
            } else {
                tail.addNodeAfter(element);
                precursor = tail;
                tail = tail.getLink();
                cursor = tail;
            }
            manyNodes++;
        }
    }

    /**
     * Add a new element to this sequence, before the current element.
     * 
     * @param element</CODE>
     *        the new element that is being added <dt><b>Postcondition:</b>
     *        <dd>A new copy of the element has been added to this sequence. If there was
     *        a current element, then the new element is placed before the current
     *        element. If there was no current element, then the new element is placed at
     *        the start of the sequence. In all cases, the new element becomes the new
     *        current element of this sequence.
     * @exception OutOfMemoryError
     *            Indicates insufficient memory for a new node.
     **/
    public void addBefore(double element) {
        if(isCurrent()) {
            if(cursor == head) {
                precursor = new Node<Double>(element, cursor);
                head = precursor;
            } else {
                precursor.addNodeAfter(element);
                cursor = precursor.getLink();
            }
        } else {
            if(head == null) {
                head = new Node<Double>(element, null);
                cursor = head;
                precursor = head;
                tail = head;
            } else {
                precursor.addNodeAfter(element);
            }
        }
        manyNodes++;
    }

    /**
     * Place the contents of another sequence at the end of this sequence.
     * 
     * @param addend</CODE>
     *        a sequence whose contents will be placed at the end of this sequence <dt>
     *        <b>Precondition:</b>
     *        <dd>The parameter, addend</CODE>, is not null.
     *        <dt><b>Postcondition:</b>
     *        <dd>The elements from addend</CODE> have been placed at the end of this
     *        sequence. The current element of this sequence remains where it was, and the
     *        addend</CODE> is also unchanged.
     * @exception NullPointerException
     *            Indicates that addend</CODE> is null.
     * @exception OutOfMemoryError
     *            Indicates insufficient memory to increase the size of this sequence.
     **/
    public void addAll(DoubleLinkedSeq addend) {
        Node<Double>[] copy;
        if(addend == null) {
            throw new IllegalArgumentException("addAll:  addend is null");
        }
        if(addend.size() > 0) {
            copy = Node.listCopyWithTail(addend.head);
            tail.getLink().setLink(copy[0]);
            copy[1].setLink(null);
            tail.setLink(copy[0]);
            manyNodes += addend.size();
        }
    }

    /**
     * Move forward, so that the current element is now the next element in
     * this sequence.
     * 
     * @param - none <dt><b>Precondition:</b>
     *        <dd>isCurrent()</CODE> returns true.
     *        <dt><b>Postcondition:</b>
     *        <dd>If the current element was already the end element of this sequence
     *        (with nothing after it), then there is no longer any current element.
     *        Otherwise, the new element is the element immediately after the original
     *        current element.
     * @exception IllegalStateException
     *            Indicates that there is no current element, so advance</CODE> may not be
     *            called.
     **/
    public void advance() {
        if(!isCurrent()) {
            return;
        }
        precursor = cursor;
        cursor = cursor.getLink();
    }

    /**
     * Generate a copy of this sequence.
     * 
     * @param - none
     * @return
     *         The return value is a copy of this sequence. Subsequent changes to the
     *         copy will not affect the original, nor vice versa. Note that the return
     *         value must be type cast to a DoubleLinkedSeq</CODE> before it can be used.
     * @exception OutOfMemoryError
     *            Indicates insufficient memory for creating the clone.
     **/
    @Override
    public Object clone() {
        DoubleLinkedSeq answer;
        try {
            answer = (DoubleLinkedSeq) super.clone();
        } catch(CloneNotSupportedException e) {
            throw new RuntimeException("This class does not implement Cloneable.");
        }
        answer.head = Node.listCopy(head);
        return answer;
    }

    /**
     * Create a new sequence that contains all the elements from one sequence
     * followed by another.
     * 
     * @param s1</CODE>
     *        the first of two sequences
     * @param s2</CODE>
     *        the second of two sequences <dt><b>Precondition:</b>
     *        <dd>Neither s1 nor s2 is null.
     * @return
     *         a new sequence that has the elements of s1</CODE> followed by the elements
     *         of s2</CODE> (with no current element)
     * @exception NullPointerException. Indicates that one of the arguments is null.
     * @exception OutOfMemoryError
     *            Indicates insufficient memory for the new sequence.
     **/
    public static DoubleLinkedSeq catenation(DoubleLinkedSeq s1, DoubleLinkedSeq s2) {
        if((s1 == null) || (s1 == null)) {
            throw new IllegalArgumentException("concatenation:  one argument is null");
        }
        DoubleLinkedSeq answer = new DoubleLinkedSeq();
        answer.addAll(s1);
        answer.addAll(s2);
        return answer;
    }

    /**
     * Accessor method to get the current element of this sequence.
     * 
     * @param - none <dt><b>Precondition:</b>
     *        <dd>isCurrent()</CODE> returns true.
     * @return
     *         the current capacity of this sequence
     * @exception IllegalStateException
     *            Indicates that there is no current element, so getCurrent</CODE> may not
     *            be called.
     **/
    public double getCurrent() {
        if(!isCurrent()) {
            throw new IllegalStateException("getCurrent: isCurrent() is null");
        }
        return cursor.getData();
    }

    /**
     * Accessor method to determine whether this sequence has a specified
     * current element that can be retrieved with the
     * getCurrent</CODE> method.
     * 
     * @param - none
     * @return
     *         true (there is a current element) or false (there is no current element at
     *         the moment)
     **/
    public boolean isCurrent() {
        if(cursor == null) {
            return false;
        }
        return true;
    }

    /**
     * Remove the current element from this sequence.
     * 
     * @param - none <dt><b>Precondition:</b>
     *        <dd>isCurrent()</CODE> returns true.
     *        <dt><b>Postcondition:</b>
     *        <dd>The current element has been removed from this sequence, and the
     *        following element (if there is one) is now the new current element. If there
     *        was no following element, then there is now no current element.
     * @exception IllegalStateException
     *            Indicates that there is no current element, so removeCurrent</CODE> may
     *            not be called.
     **/
    public void removeCurrent() {
        if(!isCurrent()) {
            throw new IllegalStateException("removeCurrent: isCurrent() is null");
        }
        if(tail == head) {
            head = null;
            tail = null;
            cursor = head;
            precursor = head;
            manyNodes--;
            return;
        }

        if(cursor == tail) {
            tail = precursor;
            tail.setLink(null);
            cursor = tail;
            precursor = head;
            while(precursor.getLink() != cursor) {
                if(precursor.getLink() == null) {
                    break;
                }
                precursor = precursor.getLink();
            }
            manyNodes--;
            return;
        }

        if(cursor == head) {
            head = head.getLink();
            cursor = head;
            precursor = head;
            manyNodes--;
            return;
        }
        cursor = cursor.getLink();
        precursor.setLink(cursor);
        manyNodes--;
    }

    /**
     * Determine the number of elements in this sequence.
     * 
     * @param - none
     * @return
     *         the number of elements in this sequence
     **/
    public int size() {
        return manyNodes;
    }

    /**
     * Set the current element at the front of this sequence.
     * 
     * @param - none <dt><b>Postcondition:</b>
     *        <dd>The front element of this sequence is now the current element (but if
     *        this sequence has no elements at all, then there is no current element).
     **/
    public void start() {
        if(head == null) {
            cursor = null;
        }
        cursor = head;
        precursor = head;
    }
}
