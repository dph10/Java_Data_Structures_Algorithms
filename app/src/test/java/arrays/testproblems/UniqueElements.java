/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arrays.testproblems;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author daniel.holden.reg
 */
public class UniqueElements {
    
    static final int[] intArray = {1,6,2,5,3,4};
    
    static final int[] test2array ={-10, 2, 1000, -100_200, 0, 1, -7, 4};
    
    static final int[] test3array ={0, 2, 1000, -100_200, 0, 1, -7, 4};
    
    @Test
    public void test1() {
        
        Assertions.assertTrue(isUnique(intArray));
        Assertions.assertTrue(isUnique(test2array));
        Assertions.assertFalse(isUnique(test3array));
    }
    
        
    @Test
    public void test2() {
        
        Assertions.assertTrue(isUniqueHashSet(intArray));
        Assertions.assertTrue(isUniqueHashSet(test2array));
        Assertions.assertFalse(isUniqueHashSet(test3array));
    }
    
    // solution complexity is O(n^2)
    public static boolean isUnique(final int[] array) {
        
        if (array.length==0) {
            return false;
        } else if(array.length==1) {
            return true;
        }
        
        for (int ii=0; ii<array.length; ii++) {
            final int val1 = array[ii];
            for (int jj=ii+1; jj<array.length; jj++) {
                if (val1 == array[jj]) {
                    return false;
                }
            }
        }
        return true;
        
    }
    
    public static boolean isUniqueHashSet(final int[] array) {
        if (array.length == 0) {
            return false;
        } else if (array.length == 1) {
            return true;
        }
        
        final Set<Integer> uniqueSet = new HashSet<>();
        
        for (int ii=0; ii<array.length; ii++) {
            if (!uniqueSet.add(array[ii])) return false;
        }
        
        return true;
        
    }
}
