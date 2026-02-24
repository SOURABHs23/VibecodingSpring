package com.learning.concepts;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ╔══════════════════════════════════════════════════════════════╗
 * ║ HashMap vs Hashtable — Key Differences ║
 * ╠══════════════════════════════════════════════════════════════╣
 * ║ ║
 * ║ Feature │ HashMap │ Hashtable ║
 * ║ ────────────────┼────────────────────┼────────────────── ║
 * ║ Thread-safe? │ ❌ No │ ✅ Yes (synchronized)║
 * ║ Null keys? │ ✅ 1 null key │ ❌ No null keys ║
 * ║ Null values? │ ✅ Multiple nulls │ ❌ No null values ║
 * ║ Performance │ ⚡ Faster │ 🐢 Slower ║
 * ║ Since │ Java 1.2 │ Java 1.0 (legacy) ║
 * ║ Iterator │ Fail-fast │ Enumerator ║
 * ║ Extends │ AbstractMap │ Dictionary ║
 * ║ Best for │ Single-threaded apps│ Legacy code only ║
 * ║ ║
 * ║ 🏆 Modern recommendation: ║
 * ║ Use HashMap for single-threaded code ║
 * ║ Use ConcurrentHashMap for multi-threaded code ║
 * ║ Avoid Hashtable — it's legacy! ║
 * ╚══════════════════════════════════════════════════════════════╝
 */
public class HashMapVsHashtableDemo {

    public static void main(String[] args) {

        System.out.println("═══════════════════════════════════════");
        System.out.println("  🗺️  HashMap vs Hashtable Demo");
        System.out.println("═══════════════════════════════════════\n");

        // ──────────────────────────────────────────
        // 1️⃣ HashMap — Modern, Fast, Allows Nulls
        // ──────────────────────────────────────────
        System.out.println("──── 1. HashMap Demo ────");

        HashMap<String, String> hashMap = new HashMap<>();

        // Adding entries
        hashMap.put("name", "Sourabh");
        hashMap.put("language", "Java");
        hashMap.put("framework", "Spring Boot");

        // ✅ HashMap allows ONE null key and MULTIPLE null values
        hashMap.put(null, "I am a null key!"); // null key is allowed
        hashMap.put("nullValue", null); // null value is allowed

        System.out.println("HashMap contents: " + hashMap);
        System.out.println("Value for null key: " + hashMap.get(null));
        System.out.println("Value for 'nullValue' key: " + hashMap.get("nullValue"));
        System.out.println();

        // ──────────────────────────────────────────
        // 2️⃣ Hashtable — Legacy, Thread-safe, NO Nulls
        // ──────────────────────────────────────────
        System.out.println("──── 2. Hashtable Demo ────");

        Hashtable<String, String> hashtable = new Hashtable<>();

        // Adding entries
        hashtable.put("name", "Sourabh");
        hashtable.put("language", "Java");
        hashtable.put("framework", "Spring Boot");

        System.out.println("Hashtable contents: " + hashtable);

        // ❌ Hashtable does NOT allow null keys
        try {
            hashtable.put(null, "This will crash!");
        } catch (NullPointerException e) {
            System.out.println("❌ Null KEY not allowed in Hashtable! → " + e.getClass().getSimpleName());
        }

        // ❌ Hashtable does NOT allow null values
        try {
            hashtable.put("test", null);
        } catch (NullPointerException e) {
            System.out.println("❌ Null VALUE not allowed in Hashtable! → " + e.getClass().getSimpleName());
        }
        System.out.println();
    }
}
