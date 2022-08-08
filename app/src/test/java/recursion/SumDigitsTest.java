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
public class SumDigitsTest {
    
    @Test
    public void test() {
        assertEquals(16, sumDigits(1537));
        assertEquals(10, sumDigits(28));
        assertEquals(10, sumDigits(82));
    }
    
    public static long sumDigits(final long value) {
        // System.out.println(value);
        if (value <0) {
            return -1L;
        }

        final long remainder = value % 10;        
        final long shiftDecimal = value/10;
        
        if (shiftDecimal==0) {
            return value;
        } else {
            return remainder + sumDigits(shiftDecimal);
        }
    }
    
}
