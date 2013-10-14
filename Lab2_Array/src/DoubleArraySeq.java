/******************************************************************************
 * This class is a homework assignment; A DoubleArraySeq is a collection of
 * double numbers. The sequence can have a special "current element," which is
 * specified and accessed through four methods that are not available in the
 * sequence class (start, getCurrent, advance and isCurrent).
 * 
 * @note (1) The capacity of one a sequence can change after it's created, but
 *       the maximum capacity is limited by the amount of free memory on the
 *       machine. The constructor, addAfter, addBefore, clone, and concatenation
 *       will result in an OutOfMemoryError when free memory is exhausted.
 *       <p>
 *       (2) A sequence's capacity cannot exceed the maximum integer 2,147,483,647
 *       (Integer.MAX_VALUE). Any attempt to create a larger capacity results in a failure
 *       due to an arithmetic overflow.
 * @note This file contains only blank implementations ("stubs") because this is
 *       a Programming Project for my students.
 * @see <A HREF="../../../../edu/colorado/collections/DoubleArraySeq.java"> Java Source
 *      Code for this class
 *      (www.cs.colorado.edu/~main/edu/colorado/collections/DoubleArraySeq.java) </A>
 * @version March 5, 2002
 ******************************************************************************/
public class DoubleArraySeq implements Cloneable {
    // Invariant of the DoubleArraySeq class:
    // 1. The number of elements in the seqeunces is in the instance variable
    // manyItems.
    // 2. For an empty sequence (with no elements), we do not care what is
    // stored in any of data; for a non-empty sequence, the elements of the
    // sequence are stored in data[0] through data[manyItems-1], and we
    // don’t care what’s in the rest of data.
    // 3. If there is a current element, then it lies in data[currentIndex];
    // if there is no current element, then currentIndex equals manyItems.
    private double[] data;
    private int manyItems;
    private int currentIndex;

    /**
     * Initialize an empty sequence with an initial capacity of 10. Note that
     * the addAfter and addBefore methods work efficiently (without needing more
     * memory) until this capacity is reached.
     * 
     * @postcondition This sequence is empty and has an initial capacity of 10.
     * @throws OutOfMemoryError Indicates insufficient memory for: new double[10].
     **/
    public DoubleArraySeq() {
        this(10);
    }

    /**
     * Initialize an empty sequence with a specified initial capacity. Note that
     * the addAfter and addBefore methods work efficiently (without needing more
     * memory) until this capacity is reached.
     * 
     * @param initialCapacity the initial capacity of this sequence
     * @precondition initialCapacity is non-negative.
     * @postcondition This sequence is empty and has the given initial capacity.
     * @throws IllegalArgumentException Indicates that initialCapacity is negative.
     * @throws OutOfMemoryError Indicates insufficient memory for: new
     *         double[initialCapacity].
     **/
    public DoubleArraySeq(int initialCapacity) {
        if(initialCapacity <= 0) {
            throw new IllegalArgumentException("Negative initialCapacity: " + initialCapacity);
        }
        data = new double[initialCapacity];
        manyItems = 0;
        currentIndex = -1;
    }

    /**
     * Add a new element to this sequence, after the current element. If the new
     * element would take this sequence beyond its current capacity, then the
     * capacity is increased before adding the new element.
     * 
     * @param element the new element that is being added
     * @postcondition A new copy of the element has been added to this sequence. If there
     *                was a current element, then the new element is placed after the
     *                current element. If there was no current element, then the new
     *                element is placed at the end of the sequence. In all cases, the new
     *                element becomes the new current element of this sequence.
     * @throws OutOfMemoryError Indicates insufficient memory for increasing the
     *         sequence's capacity.
     * @note An attempt to increase the capacity beyond Integer.MAX_VALUE will cause the
     *       sequence to fail with an arithmetic overflow.
     **/
    public void addAfter(double element) {
        if(manyItems == data.length) {
            ensureCapacity(manyItems * 2 + 1);
        }
        if(!isCurrent()) {
            currentIndex = 0;
        } else {
            currentIndex++;
        }
        for(int i = currentIndex; i < manyItems; i++) {
            data[i + 1] = data[i];
        }
        data[currentIndex] = element;
        manyItems++;
    }

    /**
     * Place the contents of another sequence at the end of this sequence.
     * 
     * @param addend a sequence whose contents will be placed at the end of this sequence
     * @precondition The parameter, addend, is not null.
     * @postcondition The elements from addend have been placed at the end of this
     *                sequence. The current element of this sequence remains where it was,
     *                and the addend is also unchanged.
     * @throws NullPointerException Indicates that addend is null.
     * @throws OutOfMemoryError Indicates insufficient memory to increase the size of this
     *         sequence.
     * @note An attempt to increase the capacity beyond Integer.MAX_VALUE will cause an
     *       arithmetic overflow that will cause the sequence to fail.
     **/
    public void addAll(DoubleArraySeq addend) {
        if(addend == null) {
            throw new NullPointerException("addend Array is Null Point.");
        }
        this.ensureCapacity(manyItems + addend.manyItems);
        System.arraycopy(addend.data, 0, this.data, this.manyItems, addend.manyItems);
        manyItems += addend.manyItems;
    }

    /**
     * Add a new element to this sequence, before the current element. If the
     * new element would take this sequence beyond its current capacity, then
     * the capacity is increased before adding the new element.
     * 
     * @param element the new element that is being added
     * @postcondition A new copy of the element has been added to this sequence. If there
     *                was a current element, then the new element is placed before the
     *                current element. If there was no current element, then the new
     *                element is placed at the start of the sequence. In all cases, the
     *                new element becomes the new current element of this sequence.
     * @throws OutOfMemoryError Indicates insufficient memory for increasing the
     *         sequence's capacity.
     * @note An attempt to increase the capacity beyond Integer.MAX_VALUE will cause the
     *       sequence to fail with an arithmetic overflow.
     **/
    public void addBefore(double element) {
        if(manyItems == data.length) {
            ensureCapacity(manyItems * 2 + 1);
        }
        if(!isCurrent()) {
            currentIndex = 0;
        }
        for(int i = manyItems; i > currentIndex; i--) {
            data[i] = data[i - 1];
        }
        data[currentIndex] = element;
        manyItems++;
    }

    /**
     * Adds the given element to the end of the sequence.
     * 
     * @param element The element to add.
     */
    public void addEnd(double element) {
        if(manyItems == data.length) {
            ensureCapacity(manyItems * 2 + 1);
        }
        currentIndex = manyItems;
        data[currentIndex] = element;
        manyItems++;
    }

    /**
     * Adds the given element to the start of the sequence.
     * 
     * @param element The element to add.
     */
    public void addFront(double element) {
        if(manyItems == data.length) {
            ensureCapacity(manyItems * 2 + 1);
        }
        currentIndex = 0;
        for(int i = manyItems; i > currentIndex; i--) {
            data[i] = data[i - 1];
        }
        data[currentIndex] = element;
        manyItems++;
    }

    /**
     * Move forward, so that the current element is now the next element in this
     * sequence.
     * 
     * @precondition isCurrent() returns true.
     * @postcondition If the current element was already the end element of this sequence
     *                (with nothing after it), then there is no longer any current
     *                element. Otherwise, the new element is the element immediately after
     *                the original current element.
     * @throws IllegalStateException Indicates that there is no current element, so
     *         advance may not be called.
     **/
    public void advance() {
        if(!isCurrent()) {
            throw new IllegalStateException("There is not current element, so it can't advance");
        }
        currentIndex++;
    }

    /**
     * Generate a copy of this sequence.
     * 
     * @return The return value is a copy of this sequence. Subsequent changes to the copy
     *         will not affect the original, nor vice versa.
     * @throws OutOfMemoryError Indicates insufficient memory for creating the clone.
     **/
    @Override
    public DoubleArraySeq clone() {
        // Clone a DoubleArraySeq object.
        DoubleArraySeq answer;

        try {
            answer = (DoubleArraySeq) super.clone();
        } catch(CloneNotSupportedException e) {
            // This exception should not occur. But if it does, it would
            // probably
            // indicate a programming error that made super.clone unavailable.
            // The most common error would be forgetting the
            // "Implements Cloneable"
            // clause at the start of this class.
            throw new RuntimeException("This class does not implement Cloneable");
        }

        answer.data = data.clone();

        return answer;
    }

    /**
     * Change the current capacity of this sequence.
     * 
     * @param minimumCapacity the new capacity for this sequence
     * @postcondition This sequence's capacity has been changed to at least
     *                minimumCapacity. If the capacity was already at or greater than
     *                minimumCapacity, then the capacity is left unchanged.
     * @throws OutOfMemoryError Indicates insufficient memory for: new
     *         int[minimumCapacity].
     **/
    public void ensureCapacity(int minimumCapacity) {
        int ensuredCapacity;
        if(data.length < minimumCapacity) {
            ensuredCapacity = minimumCapacity;
        } else {
            ensuredCapacity = data.length;
        }
        double[] biggerArray = new double[ensuredCapacity];
        System.arraycopy(data, 0, biggerArray, 0, manyItems);
        data = biggerArray;
    }

    /**
     * Searches for the given element. The currentIndex is set to the found element, else
     * is left unchanged if no element is found.
     * 
     * @param element The number to search for.
     * @return The index of the found element. If no element is found, retuns -1
     */
    public int find(double element) {
        for(int i = 0; i < manyItems; i++) {
            if(element == data[i]) {
                this.currentIndex = i;
                return i;
            }
        }
        return -1;
    }

    /**
     * Accessor method to get the current capacity of this sequence. The add
     * method works efficiently (without needing more memory) until this
     * capacity is reached.
     * 
     * @return the current capacity of this sequence
     **/
    public int getCapacity() {
        return data.length;
    }

    /**
     * Accessor method to get the current element of this sequence.
     * 
     * @precondition isCurrent() returns true.
     * @return the current element of this sequence
     * @throws IllegalStateException Indicates that there is no current element, so
     *         getCurrent may not be called.
     **/
    public double getCurrent() {
        if(this.isCurrent()) {
            return data[currentIndex];
        } else {
            throw new IllegalStateException("there is no current element");
        }
    }

    /**
     * Determine the number of elements in this sequence.
     * 
     * @return the number of elements in this sequence
     **/
    public int getSize() {
        return this.manyItems;
    }

    /**
     * A method that makes the last element of the sequence the current Element.
     * 
     * @throws IllegalStateException If exception if the sequence is empty.
     */
    public void gotoEnd() {
        if(manyItems <= 0) {
            throw new IllegalStateException("The sequence is empty");
        }
        currentIndex = manyItems - 1;
    }

    /**
     * Set the current element at the front of this sequence.
     * 
     * @postcondition The front element of this sequence is now the current element (but
     *                if this sequence has no elements at all, then there is no current
     *                element).
     **/
    public void gotoStart() {
        if(data.length > 0) {
            currentIndex = 0;
        }
    }

    /**
     * Accessor method to determine whether this sequence has a specified
     * current element that can be retrieved with the getCurrent method.
     * 
     * @return true (there is a current element) or false (there is no current element at
     *         the moment)
     **/
    public boolean isCurrent() {
        return currentIndex >= 0;
    }

    /**
     * Remove the current element from this sequence.
     * 
     * @param - none
     * @precondition isCurrent() returns true.
     * @postcondition The current element has been removed from this sequence, and the
     *                following element (if there is one) is now the new current element.
     *                If there was no following element, then there is now no current
     *                element.
     * @throws IllegalStateException Indicates that there is no current element, so
     *         removeCurrent may not be called.
     **/
    public void removeCurrent() {
        if(manyItems - 1 < currentIndex) {
            throw new IllegalStateException("There is no current element.");
        }
        for(int i = currentIndex; i < manyItems; i++) {
            try {
                data[i] = data[i + 1];
            } catch(ArrayIndexOutOfBoundsException e) {
            }
        }
        data[manyItems-- - 1] = 0;
    }

    public void removeEnd() {
        this.gotoEnd();
        this.removeCurrent();
    }

    /**
     * A method to remove the element at the front of the sequence If there is a
     * next element, make that element the current element. Throw an exception
     * if the sequence is empty.
     */
    public void removeFront() {
        this.gotoStart();
        this.removeCurrent();
    }

    /**
     * A method that returns the i^th element of the sequence, and make current
     * element to the i^th element Throw an exception if the sequence is empty,
     * or if i is greater then the sequence size.
     * 
     * @param i
     * @return
     */
    public double retrieveElement(int i) {
        if(i < 0 || i >= manyItems) {
            throw new IllegalArgumentException("Given index of " + i + " is outside the bounds of the sequence");
        }
        return data[i];
    }

    /**
     * a method that makes the ith element become the current element Throw an
     * exception if the sequence is empty, or if i is greater then the sequence
     * size.
     * 
     * @param i
     */
    public void setCurrent(int i) {
        if(i < 0 || i >= manyItems) {
            throw new IllegalArgumentException("Given index of " + i + " is outside the bounds of the sequence");
        }
        this.currentIndex = i;
    }

    @Override
    public String toString() {
        String ret = "";
        ret += "The sequence: ";
        for(int i = 0; i < manyItems; i++) {
            ret += data[i] + " ";
        }
        ret += "\nNumber of elements: " + manyItems + "\n";
        ret += "Current element: " + getCurrent();
        return ret;
    }

    /**
     * Reduce the current capacity of this sequence to its actual size (i.e.,
     * the number of elements it contains).
     * 
     * @postcondition This sequence's capacity has been changed to its current size.
     * @throws OutOfMemoryError Indicates insufficient memory for altering the capacity.
     **/
    public void trimToSize() {
        double[] trimmedArray;
        if(data.length != manyItems) {
            trimmedArray = new double[manyItems];
            System.arraycopy(data, 0, trimmedArray, 0, manyItems);
            data = trimmedArray;
        }
    }
}
