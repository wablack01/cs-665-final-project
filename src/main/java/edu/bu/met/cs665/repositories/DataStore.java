/**
 * Name: Walker Black
 * Course: CS-665 Software Designs & Patterns
 * Date: 05/02/2024
 * File Name: DataStore.java
 * Description: This interface defines the method signatures of a DataStore
 */

package edu.bu.met.cs665.repositories;

/**
 * The DataStore interface defines operations for storing,
 * accessing, and deleting data.
 * @param <K> Class type used to find data in the DataStore
 * @param <V> Object type stored in the DataStore
 */
public interface DataStore<K, V> {
  V put(K key, V value);
  V get(K key);
  V delete(K key);
}
