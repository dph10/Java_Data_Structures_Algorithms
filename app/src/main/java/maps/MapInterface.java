/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package maps;

/**
 *
 * @author daniel.holden.reg
 */
public interface MapInterface<K,V> {
    
    public int size();
    
    public default boolean isEmpty() {
        return this.size() == 0;
    }
    
    public V put(K key, V value);
    
    public V putIfAbsent(K key, V value);
    
    public V get(K key);
    
    public boolean contains(K key);
    
    public V remove(K key);
    
    public Iterable<K> keys();
    
}
