package com.jsonplaceholder;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = { "src\\test\\java\\com\\jsonplaceholder\\scenarios" },
        tags = "@PostModel",
        plugin = { "pretty", "html:target/cucumber-report.html" },
        monochrome = true
)
public class RunCucumberTests {

}
