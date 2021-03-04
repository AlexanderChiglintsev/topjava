package ru.javawebinar.topjava;

import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class StopwatchRule extends Stopwatch {
    private static final Logger log = LoggerFactory.getLogger(StopwatchRule.class);
    public static final StringBuilder results = new StringBuilder();

    @Override
    protected void finished(long nanos, Description description) {
        log.debug("Test method: {} passed in {} ms",
                description.getMethodName(),
                TimeUnit.NANOSECONDS.toMillis(nanos));
        results.append(String.format("\t%-25s %s ms \n",
                description.getMethodName(),
                TimeUnit.NANOSECONDS.toMillis(nanos))
        );
    }
}