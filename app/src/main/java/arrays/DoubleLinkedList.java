/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arrays;

import java.util.HashSet;
import java.util.Iterator;

/**
 *
 * @author daniel.holden.reg
 */
public class DoubleLinkedList<T extends Comparable<T>> implements ListInterface<T>, QueueInterface<T> {
    
    private int length;
    
    private Node<T> head = null;
    private Node<T> tail = null;
    
    public DoubleLinkedList() {this.length=0;}

    public DoubleLinkedList(final T firstVal) {
        this.head = new Node<>(firstVal);
        this.length=1;
    }

    @Override
    public boolean isFull() {
        return false;
    }
    
    static class Node<T> {
        
        private final T value;
        private Node nextNode = null;
        private Node previousNode = null;
        
        Node(final T val) {
            this.value = val;
        }

        T getValue() {
            return value;
        }
 
    }
    
    @Override
    public int capacity() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean add(final T el) {
        if (this.head==null) {
            this.head = new Node<>(el);
        } else {
            final Node<T> newNode = new Node<>(el);
            if (this.tail!=null) {
                this.tail.nextNode=newNode;
                newNode.previousNode = this.tail;
            } else {
                this.head.nextNode = newNode;
                newNode.previousNode = this.head;
            }
            
            this.tail=newNode;
        }
        this.length++;
        return true;
    }

    @Override
    public void add(final T el, final int index) {
        if (index > this.length || index < 0) {
            throw new IndexOutOfBoundsException(new StringBuilder("Request index ")
                    .append(index).append(" must be greater than or equals to zero and less than or equal to array length ")
                    .append(this.length).toString());
        } else if (index==this.length) {
            this.add(el);
        } else if (index==0) {            
            final Node<T> newNode = new Node<>(el);
            
            this.head.previousNode = newNode;
            newNode.nextNode = this.head;
            this.head = newNode;   
            this.length++;
        } else {
            
            Node<T> nodeAtCurrentIndex = this.head;
            for (int ii=0; ii<index; ii++) {
                nodeAtCurrentIndex = nodeAtCurrentIndex.nextNode;
            }
            
            final Node<T> newNode = new Node<>(el);            
            final Node<T> nodeAtPreviousIndex = nodeAtCurrentIndex.previousNode;
            
            nodeAtPreviousIndex.nextNode = newNode;
            newNode.previousNode = nodeAtPreviousIndex;
            
            nodeAtCurrentIndex.previousNode = newNode;
            newNode.nextNode = nodeAtCurrentIndex;
            this.length++;
        }
    }

    @Override
    public int clear() {
       final int previousSize = this.length;
       this.head=null;
       this.tail=null;
       this.length=0;
       
       return previousSize;
    }

    @Override
    public T remove(final int index) {
        
        if (index < 0 || index >= this.length) {
            throw new IndexOutOfBoundsException(new StringBuilder("Request index ")
                    .append(index)
                    .append(" must be greater than or equal to zero and less than the array length ")
                    .append(this.length).toString());
        }
        
        if (index == 0) {
            final T returnVal = this.head.value;
            
            this.head = this.head.nextNode;
            if (this.head!=null) {
                this.head.previousNode = null;
            }
            
            this.length--;
            if (this.length==1) {
                this.tail=null;
                this.head.nextNode = null;
                this.head.previousNode = null;
            }
            return returnVal;
        } else if (index == this.length-1) {
            
            if(this.length==1) {
                final T returnVal = this.head.value;
                this.head=null;                
                this.length--;
                return returnVal;
            }
            
            final T returnVal = this.tail.value;
            
            this.tail = this.tail.previousNode;
            this.tail.nextNode=null;
            
            this.length--;
            if (this.length==1) {
                this.head=this.tail;
                this.head.nextNode=null;
                this.head.previousNode=null;
                this.tail=null;
            }
            return returnVal;
        } else {

            Node<T> currentNode = head;

            for (int ii = 0; ii < index; ii++) {
                currentNode = currentNode.nextNode;
            }

            final T returnVal = currentNode.value;

            final Node<T> previousIndexNode = currentNode.previousNode;
            final Node<T> nextIndexNode = currentNode.nextNode;

            previousIndexNode.nextNode = nextIndexNode;
            nextIndexNode.previousNode = previousIndexNode;

            this.length--;
            if (this.length == 1) {
                this.head = this.tail;
                this.head.nextNode = null;
                this.head.previousNode = null;
                this.tail = null;
            }
            return returnVal;
        }
    }
    
    @Override
    public T pop() {
        if (this.length==0) {
            return null;
        } else if (this.tail==null) {
            final T val = this.head.getValue();
            this.remove(0);
            return val;
        } else {
            final T val = this.tail.getValue();
            this.remove(this.length-1);
            return val;
        }
    }

    @Override
    public boolean isEmpty() {
        return this.length == 0;
    }

    @Override
    public Iterator iterator() {
        return new LinkedListIterator(this);
    }

    @Override
    public T get(final int index) {
        if (index<0 || index >= this.length) {
            throw new IndexOutOfBoundsException(new StringBuilder("Request index ")
                    .append(index)
                    .append(" must be greater than or equal to zero and less than the array length ")
                    .append(this.length).toString());
        }
        
        if (index == 0) {
            return this.head.getValue();
        } else if (index == this.length-1) {
            return this.tail.getValue();
        } else {
            
            Node<T> currentNode = this.head;
            for (int ii=0; ii< index; ii++) {
                currentNode = currentNode.nextNode;
            }
            return currentNode.value;
        }
    }

    @Override
    public int length() {
       return this.length;
    }
    
    public static class LinkedListIterator<T extends Comparable<T>> implements Iterator<T> {
        
        private final DoubleLinkedList<T> list;
        private Node<T> currentNode=null;
        
        private LinkedListIterator(final DoubleLinkedList<T> listItr) {
            this.list = listItr;            
        }
        
        @Override
        public boolean hasNext() {
            if (this.currentNode == null) {
                return this.list.head!=null;
            } else {
                return this.currentNode.nextNode!=null;
            }
        }

        @Override
        public T next() {
            if (this.currentNode==null) {
                this.currentNode = this.list.head;
            } else {
                this.currentNode = this.currentNode.nextNode;
            }
            return this.currentNode.value;
        }

        @Override
        public void remove() {
            if (this.currentNode == null) {
            } else if (this.currentNode == this.list.head) {
                this.list.remove(0);
                this.currentNode=null;
            } else if (this.currentNode == this.list.tail) {
                this.list.remove(this.list.length-1);
                this.currentNode = this.list.tail;
            } else {
                
                final Node<T> previousIndexNode = this.currentNode.previousNode;
                final Node<T> nextIndexNode = this.currentNode.nextNode;
                
                previousIndexNode.nextNode = nextIndexNode;
                nextIndexNode.previousNode = previousIndexNode;
                this.currentNode = previousIndexNode;
                this.list.length--;
            }
            
        }
        
    }
    
    
    @Override
    public T deQueue() {
        if (this.length==0) {
            return null;
        }
        return this.remove(0);
    }

    @Override
    public T peek() {
        if (this.head==null) {
            return null;
        }
        return this.head.value;
    }

    @Override
    public T peekTail() {
        
        if (this.tail==null && this.length==0) {
            return null;
        } else if (this.tail==null) {
            return this.head.value;
        } else {
            return this.tail.value;
        }        
    }
    
    @Override
    public final String toString() {
        return ListInterface.toString(this);
    }
    
    public int removeDuplicates() {
        if (this.length>1) {
            final Iterator<T> itr = this.iterator();
            
            final var uniqueSet = new HashSet<T>();
            int numDuplicates=0;
            while(itr.hasNext()) {
                final var val = itr.next();
                if(!uniqueSet.add(val)) {
                    itr.remove();
                    numDuplicates++;
                }
            }

            return numDuplicates;
        } else {
            return 0;
        }
    }
    
    /**
     * 
     * @param nthFromLast index from the last element, last element is 0
     * @return 
     */
    public T getNthFromLast(final int nthFromLast) {
        if (nthFromLast < 0 || nthFromLast >= this.length) {
            throw new IndexOutOfBoundsException(new StringBuilder("Request index ")
                    .append(nthFromLast)
                    .append(" must be greater than or equal to zero and less than the array length ")
                    .append(this.length).toString());
        }
        if (length == 1) {
            return this.head.value;
        }
        
        Node<T> currentNode = this.tail;
        for (int ii=0; ii<nthFromLast; ii++) {
            currentNode = currentNode.previousNode;
        }
        return currentNode.getValue();
    }
    
    public void partitionAroundX(final T value) {
        
        if (this.length>1) {
            Node<T> currentNode  = this.head;
            
            while(currentNode.nextNode!=null) {
                
                final Node<T> nextNode = currentNode.nextNode;
                
                if (nextNode.value.compareTo(value)<0) {
                    
                    currentNode.nextNode = nextNode.nextNode;                    
                    if(nextNode.nextNode!=null) {
                        nextNode.nextNode.previousNode = currentNode;
                    }
                    
                    this.head.previousNode = nextNode;
                    nextNode.previousNode=null;
                    nextNode.nextNode = this.head;
                    this.head = nextNode;
                    
                } else {
                    currentNode = nextNode;
                }
            }      
            this.tail = currentNode;
            
        }
        
    }
    
    /**
     * This is the most efficient implementation, linking the head of 
     * {@code listToAppend} to the tail of this list.  This means the both lists
     * will now contain references to the same objects and nodes, so it is recommended
     * to not use the appended list after executing this method.
     * @param listToAppend 
     */
    public void appendLinkedList(final DoubleLinkedList<T> listToAppend) {
        
        if (this.length==0) {
            this.head = listToAppend.head;
            this.tail = listToAppend.tail;
            this.length = listToAppend.length;
        }
        
        if (listToAppend.isEmpty()) {
            return;
        }
        
        this.length+=listToAppend.length;
        
        this.tail.nextNode = listToAppend.head;
        listToAppend.head.previousNode = this.tail;
        this.tail = listToAppend.tail;
        
    }
}
