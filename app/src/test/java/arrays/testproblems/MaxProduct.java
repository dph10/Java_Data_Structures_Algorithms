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
public class MaxProduct {
    
    public static final int[] testArray = {10,20,30,40,50};
    public static String solution="(40,50)";
    
    public static final int [] testArray2 = {100, 0, 3, 2, 6, 10, 20};
    public static String solution2 = "(20,100)";
    
    @Test
    public void test1() {
        Assertions.assertEquals(solution, findMaxProduct(testArray));
        Assertions.assertEquals(solution, findMaxProductFast(testArray));
    }
    
    @Test
    public void test2() {
        Assertions.assertEquals(solution2, findMaxProduct(testArray2));
        Assertions.assertEquals(solution2, findMaxProductFast(testArray2));
    }
    
    // complexity is O(n^2) when n is length of array
    public static String findMaxProduct(final int[] array) {
        
        if (array.length<2) return "array length must be greater than 2";
        
        int maxProduct = 0;
        int val1=0;
        int val2=0;
        
        for (int ii=0; ii<array.length; ii++) {
            for (int jj=ii+1; jj<array.length; jj++) {
                
                final int testProduct = array[ii]*array[jj];
                if (testProduct<0) {
                    return "all array elements must be greater than 0";
                }
                if (testProduct > maxProduct) {
                    maxProduct=testProduct;
                    val1=array[ii];
                    val2=array[jj];
                }
                
            }
        }
        return printSolution(val1, val2);
    }
    
    public static String findMaxProductFast(final int[] array) {
        if (array.length<2) return "array length must be greater than 2";
        
        int maxVal1=0;
        int maxVal2=0;
        
        for (int ii=0; ii<array.length; ii++) {
            final int testVal = array[ii];
            if (testVal < 0) return "all array elements must be greater than 0";
            if (testVal > maxVal1) {
                final int previousMax = maxVal1;
                maxVal1 = testVal;
                if (previousMax > maxVal2) {
                    maxVal2 = previousMax;
                }
            } else if (testVal > maxVal2){
                maxVal2 = testVal;
            }
        }
        
        return printSolution(maxVal1, maxVal2);        
    }
    
    public static final String printSolution(final int val1, final int val2) {
        if (val1>=val2) {
            return new StringBuilder("(").append(val2).append(",").append(val1).append(")").toString();
        } else {
            return new StringBuilder("(").append(val1).append(",").append(val2).append(")").toString();
        }
    }
    
}
