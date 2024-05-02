/**
 * Name: Walker Black
 * Course: CS-665 Software Designs & Patterns
 * Date: 05/02/2024
 * File Name: ClientTier.java
 * Description: This enum is describes the "tier" of a Client.
 */

package edu.bu.met.cs665.client;

/**
 * The ClientTier represents the tier of a Client.
 * Servers may grant different privileges to different ClientTiers.
 */
public enum ClientTier {
  STANDARD,
  PREMIUM,
  ADMIN
}
