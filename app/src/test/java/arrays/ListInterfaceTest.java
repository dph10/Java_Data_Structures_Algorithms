/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arrays;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author daniel.holden.reg
 */
public class ListInterfaceTest {

    private static String[] basicExample;

    @BeforeEach
    public void startup() {
        basicExample = new String[]{"S", "O", "R", "T","I","N","G","_", "E", "X", 
            "A", "M", "P", "L", "E"};
    }
    
    @Test
    public void testSelectionSort() {
        ListInterface.SelectionSort(basicExample);
        assertTrue(ListInterface.isSorted(basicExample));
    }
    
    @Test
    public void testInsertionSort() {
        ListInterface.InsertionSort(basicExample);
        assertTrue(ListInterface.isSorted(basicExample));
    }
    
    @Test
    public void testShellSort() {
        ListInterface.ShellSort(basicExample);
        assertTrue(ListInterface.isSorted(basicExample));
    }
}
