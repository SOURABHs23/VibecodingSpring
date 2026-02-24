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

        // ──────────────────────────────────────────
        // 3️⃣ Thread-Safety Comparison
        // ──────────────────────────────────────────
        System.out.println("──── 3. Thread-Safety Comparison ────");
        System.out.println("HashMap  → ❌ NOT thread-safe (fast, no synchronization overhead)");
        System.out.println("Hashtable → ✅ Thread-safe (slower, every method is synchronized)");
        System.out.println();

        // Let's prove it by checking if methods are synchronized
        // In Hashtable, methods like put(), get(), remove() are all synchronized
        // This means only ONE thread can access the Hashtable at a time

        // ──────────────────────────────────────────
        // 4️⃣ Performance Comparison
        // ──────────────────────────────────────────
        System.out.println("──── 4. Performance Comparison ────");

        int iterations = 1_000_000;

        // HashMap speed test
        Map<Integer, Integer> perfHashMap = new HashMap<>();
        long start = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            perfHashMap.put(i, i);
        }
        long hashMapTime = System.nanoTime() - start;

        // Hashtable speed test
        Map<Integer, Integer> perfHashtable = new Hashtable<>();
        start = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            perfHashtable.put(i, i);
        }
        long hashtableTime = System.nanoTime() - start;

        System.out.println("HashMap   time for " + iterations + " puts: " + hashMapTime / 1_000_000 + " ms");
        System.out.println("Hashtable time for " + iterations + " puts: " + hashtableTime / 1_000_000 + " ms");
        System.out.println("⚡ HashMap is ~" + (hashtableTime / Math.max(hashMapTime, 1)) + "x faster!");
        System.out.println();

        // ──────────────────────────────────────────
        // 5️⃣ ConcurrentHashMap — The BEST of Both Worlds
        // ──────────────────────────────────────────
        System.out.println("──── 5. ConcurrentHashMap (Modern Alternative) ────");
        System.out.println("🏆 Use ConcurrentHashMap instead of Hashtable!");
        System.out.println("   ✅ Thread-safe (uses segment locking, not full lock)");
        System.out.println("   ⚡ Much faster than Hashtable");
        System.out.println("   ❌ No null keys or values (like Hashtable)");

        ConcurrentHashMap<String, String> concurrentMap = new ConcurrentHashMap<>();
        concurrentMap.put("key1", "value1");
        concurrentMap.put("key2", "value2");
        System.out.println("ConcurrentHashMap: " + concurrentMap);

        System.out.println();
        System.out.println("═══════════════════════════════════════");
        System.out.println("  📝 Summary:");
        System.out.println("  • Use HashMap       → for single-threaded apps (MOST COMMON)");
        System.out.println("  • Use ConcurrentHashMap → for multi-threaded apps");
        System.out.println("  • Avoid Hashtable   → it's legacy/outdated!");
        System.out.println("═══════════════════════════════════════");
    }
}
