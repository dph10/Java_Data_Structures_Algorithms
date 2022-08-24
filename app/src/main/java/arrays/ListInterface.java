/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package arrays;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author daniel.holden.reg
 */
public interface ListInterface<T> extends StackInterface<T>, Iterable<T>{
    
    public int capacity();
    
    public boolean add(final T el);
    
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

    public T get(final int index);
    
    public default int size() {
        return this.length();
    }

    public static <T> String toString(final ListInterface<T> list) {
        final StringBuilder sb = new StringBuilder("[");
        
        final var itr = list.iterator();
        while(itr.hasNext()) {
            final var val = itr.next();            
            sb.append(val.toString()).append(", ");
        }
        
        sb.replace(sb.length()-2, sb.length(), "");        
        sb.append("]");
        return sb.toString();
    }
    
    public static <T extends Comparable<T>> Set<T> findUnion(final ListInterface<T> l1, final ListInterface<T> l2) {

        final Set<T> solution = new HashSet<>();

        if (l1 == null || l1.isEmpty()) {
            return solution;
        } else if (l2 == null || l2.isEmpty()) {
            return solution;
        }

        final Set<T> temp = new HashSet<>();

        {
            final Iterator<T> itr1 = l1.iterator();
            while (itr1.hasNext()) {
                solution.add(itr1.next());
            }

            final Iterator<T> itr2 = l2.iterator();
            while (itr2.hasNext()) {
                temp.add(itr2.next());
            }
        }

        final Iterator<T> itrSol = solution.iterator();

        while (itrSol.hasNext()) {
            final T val = itrSol.next();
            if (!temp.contains(val)) {
                itrSol.remove();
            }
        }

        return solution;

    }
    
    public static <T extends Comparable<T>> boolean less(final T a, final T b) {
        return a.compareTo(b) < 0;
    }
    
    public static <T extends Comparable<T>> boolean isSorted(final ListInterface<T> list) {
        
        for (int ii=1; ii< list.length(); ii++) {
            if (less(list.get(ii), list.get(ii-1))) return false;
        }
        return true;        
    }
    
    public static boolean isSorted(final Comparable[] array) {
        for (int ii=1; ii<array.length; ii++) {
            if (less(array[ii], array[ii-1])) return false;
        }
        return true;
    }
    
    public static <T extends Comparable<T>> void exchange(final ListInterface<T> list, final int ii, final int jj) {
        final T val = list.get(ii);
        list.add(list.get(jj), ii);
        list.add(val,jj);
    }
    
    public static void exchange(final Comparable[] array, final int ii, final int jj) {
        final Comparable val = array[ii];
        array[ii] = array[jj];
        array[jj]=val;
    }
}
