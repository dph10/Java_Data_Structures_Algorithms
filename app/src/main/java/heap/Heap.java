/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package heap;

import arrays.QueueInterface;

/**
 *
 * @author daniel.holden.reg
 * @param <V>
 */
public class Heap<T extends Comparable<T>> implements QueueInterface<T> {
    
    private int capacity;
    private int length = 0;
    private T[] array;
    
    public Heap() {
        this(10);
    }
    
    public Heap(final int initCapacity) {
        this.capacity = initCapacity < 0 ? 10 : initCapacity;
        this.array = (T[]) new Comparable[this.capacity];
    }

    @Override
    public boolean add(final T el) {
        this.array[++this.length] = el;
        this.swim(length);
        
        if (this.length > this.capacity/2) {
            this.resizeArray(this.capacity*2);
        }
        
        return true;
    }

    @Override
    public boolean isEmpty() {
        return this.length==0;
    }

    @Override
    public T deQueue() {
        if (this.isEmpty()) {
            return null;
        }
        
        final T returnVal = this.array[1];
        
        exch(1, this.length--);
        this.array[this.length+1] = null;
        this.sink(1);
        
        if (this.length < this.capacity/4) {
            this.resizeArray(this.capacity/2);
        }        
        
        return returnVal;
    }

    @Override
    public T peek() {
       if (this.isEmpty()) {
           return  null;
       }
       return this.array[1];
    }

    @Override
    public T peekTail() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int length() {
        return this.length;
    }

    @Override
    public int capacity() {
        return this.capacity;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public int clear() {
        final int previousLength = this.length;
        for (int ii=0; ii < this.length; ii++) {
            this.array[ii]=null;
        }
        
        this.length=0;
        this.array = (T[]) new Comparable[10];
        this.capacity = this.array.length;
        return previousLength;
    }
    
    protected void resizeArray(final int size) {
        final T[] newArray = (T[]) new Comparable[size];
        for (int ii=0; ii<=this.length; ii++) {
            newArray[ii] = this.array[ii];
            this.array[ii] = null;
        }
        this.array = newArray;
        this.capacity = size;
    }
    
    protected boolean less(final int ii, final int jj) {
        return this.array[ii].compareTo(this.array[jj]) < 0;
    }
    
    private void exch(final int ii, final int jj) {
        final T val = this.array[ii];
        this.array[ii] = this.array[jj];
        this.array[jj] =  val;
    }
    
    private void swim(int k) {
        // while k > 1 and is greater than its parent at index k/2, the value
        // at this index will continue to "swim up" through the heap towards the root
        while (k > 1 && less(k/2, k)) {
            exch(k/2, k);
            k=k/2;
        }
    }
    
    private void sink(int k) {
        while (2*k <= this.length) {
            
            int jj = 2*k;  // index of first child is at 2*k, index of 2nd child is at 2*k+1
            if (jj < this.length && less(jj, jj+1)) jj++; // determine the greatest of its children
            if (!less(k, jj)) break;
            exch(k, jj);
            k=jj;
        }
    }
    
    public static final void heapSort(final Comparable[] array) {
        int N = array.length;

        if (array[0] == null) {

            for (int ii = N / 2; ii >= 1; ii--) {
                heapSortSink(array, ii, N);
            }

            while (N > 1) {
                heapSortExch(array, 1, N--);
                heapSortSink(array, 1, N);
            }
        } else {
            final Comparable[] copyArray = new Comparable[N+1];
            for (int ii=0; ii<N; ii++) {
                copyArray[ii+1] = array[ii];
            }
            
            for (int ii = N / 2; ii >= 1; ii--) {
                heapSortSink(copyArray, ii, N);
            }

            while (N > 1) {
                heapSortExch(copyArray, 1, N--);
                heapSortSink(copyArray, 1, N);
            }
            
            for (int ii=0; ii<array.length; ii++) {
                array[ii] =  copyArray[ii+1];
            }
        }
    }
    
    private static void heapSortSink(final Comparable[] array, int k, int length) {
        while (2*k <= length) {            
            int jj = 2*k;
            if (jj < length && heapSortLess(array[jj], array[jj+1])) jj++;
            if (!heapSortLess(array[k], array[jj])) break;
            heapSortExch(array, k, jj);
            k=jj;
        }
    }
    
    private static void heapSortExch(final Comparable[] array, int ii, int jj) {
        final var val = array[ii];
        array[ii] = array[jj];
        array[jj] = val;
    }
    
    private static <T extends Comparable<T>> boolean heapSortLess(final T val1, final T val2) {
        return val1.compareTo(val2) < 0;
    }
}
