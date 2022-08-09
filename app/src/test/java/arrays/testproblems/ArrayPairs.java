/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arrays.testproblems;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author daniel.holden.reg
 */
public class ArrayPairs {
    
    static final int[] input1 = {2,7,11,15};
    static final int target1= 9;
    static final int[] output1 = {0,1};
    
    
    static final int[] input2 = {3,2,4};
    static final int target2 = 6;
    static final int[] output2 = {1,2};
    
    static final int[] input3 = {4,1,3,5,5,-10, -2, 1, 6, 7, 2, 1, 4, 4, 1};
    static final int target3 = 8;
    //static final int[] output3 = {1,2,1,3};
    
    @Test
    public void test1() {
        assertTrue(Arrays.equals(output1, findPairs(input1, target1)));
        assertTrue(Arrays.equals(output1, findPairsHash(input1, target1)));
    }
    
    @Test
    public void test2() {
        assertTrue(Arrays.equals(output2, findPairs(input2, target2)));
        assertTrue(Arrays.equals(output2, findPairsHash(input2, target2)));
    }
    
    @Test
    public void test3() {
        System.out.println(Arrays.toString(findPairs(input3, target3)));
        System.out.println(Arrays.toString(findPairsHash(input3, target3)));
    }
    
    
    
    /**
     * On the order of O(N^2) where n is length of array
     * @param array
     * @param target
     * @return 
     */
    public static int[] findPairs(final int[] array, final int target) {
        
        int[] solution = {};
        
        for (int ii=0; ii<array.length; ii++) {            
            final int val1 = array[ii];
            //if (val1 < target) {                
                final int valPair = target-val1;
                for (int jj=ii+1; jj<array.length; jj++) {                    
                    if (array[jj] == valPair) {
                        solution = addToSolution(solution, ii, jj);
                    }                
                }              
            //}            
        }    
        
        return solution;
    }
    
    public static int[] findPairsHash(final int[] array, final int target) {
        
        /**
         * key: the Value of the array index
         * value: List of the array indices that have the key-value
         */
        int[] solution = {};
        final Map<Integer,List<Integer>> intMap = new LinkedHashMap<>();
        
        // first pass through array will populate the Map
        for (int ii=0; ii<array.length; ii++) {            
            final int val1 = array[ii];

            final var list = intMap.get(val1);
            if (list==null) {
                final var newList = new LinkedList<Integer>();
                newList.add(ii);
                intMap.put(val1, newList);
            } else {
                list.add(ii);
            }
            
        }
        
        // second pass through array will find all the solutions pairs
        
        while(!intMap.entrySet().isEmpty()) {
            
            final var entry = intMap.entrySet().iterator().next();            

                final int pairValue = target - entry.getKey();

                final List<Integer> indexList2 = intMap.get(pairValue);
                if (indexList2 != null) {
                    
                    if (entry.getKey().equals(pairValue)) {
                        if (indexList2.size()>1) {
                            final List<Integer> indexList1 = entry.getValue();
                            for (int ii=0; ii<indexList1.size(); ii++) {
                                for (int jj=ii+1; jj<indexList1.size(); jj++) {
                                    solution = addToSolution(solution, indexList1.get(ii), indexList1.get(jj));
                                }
                            }
                        }
                        intMap.remove(pairValue);
                    } else { 
                        
                        final List<Integer> indexList1 = entry.getValue();
                        for (int ii=0; ii<indexList1.size(); ii++) {
                            for (int jj=0; jj<indexList2.size(); jj++) {
                                solution=addToSolution(solution, indexList1.get(ii), indexList2.get(jj));
                            }
                        }
                        intMap.remove(entry.getKey());
                        intMap.remove(pairValue);
                    }
                } else {
                    intMap.remove(entry.getKey());
                }
            
        }
        
        return solution;
        
    }
    
    protected static int[] addToSolution(final int[] sol, final int val1, final int val2) {
        
        int[] solution = new int[sol.length+2];
        
        for (int ii=0; ii<sol.length; ii++) {
            solution[ii] =  sol[ii];
        }
        
        solution[solution.length-2] = val1;
        solution[solution.length-1] = val2;
        
        return solution;        
    }
}
