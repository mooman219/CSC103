import java.util.NoSuchElementException;

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
 * <dt><b>Limitations:</b> Beyond Int.MAX_VALUE</CODE> elements, the size</CODE>
 * method does not work.
 * <dt><b>Note:</b>
 * <dd>This file contains only blank implementations ("stubs") because this is a
 * Programming Project for my students.
 * <dt><b>Outline of Java Source Code for this class:</b>
 * <dd><A HREF="../../../../edu/colorado/collections/DoubleLinkedSeq.java">
 * http:
 * //www.cs.colorado.edu/~main/edu/colorado/collections/DoubleLinkedSeq.java
 * </A>
 * </dl>
 * 
 * @version
 *          Jan 24, 1999
 ******************************************************************************/
public class DoubleLinkedSeq implements Cloneable {
    private Node<Double> head;  // pointer to first element in sequence
    private Node<Double> tail;  // pointer to last element in sequence
    private Node<Double> cursor;    // pointer to current element
    private int manyNodes;  // number of elements in sequence

    /**
     * Initialize an empty sequence.
     * 
     * @postcondition This sequence is empty.
     **/
    public DoubleLinkedSeq() {
        head = null;
        tail = null;
        cursor = head;
        manyNodes = 0;
    }

    /**
     * Locates the node with the given element and sets the cursor to that node.
     * If the given element cannot be found, this returns null and does not
     * alter the cursor.
     * 
     * @param element - The element to find.
     * @return The node that was found.
     */
    public Node<Double> find(double element) {
        if(manyNodes == 0) {
            return null;
        } else {
            Node<Double> current = head;
            while(current != null) {
                if(current.getData().equals(element)) {
                    cursor = current;
                    return current;
                }
                current = current.getLink();
            }
        }
        return null;
    }

    /**
     * Adds the given element to the front of the list.
     * 
     * @param element The element to add
     * @postcondition The given element is now the head of the list.
     */
    public void addFront(double element) {
        if(manyNodes == 0) {
            head = new Node<Double>(element, null);
            tail = head;
        } else {
            head = new Node<Double>(element, head);
        }
        cursor = head;
        manyNodes++;
    }

    /**
     * Adds the given element to the end of the list.
     * 
     * @param element - The element to add.
     * @postcondition The given element is now the end of the list.
     */
    public void addEnd(double element) {
        if(manyNodes == 0) {
            head = new Node<Double>(element, null);
            tail = head;
        } else {
            tail.addNodeAfter(element);
            tail = tail.getLink();
        }
        cursor = tail;
        manyNodes++;
    }

    /**
     * Gets the index of the cursor with the head being 0 and the tail being
     * size - 1.
     * 
     * @return The index of the cursor.
     * @throws IllegalStateException If the cursor is null.
     */
    public int getIndex() {
        if(!isCurrent()) {
            throw new IllegalStateException("getCurrent: isCurrent() is null");
        }
        Node<Double> current = head;
        int index = 0;
        while(current != cursor) {
            current = current.getLink();
            index++;
        }
        return index;
    }

    /**
     * Sets the cursor position to that of the given index.
     * 
     * @param index - The index to set the cursor to.
     * @throws IllegalArgumentException if the index is out of bounds.
     */
    public void setCursor(int index) {
        if(index < 0 || index >= manyNodes) {
            throw new IllegalArgumentException("get: index: " + index + ", is out of bounds for size: " + manyNodes);
        }
        cursor = head;
        int i = 0;
        while(i != index) {
            i++;
            cursor = cursor.getLink();
        }
    }

    /**
     * Gets the data at the given index. The head has an index of 0 while the
     * tail has an index of size - 1.
     * 
     * @param index - The index of the node.
     * @return Value at the given index.
     * @throws IllegalArgumentException if the index is out of bounds.
     */
    public double get(int index) {
        if(index < 0 || index >= manyNodes) {
            throw new IllegalArgumentException("get: index: " + index + ", is out of bounds for size: " + manyNodes);
        }
        Node<Double> current = head;
        int i = 0;
        while(current != null) {
            if(i == index) {
                return current.getData();
            }
            current = current.getLink();
            i++;
        }
        return tail.getData();
    }

    /**
     * Removes the head of the list.
     * 
     * @throws NoSuchElementException Indicates that there is no head to remove.
     */
    public void removeFront() {
        if(manyNodes == 0) {
            throw new NoSuchElementException("removeFront: list is empty");
        }
        head = head.getLink();
        cursor = head;
        manyNodes--;
        if(manyNodes == 0) {
            tail = null;
        }
    }

    /**
     * Add a new element to this sequence, after the current element.
     * 
     * @param element</CODE>
     *        the new element that is being added <dt><b>Postcondition:</b>
     *        <dd>A new copy of the element has been added to this sequence. If
     *        there was a current element, then the new element is placed after
     *        the current element. If there was no current element, then the new
     *        element is placed at the end of the sequence. In all cases, the
     *        new element becomes the new current element of this sequence.
     * @exception OutOfMemoryError Indicates insufficient memory for a new node.
     **/
    public void addAfter(double element) {
        if(isCurrent()) {
            cursor.addNodeAfter(element);
            cursor = cursor.getLink();
        } else {
            if(manyNodes == 0) {
                head = new Node<Double>(element, null);
                tail = head;
            } else {
                tail.addNodeAfter(element);
                tail = tail.getLink();
            }
            cursor = tail;
        }
        manyNodes++;
    }

    /**
     * Add a new element to this sequence, before the current element.
     * 
     * @param element</CODE>
     *        the new element that is being added <dt><b>Postcondition:</b>
     *        <dd>A new copy of the element has been added to this sequence. If
     *        there was a current element, then the new element is placed before
     *        the current element. If there was no current element, then the new
     *        element is placed at the start of the sequence. In all cases, the
     *        new element becomes the new current element of this sequence.
     * @exception OutOfMemoryError
     *            Indicates insufficient memory for a new node.
     **/
    public void addBefore(double element) {
        if(manyNodes == 0) {
            head = new Node<Double>(element, null);
            tail = head;
            cursor = head;
        } else if(!isCurrent() || cursor == head) {
            head = new Node<Double>(element, head);
            cursor = head;
        } else {
            Node<Double> pre = head;
            while(pre.getLink() != cursor) {
                pre = pre.getLink();
            }
            pre.addNodeAfter(element);
            cursor = pre.getLink();
        }
        manyNodes++;
    }

    /**
     * Place the contents of another sequence at the end of this sequence.
     * 
     * @param addend</CODE>
     *        a sequence whose contents will be placed at the end of this
     *        sequence <dt><b>Precondition:</b>
     *        <dd>The parameter, addend</CODE>, is not null.
     *        <dt><b>Postcondition:</b>
     *        <dd>The elements from addend</CODE> have been placed at the end of
     *        this sequence. The current element of this sequence remains where
     *        it was, and the addend</CODE> is also unchanged.
     * @exception NullPointerException
     *            Indicates that addend</CODE> is null.
     * @exception OutOfMemoryError
     *            Indicates insufficient memory to increase the size of this
     *            sequence.
     **/
    public void addAll(DoubleLinkedSeq addend) {
        if(addend == null) {
            throw new IllegalArgumentException("addAll: addend is null");
        }
        if(addend.size() > 0) {
            tail.setLink(addend.head);
            tail = addend.tail;
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
     *        <dd>If the current element was already the end element of this
     *        sequence (with nothing after it), then there is no longer any
     *        current element. Otherwise, the new element is the element
     *        immediately after the original current element.
     * @exception IllegalStateException
     *            Indicates that there is no current element, so advance</CODE>
     *            may not be called.
     **/
    public void advance() {
        if(!isCurrent()) {
            return;
        }
        cursor = cursor.getLink();
    }

    /**
     * Generate a copy of this sequence.
     * 
     * @param - none
     * @return
     *         The return value is a copy of this sequence. Subsequent changes
     *         to the
     *         copy will not affect the original, nor vice versa. Note that the
     *         return
     *         value must be type cast to a DoubleLinkedSeq</CODE> before it can
     *         be used.
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
     *         a new sequence that has the elements of s1</CODE> followed by the
     *         elements of s2</CODE> (with no current element)
     * @exception NullPointerException. Indicates that one of the arguments is
     *            null.
     * @exception OutOfMemoryError
     *            Indicates insufficient memory for the new sequence.
     **/
    public static DoubleLinkedSeq catenation(DoubleLinkedSeq s1, DoubleLinkedSeq s2) {
        if((s1 == null) || (s1 == null)) {
            throw new IllegalArgumentException("concatenation: one argument is null");
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
     *            Indicates that there is no current element, so
     *            getCurrent</CODE> may not be called.
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
     *         true (there is a current element) or false (there is no current
     *         element at
     *         the moment)
     **/
    public boolean isCurrent() {
        return cursor != null;
    }

    /**
     * Remove the current element from this sequence.
     * 
     * @param - none <dt><b>Precondition:</b>
     *        <dd>isCurrent()</CODE> returns true.
     *        <dt><b>Postcondition:</b>
     *        <dd>The current element has been removed from this sequence, and
     *        the following element (if there is one) is now the new current
     *        element. If there was no following element, then there is now no
     *        current element.
     * @exception IllegalStateException
     *            Indicates that there is no current element, so
     *            removeCurrent</CODE> may not be called.
     **/
    public void removeCurrent() {
        if(!isCurrent()) {
            throw new IllegalStateException("removeCurrent: isCurrent() is null");
        } else if(manyNodes == 0) {
            throw new IllegalStateException("removeCurrent: list is empty");
        } else if(manyNodes == 1) {
            head = null;
            tail = null;
            cursor = null;
        } else if(cursor == head) {
            head = head.getLink();
            cursor = head;
        } else {
            Node<Double> pre = head;
            while(pre.getLink() != cursor) {
                pre = pre.getLink();
            }
            if(cursor == tail) {
                pre.setLink(null);
                tail = pre;
                cursor = null;
            } else {
                pre.setLink(cursor.getLink());
                cursor = pre;
            }
        }
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
     *        <dd>The front element of this sequence is now the current element
     *        (but if this sequence has no elements at all, then there is no
     *        current element).
     **/
    public void start() {
        if(head == null) {
            cursor = null;
        }
        cursor = head;
    }

    /**
     * Set the current element at the end of this sequence.
     * 
     * @param - none <dt><b>Postcondition:</b>
     *        <dd>The end element of this sequence is now the current element
     *        (but if this sequence has no elements at all, then there is no
     *        current element).
     **/
    public void end() {
        if(tail == null) {
            cursor = null;
        }
        cursor = tail;
    }

    @Override
    public String toString() {
        String ret = "Size: " + manyNodes + "\n";
        ret += "Current Node: " + (cursor != null ? cursor.getData() : "null") + "\n";
        ret += "Nodes: [";
        Node<Double> current = head;
        while(current != null) {
            ret += current.getData();
            if(current.getLink() != null) {
                ret += ", ";
            }
            current = current.getLink();
        }
        ret += "]";
        return ret;
    }
}
