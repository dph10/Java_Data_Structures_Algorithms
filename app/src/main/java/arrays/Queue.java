/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package arrays;

/**
 *
 * @author daniel.holden.reg
 */
public interface Queue<T> {
    
    public void add(final T el);
    
    public boolean isEmpty();
    
    public T remove();
    
    public T peek();
    
    public T peekTail();
    
    public int length();
}
