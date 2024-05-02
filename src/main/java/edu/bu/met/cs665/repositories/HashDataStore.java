/**
 * Name: Walker Black
 * Course: CS-665 Software Designs & Patterns
 * Date: 05/02/2024
 * File Name: HashDataStore.java
 * Description: This class is responsible for the methods and attributes of a HashDataStore
 */

package edu.bu.met.cs665.repositories;

import java.util.HashMap;

/**
 * The HashDataStore class is a HashMap based implementation of the
 * DataStore interface
 * @param <K> Class type of keys used to access data.
 * @param <V> Class type of values stored in the HashMap
 */
public class HashDataStore<K, V> implements DataStore<K, V> {
  private HashMap<K, V> data = new HashMap<>();

  @Override
  public V put(K key, V value) {
    return data.put(key, value);
  }

  @Override
  public V get(K key) {
    return data.get(key);
  }

  @Override
  public V delete(K key) {
    return data.remove(key);
  }
}
