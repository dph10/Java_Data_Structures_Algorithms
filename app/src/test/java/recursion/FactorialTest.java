/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package recursion;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author daniel.holden.reg
 */
public class FactorialTest {
    
    @Test
    public void testFactorial1() {
        assertEquals(6, factorialRecursion(3));
        assertEquals(40_320, factorialRecursion(8));    
        assertEquals(1, factorialRecursion(0));
    }
    
    @Test
    public void testInvalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            final var result = factorialRecursion(-3);
        });
    }
    
    public static long factorialRecursion(final long val) {
        if (val==1 || val==0) {
            return 1L;
        } else if (val<0) {
            throw new IllegalArgumentException("Value must be greater than or equal to 0!");
        } 
        else {
            return val*factorialRecursion(val-1);
        }
    }
    
}
