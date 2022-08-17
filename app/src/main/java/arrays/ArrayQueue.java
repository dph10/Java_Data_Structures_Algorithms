/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arrays;

/**
 *
 * @author daniel.holden.reg
 */
public class ArrayQueue<T> implements QueueInterface<T>{

    private final int capacity;
    private int length = 0;
    
    private final T[] array;
    
    private int queueStart = -1;
    private int queueEnd = -1;
    
    public ArrayQueue(final int maxCapacity) {
        this.capacity = maxCapacity;
        this.array = (T[]) new Object[capacity];
    }
    
    @Override
    public boolean add(final T el) {
        
        if (!this.isFull()) {
            
            if (this.isEmpty()) {
                this.queueStart=0;
                this.array[++this.queueEnd] = el;
            }
            
            if (this.queueEnd+1 == this.capacity) {
                this.queueEnd=0;
            } else {
                this.queueEnd++;
            }            
            this.array[this.queueEnd] = el;
            this.length++;
        }        
        return false;
        
    }

    @Override
    public boolean isEmpty() {
        return this.length==0;
    }

    @Override
    public T deQueue() {
        if (this.isEmpty()) {
            return null;
        } else {            
            final T result = this.array[this.queueStart];
            this.array[this.queueStart]=null;
            
            if (this.queueStart == this.queueEnd) {
                this.queueStart = this.queueEnd = -1;
            } else if (this.queueStart+1 == this.capacity) {
                this.queueStart=0;
            } else {
                this.queueStart++;
            }
            this.length--;
            return result;
        }
    }

    @Override
    public T peek() {
        if (this.queueStart>-1) {
            return this.array[this.queueStart];
        } else {
            return null;
        }
    }

    @Override
    public T peekTail() {
       if (this.queueEnd>-1) {
           return this.array[this.queueEnd];
       } else {
           return null;
       }
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
        return this.length == this.capacity;
    }
    
    @Override
    public int clear() {
        final int previousLength = this.length;
        
        this.queueStart=-1;
        this.queueEnd=-1;
        
        this.length=0;
        
        for (int ii=0; ii<this.array.length; ii++) {
            this.array[ii]=null;
        }
       
        return previousLength;
    }
}
