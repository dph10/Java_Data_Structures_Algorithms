/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arrays;

import java.util.Set;
import java.util.stream.IntStream;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 *
 * @author daniel.holden.reg
 */
public class DoubleLinkedListTest {
    
    @Test
    public void testConstructorNoArs() {        
        final var arr1 = new DoubleLinkedList<Integer>();
        assertEquals(Integer.MAX_VALUE, arr1.capacity());
        assertEquals(0, arr1.length());        
    }
    
    @Test
    public void testConstructor1Arg() {
        final var arr1 = new DoubleLinkedList<Integer>(20);
        assertEquals(1, arr1.length());
        assertEquals(20, arr1.get(0));
    }
    
    @Test
    public void testAdd() {
        
        final var arr1 = new DoubleLinkedList<Integer>();        
        final var intsToAdd = IntStream.rangeClosed(0, 100).toArray();
        
        arr1.add(-1);
        assertEquals(1, arr1.length());
        assertEquals(-1, arr1.get(0));
        
        arr1.add(10);
        assertEquals(2, arr1.length());
        assertEquals(-1, arr1.get(0));
        assertEquals(10, arr1.get(1));
        
        arr1.add(-23);
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
        assertEquals(0, arr1.length());
        
        for (final var val : intsToAdd) {
            arr1.push(val);
        }
        
        assertEquals(intsToAdd.length, arr1.length());
        
        for (int ii=0; ii<intsToAdd.length; ii++) {
            assertEquals(intsToAdd[ii], arr1.get(ii));
        }
        
        final var itr = arr1.iterator();
        for (int ii=0; ii<intsToAdd.length; ii++) {
            assertTrue(itr.hasNext());
            assertEquals(intsToAdd[ii], itr.next());
        }
        
        arr1.clear();
        assertEquals(0, arr1.length());
    }
    
    @Test
    public void testInsert() {
        
        final var intsToAdd = IntStream.range(0, 10).toArray();
        final var arr1 = new DoubleLinkedList<Integer>();
        
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
        
        final var arr = new DoubleLinkedList<Integer>();
        
        final Integer[] intArr0 = {1,2,3,10};        
        arr.addAll(intArr0);
        
        for (int ii=0; ii<intArr0.length; ii++) {
            assertEquals(intArr0[ii], arr.get(ii));
        }
        
        final Integer[] intArr1 = {1,3,10};
        arr.remove(1);
        
        assertEquals(3, arr.length());
        
        for (int ii=0; ii<intArr1.length; ii++) {
            assertEquals(intArr1[ii], arr.get(ii));
        }
        
        final Integer[] intArr2 = {1, 3};
        arr.pop();
        assertEquals(2, arr.length());
        for (int ii = 0; ii < intArr2.length; ii++) {
            assertEquals(intArr2[ii], arr.get(ii));
        }
        
        final Integer[] intArr3 = {3};
        arr.remove(0);
        assertEquals(1, arr.length());
        for (int ii = 0; ii < intArr3.length; ii++) {
            assertEquals(intArr3[ii], arr.get(ii));
        }
        
        arr.pop();
        assertEquals(0, arr.length());
    }
    
    @Test
    public void testRemoveObj() {
        final var arr = new DoubleLinkedList<Integer>();
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
        final var arr = new DoubleLinkedList<Integer>();
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
    public void queueTest() {
        final QueueInterface<Integer> queue = new DoubleLinkedList<>();
        assertEquals(0, queue.length());
        assertTrue(queue.isEmpty());
        
        final int[] array = {-10, 3, 0, 4};
        
        queue.add(array[0]);
        assertEquals(1, queue.length());
        assertEquals(-10, queue.peek());
        assertEquals(-10, queue.peekTail());
        
        queue.add(array[1]);
        assertEquals(2, queue.length());
        assertEquals(-10, queue.peek());
        assertEquals(3, queue.peekTail());
        
        queue.add(array[2]);
        queue.add(array[3]);
        assertEquals(array.length, queue.length());
        assertEquals(-10, queue.peek());
        assertEquals(4, queue.peekTail());
        
        // remove elements
        assertEquals(-10, queue.deQueue());
        assertEquals(3, queue.length());
        assertEquals(3, queue.peek());
        assertEquals(4, queue.peekTail());
        
        assertEquals(3, queue.deQueue());
        assertEquals(2, queue.length());
        assertEquals(0, queue.peek());
        assertEquals(4, queue.peekTail());
        
        assertEquals(0, queue.deQueue());
        assertEquals(1, queue.length());
        assertEquals(4, queue.peek());
        assertEquals(4, queue.peekTail());
        
        assertEquals(4, queue.deQueue());
        assertEquals(0, queue.length());
        assertNull(queue.peek());
        assertNull(queue.peekTail());
        
        assertNull(queue.deQueue());
    }
    
    @Test
    public void testToString() {
        final var arr = new int[]{-1, 3, 10, 23};
        
        final var list = new DoubleLinkedList<Integer>();
        for (final var val : arr) {
            list.add(val);
        }
        
        assertEquals("[-1, 3, 10, 23]", list.toString());
    }
    
    @Test
    public void testRemoveDuplicates() {
        final int[] array = {-10, 0, -1, 3, 4, 0, 1, 2, 3, 20, 33, 20};
        final int[] arrayUnique = {-10, 0, -1, 3, 4, 1, 2, 20, 33};
        
        final var list = new DoubleLinkedList<Integer>();
        assertEquals(0, list.removeDuplicates());
        for (final var val  : array) {
            list.push(val);
        }
        
        assertEquals(array.length - arrayUnique.length, list.removeDuplicates());
        
        final var itr = list.iterator();
        for (final var val : arrayUnique) {
            assertEquals(val, itr.next());
        }
    }
    
    @Test
    public void testGetNthFromLast() {
        final int[] array = {-10, 2, 0, 4,-1};
        
        final var list = new DoubleLinkedList<Integer>();
        
        for (final int val : array) {
            list.push(val);
        }
        
        assertEquals(-1, list.getNthFromLast(0));
        assertEquals(4, list.getNthFromLast(1));
        assertEquals(0, list.getNthFromLast(2));
        assertEquals(2, list.getNthFromLast(3));
        assertEquals(-10, list.getNthFromLast(4));
    }
    
    @Test
    public void testPartitionAroundX() {
        // test --tests *DoubleLinkedListTest.testPartitionAroundX --debug-jvm
        final int[] array = {3, 5, 8, 5, 10, 2, 1};
        final var list = new DoubleLinkedList<Integer>();
        for (final var val : array) {
            list.push(val);
        }
        
        list.partitionAroundX(5);
        assertEquals(1, list.peek());
        assertEquals(10, list.peekTail());
        System.out.println(list.toString());
    }
    
    @Test
    public void testFindUnion() {
        
        final int[] arr1 = IntStream.rangeClosed(0, 100).toArray();
        final int[] arr2 = IntStream.rangeClosed(80, 200).toArray();
        
        final var list1 = new DoubleLinkedList<Integer>();
        final var list2 = new DoubleLinkedList<Integer>();
        
        for (final int val : arr1) {
            list1.push(val);
        }
        
        for (final int val: arr2) {
            list2.push(val);
        }
        
        final Set<Integer> union = ListInterface.findUnion(list1, list2);
        
        assertEquals(21, union.size());
        
        for (int ii=80; ii<=100; ii++) {
            assertTrue(union.contains(ii));
        }
        
        System.out.println("union: " + union);
    }

}
