/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphs;

import java.io.File;
import java.time.Duration;
import java.time.Instant;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author daniel.holden.reg
 */
public class ConnectedComponentsTest {
 
    static final int numSites = 10;
    static final int[][] connectionArray = { {4,3}, {3,8}, {6,5}, {9,4}, {2,1},
            {8,9}, {5,0}, {7,2}, {6,1}, {1,0}, {6,7}
    };
    
    static final File mediumFile = 
            new File(ConnectedComponentsTest.class.getClassLoader().getResource("mediumUF.txt").getFile());
    
    static final File largeFile
            = new File(ConnectedComponentsTest.class.getClassLoader().getResource("largeUF.txt").getFile());
    
    @Test
    public void testSimpleList() {
        final var connections1 = new ConnectedComponents.SimpleList(numSites);
        
        for (int[] connectionArray1 : connectionArray) {
            connections1.connectSites(connectionArray1[0], connectionArray1[1]);
        }
        
        assertEquals(2, connections1.getNumComponents());
        assertTrue(connections1.connected(0, 6));
        assertTrue(connections1.connected(2, 5));
        assertFalse(connections1.connected(2, 4));
        assertTrue(connections1.connected(4, 8));
        assertTrue(connections1.connected(9, 3));
    }
    
    @Test
    public void testHashSets() {
        final var connections1 = new ConnectedComponents.HashSets(numSites);

        for (int[] connectionArray1 : connectionArray) {
            connections1.connectSites(connectionArray1[0], connectionArray1[1]);
        }

        assertEquals(2, connections1.getNumComponents());
        assertTrue(connections1.connected(0, 6));
        assertTrue(connections1.connected(2, 5));
        assertFalse(connections1.connected(2, 4));
        assertTrue(connections1.connected(4, 8));
        assertTrue(connections1.connected(9, 3));
        assertEquals(2, connections1.hashMap.size());
    }

    @Test
    public void testLinkedLists() {
        final var connections1 = new ConnectedComponents.LinkedLists(numSites);

        for (int[] connectionArray1 : connectionArray) {
            connections1.connectSites(connectionArray1[0], connectionArray1[1]);
        }

        assertEquals(2, connections1.getNumComponents());
        assertTrue(connections1.connected(0, 6));
        assertTrue(connections1.connected(2, 5));
        assertFalse(connections1.connected(2, 4));
        assertTrue(connections1.connected(4, 8));
        assertTrue(connections1.connected(9, 3));
        assertEquals(2, connections1.hashMap.size());
    }
    
    @Test
    public void mediumTest1() throws Exception {
        final var connections = ConnectedComponents.buildFromFile(mediumFile, 
                ConnectedComponents.HashSets::new);
        assertEquals(3, connections.getNumComponents());
    }
    
    @Test
    public void largeTest1() throws Exception {
        final var time1 = Instant.now();
        final var connections = ConnectedComponents.buildFromFile(largeFile, 
                ConnectedComponents.HashSets::new);
        final var time2 = Instant.now();
        assertEquals(6, connections.getNumComponents());
        
        System.out.println("hash sets time: " + Duration.between(time1, time2).toMillis());
    }
    
    @Test
    public void mediumTest2() throws Exception {
        
        final var connections = ConnectedComponents.buildFromFile(mediumFile,
                ConnectedComponents.LinkedLists::new);
        assertEquals(3, connections.getNumComponents());   
    }

    @Test
    public void largeTest2() throws Exception {
        final var time1 = Instant.now();
        final var connections = ConnectedComponents.buildFromFile(largeFile,
                ConnectedComponents.LinkedLists::new);
        final var time2 = Instant.now();
        assertEquals(6, connections.getNumComponents());        
        
         System.out.println("linked lists time: " + Duration.between(time1, time2).toMillis());
    }
}

