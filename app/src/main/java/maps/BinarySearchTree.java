/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package maps;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

/**
 *
 * @author daniel.holden.reg
 */
public class BinarySearchTree<K extends Comparable<K>, V> implements OrderedMap<K,V> {
 
    private Node<K,V> root = null;
    private V lastLookupValue = null;

    public BinarySearchTree() {
    }
    
    static class Node<K extends Comparable<K>, V> {
        
        private final V value;
        private final K key;
        private boolean color;
        private Node<K,V> leftChildNode;  // subtree for children less than this node's key
        private Node<K,V> rightChildNode; // subtree for children greater than this node's key
        //private Node<K,V> parentNode;
        
        private int sizeN = 1;
        
        protected Node(final K key, final V value, final boolean isRed) {
            this.key = key;
            this.value = value;
            if (this.key==null || this.value == null) {
                throw new IllegalArgumentException("Keys and values must not be null");
            }
            this.color = isRed;
            /*this.parentNode = parentNode;
            if (this.parentNode!=null) {
                if (this.key.compareTo(parentNode.key) < 0) {
                    parentNode.leftChildNode=this;
                } else {
                    parentNode.rightChildNode=this;
                }
            }*/
        }

        protected K getKey() {
            return key;
        }

        protected V getValue() {
            return value;
        }
        
        protected boolean getIsRed() {
            return this.color;
        }

        /*protected Node<K,V> getParentNode() {
            return parentNode;
        }*/

        protected Node<K,V> getLeftChildNode() {
            return leftChildNode;
        }

        protected Node<K,V> getRightChildNode() {
            return rightChildNode;
        }
        
        public boolean hasLeftChild() {
            return this.leftChildNode!=null;
        }
        
        protected boolean hasRightChild() {
            return this.rightChildNode != null;
        }
        
        protected boolean noChildren() {
            return this.leftChildNode==null && this.rightChildNode==null;
        }
        
        protected int size() {
            return this.sizeN;
        }

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 97 * hash + Objects.hashCode(this.value);
            hash = 97 * hash + Objects.hashCode(this.key);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Node<?, ?> other = (Node<?, ?>) obj;
            if (!Objects.equals(this.value, other.value)) {
                return false;
            }
            return Objects.equals(this.key, other.key);
        }
        
        @Override
        public String toString() {
            return new StringBuilder(this.key.toString())
                    .append("=").append(this.value.toString())
                    .toString();
        }
    }

    @Override
    public int size() {
        return sizeInternal(root);
    }
    
    protected static boolean getIsRed(final Node node) {
        if (node==null) return false;
        return node.getIsRed();
    }
    
    protected void flipColors(final Node<K,V> node) {
        node.color = !node.color;
        node.leftChildNode.color = !node.leftChildNode.color;
        node.rightChildNode.color = !node.rightChildNode.color;
    }
    
    protected Node<K,V> rotateLeft(final Node<K,V> h) {
        final Node<K,V> xNode = h.rightChildNode;        
        h.rightChildNode =xNode.leftChildNode;
        xNode.leftChildNode = h;
        xNode.color = h.color;
        h.color = true;
        xNode.sizeN = h.sizeN;
        h.sizeN = 1 +sizeInternal(h.leftChildNode) + sizeInternal(h.rightChildNode);
        return xNode;
    }
    
    protected Node<K,V> rotatedRight(final Node<K,V> h) {
        final Node<K,V> xNode = h.leftChildNode;
        h.leftChildNode = xNode.rightChildNode;
        xNode.rightChildNode = h;
        
        xNode.color = h.color;
        h.color = true;
        xNode.sizeN = h.sizeN;
        h.sizeN = 1 + sizeInternal(h.leftChildNode) + sizeInternal(h.rightChildNode);
        return xNode;
    }
    
    protected int sizeInternal(final Node<K,V> checkNode) {
        if (checkNode==null) {
            return 0;
        }
        return checkNode.sizeN;
    }
    
    
    @Override
    public V put(final K key, final V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Keys and values must not be null");
        }
        this.lastLookupValue=null;        
        this.root = this.putInternal(root, key, value, true);
        this.root.color = false;
        return this.lastLookupValue;
    }
    
    protected Node<K, V> balanceNode(Node<K, V> checkNode) {
        if (getIsRed(checkNode.rightChildNode) && !getIsRed(checkNode.leftChildNode)) {
            checkNode = rotateLeft(checkNode);
        }
        if (checkNode.leftChildNode!=null && getIsRed(checkNode.leftChildNode) && getIsRed(checkNode.leftChildNode.leftChildNode)) {
            checkNode = rotatedRight(checkNode);
        }
        if (getIsRed(checkNode.leftChildNode) && getIsRed(checkNode.rightChildNode)) {
            flipColors(checkNode);
        }
        checkNode.sizeN = sizeInternal(checkNode.leftChildNode) + sizeInternal(checkNode.rightChildNode)+1;
        return checkNode;
    }
    
    protected Node<K,V> putInternal(final Node<K,V> checkNode, final K key, final V value, final boolean overwrite) {
        
        if (checkNode==null) {
            return new Node(key, value, true);
        }
        
        final int compareValue = checkNode.key.compareTo(key);
        if (compareValue > 0) {
            checkNode.leftChildNode = putInternal(checkNode.leftChildNode, key, value, overwrite);
            return balanceNode(checkNode);
        } else if (compareValue < 0) {
            checkNode.rightChildNode = putInternal(checkNode.rightChildNode, key, value, overwrite);
            return balanceNode(checkNode);
        } else {
            this.lastLookupValue = checkNode.value;
            if (overwrite) {
                final Node<K, V> newNode = new Node<>(key, value, checkNode.color);
                newNode.sizeN = checkNode.sizeN;
                newNode.leftChildNode = checkNode.leftChildNode;
                newNode.rightChildNode = checkNode.rightChildNode;
                /*if (newNode.leftChildNode != null) {
                    //newNode.leftChildNode.parentNode = newNode;
                }
                if (newNode.rightChildNode != null) {
                   // newNode.rightChildNode.parentNode = newNode;
                }*/
                return balanceNode(newNode);
            } else {
                return checkNode;
            }
        }
        
        
        
    }

    @Override
    public V putIfAbsent(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Keys and values must not be null");
        }
        this.lastLookupValue = null;
        this.root = this.putInternal(root, key, value,false);
        return this.lastLookupValue;
    }

    @Override
    public V get(final K key) {
        if (key==null) {
            throw new IllegalArgumentException("Keys and values must not be null");
        }
        return this.getInternal(this.root, key);
    }
    
    private V getInternal(final Node<K,V> node, final K key) {
        if (node==null) {
            return null;
        }
        final int compareVal = node.key.compareTo(key);
        
        if (compareVal > 0) return (V) this.getInternal(node.leftChildNode, key);
        else if (compareVal < 0) return (V) this.getInternal(node.rightChildNode, key);
        else return node.value;
        
    }

    @Override
    public boolean contains(final K key) {
        return this.get(key)!=null;
    }
    
    @Override
    public int rank(final K keyRank) {        
        return this.rankInternal(this.root, keyRank);        
    }

    protected int rankInternal(final Node<K,V> nodeCheck, final K keyRank) {
        if (nodeCheck==null) {
            return 0;
        }
        final int compareValue = nodeCheck.key.compareTo(keyRank);
        if (compareValue > 0) return rankInternal(nodeCheck.leftChildNode, keyRank);
        else if (compareValue < 0) return 1 + sizeInternal(nodeCheck.leftChildNode)
                + rankInternal(nodeCheck.rightChildNode, keyRank);
        else {
            return sizeInternal(nodeCheck.leftChildNode);
        }
    }
    
    @Override
    public V remove(final K key) {
        if (this.root==null) {
            return null;
        }
        this.lastLookupValue=null;
        this.root = removeInternal(root, key);
        return this.lastLookupValue;
    }
    
    private Node<K,V> removeInternal(final Node<K,V> nodeCheck, final K key) {
        if (nodeCheck==null) {
            return null;
        }
        
        final int compareValue = nodeCheck.key.compareTo(key);
        if (compareValue>0) {
            nodeCheck.leftChildNode = removeInternal(nodeCheck.leftChildNode, key);
            return this.balanceNode(nodeCheck);
        } else if (compareValue < 0) {
            nodeCheck.rightChildNode = removeInternal(nodeCheck.rightChildNode, key);
            return this.balanceNode(nodeCheck);
        } else {
            this.lastLookupValue = nodeCheck.value;            

            if (nodeCheck.leftChildNode==null) {
                return nodeCheck.rightChildNode;
            } else if (nodeCheck.rightChildNode==null) {
                return nodeCheck.leftChildNode;
            }
            
            // if there are two children, choose its successor
            final Node<K,V> succesorNode = getMinNode(nodeCheck.rightChildNode);
            
            succesorNode.rightChildNode = deleteMin(nodeCheck.rightChildNode);
            if (succesorNode.rightChildNode!=null) {
                //succesorNode.rightChildNode.parentNode=succesorNode;
            }            
            succesorNode.leftChildNode=nodeCheck.leftChildNode;
            //succesorNode.leftChildNode.parentNode = succesorNode;
            return this.balanceNode(succesorNode);
        }
    }
    
    @Override
    public Iterable<K> keys(final K low, final K high) {
        final ArrayList<K> itr = new ArrayList<>();
        
        this.keys(root, itr, low, high);       
        return itr;
    }
    
    Node<K,V> getRoot() {
        return this.root;
    }
    
    private void keys(final Node<K,V> node, final List<K> queue, final K low, final K high) {
        if (node==null) {
            return;
        }
        
        final int compareLow = node.key.compareTo(low);
        final int compareHigh = node.key.compareTo(high);
        
        if (compareLow > 0) {
            keys(node.leftChildNode, queue, low, high);
        }
        if (compareLow >= 0 && compareHigh <= 0) {
            queue.add(node.key);
        }
        if (compareHigh < 0) {
            keys(node.rightChildNode, queue, low, high);
        }
    }
    
    @Override
    public K floor(final K key) {
        if (root==null) {
            return null;
        }
        return this.floor(this.root, key);
    }
    
    private K floor(final Node<K,V> node, final K key) {        
        if (node == null) return null;
        final int compareValue = node.key.compareTo(key);
        if (compareValue > 0) {
            return (K)floor(node.leftChildNode, key);
        } else if (compareValue==0) {
            return node.key;
        } else {
            // this node is less than the key
            final K floorFromRight = (K)floor(node.rightChildNode, key);
            if (floorFromRight!=null) {
                return floorFromRight;
            } else {
                return node.key;
            }
        }        
    }
    
    
    @Override
    public K min() {
        if (this.root==null) {
            return null;
        }
        
        return getMinNode(root).key;
    }    
        
    
    private Node<K,V> deleteMin(Node<K,V> node) {
        if (node.leftChildNode==null) {
            // replace this node with its right child 
            //node.parentNode=null;
            final var rightChild = node.rightChildNode;
            node.rightChildNode=null;
            return rightChild;
        } else {
            node.leftChildNode = deleteMin(node.leftChildNode);
            /*if (node.leftChildNode!=null) {
            //    node.leftChildNode.parentNode = node;
            }*/            
            /*if (getIsRed(node.rightChildNode)) {
                node = rotateLeft(node);
            }*/
            node = this.balanceNode(node);
            return node;
        }
    }
    
    private Node<K,V> getMinNode(final Node<K,V> node) {        
        if (node.leftChildNode==null) {
            return node;
        }
        return getMinNode(node.leftChildNode);
    }
    
    @Override
    public K ceiling(final K key) {
        if (this.root==null) {
            return null;
        }
        return this.ceiling(this.root, key);
    }
    
    private K ceiling(final Node<K,V> node, final K key) {
        if (node==null) {
            return null;
        }
        
        final int compareVal = node.key.compareTo(key);
        if (compareVal == 0) {
            return node.key;
        } else if (compareVal<0) {
            return (K) ceiling(node.rightChildNode, key);
        } else {
            // this node is greater than the key, but need to check  left child
            // for better options
            final K keyFromLeftChild = (K)ceiling(node.leftChildNode, key);
            if (keyFromLeftChild!=null) {
                return keyFromLeftChild;
            } else {
                return node.key;
            }
        }
    }
    
    @Override
    public K max() {
        if (this.root==null) {
            return null;
        }
        return getMaxNode(root).key;
    }
    
    private Node<K,V> deleteMax(final Node<K,V> node) {
        
        if (node.rightChildNode==null) {
            final var leftChild = node.leftChildNode;
           // node.parentNode=null;
            node.leftChildNode=null;
            
            return leftChild;
        } else {
            node.rightChildNode = deleteMax(node.rightChildNode);
            if (node.rightChildNode!=null) {
            //    node.rightChildNode.parentNode = node;
            }
            
            node.sizeN = sizeInternal(node.leftChildNode) + sizeInternal(node.rightChildNode)+1;
            return node;
        }
        
    }
    
    private Node<K,V> getMaxNode(final Node<K,V> node) {
        if (node.rightChildNode==null) {
            return node;
        }
        
        return getMaxNode(node.rightChildNode);
    }
    
    @Override
    public K select(final int rank) {
        if (root==null) {
            return null;
        }
        
        return this.selectInternal(this.root, rank);
    }
    
    protected K selectInternal(final Node<K,V> node, final int rank) {
        if (node == null) {
            return null;
        }
        
        final int leftSize = sizeInternal(node.leftChildNode);
        if (leftSize > rank) {
            return selectInternal(node.leftChildNode, rank);
        } else if (leftSize < rank) {
            return selectInternal(node.rightChildNode, rank-leftSize-1);
        } else {
            return node.key;
        }
    }

}
