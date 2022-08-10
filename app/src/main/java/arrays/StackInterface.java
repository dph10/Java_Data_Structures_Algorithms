/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package arrays;

/**
 *
 * @author daniel.holden.reg
 */
public interface StackInterface<T> {
    
    public int length();
    
    public void push(final T el);
    
    public T pop();
    
    public boolean isEmpty();
    
}
