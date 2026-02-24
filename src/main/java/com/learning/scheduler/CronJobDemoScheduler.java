package com.learning.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * ======================================================================
 *                 Cron Jobs & Scheduler in Spring Boot
 * ======================================================================
 *
 *  WHAT IS A CRON JOB?
 *  -------------------
 *  A Cron Job is a task that runs AUTOMATICALLY at scheduled
 *  times. Think of it like setting an alarm - it triggers
 *  your code at specific times without any user interaction.
 *
 *  Real-world examples:
 *  - Send daily email reports at 9:00 AM
 *  - Clean up old logs every midnight
 *  - Sync data from API every 5 minutes
 *  - Generate monthly invoices on the 1st of each month
 *  - Health check every 30 seconds
 *
 *  HOW DOES SPRING SCHEDULER WORK?
 *  --------------------------------
 *  1. Add @EnableScheduling on your main application class
 *  2. Create a @Component class
 *  3. Add @Scheduled annotation on methods you want to schedule
 *
 *  CRON EXPRESSION FORMAT (6 fields in Spring):
 *  ─────────────────────────────────────────────
 *  Field 1: second       (0-59)
 *  Field 2: minute       (0-59)
 *  Field 3: hour         (0-23)
 *  Field 4: day of month (1-31)
 *  Field 5: month        (1-12)
 *  Field 6: day of week  (0-7, SUN-SAT)
 *
 *  Examples:
 *  "0 0 9 * * *"         -> Every day at 9:00 AM
 *  "0 0 0 * * *"         -> Every midnight
 *  "0 0/5 * * * *"       -> Every 5 minutes
 *  "0 0 9 * * MON"       -> Every Monday at 9 AM
 *  "0 0 0 1 * *"         -> 1st of every month at midnight
 *
 *  IMPORTANT: You must add @EnableScheduling to your main class!
 *  See HelloSpringBootApplication.java
 */
@Component // Makes this class a Spring-managed bean
public class CronJobDemoScheduler {

    // Counter to track how many times each job runs
    private final AtomicInteger fixedRateCount = new AtomicInteger(0);
    private final AtomicInteger fixedDelayCount = new AtomicInteger(0);
    private final AtomicInteger cronCount = new AtomicInteger(0);

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    // ──────────────────────────────────────────────────────
    // 1. @Scheduled(fixedRate = 10000) — Fixed Rate
    // Runs every 10 seconds, regardless of previous execution
    // ──────────────────────────────────────────────────────
    @Scheduled(fixedRate = 10000) // 10000 ms = 10 seconds
    public void fixedRateTask() {
        int count = fixedRateCount.incrementAndGet();
        System.out.println("[Fixed Rate] Run #" + count
                + " | Time: " + LocalDateTime.now().format(FORMATTER)
                + " | Runs every 10 seconds (doesn't wait for previous to finish)");
    }

    // ──────────────────────────────────────────────────────
    // 2. @Scheduled(fixedDelay = 15000) — Fixed Delay
    // Waits 15 seconds AFTER the previous execution completes
    // ──────────────────────────────────────────────────────
    @Scheduled(fixedDelay = 15000) // 15000 ms = 15 seconds
    public void fixedDelayTask() {
        int count = fixedDelayCount.incrementAndGet();
        System.out.println("[Fixed Delay] Run #" + count
                + " | Time: " + LocalDateTime.now().format(FORMATTER)
                + " | Waits 15 sec after previous completes");
    }

    // ──────────────────────────────────────────────────────
    // 3. @Scheduled(cron = "...") — Cron Expression
    // Most powerful — runs at specific times using cron syntax
    // This one runs every 30 seconds
    // ──────────────────────────────────────────────────────
    @Scheduled(cron = "0/30 * * * * *") // Every 30 seconds
    public void cronExpressionTask() {
        int count = cronCount.incrementAndGet();
        System.out.println("[Cron Job] Run #" + count
                + " | Time: " + LocalDateTime.now().format(FORMATTER)
                + " | Cron: '0/30 * * * * *' -> every 30 seconds");
    }

    // ──────────────────────────────────────────────────────
    // 4. Real-World Example: Daily Cleanup Job
    // This would normally run at midnight, but for demo
    // purposes we're using initialDelay to show the concept
    // ──────────────────────────────────────────────────────
    // @Scheduled(cron = "0 0 0 * * *") // <- Uncomment for real midnight job
    @Scheduled(fixedRate = 60000, initialDelay = 5000) // Every 60 sec, starts after 5 sec delay
    public void dailyCleanupSimulation() {
        System.out.println("[Cleanup Job] Time: " + LocalDateTime.now().format(FORMATTER)
                + " | Simulating daily cleanup task..."
                + " | In production: would run at midnight with cron = '0 0 0 * * *'");
    }

    // ──────────────────────────────────────────────────────
    // KEY DIFFERENCES:
    //
    // fixedRate: Timer starts when task STARTS
    // Task starts every N ms, even if previous is still running
    // Example: Start task every 10 sec, even if task takes 3 sec
    //
    // fixedDelay: Timer starts when task ENDS
    // Waits N ms after previous task finishes, then starts again
    // Example: Wait 15 sec after task finishes, then run again
    //
    // cron: Most flexible, uses cron expression
    // Can specify exact second, minute, hour, day, month, day-of-week
    // Example: "0 0 9 * * MON-FRI" -> 9 AM on weekdays
    //
    // initialDelay: Delays the FIRST execution by N ms after app starts
    // ──────────────────────────────────────────────────────
}
