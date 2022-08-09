/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arrays;

/**
 *
 * @author daniel.holden.reg
 */
public class ArrayUtils {
    
    public static int[] reverseArray(final int[] intArray) {
        
        final int length = intArray.length;
        if (length==0 || length==1) {
            return intArray;
        }
        
        for (int ii=0; ii < (intArray.length)/2; ii++) {
            
            final int endIndex = intArray.length-1-ii;
            
            final int copyVal = intArray[ii];
            intArray[ii] = intArray[endIndex];
            intArray[endIndex]=copyVal;
            
        }
        
        return intArray;
        
    }
    
}
