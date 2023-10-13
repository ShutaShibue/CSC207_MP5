package structures;

import static java.lang.reflect.Array.newInstance;
import java.util.ArrayList;

/**
 * A basic implementation of Associative Arrays with keys of type K
 * and values of type V. Associative Arrays store key/value pairs
 * and permit you to look up values by key.
 *
 * @author Shuta Shibue
 * @author Samuel A. Rebelsky
 */
public class AssociativeArray<K, V> {
  // +-----------+---------------------------------------------------
  // | Constants |
  // +-----------+

  /**
   * The default capacity of the initial array.
   */
  static final int DEFAULT_CAPACITY = 16;

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The size of the associative array (the number of key/value pairs).
   */
  int size;

  /**
   * The array of key/value pairs.
   */
  KVPair<K, V> pairs[];

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new, empty associative array.
   */
  @SuppressWarnings({ "unchecked" })
  public AssociativeArray() {
    // Creating new arrays is sometimes a PITN.
    this.pairs = (KVPair<K, V>[]) newInstance((new KVPair<K, V>()).getClass(),
        DEFAULT_CAPACITY);
    this.size = 0;
  } // AssociativeArray()

  // +------------------+--------------------------------------------
  // | Standard Methods |
  // +------------------+

  /**
   * Create a copy of this AssociativeArray.
   */
  public AssociativeArray<K, V> clone() {
    AssociativeArray<K, V> arr = new AssociativeArray<>();
    for (int i = 0; i < pairs.length; i++) {
      if(pairs[i] == null) continue;
      arr.set(pairs[i].key, pairs[i].value);
    }
    return arr;
  } // clone()

  /**
   * Convert the array to a string.
   */
  public String toString() {
    String str = "{";
    for (int i = 0; i < pairs.length; i++) {
      if(pairs[i] == null) continue;
      str += (" " + pairs[i].key + ": " + pairs[i].value);
      if(i != size - 1) str += ",";
    }
    str += " }";


    return str;
  } // toString()

  // +----------------+----------------------------------------------
  // | Public Methods |
  // +----------------+

  /**
   * Set the value associated with key to value. Future calls to
   * get(key) will return value.
   */
  public void set(K key, V value) {
    //if key is null, throw error. Value being null is fine.
    try {
      // If the key already exists, size is same
      int index = find(key);
      pairs[index] = new KVPair<K, V>(key, value);
    } catch (Exception e) {
      //find null space, set it and increment size
      for (int i = 0; i < pairs.length; i++) {
        if(pairs[i] == null){
          size++;
          pairs[i] = new KVPair<K, V>(key, value);
          return;
        }
      }
      // If there is no space
      expand();
      pairs[size+1] = new KVPair<K, V>(key, value);
      size++;
    }
  } // set(K,V)

  /**
   * Get the value associated with key.
   *
   * @throws KeyNotFoundException
   *                              when the key does not appear in the associative
   *                              array.
   */
  public V get(K key) throws KeyNotFoundException {
    return pairs[find(key)].value;
  } // get(K)

  /**
   * Determine if key appears in the associative array.
   */
  public boolean hasKey(K key) {
    try{
      find(key);
      return true;
    }
    catch(Exception e){
      return false;
    }
  } // hasKey(K)

  /**
   * Remove the key/value pair associated with a key. Future calls
   * to get(key) will throw an exception. If the key does not appear
   * in the associative array, does nothing.
   */
  public void remove(K key) {
     try{
      pairs[find(key)] = null;
      size --;
    }
    catch(Exception e){
      return;
    }
  } // remove(K)

  /**
   * Determine how many values are in the associative array.
   */
  public int size() {
    return this.size;
  } // size()

  // +-----------------+---------------------------------------------
  // | Private Methods |
  // +-----------------+

  /**
   * Expand the underlying array.
   */
  public void expand() {
    this.pairs = java.util.Arrays.copyOf(this.pairs, this.pairs.length * 2);
  } // expand()

  /**
   * Find the index of the first entry in `pairs` that contains key.
   * If no such entry is found, throws an exception.
   */
  public int find(K key) throws KeyNotFoundException {
    for (int i = 0; i < pairs.length; i++) {
      if(pairs[i] == null) continue;
      if(pairs[i].key == null) {
        if(key == null) return i; //null can't be compared using Object.equals(), so separated it.
      }
      else if(pairs[i].key.equals(key)) return i;
    }
    throw new KeyNotFoundException();
  } // find(K)

    /**
   * returns first non-null element
   * @return
   */
  K firstKey(){
    for (int i = 0; i < pairs.length; i++) {
      if(pairs[i] == null) continue;
      else return pairs[i].key;
    }
    return null;
  }

  public String[] keys(){
    String[] ret = new String[this.size];
    int n = 0; //element captured
    
    for (int i = 0; i < pairs.length; i++) {
      if(pairs[i] == null) continue;
      ret[n] = pairs[i].key.toString();
      n++;
    }
    return ret;
  }
} // class AssociativeArray