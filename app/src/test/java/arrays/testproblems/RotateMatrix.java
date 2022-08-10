/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arrays.testproblems;

import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author daniel.holden.reg
 */
public class RotateMatrix {
    
    public static final int [][] array1 = { {1,2,3}, {4,5,6}, {7,8,9} };
    public static final int [][] sol1 = { {7,4,1}, {8,5,2}, {9,6,3} };
    
    public static final int [][] array2 = { {-1,4}, {17,2} };
    public static final int [][] sol2 = { {17, -1}, {2, 4} };
    
    public static final int [][] array3 = { {1,2,3,4}, {5,6,7,8}, {9,10,11,12}, {13,14,15,16}};
    public static final int [][] sol3 = { {13,9,5,1}, {14,10,6,2}, {15,11,7,3}, {16,12,8,4}};
    
    @Test
    public void test1() {        
        assertMatricesEqual(sol1, rotate2Dmatrix(array1));        
    }
    
    @Test
    public void test2() {
        assertMatricesEqual(sol2, rotate2Dmatrix(array2));
    }
    
    @Test
    public void test3() {
        assertMatricesEqual(sol3, rotate2Dmatrix(array3));
    }
    
    public static final int[][] rotate2Dmatrix(final int[][] mat) {
        
        final int n = mat.length; // matrix size is n x n
        
        final int[][] sol = new int[n][n];
        
        for (int ii=0; ii< n; ii++) {
            if (mat[ii].length!=n) {
                return null;
            }
            for (int jj=0; jj<n; jj++) {               
                sol[ii][jj] = mat[n-1-jj][ii];                
            }
        }
        
        return sol;
        
    }
    
    
    public void assertMatricesEqual(final int[][] mat1Ref, final int[][] check) {        
        final int n = mat1Ref.length;
        assertEquals(n, check.length);
        
        
        for (int ii=0; ii<n; ii++) {
            
            final int[] innerArrayRef = mat1Ref[ii];
            final int[] checkMat = check[ii];
            
            Assertions.assertTrue(Arrays.equals(innerArrayRef, checkMat));           
        }        
    }
    
}
