/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arrays;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

/**
 *
 * @author daniel.holden.reg
 */
public class ArrayUtilsTest {
    
    @Test
    public void testReverseArray() {
        final int[] arr1 = {1,2,3,4,5,6,7,8};
        final int[] arr1rev = {8,7,6,5,4,3,2,1};
        
        final int [] arr2 = {1,5,-10,20,100_000,-10, -350};
        final int [] arr2rev = {-350, -10, 100_000, 20, -10, 5, 1};       
        
        final int [] arrEmpty = new int[0];
        final int [] arr1El = {17};
        
        final var res1=ArrayUtils.reverseArray(arr1);
        
        assertTrue( Arrays.equals(arr1rev, res1));
        assertTrue(Arrays.equals(arr2rev, ArrayUtils.reverseArray(arr2)));
        
        assertTrue(Arrays.equals(arrEmpty, ArrayUtils.reverseArray(arrEmpty)));
        assertTrue(Arrays.equals(arr1El, ArrayUtils.reverseArray(arr1El)));
    }
    
}
