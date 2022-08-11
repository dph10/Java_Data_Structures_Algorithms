/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arrays;

import java.util.stream.IntStream;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author daniel.holden.reg
 */
public class ArrayTest {
    
    @Test
    public void testConstructorNoArs() {        
        final var arr1 = new Array<Integer>();
        assertEquals(10, arr1.capacity());
        assertEquals(0, arr1.length());        
    }
    
    @Test
    public void testConstructor1Arg() {
        final var arr1 = new Array<Integer>(20);
        assertEquals(20, arr1.capacity());
        assertEquals(0, arr1.length());
    }
    
    @Test
    public void testConstructor2Args() {
        final var arr1 = new Array<Integer>(20, Integer.MIN_VALUE);
        assertEquals(40, arr1.capacity());
        assertEquals(20, arr1.length());
        
        for (int ii=0; ii<20; ii++) {
            assertEquals(Integer.MIN_VALUE, arr1.get(ii));
        }        
    }
    
    @Test
    public void testAdd() {
        
        final var arr1 = new Array<Integer>(2);        
        final var intsToAdd = IntStream.rangeClosed(0, 100).toArray();
        
        arr1.add(-1);
        assertEquals(2, arr1.capacity());
        assertEquals(1, arr1.length());
        assertEquals(-1, arr1.get(0));
        
        arr1.add(10);
        assertEquals(4, arr1.capacity());
        assertEquals(2, arr1.length());
        assertEquals(-1, arr1.get(0));
        assertEquals(10, arr1.get(1));
        
        arr1.add(-23);
        assertEquals(8, arr1.capacity());
        assertEquals(3, arr1.length());
        assertEquals(-1, arr1.get(0));
        assertEquals(10, arr1.get(1));
        assertEquals(-23, arr1.get(2));
        
        assertThrows(IndexOutOfBoundsException.class,() -> {
            arr1.get(-1);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            arr1.get(4);
        });
        
        arr1.clear();
        assertEquals(10, arr1.capacity());
        assertEquals(0, arr1.length());
        
        for (int ii=0; ii< intsToAdd.length; ii++) {
            arr1.add(intsToAdd[ii], ii);
        }
        
        assertEquals(intsToAdd.length, arr1.length());
        assertEquals(320, arr1.capacity());
        
        for (int ii=0; ii<intsToAdd.length; ii++) {
            assertEquals(intsToAdd[ii], arr1.get(ii));
        }
        
        final var itr = arr1.iterator();
        for (int ii=0; ii<intsToAdd.length; ii++) {
            assertTrue(itr.hasNext());
            assertEquals(intsToAdd[ii], itr.next());
        }
        
        arr1.clear();
        assertEquals(10, arr1.capacity());
        assertEquals(0, arr1.length());
    }
    
    @Test
    public void testInsert() {
        
        final var intsToAdd = IntStream.range(0, 10).toArray();
        final var arr1 = new Array<Integer>();
        
        for (final int val : intsToAdd) {
            arr1.add(val);
        }
        
        for (int ii=0; ii<intsToAdd.length; ii++) {
            assertEquals(intsToAdd[ii], arr1.get(ii));
        }
        
        arr1.add(-10, 3);
        final int[] comp1 = {0, 1, 2, -10, 3, 4, 5, 6, 7, 8, 9};
        
        assertEquals(11, arr1.length());
        for (int ii=0; ii<comp1.length; ii++) {
            assertEquals(comp1[ii], arr1.get(ii));
        }
        
        arr1.add(21, 0);
        final int[] comp2 = {21, 0, 1, 2, -10, 3, 4, 5, 6, 7, 8, 9};
        assertEquals(12, arr1.length());
        for (int ii=0; ii<comp2.length; ii++) {
            assertEquals(comp2[ii], arr1.get(ii));
        }
    }
    
    @Test
    public void testRemoveIndex() {
        
        Array<Integer> arr = new Array<>(4);
        
        final Integer[] intArr0 = {1,2,3,10};        
        arr.addAll(intArr0);
        
        for (int ii=0; ii<intArr0.length; ii++) {
            assertEquals(intArr0[ii], arr.get(ii));
        }
        
        final Integer[] intArr1 = {1,3,10};
        arr.remove(1);
        
        assertEquals(3, arr.length());
        assertEquals(8, arr.capacity());
        
        for (int ii=0; ii<intArr1.length; ii++) {
            assertEquals(intArr1[ii], arr.get(ii));
        }
        
        final Integer[] intArr2 = {1, 3};
        arr.pop();
        assertEquals(2, arr.length());
        assertEquals(8, arr.capacity());
        for (int ii = 0; ii < intArr2.length; ii++) {
            assertEquals(intArr2[ii], arr.get(ii));
        }
        
        final Integer[] intArr3 = {3};
        arr.remove(0);
        assertEquals(1, arr.length());
        assertEquals(4, arr.capacity());
        for (int ii = 0; ii < intArr3.length; ii++) {
            assertEquals(intArr3[ii], arr.get(ii));
        }
        
        arr.pop();
        assertEquals(0, arr.length());
        assertEquals(2, arr.capacity());
    }
    
    @Test
    public void testRemoveObj() {
        Array<Integer> arr = new Array<>();
        final Integer[] intArr0 = {1,2,3,10};        
        arr.addAll(intArr0);
        
        final Integer myInt=3;
        
        assertEquals(2, arr.remove(myInt));
        final Integer[] intArr1 = {1,2,10};
        assertEquals(3, arr.length());
        for (int ii=0; ii<intArr1.length; ii++) {
            assertEquals(intArr1[ii], arr.get(ii));
        }
        
    }
    
    @Test
    public void testRemoveIterator() {
        Array<Integer> arr = new Array<>();
        final Integer[] intArr0 = {1,2,3,10}; 
        
        arr.addAll(intArr0);
        
        final var itr = arr.iterator();
        assertTrue(itr.hasNext());
        
        assertEquals(intArr0[0], itr.next());
        assertEquals(intArr0[1], itr.next());
        itr.next();
        itr.remove();
        
        assertTrue(itr.hasNext());
        assertEquals(intArr0[3], itr.next());        
        final Integer[] intArr1 = {1,2,10};
        assertFalse(itr.hasNext());
        
        assertEquals(3, arr.length());
        for (int ii=0; ii<intArr1.length; ii++) {
            assertEquals(intArr1[ii], arr.get(ii));
        }
    }
    
    @Test
    public void testToString() {
        final var arr = new int[]{-1, 3, 10, 23};

        final var list = new Array<Integer>();
        for (final var val : arr) {
            list.add(val);
        }

        assertEquals("[-1, 3, 10, 23]", list.toString());
    }

}
