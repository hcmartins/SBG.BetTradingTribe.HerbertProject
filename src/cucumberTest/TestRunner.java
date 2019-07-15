package cucumberTest;
 
 
import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.vimalselvam.cucumber.listener.ExtentProperties;
import com.vimalselvam.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
 
@RunWith(Cucumber.class)
@CucumberOptions(
		features = "Feature",
		glue="stepDefinition",		
		plugin = {"json:target/cucumber-reports/Cucumber.json" ,"com.vimalselvam.cucumber.listener.ExtentCucumberFormatter:"},
		monochrome=true
 )
public class TestRunner {
    @BeforeClass
    public static void setup() {
        ExtentProperties extentProperties = ExtentProperties.INSTANCE;
        extentProperties.setReportPath("output/report.html");
    }

    @AfterClass
    public static void teardown() {
        Reporter.loadXMLConfig(new File("extent-config.xml"));
        Reporter.setSystemInfo("user", System.getProperty("user.name"));
        Reporter.setTestRunnerOutput("Herbert");
    }

}