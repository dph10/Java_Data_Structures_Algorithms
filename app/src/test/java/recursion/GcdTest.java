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
public class GcdTest {
    
    @Test
    public void test1() {
        assertEquals(6, gcd(18, 48));
        assertEquals(5, gcd(1230, 655));
    }
    
    public static int gcd(final int x, final int y) {
        
        if (x <0 || y<0) {
            throw new IllegalArgumentException("Method parameters must be positive integers");
        }
        
        int min, max;
        
        if (x<=y) {
            min=x;
            max=y;
        } else {
            min=y;
            max=x;
        }
        
        if (min==0) {
            return max;
        } else {
            
            final int remainder = max % min;
            if (remainder == 0) {
                return min;
            } else {
                return gcd(min, remainder);
            }
            
        }
        
        
    }
    
}
