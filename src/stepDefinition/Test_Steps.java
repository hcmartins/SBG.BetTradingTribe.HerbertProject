package stepDefinition;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.Map;
import java.util.Random;
import org.apache.commons.lang3.StringUtils;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import utilities.config;

import org.junit.Assert;

public class Test_Steps {

	private Response response;
	private ValidatableResponse json;
	private RequestSpecification request;
	private int newRand;
	private String EndpointURL = "";

	@Before
	public void beforeScenario(Scenario scenario) {
		Random rand = new Random();

		newRand = rand.nextInt(10000) + 1;
	}

	@Given("^The EndPointUrl is up and running$")
	public void the_EndPointUrl_is_up_and_running_on_port() throws Throwable {
		EndpointURL = config.getproperty("EndPointURL");
		request = RestAssured.with();
		request.given();
	}

	@When("^a user performs a get request to \"([^\"]*)\" with parameters \"([^\"]*)\"$")
	public void a_user_performs_a_get_request_to_with_parameters(String resource, String parameters) {
		System.out.println(EndpointURL + resource + parameters);
		response = request.when().get(EndpointURL + resource + parameters);
		response.asString();

		System.out.println("Get Response:\n" + response.asString());
	}

	@When("^a user performs a post request to \"([^\"]*)\" with below details$")
	public void a_user_performs_a_post_request_to_with_below_details(String resource, String body) throws Throwable {
		if (body.contains("%%New"))
			body = body.replaceAll("%%New", newRand + "");
		System.out.println(EndpointURL + resource);
		response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(body).when()
				.post(EndpointURL + resource);
		response.asString();
		System.out.println("POSt Response\n" + response.asString());
	}

	@When("^a user performs a put request to \"([^\"]*)\" with below details$")
	public void a_user_performs_a_put_request_to_with_below_details(String resource, String body) throws Throwable {
		if (body.contains("%%New"))
			body = body.replaceAll("%%New", newRand + "");
		System.out.println(body);
		response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(body).when()
				.put(EndpointURL + resource);
		response.asString();
		System.out.println("PUT Response\n" + response.asString());
	}

	@When("^a user performs a delete request to \"([^\"]*)\" with below details$")
	public void delete_request(String resource) {
		System.out.println(EndpointURL + resource);
		response = given().contentType(ContentType.JSON).delete(EndpointURL + resource);
		System.out.println(response.asString());
	}

	@Then("status code is (\\d+)")
	public void verify_status_code(int statusCode) {
		json = response.then().statusCode(statusCode);
	}

	@And("^response contains (\\d+) fixtures$")
	public void counting_fixtures(String count) {
		JsonPath jp = new JsonPath(response.asString());
		String numFromsResponse = jp.get("fixtureId.size()").toString();
		Assert.assertTrue(numFromsResponse.equals(count)); // Asserting that the response contains 3 fixtureIds
	}

	@And("^response contains (\\d+) \"([^\"]*)\" tag$")
	public void response_contains_tag(String count, String tagName) {
		JsonPath jp = new JsonPath(response.asString());
		String numFromsResponse = jp.get(tagName + ".size()").toString();
		System.out.println(numFromsResponse);
		Assert.assertTrue(numFromsResponse.equals(count));
	}

	@And("^response contains \"([^\"]*)\"$")
	public void response_contains_String(String arg1) {
		Assert.assertTrue(response.asString().contains(arg1));
	}

	@And("response includes the following$")
	public void response_equals(Map<String, String> responseFields) {
		for (Map.Entry<String, String> field : responseFields.entrySet()) {
			if (StringUtils.isNumeric(field.getValue())) {
				json.body(field.getKey(), equalTo(Integer.parseInt(field.getValue())));
			} else {
				json.body(field.getKey(), equalTo(field.getValue()));
			}
		}
	}

	@And("response includes the following in any order")
	public void response_contains_in_any_order(Map<String, String> responseFields) {

	}

}
