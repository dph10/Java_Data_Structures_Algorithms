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
public class PowerTest {
    
    @Test
    public void test1() {
        assertEquals(8, powerRecursive(2, 3));
        assertEquals(2401, powerRecursive(7, 4));
        
    }
    
    public static long powerRecursive(final long val, final int pwr) {
        
        if (pwr<0) {
            throw new IllegalArgumentException("The power/exponent must be a positive integer");
        } else if(pwr==0) {
            return 1;
        }
        
        if (pwr==1) {
            return val;
        } else {
            return val * powerRecursive(val, pwr-1);
        }
        
    }
    
}
