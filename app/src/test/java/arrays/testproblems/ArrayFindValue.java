/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arrays.testproblems;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author daniel.holden.reg
 */
public class ArrayFindValue {
    
    public static final int[] testArray = {1000, -10, 2, -4, 0, 3, 1, 7, -4, 18, 3, -21};    
    public static final int[] test2 = {1,2,3,4,5,6};
    
    
    @Test
    public void test1() {        
        Assertions.assertEquals(6, findArrayValue(testArray, 1));        
    }
    
    @Test
    public void test2() {
        Assertions.assertEquals(5, findArrayValue(test2, 6));
    }
    
    @Test
    public void testNotPresent() {
        Assertions.assertEquals(-1, findArrayValue(testArray, 17));
    }
    
    @Test
    public void testEmpty() {
        Assertions.assertEquals(-1, findArrayValue(new int[0], 10));
    }
    
    public static int findArrayValue(final int[] array, final int target) {
        
        for (int ii=0; ii< array.length; ii++) {
            if (array[ii] == target) {
                return ii;
            }
        }
        
        return -1;
    }
    
}
