package ru.javawebinar.topjava;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.repository.inmemory.InMemoryMealRepository;

import java.util.HashMap;
import java.util.Map;

public class StopwatchRule implements TestRule {
    private static final Logger log = LoggerFactory.getLogger(InMemoryMealRepository.class);
    private long time;
    private long timeInterval;
    public static final Map<String, Long> results = new HashMap<>();

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
    }
}