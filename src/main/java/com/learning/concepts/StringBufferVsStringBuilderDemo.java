package com.learning.concepts;

/**
 * ╔══════════════════════════════════════════════════════════════════╗
 * ║ StringBuffer vs StringBuilder — Key Differences ║
 * ╠══════════════════════════════════════════════════════════════════╣
 * ║ ║
 * ║ Feature │ StringBuffer │ StringBuilder ║
 * ║ ─────────────────┼─────────────────────┼─────────────────── ║
 * ║ Thread-safe? │ ✅ Yes (synchronized)│ ❌ No ║
 * ║ Performance │ 🐢 Slower │ ⚡ Faster ║
 * ║ Mutable? │ ✅ Yes │ ✅ Yes ║
 * ║ Since │ Java 1.0 │ Java 1.5 ║
 * ║ Best for │ Multi-threaded │ Single-threaded ║
 * ║ ║
 * ║ Also compared: String (immutable, creates new objects) ║
 * ║ ║
 * ║ 🏆 Use StringBuilder in 99% of cases (single-threaded) ║
 * ╚══════════════════════════════════════════════════════════════════╝
 *
 * WHY do we need StringBuffer/StringBuilder?
 * ──────────────────────────────────────────
 * String in Java is IMMUTABLE. Every time you modify a String,
 * a NEW object is created in memory. This is wasteful when
 * doing lots of string concatenation (e.g., building HTML, SQL, logs).
 *
 * StringBuffer and StringBuilder are MUTABLE — they modify
 * the same object in memory, making them much more efficient.
 */
public class StringBufferVsStringBuilderDemo {

    public static void main(String[] args) {

        System.out.println("═══════════════════════════════════════════");
        System.out.println("  📝 String vs StringBuffer vs StringBuilder");
        System.out.println("═══════════════════════════════════════════\n");

        // ──────────────────────────────────────────
        // 1️⃣ String — Immutable (creates NEW object each time)
        // ──────────────────────────────────────────
        System.out.println("──── 1. String (Immutable) ────");

        String str = "Hello";
        System.out.println("Original string: \"" + str + "\"  (hashCode: " + System.identityHashCode(str) + ")");

        str = str + " World"; // ← This creates a BRAND NEW String object!
        System.out.println("After concat:    \"" + str + "\" (hashCode: " + System.identityHashCode(str) + ")");
        System.out.println("⚠️  Notice: hashCode changed! A NEW object was created in memory.");
        System.out.println("   The old \"Hello\" is now garbage (wasted memory).\n");

        // ──────────────────────────────────────────
        // 2️⃣ StringBuilder — Mutable, Fast, NOT thread-safe
        // ──────────────────────────────────────────
        System.out.println("──── 2. StringBuilder (Mutable, Fast) ────");

        StringBuilder sb = new StringBuilder("Hello");
        System.out.println("Original: \"" + sb + "\"  (hashCode: " + System.identityHashCode(sb) + ")");

        sb.append(" World"); // ← Modifies the SAME object, no new object created!
        System.out.println("After append: \"" + sb + "\" (hashCode: " + System.identityHashCode(sb) + ")");
        System.out.println("✅ Same hashCode! The same object was modified (no waste).\n");


        // ──────────────────────────────────────────
        // 3️⃣ StringBuffer — Mutable, Slower, Thread-safe
        // ──────────────────────────────────────────
        System.out.println("──── 3. StringBuffer (Mutable, Thread-safe) ────");

        StringBuffer sbuf = new StringBuffer("Hello");
        System.out.println("Original: \"" + sbuf + "\"  (hashCode: " + System.identityHashCode(sbuf) + ")");

        sbuf.append(" World");
        System.out.println("After append: \"" + sbuf + "\" (hashCode: " + System.identityHashCode(sbuf) + ")");
        System.out.println("✅ Same hashCode! Same object modified (just like StringBuilder).");
        System.out.println("🔒 But every method is synchronized → safe for multi-threaded use.\n");

        // StringBuffer has the SAME methods as StringBuilder
        // The ONLY difference is that StringBuffer methods are synchronized

    }
}
