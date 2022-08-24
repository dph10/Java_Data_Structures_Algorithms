/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package arrays;

/**
 *
 * @author daniel.holden.reg
 */
public interface QueueInterface<T> {
    
    public boolean add(final T el);
    
    public default boolean isEmpty() {
        return this.length()==0;
    }
    
    public T deQueue();
    
    public T peek();
    
    public T peekTail();
    
    public int length();
    
    public int capacity();
    
    public boolean isFull();
    
    public int clear();

}
