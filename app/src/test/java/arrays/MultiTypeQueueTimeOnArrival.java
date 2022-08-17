/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arrays;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author daniel.holden.reg
 */
public class MultiTypeQueueTimeOnArrival {
    
    
    static final Item item1a = new TypeA();
    static final Item item2a = new TypeA();
    
    static final Item item1b = new TypeB();
    static final Item item2b = new TypeB();
    static final Item item3b = new TypeB();
    
    
    @Test
    public void test() throws Exception{
        
        final var queue = new MultiTypeTOAqueue();     
        assertTrue(queue.isEmpty());
        enqueue(queue);
        assertFalse(queue.isEmpty());
        
        assertEquals(5, queue.length());
        assertEquals(item1a, queue.peek());
        
        assertEquals(item1a, queue.deQueue());
        assertEquals(item1b, queue.deQueue());
        
        assertEquals(3, queue.length());
        assertEquals(item2a, queue.peek());
        
        assertEquals(item2b, queue.deQueue(TypeB.class));
        
        assertEquals(2, queue.length());
        assertEquals(item2a, queue.peek());
        
        assertEquals(item2a, queue.deQueue(TypeA.class));
        assertNull(queue.deQueue(TypeA.class));
        
        assertEquals(item3b, queue.deQueue());
        assertTrue(queue.isEmpty());
        
    }
    
    public static void enqueue(final MultiTypeTOAqueue q1) throws InterruptedException {
        
        q1.add(item1a);
        Thread.sleep(2);
        q1.add(item1b);
        Thread.sleep(2);
        
        q1.add(item2a);
        Thread.sleep(2);
        q1.add(item2b);
        Thread.sleep(2);
        q1.add(item3b);
        
    }
    
    public static class MultiTypeTOAqueue implements QueueInterface<Item>{
        
        private final QueueInterface<Item> queueA = new DoubleLinkedList<>();
        private final QueueInterface<Item> queueB = new DoubleLinkedList<>();

        @Override
        public boolean add(final Item el) {
            el.setTimeOnArrival();
            if (el instanceof TypeA typea) {
                queueA.add(typea);
            } else {
                queueB.add(el);
            }
            return true;
        }

        @Override
        public boolean isEmpty() {
            return queueA.isEmpty() && queueB.isEmpty();
        }

        public <A extends Item> Item deQueue(final Class<A> clazz) {
            
            if (clazz.equals(TypeA.class)) {
                if (this.queueA.isEmpty()) {
                    return null;
                } else {
                    return queueA.deQueue();
                }
            } else {
                if (this.queueB.isEmpty()) {
                    return null;
                } else {
                    return queueB.deQueue();
                }
            }
            
        }
        
        @Override
        public Item deQueue() {
            if (this.isEmpty()) {
                return null;
            }
            TypeA typeA = null;
            TypeB typeB = null;
            
            if (!queueA.isEmpty()) {
                typeA = (TypeA)queueA.peek();
            }
            if (!queueB.isEmpty()) {
                typeB = (TypeB)queueB.peek();
            }
            
            if (typeA==null) {
                return queueB.deQueue();
            } else if (typeB==null) {
                return queueA.deQueue();
            } else {
                
                if (typeA.compareTo(typeB) >= 0) {
                    return queueB.deQueue();
                } else {
                    return queueA.deQueue();
                }
                
            }
        }

        @Override
        public Item peek() {
            if (this.isEmpty()) {
                return null;
            }
            TypeA typeA = null;
            TypeB typeB = null;
            
            if (!queueA.isEmpty()) {
                typeA = (TypeA)queueA.peek();
            }
            if (!queueB.isEmpty()) {
                typeB = (TypeB)queueB.peek();
            }
            if (typeA==null) {
                return typeB;
            } else if (typeB==null) {
                return typeA;
            } else {
                return typeA.compareTo(typeB) >=0 ? typeB : typeA;
            }
        }

        @Override
        public Item peekTail() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public int length() {
            return this.queueA.length() + this.queueB.length();
        }

        @Override
        public int capacity() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isFull() {
            return false;
        }

        @Override
        public int clear() {
            final int previousSize = this.length();
            this.queueA.clear();
            this.queueB.clear();
            
            return previousSize;
        }

        
        
    }
    
    public static abstract class Item implements Comparable<Item>{
        
        private long timeOnArrival = Long.MIN_VALUE;
        
        private static final AtomicInteger counter = new AtomicInteger(-1);
        
        private final int id =counter.getAndIncrement();
        
        public long getTimeOnArrival() {
            return this.timeOnArrival;
        }
        
        public int getId() {
            return this.id;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 79 * hash + this.id;
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Item other = (Item) obj;
            return this.id == other.id;
        }

        long setTimeOnArrival() {
                return this.timeOnArrival = Instant.now().toEpochMilli();    
        }

        @Override
        public int compareTo(final Item o) {
            return Long.compare(this.timeOnArrival, o.timeOnArrival);
        }
    }
    
    public static class TypeA extends Item {
        
    }
    
    public static class TypeB extends Item {
        
    }
}
