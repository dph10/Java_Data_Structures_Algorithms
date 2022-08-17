/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package maps;


/**
 *
 * @author daniel.holden.reg
 */
public interface OrderedMap<K extends Comparable<K>, V> extends MapInterface<K,V> {
    
    public K min();
    
    public K max();
    
    public int rank(K keyRank);
    
    public K ceiling(K key);
    
    public K floor(K key);
    
    Iterable<K> keys(K low, K high);
    
    @Override
    public default Iterable<K> keys() {
        return keys(this.min(), this.max());
    }
    
    public K select(final int rank);
    
}
