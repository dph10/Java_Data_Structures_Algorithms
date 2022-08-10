/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package arrays;

import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author daniel.holden.reg
 */
public interface ListInterface<T> extends StackInterface<T>{

    
    public int capacity();
    
    public void add(final T el);
    
    public void add(final T el, final int index);
    
    public default void addAll(final Collection<T> objs) {
        for (final T obj : objs) {
            this.add(obj);
        }
    }
    
    public default void addAll(final T[] objs) {
        for (final T obj : objs) {
            this.add(obj);
        }
    }
    
    public int clear();
    
    public default boolean contains(final T el) {
        final var itr = this.iterator();
        
        while(itr.hasNext()) {
            final var thisEl = itr.next();
            if (thisEl.equals(el)) {
                return true;
            }
        }
        return false;
    }
    
    public T remove(final int index);
    
    public default int remove(final T el) {
        final var itr = this.iterator();
        int index = -1;
        
        while(itr.hasNext()) {
            index++;
            final var thisEl = itr.next();
            if (thisEl.equals(el)) {
                itr.remove();
                return index;
            }            
        }        
        return index;
    }
    
    @Override
    public default void push(final T el) {
        this.add(el);
    }
    
    @Override
    public default T pop() {
        if (this.length()== 0) {
            return null;
        }        
        final T val = this.get(this.length()-1);
        this.remove(this.length() - 1);
        return val;
    }
    
    @Override
    public default boolean isEmpty() {
        return this.length()==0;
    }
    
    public Iterator<T> iterator();
    
    public T get(final int index);
    
    public default int size() {
        return this.length();
    }
}
