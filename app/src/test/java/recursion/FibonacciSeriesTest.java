/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package recursion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author daniel.holden.reg
 */
public class FibonacciSeriesTest {
    
    @Test
    public void testFibb1() {
        Assertions.assertEquals(2, fibbRecursive(3));
        Assertions.assertEquals(3, fibbRecursive(4));
    }
    
    public static long fibbRecursive(final long val) {
        if (val < 0) {
            throw new IllegalArgumentException("Fibonacci only defined for positive integers");
        } else if (val <= 1) {
            return val;
        }
        return fibbRecursive(val-1) + fibbRecursive(val-2);
    }
    
}
