/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arrays;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

/**
 *
 * @author daniel.holden.reg
 */
public class Array<T> implements ListInterface<T> {
    
    private int capacity;   
    private int length = 0;
    private T[] array;
    
    public Array() {
        this(10);
    }
    
    public Array(final int initCapacity) {
        this.capacity = initCapacity;
        this.array= (T[]) new Object[capacity];
    }
    
    public Array(final int initLength, final T initValue) {
        this.capacity=2*initLength;
        this.length = initLength;
        
        this.array = (T[]) new Object[this.capacity];
        
        for (int ii=0; ii<this.length; ii++) {
            this.array[ii] = initValue;
        }
    }
    
    @Override
    public int length() {
        return this.length;
    }
    
    @Override
    public boolean isEmpty() {
        return this.length==0;
    }
    
    @Override
    public int capacity() {
        return this.capacity;
    }
    
    @Override
    public boolean add(final T el) {
        this.array[this.length++] = el;
        
        if (this.length > this.capacity/2) {
            this.resizeArray(this.capacity*2);
        } 
        
        return true;
    }
    
    @Override
    public T get(final int index) {
        if (index<0 || index >= this.length) {
            throw new IndexOutOfBoundsException(new StringBuilder("Request index ")
                    .append(index)
                    .append(" must be greater than or equal to zero and less than the array length ")
                    .append(this.length).toString());
        }
        
        return this.array[index];
    }
    
    @Override
    public void add(final T el, final int index) {
        if (index > this.length || index <0) {
            throw new IndexOutOfBoundsException(new StringBuilder("Request index ")
                    .append(index).append(" must be greater than or equals to zero and less than or equal to array length ")
                    .append(this.length).toString());
        } else if(index == this.length) {
            this.add(el);
        } else {
            
            for (int ii=this.length-1; ii>=index; ii--) {
                this.array[ii+1]=this.array[ii];
            }
            this.array[index] = el;
            
            if (++this.length> this.capacity/2 ) {
                this.resizeArray(this.capacity*2);
            }
            
        }
    }
    
    @Override
    public void addAll(final Collection<T> objs) {
        for (final var obj : objs) {
            this.add(obj);
        }
    }
    
    @Override
    public void addAll(final T[] objs) {
        for (final var obj : objs) {
            this.add(obj);
        }
    }

    protected void resizeArray(final int size) {
        final T[] newArray = (T[]) new Object[size];
        
        for (int ii=0; ii<this.length; ii++) {
            newArray[ii] = this.array[ii];
            this.array[ii] = null;
        }
        
        this.array = newArray;
        this.capacity = size;
    }
    
    @Override
    public int clear() {
        
        final int currentLength = this.length;
        
        for(int ii=0; ii< this.length; ii++) {
            this.array[ii]=null;
        }
        this.length=0;
        this.array = (T[]) new Object[10];
        this.capacity=this.array.length;
        
        return currentLength;
        
    }
    
    @Override
    public boolean contains(final T el) {
        for (int ii=0; ii<this.length; ii++) {
            if (Objects.equals(this.array[ii], el)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public T remove(final int index) {
        
        if (index<0 || index>= this.length) {
             throw new IndexOutOfBoundsException(new StringBuilder("Request index ")
                    .append(index)
                    .append(" must be greater than or equal to zero and less than the array length ")
                    .append(this.length).toString());
        }
        final T returnVal = this.array[index];
        this.array[index] = null;
        for (int ii=index+1; ii<this.length; ii++) {
            this.array[ii-1] = this.array[ii];
        }        
        
        if (--this.length < this.capacity/4) {
            this.resizeArray(this.capacity/2);
        }        
        return returnVal;
    }
    
    /*@Override
    public int remove(final T el) {
        
        int index = 0;
        while(index < this.length) {
            if (Objects.equals(this.array[index], el)) break;
            index++;
        }
        
        if (index == this.length) return -1;
        else {
            // remove element
            this.remove(index);
            return index;
        }
    }*/
    
    @Override
    public T pop() {
        if (this.length==0) {
            return null;
        }
        final T val = this.array[this.length-1];
        this.remove(this.length-1);
        
        return val;
    }
    
    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator<>(this);
    }
    
    public static class ArrayIterator<T> implements Iterator<T> {
        
        private final Array<T> array;
        private int currentIndex = -1;        
        
        private ArrayIterator(final Array array) {
            this.array = array;
        }

        @Override
        public boolean hasNext() {
            return this.currentIndex < this.array.length-1;
        }

        @Override
        public T next() {
            return this.array.get(++this.currentIndex);
        }

        @Override
        public void remove() {
            if (this.currentIndex>=0) {
                this.array.remove(this.currentIndex--);
            }
        }  
    }
    
    @Override
    public final String toString() {
        return ListInterface.toString(this);
    }
    
}
