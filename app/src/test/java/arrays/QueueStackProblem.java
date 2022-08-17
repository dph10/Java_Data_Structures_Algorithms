/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arrays;

import java.util.ArrayDeque;
import java.util.Deque;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author daniel.holden.reg
 */
public class QueueStackProblem {
    
    public static final int[] arr1 = {-3, 1, -1, 0, -10};
    public static final int[] arr2 = {100, 5, 1, 2, -8};
    
    @Test
    public void test1() {
        final var queue = new StackQueue();        
        assertTrue(queue.isEmpty());  
        
        // enqueue the first array
        for (final int val : arr1) {
            queue.enqueue(val);
        }
        
        assertEquals(-3, queue.peek());
        assertEquals(5, queue.size());
        
        assertEquals(-3, queue.dequeue());
        assertEquals(1, queue.dequeue());
        assertEquals(3, queue.size());
        assertEquals(-1, queue.peek());
        
        // enque the 2nd array
        for (final int val : arr2) {
            queue.enqueue(val);
        }
        
        assertEquals(-1, queue.peek());
        assertEquals(8, queue.size());
        
        assertEquals(-1, queue.dequeue());
        assertEquals(0, queue.dequeue());
        assertEquals(-10, queue.dequeue());
        
        for (final int val : arr2) {
            assertEquals(val, queue.dequeue());
        }
    }
    
    public class StackQueue {
        
        private final Deque<Integer> q1 = new ArrayDeque<>();
        private final Deque<Integer> q2 = new ArrayDeque<>();
        
        public void enqueue(final int val) {
            q1.push(val);
        }
        
        public int size() {
            return q1.size()+q2.size();
        }
        
        public int dequeue() {
            if (q2.isEmpty()) {
                if(q1.isEmpty()) {
                    return Integer.MIN_VALUE;
                }
                while(!q1.isEmpty()) {
                    q2.push(q1.pop());
                }               
            }
            return q2.pop();
        }
        
        public boolean isEmpty() {
            return this.size()==0;
        }
        
        public int peek() {
            if (q2.isEmpty()) {
                if (q1.isEmpty()) {
                    return Integer.MIN_VALUE;
                }
                return q1.peekLast();
            }
            return q2.peek();
        }
    }
}
