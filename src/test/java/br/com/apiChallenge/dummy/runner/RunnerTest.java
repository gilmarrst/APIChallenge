package br.com.apiChallenge.dummy.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = "", plugin = { "pretty",
		"html:target/report/html" }, monochrome = true,
		tags = { "@ApiChallenge" })
public class RunnerTest {
}
