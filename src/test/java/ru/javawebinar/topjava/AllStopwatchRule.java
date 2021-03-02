package ru.javawebinar.topjava;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.repository.inmemory.InMemoryMealRepository;

public class AllStopwatchRule implements TestRule {
    private static final Logger log = LoggerFactory.getLogger(InMemoryMealRepository.class);

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                base.evaluate();
                StringBuilder str = new StringBuilder();
                str.append(" \nResult time for tests passes in Class: ")
                        .append(description.getTestClass().getName()).append("\n");
                StopwatchRule.results.forEach((a, b) -> str.append("*** Test ")
                        .append(a).append(" passed in ")
                        .append(b).append(" ms. \n"));
                log.debug(str.toString());
                StopwatchRule.results.clear();
            }
        };
    }
}