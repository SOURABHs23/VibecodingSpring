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

        // Common StringBuilder methods
        System.out.println("── Common StringBuilder Methods ──");
        StringBuilder demo = new StringBuilder("Java");

        demo.append(" is awesome"); // Add at end
        System.out.println("append():  " + demo);

        demo.insert(5, "17 "); // Insert at position
        System.out.println("insert():  " + demo);

        demo.replace(0, 4, "Spring"); // Replace range
        System.out.println("replace(): " + demo);

        demo.delete(6, 9); // Delete range
        System.out.println("delete():  " + demo);

        demo.reverse(); // Reverse the string
        System.out.println("reverse(): " + demo);

        System.out.println("length():  " + demo.length());
        System.out.println("toString(): " + demo.toString());
        System.out.println();

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

        // ──────────────────────────────────────────
        // 4️⃣ Performance Comparison
        // ──────────────────────────────────────────
        System.out.println("──── 4. Performance Comparison ────");
        int iterations = 500_000;

        // String concatenation (SLOWEST — creates new object each time)
        long start = System.nanoTime();
        String result = "";
        for (int i = 0; i < 50_000; i++) { // Using fewer iterations for String (it's THAT slow)
            result = result + "a";
        }
        long stringTime = System.nanoTime() - start;

        // StringBuilder (FASTEST)
        start = System.nanoTime();
        StringBuilder sbPerf = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            sbPerf.append("a");
        }
        long stringBuilderTime = System.nanoTime() - start;

        // StringBuffer (SLOWER than StringBuilder due to synchronization)
        start = System.nanoTime();
        StringBuffer sbufPerf = new StringBuffer();
        for (int i = 0; i < iterations; i++) {
            sbufPerf.append("a");
        }
        long stringBufferTime = System.nanoTime() - start;

        System.out.println("String        (50K concat):   " + stringTime / 1_000_000 + " ms  🐌 VERY SLOW");
        System.out.println("StringBuilder (500K appends): " + stringBuilderTime / 1_000_000 + " ms  ⚡ FASTEST");
        System.out.println(
                "StringBuffer  (500K appends): " + stringBufferTime / 1_000_000 + " ms  🔒 Thread-safe but slower");
        System.out.println();

        // ──────────────────────────────────────────
        // 5️⃣ Real-World Example: Building an HTML page
        // ──────────────────────────────────────────
        System.out.println("──── 5. Real-World Example: Building HTML ────");

        // ❌ BAD: Using String concatenation
        String html = "";
        html += "<html>";
        html += "<body>";
        html += "<h1>Hello World</h1>";
        html += "</body>";
        html += "</html>";
        // This created 5 temporary String objects — wasteful!

        // ✅ GOOD: Using StringBuilder
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<html>")
                .append("<body>") // .append() returns 'this', enabling chaining!
                .append("<h1>Hello World</h1>")
                .append("</body>")
                .append("</html>");
        // This modified the SAME object 5 times — efficient!

        System.out.println("HTML (String):        " + html);
        System.out.println("HTML (StringBuilder): " + htmlBuilder.toString());
        System.out.println("Both produce same result, but StringBuilder is much more efficient!\n");

        // ──────────────────────────────────────────
        // Summary
        // ──────────────────────────────────────────
        System.out.println("═══════════════════════════════════════════");
        System.out.println("  📝 Summary:");
        System.out.println("  • String        → Immutable, slow for concatenation");
        System.out.println("  • StringBuilder → Mutable, fast, NOT thread-safe (USE THIS! ⭐)");
        System.out.println("  • StringBuffer  → Mutable, slower, thread-safe (rarely needed)");
        System.out.println("═══════════════════════════════════════════");
    }
}
