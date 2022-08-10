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
public class ArrayPermutation {
    
    public static final int[] array1a = {1,2,3,4,5};
    public static final int[] array1b = {5,1,2,3,4};
    
    public static final int[] array2a = {-10 , 5, 3, 2, -4, 18};
    public static final int[] array2b = {2, 3, 5, 18, -4, -10};
    public static final int[] array2c = { 3, 5, 2, -4, 25, 7};
    
    @Test
    public void test1() {
        Assertions.assertTrue(testPermutation(array1a, array1b));
        Assertions.assertFalse(testPermutation(array1a, array2a));
    }
    
    @Test
    public void test2() {
        Assertions.assertTrue(testPermutation(array2a, array2b));
        Assertions.assertFalse( testPermutation(array2a, array2c));
        Assertions.assertFalse(testPermutation(array2b, array2c));
        Assertions.assertFalse(testPermutation(array2b, array1a));
        Assertions.assertFalse(testPermutation(array2a, array1b));
    }
    
    public static boolean testPermutation(final int[] array1, final int[] array2) {
        if (array1.length != array2.length) return false;
        
        int sum1 = 0, sum2=0, prod1=1, prod2=1;
        
        for (final int val : array1) {
            sum1 += val;
            prod1 *= val;
        }
        
        for (final int val: array2) {
            sum2 += val;
            prod2 *= val;
        }
        
        return (sum1 == sum2) && (prod1==prod2);
    }
}
