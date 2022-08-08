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
public class BinaryTest {
    
    
    @Test
    public void test1() {
        Assertions.assertEquals(1101, decimalToBinary(13));
        Assertions.assertEquals(10_0110_1101, decimalToBinary(621));
        Assertions.assertEquals(-1, decimalToBinary(-12));
    }
    
    public static int decimalToBinary(final int val) {
        if (val < 0) {
            return -1;
        }
        
        else if (val<2) {
            return val % 2;
        }
        else {  
            return val % 2 + 10*decimalToBinary(val/2);
        }
    }
}
