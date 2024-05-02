/**
 * Name: Walker Black
 * Course: CS-665 Software Designs & Patterns
 * Date: 05/02/2024
 * File Name: Callback.java
 * Description: This class defines the Callback<T> generic interface.
 */

package edu.bu.met.cs665.callbacks;

/**
 * Perform some operation on the provided data type.
 * @param <T> The Class type of the data object.
 */
public interface Callback<T> {
  void call(T data);
}
