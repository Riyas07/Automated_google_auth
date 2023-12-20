package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"html:Reports/Authorization_grant_flow.html","json:Reports/Authorization_grant_flow.json"},
features = "Feature",
glue = "stepDefinitions",
tags = "@Authorization_grant_flow")
public class Authorization_grant_flow_Runner {
}
