package runner;


import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

// Configuration for the test scenarios
@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/java/features/Pets.feature",
		glue= {"steps","runner"},
		plugin= {"json:target/jsonReports/cucumber-report.json"}
		)
public class TestRunner {
	
	

}
