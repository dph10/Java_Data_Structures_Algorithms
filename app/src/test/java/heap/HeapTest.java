/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package heap;

import arrays.ListInterface;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author daniel.holden.reg
 */
public class HeapTest {
    
    private static String[] basicExample;
    
    @BeforeEach
    public void startup() {
        basicExample = new String[]{"S","O","R","T","E","X","A","M","P","L","E"};
    }
    
    @Test
    public void testConstructor() {
        
        final var heap = new Heap<String>();
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.length());
        assertNull(heap.peek());
        assertEquals(10, heap.capacity());
        
        for (final var s : basicExample) {
            heap.add(s);
        }
        
        assertEquals(40, heap.capacity());
        assertEquals(basicExample.length, heap.length());
        assertFalse(heap.isEmpty());
        
        assertEquals("X", heap.peek());
        assertEquals("X", heap.deQueue());
        assertEquals(basicExample.length-1, heap.length());

        assertEquals("T", heap.peek());
        assertEquals("T", heap.deQueue());
        assertEquals(basicExample.length-2, heap.length());

        assertEquals("S", heap.peek());
        assertEquals("S", heap.deQueue());
        assertEquals(basicExample.length-3, heap.length());

        assertEquals("R", heap.peek());
        assertEquals("R", heap.deQueue());
        assertEquals(basicExample.length-4, heap.length());

        assertEquals("P", heap.peek());
        assertEquals("P", heap.deQueue());
        assertEquals(basicExample.length-5, heap.length());

        assertEquals("O", heap.peek());
        assertEquals("O", heap.deQueue());
        assertEquals(basicExample.length-6, heap.length());

        assertEquals("M", heap.peek());
        assertEquals("M", heap.deQueue());
        assertEquals(basicExample.length-7, heap.length());

        assertEquals("L", heap.peek());
        assertEquals("L", heap.deQueue());
        assertEquals(basicExample.length-8, heap.length());

        assertEquals("E", heap.peek());
        assertEquals("E", heap.deQueue());
        assertEquals(basicExample.length-9, heap.length());

        assertEquals("E", heap.peek());
        assertEquals("E", heap.deQueue());
        assertEquals(basicExample.length-10, heap.length());

        assertEquals("A", heap.peek());
        assertEquals("A", heap.deQueue());
        assertEquals(0, heap.length());
        
        assertTrue(heap.isEmpty());
    }
    
    @Test
    public void testHeapSort() {
        final var solution = new String[]{"A","E","E","L","M","O","P","R","S","T","X"};
        
        Heap.heapSort(basicExample);
        assertTrue(ListInterface.isSorted(basicExample));
        assertTrue(Arrays.equals(solution, basicExample));
    }
    
}
