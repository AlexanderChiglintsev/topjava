package ru.javawebinar.topjava;

import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class StopwatchRule extends Stopwatch {
    private static final Logger log = LoggerFactory.getLogger(StopwatchRule.class);
    public static final Map<String, Long> results = new LinkedHashMap<>();

    @Override
    protected void finished(long nanos, Description description) {
        log.debug("Test method: {} passed in {} ms.",
                description.getMethodName(),
                TimeUnit.NANOSECONDS.toMicros(nanos));
        results.put(description.getMethodName(), TimeUnit.NANOSECONDS.toMillis(nanos));
    }

    /*private static final Logger log = LoggerFactory.getLogger(StopwatchRule.class);
    public static final Map<String, Long> results = new HashMap<>();
    private long time;
    private long timeInterval;

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                time = System.currentTimeMillis();
                base.evaluate();
                timeInterval = System.currentTimeMillis() - time;
                log.debug("Test method: {}() passed in {} ms.", description.getMethodName(), timeInterval);
                results.put(description.getMethodName() + "()", timeInterval);
            }
        };
    }*/
}