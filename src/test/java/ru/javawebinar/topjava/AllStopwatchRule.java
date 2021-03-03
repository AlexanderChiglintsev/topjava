package ru.javawebinar.topjava;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AllStopwatchRule implements TestRule {
    private static final Logger log = LoggerFactory.getLogger(AllStopwatchRule.class);

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                base.evaluate();
                StringBuilder str = new StringBuilder();
                str.append(" \n\nResult time for tests passes in Class: ")
                        .append(description.getTestClass().getName()).append("\n");
                StopwatchRule.results.forEach((a, b) -> str.append("   ")
                        .append(String.format("%-25s", a))
                        .append(b).append(" ms. \n"));
                log.debug(str.toString());
                StopwatchRule.results.clear();
            }
        };
    }
}