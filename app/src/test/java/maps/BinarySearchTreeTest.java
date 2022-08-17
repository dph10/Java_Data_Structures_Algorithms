/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package maps;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 *
 * @author daniel.holden.reg
 */
public class BinarySearchTreeTest {
    
    static BinarySearchTree<Integer, String> testTree;
    
    @Test
    public void testPutInternal() {
        testTree = new BinarySearchTree<>();
        assertNull(testTree.put(10, "ten"));
        
        assertEquals(1, testTree.size());
        assertEquals(10, testTree.getRoot().getKey());
        assertEquals("ten", testTree.getRoot().getValue());
        
        assertNull(testTree.put(6, "six"));
        assertNull(testTree.put(12, "twelve"));
        
        assertEquals(3, testTree.size());
        assertEquals(10, testTree.getRoot().getKey());
        
        assertEquals(6, testTree.getRoot().getLeftChildNode().getKey());
        assertEquals(testTree.getRoot(), testTree.getRoot().getLeftChildNode().getParentNode());
    
        assertEquals(12, testTree.getRoot().getRightChildNode().getKey());
        assertEquals(testTree.getRoot(), testTree.getRoot().getRightChildNode().getParentNode());
        
        // replace root node
        assertEquals("ten", testTree.put(10, "TEN"));
        assertEquals("TEN", testTree.getRoot().getValue());
        assertEquals(6, testTree.getRoot().getLeftChildNode().getKey());
        assertEquals(testTree.getRoot(), testTree.getRoot().getLeftChildNode().getParentNode());
    
        assertEquals(12, testTree.getRoot().getRightChildNode().getKey());
        assertEquals(testTree.getRoot(), testTree.getRoot().getRightChildNode().getParentNode());
        assertEquals(1, testTree.getRoot().getLeftChildNode().size());
        assertEquals(1, testTree.getRoot().getRightChildNode().size());
        
        assertTreeRecursive(testTree);
        
        assertEquals(6, testTree.min());
        assertEquals(12, testTree.max());
        
        final var keyList = (List<Integer>)testTree.keys();
        assertEquals(3, keyList.size());
        assertEquals(6, keyList.get(0));
        assertEquals(10, keyList.get(1));
        assertEquals(12, keyList.get(2));
        
    }
    
    @Test   
    public void testPut() {
        testTree = new BinarySearchTree<>();
        buildTree(testTree);
        assertTreeRecursive(testTree);
        
        assertEquals("eight", testTree.put(8, "EIGHT"));
        assertEquals("EIGHT", testTree.get(8));
        
        assertEquals("fifteen", testTree.put(15, "FIFTEEN"));
        assertEquals("FIFTEEN", testTree.get(15));
        assertEquals(16, testTree.size());
        assertTreeRecursive(testTree);
    }
    
    @Test
    public void testPutIfAbsent() {
        testTree = new BinarySearchTree<>();
        buildTree(testTree);
        assertTreeRecursive(testTree);

        assertEquals("eight", testTree.putIfAbsent(8, "EIGHT"));
        assertEquals("eight", testTree.get(8));

        assertEquals("fifteen", testTree.putIfAbsent(15, "FIFTEEN"));
        assertEquals("fifteen", testTree.get(15));
        assertEquals(16, testTree.size());
        assertTreeRecursive(testTree);
        
        assertNull(testTree.putIfAbsent(16, "sixteen"));
        assertNull(testTree.putIfAbsent(2, "two"));
        
        assertEquals(18, testTree.size());
        assertEquals("sixteen", testTree.get(16));
        assertEquals("two", testTree.get(2));
        assertTreeRecursive(testTree);
    }
    
    @Test
    public void testGet() {
        testTree = new BinarySearchTree<>();
        buildTree(testTree);
        assertTreeRecursive(testTree);
        
        assertEquals("three", testTree.get(3));
        assertEquals("five", testTree.get(5));
        assertEquals("seven", testTree.get(7));
        assertEquals("nine", testTree.get(9));
        assertEquals("four", testTree.get(4));
        assertEquals("six", testTree.get(6));
        assertEquals("ten", testTree.get(10));
        assertEquals("twelve", testTree.get(12));
        assertEquals("eleven", testTree.get(11));
        assertEquals("fifteen", testTree.get(15));
        assertEquals("thirteen", testTree.get(13));
        assertEquals("twenty", testTree.get(20));
        assertEquals("eighteen", testTree.get(18));
        assertEquals("twenty-one", testTree.get(21));
        assertNull(testTree.get(2));
        assertNull(testTree.get(16));
    }
    
    @Test
    public void testContains() {
        testTree = new BinarySearchTree<>();
        buildTree(testTree);
        assertTreeRecursive(testTree);

        assertTrue(testTree.contains(3));
        assertTrue(testTree.contains(5));
        assertTrue(testTree.contains(7));
        assertTrue(testTree.contains(9));
        assertTrue(testTree.contains(4));
        assertTrue(testTree.contains(6));
        assertTrue( testTree.contains(10));
        assertTrue(testTree.contains(12));
        assertTrue(testTree.contains(11));
        assertTrue(testTree.contains(15));
        assertTrue(testTree.contains(13));
        assertTrue(testTree.contains(20));
        assertTrue( testTree.contains(18));
        assertTrue(testTree.contains(21));
        assertFalse(testTree.contains(2));
        assertFalse(testTree.contains(16));
    }
    
    @Test
    public void testRemove1() {
        testTree = new BinarySearchTree<>();
        assertNull(testTree.put(10, "ten"));
        assertNull(testTree.put(6, "six"));
        assertNull(testTree.put(12, "twelve"));
        assertNull(testTree.put(11, "eleven"));
        assertEquals(4, testTree.size());
        
        assertNull(testTree.remove(20));
        assertEquals(4, testTree.size());
        assertTreeRecursive(testTree);
        
    }
    
    @Test
    public void testRemove2() {
        // test --tests *BinarySearchTreeTest.testRemove2 --debug-jvm
        testTree = new BinarySearchTree<>();
        assertNull(testTree.put(10, "ten"));
        assertNull(testTree.put(6, "six"));
        assertNull(testTree.put(12, "twelve"));
        assertNull(testTree.put(11, "eleven"));
        assertEquals(4, testTree.size());
        
        assertEquals("six", testTree.remove(6));
        assertEquals(3, testTree.size());
        assertTreeRecursive(testTree);
    }
    
    @Test
    public void testRemove3() {
        testTree = new BinarySearchTree<>();
        assertNull(testTree.put(10, "ten"));
        assertNull(testTree.put(6, "six"));
        assertNull(testTree.put(12, "twelve"));
        assertNull(testTree.put(11, "eleven"));
        assertEquals(4, testTree.size());

        assertEquals("eleven", testTree.remove(11));
        assertEquals(3, testTree.size());
        assertTreeRecursive(testTree);
    }
    
    public void testRemoveRotation() {
        testTree = new BinarySearchTree<>();
        assertNull(testTree.put(10, "ten"));
        assertNull(testTree.put(6, "six"));
        assertNull(testTree.put(12, "twelve"));
        assertNull(testTree.put(11, "eleven"));
        assertNull(testTree.put(15, "fifteen"));
        assertEquals(5, testTree.size());

        assertEquals("twelve", testTree.remove(12));
        assertEquals(4, testTree.size());
        assertTreeRecursive(testTree);
    }
    
    @Test
    public void testRemoveFull() {
        // test --tests *BinarySearchTreeTest.testRemoveFull --debug-jvm
        
        testTree = new BinarySearchTree<>();
        buildTree(testTree);
        assertEquals(16, testTree.size());
        
        assertNull(testTree.remove(17));
        assertNull(testTree.remove(-1));
        assertEquals(16, testTree.size());
        assertTreeRecursive(testTree);
        
        assertEquals("twelve", testTree.remove(12));
        assertEquals("eight", testTree.remove(8));
        
        assertEquals(14, testTree.size());
        assertTreeRecursive(testTree);
        
        assertEquals("fifteen", testTree.remove(15));
        assertEquals(13, testTree.size());
        assertTreeRecursive(testTree);
        
        final var keyList = testTree.keys();
        int previousVal = Integer.MIN_VALUE;
        for (final int val: keyList) {
            assertTrue(val > previousVal);
            previousVal = val;
        }
    }
    
    @Test
    public void testKeys() {
        testTree = new BinarySearchTree<>();
        buildTree(testTree);
        
        List<Integer> keyList = (List<Integer>) testTree.keys();
        
        int previouValue = Integer.MIN_VALUE;
        
        for (final int val : keyList) {
            assertTrue(val > previouValue);
            previouValue = val;
        }
        
        keyList = (List<Integer>)testTree.keys(8, 16);
        previouValue = 7;
        for (final int val : keyList) {
            assertTrue(val > previouValue);
            assertTrue(val <= 16);
            previouValue = val;
        }
        assertEquals(keyList.get(keyList.size()-1), 15);
    }
    
    @Test
    public void testMin() {
        testTree = new BinarySearchTree<>();
        buildTree(testTree);
        assertEquals(3, testTree.min());
    }
    
    @Test
    public void testMax() {
        assertEquals(21, testTree.max());
    }
    
    @Test
    public void testCeiling() {
        testTree = new BinarySearchTree<>();
        buildTree(testTree);
        
        assertEquals(18, testTree.ceiling(17));
        assertEquals(4, testTree.ceiling(4));
        assertNull(testTree.ceiling(40));
    }
    
    @Test
    public void testFloor() {
        testTree = new BinarySearchTree<>();
        buildTree(testTree);
        assertEquals(3, testTree.floor(3));
        assertEquals(15, testTree.floor(16));
        assertNull(testTree.floor(-1));
    }
    
    public <K extends Comparable<K>,V> void assertTreeRecursive(final BinarySearchTree<K,V> checkTree) {        
        assertTreeRecursive(checkTree.getRoot());        
    }
    
    public <K extends Comparable<K>,V> void assertTreeRecursive(final BinarySearchTree.Node<K,V> checkNode) {
        
        if (checkNode==null) {
            return;
        }
        
        final StringBuilder sb = new StringBuilder("node ").append("(").append(checkNode.getKey()).append("=")
                .append(checkNode.getValue()).append(", size=").append(checkNode.size()).append(")");
        
        if (checkNode.hasLeftChild()) {
            sb.append(" left=").append(checkNode.getLeftChildNode().getKey());
        }
        if(checkNode.hasRightChild()) {
            sb.append(" right=").append(checkNode.getRightChildNode().getKey());
        }
        
        
        System.out.println(sb.toString());
        if (checkNode.size()==1) {
            assertTrue(checkNode.noChildren());
        }
        
        if (checkNode.hasLeftChild()) {            
            assertEquals(checkNode, checkNode.getLeftChildNode().getParentNode());            
            final int compareVal = checkNode.getKey().compareTo(checkNode.getLeftChildNode().getKey());
            assertTrue(compareVal > 0);            
        }
        
        if (checkNode.hasRightChild()) {
            assertEquals(checkNode, checkNode.getRightChildNode().getParentNode());
            final int compareVal = checkNode.getKey().compareTo(checkNode.getRightChildNode().getKey());
            assertTrue(compareVal < 0);
        }
        
        assertTreeRecursive(checkNode.getLeftChildNode());
        assertTreeRecursive(checkNode.getRightChildNode());
        
    }
    
    public void buildTree(final BinarySearchTree<Integer, String> tree) {
        
        assertNull(tree.put(10, "ten"));
        assertNull(tree.put(6, "six"));
        assertNull(tree.put(12, "twelve"));
        assertNull(tree.put(11, "eleven"));
        assertNull(tree.put(4, "four"));
        assertNull(tree.put(8, "eight"));
        assertNull(tree.put(7, "seven"));
        assertNull(tree.put(9, "nine"));
        assertNull(tree.put(3, "three"));
        assertNull(tree.put(5, "five"));
        assertNull(tree.put(15, "fifteen"));
        assertNull(tree.put(13, "thirteen"));
        assertNull(tree.put(14, "fourteen"));
        assertNull(tree.put(20, "twenty"));
        assertNull(tree.put(18, "eighteen"));
        assertNull(tree.put(21, "twenty-one"));
        
        assertEquals(16, tree.size());
        
    }
    
}
